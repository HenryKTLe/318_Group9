package group09.InventoryService.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private Double price;
    private String comment;

    //@OneToMany(targetEntity = Part.class, cascade = CascadeType.ALL)
    //@JoinColumn(name = "sc_foreignKey", referencedColumnName = "productId")
    @OneToMany(mappedBy = "product")
    @JsonIgnoreProperties(value = {"product"})
    private List<Part> parts = new ArrayList<>();

    public Product() {
    }

    public Product(String productName, Double price, String comment, List<Part> parts) {
        this.productName = productName;
        this.price = price;
        this.comment = comment;
        this.parts = parts;
    }

    public Product(Long productId, String productName, Double price, String comment, List<Part> parts) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.comment = comment;
        this.parts = parts;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", comment='" + comment + '\'' +
                '}';
    }
}