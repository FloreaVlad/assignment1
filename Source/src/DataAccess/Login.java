/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

/**
 *
 * @author marinescugabriel
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login {
    private int idLogin;
    private String username;
    private String password;
    private int role;

    public int getIdLogin() {
        return idLogin;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }

    public void setIdLogin(int idLogin) {
        this.idLogin = idLogin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role) {
        this.role = role;
    }
    public static int searchID(String user) {
        try {
            Connection conn = DBConnect.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from login where username='"+user+"'");
            int id=0;
            
            while (rs.next()) {
                id=Integer.parseInt(rs.getString("idlogin"));
                
            }
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public static String searchPassword(String user) throws SQLException {
        Connection conn = DBConnect.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from login where username='"+user+"'");
        String pass="";
        
        while (rs.next()) {
            pass=rs.getString("password");
        
        }
        return pass;
    }
    public static int searchRole(String user) throws SQLException {
        Connection conn = DBConnect.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from login where username='"+user+"'");
        int role=1;
        
        while (rs.next()) {
            role=Integer.parseInt(rs.getString("role"));
        
        }
        return role;
    }
}
