/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnqm.controller;

import hnqm.book.BookDAO;
import hnqm.book.BookDTO;
import hnqm.category.CategoryDAO;
import hnqm.category.CategoryDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Minh Hoang
 */
@WebServlet(name = "SearchADController", urlPatterns = {"/SearchADController"})
public class SearchADController extends HttpServlet {

    private static final String SUCCESS = "admin.jsp";
    private static final String ERROR = "admin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        String err = "";
        boolean check = true;
        BookDAO bookDAO = new BookDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        String name = request.getParameter("txtName");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        try {
            if (name.isEmpty() && from.isEmpty() && to.isEmpty()) {
                url = "error.jsp";
                check = false;
            }
            if (from.isEmpty()) {
                from = "0";
            }
            if (to.isEmpty()) {
                to = "0";
            }

            String regexForm = "^[0-9]{0,}$";
            Pattern pattern = Pattern.compile(regexForm);
            Matcher matcher = pattern.matcher(from);
            Matcher matcher1 = pattern.matcher(to);
            if (!matcher.find() || !matcher1.find()) {
                err = "Please enter data type number!";
                check = false;
            }

            if (check) {
                if (name.isEmpty()) {
                    List<BookDTO> listBook = bookDAO.searchBookByPrice(Float.parseFloat(from), Float.parseFloat(to));
                    if (listBook != null) {
                        List<CategoryDTO> listCategory = categoryDAO.getAllCategory();
                        request.setAttribute("LIST_CATEGORY", listCategory);
                        request.setAttribute("LIST_BOOK", listBook);
                        url = SUCCESS;
                    } else {
                        request.setAttribute("NO_DATA", "No Result for data!");
                        List<BookDTO> listBook1 = bookDAO.getAllBook();
                        List<CategoryDTO> listCategory = categoryDAO.getAllCategory();
                        request.setAttribute("LIST_CATEGORY", listCategory);
                        request.setAttribute("LIST_BOOK", listBook1);
                        url = SUCCESS;
                    }

                } else {
                    List<BookDTO> listBook = bookDAO.searchBook(name, Float.parseFloat(from), Float.parseFloat(to));
                    if (listBook != null) {
                        List<CategoryDTO> listCategory = categoryDAO.getAllCategory();
                        request.setAttribute("LIST_CATEGORY", listCategory);
                        request.setAttribute("LIST_BOOK", listBook);
                        url = SUCCESS;
                    } else {
                        request.setAttribute("NO_DATA", "No Result for data!");
                        List<BookDTO> listBook1 = bookDAO.getAllBook();
                        List<CategoryDTO> listCategory = categoryDAO.getAllCategory();
                        request.setAttribute("LIST_CATEGORY", listCategory);
                        request.setAttribute("LIST_BOOK", listBook1);
                        url = SUCCESS;
                    }
                }
            } else {
                request.setAttribute("ERROR_SEARCH", err);
                List<BookDTO> listBook = bookDAO.getAllBook();
                List<CategoryDTO> listCategory = categoryDAO.getAllCategory();
                request.setAttribute("LIST_CATEGORY", listCategory);
                request.setAttribute("LIST_BOOK", listBook);
            }
        } catch (Exception e) {
            log("Error: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
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
