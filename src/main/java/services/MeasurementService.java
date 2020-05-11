package services;

import db.ConnectionPool;
import db.IcesiDatabase;
import model.Measurement;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.ArrayList;

@Stateless
@Path("measurement")
public class MeasurementService {
    @Path("insert/{idSector}")
    @POST
    @Consumes("application/json")//recibo
    @Produces("application/json")//enviar
    public Measurement insertMeasurement(Measurement measurement,@PathParam("idSector") String idSector){
        IcesiDatabase icesiDataBase = ConnectionPool.getAvailableConnection();
        icesiDataBase.insertMeasurement(measurement, idSector);
        icesiDataBase.setBusy(false);
        return measurement;
    }

    @GET
    @Path("getall")
    @Produces("application/json")
    public ArrayList<Measurement> getAllMeasurements(){
        IcesiDatabase icesiDataBase = ConnectionPool.getAvailableConnection();
        ArrayList<Measurement> measurements = icesiDataBase.getAllMeasurements();
        icesiDataBase.setBusy(false);
        return measurements;
    }
    @GET
    @Path("byid")
    @Produces("application/json")
    public Measurement getMeasurementByID(@QueryParam("id") String id){
        IcesiDatabase icesiDataBase = ConnectionPool.getAvailableConnection();
        Measurement measurement = icesiDataBase.getMeasurementById(id);
        icesiDataBase.setBusy(false);
        return measurement;
    }
    @PUT
    @Path("update")
    @Consumes("application/json")
    @Produces("application/json")
    public Measurement modifyMeasurement(Measurement measurement){
        IcesiDatabase icesiDataBase = ConnectionPool.getAvailableConnection();
        icesiDataBase.modifyMeasurement(measurement);
        icesiDataBase.setBusy(false);
        return measurement;
    }

    @DELETE
    @Path("delete/{id}")
    public void deleteMeasurementByID(@PathParam("id")String id){
        IcesiDatabase icesiDataBase = ConnectionPool.getAvailableConnection();
        icesiDataBase.deleteMeasurementById(id);
        icesiDataBase.setBusy(false);
    }
}
