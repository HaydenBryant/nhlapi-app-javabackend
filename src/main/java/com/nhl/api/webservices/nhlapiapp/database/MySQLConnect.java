package com.nhl.api.webservices.nhlapiapp.database;

import com.nhl.api.webservices.nhlapiapp.model.User;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLConnect {

    public void saveUser(User createdUser) {
//
//        String url = "jdbc:mysql://localhost:3306/hockey_stats";
//        String user = "root";
//        String password = "Passwordmsql!";
//
////        String query = "SELECT VERSION()";
//
//        String sql = "INSERT INTO User(name) VALUES(?)";
//
//        try (Connection con = DriverManager.getConnection(url, user, password);
//
//             PreparedStatement pst = con.prepareStatement(sql)) {
//
//            pst.setString(1, createdUser.getName());
//            pst.executeUpdate();
//
//            System.out.println("A new user has been inserted");
//
//        } catch (SQLException ex) {
//
//            Logger lgr = Logger.getLogger(MySQLConnect.class.getName());
//            lgr.log(Level.SEVERE, ex.getMessage(), ex);
//        }

        try
        {
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/hockeystats?useSSL=false";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "Passwordmsql!");

            this.addUser(createdUser, conn);

//            Statement st = conn.createStatement();

//            st.executeUpdate("INSERT INTO users (name, username, email, password, favorite_team) "
//                    +"VALUES ('Fred', 'Flinstone', 'email@email.com', 'password', 'bruins')");

            conn.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    public void addUser(User user, Connection conn)
            throws SQLException
    {
        String query = "INSERT INTO users ("
                + " name,"
                + " username,"
                + " email,"
                + " password,"
                + " favorite_team ) VALUES ("
                + "?, ?, ?, ?, ?)";

        try {
            // set all the preparedstatement parameters
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, user.getName());
            st.setString(2, user.getUsername());
            st.setString(3, user.getEmail());
            st.setString(4, user.getPassword());
            st.setString(5, user.getFavoriteTeam());

            // execute the preparedstatement insert
            st.executeUpdate();
            st.close();
        }
        catch (SQLException se)
        {
            // log exception
            throw se;
        }
    }
}

