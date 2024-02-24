package org.example;

import org.postgresql.ds.PGSimpleDataSource;

import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class database {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";

    private static final Logger logger = Logger.getLogger(database.class.getName());

    private database() {
    }

    public static Connection getConnection(){


        //Connection con = null ;

        try {
            DriverManager.registerDriver(new org.postgresql.Driver());

            String connInfo="jdbc:postgresql://localhost:5432/postgres";
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(connInfo,"postgres","123456");
            //  con.setAutoCommit(false);
            return con;
        } catch (ClassNotFoundException | SQLException e) {

            //  System.out.println(e);
            JOptionPane.showConfirmDialog(null, e.toString());
            return null;
        }



    }


    public static ResultSet runQuery(String query) {
        ResultSet resultSet = null;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            resultSet = stmt.executeQuery();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database connection error: ", e);
        }
        return resultSet;
    }

    public static void insertIntoDatabase(String sql) {
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                logger.info("Insert successful, " + affectedRows + " rows affected.");
            } else {
                logger.info("Insert failed, no rows affected.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database connection error: ", e);
        }
    }
}
