package modelo.administrador;

import java.io.FileInputStream;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;

public class modeloTransporte {
  private int transporteId;
    private String transporteTipo;
    private String transporteImagen;
    private Conexion conexion = new Conexion();
    
    public int ConsultarIdTransporte() {
        int id=0;
        ResultSet rs;
        try {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            rs = s.executeQuery("SELECT MAX(idTransporte) FROM transporte"); 
            while (rs.next())
            {
                id=rs.getInt (1);
            }
            conexion.cerrarConexion(con);
                        
        }catch (SQLException e){
            return 0;
        }
        return id;
    }
    
    public boolean transporteInsertar(String Tipo, String Foto) {            
        try {
            Connection con = conexion.abrirConexion();
            FileInputStream archivofoto;
            //Para ejecutar la consulta
            Statement s = con.createStatement();
            
            //Update en la tabla destino
            int registro = s.executeUpdate("INSERT INTO transporte (tipo, foto) VALUES ('"+Tipo+"', '"+Foto+"');");
            
            conexion.cerrarConexion(con);
            return true;
            
        }catch (SQLException e){
            return false;
        }
    }     
    
    public boolean transporteActualizar(int transporteId, String transporteTipo, String transporteImagen) {                                           
        try {
            Connection con = conexion.abrirConexion();
            
            //Para ejecutar la consulta
            Statement s = con.createStatement();
            
            //Update en la tabla usuarios
            int registro = s.executeUpdate(
                "update transporte set tipo = '" 
                    + transporteTipo + "', foto = '" + transporteImagen
                                + "'where idTransporte = " + transporteId + ";");
                    
            conexion.cerrarConexion(con);
            return true;
            
        }catch (SQLException e){
            return false;
        }
    }     
    
    public boolean transporteEliminar(int transporteId, String transporteTipo, String transporteImagen) {                                         
        try {
            Connection con = conexion.abrirConexion();
            
            //Para ejecutar la consulta
            Statement s = con.createStatement();
            
            //Update en la tabla usuarios
            int registro = s.executeUpdate(
            //BORRA EN LA TABLA DE USUARIOS
                "delete from transporte where idTransporte = " + transporteId + ";");
            
            int limpiar = s.executeUpdate("ALTER TABLE transporte auto_increment = 1;");
            
            conexion.cerrarConexion(con);
            return true;
            
        }catch (SQLException e){
            return false;
        }
    }    
    
    public DefaultTableModel transporteConsultar(){
        try{
            //PARA ABRIR A LA BASE DE DATOS
            Connection con = conexion.abrirConexion();
            //PARA GENERAR CONSULTAS
            Statement s = con.createStatement();
            //PARA ESTABLECER EL MODELO AL JTABLE
            DefaultTableModel modelo;
            
            try{
                //EJECUTAR LA CONSULTA
                ResultSet rs = s.executeQuery("select idTransporte as ID, tipo as Tipo, foto as Foto from transporte;");
                //PARA ESTABLECER EL MODELO AL JTABLE
                modelo = new DefaultTableModel();
                //OBTENIENDO LA INFORMACION DE LAS COLUMNAS
                //QUE ESTAN SIENDO CONSULTADAS
                ResultSetMetaData rsMd = rs.getMetaData();
                //LA CANTIDAD DE COLUMNAS QUE TIENE LA CONSULTA
                int cantidadColumnas = rsMd.getColumnCount();
                //ESTABLECER COMO CABECERAS EL NOMBRE EL NOMBRE DE LAS COLUMNAS
                for(int i=1; i<=cantidadColumnas; i++){
                    modelo.addColumn(rsMd.getColumnLabel(i));
                }
                //CREANDO LAS FILAS PARA LA TABLE
                while (rs.next()){
                    Object[]fila=new Object[cantidadColumnas];
                    for(int i = 0; i<cantidadColumnas; i++){
                        fila[i]=rs.getObject(i+1);
                    }
                    modelo.addRow(fila);
                }
                return modelo;
            }finally{
            conexion.cerrarConexion(con);
        }
        } catch (SQLException e) {
        return null;
    }
}
    
    // Consulta los datos que se estan ingresando buscando una coincidencia en la tabla de la BD 
    public DefaultTableModel Buscador(String buscar) 
    {   try 
        {   Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            DefaultTableModel modelo = new DefaultTableModel();
            try
            {   ResultSet rs = s.executeQuery("select idTransporte as ID, tipo as Tipo, foto as Foto from transporte WHERE "
                + "tipo LIKE '%"+buscar+"%'"); 
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