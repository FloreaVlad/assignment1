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
public class Bills {
    private int idBills;
    private String company;
    private float amount;
    private int idClients;

    public void setIdBills(int idBills) {
        this.idBills = idBills;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setIdClients(int idClients) {
        this.idClients = idClients;
    }

    public int getIdBills() {
        return idBills;
    }

    public String getCompany() {
        return company;
    }

    public float getAmount() {
        return amount;
    }

    public int getIdClients() {
        return idClients;
    }
    
    
}
