/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnqm.user;

/**
 *
 * @author Minh Hoang
 */
public class UserDTO {
    private String userID,fullName,password,roleID,status,phone;

    public UserDTO() {
    }

    public UserDTO(String userID, String fullName, String password, String roleID, String status, String phone) {
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
        this.roleID = roleID;
        this.status = status;
        this.phone = phone;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}
