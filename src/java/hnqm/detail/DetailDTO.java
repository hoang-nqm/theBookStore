/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnqm.detail;

import hnqm.book.BookDTO;
import java.io.Serializable;

/**
 *
 * @author Minh Hoang
 */
public class DetailDTO implements Serializable {

    private int detailID;
    private int orderID;
    private float price;
    private int quantity;
    private BookDTO book;

    public DetailDTO() {
    }

    public DetailDTO(int detailID, int orderID, float price, int quantity, BookDTO book) {
        this.detailID = detailID;
        this.orderID = orderID;
        this.price = price;
        this.quantity = quantity;
        this.book = book;
    }

    public int getDetailID() {
        return detailID;
    }

    public void setDetailID(int detailID) {
        this.detailID = detailID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

}
