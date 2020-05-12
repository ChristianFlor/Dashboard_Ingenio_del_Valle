package services;


import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Stateless
@Path("echo")
public class EchoService {

    @GET
    @Path("echoing")
    public String index(){
        return "echo";
    }
}