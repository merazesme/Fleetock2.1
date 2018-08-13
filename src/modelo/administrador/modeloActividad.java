/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.administrador;
import java.sql.Connection; 
import java.sql.ResultSet; 
import java.sql.ResultSetMetaData;
import java.sql.SQLException; 
import java.sql.Statement; 
import javax.swing.table.DefaultTableModel; 
import modelo.Conexion;
/**
 *
 * @author Fabiola Paez
 */
public class modeloActividad {
    private Conexion conexion= new Conexion(); // Se crea una conexion a la BD 
    
    // Agrega un registro a la tabla activiada a la BD 
    public boolean administradorInsertarA(String aNombre, String aDescripcion)
    {   try
        {   Connection con= conexion.abrirConexion(); 
            Statement s= con.createStatement(); 
            int registro =s.executeUpdate("insert into actividad(nombre, descripcion)values('"+aNombre+"','"+aDescripcion+"');"); 
            conexion.cerrarConexion(con); 
            return true; 
        }
        catch(SQLException e)
        {   return false;    
        }
    }
    
    // Actualiza un registro de la tabla actividad  
    public boolean administradorActualizarA(int aId,String aNombre, String aDescripcion)
    {   try
        {   Connection con= conexion.abrirConexion(); 
            Statement s= con.createStatement(); 
            int registro =s.executeUpdate("update actividad set nombre='"+aNombre+"',descripcion='"+aDescripcion+"'where idActividad="+aId+";"); 
            conexion.cerrarConexion(con); 
            return true; 
        }
        catch(SQLException e)
        {   return false; 
        }
    }
    
    // Elimina un registro de la tabla actividad 
    public boolean administradorEliminarA(int aId)
    {   try
        {   Connection con= conexion.abrirConexion(); 
            Statement s= con.createStatement(); 
            int registro =s.executeUpdate("DELETE FROM actividad WHERE actividad.idActividad = '"+aId+"';");  
            int limpiar = s.executeUpdate("ALTER TABLE actividad auto_increment = 1;");
            conexion.cerrarConexion(con); 
            return true; 
        }
        catch(SQLException e)
        {   return false; 
        }
    }
    
    // Checa que no este en otras tablas el registro que se quiere borrar 
    public int administradorConsultaParaEliminarA(int aId)
    {   try
        {   Connection con= conexion.abrirConexion(); 
            Statement s= con.createStatement(); 
            ResultSet registro =s.executeQuery(
                "SELECT COUNT(*) from actividad inner join contiene on contiene.Actividad_idActividad=actividad.idActividad WHERE actividad.idActividad="+aId+" OR "
                +"(SELECT COUNT(*) from actividad inner join tiene on tiene.Actividad_idActividad=actividad.idActividad WHERE actividad.idActividad="+aId+") OR " 
                +"(SELECT COUNT(*) from actividad inner join posee on posee.Actividad_idActividad=actividad.idActividad WHERE actividad.idActividad="+aId+");");  
            int resultado=0; 
            while(registro.next())
                resultado=registro.getInt("COUNT(*)");
            conexion.cerrarConexion(con); 
            return resultado; 
        }
        catch(SQLException e)
        {   return -1; 
        }
    }
    
    // Carga las actividades guardadas en la BD a la tabla 
    public DefaultTableModel administradorConsultar()
    {   try
        {   // para abrir conexion a la BD 
            Connection con = conexion.abrirConexion(); 
            // para generar consultas 
            Statement s= con.createStatement(); 
            // para establecer el modelo al jtable 
            DefaultTableModel modelo; 
            try
            {   ResultSet rs = s.executeQuery("select idActividad as ID, nombre as Nombre, descripcion as Descripción from actividad;"); 
                // para establecer el modelo al jtable 
                modelo= new DefaultTableModel(); 
                // obteniendo la informacion de las columnas que esta siendo consultadas 
                ResultSetMetaData rsMD = rs.getMetaData(); 
                // la cantidad de conlumnas que tien la consulta
                int cantidadColumnas = rsMD.getColumnCount(); 
                // establecer como cabecera el nombre de las columnas 
                for( int i= 1; i<= cantidadColumnas; i++)
                    modelo.addColumn(rsMD.getColumnLabel(i)); 
                // creando las filas para el jtable 
                while (rs.next())
                {   Object[] fila = new Object[cantidadColumnas]; 
                    for ( int i=0; i< cantidadColumnas; i++)
                        fila[i]= rs.getObject(i+1); 
                    modelo.addRow(fila); 
                }
                return modelo; 
            }
            finally
            {   //cerrar objeto de result 
                conexion.cerrarConexion(con); 
            }
        }
        catch(SQLException e)
        {   return null; 
        }
    }
    
    // Busca las coincidencias de lo que ingrese en el buscador en la tabla de Actividades 
    public DefaultTableModel Buscador(String buscar)
    {   try 
        {   Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            DefaultTableModel modelo = new DefaultTableModel();
            try
            {   ResultSet rs = s.executeQuery("SELECT idActividad as ID, nombre as Nombre, descripcion as Descripción FROM actividad WHERE "
                + "nombre LIKE '%"+buscar+"%'"); 
                ResultSetMetaData rsMd = rs.getMetaData();
                int cantidadColumnas = rsMd.getColumnCount();
                for(int i=1; i<=cantidadColumnas; i++)
                    modelo.addColumn(rsMd.getColumnLabel(i));
                //Creando filas para el jtable
                while (rs.next())
                {   Object[]fila=new Object[cantidadColumnas];
                    for(int i = 0; i<cantidadColumnas; i++)
                        fila[i]=rs.getObject(i+1);
                    modelo.addRow(fila);
                }
                return modelo;
            }
            finally
            {   conexion.cerrarConexion(con);
            }
        } catch (SQLException ex) 
        {   return null; 
        } 
    }
}