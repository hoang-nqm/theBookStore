/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnmq.order;

import java.util.Date;

/**
 *
 * @author Minh Hoang
 */
public class OrderDTO {
    private int orderID;
    private String userID;
    private Date dateOrder;
    private float total;

    public OrderDTO() {
    }

    public OrderDTO(int orderID, Date dateOrder, float total) {
        this.orderID = orderID;
        this.dateOrder = dateOrder;
        this.total = total;
    }

    public OrderDTO(int orderID, String userID, Date dateOrder, float total) {
        this.orderID = orderID;
        this.userID = userID;
        this.dateOrder = dateOrder;
        this.total = total;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
}
