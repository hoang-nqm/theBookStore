/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnqm.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Minh Hoang
 */
@MultipartConfig
public class MainController extends HttpServlet {

    private static final String HOME = "HomeController";
    private static final String ADMIN = "ManagerAdminController";
    private static final String USER = "UserHomeController";
    private static final String LOGIN = "LoginController";
    private static final String SEARCH = "SearchController";
    private static final String SEARCH_AD = "SearchADController";
    private static final String DELETE = "DeleteBookController";
    private static final String UPDATE = "UpdateBookController";
    private static final String INSERT_BOOK = "InsertBookController";
    private static final String VIEW_INSERT = "ViewInsertController";
    private static final String VIEW_UPDATE = "GetBookByIDController";
    private static final String HISTORY = "HistoryController";
    private static final String SEARCHH = "SearchhController";
    private static final String MORE_DETAIL = "MoreDetailController";

    private static final String ADD_CART = "AddToCartController";
    private static final String DELETE_CART = "DeleteCartController";
    private static final String UPDATE_CART = "UpdateCartController";
    private static final String CHECKOUT_CART = "CheckoutCartController";

    private static final String VIEW_LOGIN = "login.html";
    private static final String ERROR = "error.jsp";
    private static final String REDIRECT_LOGIN = "login.jsp";
    private static final String REDIRECT_INSERT = "viewInsert.jsp";
    private static final String REDIRECT_UPDATE = "viewUpdate.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        String action = request.getParameter("action");
        try {
            if (action == null) {
                url = HOME;
            } else if ("Login".equals(action)) {
                url = LOGIN;
            } else if ("loginUser".equals(action)) {
                url = VIEW_LOGIN;
            } else if ("Search".equals(action)) {
                url = SEARCH;
            } else if ("insertBook".equals(action)) {
                url = VIEW_INSERT;
            } else if ("Insert".equals(action)) {
                url = INSERT_BOOK;
            } else if ("admin".equals(action)) {
                url = ADMIN;
            } else if ("user".equals(action)) {
                url = USER;
            } else if ("delete".equals(action)) {
                url = DELETE;
            } else if ("redirectInsert".equals(action)) {
                url = REDIRECT_INSERT;
            } else if ("viewUpdate".equals(action)) {
                url = VIEW_UPDATE;
            } else if ("Update".equals(action)) {
                url = UPDATE;
            } else if ("redirectUpdate".equals(action)) {
                url = REDIRECT_UPDATE;
            } else if ("SearchAD".equals(action)) {
                url = SEARCH_AD;
            } else if ("addCart".equals(action)) {
                url = ADD_CART;
            } else if ("deleteCart".equals(action)) {
                url = DELETE_CART;
            } else if ("checkOut".equals(action)) {
                url = CHECKOUT_CART;
            } else if ("updateCart".equals(action)) {
                url = UPDATE_CART;
            } else if ("history".equals(action)) {
                url = HISTORY;
            } else if ("SearchH".equals(action)) {
                url = SEARCHH;
            } else if ("moreDetail".equals(action)) {
                url = MORE_DETAIL;
            }

        } catch (Exception e) {
            log("Error :" + e.getMessage());
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
