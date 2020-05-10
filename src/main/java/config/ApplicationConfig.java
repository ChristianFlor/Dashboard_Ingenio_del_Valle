package config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class ApplicationConfig extends Application {


    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> res = new HashSet<>();
        res.add(services.EchoService.class);
        res.add(services.EngineerService.class);
        res.add(services.MeasurementService.class);
        res.add(services.SectorService.class);
        res.add(services.SimulationService.class);
        return res;
    }
}
