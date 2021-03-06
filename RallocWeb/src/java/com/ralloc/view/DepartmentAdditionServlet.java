/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ralloc.view;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ralloc.model.Department;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
/**
 *
 * @author kaushiknsiyer
 */
@WebServlet(name = "DepartmentAdditionServlet", urlPatterns = {"/Department/add"})
public class DepartmentAdditionServlet extends HttpServlet {
    public static String errorMessage = "";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet DepartmentAdditionServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>"+request.getParameter("DepartmentName")+"</h1>");
//            out.println("<h1>"+request.getParameter("ClusterName")+"</h1>");
//            out.println("<h1>"+request.getParameter("MaximumIntake")+"</h1>");
//            out.println("</body>");
//            out.println("</html>");
            try {
                String intake =  (String)request.getParameter("MaximumIntake");
                if(Integer.parseInt(intake) <= 0)
                    throw new InputMismatchException("Invalid intake value ");
                String deptName = (String)request.getParameter("DepartmentName");
                if(!deptName.matches("[a-zA-Z]*"))
                    throw new InputMismatchException("Invalid Department Name");
                Department.addDepartment(request.getParameter("DepartmentName"), request.getParameter("ClusterName"),request.getParameter("MaximumIntake"));
                response.sendRedirect(request.getContextPath()+"/home");
            } catch (SQLException | InputMismatchException ex) {
                errorMessage = "An invalid or existing department data was entered : " + ex.getMessage();
                response.sendRedirect(request.getContextPath()+"/viewError.jsp");
                Logger.getLogger(DepartmentAdditionServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
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
