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
 * @author alfredo
 */
public class modeloPreferencias {
    Conexion conexion = new Conexion();
           
    public String[] preferensias(String idU)
    {   
       try
        {
            //abrir conexión
            Connection con= conexion.abrirConexion(); 
            //generar consultas
            Statement s = con.createStatement(); 
            //consulta
            ResultSet rs = s.executeQuery("SELECT tipositio.idTipoSitio FROM tipositio "
               + "INNER JOIN prefiere ON prefiere.TipoSitio_idTipoSitio = tipositio.idTipoSitio "
               + "INNER JOIN usuario ON usuario.idUsuario = prefiere.Usuario_idUsuario "
               + "WHERE usuario.idUsuario = " + idU);
            //número de registros obrenidos
            int count = 0;
            while (rs.next()) {
                ++count;
            }
            //declaración del array
            String [] a = new String [count];
            //se regresa al primero
            rs.beforeFirst();
            //contador para copiar del resultset al array
            int i = 0;
            //copiar del resultset al array
            while (rs.next())
            {
                a[i] = rs.getString(1);
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
    public boolean ModificarTipoDesitio(String idUsuario, int idTipoSitio)
    {
        try {
            //Se abre la conexion con la bd.
            Connection con = conexion.abrirConexion();
            //Permite crear consultas
            Statement s = con.createStatement();
            int registroUsuarios = s.executeUpdate("insert into prefiere(Usuario_idUsuario, TipoSitio_idTipoSitio) values ("+ idUsuario + ", "  + idTipoSitio + ");");
           
            conexion.cerrarConexion(con);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al intentar abrir la base de datos.");
            return false;
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Error al intentar conectar con el servidor.");
            return false;
        }
    }
    public boolean BorrarTipoDesitio(String idUsuario, int idTipoSitio)
    {
        try {
            //Se abre la conexion con la bd.
            Connection con = conexion.abrirConexion();
            //Permite crear consultas
            Statement s = con.createStatement();
            //int registroUsuarios = "DELETE FROM `prefiere` WHERE `prefiere`.`Usuario_idUsuario` = "+ idUsuario +" AND `prefiere`.`TipoSitio_idTipoSitio` = "+ idTipoSitio+"";  
            int registro =s.executeUpdate("delete from prefiere where prefiere.Usuario_idUsuario="+idUsuario+" and prefiere.TipoSitio_idTipoSitio="+idTipoSitio+";");  
            conexion.cerrarConexion(con);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al intentar abrir la base de datos.");
            return false;
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Error al intentar conectar con el servidor.");
            return false;
        }
    }
}
