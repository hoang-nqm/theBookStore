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
import hnqm.user.UserDAO;
import hnqm.user.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Minh Hoang
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private static final String USER = "MainController?action=user";
    private static final String ADMIN = "MainController?action=admin";
    private static final String ERROR = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        String err = "";
        BookDAO bookDAO = new BookDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        try {

            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            UserDAO userDAO = new UserDAO();
            boolean checkEmail = userDAO.checkEmail(email);
            HttpSession session = request.getSession();
            HttpSession errorSession = request.getSession();
            if (email.isEmpty() || password.isEmpty()) {
                err = "Email and Password must not empty";
                errorSession.setAttribute("ERROR_LOGIN", err);
            } else {
                if (checkEmail) {
                    UserDTO user = userDAO.checkLogin(email, password);
                    if (user != null) {
                        if (user.getRoleID().equals("2")) {
                            if (user.getStatus().equalsIgnoreCase("Active")) {
                                url = USER;
                            }
                        } else if (user.getRoleID().equals("1")) {
                            if (user.getStatus().equalsIgnoreCase("Active")) {
                                url = ADMIN;
                            }
                        }
                        Cookie cookie = new Cookie(email, password);
                        cookie.setMaxAge(60 * 60 * 24 * 7);
                        response.addCookie(cookie);
                        session.setAttribute("LOGIN_USER", user);
                    } else {
                        err = "Wrong password";
                        request.setAttribute("EMAIL", email);
                        errorSession.setAttribute("ERROR_LOGIN", err);
                    }
                } else {
                    err = "Not found account";
                    errorSession.setAttribute("ERROR_LOGIN", err);
                }
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
