package group09.ProcurementService.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;
    private String supplierName;
    private String supplierBase;

    @OneToMany(targetEntity = Contact.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "sc_foreignKey", referencedColumnName = "supplierId")
    private List<Contact> contacts = new ArrayList<>();

    //for -> Look up Supplier by Part
//    @OneToMany(targetEntity = Part.class, cascade = CascadeType.ALL)
//    @JoinColumn(name = "sc_foreignKey", referencedColumnName = "supplierId")
//    private List<Part> parts = new ArrayList<>();

    public Supplier() {
    }

//    public Supplier(Long supplierId, String supplierName, String supplierBase, List<Contact> contacts, List<Part> parts) {
//        this.supplierId = supplierId;
//        this.supplierName = supplierName;
//        this.supplierBase = supplierBase;
//        this.contacts = contacts;
//        this.parts = parts;
//    }

    public Supplier(Long supplierId, String supplierName, String supplierBase, List<Contact> contacts) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierBase = supplierBase;
        this.contacts = contacts;
    }

    public Supplier(String supplierName, String supplierBase, List<Contact> contacts) {
        this.supplierName = supplierName;
        this.supplierBase = supplierBase;
        this.contacts = contacts;
    }

    public Supplier(Long supplierId, String supplierName, String supplierBase) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierBase = supplierBase;
    }

    public Supplier(String supplierName, String supplierBase) {
        this.supplierName = supplierName;
        this.supplierBase = supplierBase;
    }

//    public List<Part> getParts() {
//        return parts;
//    }
//
//    public void setParts(List<Part> parts) {
//        this.parts = parts;
//    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getSupplierBase() {
        return supplierBase;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setSupplierBase(String supplierBase) {
        this.supplierBase = supplierBase;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId=" + supplierId +
                ", supplierName='" + supplierName + '\'' +
                ", supplierBase='" + supplierBase + '\'' +
                '}';
    }
}

