package control.administrador;

import java.io.File;
import static com.sun.javafx.tk.ImageLoader.*;
import control.CambiaPanel;
import java.awt.Image;
import modelo.administrador.modeloDestino;
import vistas.administrador.avistaDestino;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;

import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.Conexion;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import vistas.administrador.avistaDestinoActividades;
import vistas.administrador.avistaMenu;
import vistas.administrador.avistaDestinoTipoSitio;
import vistas.administrador.avistaDestinoTransportes;

public class acontrolDestinos implements ActionListener, MouseListener, KeyListener{
     private modeloDestino modelo;
     private avistaDestino vista;
     private avistaMenu menu;
     private avistaDestinoTipoSitio vistaTipoSitio;
     private avistaDestinoActividades vistaActividades;
     private avistaDestinoTransportes vistaTransportes;
     int idDestino=0;
     String Foto="";
     //Se crea una carpeta en el Disco C
     private String actividad = "C:/xampp/htdocs/Fleetock/Imagenes/Actividad/";
     private String destino = "C:/xampp/htdocs/Fleetock/Imagenes/Destinos/";
     private JFileChooser archivo = new JFileChooser();
     int ventana =0;
     String archi="";
     public int p=0;
     int bandera=0;
     int idTipoSitio=0;
     int idActividad=0;
     int idEstiloViaje=0;
     int idTransporte=0;
     int idTiene=0;
     int idSeDesplazaEn=0;
     boolean continuar=false;
     String nombreDestino="";
    
     public acontrolDestinos(modeloDestino modelo, avistaDestino vista, avistaMenu menu, avistaDestinoTipoSitio vistaTipoSitio, avistaDestinoActividades vistaActividades, avistaDestinoTransportes vistaTransportes){
         this.modelo = modelo;
         this.vista = vista;
         this.menu = menu;
         this.vistaTipoSitio = vistaTipoSitio;
         this.vistaActividades = vistaActividades;
         this.vistaTransportes = vistaTransportes;
         this.vista.btn_Insertar.addActionListener(this);
         this.vista.btn_Eliminar.addActionListener(this);
         this.vista.btn_Actualizar.addActionListener(this);
         this.vista.tbl_Destino.addMouseListener(this);
         this.vista.btn_Seleccionar.addActionListener(this);
         this.vista.btn_Cancelar.addActionListener(this);
         this.vista.btnGenerarReporte.addActionListener(this);
         this.vista.btn_AsignarDatos.addActionListener(this);
         this.vista.txt_Buscar.addKeyListener(this);
     
         //Vista Tipo Sitio
         this.vistaTipoSitio.btn_AgregarTipoSitio.addActionListener(this);
         this.vistaTipoSitio.tbl_TipoSitio.addMouseListener(this);
         this.vistaTipoSitio.btn_Finalizar.addActionListener(this);
         this.vistaTipoSitio.jcb_TipoSitio.addActionListener(this);
         this.vistaTipoSitio.btn_Cancelar.addActionListener(this);
         this.vistaTipoSitio.btn_Eliminar.addActionListener(this);
         this.vistaTipoSitio.txt_Buscar.addKeyListener(this);
         
         //Vista Actividades
         this.vistaActividades.btn_AgregarActividades.addActionListener(this);
         this.vistaActividades.tbl_Actividad.addMouseListener(this);
         this.vistaActividades.jcb_EstiloViaje.addActionListener(this);
         this.vistaActividades.jcb_Actividad.addActionListener(this);
         this.vistaActividades.btn_Eliminar.addActionListener(this);
         this.vistaActividades.txt_costo.addActionListener(this);
         this.vistaActividades.btn_Cancelar.addActionListener(this);
         this.vistaActividades.btn_Finalizar.addActionListener(this);
         this.vistaActividades.btn_Seleccionar.addActionListener(this);
         this.vistaActividades.txt_Buscar.addKeyListener(this);
         
         //Vista Transportes
         this.vistaTransportes.btn_AgregarTransportes.addActionListener(this);
         this.vistaTransportes.tbl_Transportes.addMouseListener(this);
         this.vistaTransportes.jcb_EstiloViaje.addActionListener(this);
         this.vistaTransportes.jcb_Transportes.addActionListener(this);
         this.vistaTransportes.txt_Costo.addActionListener(this);
         this.vistaTransportes.btn_Eliminar.addActionListener(this);
         this.vistaTransportes.btn_Cancelar.addActionListener(this);
         this.vistaTransportes.btn_Finalizar.addActionListener(this);
         this.vistaTipoSitio.txt_Buscar.addKeyListener(this);
         
         desabilitarVista();
     }
     
    //Oculta tabla Destino id y foto
    public void ocultaColumnaTablaDestino()
    {
        this.vista.tbl_Destino.getColumnModel().getColumn(0).setMaxWidth(0);
        this.vista.tbl_Destino.getColumnModel().getColumn(0).setMinWidth(0);
        this.vista.tbl_Destino.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        this.vista.tbl_Destino.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        this.vista.tbl_Destino.getColumnModel().getColumn(0).setPreferredWidth(0);
    }
     
    public void iniciarVistaDestino(){
        LimpiarVistaDestino();
        idDestino=0;
        vista.setVisible(true);
        desabilitarVista();
    }
     
    public void iniciarVistaTipoSitio(){
        CambiaPanel cambiar = new CambiaPanel(menu.panelCambiante, vistaTipoSitio);
        vistaTipoSitio.setVisible(true);
        vistaTipoSitio.btn_Eliminar.setEnabled(false);
        vistaTipoSitio.btnActividades.setText(nombreDestino);
        LimpiarVistaTipoSitio();
        //ocultaColumnaTablaDestino();
        if(modelo.consultarTipoSitio().length > 0)
        {    
           String [] TipoSitio = modelo.consultarTipoSitio();
           for(int i=0; i<TipoSitio.length; i++)
           {
               vistaTipoSitio.jcb_TipoSitio.addItem(TipoSitio[i]);
           }
        }
        this.vistaTipoSitio.jcb_TipoSitio.setSelectedItem(null);
    }
    
    public void iniciarVistaActividades(){
        CambiaPanel cambiar = new CambiaPanel(menu.panelCambiante, vistaActividades);
        vistaActividades.setVisible(true);
        vistaActividades.btn_Eliminar.setEnabled(false);
        vistaActividades.nombreDestino.setText(nombreDestino);
        LimpiarVistaActividades();
        //Consultar Actividades
        if(modelo.ConsultarActividades().length > 0)
        {    
           String [] Actividades = modelo.ConsultarActividades();
           for(int i=0; i<Actividades.length; i++)
           {
               vistaActividades.jcb_Actividad.addItem(Actividades[i]);
           }
        }
        this.vistaActividades.jcb_Actividad.setSelectedItem(null);
        //Consultar Estilos de Viaje
        if(modelo.consultarEstilosViaje().length > 0)
        {    
           String [] EstiloViaje = modelo.consultarEstilosViaje();
           for(int i=0; i<EstiloViaje.length; i++)
           {
               vistaActividades.jcb_EstiloViaje.addItem(EstiloViaje[i]);
           }
        }
        this.vistaActividades.jcb_EstiloViaje.setSelectedItem(null);
    }
    
    public void iniciarVistaTransportes(){
        CambiaPanel cambiar = new CambiaPanel(menu.panelCambiante, vistaTransportes);
        vistaTransportes.setVisible(true);
        vistaTransportes.nombreDestino.setText(nombreDestino);
        LimpiarVistaTransportes();
        //Consultar Transportes
        if(modelo.ConsultarTransportes().length > 0)
        {    
           String [] Transportes = modelo.ConsultarTransportes();
           for(int i=0; i<Transportes.length; i++)
           {
               vistaTransportes.jcb_Transportes.addItem(Transportes[i]);
           }
        }
        vistaTransportes.jcb_Transportes.setSelectedItem(null);
        //Consultar Estilos de Viaje
        if(modelo.consultarEstilosViaje().length > 0)
        {    
           String [] EstiloViaje = modelo.consultarEstilosViaje();
           for(int i=0; i<EstiloViaje.length; i++)
           {
               vistaTransportes.jcb_EstiloViaje.addItem(EstiloViaje[i]);
           }
        }
        this.vistaTransportes.jcb_EstiloViaje.setSelectedItem(null);
    }

     //Limpia los JTextField
     public void LimpiarVistaDestino(){
        vista.tbl_Destino.setModel(modelo.consultarDestino());
        ocultaColumnaTablaDestino();
        Foto="";
        vista.txt_Buscar.setText("");
        vista.txt_Nombre.setText("");
        vista.txt_Pais.setText("");
        vista.lbl_Foto.setIcon(null);
        vista.lbl_Foto.removeAll();
        vista.lbl_Foto.revalidate();
        vista.lbl_Foto.repaint();
        vista.lbl_Foto.setIcon(new ImageIcon("C:/xampp/htdocs/Fleetock/Imagenes/fondo.jpg"));
        vista.btn_Insertar.setEnabled(true);
        desabilitarVista();
     }
     
     public void LimpiarVistaTipoSitio(){
         this.vistaTipoSitio.btn_AgregarTipoSitio.setEnabled(true);
         this.vistaTipoSitio.btn_Eliminar.setEnabled(false);
         vistaTipoSitio.tbl_TipoSitio.setModel(modelo.consultarDestinoTipoSitio(idDestino));
         vistaTipoSitio.jcb_TipoSitio.setSelectedItem(null);
     }
     
     public void LimpiarVistaActividades(){
        idActividad=0;
        Foto="";
        idEstiloViaje=0;
        idTiene=0;
        vistaActividades.btn_Eliminar.setEnabled(false);
        vistaActividades.btn_AgregarActividades.setEnabled(true);
        vistaActividades.txt_Localizacion.setText("");
        vistaActividades.txt_costo.setText("");
        vistaActividades.lbl_Foto.setIcon(null);
        vistaActividades.lbl_Foto.removeAll();
        vistaActividades.lbl_Foto.revalidate();
        vistaActividades.lbl_Foto.repaint();
        vistaActividades.lbl_Foto.setIcon(new ImageIcon("C:/xampp/htdocs/Fleetock/Imagenes/fondo.jpg"));
        vistaActividades.jcb_Actividad.setSelectedItem(null);
        vistaActividades.jcb_EstiloViaje.setSelectedItem(null);
        vistaActividades.tbl_Actividad.setModel(modelo.consultarDestinoActividades(idDestino));
        vistaActividades.lbl_Foto.setEnabled(true);
        vistaActividades.btn_Seleccionar.setEnabled(true);
        vistaActividades.btn_AgregarActividades.setEnabled(true);
        vistaActividades.btn_Eliminar.setEnabled(false);
        vistaActividades.txt_Localizacion.setEnabled(true);
        vistaActividades.txt_costo.setEnabled(true);
        vistaActividades.jcb_Actividad.setEnabled(true);
        vistaActividades.jcb_EstiloViaje.setEnabled(true);
    }
     
     public void LimpiarVistaTransportes(){
        idTransporte=0;
        idEstiloViaje=0;
        idSeDesplazaEn=0;
        vistaTransportes.btn_AgregarTransportes.setEnabled(true);
        vistaTransportes.jcb_EstiloViaje.setEnabled(true);
        vistaTransportes.jcb_Transportes.setEnabled(true);
        vistaTransportes.txt_Costo.setEnabled(true);
        vistaTransportes.btn_Eliminar.setEnabled(false);
        vistaTransportes.txt_Costo.setText("");
        vistaTransportes.jcb_Transportes.setSelectedItem(null);
        vistaTransportes.jcb_EstiloViaje.setSelectedItem(null);
        vistaTransportes.tbl_Transportes.setModel(modelo.consultarDestinoTransportes(idDestino));
    }
     
     //Se valida si los campos estas vacios o no
    public String validacionCamposVacios()
    {
        if(vista.txt_Nombre.getText().isEmpty() || vista.txt_Pais.getText().isEmpty() || bandera==0)
            return "Favor de llenar todos los campos"; 
        else return null;
    }
    
    public String validacionCamposVaciosActividad()
    {
        if(vistaActividades.txt_Localizacion.getText().isEmpty() || bandera==0 || vistaActividades.jcb_Actividad.getSelectedItem() == null || vistaActividades.jcb_EstiloViaje.getSelectedItem() == null || vistaActividades.txt_costo.getText().isEmpty())
            return "Favor de llenar todos los campos"; 
        else return null;
    }
    
    public String validacionCamposVaciosTransportes()
    {
        if(vistaTransportes.txt_Costo.getText().isEmpty() || vistaTransportes.jcb_Transportes.getSelectedItem() == null || vistaTransportes.jcb_EstiloViaje.getSelectedItem() == null)
            return "Favor de llenar todos los campos"; 
        else return null;
    }
     
     //Habilita botones
     public void habilitarVista(){
         vista.btn_Actualizar.setEnabled(true);
         vista.btn_Eliminar.setEnabled(true);
         vista.btn_AsignarDatos.setEnabled(true);
     }
     
     //Desabilita botones
     public void desabilitarVista(){
         vista.btn_Actualizar.setEnabled(false);
         vista.btn_Eliminar.setEnabled(false);
         vista.btn_AsignarDatos.setEnabled(false);
     }
     
     //Compara si los campos no estan vacios para habilitar los botones
     public void CamposVacios(){
        if(vista.txt_Nombre.getText()!="" && vista.txt_Pais.getText()!="" && bandera==0)
        {
            desabilitarVista();
        }
        else
        {
             habilitarVista();
        }
     }
     
     @Override
     public void actionPerformed(ActionEvent evento){
        CamposVacios();//Funcion para habilitar 
        if(vista.btn_Insertar == evento.getSource()) {
            if(validacionCamposVacios()==null)
            {
                if(ventana == JFileChooser.APPROVE_OPTION)
                { 
                    //Genera un fichero
                    File fichero= archivo.getSelectedFile();
                    String ruta = fichero.getAbsolutePath();
                    idDestino=modelo.ConsultarIdDestino();
                    idDestino=idDestino+1;
                    System.out.println("ID destino:"+idDestino);
                    String nombre = idDestino+"."+getFileExtension(fichero); 
                    archi = destino + nombre; 
                    File folder = new File(destino); 
                    if(!folder.exists())
                        folder.mkdirs(); 
                    //Genera la ruta donde se guarda la imagen
                    copyFile_Java(ruta,archi); 
                    Foto=archi;
                    idDestino=0;
                }
                //Inserta Destino
                if(modelo.destinoInsertar(vista.txt_Nombre.getText(), vista.txt_Pais.getText(), Foto)==true)
                {
                    idDestino=modelo.ConsultarIdDestino();
                    System.out.println("Destino id:" + idDestino);
                    nombreDestino=vista.txt_Nombre.getText();
                    LimpiarVistaDestino();
                    continuar=true;
                    iniciarVistaTipoSitio();
                }
                else
                    JOptionPane.showMessageDialog(null, "Error al insetar Destino");
            }
         else 
            JOptionPane.showMessageDialog(null, ""+validacionCamposVacios());
        }  
         //Boton Eliminar
         if(vista.btn_Eliminar == evento.getSource()) {
             //Muestra mensaje
             if (JOptionPane.showConfirmDialog(vista,
                    "¿Estás seguro que deseas eliminar este registro?", "Fleetock",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                        if(modelo.destinoEliminar(idDestino, vista.txt_Nombre.getText(), vista.txt_Pais.getText(), Foto)){
                           //Elimina imagen del la carpeta en Disco C
                           File dir = new File(destino); 
                           String[] ficheros = dir.list(); 
                           File archivoel = new File(destino+ficheros[p]);
                           archivoel.delete();
                           JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente");
                           LimpiarVistaDestino();
                           idDestino=0;
                }
                        else {
                            JOptionPane.showMessageDialog(null, "No se pudo eliminar, este registro se encuentra en otras tablas");
                            LimpiarVistaDestino();
                            idDestino=0;
                        } 
            }
         }
         else if(vista.btn_Cancelar == evento.getSource()) {
             LimpiarVistaDestino();
             idDestino=0;
         }
         //Boton Actualizar
         else if(vista.btn_Actualizar == evento.getSource()) {
            if(validacionCamposVacios()==null)
            {
                if(ventana == JFileChooser.APPROVE_OPTION)
                { 
                    File dir = new File(destino); 
                    String[] ficheros = dir.list(); 
                    File archivoel = new File(destino+ficheros[p]);
                    //Compara si existe imagen
                    if(Foto.equals(actividad+ficheros[p])){
                        //Elimina imagen del la carpeta en Disco C
                        archivoel.delete();
                    }
                    //Genera un fichero
                    File fichero= archivo.getSelectedFile();
                    String ruta = fichero.getAbsolutePath();
                    String nombre = idDestino+"."+getFileExtension(fichero); 
                    archi = destino + nombre; 
                    File folder = new File(destino); 
                    if(!folder.exists())
                        folder.mkdirs(); 
                    //Genera la ruta donde se guarda la imagen
                    copyFile_Java(ruta,archi); 
                    Foto=archi;
                }
                if(modelo.destinoActualizar(idDestino, vista.txt_Nombre.getText(), vista.txt_Pais.getText(), Foto)==true){
                    JOptionPane.showMessageDialog(null, "Registro actualizado exitosamente");
                    LimpiarVistaDestino();
                    idDestino=0;
                }
                else  
                    JOptionPane.showMessageDialog(null, "Error al actualizar destino");
            }
            else 
                JOptionPane.showMessageDialog(null, ""+validacionCamposVacios());
         }
         //Boton Seleccionar Imagen desde documentos
         else if(vista.btn_Seleccionar == evento.getSource()) {
            //Inserta y muestra imagen seleccionada
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formato de archivo JPG", "jpg", "jpeg");
            archivo.addChoosableFileFilter(filtro);
            archivo.setDialogTitle("Abrir Archivo");
            ventana = archivo.showOpenDialog(null);
            if(ventana == JFileChooser.APPROVE_OPTION)
            {
                //Limpia Jlabel y reemplaza a nueva imagen seleccionada
                vista.lbl_Foto.removeAll();
                vista.lbl_Foto.revalidate();
                vista.lbl_Foto.repaint();
                File file = archivo.getSelectedFile();
                //System.out.println("extension "+ getFileExtension(file));
                if( !"jpg".equals(getFileExtension(file)) && !"png".equals(getFileExtension(file)))
                {
                    JOptionPane.showMessageDialog(null, "Favor de introducir una extension valida.");
                    Foto=""; 
                }
                else
                {
                    Image foto = vista.getToolkit().getImage(String.valueOf(file));   
                    foto = foto.getScaledInstance(171, 159, Image.SCALE_DEFAULT);
                    vista.lbl_Foto.setIcon(new ImageIcon(foto));
                    habilitarVista();
                    bandera=1;
                }
            }
        }
        else if(vista.btn_AsignarDatos == evento.getSource()) {
            String[] options = {"Tipo de Sitio", "Actividades", "Transportes", "Cancelar"};
            int seleccion = JOptionPane.showOptionDialog(null, "Seleccione el tipo de dato que desea agregar", "Agregar Datos", 
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            switch (seleccion) {
                case 0:
                    iniciarVistaTipoSitio();
                    break;
                case 1:
                    iniciarVistaActividades();
                    break;
                case 2:
                    iniciarVistaTransportes();
                    break;
                default:
                    LimpiarVistaDestino();
                    idDestino=0;
                    break;
             }
        }
        else if(vista.btnGenerarReporte == evento.getSource()) {
            LimpiarVistaDestino();
            desabilitarVista();
            try 
            {
                try 
                {
                    Conexion con = new Conexion();
                    Connection conn = con.abrirConexion();
                    if(con != null) 
                    {
                        JasperReport reporte = null;
                        String path = ("src\\Reportes\\Destinos.jasper");
                        reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
                        JasperPrint jprint = JasperFillManager.fillReport(reporte, null, conn);
                        JasperViewer view = new JasperViewer(jprint, false);
                        view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        view.setVisible(true);
                    }
                } catch (JRException ex) {
                    JOptionPane.showMessageDialog(null, "Error al intentar generar el reporte");
                } 
            }catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error en la conexion");
            } 
        }
        else if(vistaTipoSitio.btn_AgregarTipoSitio == evento.getSource()) {
            if (vistaTipoSitio.jcb_TipoSitio.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Favor de escoger un Tipo de Sitio"); 
            }  
            else {
                idTipoSitio=modelo.ConsultarIdTipoSitio(vistaTipoSitio.jcb_TipoSitio.getSelectedItem().toString());
                System.out.println("ID Tipo Sitio: " + idTipoSitio);
                if(modelo.InsertarTipoSitio(idTipoSitio, idDestino) == false) {
                    JOptionPane.showMessageDialog(null, "YA EXISTE REGISTRO");
                    LimpiarVistaTipoSitio();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Se agrego Tipo de Sitio exitosamente");
                    LimpiarVistaTipoSitio();
                }
            }
        }
        else if(vistaTipoSitio.btn_Eliminar == evento.getSource()) {
            //Compara si la tabla tiene registros
            if(vistaTipoSitio.jcb_TipoSitio.getSelectedItem()==null) {
                    JOptionPane.showMessageDialog(null, "Favor de escoger un Tipo de Sitio"); 
                }
            else {
                idTipoSitio=modelo.ConsultarIdTipoSitio(vistaTipoSitio.jcb_TipoSitio.getSelectedItem().toString());
                System.out.println("ID Tipo Sitio: " + idTipoSitio);
                if(modelo.TipoSitioEliminar(idTipoSitio, idDestino) == false) {
                    JOptionPane.showMessageDialog(null, "NO EXISTE REGISTRO");
                    LimpiarVistaTipoSitio();
                }
                else {
                    if (JOptionPane.showConfirmDialog(vistaTipoSitio,
                    "¿Estás seguro que deseas eliminar este registro?", "Fleetock",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
                    {
                        JOptionPane.showMessageDialog(null, "Se elimino Tipo de Sitio exitosamente");
                        LimpiarVistaTipoSitio();
                    }   
                }  
            } 
        }
        else if(vistaTipoSitio.btn_Cancelar == evento.getSource() 
                || vistaActividades.btn_Cancelar == evento.getSource()  
                    || vistaTransportes.btn_Cancelar == evento.getSource()) {
                        LimpiarVistaTipoSitio();
                        LimpiarVistaActividades();
                        LimpiarVistaTransportes();
        }
        else if(vistaTipoSitio.btn_Finalizar == evento.getSource()) {
            vistaTipoSitio.jcb_TipoSitio.removeAllItems();
            if(continuar==true) {
                LimpiarVistaTipoSitio();
                iniciarVistaActividades();
            }
            else {
                LimpiarVistaTipoSitio();
                CambiaPanel cambiar = new CambiaPanel(menu.panelCambiante, vista);
                iniciarVistaDestino();
            }
        }
        else if(vistaActividades.btn_Finalizar == evento.getSource()) {
            vistaActividades.jcb_Actividad.removeAllItems();
            vistaActividades.jcb_EstiloViaje.removeAllItems();
            if(continuar==true) {
                LimpiarVistaActividades();
                iniciarVistaTransportes();
            }
            else {
                LimpiarVistaActividades();
                CambiaPanel cambiar = new CambiaPanel(menu.panelCambiante, vista);
                iniciarVistaDestino();
            }
        }
        else if(vistaTransportes.btn_Finalizar == evento.getSource()) {
            vistaTransportes.jcb_Transportes.removeAllItems();
            vistaTransportes.jcb_EstiloViaje.removeAllItems();
            if (continuar==true)
                JOptionPane.showMessageDialog(null, "Destino agregado exitosamente");
            LimpiarVistaTransportes();
            continuar=false;
            idDestino=0;
            nombreDestino="";
            CambiaPanel cambiar = new CambiaPanel(menu.panelCambiante, vista);
            iniciarVistaDestino();
        }
        if(vistaActividades.btn_AgregarActividades == evento.getSource()) {
            if(validacionCamposVaciosActividad()==null)
            {
                if(ventana == JFileChooser.APPROVE_OPTION)
                { 
                    //Genera un fichero
                    File fichero = archivo.getSelectedFile();
                    String ruta = fichero.getAbsolutePath(); 
                    idTiene=modelo.ConsultarIdTiene();
                    idTiene=idTiene+1;
                    String nombre = idTiene+"."+getFileExtension(fichero); 
                    archi = destino + nombre; 
                    archi = actividad + ""+ nombre; 
                    File folder = new File(actividad); 
                    if(!folder.exists())
                        folder.mkdirs(); 
                    //Genera la ruta donde se guarda la imagen
                    copyFile_Java(ruta,archi); 
                    Foto=archi;
                }
                //Inserta Atividad
                idActividad=modelo.ConsultarIdActividad(vistaActividades.jcb_Actividad.getSelectedItem().toString());
                System.out.println("ID Actividad:" + idActividad);
                idEstiloViaje=modelo.ConsultarIdEstiloViaje(vistaActividades.jcb_EstiloViaje.getSelectedItem().toString());
                System.out.println("Estilo Viaje:"+vistaActividades.jcb_EstiloViaje.getSelectedItem().toString());
                System.out.println("ID Estilo Viaje: " +idEstiloViaje);
                if(modelo.InsertarActividad(idActividad, idDestino, Foto, vistaActividades.txt_Localizacion.getText(), idEstiloViaje, Integer.parseInt(vistaActividades.txt_costo.getText())))
                {
                    JOptionPane.showMessageDialog(null, "Se agrego Actividad exitosamente");
                    LimpiarVistaActividades();
                }
                else JOptionPane.showMessageDialog(null, "Error al agregar Actividad");
            }
        else 
            JOptionPane.showMessageDialog(null, ""+validacionCamposVaciosActividad());
        }
        else if(vistaActividades.btn_Seleccionar == evento.getSource()) {
           //Inserta y muestra imagen seleccionada
           FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formato de archivo JPG", "jpg", "jpeg");
           archivo.addChoosableFileFilter(filtro);
           archivo.setDialogTitle("Abrir Archivo");
           ventana = archivo.showOpenDialog(null);
           if(ventana == JFileChooser.APPROVE_OPTION)
           {
               //Limpia Jlabel y reemplaza a nueva imagen seleccionada
               vistaActividades.lbl_Foto.removeAll();
               vistaActividades.lbl_Foto.revalidate();
               vistaActividades.lbl_Foto.repaint();
               File file = archivo.getSelectedFile();
               //System.out.println("extension "+ getFileExtension(file));
               if( !"jpg".equals(getFileExtension(file)) && !"png".equals(getFileExtension(file)))
               {
                   JOptionPane.showMessageDialog(null, "Favor de introducir un archivo valido.");
                   Foto=""; 
               }
               else
               {
                   Image foto = vistaActividades.getToolkit().getImage(String.valueOf(file));   
                   foto = foto.getScaledInstance(171, 159, Image.SCALE_DEFAULT);
                   vistaActividades.lbl_Foto.setIcon(new ImageIcon(foto));
                   bandera=1;
               }
           }
        }
        //Boton Eliminar
        else if(vistaActividades.btn_Eliminar == evento.getSource()) {
            if (JOptionPane.showConfirmDialog(vistaActividades,
                    "¿Estás seguro que deseas eliminar este registro?", "Fleetock",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                        if(modelo.ActividadesEliminar(idEstiloViaje, idTiene)==true) {
                            File dir = new File(actividad); 
                            String[] ficheros = dir.list(); 
                            File archivoel = new File(actividad+ficheros[p]);
                            //Elimina imagen del la carpeta en Disco C
                            archivoel.delete();
                            JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente");
                            LimpiarVistaActividades();
                        }
                        else 
                            JOptionPane.showMessageDialog(null, "No se puedo eliminar ");
            }
        }
        else if(vistaTransportes.btn_AgregarTransportes == evento.getSource()) {
            if(validacionCamposVaciosTransportes()==null) {
                //Inserta Transporte
                idTransporte=modelo.ConsultarIdTransportes(vistaTransportes.jcb_Transportes.getSelectedItem().toString());
                System.out.println("ID Transporte:" + idTransporte);
                idEstiloViaje=modelo.ConsultarIdEstiloViaje(vistaTransportes.jcb_EstiloViaje.getSelectedItem().toString());
                System.out.println("Estilo Viaje:"+vistaTransportes.jcb_EstiloViaje.getSelectedItem().toString());
                System.out.println("ID Estilo Viaje: " +idEstiloViaje);
                if(modelo.InsertarTransportes(idDestino, idTransporte, idEstiloViaje, Integer.parseInt(vistaTransportes.txt_Costo.getText()))==true) {
                        JOptionPane.showMessageDialog(null, "Se agrego Transporte exitosamente");
                        LimpiarVistaTransportes();
                }
                else    
                    JOptionPane.showMessageDialog(null, "Error al agregar Transporte");
                }
            else 
                JOptionPane.showMessageDialog(null, "Favor de llenar todos los campos");
        }
        //Boton Eliminar
        else if(vistaTransportes.btn_Eliminar == evento.getSource()) {
            if (JOptionPane.showConfirmDialog(vistaTransportes,
                    "¿Estás seguro que deseas eliminar este registro?", "Fleetock",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                        System.out.println("ID Se desplazaEn");
                        if(modelo.TransportesEliminar(idEstiloViaje, idSeDesplazaEn)==true) {
                            JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente");
                            LimpiarVistaTransportes();
                        }
                        else 
                            JOptionPane.showMessageDialog(null, "No se puedo eliminar");
            }
        }
    }
     
    //Guarda la Imagen
    public static void copyFile_Java(String origen, String destino)
    {
        try 
        {
            CopyOption[]  options = new CopyOption[]
            {
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
            }; 
            Files.copy(Paths.get(origen),Paths.get(destino), options); 
            //JOptionPane.showMessageDialog(null, "Imagen guardada");
        }catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "Error al guardar imagen");
            System.err.println(e.toString());
        }
    }
    
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
     
     @Override
     public void mouseClicked(MouseEvent e){
        if(vista.tbl_Destino == e.getSource()){
            habilitarVista();
            vista.btn_Insertar.setEnabled(false);
            int fila = vista.tbl_Destino.rowAtPoint(e.getPoint());
            if(fila > -1){
                //Guarda los valores de la tabla a los JTextField
                idDestino=(Integer.parseInt(String.valueOf(vista.tbl_Destino.getValueAt(fila, 0))));
                vista.txt_Nombre.setText(String.valueOf(vista.tbl_Destino.getValueAt(fila, 1)));
                nombreDestino=(String.valueOf(vista.tbl_Destino.getValueAt(fila, 1)));
                vista.txt_Pais.setText(String.valueOf(vista.tbl_Destino.getValueAt(fila, 2)));
                Foto=(String.valueOf(vista.tbl_Destino.getValueAt(fila, 3)));
                bandera=0; // Se limpia esta bandera
                }
                File dir = new File(destino); // Se crea un objeto tipo file
                String[] ficheros = dir.list(); // Se crea un arreglo que contiene la lista del fichero 
                for(p=0; p<dir.list().length; p++) // Se busca en el fichero la imagen, con la ruta que se guardo en la BD
                {   
                    if(String.valueOf(vista.tbl_Destino.getValueAt(fila, 3)).equals(destino+ficheros[p]))
                    {   
                        bandera=1;
                        break;
                    }
                }
                if(bandera == 0) // Si no tiene imagen se coloca una imagen que indica que en ese registro no se cuenta con imagen 
                {   vista.lbl_Foto.removeAll();
                    vista.lbl_Foto.revalidate();
                    vista.lbl_Foto.repaint();
                    vista.lbl_Foto.setIcon(new ImageIcon("C:/xampp/htdocs/Fleetock/Imagenes/fondo.jpg"));
                }
                else // Si existe la imagen se carga en el label 
                {   int x = vista.lbl_Foto.getWidth(); 
                    int y = vista.lbl_Foto.getHeight(); 
                    Imagen imagen = new Imagen(x, y, destino+""+ficheros[p]); 
                    vista.lbl_Foto.removeAll();
                    vista.lbl_Foto.revalidate();
                    vista.lbl_Foto.repaint();
                    vista.lbl_Foto.add(imagen); 
                    vista.lbl_Foto.repaint();
                }
       }
        //Tabla Tipo Sitio
        if(vistaTipoSitio.tbl_TipoSitio == e.getSource()){
            int fila = vistaTipoSitio.tbl_TipoSitio.rowAtPoint(e.getPoint());
            if(fila > -1){
                this.vistaTipoSitio.btn_AgregarTipoSitio.setEnabled(false);
                this.vistaTipoSitio.btn_Eliminar.setEnabled(true);
                String nombreTipoSitio=String.valueOf(vistaTipoSitio.tbl_TipoSitio.getValueAt(fila, 0));
                vistaTipoSitio.jcb_TipoSitio.setSelectedItem(nombreTipoSitio);
                idTipoSitio=modelo.ConsultarIdTipoSitio(nombreTipoSitio);
            }
        }
        //Tabla Actividades
        if(vistaActividades.tbl_Actividad == e.getSource()){
            int fila = vistaActividades.tbl_Actividad.rowAtPoint(e.getPoint());
            if(fila > -1){
                vistaActividades.lbl_Foto.setEnabled(false);
                vistaActividades.btn_Seleccionar.setEnabled(false);
                vistaActividades.btn_AgregarActividades.setEnabled(false);
                vistaActividades.btn_Eliminar.setEnabled(true);
                vistaActividades.txt_Localizacion.setEnabled(false);
                vistaActividades.txt_costo.setEnabled(false);
                vistaActividades.jcb_Actividad.setEnabled(false);
                vistaActividades.jcb_EstiloViaje.setEnabled(false);
                vistaActividades.jcb_Actividad.setSelectedItem(String.valueOf(vistaActividades.tbl_Actividad.getValueAt(fila, 0)));
                vistaActividades.jcb_EstiloViaje.setSelectedItem(String.valueOf(vistaActividades.tbl_Actividad.getValueAt(fila, 1)));
                vistaActividades.txt_Localizacion.setText(String.valueOf(vistaActividades.tbl_Actividad.getValueAt(fila, 2)));
                idActividad=modelo.ConsultarIdActividad(vistaActividades.jcb_Actividad.getSelectedItem().toString());
                Foto=String.valueOf(vistaActividades.tbl_Actividad.getValueAt(fila, 3));
                idTiene=(Integer.parseInt(String.valueOf(vistaActividades.tbl_Actividad.getValueAt(fila, 4))));
                idEstiloViaje=(Integer.parseInt(String.valueOf(vistaActividades.tbl_Actividad.getValueAt(fila, 5))));
                vistaActividades.txt_costo.setText(String.valueOf(vistaActividades.tbl_Actividad.getValueAt(fila, 6)));
            }
            //Busca si la imagen existe en el fichero por medio de su ruta y la muestra
            File dir = new File(actividad); 
            String[] ficheros = dir.list(); 
            for(p=0; p<dir.list().length; p++)
            {   
                //Compara si existe imagen
                if(String.valueOf(vistaActividades.tbl_Actividad.getValueAt(fila, 3)).equals(actividad+ficheros[p]))
                break;  
            }
            if(ficheros == null) {
                vistaActividades.lbl_Foto.setIcon(new ImageIcon("C:/xampp/htdocs/Fleetock/Imagenes/fondo.jpg"));
            }
            else {
                //Muestra la imagen seleccionada en el JLabel
                int x = vistaActividades.lbl_Foto.getWidth();
                int y = vistaActividades.lbl_Foto.getHeight();
                Imagen imagen = new Imagen(x, y, actividad+""+ficheros[p]);
                vistaActividades.lbl_Foto.removeAll();
                vistaActividades.lbl_Foto.revalidate();
                vistaActividades.lbl_Foto.repaint();
                vistaActividades.lbl_Foto.add(imagen);
                vistaActividades.lbl_Foto.repaint();
            }
        }
        //Tabla Transportes
        if(vistaTransportes.tbl_Transportes == e.getSource())
        {  
            int fila = vistaTransportes.tbl_Transportes.rowAtPoint(e.getPoint());
            if(fila > -1){
                this.vistaTransportes.btn_Eliminar.setEnabled(true);
                this.vistaTransportes.btn_AgregarTransportes.setEnabled(false);
                vistaTransportes.jcb_EstiloViaje.setEnabled(false);
                vistaTransportes.jcb_Transportes.setEnabled(false);
                vistaTransportes.txt_Costo.setEnabled(false);
                vistaTransportes.jcb_Transportes.setSelectedItem(String.valueOf(vistaTransportes.tbl_Transportes.getValueAt(fila, 0)));
                vistaTransportes.jcb_EstiloViaje.setSelectedItem(String.valueOf(vistaTransportes.tbl_Transportes.getValueAt(fila, 1)));
                vistaTransportes.txt_Costo.setText(String.valueOf(vistaTransportes.tbl_Transportes.getValueAt(fila, 2)));
                idSeDesplazaEn=Integer.parseInt(String.valueOf(vistaTransportes.tbl_Transportes.getValueAt(fila, 3)));
            }
        }
    }
     
     @Override
     public void mousePressed(MouseEvent e){
         
     }
     
     @Override
     public void mouseReleased(MouseEvent e){
         
     }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    // Metodos de entrada de teclado 
     @Override
    public void keyTyped(KeyEvent ke) {
    }
    
    @Override
    public void keyPressed(KeyEvent ke) {
    }
    
    @Override
    public void keyReleased(KeyEvent ke) 
    {   
        // Buscador en tiempo real (busca lo que se esta tecleando) 
        vista.tbl_Destino.setModel(modelo.BuscadorDestino(String.valueOf(vista.txt_Buscar.getText())));
        ocultaColumnaTablaDestino();
        vistaTipoSitio.tbl_TipoSitio.setModel(modelo.BuscadorTipoSitio(idDestino, String.valueOf(vistaTipoSitio.txt_Buscar.getText())));
    }
}