/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnqm.controller;

import hnmq.order.OrderDAO;
import hnmq.order.OrderDTO;
import hnqm.category.CategoryDAO;
import hnqm.detail.DetailDAO;
import hnqm.detail.DetailDTO;
import hnqm.user.UserDAO;
import hnqm.user.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "MoreDetailController", urlPatterns = {"/MoreDetailController"})
public class MoreDetailController extends HttpServlet {
    
    private static final String SUCCESS = "moreDetail.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SUCCESS;
        OrderDAO orderDAO = new OrderDAO();
        DetailDAO detailDAO = new DetailDAO();
        UserDAO userDAO = new UserDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
        String orderID = request.getParameter("orderID");
        try {
            if (user == null) {
                url = "LogoutController";
            } else {
                String userID = user.getUserID();
                String statusUser = userDAO.getStatus(userID);
                if (statusUser.equals("Active")) {
                    if (orderDAO.checkOderID(orderID)) {
                        List<DetailDTO> listDetail = detailDAO.getOrderbyOrderID(orderID);
                        request.setAttribute("LIST_DETAIL", listDetail);
                    } else {
                        url = "MainController";
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
