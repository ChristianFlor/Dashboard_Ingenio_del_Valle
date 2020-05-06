package services;

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
        IcesiDatabase icesiDataBase = new IcesiDatabase();
        icesiDataBase.insertMeasurement(measurement, idSector);
        icesiDataBase.closeConnection();
        return measurement;
    }

    @GET
    @Path("getall")
    @Produces("application/json")
    public ArrayList<Measurement> getAllMeasurements(){
        IcesiDatabase icesiDataBase = new IcesiDatabase();
        ArrayList<Measurement> measurements = icesiDataBase.getAllMeasurements();
        icesiDataBase.closeConnection();
        return measurements;
    }
    @GET
    @Path("byid")
    @Produces("application/json")
    public Measurement getMeasurementByID(@QueryParam("id") String id){
        IcesiDatabase icesiDataBase = new IcesiDatabase();
        Measurement measurement = icesiDataBase.getMeasurementById(id);
        icesiDataBase.closeConnection();
        return measurement;
    }
    @PUT
    @Path("update")
    @Consumes("application/json")
    @Produces("application/json")
    public Measurement modifyMeasurement(Measurement measurement){
        IcesiDatabase icesiDataBase = new IcesiDatabase();
        icesiDataBase.modifyMeasurement(measurement);
        icesiDataBase.closeConnection();
        return measurement;
    }

    @DELETE
    @Path("delete/{id}")
    public void deleteMeasurementByID(@PathParam("id")String id){
        IcesiDatabase icesiDataBase = new IcesiDatabase();
        icesiDataBase.deleteMeasurementById(id);
        icesiDataBase.closeConnection();
    }
}
