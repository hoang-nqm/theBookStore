/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnqm.category;

import hnmq.utils.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Minh Hoang
 */
public class CategoryDAO {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public List<CategoryDTO> getAllCategory() throws Exception {

        List<CategoryDTO> result = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "SELECT categoryID,categoryName FROM Category ";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String categoryID = rs.getString("categoryID");
                    String categoryName = rs.getString("categoryName");
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new CategoryDTO(categoryID, categoryName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return result;
    }

    public String getNameByID(String categoryID) throws Exception {
        String cateName = "";
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "SELECT categoryName FROM Category where categoryID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, categoryID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    cateName = rs.getString("categoryName");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return cateName;
    }
}
