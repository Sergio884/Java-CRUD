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
 * @author edgar
 */
@WebServlet(name = "AlumnoServlet", urlPatterns = {"/AlumnoServlet"})
public class AlumnoServlet extends HttpServlet {

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
        String accion = request.getParameter("accion");
        if(accion.equals("listaDeAlumnos")){
            listaDeAlumnos(request, response);
        }else{
            if(accion.equals("nuevo")){
                nuevoAlumno(request,response); 
            }else{
                if(accion.equals("actualizar")){
                    actualizarAlumno(request,response);
                    }else{
                        if(accion.equals("eliminar")){
                            eliminarAlumno(request,response);
                        }else{
                            if(accion.equals("ver")){
                            verAlumno(request,response);
                            }else{
                            if(accion.equals("guardar")){
                                almacenarAlumno(request,response);
                            }
                                }
                            }
                        
                        }
                }
        }
        /*response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AlumnoServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AlumnoServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
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

    private void listaDeAlumnos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Listado de Alumnos</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js'></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<div class='card-boder-info mb-3'>");
            out.println("<div class='card-header'>Listado de Alumnos</div>");
            out.println("<div class='card-body'>");
            out.println("<h5 class='card-title'>Lista de Alumno</h5>");
            out.println("<table class='table table-striped'>");
            out.println("<tr>");
            out.println("<th>ID Alumno</th>");
            out.println("<th>Nombre</th>");
            out.println("<th>Apellido Paterno</th>");
            out.println("<th>Apellido Materno</th>");
            out.println("<th>Email</th>");
            out.println("<th>No° Boleta</th>");
            out.println("<th>Actualizar</th>");
            out.println("<th>Borrar</th>");
            out.println("</tr>");
            
            AlumnoDAO dao = new AlumnoDAO();
            try{
                List lista = dao.readAll();
                for (int i = 0; i < lista.size(); i++) {
                    AlumnoDTO dto = (AlumnoDTO) lista.get(i);
                    out.println("<tr>");
                    out.println("<td>");
                    out.println("<a href='AlumnoServlet?accion=ver&id="
                            +dto.getEntidad().getIdAlumno()+"' class='btn btn-success'>"
                            +dto.getEntidad().getIdAlumno()+"</a>");
                    out.println("</td>");
                    
                    out.println("<td>");
                    out.println(dto.getEntidad().getNombre());
                    out.println("</td>");
                    
                     out.println("<td>");
                    out.println(dto.getEntidad().getPaterno());
                    out.println("</td>");
                    
                     out.println("<td>");
                    out.println(dto.getEntidad().getMaterno());
                    out.println("</td>");
                    
                     out.println("<td>");
                    out.println(dto.getEntidad().getEmail());
                    out.println("</td>");
                    
                     out.println("<td>");
                    out.println(dto.getEntidad().getNoBoleta());
                    out.println("</td>");
                    
                    out.println("<td>");
                    out.println("<a href='AlumnoServlet?accion=actualizar&id="
                            +dto.getEntidad().getIdAlumno()+"' class='btn btn-warning'>Actualizar</a>");
                    out.println("</td>");
                    
                    
                    out.println("<td>");
                    out.println("<a href='AlumnoServlet?accion=eliminar&id="
                            +dto.getEntidad().getIdAlumno()+"' class='btn btn-danger'>Eliminar</a>");
                    out.println("</td>");
                    
                    out.println("</tr>");
                    
                    
                }
            }catch(SQLException ex){
                Logger.getLogger(AlumnoServlet.class.getName()).log(Level.SEVERE,null,ex);
            }
            
            out.println("</table>");
            
            out.println("<div align='center'>");
            out.println("<br/>");
            out.println("<a href='alumnoForm.html' class='btn btn-primary'>Crear Alumno</a>");
            out.println("</div>");
            
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void nuevoAlumno(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void actualizarAlumno(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void verAlumno(HttpServletRequest request, HttpServletResponse response) throws IOException {
         response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Información del alumno</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js'></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<div class='card-boder-info mb-3'>");
            out.println("<div class='card-header'>Alumno</div>");
            out.println("<div class='card-body'>");
            out.println("<h5 class='card-title'>Información</h5>");
            
            AlumnoDAO dao = new AlumnoDAO();
            AlumnoDTO dto = new AlumnoDTO();
            
            dto.getEntidad().setIdAlumno(Integer.parseInt(request.getParameter("id")));
            
             try {
                 dto = dao.read(dto);
             } catch (SQLException ex) {
                 Logger.getLogger(AlumnoServlet.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            if(dto != null){   
                out.println("<table class='table table-striped'>");
                out.println("<tr>");
                out.println("<td>ID Alumno</td><td>"+dto.getEntidad().getIdAlumno()+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th>Nombre</th><td>"+dto.getEntidad().getNombre()+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th>Apellido Paterno</th><td>"+dto.getEntidad().getPaterno()+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th>Apellido Materno</th><td>"+dto.getEntidad().getMaterno()+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th>Email</th><td>"+dto.getEntidad().getEmail()+"</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th>No° Boleta</th><td>"+dto.getEntidad().getNoBoleta()+"</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<div align='center'>");
                out.println("<a href='AlumnoServlet?accion=eliminar&id="
                        + dto.getEntidad().getIdAlumno() + "' class='btn btn-danger'>Eliminar</a>");
                out.println("<br/>");
                out.println("<a href='AlumnoServlet?accion=listaDeAlumnos' class='btn btn-primary'>Lista De Alumnos</a>");
                out.println("</div>");
            }
            
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void almacenarAlumno(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void eliminarAlumno(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
