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
@WebServlet(name = "ActualizarCarrera", urlPatterns = {"/ActualizarCarrera"})
public class ActualizarCarrera extends HttpServlet {

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
            out.println("<title>Actualizar Carrera</title>");            
            out.println("</head>");
            out.println("<body>");
            
             Carrera c = new Carrera();
            c.setIdCarrera(Integer.parseInt(request.getParameter("id")));

            CarreraDAO dao = new CarreraDAO();
            try {
                c = dao.read(c);
            } catch (SQLException ex) {
                Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(c != null){
                out.println("<form name='frmDatos' method='post' action='Actualizar?id="+c.getIdCarrera()+"'>");
                out.println("<table align='center'>");
                   
                  out.println("<tr>");
                    out.println("<td>ID Carrera:</td><td>"+c.getIdCarrera()+"</td>");
                   out.println("</tr>");
                   
                   out.println("<tr>");
                    out.println("<td>Nombre Carrera:</td><td><input type='text' value='"+
                            c.getNombreCarrera()+"' name='txtNombre' id='txtNombre' placeholder='Nombre de la carrera' required='required'/> </td>");
                   out.println("</tr>");
                   
                   out.println("<tr>");
                    out.println("<td>Duración:</td><td><input type='number' value='"+
                            c.getDuracion()+"' name='txtDuracion' id='txtDuracion' min='3' max='6' step='1' required='required'/> </td>");
                   out.println("</tr>");
                   
                   out.println("<tr>");
                   out.println("<td colspan='2'><br/><div align='center'><input type='submit' value='Actualizar' name='btnActualizar'></div></td>");
                   out.println("</tr>");
                   
                out.println("</table>");
                out.println("<br/>");
                out.println("<div align='center'>");
                out.println("<a href='MostrarCarreras'>Mostrar Carreras</a>");
                out.println("</div>");
            }
            
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
