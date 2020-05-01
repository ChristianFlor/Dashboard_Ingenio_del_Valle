package model;

import java.util.ArrayList;

public class Dashboard {

    public static final String ADMIN_KEY = "admin;admin";
    public static final int NUMBER_OF_SECTORS = 10;
    private ArrayList<Engineer> engineers;
    private String key;

    public Dashboard() {
        this.key = ADMIN_KEY;
        this.engineers = new ArrayList<>();
    }

    public void registerEngineer(Engineer engineer){
        engineers.add(engineer);
    }

    public void assignEngineer(Engineer engineer, String idSector) throws Exception {
        boolean flag = true;
        for (Engineer eng : engineers) {
            if(eng.getMeasurements().get(idSector) != null) {
                flag = false;
                break;
            }
        }
        if(!flag) throw new Exception("Already assigned");
        for (int i = 0; i < engineers.size(); i++) {
            String currentID = engineers.get(i).getId();
            if(engineer.getId().equals(currentID)){
                ArrayList<Measurement> measurements = new ArrayList<>();
                engineers.get(i).getMeasurements().put(idSector, measurements);
                return;
            }
        }
    }

    public void addMeasurement(String idSector, Measurement measurement){
        for (int i = 0; i < engineers.size(); i++) {
            if(engineers.get(i).getMeasurements().containsKey(idSector)){
                engineers.get(i).addMeasurement(idSector,measurement);
            }
        }
    }

}
