package control.administrador;
import java.awt.Image;
import modelo.administrador.modeloSitioTuristico; 
import vistas.administrador.avistaTipoSitio;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
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

public class acontrolSitioTuristico implements ActionListener, MouseListener, KeyListener{
    private modeloSitioTuristico modelo; // Se crea un objeto del modelo 
    private avistaTipoSitio vista; // Se crea un objeto de la vista
    String Foto="";
    int id=0;
    String f="";
    private String tipo = "C:/xampp/htdocs/Fleetock/Imagenes/TipoSitio/";
    private JFileChooser archivo = new JFileChooser();
    public int p=0; 
    File file;
    int ventana =0;
    String rutaArchivo="";
    boolean bandera=false;
    
    public acontrolSitioTuristico(modeloSitioTuristico  modelo, avistaTipoSitio vista)
    {   this.modelo= modelo; 
        this.vista= vista; 
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnCancelar.addActionListener(this);
        this.vista.btnGenerarR.addActionListener(this); 
        this.vista.tbl_STuristicos.addMouseListener(this);
        this.vista.txt_Buscar.addKeyListener(this);
        this.vista.btn_Seleccionar.addActionListener(this);
        vista.txtTipo.setEnabled(false);
        deshabilitar(); 
    }
    public void limpiar() // Limpia los cuadros de texto cuando se agrega, modifica y se elimina 
    {   
        Foto="";
        id=0;
        f="";
        vista.taDescripcion.setText("");
        vista.txtTipo.setText("");
        deshabilitar();
        vista.tbl_STuristicos.setModel(modelo.administradorConsultar()); 
        ocultaColumna(); 
        vista.taDescripcion.setEnabled(false);
        vista.lbl_Foto.setIcon(null);
        vista.lbl_Foto.removeAll();
        vista.lbl_Foto.revalidate();
        vista.lbl_Foto.repaint();
        deshabilitar();
    }
    public void habilitar() // Habilita los botones cuando se selecciona una fila de la tabla 
    {   vista.btnActualizar.setEnabled(true);
        vista.btnCancelar.setEnabled(true);
        vista.lbl_Foto.setEnabled(true);
        vista.btn_Seleccionar.setEnabled(true);
    }
    public void deshabilitar() // Se deshabilitan los botones cuando recien se crea el objeto o no tiene seleccionado una fila de la tabla 
    {   vista.btnActualizar.setEnabled(false);
        vista.lbl_Foto.setEnabled(false);
        vista.btn_Seleccionar.setEnabled(false);
    }
    public void iniciarVista() // Muestra la vista 
    {   limpiar();
        vista.setVisible(true); 
    }
    public String validacionCamposVacios() // Valida que los campos no esten vacios
    {   if(vista.txtTipo.getText().isEmpty() || vista.taDescripcion.getText().isEmpty())
            return "Favor de llenar todos los campos"; 
        else 
            return null;
    }
    public void ocultaColumna() // Oculta la primer columna de la tabla 
    {   this.vista.tbl_STuristicos.getColumnModel().getColumn(0).setMaxWidth(0);
        this.vista.tbl_STuristicos.getColumnModel().getColumn(0).setMinWidth(0);
        this.vista.tbl_STuristicos.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        this.vista.tbl_STuristicos.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        this.vista.tbl_STuristicos.getColumnModel().getColumn(0).setPreferredWidth(0);
    }
    @Override
    public void actionPerformed(ActionEvent evento) {  
        if(vista.btnActualizar == evento.getSource())
        {  
            if(bandera==true) 
            {
                File dir = new File(tipo);
                System.out.println("f: "+f);
                if(Foto!=f){
                    //Elimina imagen del la carpeta en Disco C
                    String[] ficheros = dir.list(); 
                    File archivoel = new File(tipo+ficheros[p]);
                    archivoel.delete();
                }
                else{
                    limpiar();
                }
                //Genera un fichero
                File fichero= archivo.getSelectedFile();
                String ruta = fichero.getAbsolutePath(); 
                rutaArchivo = tipo + Foto; 
                File folder = new File(tipo); 
                if(!folder.exists())
                    folder.mkdirs(); 
                //Genera la ruta donde se guarda la imagen
                copyFile_Java(ruta,rutaArchivo); 
                Foto=rutaArchivo;
                System.out.println("RUTA FINAL:"+Foto);
                if(modelo.administradorActualizarS(id,vista.taDescripcion.getText(), Foto))
                    {   JOptionPane.showMessageDialog(null, "Registro actualizado exitosamente");
                        this.vista.tbl_STuristicos.setModel(modelo.administradorConsultar());
                        ocultaColumna(); 
                        limpiar(); 
                    }
                    else 
                        JOptionPane.showMessageDialog(null, "Error al actualizar los datos");
            } 
            else
                JOptionPane.showMessageDialog(null, "No se encontro imagen, favor de agregar una valida");
        }

        else if(vista.btnCancelar == evento.getSource()) 
        {   
            // Limpia los cuadros de texto y deshabilita los botones cuando cancela
            limpiar();
        }
        else if(vista.btnGenerarR == evento.getSource()) 
        {   try
            {   try
                {   Conexion conexion = new Conexion(); 
                    Connection con = conexion.abrirConexion();
                    if( con != null)
                    {   JasperReport reporte= null; 
                        String path="src\\Reportes\\reporteTipoSitio.jasper"; 
                        reporte = (JasperReport) JRLoader.loadObjectFromFile(path); 
                        JasperPrint jprint = JasperFillManager.fillReport(reporte, null,con); 
                        JasperViewer view = new JasperViewer(jprint, false); 
                        view.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
                        view.setVisible(true);
                    }
                }
                catch(SQLException e)
                {   JOptionPane.showMessageDialog(null, "Error al intentar generar el reporte");
                }
            }catch(JRException ex)
            {   JOptionPane.showMessageDialog(null, "Error al intentar generar el reporte"); 
            }
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
                //System.out.println("extension "+ getFileExtension(file));
                if( !"jpg".equals(getFileExtension(file)) && !"png".equals(getFileExtension(file)))
                {
                    JOptionPane.showMessageDialog(null, "Favor de introducir un archivo jpg o png.");
                    Foto="";
                }else{ 
                Image foto = vista.getToolkit().getImage(String.valueOf(file)); 
                foto = foto.getScaledInstance(168, 159, Image.SCALE_DEFAULT); 
                vista.lbl_Foto.setIcon(new ImageIcon(foto)); 
                Foto=id+"."+getFileExtension(file); 
                System.out.println("SE GUARDA EN:"+Foto);
                bandera=true; 
                }
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
    public void mouseClicked(MouseEvent e) // Carga los elementos de la fila que selecciono a los textfield
    {   
        int fila = vista.tbl_STuristicos.rowAtPoint(e.getPoint()); 
        if(vista.tbl_STuristicos == e.getSource())
        {   
            vista.taDescripcion.setEnabled(true);
            if( fila > -1)
            {  
                id=(Integer.parseInt(String.valueOf(vista.tbl_STuristicos.getValueAt(fila, 0))));
                vista.txtTipo.setText(String.valueOf(vista.tbl_STuristicos.getValueAt(fila, 1)));
                vista.taDescripcion.setText(String.valueOf(vista.tbl_STuristicos.getValueAt(fila, 2)));
                Foto=(String.valueOf(vista.tbl_STuristicos.getValueAt(fila, 3)));
                f=Foto;
                habilitar(); 
                bandera=false;
            }
        }
        File dir = new File(tipo); // Se crea un objeto tipo file
        String[] ficheros = dir.list(); // Se crea un arreglo que contiene la lista del fichero 
        for(p=0; p<dir.list().length; p++) // Se busca en el fichero la imagen, con la ruta que se guardo en la BD
        {   
            if(String.valueOf(vista.tbl_STuristicos.getValueAt(fila, 3)).equals(tipo+ficheros[p]))
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
            Imagen imagen = new Imagen(x, y, tipo+""+ficheros[p]); 
            vista.lbl_Foto.removeAll();
            vista.lbl_Foto.revalidate();
            vista.lbl_Foto.repaint();
            vista.lbl_Foto.add(imagen); 
            vista.lbl_Foto.repaint();
        }
    }   
    
    @Override
    public void mousePressed(MouseEvent me) {}
    @Override
    public void mouseReleased(MouseEvent me) {}
    @Override
    public void mouseEntered(MouseEvent me) {}
    @Override
    public void mouseExited(MouseEvent me) {}
    // Metodos de entrada de datos 
    @Override
    public void keyTyped(KeyEvent ke) {}
    @Override
    public void keyPressed(KeyEvent ke) {}
    @Override
    public void keyReleased(KeyEvent ke) 
    {   // Buscador en tiempo real (busca lo que se esta tecleando) 
        vista.tbl_STuristicos.setModel(modelo.Buscador(String.valueOf(vista.txt_Buscar.getText())));
        ocultaColumna(); 
    }
}