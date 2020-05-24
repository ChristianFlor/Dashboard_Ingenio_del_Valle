package services;

import db.ConnectionPool;
import db.IcesiDatabase;
import model.Measurement;
import model.Simulation;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.UUID;

@Stateless
@Path("simulation")
public class SimulationService {

    @GET
    @Path("simulate/{mode}/{periodNum}")
    @Produces("application/json")
    public ArrayList<Double> simulate(@PathParam("mode") String mode, @PathParam("periodNum") String periodNum) {
        Simulation simulation = new Simulation();
        return simulation.simulate(mode.charAt(0), Integer.parseInt(periodNum));
    }
    @GET
    @Path("get/data/{sector}")
    @Produces("application/json")
    public ArrayList<ArrayList<Double>> getData(@PathParam("sector")String sector) {
        IcesiDatabase icesiDatabase = ConnectionPool.getAvailableConnection();
        ArrayList<Measurement> measurements = icesiDatabase.getListMeasurement(sector);
        ArrayList<ArrayList<Double>> arrayLists = new ArrayList<>();
        ArrayList<Double> ph = new ArrayList<>();
        ArrayList<Double> hum = new ArrayList<>();
        ArrayList<Double> co2 = new ArrayList<>();
        ArrayList<Double> temperature = new ArrayList<>();
        measurements.forEach(e -> {
            ph.add(e.getpH());
            hum.add(e.getHumidity());
            co2.add(e.getCo2());
            temperature.add(e.getTemperature());
        });
        arrayLists.add(ph);
        arrayLists.add(hum);
        arrayLists.add(co2);
        arrayLists.add(temperature);
        icesiDatabase.setBusy(false);
        return arrayLists;
    }

    @POST
    @Path("produce/{sector}/{num}")
    public void postSimulation(@PathParam("sector")String sector,@PathParam("num") String num) {
        IcesiDatabase icesiDatabase = ConnectionPool.getAvailableConnection();
        int n = Integer.parseInt(num);
        Simulation simulation = new Simulation();
        ArrayList<Double> ph = simulation.simulate(Simulation.PH,n);
        ArrayList<Double> hum = simulation.simulate(Simulation.HUMIDITY,n);
        ArrayList<Double> co2 = simulation.simulate(Simulation.CO2,n);
        ArrayList<Double> temperature = simulation.simulate(Simulation.TEMPERATURE,n);
        for (int i = 0; i < n; i++) {
            Measurement measurement = new Measurement(UUID.randomUUID().toString(),ph.get(i),hum.get(i),co2.get(i),temperature.get(i),Long.parseLong("0"));
            icesiDatabase.insertMeasurement(measurement,sector);
        }
        icesiDatabase.setBusy(false);
    }
}
