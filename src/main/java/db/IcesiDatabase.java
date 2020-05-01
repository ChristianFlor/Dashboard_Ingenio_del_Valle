package db;

import java.sql.*;
import java.util.ArrayList;

public class IcesiDatabase {
    private Connection connection;
    private Statement statement;

    public IcesiDatabase() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://200.3.193.22:3306/P09728_1_11", "P09728_1_11", "ZCSaQGZU");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
    public void closeConnection() throws SQLException { connection.close();}
    /*

    public void insertEstudiante(Estudiante estudiante) {
        String sql= "INSERT INTO estudiantes(id,nombre,codigo) VALUES ('%ID%','%NOMBRE%','%CODIGO%')";
        sql = sql.replace("%ID%", estudiante.getId());
        sql = sql.replace("%NOMBRE%", estudiante.getNombre());
        sql = sql.replace("%CODIGO%", estudiante.getCodigo());
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Estudiante> getAllEstudientes() {
        ArrayList<Estudiante> estudiantes = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM estudiantes");
            while (resultSet.next()){
                String id =resultSet.getString(1);
                String nombre =resultSet.getString(2);
                String codigo =resultSet.getString(3);
                Estudiante est= new Estudiante(id, nombre,codigo);
                estudiantes.add(est);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  estudiantes;
    }

    public Estudiante getEstudienteByID(String id) {
        String sql="SELECT * FROM estudiantes WHERE id='%ID%'";
        sql = sql.replace("%ID%",id);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String id2 =resultSet.getString(1);
                String nombre =resultSet.getString(2);
                String codigo =resultSet.getString(3);
                Estudiante est= new Estudiante(id, nombre,codigo);
                return est;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Estudiante estudiante= new Estudiante("NO ID", "NO NAME", "NO CODE");
        return estudiante;
    }

    public void insertMateria(Materia materia) {
        String sql= "INSERT INTO materias(id,nombre,NRC) VALUES ('%ID%','%NOMBRE%','%NRC%')";
        sql = sql.replace("%ID%", materia.getId());
        sql = sql.replace("%NOMBRE%", materia.getNombre());
        sql = sql.replace("%NRC%", materia.getNrc());
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Materia> getAllMaterias()  {
        ArrayList<Materia> materias = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM materias");
            while (resultSet.next()){
                String id =resultSet.getString(1);
                String nombre =resultSet.getString(2);
                String nrc =resultSet.getString(3);
                Materia mat= new Materia(id, nombre,nrc);
                materias.add(mat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  materias;
    }

    public Materia getMateriaByID(String id){
        String sql="SELECT * FROM materias WHERE id='%ID%'";
        sql = sql.replace("%ID%",id);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String id2 =resultSet.getString(1);
                String nombre =resultSet.getString(2);
                String nrc =resultSet.getString(3);
                Materia est= new Materia(id, nombre,nrc);
                return est;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Materia materia= new Materia("NO ID", "NO NAME", "NO NRC");
        return materia;
    }*/

}
