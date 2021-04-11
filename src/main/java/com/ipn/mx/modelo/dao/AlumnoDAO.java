/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.AlumnoDTO;
import com.ipn.mx.modelo.entidades.Alumno;
import com.ipn.mx.modelo.entidades.Carrera;
import java.sql.CallableStatement;
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
    private static final String SQL_INSERT = "{call spCreate(?,?,?,?,?,?)}";
    private static final String SQL_UPDATE = "{call spUpdate(?,?,?,?,?,?,?)}";
    private static final String SQL_DELETE = "{call spDelete(?)}";
    private static final String SQL_READ = "{call spRead(?)}";
    private static final String SQL_READ_ALL = "call spReadAll()";

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
    
    public void create(AlumnoDTO dto)throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        try{
            cs = conexion.prepareCall(SQL_INSERT);
            cs.setString(1, dto.getEntidad().getNombre());
            cs.setString(2, dto.getEntidad().getPaterno());
            cs.setString(3, dto.getEntidad().getMaterno());
            cs.setString(4, dto.getEntidad().getEmail());
            cs.setString(5, dto.getEntidad().getNoBoleta());
            cs.setInt(6, dto.getEntidad().getIdCarrera());
            cs.executeUpdate();
        }finally{
            if (cs != null) {
                cs.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }
    public void update(AlumnoDTO dto)throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        try{
            cs = conexion.prepareCall(SQL_UPDATE);
            cs.setString(1, dto.getEntidad().getNombre());
            cs.setString(2, dto.getEntidad().getPaterno());
            cs.setString(3, dto.getEntidad().getMaterno());
            cs.setString(4, dto.getEntidad().getEmail());
            cs.setString(5, dto.getEntidad().getNoBoleta());
            cs.setInt(6, dto.getEntidad().getIdCarrera());
            cs.setInt(7, dto.getEntidad().getIdAlumno());
            cs.executeUpdate();
        }finally{
            if (cs != null) {
                cs.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }
    public void delete(AlumnoDTO dto)throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        try{
            cs = conexion.prepareCall(SQL_DELETE);
            cs.setInt(1, dto.getEntidad().getIdAlumno());
            cs.executeUpdate();
        }finally{
            if (cs != null) {
                cs.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }
    public AlumnoDTO read(AlumnoDTO dto) throws SQLException {
        obtenerConexion();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = conexion.prepareCall(SQL_READ);
            cs.setInt(1, dto.getEntidad().getIdAlumno());
            rs = cs.executeQuery();
            List resultados = obtenerResultados(rs);
            if(resultados.size() > 0){
                return (AlumnoDTO) resultados.get(0);
            }else{
                return null;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cs != null) {
                cs.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }
    
    public List readAll() throws SQLException {
        obtenerConexion();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = conexion.prepareCall(SQL_READ_ALL);
            rs = cs.executeQuery();
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
            if (cs != null) {
                cs.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }

    private List obtenerResultados(ResultSet rs) throws SQLException{
        List resultados = new ArrayList();
        while(rs.next()){
            AlumnoDTO dto = new AlumnoDTO();
            dto.getEntidad().setIdAlumno(rs.getInt("idAlumno"));
            dto.getEntidad().setNombre(rs.getString("nombre"));
            dto.getEntidad().setPaterno(rs.getString("paterno"));
            dto.getEntidad().setMaterno(rs.getString("materno"));
            dto.getEntidad().setEmail(rs.getString("email"));
            dto.getEntidad().setNoBoleta(rs.getString("noBoleta"));
            dto.getEntidad().setIdCarrera(rs.getInt("idCarrera"));
            resultados.add(dto);
        }
        return resultados;
    }
    
    public static void main(String[] args) {
        AlumnoDTO dto = new AlumnoDTO();
        /*
        dto.getEntidad().setNombre("Sergio");
        dto.getEntidad().setPaterno("Valle");
        dto.getEntidad().setMaterno("Ortiz");
        dto.getEntidad().setEmail("edilberto.sergio.valle.ortiz@email.com");
        dto.getEntidad().setNoBoleta("2019630028");
        dto.getEntidad().setIdCarrera(1);
        dto.getEntidad().setIdAlumno(1);
        */
        dto.getEntidad().setIdAlumno(1);
        AlumnoDAO dao = new AlumnoDAO();
        try {
          //dao.create(dto);
          //dao.update(dto);
           // System.out.println(dao.readAll());
           System.out.println(dao.read(dto));
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
