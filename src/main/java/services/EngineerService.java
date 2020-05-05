package services;

import db.IcesiDatabase;
import model.Engineer;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.ArrayList;

@Stateless
@Path("engineer")
public class EngineerService {

    //CRUD: Create,Read,Uptade,Delate

    @Path("register")
    @POST
    @Consumes("application/json")//recibo
    @Produces("application/json")//enviar
    public Engineer registerEngineer(Engineer engineer){
        IcesiDatabase icesiDataBase = new IcesiDatabase();
        icesiDataBase.insertEngineer(engineer);
        icesiDataBase.closeConnection();
        return engineer;
    }

    //Obtener lista de ingenieros
    @GET
    @Path("getall")
    @Produces("application/json")
    public ArrayList<Engineer> getAllEngineers(){
        IcesiDatabase icesiDataBase = new IcesiDatabase();
        ArrayList<Engineer> engineers = icesiDataBase.getAllEngineers();
        icesiDataBase.closeConnection();
        return engineers;
    }

    //Obtener un elemento por id
    @GET
    @Path("byid")
    @Produces("application/json")
    public Engineer getEngineerByID(@QueryParam("id") String id){
        IcesiDatabase icesiDataBase = new IcesiDatabase();
        Engineer engineer = icesiDataBase.getEngineerByID(id);
        icesiDataBase.closeConnection();
        return engineer;
    }

    //CRUD: Create,Read,Uptade,Delate

    @PUT
    @Path("update")
    @Consumes("application/json")
    @Produces("application/json")
    public Engineer modifyEngineer(Engineer engineer){
        IcesiDatabase icesiDataBase = new IcesiDatabase();
        icesiDataBase.modifyEnginner(engineer);
        icesiDataBase.closeConnection();
        return engineer;
    }

    @DELETE
    @Path("delete/{id}")
    public void deleteEngineerByID(@PathParam("id")String id){
        IcesiDatabase icesiDataBase = new IcesiDatabase();
        icesiDataBase.deleteEngineerById(id);
        icesiDataBase.closeConnection();
    }
    @GET
    @Path("list/{engineerID}")
    @Produces("application/json")
    public ArrayList<String> getListSectors(@PathParam("engineerID") String engineerID){
        IcesiDatabase icesiDataBase = new IcesiDatabase();
        ArrayList<String> sectors = icesiDataBase.getListSectores(engineerID);
        icesiDataBase.closeConnection();
        return sectors;
    }

}
