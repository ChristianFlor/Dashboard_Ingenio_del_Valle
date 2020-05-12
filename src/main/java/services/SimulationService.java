package services;

        import model.Simulation;

        import javax.ejb.Stateless;
        import javax.ws.rs.*;
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
}
