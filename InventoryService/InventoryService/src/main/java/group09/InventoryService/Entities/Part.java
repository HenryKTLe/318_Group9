package group09.InventoryService.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partId;

    private String partName;

    private String description;

    private Long supplierId;

    //private Long productId;

    @ManyToOne
    @JoinColumn(name = "product_product_id")
    private Product product;

    public Part() {
    }

    public Part(Long partId, String partName, String description, Long supplierId, Product product){//, Long productId){ //Supplier supplier, Long productId) {
        this.partId = partId;
        this.partName = partName;
        this.description = description;
        this.supplierId = supplierId;
        this.product = product;
        //this.productId = productId;
    }

    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

//    public Long getProductId() {
//        return productId;
//    }
//
//    public void setProductId(Long productId) {
//        this.productId = productId;
//    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Part{" +
                "partId=" + partId +
                ", partName='" + partName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}


//    @JsonBackReference
//    @ManyToOne
//    @JoinColumn(name = "supplierId")
//    @JsonIgnore
//    //@JoinColumn(name="mapping_type_id")
//    private Supplier supplier;

//    public Supplier getSupplier() {
//        return supplier;
//    }
//
//    public void setSupplier(Supplier supplier) {
//        this.supplier = supplier;
//    }