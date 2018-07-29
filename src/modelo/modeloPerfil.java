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
public class modeloPerfil {
    Conexion conexion = new Conexion();
    //Datos del usuario
    public String[] usuarioDatos(String id){   
       try
        {
            //abrir conexión
            Connection con= conexion.abrirConexion(); 
            //generar consultas
            Statement s = con.createStatement(); 
            //consulta
            ResultSet rs = s.executeQuery("SELECT usuario.nombre, usuario.apellidos, usuario.foto from usuario " +
                    "INNER JOIN login ON login.Usuario_idUsuario = usuario.idUsuario " +
                    "where usuario.idUsuario = " + id + ";");
        
            //declaración del array
            String [] a = new String[4];
            rs.next();
            //copiar del resultset al array
            a[0] = rs.getString(1);
            a[1] = rs.getString(2);
            a[2] = rs.getString(3);

            //cerrar conexión
            conexion.cerrarConexion(con); 
            return a; 
        }
        catch(SQLException e)
        {
          return null;    
        }
    }
    //Datos Viaje
    public String[][] datosViajes(String idU)
    {
        ResultSet sql;       
         try {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            sql = s.executeQuery("SELECT viaje.idViaje, viaje.nombre, destino.foto, destino.nombre from viaje " +
                        "INNER JOIN pertenece ON pertenece.Viaje_idViaje = viaje.idViaje " +
                        "INNER JOIN destino ON destino.idDestino = pertenece.Destino_idDestino " +
                        "where viaje.Usuario_idUsuario  = "+idU);
            //número de registros obrenidos
            int count = 0;
            while (sql.next()) {
                ++count;
            }
            //declaración del array
            String [][] a = new String [count][5];
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
