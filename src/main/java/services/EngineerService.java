package services;

import model.Engineer;

import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Stateless
@Path("engineer")
public class EngineerService {

    //CRUD: Create,Read,Uptade,Delate

    @POST
    @Path("register")
    public void registerEnginerr(Engineer engineer){

    }


}
