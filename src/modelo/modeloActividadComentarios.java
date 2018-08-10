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
import javax.swing.JOptionPane;

/**
 *
 * @author ITZEL
 */
public class modeloActividadComentarios {
    Conexion conexion = new Conexion();
    //Datos de las actividad
    public String[] datosActividades(String idAc)
    {
        ResultSet sql;       
         try {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            sql = s.executeQuery("SELECT actividad.nombre, tiene.foto, actividad.descripcion, tiene.localizacion FROM actividad " +
                    "INNER JOIN tiene ON actividad.idActividad = tiene.Actividad_idActividad " +
                    "where actividad.idActividad = " + idAc + ";");
                        //declaración del array
            String [] a = new String [5];
            //copiar del resultset al array
            sql.next();
            
            a[0] = sql.getString(1);
            a[1] = sql.getString(2);
            a[2] = sql.getString(3);
            a[3] = sql.getString(4);

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
    
    public boolean insertarComentarios(String comentarios, int calificacion, String usuario, int actividad, String titulo, String fecha)
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
                    + "'" + actividad + "', "
                    + "NULL, "
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
    
    public String ComentariosCalificacion(String id){   
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
