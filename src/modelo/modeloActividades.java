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
public class modeloActividades {
    Conexion conexion = new Conexion();
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
                    "where tiene.Destino_idDestino = " + idDes +";");
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
    
    //Datos de las actividades de busqueda
    public String[][] datosBusqueda(String actividad, String idD)
    {
        ResultSet sql;       
         try {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            sql = s.executeQuery("SELECT actividad.idActividad, actividad.nombre, tiene.foto FROM actividad " +
                "INNER JOIN tiene ON actividad.idActividad = tiene.Actividad_idActividad " +
                "where actividad.nombre like '%"+actividad+"%' and tiene.Destino_idDestino="+idD+";");
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
}
