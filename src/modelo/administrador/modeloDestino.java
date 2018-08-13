package modelo.administrador;

import java.io.FileInputStream;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;

public class modeloDestino {
    private int destinoId;
    private String destinoNombre;
    private String destinoPais;
    private String destinoImagen;
    private Conexion conexion = new Conexion();
    
    public boolean destinoInsertar(String destinoNombre, String destinoPais, String destinoImagen) {  
        try {
            Connection con = conexion.abrirConexion();
            FileInputStream archivofoto;
            //Para ejecutar la consulta
            Statement s = con.createStatement();
            
            //Update en la tabla destino
            int registro = s.executeUpdate(
                 "insert into destino(nombre, pais, foto)values("
                         + "'"+destinoNombre+"','"+destinoPais+"','"+destinoImagen+"');");
            
            conexion.cerrarConexion(con);
            return true;
            
        }catch (SQLException e){
            return false;
        }
    }
    
    public int ConsultarIdDestino() {
        int id=0;
        ResultSet rs;
        try {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            rs = s.executeQuery("SELECT MAX(idDestino) FROM destino"); 
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
    
    public int ConsultarIdTiene() {
        int id=0;
        ResultSet rs;
        try {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            rs = s.executeQuery("SELECT MAX(idTiene) FROM tiene"); 
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
    
    public int ConsultarIdTipoSitio(String tipo) {
        int id=0;
        ResultSet rs;
        try {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            rs = s.executeQuery("SELECT idTipoSitio FROM tipositio WHERE tipo = '"+tipo+"';"); 
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
    
    public int ConsultarIdActividad(String actividad) {
        int id=0;
        ResultSet rs;
        try {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            rs = s.executeQuery("SELECT idActividad FROM actividad WHERE nombre = '"+actividad+"';"); 
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
    
    public int ConsultarIdEstiloViaje(String estiloviaje) {
        int id=0;
        ResultSet rs;
        try {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            rs = s.executeQuery("SELECT idEstiloViaje FROM estiloviaje WHERE tipo='"+estiloviaje+"';"); 
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
    
    public int ConsultarIdTransportes(String transporte) {
        int id=0;
        ResultSet rs;
        try {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            rs = s.executeQuery("SELECT idTransporte FROM transporte WHERE tipo='"+transporte+"';"); 
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
    
    public String[] ConsultarActividades(){   
       try
        {
            //abrir conexión
            Connection con= conexion.abrirConexion(); 
            //generar consultas
            Statement s = con.createStatement(); 
            //consulta
            ResultSet rs = s.executeQuery("SELECT nombre FROM actividad");
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
    
    public String[] consultarEstilosViaje(){   
       try
        {
            //abrir conexión
            Connection con= conexion.abrirConexion(); 
            //generar consultas
            Statement s = con.createStatement(); 
            //consulta
            ResultSet rs = s.executeQuery("SELECT tipo FROM estiloviaje");
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
    
    public String[] ConsultarTransportes(){   
       try
        {
            //abrir conexión
            Connection con= conexion.abrirConexion(); 
            //generar consultas
            Statement s = con.createStatement(); 
            //consulta
            ResultSet rs = s.executeQuery("SELECT tipo FROM transporte");
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
    
    public boolean InsertarTipoSitio(int tipoSitioId, int destinoId) {            
        try {
            Connection con = conexion.abrirConexion();
            //Para ejecutar la consulta
            Statement s = con.createStatement();
            
            //Update en la tabla tipo sitio
            int registro = s.executeUpdate(
                 "insert into sedivideen (TipoSitio_idTipoSitio, Destino_idDestino)values("
                    + tipoSitioId + ", '" 
                                + destinoId + "');");
            
            conexion.cerrarConexion(con);
            return true;
            
        }catch (SQLException e){
            return false;
        }
    }
    
    public boolean InsertarActividad(int actividadId, int destinoId, String foto, String localizacion, int estiloViajeId, int costo) {   
        PreparedStatement sentencia1 = null;
        PreparedStatement sentencia2 = null;
        int idTiene=0;
        Connection con = null;
        try {
            con = conexion.abrirConexion();
            con.setAutoCommit(false);
            
            sentencia1 = con.prepareStatement("INSERT INTO tiene (Actividad_idActividad, Destino_idDestino, foto, localizacion) "
                        + "VALUES ('"+actividadId+"', '"+destinoId+"', '"+foto+"', '"+localizacion+"');"); 
            
            int s1=sentencia1.executeUpdate(); 
                        
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(tiene.idTiene) FROM tiene"); 
            
            while (rs.next())
            {
                idTiene=rs.getInt (1);
            }
            System.out.println("ID Tiene:"+idTiene);
            if(idTiene==0)
                    throw new SQLException("Error al cargar datos");
            
            sentencia2 = con.prepareStatement("INSERT INTO posee (EstiloViaje_idEstiloViaje, tiene_idTiene, costo) VALUES "
                    + "('"+estiloViajeId+"', '"+idTiene+"', '"+costo+"');");
            
            int s2=sentencia2.executeUpdate(); 
            if(s1==0 || s2==0)
                    throw new SQLException("Datos ingresados invalidos");
           
            con.commit();
            conexion.cerrarConexion(con);
            return true;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false; 
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Error al intentar conectar con el servidor.");
            return false;
        }
    }
    
    public boolean InsertarTransportes(int destinoId, int transporteId, int estiloViajeId, int costo) {   
        PreparedStatement sentencia1 = null;
        PreparedStatement sentencia2 = null;
        int idSeDesplazaEn=0;
        Connection con = null;
        try {
            con = conexion.abrirConexion();
            con.setAutoCommit(false);
            
            sentencia1 = con.prepareStatement("INSERT INTO sedesplazaen (Destino_idDestino, Transporte_idTransporte)VALUES("
                    + destinoId + ", '" 
                                + transporteId + "');"); 
            
            int s1=sentencia1.executeUpdate(); 
            
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(sedesplazaen.idSeDesplazaEn) FROM sedesplazaen"); 
            while (rs.next())
            {
                idSeDesplazaEn=rs.getInt (1);
            }
            System.out.println("ID Tiene:"+idSeDesplazaEn);
            if(idSeDesplazaEn==0)
                    throw new SQLException("Error al cargar datos");
            
            sentencia2 = con.prepareStatement("INSERT INTO corresponde (EstiloViaje_idEstiloViaje, sedesplazaen_idSeDesplazaEn, costo) VALUES "
                    + "('"+estiloViajeId+"', '"+idSeDesplazaEn+"', '"+costo+"');");
            
            int s2=sentencia2.executeUpdate(); 
            if(s1==0 || s2==0)
                    throw new SQLException("Datos ingresados invalidos");
           
            con.commit();
            conexion.cerrarConexion(con);
            return true;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false; 
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Error al intentar conectar con el servidor.");
            return false;
        }
    }
    
    public boolean destinoActualizar(int destinoId, String destinoNombre, String destinoPais, String destinoImagen) {                                           
        try {
            Connection con = conexion.abrirConexion();
            
            //Para ejecutar la consulta
            Statement s = con.createStatement();
            
            //Update en la tabla destino
            int registro = s.executeUpdate(
                "update destino set nombre = '" 
                    + destinoNombre + "', pais = '" + destinoPais
                            + "', foto = '" + destinoImagen
                                + "'where idDestino = " + destinoId + ";");
                    
            conexion.cerrarConexion(con);
            return true;
            
        }catch (SQLException e){
            return false;
        }
    }     
    
    public boolean destinoEliminar(int destinoId, String destinoNombre, String destinoPais, String destinoImagen) {                                         
        try {
            Connection con = conexion.abrirConexion();
            
            //Para ejecutar la consulta
            Statement s = con.createStatement();
            
            //Update en la tabla destino
            int registro = s.executeUpdate(
            //BORRA EN LA TABLA DE DESTINO
                "delete from destino where idDestino = " + destinoId + ";");
            
            int limpiar = s.executeUpdate("ALTER TABLE destino auto_increment = 1;");
            
            conexion.cerrarConexion(con);
            return true;
            
        }catch (SQLException e){
            return false;
        }
    }
    
    public boolean TipoSitioEliminar(int idTipoSitio, int idDestino) {                                         
        try {
            Connection con = conexion.abrirConexion();
            
            //Para ejecutar la consulta
            Statement s = con.createStatement();
            
            //Update en la tabla destino
            int registro = s.executeUpdate("DELETE FROM sedivideen WHERE sedivideen.TipoSitio_idTipoSitio = '"+idTipoSitio+"' "
                                                + "AND sedivideen.Destino_idDestino = '"+idDestino+"'");
            
            conexion.cerrarConexion(con);
            return true;
            
        }catch (SQLException e){
            return false;
        }
    }
    
    public boolean ActividadesEliminar(int idEstiloViaje, int idTiene) {   
        PreparedStatement sentencia1 = null;
        PreparedStatement sentencia2 = null;
        Connection con = null;
        try {
            con = conexion.abrirConexion();
            con.setAutoCommit(false);
            
            sentencia1 = con.prepareStatement("DELETE FROM posee "
                    + "WHERE posee.EstiloViaje_idEstiloViaje = '"+idEstiloViaje+"' "
                    + "AND posee.tiene_idTiene = '"+idTiene+"';"); 
            
            sentencia2 = con.prepareStatement("DELETE FROM tiene WHERE tiene.idTiene = '"+idTiene+"';");
            
            Statement s= con.createStatement(); 
            int limpiar = s.executeUpdate("ALTER TABLE tiene auto_increment = 1;");
            
            int s1=sentencia1.executeUpdate(); 
            int s2=sentencia2.executeUpdate(); 
            if(s1==0 || s2==0)
                    throw new SQLException("Datos ingresados invalidos");
           
            con.commit();
            conexion.cerrarConexion(con);
            return true;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false; 
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Error al intentar conectar con el servidor.");
            return false;
        }
    }
    
    public boolean TransportesEliminar(int idEstiloViaje, int idSeDesplazaEn) {   
        PreparedStatement sentencia1 = null;
        PreparedStatement sentencia2 = null;
        Connection con = null;
        try {
            con = conexion.abrirConexion();
            con.setAutoCommit(false);
            
            sentencia1 = con.prepareStatement("DELETE FROM corresponde "
                    + "WHERE corresponde.sedesplazaen_idSeDesplazaEn = '"+idSeDesplazaEn+"';"); 
            
            sentencia2 = con.prepareStatement("DELETE FROM sedesplazaen "
                    + "WHERE sedesplazaen.idSeDesplazaEn = '"+idSeDesplazaEn+"';"); 
            
            Statement s= con.createStatement(); 
            int limpiar = s.executeUpdate("ALTER TABLE sedesplazaen auto_increment = 1;");
            
            int s1=sentencia1.executeUpdate(); 
            int s2=sentencia2.executeUpdate(); 
            if(s1==0 || s2==0)
                    throw new SQLException("Datos ingresados invalidos");
           
            con.commit();
            conexion.cerrarConexion(con);
            return true;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false; 
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Error al intentar conectar con el servidor.");
            return false;
        }
    }
        
    public DefaultTableModel consultarDestinoTipoSitio(int idDestino){
        try{
            //PARA ABRIR A LA BASE DE DATOS
            Connection con = conexion.abrirConexion();
            //PARA GENERAR CONSULTAS
            Statement s = con.createStatement();
            //PARA ESTABLECER EL MODELO AL JTABLE
            DefaultTableModel modelo;
            
            try{
                //EJECUTAR LA CONSULTA
                ResultSet rs = s.executeQuery("SELECT tipositio.tipo AS 'Tipo Sitio' "
                        + "FROM destino "
                        + "INNER JOIN sedivideen ON destino.idDestino = sedivideen.Destino_idDestino "
                        + "INNER JOIN tipositio ON tipositio.idTipoSitio = sedivideen.TipoSitio_idTipoSitio "
                        + "WHERE destino.idDestino='"+idDestino+"'");
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
    
    public DefaultTableModel consultarDestinoActividades(int idDestino){
        try{
            //PARA ABRIR A LA BASE DE DATOS
            Connection con = conexion.abrirConexion();
            //PARA GENERAR CONSULTAS
            Statement s = con.createStatement();
            //PARA ESTABLECER EL MODELO AL JTABLE
            DefaultTableModel modelo;
            
            try{
                //EJECUTAR LA CONSULTA
                ResultSet rs = s.executeQuery("SELECT actividad.nombre AS 'Actividad', estiloviaje.tipo AS 'Estilo Viaje', "
                        + "tiene.localizacion AS 'Localización', tiene.foto AS 'Foto', tiene.idTiene, posee.EstiloViaje_idEstiloViaje, posee.costo "
                        + "FROM destino "
                        + "INNER JOIN tiene ON tiene.Destino_idDestino=destino.idDestino "
                        + "INNER JOIN actividad ON tiene.Actividad_idActividad=actividad.idActividad "
                        + "INNER JOIN posee ON posee.tiene_idTiene=tiene.idTiene "
                        + "INNER JOIN estiloviaje ON estiloviaje.idEstiloViaje=posee.EstiloViaje_idEstiloViaje "
                        + "WHERE destino.idDestino='"+idDestino+"';");
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
    
    public DefaultTableModel consultarDestinoTransportes(int idDestino){
        try{
            //PARA ABRIR A LA BASE DE DATOS
            Connection con = conexion.abrirConexion();
            //PARA GENERAR CONSULTAS
            Statement s = con.createStatement();
            //PARA ESTABLECER EL MODELO AL JTABLE
            DefaultTableModel modelo;
            
            try{
                //EJECUTAR LA CONSULTA
                ResultSet rs = s.executeQuery("SELECT transporte.tipo AS 'Transporte', estiloviaje.tipo AS 'Estilo Viaje', "
                        + "corresponde.costo AS 'Costo', sedesplazaen.idSeDesplazaEn FROM destino "
                        + "INNER JOIN sedesplazaen ON sedesplazaen.Destino_idDestino=destino.idDestino "
                        + "INNER JOIN transporte ON transporte.idTransporte=sedesplazaen.Transporte_idTransporte "
                        + "INNER JOIN corresponde ON corresponde.sedesplazaen_idSeDesplazaEn=sedesplazaen.idSeDesplazaEn "
                        + "INNER JOIN estiloviaje ON estiloviaje.idEstiloViaje=corresponde.EstiloViaje_idEstiloViaje "
                        + "WHERE destino.idDestino='"+idDestino+"';");
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
    
    public String[] consultarTipoSitio(){   
       try
        {
            //abrir conexión
            Connection con= conexion.abrirConexion(); 
            //generar consultas
            Statement s = con.createStatement(); 
            //consulta
            ResultSet rs = s.executeQuery("SELECT tipo FROM tipositio");
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
    
    public DefaultTableModel consultarDestino(){
        try{
            //PARA ABRIR A LA BASE DE DATOS
            Connection con = conexion.abrirConexion();
            //PARA GENERAR CONSULTAS
            Statement s = con.createStatement();
            //PARA ESTABLECER EL MODELO AL JTABLE
            DefaultTableModel modelo;
            
            try{
                //EJECUTAR LA CONSULTA
                ResultSet rs = s.executeQuery("SELECT idDestino, nombre AS Nombre, pais AS País, foto AS 'Foto' FROM destino;");
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
    public DefaultTableModel BuscadorDestino(String buscar) 
    {   try 
        {   Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            DefaultTableModel modelo = new DefaultTableModel();
            try
            {   ResultSet rs = s.executeQuery(" "
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
    
    public DefaultTableModel BuscadorTipoSitio(int idDestino, String buscar) 
    {   try 
        {   Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            DefaultTableModel modelo = new DefaultTableModel();
            try
            {   ResultSet rs = s.executeQuery("SELECT tipositio.tipo AS 'Tipo Sitio' "
                        + "FROM destino "
                        + "INNER JOIN sedivideen ON destino.idDestino = sedivideen.Destino_idDestino "
                        + "INNER JOIN tipositio ON tipositio.idTipoSitio = sedivideen.TipoSitio_idTipoSitio "
                        + "WHERE destino.idDestino='"+idDestino+"'"
                        + "OR tipo LIKE '%"+buscar+"%'"); 
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