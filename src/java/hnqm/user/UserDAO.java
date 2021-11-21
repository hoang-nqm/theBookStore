/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnqm.user;

import hnmq.utils.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Minh Hoang
 */
public class UserDAO {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public UserDTO checkLogin(String email, String password) throws SQLException, Exception {
        UserDTO result = null;

        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "SELECT userID,fullName,password, roleID,phone,status "
                        + "FROM Users "
                        + "WHERE userID = ? and password = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {

                    String name = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String phone = rs.getString("phone");
                    String status = rs.getString("status");
                    result = new UserDTO(email, name, password, roleID, status, phone);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return result;
    }

    public boolean checkEmail(String email) throws Exception {
        boolean check = false;
        String sql = "select fullName from Users where userID=?";
        try {
            con = DBHelper.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                check = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return check;
    }
      public String getStatus(String userID) throws Exception {
        String check = "";
        String sql = "select status from Users where userID=?";
        try {
            con = DBHelper.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, userID);
            rs = ps.executeQuery();
            if (rs.next()) {
                check = rs.getString("status");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return check;
    }
}
