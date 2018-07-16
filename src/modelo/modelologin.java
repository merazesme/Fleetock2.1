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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author alfredo
 */
public class modelologin {
        private Conexion conexion = new Conexion();
    //En esta funcion se hace la consulta donde tenemos dos parametros donde lo usaremos para que el usuario escriba 
    //su usuario y contraseña
    public int ingresar(String usu, String contra)
    {
        String capturar="";
        int control=0;
        ResultSet sql;       
         try {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            //En este variable se guarda la consulta y que es de tipo result set
            sql = s.executeQuery("SELECT * FROM login WHERE usuario='" + usu + "' && contraseña='" + contra + "' ");
            //este se usa para recorrer toda la consulta
            while(sql.next())
            {
                //Lo adquirido de la consukta se pasa a una variable de tipo string llamada captura
                capturar = sql.getString("tipo");
            }
            //Se compara el tipo de usuario
            if(capturar.equals("Usuario"))
            {
                //Se retorna un 1 si captura es Administrador
                control = 1;
            }
            if(capturar.equals("Administrador"))
            {
                //Se retorna un 2 si captura es Usuario
                control = 2;
            }
            if((!capturar.equals("Administrador")) && (!capturar.equals("Usuario")))
            {
                //Se retorna un 3 si no es nignuno de los dos
                control = 3;
            }
           conexion.cerrarConexion(con);
        }
        catch (SQLException ex)
        {
            //AQUI COMENTE LO DE REGISTRAR
           // Logger.getLogger(modeloRegistrar.class.getName()).log(Level.SEVERE, null, ex);
        }
         catch(NullPointerException e){
             //Arroja un mensaje 
            JOptionPane.showMessageDialog(null, "Error al intentar conectar con el servidor.");
        }
         return control;
    }
}
