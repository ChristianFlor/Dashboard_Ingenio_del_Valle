package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Engineer {

    private String name;
    private String lastname;
    private String id;
    private String username;
    private String password;
    private String email;
    private HashMap<String, ArrayList<Measurement>> measurements; // { idSector, medidasSector = {...} }

    public Engineer(String name, String lastname, String id, String username, String password, String email) {
        this.name = name;
        this.lastname = lastname;
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.measurements = new HashMap<>();
    }

    public Engineer(String id) {
        this.id = id;
    }
    public Engineer() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashMap<String, ArrayList<Measurement>> getMeasurements() {
        return measurements;
    }

    @Override
    public String toString() {
        return "Engineer{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public void addMeasurement(String idSector, Measurement measurement){
        if(measurements.containsKey(idSector)){
            measurements.get(idSector).add(measurement);
        }
    }



}
