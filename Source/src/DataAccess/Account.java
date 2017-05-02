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
import View.ClientFrame;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Account {

    private int idaccounts;
    private String type;
    private float amount;
    private Date created_at;
    private int idClient;

    public Account() {
        this.amount = 0;
        this.created_at = null;
        this.idClient = 0;
    }

    public Account(String type, float amount, Date created_at, int idClient) {
        this.type = type;
        this.amount = amount;
        this.created_at = created_at;
        this.idClient = idClient;
    }

    public String getType() {
        return type;
    }

    public float getAmount() {
        return amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getAid() {
        return idaccounts;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public int getClient() {
        return idClient;
    }

    public void setAid(int idaccounts) {
        this.idaccounts = idaccounts;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setClient(int idClient) {
        this.idClient = idClient;
    }
    
    public static int hasMoney(int acc, int amount){
        try{
        Connection conn = DBConnect.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select amount from accounts where idaccounts='"+acc+"'");
        int amountFound=0;
        while (rs.next()) {
            amountFound=Integer.parseInt(rs.getString("amount"));
        }
        if (amountFound>amount){
            return 1;
        }else{
            return 0;
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
                
    }
    public static void insertAccount(String ID, String date, String type, String money){
        try{
        Connection conn = DBConnect.getConnection();
        Statement st = conn.createStatement();
      
        st.executeUpdate("Insert INTO accounts (idclients,amount,creation_date,type) values ('"+ID+"','"+money+"','"+date+"','"+type+"')");
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void updateAccount(String accID, String ID, String date, String type, String money){
        try{
        Connection conn = DBConnect.getConnection();
        Statement st = conn.createStatement();
      
        st.executeUpdate("UPDATE accounts SET idclients='"+ID+"', amount='"+money+"', creation_date='"+date+"', type='"+type+"' where idaccounts='"+accID+"';");
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static int determineAmount(int amount,int acc){
        try{
        Connection conn = DBConnect.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select amount from accounts where idaccounts='"+acc+"'");
        int amountFound=0;
        while (rs.next()) {
            amountFound=Integer.parseInt(rs.getString("amount"));
        }
        amountFound=amountFound-amount;
        return amountFound;
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public static void withdrawMoney(int amount,int acc){
        try{
        Connection conn = DBConnect.getConnection();
        Statement st = conn.createStatement();
        int amountFound=determineAmount(amount,acc);
        st.execute("UPDATE accounts SET amount="+amountFound+" where idaccounts="+acc);
        
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void depositMoney(int amount,int acc){
        try{
        Connection conn = DBConnect.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select amount from accounts where idaccounts='"+acc+"'");
        int amountFound=0;
        while (rs.next()) {
            amountFound=Integer.parseInt(rs.getString("amount"));
        }
        amountFound=amountFound+amount;
        Statement stt = conn.createStatement();
        stt.executeUpdate("UPDATE accounts SET amount="+amountFound+" where idaccounts="+acc+"");
        
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
    public static void deleteID(int ID){
        try{
        Connection conn = DBConnect.getConnection();
        Statement st = conn.createStatement();
      
        st.executeUpdate("DELETE FROM accounts WHERE idaccounts="+ID);
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void payUtility(int company){
        try{
        Connection conn = DBConnect.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select company from bills where idbills='"+company+"'");
        String sCompany="";
        while (rs.next()) {
            sCompany=rs.getString("company");
        }
        Statement stt = conn.createStatement();
        stt.executeUpdate("DELETE FROM bills WHERE idbills="+company);
        ClientFrame.displayError(sCompany+" bill was paid! Congratulations!");
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static int getAmount(int company){
         try{
        Connection conn = DBConnect.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select amount from bills where idbills='"+company+"'");
        int amount=0;
        while (rs.next()) {
            amount=Integer.parseInt(rs.getString("amount"));
        }
        return amount;
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
         return 0;
    }
    public static DefaultTableModel displayAccounts(String username) {
        try {
            Connection conn = DBConnect.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from accounts where idclients='"+Login.searchID(username)+"'");
            ResultSetMetaData rsmetadata=rs.getMetaData();
            int colums=rsmetadata.getColumnCount();
            DefaultTableModel dtm= new DefaultTableModel(){
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            Vector colums_name=new Vector();
            Vector data_rows=new Vector();
            
            colums_name.addElement("IBAN");
            colums_name.addElement("ClientID");
            colums_name.addElement("Available Amount");
            colums_name.addElement("Created At");
            colums_name.addElement("Type");
            
            
            dtm.setColumnIdentifiers(colums_name);
            
            while(rs.next()){
                data_rows=new Vector();
                for (int j=1;j<=colums;j++){
                    data_rows.addElement(rs.getString(j));
                }
                dtm.addRow(data_rows);
            }
            return dtm;
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static String getAmountAvailable(int ID){
        try{
        Connection conn = DBConnect.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select amount from accounts where idaccounts='"+ID+"'");
        String address="";
        while (rs.next()) {
            address=rs.getString("amount");
        }
        return address;
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
        
    }
    
    public static String getCreationDate(int ID){
        try{
        Connection conn = DBConnect.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select creation_date from accounts where idaccounts='"+ID+"'");
        String address="";
        while (rs.next()) {
            address=rs.getString("creation_date");
        }
        return address;
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
        
    }
    
    public static String getClientID(int ID){
        try{
        Connection conn = DBConnect.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select idclients from accounts where idaccounts='"+ID+"'");
        String address="";
        while (rs.next()) {
            address=rs.getString("idclients");
        }
        return address;
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
        
    }
    
    public static String getType(int ID){
        try{
        Connection conn = DBConnect.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select type from accounts where idaccounts='"+ID+"'");
        String address="";
        while (rs.next()) {
            address=rs.getString("type");
        }
        return address;
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
        
    }
    
    
}

