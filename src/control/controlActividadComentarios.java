/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelo.modeloActividadComentarios;
import vistas.vistaActividadComentarios;
import vistas.vistaPrincipal;

/**
 *
 * @author Holi
 */
public class controlActividadComentarios implements MouseListener{
    
    private vistaActividadComentarios vista;
    private vistaPrincipal vPrincipal;
    private modeloActividadComentarios modelo;
    //ID de la actividad y del destino
    private String idA, idD; 
    //Datos de las actividades
    String [] act;
       
    public controlActividadComentarios(vistaActividadComentarios vActividadComentarios, vistaPrincipal vPrincipal, modeloActividadComentarios modelo, String idA, String idD)
    {
        this.vista=vActividadComentarios;
        this.vPrincipal=vPrincipal;
        this.modelo=modelo;
        //id de la actividad
        this.idA=idA;
        //id del destino
        this.idD=idD;
        //carga los datos de la actividad
        datosA();
        this.vista.lblEstilo.addMouseListener(this);
        
        this.vista.lblEstilo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    
    public void datosA(){
        //datos
        act = modelo.datosActividades(idA, idD);
        //Si trae datos
        if(act!=null){
            //nombre de la actividad
            vista.lblNombre.setText(act[0]);
            //imagen de la actividad
            ImageIcon imagen = new ImageIcon(act[1]);
            Icon fondo = new ImageIcon(imagen.getImage().getScaledInstance(827, 500, Image.SCALE_DEFAULT));
            vista.lblImagen.setIcon(fondo);
            //descripcion de la actividad
            vista.lblDescripcion.setText(act[2]);
            //localizacion dependiendo del destino
            vista.lblLocalizacion.setText(act[3]);
            //estilo de viaje
            vista.lblEstilo.setText(act[4]);
            vista.lblEstiloPresupuesto.setText("Presupuesto entre "+act[6]+"-"+act[7]+" pesos mexicanos");
        }
    }
    
    //Panel del estilo de viaje
    private JPanel getPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("<html>"
                + "<center><h1>"+act[4]+"</h1><center>"
                + "<p>"+act[5]+"</p>"
                + "</html>");
        ImageIcon image2 = new ImageIcon(act[8]);                
        Icon fondo2 = new ImageIcon(image2.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
       
        label.setIcon(fondo2);
        panel.add(label);

        return panel;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == this.vista.lblEstilo){
            JOptionPane.showMessageDialog(null,getPanel(),"Detalle de Estilo de Viaje", JOptionPane.DEFAULT_OPTION, null);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==this.vista.lblEstilo){
            this.vista.lblEstilo.setForeground(new Color(76,2,131));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==this.vista.lblEstilo){
            this.vista.lblEstilo.setForeground(new Color(102,102,102));
        }
    }
}
