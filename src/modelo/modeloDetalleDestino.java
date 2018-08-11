/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author ITZEL
 */
public class modeloDetalleDestino {
    private Conexion conexion = new Conexion();
    //Datos del detino
    public String[] datosDestinos(String id)
    {
        ResultSet sql;       
         try {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            sql = s.executeQuery("SELECT `nombre`, `foto` from destino "
                    + "where idDestino = " + id + ";");
            //declaración del array
            String [] a = new String [3];
            //copiar del resultset al array
            sql.next();
            
            a[0] = sql.getString(1);
            a[1] = sql.getString(2);

           conexion.cerrarConexion(con);
           return a;
        }
        catch (SQLException ex)
        {
            return null;
        }
         catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Error al intentar conectar con el servidor.");
            return null;
        }
    }
    
    //Calificaciones de las actividades
    public String actividadCal(String id){   
       try
        {
            //abrir conexión
            Connection con= conexion.abrirConexion(); 
            //generar consultas
            Statement s = con.createStatement(); 
            //consulta
            ResultSet rs = s.executeQuery("SELECT avg(calificacion) FROM `comentarios` where comentarios.actividad_idActividad = " + id + ";");
        
            //declaración del array
            String  a;
            rs.next();
            //copiar del resultset al array
            a = rs.getString(1);

            //cerrar conexión
            conexion.cerrarConexion(con); 
            return a; 
        }
        catch(SQLException e)
        {
          return null;    
        }
    }
    
    //Datos de las actividades
    public String[][] datosActividades(String idDes)
    {
        ResultSet sql;       
         try {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            sql = s.executeQuery("SELECT actividad.idActividad, actividad.nombre, tiene.foto FROM actividad " +
                    "INNER JOIN tiene ON actividad.idActividad = tiene.Actividad_idActividad " +
                    "where tiene.Destino_idDestino = " + idDes + ";");
            //número de registros obrenidos
            int count = 0;
            while (sql.next()) {
                ++count;
            }
            //declaración del array
            String [][] a = new String [count][4];
            //se regresa al primero
            sql.beforeFirst();
            //contador para copiar del resultset al array
            int i = 0;
            //copiar del resultset al array
            while (sql.next())
            {
                a[i][0] = sql.getString(1);
                a[i][1] = sql.getString(2);
                a[i][2] = sql.getString(3);
                i++;
            } 
           conexion.cerrarConexion(con);
           return a;
        }
        catch (SQLException ex)
        {
            return null;
        }
         catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Error al intentar conectar con el servidor.");
            return null;
        }
    }
    
    public String[][] datosTransportes(String idDes)
    {
        ResultSet sql;       
         try {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            sql = s.executeQuery("SELECT transporte.idTransporte, transporte.tipo, transporte.foto, estiloviaje.tipo, " + 
                    "corresponde.costo FROM `transporte` " +
                    "INNER JOIN sedesplazaen ON transporte.idTransporte = sedesplazaen.Transporte_idTransporte " +
                    "INNER JOIN corresponde ON corresponde.sedesplazaen_idSeDesplazaEn = sedesplazaen.idSeDesplazaEn " +
                    "INNER JOIN estiloviaje ON estiloviaje.idEstiloViaje = corresponde.EstiloViaje_idEstiloViaje " +
                    "WHERE sedesplazaen.Destino_idDestino = " + idDes + ";");
            //número de registros obrenidos
            int count = 0;
            while (sql.next()) {
                ++count;
            }
            //declaración del array
            String [][] a = new String [count][6];
            //se regresa al primero
            sql.beforeFirst();
            //contador para copiar del resultset al array
            int i = 0;
            //copiar del resultset al array
            while (sql.next())
            {
                a[i][0] = sql.getString(1);
                a[i][1] = sql.getString(2);
                a[i][2] = sql.getString(3);
                a[i][3] = sql.getString(4);
                a[i][4] = sql.getString(5);
                i++;
            } 
           conexion.cerrarConexion(con);
           return a;
        }
        catch (SQLException ex)
        {
            return null;
        }
         catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Error al intentar conectar con el servidor.");
            return null;
        }
    }
    
    //Agregar deseo
    public boolean deseos(String idU, String idD){
        try{
            //abrir la conexion a la BD
            Connection con = conexion.abrirConexion();
            //Para ejecutar la consulta
            Statement s = con.createStatement();
            int registro = s.executeUpdate("INSERT INTO `deseos`(`usuario_idUsuario`, `destino_idDestino`) "
                    + "VALUES ("+idU+","+idD+");");
            conexion.cerrarConexion(con); 
            return true;
        }catch(SQLException e){
            return false;
        }
    }
    
    //Quitar deseo
    public boolean deseosQ(String idU, String idD){
        try {
            Connection con = conexion.abrirConexion();
            
            //Para ejecutar la consulta
            Statement s = con.createStatement();
            
            //Delete en la tabla pertenece
            int registro = s.executeUpdate("DELETE FROM `deseos` WHERE `usuario_idUsuario` = "+idU+" "
                    + "and `destino_idDestino` = "+idD+";");
            
            conexion.cerrarConexion(con);
            return true;
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al intentar abrir la base de datos.");
            return false;
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Error al intentar conectar con el servidor.");
            return false;
        }
    }
    
    //consultar deseo
    public String [] deseosC(String idU)
    {
        ResultSet sql;       
         try {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            sql = s.executeQuery("SELECT `destino_idDestino` FROM `deseos` WHERE `usuario_idUsuario` = "+idU+";");
            //declaración del array
            //número de registros obrenidos
            int count = 0;
            while (sql.next()) {
                ++count;
            }
            //declaración del array
            String [] a = new String [count];
            //se regresa al primero
            sql.beforeFirst();
            //contador para copiar del resultset al array
            int i = 0;
            //copiar del resultset al array
            while (sql.next())
            {
                a[i] = sql.getString(1);
                i++;
            } 
           conexion.cerrarConexion(con);
           return a;
        }
        catch (SQLException ex)
        {
            return null;
        }
         catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Error al intentar conectar con el servidor.");
            return null;
        }
    }
    
    //Para insertar un comentario en destino
    //public boolean insertarComentarios(String comentarios, int calificacion, String usuario, String nada,int destino, String titulo, String fecha)
    public boolean insertarComentarios(String comentarios, int calificacion, String usuario, int destino, String titulo, String fecha)
    {
        try
        {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            int registro = s.executeUpdate("insert into comentarios(comentario, calificacion, usuario_idUsuario, actividad_idActividad, destino_idDestino, titulo, fecha) values("
                    + ""  
                    + "'" + comentarios + "', "
                    + "'" + calificacion + "', "
                    + "'" + usuario + "', "
                    + "NULL, "
                    + "'" + destino + "', "
                    + "'" + titulo + "', "
                    + "'" + fecha + "');");
            conexion.cerrarConexion(con);
            return true;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public String[][] Comentarios(String sentencia)
    {   
       try
        {
            //abrir conexión
            Connection con= conexion.abrirConexion(); 
            //generar consultas
            Statement s = con.createStatement(); 
            //consulta
            ResultSet rs = s.executeQuery(sentencia);
            //número de registros obrenidos
            int count = 0;
            while (rs.next()) {
                ++count;
            }
            //declaración del array
            String [][] a = new String [count][9];
            //se regresa al primero
            rs.beforeFirst();
            //contador para copiar del resultset al array
            int i = 0;
            //copiar del resultset al array
            while (rs.next())
            {
                a[i][0] = rs.getString(1);
                a[i][1] = rs.getString(2);
                a[i][2] = rs.getString(3);
                a[i][3] = rs.getString(4);
                a[i][4] = rs.getString(5);
                a[i][5] = rs.getString(6);
                a[i][6] = rs.getString(7);
                a[i][7] = rs.getString(8);
                a[i][8] = rs.getString(9);
                i++;
            }  
            //cerrar conexión
            conexion.cerrarConexion(con); 
            return a; 
        }
        catch(SQLException e)
        {
          return null;    
        }
    }

    public String ComentariosCalificacion(String id)
    {   
       try
        {
            //abrir conexión
            Connection con= conexion.abrirConexion(); 
            //generar consultas
            Statement s = con.createStatement(); 
            //consulta
            ResultSet rs = s.executeQuery("SELECT avg(calificacion) FROM `comentarios` where comentarios.idComentarios = " + id + ";");
        
            //declaración del array
            String  a;
            rs.next();
            //copiar del resultset al array
            a = rs.getString(1);

            //cerrar conexión
            conexion.cerrarConexion(con); 
            return a; 
        }
        catch(SQLException e)
        {
          return null;    
        }
    }
}

