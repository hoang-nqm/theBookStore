/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnqm.controller;

import hnqm.book.BookDAO;
import hnqm.book.BookDTO;
import hnqm.book.InsertBookError;
import hnqm.category.CategoryDAO;
import hnqm.category.CategoryDTO;
import hnqm.user.UserDAO;
import hnqm.user.UserDTO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Minh Hoang
 */
@MultipartConfig
public class UpdateBookController extends HttpServlet {

    private static final String SUCCESS = "MainController?action=admin";
    private static final String ERROR = "MainController?action=redirectUpdate";
    private static final String UPLOAD_DIR = "image";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;
        InsertBookError err = new InsertBookError();
        BookDAO bookDAO = new BookDAO();
        UserDAO userDAO = new UserDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
        try {
            if (user == null) {
                url = "LogoutController";
            } else {
                String userID = user.getUserID();
                String statusUser = userDAO.getStatus(userID);
                if (statusUser.equals("Active")) {

                    String bookID = request.getParameter("bookID");
                    String title = request.getParameter("title");
                    String price = request.getParameter("price");
                    String author = request.getParameter("author");
                    String status = request.getParameter("status");
                    String description = request.getParameter("description");
                    String quantity = request.getParameter("quantity");
                    String category = request.getParameter("category");
                    String importDate = request.getParameter("importDate");
                    String fileName = uploadFile(request);
                    if (fileName.isEmpty()) {
                        BookDTO book = bookDAO.getBookByID(bookID);
                        fileName = book.getImage();
                    }

                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(importDate);
                    boolean check = true;
                    if (title.isEmpty()) {
                        err.setTitleError("Title not empty");
                        check = false;
                    }
                    if (price.isEmpty()) {
                        err.setPriceError("Price not empty");
                        check = false;
                    }
                    if (description.isEmpty()) {
                        err.setDescriptionError("Description not empty");
                        check = false;
                    }
                    String regexNumber = "^([0-9]){0,}$";
                    Pattern pattern = Pattern.compile(regexNumber);
                    Matcher matcher = pattern.matcher(price);
                    if (!matcher.find()) {
                        err.setPriceError("Price must be number");
                        check = false;
                    }
                    if (author.isEmpty()) {
                        err.setAuthorError("Author not empty");
                        check = false;
                    }
                    if (quantity.isEmpty()) {
                        err.setQuantityError("Author not empty");
                        check = false;
                    }
                    Matcher matcherQuantity = pattern.matcher(quantity);
                    if (!matcherQuantity.find()) {
                        err.setQuantityError("Quantity must be number");
                        check = false;
                    }

                    if (status.equals("Active") || status.equals("Inactive")) {

                    } else {
                        err.setStatusError("Status must be Active or Inactive");
                        check = false;
                    }

                    if (check) {
                        BookDTO book = new BookDTO(bookID, title, fileName, description, author, category, status, Float.parseFloat(price), Integer.parseInt(quantity), date);
                        bookDAO.updateBook(book);
                        request.setAttribute("SUCCESS", "Update successully");
                        url = SUCCESS;
                    } else {
                        request.setAttribute("ERROR_CREATE", err);
                        List<CategoryDTO> listCategory = categoryDAO.getAllCategory();
                        request.setAttribute("LIST_CATEGORY", listCategory);
                        BookDTO book = new BookDTO(bookID, title, description, author, status);
                        String cateName = categoryDAO.getNameByID(book.getCategoryID());
                        request.setAttribute("BOOK", book);
                        request.setAttribute("cateName", cateName);
                        request.setAttribute("ERROR_CREATE", err);
                        request.setAttribute("IMPORT", importDate);
                        request.setAttribute("PRICE", price);
                        request.setAttribute("QUANTITY", quantity);

                    }
                } else {
                    url = "LogoutController";
                }

            }

        } catch (Exception e) {
            log("Error: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

    private String uploadFile(HttpServletRequest request) throws IOException, ServletException {
        String fileName = "";
        try {
            Part filePart = request.getPart("file");
            fileName = (String) getFileName(filePart);

            String applicationPath = request.getServletContext().getRealPath("");
            String basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new File(basePath + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception e) {
                e.printStackTrace();
                fileName = "";
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }

        } catch (Exception e) {
            fileName = "";
        }
        return fileName;
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        return null;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
