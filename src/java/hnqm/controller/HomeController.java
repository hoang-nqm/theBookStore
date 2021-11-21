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
import hnqm.user.UserDTO;
import java.io.IOException;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Minh Hoang
 */
@WebServlet(name = "HomeController", urlPatterns = {"/HomeController"})
public class HomeController extends HttpServlet {

    private static final String SUCCESS = "home.jsp";
    private static final String ERROR = "error.jsp";
  

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        BookDAO bookDAO = new BookDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
        try {
            if (user == null) {
                List<BookDTO> listBook = bookDAO.getAllBook();
                List<CategoryDTO> listCategory = categoryDAO.getAllCategory();
                request.setAttribute("LIST_CATEGORY", listCategory);
                request.setAttribute("LIST_BOOK", listBook);
                url = SUCCESS;
            } else {
                session.invalidate();
                List<BookDTO> listBook = bookDAO.getAllBook();
                List<CategoryDTO> listCategory = categoryDAO.getAllCategory();
                request.setAttribute("LIST_CATEGORY", listCategory);
                request.setAttribute("LIST_BOOK", listBook);
                url = SUCCESS;
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
