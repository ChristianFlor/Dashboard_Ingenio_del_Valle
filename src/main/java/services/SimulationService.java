package services;

import model.Simulation;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;

@Stateless
@Path("simulation")
public class SimulationService {

    @GET
    @Path("simulate")
    @Produces("application/json")
    public ArrayList<Double> simulate(@QueryParam("mode") String mode, @QueryParam("periodnum") String periodNum) {
        Simulation simulation = new Simulation();
        return simulation.simulate(mode.charAt(0), Integer.parseInt(periodNum));
    }
}
