package control.administrador;

import static com.sun.javafx.tk.ImageLoader.*;
import java.awt.Image;
import modelo.administrador.modeloUsuario;
import vistas.administrador.avistaUsuarios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import modelo.Conexion;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import vistas.administrador.avistaMenu;

public class acontrolUsuarios implements ActionListener, MouseListener, KeyListener{
     private modeloUsuario modelo;
     private avistaUsuarios vista;
     String Id="";
    
     public acontrolUsuarios(modeloUsuario modelo, avistaUsuarios vista){
         this.modelo = modelo;
         this.vista = vista;
         this.vista.btn_Actualizar.addActionListener(this);
         this.vista.tbl_Login.addMouseListener(this);
         this.vista.btn_Cancelar.addActionListener(this);
         this.vista.btn_GenerarReporte.addActionListener(this);
         this.vista.txt_Buscar.addKeyListener(this);
         
         desabilitar();
    }
     
    //Oculta Columnas en tabla
    public void ocultaColumna(){
        this.vista.tbl_Login.getColumnModel().getColumn(0).setMaxWidth(0);
        this.vista.tbl_Login.getColumnModel().getColumn(0).setMinWidth(0);
        this.vista.tbl_Login.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        this.vista.tbl_Login.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        this.vista.tbl_Login.getColumnModel().getColumn(0).setPreferredWidth(0);
  
        this.vista.tbl_Login.getColumnModel().getColumn(2).setMaxWidth(2);
        this.vista.tbl_Login.getColumnModel().getColumn(2).setMinWidth(2);
        this.vista.tbl_Login.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(2);
        this.vista.tbl_Login.getTableHeader().getColumnModel().getColumn(2).setMinWidth(2);
        this.vista.tbl_Login.getColumnModel().getColumn(2).setPreferredWidth(2);
    }
     
     public void iniciarVista(){
         vista.setVisible(true);
         Limpiar();
    }
     
     //Reinicia comandos
     public void Limpiar(){
        Id="";
        vista.txt_Usuario.setText("");
        vista.txt_Buscar.setText("");
        vista.txt_Contraseña.setText("");
        vista.txt_Tipo.setText("");
        this.vista.tbl_Login.setModel(modelo.BloquearUsuariosConsultar());
        ocultaColumna();
     }
     
     public void habilitar(){
         //Habilita botones
         vista.btn_Cancelar.setEnabled(true);
         vista.btn_Actualizar.setEnabled(true);
         
         //Habilita JTextField
         vista.txt_Usuario.setEnabled(true);
         vista.txt_Contraseña.setEnabled(true);
         vista.txt_Tipo.setEnabled(true);
     }
     
     public void desabilitar(){
         //Desabilita botones
         vista.btn_Cancelar.setEnabled(false);
         vista.btn_Actualizar.setEnabled(false);
         
         //Desabilita JTextField
         vista.txt_Usuario.setEnabled(false);
         vista.txt_Contraseña.setEnabled(false);
         vista.txt_Tipo.setEnabled(false);
     }
     
     //Valida si los campos no estan vacios y habilita botones y JTextField
     public void CamposVacios(){
        if(Id!="" && vista.txt_Usuario.getText()!="" && vista.txt_Contraseña.getText()!="" && vista.txt_Tipo.getText()!=""){
            desabilitar();
        }
        else{
             habilitar();
        }
     }
     
     @Override
     public void actionPerformed(ActionEvent evento){
        CamposVacios();
        //Boton Actualizar
        if(vista.btn_Actualizar == evento.getSource()) {
            if(modelo.ActualizarUsuario(Integer.parseInt(Id), vista.txt_Usuario.getText(), vista.txt_Contraseña.getText(),vista.txt_Tipo.getText())){
                JOptionPane.showMessageDialog(null, "Usuario actualizado exitosamente");
                Limpiar();
            }
        }
        //Boton borrar texto
        else if(vista.btn_Cancelar == evento.getSource()) {
           Limpiar();
        }
        //Boton Generar Reporte
        else if(vista.btn_GenerarReporte == evento.getSource()) {
            Limpiar();
            desabilitar();
            try 
            {
                try 
            {
                Conexion con = new Conexion();
                Connection conn = con.abrirConexion();
                if(con != null) 
                {
                    JasperReport reporte = null;
                    String path = ("src\\Reportes\\Usuarios.jasper");
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
    }
              
     @Override
     public void mouseClicked(MouseEvent e){
         habilitar();
         if(vista.tbl_Login == e.getSource()){
             habilitar();
             int fila = vista.tbl_Login.rowAtPoint(e.getPoint());
             if(fila > -1){
                 //Guardar datos de la tabla a los JTextField
                 Id=(String.valueOf(vista.tbl_Login.getValueAt(fila, 0)));
                 vista.txt_Usuario.setText(String.valueOf(vista.tbl_Login.getValueAt(fila, 1)));
                 vista.txt_Contraseña.setText(String.valueOf(vista.tbl_Login.getValueAt(fila, 2)));
                 vista.txt_Tipo.setText(String.valueOf(vista.tbl_Login.getValueAt(fila, 3)));
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
    {   // Buscador en tiempo real (busca lo que se esta tecleando) 
        vista.tbl_Login.setModel(modelo.Buscador(String.valueOf(vista.txt_Buscar.getText())));
        ocultaColumna();
    }
}