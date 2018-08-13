package control.administrador;

import java.io.File;

import static com.sun.javafx.tk.ImageLoader.*;
import java.awt.Image;
import modelo.administrador.modeloTransporte;
import vistas.administrador.avistaTransporte;

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
import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class acontrolTransportes implements ActionListener, MouseListener, KeyListener{
    private modeloTransporte modelo;
    private avistaTransporte vista;
    //Se crea una carpeta en el Disco C
    private String transporte = "C:/xampp/htdocs/Fleetock/Imagenes/Transportes/";
    private JFileChooser archivo = new JFileChooser();
    public int p=0; 
    File file;
    int ventana =0;
    String rutaArchivo="";
    boolean bandera = false; 
    String Tipo="";
    String r="";
    
    public acontrolTransportes(modeloTransporte modelo, avistaTransporte vista){
        this.modelo = modelo;
        this.vista = vista;
        this.vista.btn_Insertar.addActionListener(this);
        this.vista.btn_Eliminar.addActionListener(this);
        this.vista.btn_Actualizar.addActionListener(this);
        this.vista.tbl_Transporte.addMouseListener(this);
        this.vista.btn_Seleccionar.addActionListener(this);
        this.vista.btn_Cancelar.addActionListener(this);
        this.vista.txt_Buscar.addKeyListener(this);
        desabilitar();
     }
    
    //oculta la columna de id
    public void ocultaColumna()
    {
        this.vista.tbl_Transporte.getColumnModel().getColumn(0).setMaxWidth(0);
        this.vista.tbl_Transporte.getColumnModel().getColumn(0).setMinWidth(0);
        this.vista.tbl_Transporte.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        this.vista.tbl_Transporte.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        this.vista.tbl_Transporte.getColumnModel().getColumn(0).setPreferredWidth(0);
    }
    
    public void iniciarVista(){
        //vista.lbl_Foto.setIcon(new ImageIcon(getClass().getResource("../imagenes/fondo.jpg")));
        Limpiar();
        vista.setVisible(true);
    }
     
    public void Limpiar(){
        vista.tbl_Transporte.setModel(modelo.transporteConsultar());
        ocultaColumna(); 
        bandera=false;
        vista.txt_Id.setText("");
        vista.txt_Tipo.setText("");
        vista.txtUrl.setText("");
        vista.txt_Buscar.setText("");
        vista.lbl_Foto.setIcon(null);
        vista.lbl_Foto.removeAll();
        vista.lbl_Foto.revalidate();
        vista.lbl_Foto.repaint();
        r="";
        Tipo="";
        desabilitar();
    }
     
     //Habilita los botones
     public void habilitar(){
         vista.btn_Cancelar.setEnabled(true);
         vista.btn_Actualizar.setEnabled(true);
         vista.btn_Eliminar.setEnabled(true);
     }
     
     //Desabilita los botones
     public void desabilitar(){
         vista.btn_Actualizar.setEnabled(false);
         vista.btn_Eliminar.setEnabled(false);
     }
     
     //Compara si los campos no estan vacios para poder continuar
     public String CamposVacios()
    {
        if(vista.txt_Tipo.getText().isEmpty() || vista.txtUrl.getText().isEmpty())
            return "Favor de llenar todos los campos"; 
        else 
            return null;
    }
     
     @Override
     public void actionPerformed(ActionEvent evento){
        //Boton Insertar
        if(vista.btn_Insertar == evento.getSource()) {
            if(CamposVacios()==null)
            {
                if(ventana == JFileChooser.APPROVE_OPTION)
                { 
                 int idT=modelo.ConsultarIdTransporte();
                 String ruta = file.getAbsolutePath(); 
                 File fichero= archivo.getSelectedFile();
                 idT=idT+1;
                 rutaArchivo = transporte+""+idT+"."+getFileExtension(fichero); 
                 File folder = new File(transporte); 
                 if(!folder.exists())
                     folder.mkdirs(); 
                 copyFile_Java(ruta,rutaArchivo); 
                }
                //Inserta Destino
                if(modelo.transporteInsertar(vista.txt_Tipo.getText(), rutaArchivo)==true)
                {
                    Limpiar();
                    JOptionPane.showMessageDialog(null, "Transporte agregado exitosamente");
                }
                else
                    JOptionPane.showMessageDialog(null, "Error al insertar Destino");
            }
            else 
                JOptionPane.showMessageDialog(null, ""+CamposVacios());
        }
         //Boton Eliminar
         else if(vista.btn_Eliminar == evento.getSource()) {
            if (JOptionPane.showConfirmDialog(vista,
                "¿Estás seguro que deseas eliminar este registro?", "Fleetock",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    if(modelo.transporteEliminar(Integer.parseInt(vista.txt_Id.getText()), vista.txt_Tipo.getText(), vista.txtUrl.getText()))
                    {
                        JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente");
                        Limpiar();
                        File dir = new File(transporte); 
                        String[] ficheros = dir.list(); 
                        File archivoel = new File(transporte+ficheros[p]);
                        archivoel.delete();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar, este registro se encuentra en otras tablas");
                        Limpiar();
                    }
            }
            else
            {
                Limpiar();
                vista.btn_Insertar.setEnabled(true);
            }
         }
         //Boton Actualizar
         else if(vista.btn_Actualizar == evento.getSource()) {
            if(bandera == true)
            {       
                File dir = new File(transporte);
                if(vista.txt_Tipo.getText().equals(Tipo)){}
                else {
                    //Elimina imagen del la carpeta en Disco C
                    String[] ficheros = dir.list(); 
                    File archivoel = new File(transporte+ficheros[p]);
                    archivoel.delete();
                }
                if (dir.exists()) {} 
                else {
                    //Elimina imagen del la carpeta en Disco C
                    String[] ficheros = dir.list(); 
                    File archivoel = new File(transporte+ficheros[p]);
                    archivoel.delete();
                }
                if(vista.txtUrl.getText().equals(r) && vista.txt_Tipo.getText().equals(Tipo))
                {
                    JOptionPane.showMessageDialog(null, "No se registro ningun cambio");
                    Limpiar();
                }
                else if(vista.txtUrl.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Favor de seleccionar una imagen");
                        Limpiar();
                }
                else if(vista.txt_Tipo.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Favor de llenar todos los campos");
                        Limpiar();
                }
                else {
                    //Genera un fichero
                    File fichero= archivo.getSelectedFile();
                    String ruta = fichero.getAbsolutePath();
                    String nombre = vista.txt_Id.getText()+"."+getFileExtension(fichero); 
                    rutaArchivo = transporte + nombre; 
                    File folder = new File(transporte); 
                    if(!folder.exists())
                        folder.mkdirs(); 
                    //Genera la ruta donde se guarda la imagen
                    copyFile_Java(ruta,rutaArchivo); 
                    vista.txtUrl.setText(rutaArchivo);
                    if(modelo.transporteActualizar(Integer.parseInt(vista.txt_Id.getText()), vista.txt_Tipo.getText(), vista.txtUrl.getText()))
                    {
                        JOptionPane.showMessageDialog(null, "Registro actualizado exitosamente");
                        Limpiar();
                        vista.btn_Insertar.setEnabled(true);
                    }else 
                        JOptionPane.showMessageDialog(null, "Error al actualizar los datos");
                }
            }
            else
                JOptionPane.showMessageDialog(null, "No se encontro imagen, favor de agregar una valida");
        }
        //Boton borrar texto
        else if(vista.btn_Cancelar == evento.getSource()) 
        {
            Limpiar();
            vista.btn_Insertar.setEnabled(true);
        }
        
        //Boton actualizar
        else if(vista.btn_Seleccionar == evento.getSource()) {
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formato de archivo JPG", "jpg", "jpeg"); 
            archivo.addChoosableFileFilter(filtro);
            archivo.setDialogTitle("Abrir archivo"); 
            ventana = archivo.showOpenDialog(null); 
            if( ventana == JFileChooser.APPROVE_OPTION)
            {
                vista.lbl_Foto.removeAll();
                vista.lbl_Foto.revalidate();
                vista.lbl_Foto.repaint();
                file = archivo.getSelectedFile(); 
                System.out.println("FILE:"+file);
                //System.out.println("extension "+ getFileExtension(file));
                if( !"jpg".equals(getFileExtension(file)) && !"png".equals(getFileExtension(file)))
                {
                    JOptionPane.showMessageDialog(null, "Favor de introducir un archivo jpg o png.");
                    vista.txtUrl.setText(""); 
                }else{ 
                Image foto = vista.getToolkit().getImage(String.valueOf(file)); 
                foto = foto.getScaledInstance(168, 159, Image.SCALE_DEFAULT); 
                vista.lbl_Foto.setIcon(new ImageIcon(foto)); 
                vista.txtUrl.setText(""+bandera); 
                bandera=true; }
            }
        }
    }
      
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
    
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
     
     @Override
     public void mouseClicked(MouseEvent e){
        habilitar();
        if(vista.tbl_Transporte == e.getSource())
        {
            vista.btn_Insertar.setEnabled(false);
            int fila = vista.tbl_Transporte.rowAtPoint(e.getPoint());
            if(fila > -1)
            {
                vista.txt_Id.setText(String.valueOf(vista.tbl_Transporte.getValueAt(fila, 0)));
                vista.txt_Tipo.setText(String.valueOf(vista.tbl_Transporte.getValueAt(fila, 1)));
                Tipo=vista.txt_Tipo.getText();
                vista.txtUrl.setText(String.valueOf(vista.tbl_Transporte.getValueAt(fila, 2)));
                r=vista.txtUrl.getText();
                habilitar(); 
                bandera=false; // Se limpia esta bandera
                }
                File dir = new File(transporte); // Se crea un objeto tipo file
                String[] ficheros = dir.list(); // Se crea un arreglo que contiene la lista del fichero 
                for(p=0; p<dir.list().length; p++) // Se busca en el fichero la imagen, con la ruta que se guardo en la BD
                {   
                    if(String.valueOf(vista.tbl_Transporte.getValueAt(fila, 2)).equals(transporte+ficheros[p]))
                    {   
                        bandera=true;
                        break;
                    }
                }
                if(bandera == false) // Si no tiene imagen se coloca una imagen que indica que en ese registro no se cuenta con imagen 
                {   vista.lbl_Foto.removeAll();
                    vista.lbl_Foto.revalidate();
                    vista.lbl_Foto.repaint();
                    vista.lbl_Foto.setIcon(new ImageIcon("C:/xampp/htdocs/Fleetock/Imagenes/fondo.jpg"));
                }
                else // Si existe la imagen se carga en el label 
                {   int x = vista.lbl_Foto.getWidth(); 
                    int y = vista.lbl_Foto.getHeight(); 
                    Imagen imagen = new Imagen(x, y, transporte+""+ficheros[p]); 
                    vista.lbl_Foto.removeAll();
                    vista.lbl_Foto.revalidate();
                    vista.lbl_Foto.repaint();
                    vista.lbl_Foto.add(imagen); 
                    vista.lbl_Foto.repaint();
                }
       }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        vista.tbl_Transporte.setModel(modelo.Buscador(String.valueOf(vista.txt_Buscar.getText())));
        ocultaColumna();
    }
  }