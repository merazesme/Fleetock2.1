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
    public String[] datosActividades(String idAc, String idD)
    {
        ResultSet sql;       
         try {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            sql = s.executeQuery("SELECT actividad.nombre, tiene.foto, actividad.descripcion, tiene.localizacion, "
                    + "estiloviaje.tipo, estiloviaje.descripcion, estiloviaje.presupuesto_min, estiloviaje.presupuesto_max, "
                    + "estiloviaje.foto FROM actividad " +
                    "INNER JOIN tiene ON actividad.idActividad = tiene.Actividad_idActividad " +
                    "INNER JOIN posee on posee.tiene_idTiene = tiene.idTiene " +
                    "INNER JOIN estiloviaje ON estiloviaje.idEstiloViaje = posee.EstiloViaje_idEstiloViaje " +
                    "where actividad.idActividad = " + idAc + " and tiene.Destino_idDestino="+idD+";");
                        //declaración del array
            String [] a = new String [9];
            //copiar del resultset al array
            sql.next();
            
            a[0] = sql.getString(1);
            a[1] = sql.getString(2);
            a[2] = sql.getString(3);
            a[3] = sql.getString(4);
            a[4] = sql.getString(5);
            a[5] = sql.getString(6);
            a[6] = sql.getString(7);
            a[7] = sql.getString(8);
            a[8] = sql.getString(9);

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
