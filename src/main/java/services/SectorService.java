package services;

import db.IcesiDatabase;
import model.Measurement;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.ArrayList;

@Stateless
@Path("sector")
public class SectorService {
    @Path("insert/{id}/{idEng}")
    @POST
    public void registerSector(@PathParam("id")String id,@PathParam("idEng") String idEngAssigned){
        IcesiDatabase icesiDataBase = new IcesiDatabase();
        icesiDataBase.insertSector(id,idEngAssigned);
        icesiDataBase.closeConnection();
    }
    @PUT
    @Path("update/{id}/{idEng}")
    public void modifySector(@PathParam("id")String id,@PathParam("idEng") String idEngAssigned){
        IcesiDatabase icesiDataBase = new IcesiDatabase();
        icesiDataBase.modifySector(id, idEngAssigned);
        icesiDataBase.closeConnection();
    }

    @DELETE
    @Path("delete/{id}")
    public void deleteSectorByID(@PathParam("id")String id){
        IcesiDatabase icesiDataBase = new IcesiDatabase();
        icesiDataBase.deleteSectorById(id);
        icesiDataBase.closeConnection();
    }

    @GET
    @Path("list/{sectorID}")
    @Produces("application/json")
    public ArrayList<Measurement> getListMeasurements(@PathParam("sectorID") String sectorID){
        IcesiDatabase icesiDataBase = new IcesiDatabase();
        ArrayList<Measurement> measurements = icesiDataBase.getListMeasurement(sectorID);
        icesiDataBase.closeConnection();
        return measurements;
    }

}
