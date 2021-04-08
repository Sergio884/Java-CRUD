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
import java.util.List;
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
@WebServlet(name = "MostrarCarreras", urlPatterns = {"/MostrarCarreras"})
public class MostrarCarreras extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Mostrar Carreras</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h3 align='center'>Listado de Carreras</h3>");
            out.println("<table align='center' border='1'> ");
            out.println("<tr>");
            out.println("<th>ID Carrera</th>");
            out.println("<th>Nombre Carrera</th>");
            out.println("<th>Duración Carrera</th>");
            out.println("<th>Acciones</th>");
            out.println("</tr>");
            out.println();
            out.println();
            CarreraDAO dao = new CarreraDAO();
            try {
                List lista = dao.readAll();
                for (int i = 0; i < lista.size(); i++) {
                    Carrera c = (Carrera) lista.get(i);
                    out.println("<tr>");
                    out.println("<td>");
                    out.println("<a href='VerCarrera?id="+c.getIdCarrera()+"'>"
                            + c.getIdCarrera()+"</a>");
                    out.println("</td>");
                    out.println("<td>");
                    out.println(c.getNombreCarrera());
                    out.println("</td>");
                    out.println("<td>");
                    out.println(c.getDuracion());
                    out.println("</td>");
                    out.println("<td>");
                    out.println("<a href='ActualizarCarrera?id="+c.getIdCarrera()+"'>"+
                            "Actualizar</a>");
                    out.println("</td>");
                    out.println("</tr>");   
                }
            } catch (SQLException ex) {
                Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("</table>");
            out.println("<div align='center'>");
            out.println("<a href='nuevaCarrera.html'>Agregar Carrera</a>");
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
