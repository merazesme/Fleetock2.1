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
public class modeloEditarViaje {
    Conexion conexion = new Conexion();
    //Datos Viaje
    public String[] datosViajes(String idV)
    {
        ResultSet sql;       
         try {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            sql = s.executeQuery("SELECT viaje.nombre, viaje.descripcion, viaje.fecha_inicio, viaje.fecha_fin, "
                    + "viaje.estadoDelViaje, estiloviaje.tipo, pertenece.Destino_idDestino from viaje " +
                    "INNER JOIN estiloviaje on estiloviaje.idEstiloViaje = viaje.EstiloViaje_idEstiloViaje " +
                    "INNER JOIN pertenece ON pertenece.Viaje_idViaje = viaje.idViaje " +
                    "where viaje.idViaje = "+idV+";");
            //declaración del array
            String [] a = new String [8];
            sql.next();
            //copiar del resultset al array
            a[0] = sql.getString(1);
            a[1] = sql.getString(2);
            a[2] = sql.getString(3);
            a[3] = sql.getString(4);
            a[4] = sql.getString(5);
            a[5] = sql.getString(6);
            a[6] = sql.getString(7);
           conexion.cerrarConexion(con);
           return a;
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al intentar abrir la base de datos.");
            return null;
        }
         catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Error al intentar conectar con el servidor.");
            return null;
        }
    }
    
    //Datos de los Estilos de viaje
    public String[][] datosEstiloViaje()
    {
        ResultSet sql;       
         try {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            sql = s.executeQuery("SELECT `idEstiloViaje`, `tipo` FROM `estiloviaje` ;");
            //número de registros obrenidos
            int count = 0;
            while (sql.next()) {
                ++count;
            }
            //declaración del array
            String [][] a = new String [count][2];
            //se regresa al primero
            sql.beforeFirst();
            //contador para copiar del resultset al array
            int i = 0;
            //copiar del resultset al array
            while (sql.next())
            {
                a[i][0] = sql.getString(1);
                a[i][1] = sql.getString(2);
                i++;
            } 
           conexion.cerrarConexion(con);
           return a;
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al intentar abrir la base de datos.");
            return null;
        }
        catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Error al intentar conectar con el servidor.");
            return null;
        }
    }
    
    //Actividades del viaje
    public String[][] actividadesViaje(String idViaje, String where){   
       try
        {
            //abrir conexión
            Connection con= conexion.abrirConexion(); 
            //generar consultas
            Statement s = con.createStatement(); 
            //consulta
            ResultSet rs = s.executeQuery("SELECT actividad.idActividad, actividad.nombre, tiene.foto, contiene.fechaActividad "
                    + "FROM `actividad` "
                    + "INNER JOIN contiene on contiene.Actividad_idActividad = actividad.idActividad "
                    + "INNER JOIN tiene on tiene.Actividad_idActividad = contiene.Actividad_idActividad "
                    + "INNER JOIN pertenece on pertenece.Viaje_idViaje = contiene.Viaje_idViaje "
                    + "WHERE contiene.Viaje_idViaje = "+idViaje+" and "
                    + "tiene.Destino_idDestino = pertenece.Destino_idDestino"
                    + where+";");
            //número de registros obrenidos
            int count = 0;
            while (rs.next()) {
                ++count;
            }
            //declaración del array
            String [][] a = new String [count][4];
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
                i++;
            }  
            //cerrar conexión
            conexion.cerrarConexion(con); 
            return a; 
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al intentar abrir la base de datos.");
            return null;    
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Error al intentar conectar con el servidor.");
            return null;
        }
    }
    
    //Modificar fecha de actividades
    public boolean actualizarActividad(String idViaje, String idActividad, String fecha)
    {   try
        {
            Connection con= conexion.abrirConexion(); 
            Statement s= con.createStatement(); 
            int registro =s.executeUpdate("UPDATE `contiene` SET `fechaActividad`=  " + fecha
                    + " WHERE `Viaje_idViaje` = "+idViaje+" and `Actividad_idActividad` = "+idActividad+";");
            conexion.cerrarConexion(con); 
            return true; 
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al intentar abrir la base de datos.");
            return false; 
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Error al intentar conectar con el servidor.");
            return false;
        }
    }
    
    //Actualizar viaje
    public boolean actualizarViaje(String idViaje, String nombre, String descripcion, String estado, String estilo, String fechai, String fechaf)
    {   try
        {
            Connection con= conexion.abrirConexion(); 
            Statement s= con.createStatement(); 
            int registro =s.executeUpdate("UPDATE `viaje` SET `nombre`='"+nombre+"',`descripcion`='"+descripcion+"',"
                    + "`fecha_inicio`='"+fechai+"',`fecha_fin`='"+fechaf+"',`estadoDelViaje`='"+estado+"',"
                    + "`EstiloViaje_idEstiloViaje`= " + estilo
                    + " WHERE idViaje = " + idViaje); 
            conexion.cerrarConexion(con); 
            return true; 
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al intentar abrir la base de datos.");
            return false; 
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Error al intentar conectar con el servidor.");
            return false;
        }
    }
    
    //Eliminar viaje
    public boolean viajeEliminar(String idViaje) {                                         
        try {
            Connection con = conexion.abrirConexion();
            
            //Para ejecutar la consulta
            Statement s = con.createStatement();
            
            //Delete en la tabla pertenece
            int registroPertenece = s.executeUpdate("delete from pertenece where Viaje_idViaje = " + idViaje + ";");
            int registroContiene = s.executeUpdate("delete from contiene where Viaje_idViaje = " + idViaje + ";");
            int registroViaje = s.executeUpdate("delete from viaje where idViaje = " + idViaje + ";");
            
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
}
