/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.AlumnoDTO;
import com.ipn.mx.modelo.entidades.Alumno;
import com.ipn.mx.modelo.entidades.Carrera;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author edgar
 */
public class AlumnoDAO {
    private static final String SQL_INSERT = "insert into alumno(nombre, paterno, materno, email, noBoleta, idCarrera) values(?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "update alumno set nombre = ?, paterno = ?, materno = ?, email = ?, noBoleta = ?, idCarrera = ? where idAlumno = ?";
    private static final String SQL_DELETE = "delete from alumno where idAlumno = ?";
    private static final String SQL_READ = "select * from alumno where idAlumno = ?";
    private static final String SQL_READ_ALL = "select * from alumno";

    private Connection conexion;

    private void obtenerConexion() {
        String usuario = "root";
        String clave = "admin";
        String url ="jdbc:mysql://localhost:3306/proyecto?zeroDateTimeBehavior=CONVERT_TO_NULL";
        //String url = "jdbc:mysql://localhost:3306/proyecto?serverTimezone=America/Mexico_City&allowPublicKeyRetrieval=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&useSSL=false";
        String driverMySql = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driverMySql);
            conexion = DriverManager.getConnection(url, usuario, clave);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void create(Connection con, AlumnoDTO dto)throws SQLException{
        obtenerConexion();
        PreparedStatement ps = null;
        try{
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setString(1, dto.getEntidad().getNombre());
            ps.setString(2, dto.getEntidad().getPaterno());
            ps.setString(3, dto.getEntidad().getMaterno());
            ps.setString(4, dto.getEntidad().getEmail());
            ps.setString(5, dto.getEntidad().getNoBoleta());
            ps.setInt(6, dto.getEntidad().getIdCarrera());
            ps.executeUpdate();
        }finally{
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }
    public void update(Connection con, AlumnoDTO dto)throws SQLException{
        obtenerConexion();
        PreparedStatement ps = null;
        try{
            ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setString(1, dto.getEntidad().getNombre());
            ps.setString(2, dto.getEntidad().getPaterno());
            ps.setString(3, dto.getEntidad().getMaterno());
            ps.setString(4, dto.getEntidad().getEmail());
            ps.setString(5, dto.getEntidad().getNoBoleta());
            ps.setInt(6, dto.getEntidad().getIdCarrera());
            ps.setInt(7, dto.getEntidad().getIdAlumno());
            ps.executeUpdate();
        }finally{
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }
    public void delete(Connection con, AlumnoDTO dto)throws SQLException{
        obtenerConexion();
        PreparedStatement ps = null;
        try{
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getEntidad().getIdAlumno());
            ps.executeUpdate();
        }finally{
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }
    public Alumno read(Connection con, AlumnoDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conexion.prepareStatement(SQL_READ);
            ps.setInt(1, dto.getEntidad().getIdAlumno());
            rs = ps.executeQuery();
            List resultados = obtenerResultados(rs);
            if(resultados.size() > 0){
                return (Alumno) resultados.get(0);
            }else{
                return null;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }
    public List readAll() throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conexion.prepareStatement(SQL_READ_ALL);
            rs = ps.executeQuery();
            List resultados = obtenerResultados(rs);
            if(resultados.size() > 0){
                return resultados;
            }else{
                return null;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }

    private List obtenerResultados(ResultSet rs) throws SQLException{
        List resultados = new ArrayList();
        while(rs.next()){
            Alumno a  = new Alumno();
            a.setIdAlumno(rs.getInt("idAlumno"));
            a.setNombre(rs.getString("nombre"));
            a.setPaterno(rs.getString("paterno"));
            a.setMaterno(rs.getString("materno"));
            a.setEmail(rs.getString("email"));
            a.setNoBoleta(rs.getString("noBoleta"));
            a.setIdCarrera(rs.getInt("idCarrera"));
            resultados.add(a);
        }
        return resultados;
    }
}
