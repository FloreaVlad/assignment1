/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;
import View.*;
import DataAccess.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author marinescugabriel
 */
public class Assignment1 {
    private static String username;
    private static String password;
    private static String content;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new LoginFrame().show();
    }
    
    public static void setFields(){
        username=LoginFrame.getUsername();
        password=LoginFrame.getPassword();
        System.out.println(username+password);
        try {
            openFrame();
        } catch (SQLException ex) {
            Logger.getLogger(Assignment1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void transferMoney(String to, String from, String amount){
        int ito=Integer.parseInt(to);
        int ifrom=Integer.parseInt(from);
        int iamount=Integer.parseInt(amount);
        if (DataAccess.Account.hasMoney(ifrom,iamount)==1){
            DataAccess.Account.withdrawMoney(iamount,ifrom);
            DataAccess.Account.depositMoney(iamount,ito);
        }else{
            ClientFrame.displayError("The selected account does not have enough money");
        }
        
    }
    
    public static void deleteClient(int ID){
        Client.deleteID(ID);
        AdminFrame.displayError("The client was deleted");
    }
    public static void deleteAccount(int ID){
        Account.deleteID(ID);
        AdminFrame.displayError("The account was deleted");
    }
    
    public static void payBill(String company,String account){
        int iaccount=Integer.parseInt(account);
        int icompany=Integer.parseInt(company);
        int amount=DataAccess.Account.getAmount(icompany);
        if (DataAccess.Account.hasMoney(iaccount, amount)==1){
            DataAccess.Account.withdrawMoney(amount, iaccount);
            DataAccess.Account.payUtility(icompany);
        }
    }
    public static void insertAccount(String ID, String date, String type, String money){
        Account.insertAccount(ID,date,type,money);
        AccountModification.displayError("Account added");
    }
    public static void insertClient(String name,String icn, String pcn, String address, String email, String phone){
        Client.insertClient(name,icn,pcn,address,email,phone);
        ClientModification.displayError("Client inserted!");
    }
    public static int checkBox(String a,String b, String c, String d, String e, String f){
        if (a.equals("")||b.equals("")||c.equals("")||d.equals("")||e.equals("")||f.equals("")){
            ClientModification.displayError("Fill all the text boxes!!!");
            return 0;
        }else{
            return 1;
        }
    }
    public static void openUpdateAccount(int ID){
        new View.AccountModification(ID).show();
        AccountModification.setAccountNumber(Integer.toString(ID));
        AccountModification.setMoneyAmount(Account.getAmountAvailable(ID));
        AccountModification.setCreationDate(Account.getCreationDate(ID));
        AccountModification.setClientID(Account.getClientID(ID));
        AccountModification.setClientName(Client.getName(Integer.parseInt(Account.getClientID(ID))));
        AccountModification.accountNumber.setEditable(false);
        AccountModification.clientName.setEditable(false);
    }
    public static void openUpdate(int ID){
       new View.ClientModification(ID).show();
       
       ClientModification.setAddress(Client.getAddress(ID));
       ClientModification.setEmail(Client.getEmail(ID));
       ClientModification.setNameText(Client.getName(ID));
       ClientModification.setIcn(Client.getIcn(ID));
       ClientModification.setPcn(Client.getPcn(ID));
       ClientModification.setPhone(Client.getPhone(ID));
    }
            
        
    public static void doReport(String content) {
		// TODO Auto-generated method stub
		try {
			 
			File file = new File("//Users//report.txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
 
			System.out.println("Report Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    public static void generateReport(String ID, String start, String end){
        content=Activities.getActivities(Integer.parseInt(ID),start,end);
        Assignment1.doReport(content);
    }
    public static void generateReport(int ID, String start, String end){
        content=Activities.getActivities(ID,start,end);
        Assignment1.doReport(content);
    }
    public static void insertActivity(String type, int ID, String date, String idAccount){
        Activities.insertActivity(type, ID, date, idAccount);
    }
    public static void openReadAccount(int ID){
       new View.AccountModification(ID).show();
       
       AccountModification.setAccountNumber(Integer.toString(ID));
       AccountModification.setClientID(Account.getClientID(ID));
       AccountModification.setMoneyAmount(Account.getAmountAvailable(ID));
       AccountModification.setClientName(Client.getName(Integer.parseInt(Account.getClientID(ID))));
       AccountModification.setCreationDate(Account.getCreationDate(ID));
       AccountModification.setType(Account.getType(ID));
       System.out.println(Account.getType(ID));
       AccountModification.clientID.setEditable(false);
       AccountModification.clientName.setEditable(false);
       AccountModification.accountNumber.setEditable(false);
       AccountModification.creationDate.setEditable(false);
       AccountModification.type.setEditable(false);
       AccountModification.moneyAmount.setEditable(false);
       
    }        
            
    public static void openRead(int ID){
       new View.ClientModification(ID).show();
       
       ClientModification.setAddress(Client.getAddress(ID));
       ClientModification.setEmail(Client.getEmail(ID));
       ClientModification.setNameText(Client.getName(ID));
       ClientModification.setIcn(Client.getIcn(ID));
       ClientModification.setPcn(Client.getPcn(ID));
       ClientModification.setPhone(Client.getPhone(ID));
       ClientModification.name.setEditable(false);
       ClientModification.email.setEditable(false);
       ClientModification.phone.setEditable(false);
       ClientModification.icn.setEditable(false);
       ClientModification.pcn.setEditable(false);
       ClientModification.address.setEditable(false);
       
    }
   public static void updateAccount(String accID,String ID, String date, String type, String money){
       Account.updateAccount(accID,ID,date,type,money);
   }
   public static void updateClient(int ID,String name, String icn, String pcn, String address, String email, String phone){
   Client.updateClient(ID, name, icn, pcn, address, email, phone);
   }
    public static void openNew(){
        new View.ClientModification().show();
    }
    public static void openNewAccount(){
        new View.AccountModification().show();
        AccountModification.clientName.setEditable(false);
        AccountModification.accountNumber.setEditable(false);
        
    }
    public static void openFrame() throws SQLException{
        String recievedPassword;
        int role;
        recievedPassword = Login.searchPassword(username);
        role = Login.searchRole(username);
        
        
        System.out.println(recievedPassword+role);
        if ((recievedPassword.equals(password))&&(role==0)){
            new AdminFrame(username,password).show();
        }else{
            if((recievedPassword.equals(password))&&(role==1)){
                new ClientFrame(username,password).show();
            }else{
                LoginFrame.displayError();
            }
        }
    }
    
}
