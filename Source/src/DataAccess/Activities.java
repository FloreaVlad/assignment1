/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author marinescugabriel
 */
public class Activities {
    private int idActivities;
    private String description;
    private Date creationDate;
    private int idClients;
    private int idAccount;
    
    
    public Activities(){
        idActivities=0;
        description="";
        creationDate=null;
        idClients=0;
        idAccount=0;
    }
    
    public Activities(int idActivities,String description, Date creationDate, int idClients, int idAccount){
        this.idActivities=idActivities;
        this.description=description;
        this.creationDate=creationDate;
        this.idClients=idClients;
        this.idAccount=idAccount;
    }
    
    public int getIdActivities(){
        return idActivities;
    }
    
    public int getIdClients(){
        return idClients;
    }
    
    public int getIdAccount(){
        return idAccount;
    }
    
    public String getDescription(){
        return description;
    }
    
    public Date getCreationDate(){
        return creationDate;
    }
    
    public void setIdActivities(int idActivities){
        this.idActivities=idActivities;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setIdClients(int idClients) {
        this.idClients = idClients;
    }
    
    public static String getActivities(int ID,String start, String end){
        try{
        Connection conn = DBConnect.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from activities where CAST(creation_date as DATE)>='"+start+"' AND CAST(creation_date as DATE)<='"+end+"' AND idclients='"+ID+"'");
        String description="";
        String date="";
        String result="";
        while (rs.next()) {
            description=rs.getString("description");
            date=rs.getString("creation_date");
            result=result+"\n"+"Transaction type: "+description+"      Date: "+date;
        }
        return result;
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public static void insertActivity(String type, int ID, String date, String idAccount){
        try{
        Connection conn = DBConnect.getConnection();
        Statement st = conn.createStatement();
        String idd=Integer.toString(ID);
        st.executeUpdate("Insert INTO activities (description,creation_date,idclients,idaccounts) values ('"+type+"','"+date+"','"+idd+"','"+idAccount+"')");
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
