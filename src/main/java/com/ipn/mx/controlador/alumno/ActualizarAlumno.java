/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador.alumno;

import com.ipn.mx.modelo.dao.AlumnoDAO;
import com.ipn.mx.modelo.dto.AlumnoDTO;
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
@WebServlet(name = "ActualizarAlumno", urlPatterns = {"/ActualizarAlumno"})
public class ActualizarAlumno extends HttpServlet {

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
            out.println("<title>Actualizar Alumno</title>");    
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js'></script>");
           
            out.println("</head>");
            out.println("<body>");

            String msg = "";
            AlumnoDTO dto = new AlumnoDTO();
            dto.getEntidad().setNombre("Edilberto Sergio");
            dto.getEntidad().setPaterno("Valle");
            dto.getEntidad().setMaterno("Ortiz");
            dto.getEntidad().setEmail("edilberto.sergio.valle.ortiz@email.com");
            dto.getEntidad().setNoBoleta("2019630028");
            dto.getEntidad().setIdCarrera(1);
            dto.getEntidad().setIdAlumno(1);

            AlumnoDAO dao = new AlumnoDAO();
            try {
                dao.update(dto);
                msg = "El alumno se actualizo exitosamente";
            } catch (SQLException ex) {
                Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
                msg = "Hubo un error al actualizar a el alumno";
            }
            out.println("<div align='center'>");
            out.println(msg);
            out.println("<br/>");
            out.println("<a href='AlumnoServlet?accion=listaDeAlumnos' class='btn btn-primary'>Lista De Alumnos</a>");
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