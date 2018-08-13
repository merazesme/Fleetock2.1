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
public class modeloEditarPerfil {
    Conexion conexion = new Conexion();
    //Datos del usuario
    public String[] jalarDatosUsuario(String idU){   
        try
        {
            //abrir conexión
            Connection con= conexion.abrirConexion(); 
            //generar consultas
            Statement s = con.createStatement(); 
            //consulta
            ResultSet rs = s.executeQuery("select usuario.nombre, usuario.apellidos, usuario.fechaNac, usuario.edad, " +
                    "usuario.sexo, usuario.email, usuario.descripcion, usuario.foto, login.contraseña " +
                    "from usuario " +
                    "INNER JOIN login ON login.Usuario_idUsuario=usuario.idUsuario " +
                    "where usuario.idUsuario = "+idU+";");
            
            //declaración del array
            String [] a = new String [9];
            //contador para copiar del resultset al array
            int i = 0;
            //copiar del resultset al array
            while (rs.next())
            {
                a[0] = rs.getString(1);
                a[1] = rs.getString(2);
                a[2] = rs.getString(3);
                a[3] = rs.getString(4);
                a[4] = rs.getString(5);
                a[5] = rs.getString(6);
                a[6] = rs.getString(7);
                a[7] = rs.getString(8);
                a[8] = rs.getString(9);
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
    
    //Fecha
    public int jalarFecha(int opcion, String idU)
    {   
        try
        {
            //abrir conexión
            Connection con= conexion.abrirConexion(); 
            //generar consultas
            Statement s = con.createStatement(); 
            //consulta
            String fechaS=null;
            switch(opcion)
            {
                case 1:
                    fechaS = "year(fechaNac)";
                    break;
                case 2:
                    fechaS = "month(fechaNac)";
                    break;
                case 3:
                    fechaS = "day(fechaNac)";
                    break;
            }
               
            ResultSet rs = s.executeQuery("select "+fechaS+" from usuario"
                    + " where usuario.idUsuario = "+idU+";");
            //declaración del array
            int fecha=0;
            //contador para copiar del resultset al array
            int i = 0;
            //copiar del resultset al array
            while (rs.next())
            {
                fecha = rs.getInt(1);
                i++;
            }  
            conexion.cerrarConexion(con); 
            return fecha; 
        }
        catch(SQLException e)
        {
          JOptionPane.showMessageDialog(null, "Error al intentar abrir la base de datos.");
          return 0;    
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Error al intentar conectar con el servidor.");
            return 0;
        }
    }
    
    //Modificar contraseña
    public boolean actualizarContraseña(String id, String contraseña)
    {   try
        {
            Connection con= conexion.abrirConexion(); 
            Statement s= con.createStatement(); 
            int registro =s.executeUpdate("update login set contraseña='"+contraseña+"' where login.Usuario_idUsuario="+id+";"); 
            conexion.cerrarConexion(con); 
            return true; 
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al intentar abrir la base de datos.");
            return false; 
        }
        catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Error al intentar conectar con el servidor.");
            return false;
        }
    }
    
    //Verificar tockname
    public String [] validarTockname(String tockname)
    {
        ResultSet verificarTockname;
        String [] tocknameVerificado= new String[2];
        try {
        Connection con = conexion.abrirConexion();
            
        Statement s = con.createStatement();
        verificarTockname = s.executeQuery("select count(usuario), idLogin from login where usuario = '"+ tockname +"'");
        if(verificarTockname.next()) 
        {
            //Si hay resultados obtengo el valor. 
             tocknameVerificado[0] = verificarTockname.getString(1);
             tocknameVerificado[1] = verificarTockname.getString(2);
        }
        conexion.cerrarConexion(con);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al intentar abrir la base de datos.");
        }
        catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Error al intentar conectar con el servidor.");
        }
        return tocknameVerificado;
    }
    
    //Actualiza los datos del usuario
    public boolean actualizarDatosUsuario(String id, String nombre, String apellidos, String fechaNac, int edad, String sexo, String email, String descripcion, String foto, String user)
    {   try
        {
            Connection con= conexion.abrirConexion(); 
            Statement s= con.createStatement(); 
            int registro =s.executeUpdate("update usuario INNER JOIN login ON login.Usuario_idUsuario = usuario.idUsuario "
                    + "set usuario.nombre='"+nombre+"', usuario.apellidos='"+apellidos+"', usuario.fechaNac='"+fechaNac+"', "
                    + "usuario.edad="+edad+", usuario.sexo='"+sexo+"', usuario.email='"+email+"', "
                    + "usuario.descripcion='"+descripcion+"', usuario.foto='"+foto+"', login.usuario='"+user+"' "
                    + "WHERE usuario.idUsuario="+id+";"); 
            conexion.cerrarConexion(con); 
            return true; 
        }
        catch(SQLException e)
        {
            return false; 
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Error al intentar conectar con el servidor.");
            return false;
        }
    }
}
