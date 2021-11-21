/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnqm.controller;

import hnmq.order.OrderDAO;
import hnmq.order.OrderDTO;
import hnqm.book.BookDAO;
import hnqm.detail.DetailDAO;
import hnqm.detail.DetailDTO;
import hnqm.user.UserDAO;
import hnqm.user.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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
@WebServlet(name = "CheckoutCartController", urlPatterns = {"/CheckoutCartController"})
public class CheckoutCartController extends HttpServlet {

    private static final String ERROR = "cart.jsp";
    private static final String SUCCESS = "MainController?action=user";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        boolean check = true;
        BookDAO bookDAO = new BookDAO();
        OrderDAO orderDAO = new OrderDAO();
        UserDAO userDAO = new UserDAO();
        DetailDAO detailDAO = new DetailDAO();
        HttpSession session = request.getSession();
        ArrayList<DetailDTO> list = (ArrayList<DetailDTO>) session.getAttribute("CART");
        UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
        try {
            if (user == null) {
                url = "LogoutController";
            } else {
                String userID1 = user.getUserID();
                String statusUser = userDAO.getStatus(userID1);
                if (statusUser.equals("Active")) {
                    if (list != null) {
                        float total = Float.parseFloat(request.getParameter("total"));
                        String userID = request.getParameter("userID");
                        for (DetailDTO detailDTO1 : list) {
                            int checkQuantity = bookDAO.getQuantityByID(detailDTO1.getBook().getBookID());
                            if (detailDTO1.getBook().getStatus().equals("Active")) {
                                if (detailDTO1.getQuantity() > checkQuantity) {
                                    request.setAttribute("ERR", "The number of " + detailDTO1.getBook().getTitle() + " books is not enough");
                                    check = false;
                                }
                            } else {
                                request.setAttribute("ERR",  detailDTO1.getBook().getTitle() + " book is no longer active");
                                check = false;
                            }
                        }
                        if (check) {
                            if (orderDAO.insertOrder(new OrderDTO(0, userID, new Date(), total)) != -1) {
                                for (DetailDTO detailDTO : list) {
                                    int orderID = orderDAO.getOrderID();
                                    detailDTO.setOrderID(orderID);
                                    if (detailDAO.insertDetail(detailDTO) != -1) {
                                        int CurQuantity = bookDAO.getQuantityByID(detailDTO.getBook().getBookID());
                                        String bookID = detailDTO.getBook().getBookID();
                                        int Quantity = CurQuantity - detailDTO.getQuantity();
                                        boolean result = bookDAO.updateQuantity(bookID, Quantity);
                                    }
                                }
                                session.setAttribute("CART", null);
                                request.setAttribute("CHECK_OUT", "Buy Successfully!");
                                url = SUCCESS;

                            }
                        }

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
