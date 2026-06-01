/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.MySqlConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

    MySqlConnection mysql =
            new MySqlConnection();

    Connection conn =
            mysql.openConnection();

    public void getFlights(){

        try{

            String query=
                    "select * from flights";

            ResultSet rs;
            rs = mysql.runQuery(conn, query);

            while(rs.next()){

                System.out.println(
                        rs.getString("flight_name")
                );

            }

        }
        catch(SQLException e){

            System.out.println(e);

        }

    }

}
