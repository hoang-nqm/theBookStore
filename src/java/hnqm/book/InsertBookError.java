/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnqm.book;

/**
 *
 * @author Minh Hoang
 */
public class InsertBookError {

    private String bookIDError, titleError, priceError, authorError, quantityError, statusError, descriptionError;

    public InsertBookError(String bookIDError, String titleError, String priceError, String authorError, String quantityError, String statusError, String descriptionError) {
        this.bookIDError = bookIDError;
        this.titleError = titleError;
        this.priceError = priceError;
        this.authorError = authorError;
        this.quantityError = quantityError;
        this.statusError = statusError;
        this.descriptionError = descriptionError;
    }

    public InsertBookError() {
    }

    public String getBookIDError() {
        return bookIDError;
    }

    public void setBookIDError(String bookIDError) {
        this.bookIDError = bookIDError;
    }

    public String getTitleError() {
        return titleError;
    }

    public void setTitleError(String titleError) {
        this.titleError = titleError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getAuthorError() {
        return authorError;
    }

    public void setAuthorError(String authorError) {
        this.authorError = authorError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    public String getStatusError() {
        return statusError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }

    public void setStatusError(String statusError) {
        this.statusError = statusError;
    }

}
