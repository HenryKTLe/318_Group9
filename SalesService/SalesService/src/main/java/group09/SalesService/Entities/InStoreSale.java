package group09.SalesService.Entities;

public class InStoreSale extends Sale {
    private int receiptNo;

    public void setReceiptNo(int receiptNo){
        this.receiptNo = receiptNo;
    }

    public int getReceiptNo(){ return receiptNo; }
}
