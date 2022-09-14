package group09.SalesService.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.util.ArrayList; // import the ArrayList class
import javax.persistence.*;

@Entity
@Table
public class Store {
    private String address;
    private String manager;
    private String name;

    ArrayList<InStoreSale> sales = new ArrayList<InStoreSale>();

    public Store(){} //copy constructor

    public Store (String address, String manager, String name) {
        this.address = address;
        this.manager = manager;
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) { this.address = address;}

    public String getManager() { return manager; }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<InStoreSale> getSales() { return sales; }

    public void setSales(ArrayList<InStoreSale> sales) { this.sales = sales; }

    public void addSale(InStoreSale iss) { sales.add(iss); }

    public String printSales() {
        String salesRecord = "SALES RECORD: " + name + '\'';

        for (int i = 0; i < sales.size(); i++) {
            salesRecord += "Sale " + i + ": " + '\'';
        }

        return salesRecord;
    }

}
