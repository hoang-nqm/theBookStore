/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnqm.book;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Minh Hoang
 */
public class BookDTO implements Serializable {

    private String bookID, title, image, description, author, categoryID, status;
    private float price;
    private int quantity;
    private Date importDate;

    public BookDTO() {
    }

    public BookDTO(String bookID, String title, String image, String description, String author, String categoryID, String status, int quantity) {
        this.bookID = bookID;
        this.title = title;
        this.image = image;
        this.description = description;
        this.author = author;
        this.categoryID = categoryID;
        this.status = status;
        this.quantity = quantity;
    }

    public BookDTO(String bookID, String title, String description, String author, int quantity) {
        this.bookID = bookID;
        this.title = title;
        this.description = description;
        this.author = author;
        this.quantity = quantity;
    }

    public BookDTO(String bookID, String title, String image, String description, String author, String categoryID, String status, float price, int quantity, Date importDate) {
        this.bookID = bookID;
        this.title = title;
        this.image = image;
        this.description = description;
        this.author = author;
        this.categoryID = categoryID;
        this.status = status;
        this.price = price;
        this.quantity = quantity;
        this.importDate = importDate;
    }

    public BookDTO(String bookID, String title, String description, String author, float price, int quantity) {
        this.bookID = bookID;
        this.title = title;
        this.description = description;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
    }

    public BookDTO(String bookID, String title, String description, String author) {
        this.bookID = bookID;
        this.title = title;
        this.description = description;
        this.author = author;
    }

    public BookDTO(String bookID, String title, String description, String author, String status) {
        this.bookID = bookID;
        this.title = title;
        this.description = description;
        this.author = author;
        this.status = status;
 
    }

   

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }
   
    
}
