/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import modelo.modeloActividadComentarios;
import modelo.modeloActividades;
import modelo.modeloAgregarViaje;
import modelo.modeloDetalleDestino;
import vistas.vistaActividadComentarios;
import vistas.vistaActividades;
import vistas.vistaAgregarViaje;
import vistas.vistaDetallesDestino;
import vistas.vistaPrincipal;

/**
 *
 * @author Holi
 */
public class controlDetalleDestino implements ActionListener{

    private vistaDetallesDestino vista;
    private vistaPrincipal vistaPrincipal;
    private modeloDetalleDestino modelo;
    private String idD;
    private JButton btnImagen;   
    private boolean banDeseos=false;
    
    public controlDetalleDestino(vistaDetallesDestino vista, vistaPrincipal vistaPrincipal, modeloDetalleDestino modelo, String idD)
    {
        this.vista=vista;
        this.modelo=modelo;
        this.vistaPrincipal=vistaPrincipal;
        this.idD = idD;
        this.vista.btnActividades.addActionListener(this);
        this.vista.btnViaje.addActionListener(this);
        this.vista.btnDeseos.addActionListener(this);
        imagenN();
       
        actTrans(this.modelo.datosTransportes(this.idD), vista.pnlTransportes, "autobus");
        actTrans(this.modelo.datosActividades(this.idD), vista.pnlActividades, "billete-con-estrella");
        String [] des = modelo.deseosC(controlPrincipal.usuario[2]);
        if(des!=null){
            for(int i=0; i<des.length; i++){
                if(des[i].equals(idD)){
                    ImageIcon image = new ImageIcon(getClass().getResource("../images/btn_DeseoQuitarMorado.png"));
                    Icon fondo = new ImageIcon(image.getImage().getScaledInstance(154, 31, Image.SCALE_DEFAULT));
                    this.vista.btnDeseos.setIcon(fondo);
                    
                    //efecto del mouse
                    ImageIcon image3 = new ImageIcon(getClass().getResource("../images/btn_DeseosQuitarLila.png"));
                    Icon fondo3 = new ImageIcon(image3.getImage().getScaledInstance(154, 31, Image.SCALE_DEFAULT));
                    this.vista.btnDeseos.setRolloverIcon(fondo3);
                    banDeseos=true;
                }
            }
        }
    }
    
    public void imagenN(){
        String [] des;
        des=modelo.datosDestinos(idD);
        if(des!=null){
            vista.lblNombre.setText(des[0]);
            ImageIcon imagen = new ImageIcon(des[1]);
            Icon fondo = new ImageIcon(imagen.getImage().getScaledInstance(827, 500, Image.SCALE_DEFAULT));
            vista.lblImagen.setIcon(fondo);
        }
    }
    
    public void actTrans(String [][] act, JPanel p, String img){
        //limpia el panel
        p.removeAll();
        p.revalidate();
        p.repaint();
        p.setLayout(new FlowLayout(FlowLayout.LEFT));
        if(act.length > 0){
            for(int i=0; i<act.length; i++){
                //Panel principal: verde
                JPanel principal = new JPanel();
                principal.setLayout(new GridLayout( 2, 1, 0, 5));
                principal.setBackground(new java.awt.Color(156,255,87));
                principal.setSize(250,200);
                principal.setPreferredSize(new Dimension(250, 250));
                //Imagen
                ImageIcon image = new ImageIcon(getClass().getResource("../images/icons8-"+img+"-100.png"));
                if(act[i][2] != null){
                    image = new ImageIcon(act[i][2]);
                }

                Icon fondo = new ImageIcon(image.getImage().getScaledInstance(250, 150, Image.SCALE_DEFAULT));
                btnImagen = new JButton(fondo);
                
                    //Para hacerlo invisible
                btnImagen.setBorderPainted(false);
                btnImagen.setContentAreaFilled(false);
                btnImagen.setDefaultCapable(false);
                btnImagen.setFocusPainted(false);
                btnImagen.setFocusable(false);
                btnImagen.setSize(250, 150);
                btnImagen.addActionListener(this);
                
                    //Sin bordes
                btnImagen.setBorder(new EmptyBorder(5, 0, 0, 0));
                
                
                //Panel de información
                JPanel informacion = new JPanel(new BorderLayout(0, -70));
                informacion.setBackground(new java.awt.Color(156,255,87));
                    //Nombre del destino
                JLabel nombreD = new JLabel(act[i][1]);
                nombreD.setFont(new Font("Candara", Font.PLAIN, 14));
                nombreD.setBorder(new EmptyBorder(10, 5, 0, 0));
                nombreD.setSize(250, 15);
                if(!img.equals("autobus")){
                    //id de la actividad
                    btnImagen.setName("A"+act[i][0]);
                    btnImagen.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    btnImagen.setToolTipText("Ver Actividad");
                    
                     //Calificación
                    JPanel cal = new JPanel();
                    cal.setLayout(new BoxLayout(cal, BoxLayout.X_AXIS));
                    cal.setBackground(new java.awt.Color(156,255,87));
                    cal.setBorder(new EmptyBorder(10, 5, 0, 0));

                        //si tiene calificación que cree los radiobutton              
                    if(modelo.actividadCal(act[i][0]) != null){
                        JRadioButton r1 = new JRadioButton();
                        JRadioButton r2 = new JRadioButton();
                        JRadioButton r3 = new JRadioButton();
                        JRadioButton r4 = new JRadioButton();
                        JRadioButton r5 = new JRadioButton();

                        r1.setBackground(new java.awt.Color(156,255,87));
                        r2.setBackground(new java.awt.Color(156,255,87));
                        r3.setBackground(new java.awt.Color(156,255,87));
                        r4.setBackground(new java.awt.Color(156,255,87));
                        r5.setBackground(new java.awt.Color(156,255,87));

                        r1.setEnabled(false);
                        r2.setEnabled(false);
                        r3.setEnabled(false);
                        r4.setEnabled(false);
                        r5.setEnabled(false);

                        cal.add(r1);
                        cal.add(r2);
                        cal.add(r3);
                        cal.add(r4);
                        cal.add(r5);
                            //Rellenar los radiobutton
                        double c = Double.parseDouble(modelo.actividadCal(act[i][0]));
                        if(c<=1.99){
                            r1.setSelected(true);
                        }
                        else if(c<=2.99){
                            r1.setSelected(true);
                            r2.setSelected(true);
                        }
                        else if(c<=3.99){
                            r1.setSelected(true);
                            r2.setSelected(true);
                            r3.setSelected(true);
                        }
                        else if(c<=4.99){
                            r1.setSelected(true);
                            r2.setSelected(true);
                            r3.setSelected(true);
                            r4.setSelected(true);
                        }
                        else{
                            r1.setSelected(true);
                            r2.setSelected(true);
                            r3.setSelected(true);
                            r4.setSelected(true);
                            r5.setSelected(true);
                        }
                    }
                        //Si no tiene calificación que aparezca el mensaje
                    else{
                        JLabel mensajec = new JLabel("¡Sé el primero en calificarlo!");
                        mensajec.setForeground(new Color(76,2,131));
                        mensajec.setFont(new Font("Candara", Font.PLAIN, 14));
                        mensajec.setHorizontalAlignment(SwingConstants.LEFT);
                        mensajec.setBorder(new EmptyBorder(10, 5, 0, 0));
                        cal.add(mensajec);
                    }
                    informacion.add(nombreD, BorderLayout.NORTH); 
                    informacion.add(cal, BorderLayout.CENTER);
                }
                else
                {
                    
                    JLabel datosT = new JLabel("<html><p><b>Estilo de Viaje:</b> "+act[i][3]+"</p><p><b>Costo promedio:</b> "+act[i][4]+"</p></html>");
                    datosT.setFont(new Font("Candara", Font.PLAIN, 14));
                    datosT.setBorder(new EmptyBorder(10, 5, 0, 0));
                    datosT.setSize(250, 15);
                    informacion.add(nombreD, BorderLayout.NORTH); 
                    informacion.add(datosT, BorderLayout.CENTER); 
                }
                
                //Agregar la imagen y la información
                principal.add(btnImagen);
                principal.add(informacion);
                //Agregar el panel principal al scroll
                p.add(principal);
                //separación en blanco
                JPanel separacion = new JPanel();
                separacion.setBackground(new java.awt.Color(255,255,255));
                separacion.setSize(5,5);
                p.add(separacion);
            }
        }
        else
        {
            //Si no se encuentra 
            JLabel mensaje = new JLabel("<html><p>No se ha encontrado nada :(</p></html>");
            mensaje.setForeground(new Color(76,2,131));
            mensaje.setFont(new Font("Candara", Font.PLAIN, 14));
            p.add(mensaje);
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            JComponent selectedButton = (JComponent) e.getSource();
            String letra = selectedButton.getName().substring(0, 1);  
            String idA = selectedButton.getName().substring(1);  
        //Botón de detalles de actividad
            if(letra.equals("A")){
                vistaActividadComentarios vActividadComentarios = new vistaActividadComentarios();
                modeloActividadComentarios mActividadComentarios = new modeloActividadComentarios();
                controlActividadComentarios comentarios = new controlActividadComentarios(vActividadComentarios, vistaPrincipal, mActividadComentarios, idA, idD);
                CambiaPanel cambiar = new CambiaPanel(vistaPrincipal.panelCambiante, vActividadComentarios);
            }
        }
        catch(NullPointerException ex){}
        
        //botón de más actividades
        if(this.vista.btnActividades == e.getSource())
        {

            vistaActividades vActividades = new vistaActividades();
            modeloActividades mActividades = new modeloActividades();
            controlActividades cActividades = new controlActividades(vActividades, vistaPrincipal, mActividades, idD, null);
            CambiaPanel cambiar = new CambiaPanel(vistaPrincipal.panelCambiante, vActividades);
        }

        //botón de nuevo viaje
        if(this.vista.btnViaje == e.getSource())
        {
            vistaAgregarViaje vAgregarViaje = new vistaAgregarViaje();
            modeloAgregarViaje mAgregarViaje = new modeloAgregarViaje();
            controlAgregarViaje cAgregarViaje = new controlAgregarViaje(vAgregarViaje, vistaPrincipal, mAgregarViaje, idD);
            CambiaPanel cambiar = new CambiaPanel(vistaPrincipal.panelCambiante, vAgregarViaje);
        }
        
        //botón de lista de deseos
        if(this.vista.btnDeseos == e.getSource()){
            if(banDeseos){
                if(modelo.deseosQ(controlPrincipal.usuario[2], idD)){
                    JOptionPane.showMessageDialog(null, "¡Se ha quitado de tu lista de deseos! :(", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                    ImageIcon image = new ImageIcon(getClass().getResource("../images/btn_DeseoMorado.png"));
                    Icon fondo = new ImageIcon(image.getImage().getScaledInstance(154, 31, Image.SCALE_DEFAULT));
                    this.vista.btnDeseos.setIcon(fondo);

                    //efecto del mouse
                    ImageIcon image3 = new ImageIcon(getClass().getResource("../images/btn_DeseosLila.png"));
                    Icon fondo3 = new ImageIcon(image3.getImage().getScaledInstance(154, 31, Image.SCALE_DEFAULT));
                    this.vista.btnDeseos.setRolloverIcon(fondo3);
                    banDeseos=false;
                }
            }else{
                if(modelo.deseos(controlPrincipal.usuario[2], idD)){
                    JOptionPane.showMessageDialog(null, "¡Se ha guardado en tu lista de deseos!", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                    ImageIcon image = new ImageIcon(getClass().getResource("../images/btn_DeseoQuitarMorado.png"));
                    Icon fondo = new ImageIcon(image.getImage().getScaledInstance(154, 31, Image.SCALE_DEFAULT));
                    this.vista.btnDeseos.setIcon(fondo);

                    //efecto del mouse
                    ImageIcon image3 = new ImageIcon(getClass().getResource("../images/btn_DeseosQuitarLila.png"));
                    Icon fondo3 = new ImageIcon(image3.getImage().getScaledInstance(154, 31, Image.SCALE_DEFAULT));
                    this.vista.btnDeseos.setRolloverIcon(fondo3);
                    banDeseos=true;
                }
            }
            
        }
    }
}
