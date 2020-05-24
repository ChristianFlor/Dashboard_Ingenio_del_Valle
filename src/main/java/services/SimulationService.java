package services;

        import db.ConnectionPool;
        import db.IcesiDatabase;
        import model.Measurement;
        import model.Simulation;

        import javax.ejb.Stateless;
        import javax.ws.rs.*;
        import java.lang.reflect.Array;
        import java.util.ArrayList;

@Stateless
@Path("simulation")
public class SimulationService {

    @GET
    @Path("simulate/{mode}/{periodNum}")
    @Produces("application/json")
    public ArrayList<Double> simulate(@PathParam("mode") String mode, @PathParam("periodNum") String periodNum) {
        Simulation simulation = new Simulation();
        System.out.println(mode.charAt(0)+" "+periodNum);

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
        return arrayLists;
    }
}
