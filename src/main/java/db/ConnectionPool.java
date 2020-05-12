package db;

import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionPool {

    public  static ArrayList<IcesiDatabase> connections = new ArrayList<>();

    public  static IcesiDatabase getAvailableConnection(){

        for(int i = 0; i<connections.size(); i++){

            if(connections.get(i).isBusy()){
                connections.get(i).setBusy(true);
                return connections.get(i);
            }
        }
        try{
            IcesiDatabase instance = new IcesiDatabase();
            connections.add(instance);
            instance.setBusy(true);
            return instance;
        }catch (SQLException e){
            e.printStackTrace();
            //Algoritmo de reintento
            return null;
        }
    }
}
