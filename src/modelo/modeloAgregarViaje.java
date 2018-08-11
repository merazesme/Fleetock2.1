/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ITZEL
 */
public class modeloAgregarViaje {
    Conexion conexion = new Conexion();
    //Datos del detino
    public String datosDestinos(String id)
    {
        ResultSet sql;       
         try {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            sql = s.executeQuery("SELECT `nombre` from destino "
                    + "where idDestino = " + id + ";");
            //declaración del array
            String a;
            //copiar del resultset al array
            sql.next();
            
            a = sql.getString(1);
            
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
    public String[][] datosActividades(String where)
    {
        ResultSet sql;       
         try {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            sql = s.executeQuery("SELECT actividad.idActividad, actividad.nombre, tiene.foto FROM actividad " +
                    "INNER JOIN tiene ON actividad.idActividad = tiene.Actividad_idActividad " +
                    "INNER JOIN posee ON posee.tiene_idTiene = tiene.idTiene " +
                    where);
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
            return null;
        }
        catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Error al intentar conectar con el servidor.");
            return null;
        }
    }
    
    //Insertar un viaje
    public boolean insertarViaje(String nombre, String finicio, String ffin, String estado, String idEstilo, String idUsuario, String idDestino, List<String> actSelec){
        try{
            //Se abre la conexion con la bd.
            Connection con = conexion.abrirConexion();
            //Permite crear consultas
            Statement s = con.createStatement();
            int idViaje=0;
            //Inserta un registro en la tabla Viaje.            
            PreparedStatement p = con.prepareStatement("INSERT INTO `viaje`(`nombre`, `fecha_inicio`, `fecha_fin`, "
                            + "`estadoDelViaje`, `Usuario_idUsuario`, `EstiloViaje_idEstiloViaje`) VALUES "
                            + "('"+nombre+"', '"+finicio+"', '"+ffin+"','"+estado+"',"+idUsuario+","+idEstilo+")", PreparedStatement.RETURN_GENERATED_KEYS);
            p.executeUpdate();
            ResultSet gK = p.getGeneratedKeys();
            if (gK.next())
                idViaje = gK.getInt(1);
            //Inserta un registro en la tabla pertenece.
            int registroPertenece = s.executeUpdate("INSERT INTO `pertenece`(`Viaje_idViaje`, `Destino_idDestino`) "
                    + "VALUES ("+idViaje+","+idDestino+")");
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //Inserta un registro en la tabla contiene.
            for(int i=0; i<actSelec.size(); i++){
                int registroContiene = s.executeUpdate("INSERT INTO `contiene`(`Viaje_idViaje`, `Actividad_idActividad`, `destino_idDestino`) "
                        + "VALUES ("+idViaje+","+actSelec.get(i)+", "+idDestino+")");
            }
                        
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
