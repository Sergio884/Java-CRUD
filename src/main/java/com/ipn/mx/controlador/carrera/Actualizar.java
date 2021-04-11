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
@WebServlet(name = "Actualizar", urlPatterns = {"/Actualizar"})
public class Actualizar extends HttpServlet {

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
            out.println("<title>Actualizar</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js'></script>");               
            out.println("</head>");
            out.println("<body>");

            String msg = "";
            Carrera c = new Carrera();
            c.setNombreCarrera(request.getParameter("txtNombre"));
            c.setDuracion(Integer.parseInt(request.getParameter("txtDuracion")));
            c.setIdCarrera(Integer.parseInt(request.getParameter("id")));

            CarreraDAO dao = new CarreraDAO();
            try {
                dao.update(c);
                msg = "La carrera se actualizo exitosamente";
            } catch (SQLException ex) {
                Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
                msg = "Hubo un error al actualizar la carrera";
            }
            out.println("<div align='center'>");
            out.println("<h5 class='card-title'>"+msg+"</h5>");
            out.println("<br/>");
            out.println("<a href='MostrarCarreras' class='btn btn-success'>Lista de carreras<a/>");
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
