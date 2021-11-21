/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnqm.book;

import hnmq.utils.DBHelper;
import hnqm.category.CategoryDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Minh Hoang
 */
public class BookDAO {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public List<BookDTO> getAllBook() throws Exception {

        List<BookDTO> result = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "SELECT bookID,title,image, description,author,categoryID,status,price,quantity,importDate FROM Books";

                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String bookID = rs.getString("bookID");
                    String title = rs.getString("title");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    String author = rs.getString("author");
                    String categoryID = rs.getString("categoryID");
                    String status = rs.getString("status");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    Date importDate = rs.getDate("importDate");
                    CategoryDAO categoryDAO = new CategoryDAO();
                    BookDTO book = new BookDTO(bookID, title, image, description, author, categoryDAO.getNameByID(categoryID), status, price, quantity, importDate);
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(book);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return result;
    }

    public List<BookDTO> getBookByCateID(String cateID) throws Exception {

        List<BookDTO> result = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "SELECT bookID,title,image, description,author,categoryID,status,price,quantity,importDate FROM Books WHERE categoryID=?";

                ps = con.prepareStatement(sql);
                ps.setString(1, cateID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String bookID = rs.getString("bookID");
                    String title = rs.getString("title");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    String author = rs.getString("author");
                    String categoryID = rs.getString("categoryID");
                    String status = rs.getString("status");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    Date importDate = rs.getDate("importDate");
                    CategoryDAO categoryDAO = new CategoryDAO();
                    BookDTO book = new BookDTO(bookID, title, image, description, author, categoryDAO.getNameByID(categoryID), status, price, quantity, importDate);
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(book);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return result;
    }

    public List<BookDTO> searchBook(String name, float from, float to) throws Exception {

        List<BookDTO> result = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "SELECT bookID,title,image, description,author,categoryID,status,price,quantity,importDate FROM Books  where [title] like ? or price between ? and ?";

                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + name + "%");
                ps.setFloat(2, from);
                ps.setFloat(3, to);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String bookID = rs.getString("bookID");
                    String title = rs.getString("title");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    String author = rs.getString("author");
                    String categoryID = rs.getString("categoryID");
                    String status = rs.getString("status");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    Date importDate = rs.getDate("importDate");
                    CategoryDAO categoryDAO = new CategoryDAO();
                    BookDTO book = new BookDTO(bookID, title, image, description, author, categoryDAO.getNameByID(categoryID), status, price, quantity, importDate);
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(book);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return result;
    }

    public List<BookDTO> searchBookByPrice(float from, float to) throws Exception {

        List<BookDTO> result = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "SELECT bookID,title,image, description,author,categoryID,status,price,quantity,importDate FROM Books  where price between ? and ?";

                ps = con.prepareStatement(sql);
                ps.setFloat(1, from);
                ps.setFloat(2, to);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String bookID = rs.getString("bookID");
                    String title = rs.getString("title");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    String author = rs.getString("author");
                    String categoryID = rs.getString("categoryID");
                    String status = rs.getString("status");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    Date importDate = rs.getDate("importDate");
                    CategoryDAO categoryDAO = new CategoryDAO();
                    BookDTO book = new BookDTO(bookID, title, image, description, author, categoryDAO.getNameByID(categoryID), status, price, quantity, importDate);
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(book);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return result;
    }

    public boolean deleteBook(String bookID) throws Exception {
        boolean check = false;
        try {
            con = new DBHelper().getConnection();
            if (con != null) {
                String sql = "update Books set status=? where bookID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "Inactive");
                ps.setString(2, bookID);
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return check;
    }

    public boolean updateQuantity(String bookID, int quantity) throws Exception {
        boolean check = false;
        try {
            con = new DBHelper().getConnection();
            if (con != null) {
                String sql = "update Books set quantity=? where bookID=?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, quantity);
                ps.setString(2, bookID);
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return check;
    }

    public boolean checkBookID(String bookID) throws Exception {
        boolean check = false;
        String sql = "select title from Books where bookID=?";
        try {
            con = DBHelper.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, bookID);
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

    public boolean createBook(BookDTO book) throws Exception {

        boolean check = false;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "insert into Books(bookID,title,image,description,price,author,categoryID,status,quantity,importDate) values(?,?,?,?,?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, book.getBookID());
                ps.setString(2, book.getTitle());
                ps.setString(3, book.getImage());
                ps.setString(4, book.getDescription());
                ps.setFloat(5, book.getPrice());
                ps.setString(6, book.getAuthor());
                ps.setString(7, book.getCategoryID());
                ps.setString(8, book.getStatus());
                ps.setInt(9, book.getQuantity());
                ps.setTimestamp(10, new Timestamp(book.getImportDate().getTime()));

                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return check;
    }

    public BookDTO getBookByID(String bookID) throws Exception {

        BookDTO result = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "SELECT title,image,description,author,categoryID,status,price,quantity,importDate FROM Books WHERE bookID=?";

                ps = con.prepareStatement(sql);
                ps.setString(1, bookID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String title = rs.getString("title");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    String author = rs.getString("author");
                    String categoryID = rs.getString("categoryID");
                    String status = rs.getString("status");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    Date importDate = rs.getDate("importDate");
                    CategoryDAO categoryDAO = new CategoryDAO();
                    result = new BookDTO(bookID, title, image, description, author, categoryID, status, price, quantity, importDate);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return result;
    }

    public boolean updateBook(BookDTO book) throws Exception {
        boolean check = false;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "update Books set title=?,image=?,description=?,author=?,categoryID=?,status=?,price=?,quantity=?,importDate=? where bookID=? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, book.getTitle());
                ps.setString(2, book.getImage());
                ps.setString(3, book.getDescription());
                ps.setString(4, book.getAuthor());
                ps.setString(5, book.getCategoryID());
                ps.setString(6, book.getStatus());
                ps.setFloat(7, book.getPrice());
                ps.setInt(8, book.getQuantity());
                ps.setTimestamp(9, new Timestamp(book.getImportDate().getTime()));
                ps.setString(10, book.getBookID());
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return check;
    }

    public int getQuantityByID(String bookID) throws Exception {
        int quantity = 0;
        String sql = "select quantity from Books where bookID=?";
        try {
            con = DBHelper.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, bookID);
            rs = ps.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt("quantity");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.closeConnection(rs, ps, con);
        }
        return quantity;
    }

}
