package group09.SalesService.Entities;

public class OnlineSale extends Sale {

    private String address;

    private String customerName;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) { this.address = address; }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) { this.customerName = customerName; }
}
