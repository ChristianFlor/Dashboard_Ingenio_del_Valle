package services;

import db.ConnectionPool;
import db.IcesiDatabase;
import model.Email;
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
        IcesiDatabase icesiDataBase = ConnectionPool.getAvailableConnection();
        icesiDataBase.insertEngineer(engineer);
        icesiDataBase.setBusy(false);
        return engineer;
    }
    @Path("login/{username}/{password}")
    @GET
    @Produces("application/json")//enviar
    public Engineer validationEngineer(@PathParam("username") String username, @PathParam("password") String password){
        IcesiDatabase icesiDataBase = ConnectionPool.getAvailableConnection();
        Engineer valid= icesiDataBase.validar(username,password);
        icesiDataBase.setBusy(false);
        System.out.println(valid.getUsername()+"\t"+valid.getPassword());
        return valid;
    }
    //Obtener lista de ingenieros
    @GET
    @Path("getall")
    @Produces("application/json")
    public ArrayList<Engineer> getAllEngineers(){
        IcesiDatabase icesiDataBase =  ConnectionPool.getAvailableConnection();
        ArrayList<Engineer> engineers = icesiDataBase.getAllEngineers();
        icesiDataBase.setBusy(false);
        return engineers;
    }

    //Obtener un elemento por id
    @GET
    @Path("byid")
    @Produces("application/json")
    public Engineer getEngineerByID(@QueryParam("id") String id){
        IcesiDatabase icesiDataBase = ConnectionPool.getAvailableConnection();
        Engineer engineer = icesiDataBase.getEngineerByID(id);
        icesiDataBase.setBusy(false);
        return engineer;
    }

    @GET
    @Path("byEmail")
    @Produces("application/json")
    public Engineer getEngineerByEmail(@QueryParam("email") String email){
        IcesiDatabase icesiDataBase = ConnectionPool.getAvailableConnection();
        Engineer engineer = icesiDataBase.getEngineerByEmail(email);
        icesiDataBase.setBusy(false);
        return engineer;
    }

    //CRUD: Create,Read,Uptade,Delate

    @PUT
    @Path("update")
    @Consumes("application/json")
    @Produces("application/json")
    public Engineer modifyEngineer(Engineer engineer){
        IcesiDatabase icesiDataBase = ConnectionPool.getAvailableConnection();
        icesiDataBase.modifyEnginner(engineer);
        icesiDataBase.setBusy(false);
        return engineer;
    }

    @DELETE
    @Path("delete/{id}")
    public void deleteEngineerByID(@PathParam("id")String id){
        IcesiDatabase icesiDataBase = ConnectionPool.getAvailableConnection();
        icesiDataBase.deleteEngineerById(id);
        icesiDataBase.setBusy(false);
    }
    @GET
    @Path("list/{engineerID}")
    @Produces("application/json")
    public ArrayList<String> getListSectors(@PathParam("engineerID") String engineerID){
        IcesiDatabase icesiDataBase = ConnectionPool.getAvailableConnection();
        ArrayList<String> sectors = icesiDataBase.getListSectores(engineerID);
        icesiDataBase.setBusy(false);
        return sectors;
    }

    @HEAD
    @Path("enviaremail/{email}")
    public void enviarEmail(@PathParam("email") String email){
        IcesiDatabase icesiDatabase =   ConnectionPool.getAvailableConnection();
        Engineer engineer = icesiDatabase.getEngineerByEmail(email);
        String pass = engineer.getPassword();
        Email email1 = new Email();
        email1.enviarEmail(email,pass);
    }

}
