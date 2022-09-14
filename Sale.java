package group09.SalesService.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import javax.persistence.*;

@Entity
@Table
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String productName;

    private int quantity;

    private String dateAndTime;

    public Sale() {
    }

    public Sale(String productName, int quantity, String dateAndTime){//, Long productId){ //Supplier supplier, Long productId) {
        this.productName = productName;
        this.quantity = quantity;
        this.dateAndTime = dateAndTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
            return "Sale{" +
                "productName=" + productName +
                ", quantity='" + quantity + '\'' +
                ", dateAndTime='" + dateAndTime + '\'' +
                '}';
    }
}