/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador.carrera;

import com.ipn.mx.modelo.dao.CarreraDAO;
import com.ipn.mx.modelo.entidades.Carrera;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sergio
 */
@WebServlet(name = "VerCarrera", urlPatterns = {"/VerCarrera"})
public class VerCarrera extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Ver Carrera</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js'></script>");   
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<div class='card-boder-info mb-3'>");
            out.println("<div class='card-header'>Carrera</div>");
            out.println("<div class='card-body'>");
            out.println("<h5 class='card-title'>Información</h5>");

            Carrera c = new Carrera();
            c.setIdCarrera(Integer.parseInt(request.getParameter("id")));

            CarreraDAO dao = new CarreraDAO();
            try {
                c = dao.read(c);
            } catch (SQLException ex) {
                Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(c != null){
                out.println("<table class='table table-striped'>");
                   
                  out.println("<tr>");
                    out.println("<th>ID Carrera:</th><td>"+c.getIdCarrera()+"</td>");
                   out.println("</tr>");
                   
                   out.println("<tr>");
                    out.println("<th>Nombre Carrera:</th><td>"+c.getNombreCarrera()+"</td>");
                   out.println("</tr>");
                   
                   out.println("<tr>");
                    out.println("<th>Duracion:</th><td>"+c.getDuracion()+"</td>");
                   out.println("</tr>");
                out.println("</table>");
                out.println("<br/>");
                out.println("<div align='center'>");
                out.println("<a href='EliminarCarrera?id="+c.getIdCarrera()+"' class='btn btn-danger'>Eliminar Carrera</a>");
                out.println("<br/>");
                out.println("<a href='MostrarCarreras' class='btn btn-success'>Mostrar Carreras</a>");
                out.println("</div>");
            }
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");


            out.println("</body>");
            out.println("</html>");
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
