/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 * Data Access Object class for executing flight-related database queries.
 */
import model.SearchFlight;
import java.sql.*;
import java.util.ArrayList;

public class FlightDAO {

    DBConnection db=new DBConnection();

    public ArrayList<SearchFlight> getFlights(){

        ArrayList<SearchFlight> list=
                new ArrayList<>();

        try{

            Connection con=
                    db.getConnection();

            String sql=
                    "select * from flights";

            PreparedStatement ps=
                    con.prepareStatement(sql);

            ResultSet rs=
                    ps.executeQuery();

            while(rs.next()){

                SearchFlight sf=
                        new SearchFlight(

                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)

                        );

                list.add(sf);

            }

        }
        catch(Exception e){

            System.out.println(e);

        }

        return list;

    }

    private static class DBConnection {

        public DBConnection() {
        }

        private Connection getConnection() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }

}