package db;

import model.Engineer;
import model.Measurement;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class IcesiDatabase {
    private Connection connection;
    private Statement statement;
    private boolean busy = false;

    public IcesiDatabase() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://200.3.193.22:3306/P09728_1_11", "P09728_1_11", "ZCSaQGZU");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
    public void closeConnection()  {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertEngineer(Engineer engineer){
        String sql= "INSERT INTO engineer(id,nameEngineer,lastname,email,username,pass) VALUES ('%ID%','%NAMEENGINEER%','%LASTNAME%','%EMAIL%','%USERNAME%','%PASS%')";
        sql = sql.replace("%ID%",engineer.getId());
        sql = sql.replace("%NAMEENGINEER%",engineer.getName());
        sql = sql.replace("%LASTNAME%",engineer.getLastname());
        sql = sql.replace("%EMAIL%",engineer.getEmail());
        sql = sql.replace("%USERNAME%",engineer.getUsername());
        sql = sql.replace("%PASS%",engineer.getPassword());
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertMeasurement(Measurement measurement,String idSector){
        String sql= "INSERT INTO measurement(id,ph,humidity,co2,temperature,measurementDate,idSector) VALUES " +
                "('%ID%','%PH%','%HUMIDITY%','%CO2%','%TEMPERATURE%','%MEASUREMENTDATE%','%IDSECTOR%')";
        sql = sql.replace("%ID%",measurement.getId());
        sql = sql.replace("%PH%",measurement.getpH()+"");
        sql = sql.replace("%HUMIDITY%",measurement.getHumidity()+"");
        sql = sql.replace("%CO2%",measurement.getCo2()+"");
        sql = sql.replace("%TEMPERATURE%",measurement.getTemperature()+"");
        sql = sql.replace("%MEASUREMENTDATE%",measurement.getDateTime()+"");
        sql = sql.replace("%IDSECTOR%",idSector);
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertSector(String id,String idEngAssigned){
        String sql = "INSERT INTO sector(id,idEngAssigned) VALUES ('%ID%','%IDENGASSIGNED%')";
        sql = sql.replace("%ID%",id);
        sql = sql.replace("%IDENGASSIGNED%",idEngAssigned);
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertSector(String id){
        String sql = "INSERT INTO sector(id) VALUES ('%ID%')";
        sql = sql.replace("%ID%",id);
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Engineer> getAllEngineers(){
        ArrayList<Engineer> engineers = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT *FROM engineer");

            while(resultSet.next()){
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String email = resultSet.getString(4);
                String username = resultSet.getString(5);
                String pass = resultSet.getString(6);
                Engineer engineer = new Engineer(name,lastName,id,username,pass,email);
                engineers.add(engineer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  engineers;
    }
    public Engineer validar(String username, String password){

        String sql = "SELECT * FROM engineer WHERE username = '%USERNAME%' AND pass = '%PASS%'";

        sql = sql.replace("%USERNAME%",username).replace("%PASS%",password);
        try {
            ResultSet resultSet =statement.executeQuery(sql);

            if(resultSet.next()){
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String email = resultSet.getString(4);
                String user = resultSet.getString(5);
                String pass = resultSet.getString(6);
                Engineer engineer = new Engineer(name,lastName,id,user,pass,email);
                return engineer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Engineer();
    }
    public Engineer getEngineerByID(String id){
        String sql = "SELECT * FROM engineer WHERE id = '%ID%'";
        sql = sql.replace("%ID%",id);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                String idE = resultSet.getString(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String email = resultSet.getString(4);
                String username = resultSet.getString(5);
                String pass = resultSet.getString(6);
                Engineer engineer = new Engineer(name,lastName,idE,username,pass,email);
                return engineer;
            }
            statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Engineer engineer = new Engineer();
        return engineer;
    }

    public ArrayList<Measurement> getAllMeasurements(){
        ArrayList<Measurement> measurements = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT *FROM measurement");

            while(resultSet.next()){
                String id = resultSet.getString(1);
                double ph = Double.parseDouble(resultSet.getString(2));
                double humidity = Double.parseDouble(resultSet.getString(3));
                double co2 = Double.parseDouble(resultSet.getString(4));
                double temperature = Double.parseDouble(resultSet.getString(5));
                long measurementDate = Long.parseLong(resultSet.getString(6));
                Measurement measurement = new Measurement(id,ph,humidity,co2,temperature,measurementDate);
                measurements.add(measurement);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return measurements;
    }

    public Measurement getMeasurementById(String id){
        String sql = "SELECT * FROM measurement WHERE id = '%ID%'";
        sql = sql.replace("%ID%",id);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                String idM = resultSet.getString(1);
                double ph = Double.parseDouble(resultSet.getString(2));
                double humidity = Double.parseDouble(resultSet.getString(3));
                double co2 = Double.parseDouble(resultSet.getString(4));
                double temperature = Double.parseDouble(resultSet.getString(5));
                long measurementDate = Long.parseLong(resultSet.getString(6));
                Measurement measurement = new Measurement(idM,ph,humidity,co2,temperature,measurementDate);
                return measurement;
            }
            statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Measurement measurement = new Measurement();
        return  measurement;
    }

    public void modifyEnginner(Engineer engineer){
        String sql = "UPDATE engineer SET nameEngineer ='%NAME%',lastname='%LASTNAME%',email = '%EMAIL%'," +
                "username = '%USERNAME%',pass = '%PASS%' WHERE id='%ID%'";
        sql = sql.replace("%ID%",engineer.getId());
        sql = sql.replace("%NAME%",engineer.getName());
        sql = sql.replace("%LASTNAME%",engineer.getLastname());
        sql = sql.replace("%EMAIL%",engineer.getEmail());
        sql = sql.replace("%USERNAME%",engineer.getUsername());
        sql = sql.replace("%PASS%",engineer.getPassword());
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifyMeasurement(Measurement measurement){
        String sql = "UPDATE measurement SET ph = '%PH%',humidity = '%HUMIDITY%', co2 = '%CO2%', " +
                "temperature = '%TEMPERATURE%', measurementDate = '%MEASUREMENTDATE%' WHERE id = '%ID%'";
        sql = sql.replace("%ID%",measurement.getId());
        sql = sql.replace("%PH%",measurement.getpH()+"");
        sql = sql.replace("%HUMIDITY%",measurement.getHumidity()+"");
        sql = sql.replace("%CO2%",measurement.getCo2()+"");
        sql = sql.replace("%TEMPERATURE%",measurement.getTemperature()+"");
        sql = sql.replace("%MEASUREMENTDATE%",measurement.getDateTime()+"");
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void modifySector(String id, String idEngAssigned){
        String sql = "UPDATE sector SET idEngAssigned = '%IDENGASSIGNED%' WHERE id = '%ID%'";
        sql = sql.replace("%IDENGASSIGNED%",idEngAssigned);
        sql = sql.replace("%ID%",id);
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEngineerById(String id){
        String sql = "DELETE FROM engineer WHERE id='%ID%'";
        sql = sql.replace("%ID%",id);
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMeasurementById(String id){
        String sql = "DELETE FROM measurement WHERE id='%ID%'";
        sql = sql.replace("%ID%",id);
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSectorById(String id){
        String sql = "DELETE FROM sector WHERE id='%ID%'";
        sql = sql.replace("%ID%",id);
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Lista de medidas de un sector
    public ArrayList<Measurement> getListMeasurement(String idSector){
        ArrayList<Measurement> measurements = new ArrayList<>();
        try {
            String sql = "SELECT * FROM measurement WHERE idSector = '%IDSECTOR%'";
            sql = sql.replace("%IDSECTOR%",idSector);
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                String id = resultSet.getString(1);
                double ph = Double.parseDouble(resultSet.getString(2));
                double humidity = Double.parseDouble(resultSet.getString(3));
                double co2 = Double.parseDouble(resultSet.getString(4));
                double temperature = Double.parseDouble(resultSet.getString(5));
                long measurementDate = Long.parseLong(resultSet.getString(6));
                Measurement measurement = new Measurement(id,ph,humidity,co2,temperature,measurementDate);
                measurements.add(measurement);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return measurements;
    }

    public ArrayList<String> getAllSectores(){
        ArrayList<String> sectores = new ArrayList<>();
        String sql = "SELECT*FROM sector";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                String id = resultSet.getString(1);
                sectores.add(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sectores;
    }

    //Lista de sectores de un ingeniero
    public ArrayList<String> getListSectores(String idEngAssigned){
        ArrayList<String> sectores = new ArrayList<>();
        try {
            String sql = "SELECT * FROM sector WHERE idEngAssigned = '%IDENGASSIGNED%'";
            sql = sql.replace("%IDENGASSIGNED%",idEngAssigned);
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                String id = resultSet.getString(1);
                sectores.add(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sectores;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }
}
