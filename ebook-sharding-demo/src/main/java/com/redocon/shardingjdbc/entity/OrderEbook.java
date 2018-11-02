package com.redocon.shardingjdbc.entity;

public class OrderEbook {

    private Integer id;
    private Integer ebookId;
    private Integer customerId;
    private Integer orderId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEbookId() {
        return ebookId;
    }

    public void setEbookId(Integer ebookId) {
        this.ebookId = ebookId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
