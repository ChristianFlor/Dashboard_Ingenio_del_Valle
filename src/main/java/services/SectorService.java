package services;

import db.ConnectionPool;
import db.IcesiDatabase;
import model.Measurement;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.ArrayList;

@Stateless
@Path("sector")
public class SectorService {

    @Path("insert/{id}")
    @POST
    public void registerSector(@PathParam("id")String id){
        IcesiDatabase icesiDataBase = ConnectionPool.getAvailableConnection();
        icesiDataBase.insertSector(id);
        icesiDataBase.setBusy(false);
    }

    @Path("assign/{id}/{idEng}")
    @POST
    public void assignSector(@PathParam("id")String id,@PathParam("idEng") String idEngAssigned){
        IcesiDatabase icesiDataBase = ConnectionPool.getAvailableConnection();
        icesiDataBase.insertSector(id,idEngAssigned);
        icesiDataBase.setBusy(false);
    }

    @PUT
    @Path("update/{id}/{idEng}")
    public void modifySector(@PathParam("id")String id,@PathParam("idEng") String idEngAssigned){
        IcesiDatabase icesiDataBase = ConnectionPool.getAvailableConnection();

        icesiDataBase.modifySector(id, idEngAssigned);
        icesiDataBase.setBusy(false);
    }

    @DELETE
    @Path("delete/{id}")
    public void deleteSectorByID(@PathParam("id")String id){
        IcesiDatabase icesiDataBase = ConnectionPool.getAvailableConnection();
        icesiDataBase.deleteSectorById(id);
        icesiDataBase.setBusy(false);
    }

    @GET
    @Path("list/{sectorID}")
    @Produces("application/json")
    public ArrayList<Measurement> getListMeasurements(@PathParam("sectorID") String sectorID){
        IcesiDatabase icesiDataBase = ConnectionPool.getAvailableConnection();
        ArrayList<Measurement> measurements = icesiDataBase.getListMeasurement(sectorID);
        icesiDataBase.setBusy(false);
        return measurements;
    }

    @GET
    @Path("getall")
    @Produces("application/json")
    public ArrayList<String> getAllSectores(){
        IcesiDatabase icesiDatabase = ConnectionPool.getAvailableConnection();
        ArrayList<String> sectores = icesiDatabase.getAllSectores();
        icesiDatabase.setBusy(false);
        return sectores;
    }

}
