/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnqm.detail;

import hnmq.utils.DBHelper;
import hnqm.book.BookDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Minh Hoang
 */
public class DetailDAO {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public int insertDetail(DetailDTO dto) throws SQLException, Exception {
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "insert into Details(price,quantity,OrderID,bookID) values(?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setFloat(1, dto.getPrice());
                ps.setInt(2, dto.getQuantity());
                ps.setInt(3, dto.getOrderID());
                ps.setString(4, dto.getBook().getBookID());
                return ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }

        return -1;
    }

    public List<DetailDTO> getOrderbyOrderID(String orderID) throws Exception {

        List<DetailDTO> result = null;
        BookDAO bookDAO = new BookDAO();
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "SELECT detailID,price,quantity,bookID FROM Details WHERE OrderID=?";

                ps = con.prepareStatement(sql);
                ps.setString(1, orderID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int detailID = rs.getInt("detailID");

                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    String bookID = rs.getString("bookID");

                    DetailDTO detail = new DetailDTO(detailID, Integer.parseInt(orderID), price, quantity, bookDAO.getBookByID(bookID));

                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(detail);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return result;
    }
}
