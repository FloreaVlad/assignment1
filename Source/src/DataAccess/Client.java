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

public class Client {

    private int idclients;
    private String name;
    private String email;
    private String phone;
    private String identity_card_number;
    private String personal_numeric_code;
    private String address;

    public Client() {
        this.idclients = 0;
        this.name = "";
        this.email = "";
        this.phone = "";
        this.identity_card_number = "";
        this.personal_numeric_code = "";
        this.address = "";
    }

    public Client(String name, String email, String phone, String identity_card_number, String personal_numeric_code, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.identity_card_number = identity_card_number;
        this.personal_numeric_code = personal_numeric_code;
        this.address = address;
    }

    public int getCid() {
        return idclients;
    }

    public void setCid(int idclients) {
        this.idclients = idclients;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getIdentity_card_number() {
        return identity_card_number;
    }

    public String getPersonal_numeric_code() {
        return personal_numeric_code;
    }

    public String getAddress() {
        return address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setIdentity_card_number(String identity_card_number) {
        this.identity_card_number = identity_card_number;
    }

    public void setPersonal_numeric_code(String personal_numeric_code) {
        this.personal_numeric_code = personal_numeric_code;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void deleteID(int ID){
        try{
        Connection conn = DBConnect.getConnection();
        Statement st = conn.createStatement();
      
        st.executeUpdate("DELETE FROM clients WHERE idclients="+ID);
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String getName(int ID){
        try{
        Connection conn = DBConnect.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select name from clients where idclients='"+ID+"'");
        String name="";
        while (rs.next()) {
            name=rs.getString("name");
        }
        return name;
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
        
    }
    
    public static String getIcn(int ID){
        try{
        Connection conn = DBConnect.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select icn from clients where idclients='"+ID+"'");
        String icn="";
        while (rs.next()) {
            icn=rs.getString("icn");
        }
        return icn;
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
        
    }
    
    public static String getPcn(int ID){
        try{
        Connection conn = DBConnect.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select pcn from clients where idclients='"+ID+"'");
        String pcn="";
        while (rs.next()) {
            pcn=rs.getString("pcn");
        }
        return pcn;
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
        
    }
    public static String getAddress(int ID){
        try{
        Connection conn = DBConnect.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select address from clients where idclients='"+ID+"'");
        String address="";
        while (rs.next()) {
            address=rs.getString("address");
        }
        return address;
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
        
    }
    
    public static String getEmail(int ID){
        try{
        Connection conn = DBConnect.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select email from clients where idclients='"+ID+"'");
        String email="";
        while (rs.next()) {
            email=rs.getString("email");
        }
        return email;
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
        
    }
    
    public static String getPhone(int ID){
        try{
        Connection conn = DBConnect.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select phone from clients where idclients='"+ID+"'");
        String phone="";
        while (rs.next()) {
            phone=rs.getString("phone");
        }
        return phone;
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
        
    }
    
    public static void insertClient(String name, String icn, String pcn, String address, String email, String phone){
        try{
        Connection conn = DBConnect.getConnection();
        Statement st = conn.createStatement();
      
        st.executeUpdate("Insert INTO clients (name,icn,pcn,address,email,phone) values ('"+name+"','"+icn+"','"+pcn+"','"+address+"','"+email+"','"+phone+"')");
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void updateClient(int ID,String name, String icn, String pcn, String address, String email, String phone){
        try{
        Connection conn = DBConnect.getConnection();
        Statement st = conn.createStatement();
      
        st.executeUpdate("UPDATE clients SET name='"+name+"', icn='"+icn+"', pcn='"+pcn+"', address='"+address+"', email='"+email+"', phone='"+phone+"' where idclients='"+ID+"';");
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
