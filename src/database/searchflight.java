/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author peace
 */

public class searchflight implements Db {

    @Override
    public Connection openConnection() {

        try {

            String username = "root";
            String password = "1234";
            String database = "yatraair";

            Connection conn;

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + database,
                    username,
                    password
            );

            if (conn == null) {

                System.out.println("Connection not successful");

            } else {

                System.out.println("Connection successful");

            }

            return conn;

        } catch (SQLException e) {

            System.out.println(e);

        }

        return null;
    }

    @Override
    public void closeConnection(Connection conn) {

        try {

            if (conn != null && !conn.isClosed()) {

                conn.close();

                System.out.println("Connection Closed");

            }

        } catch (SQLException e) {

            System.out.println(e);

        }

    }

    @Override
    public ResultSet runQuery(Connection conn, String query) {

        try {

            Statement stmt = conn.createStatement();

            return stmt.executeQuery(query);

        } catch (SQLException e) {

            System.out.println(e);

        }

        return null;
    }

    @Override
    public int executeUpdate(Connection conn, String query) {

        try {

            Statement stmt = conn.createStatement();

            return stmt.executeUpdate(query);

        } catch (SQLException e) {

            System.out.println(e);

        }

        return 0;
    }

}
