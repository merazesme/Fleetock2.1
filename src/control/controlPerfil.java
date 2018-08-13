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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import static javax.swing.Spring.width;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import modelo.modeloEditarPerfil;
import modelo.modeloEditarViaje;
import modelo.modeloEditarViajeMN;
import modelo.modeloNuevoViajeSS;
import modelo.modeloPerfil;
import vistas.vistaEditarPerfil;
import vistas.vistaEditarViaje;
import vistas.vistaEditarViajeMN;
import vistas.vistaNuevoViajeSS;
import vistas.vistaPerfil;
import vistas.vistaPrincipal;

/**
 *
 * @author Holi
 */
public class controlPerfil implements ActionListener, MouseListener{

    private vistaPerfil vista;
    private vistaPrincipal vistaPrincipal;
    private modeloPerfil modelo;
    private JButton btnImagen;
    private JLabel mensaje = new JLabel();
    private String idU;
    private boolean pC=false;
    public controlPerfil(vistaPerfil vista, vistaPrincipal vistaPrincipal, modeloPerfil modelo, String idU)
    {
        this.vista=vista;
        this.idU=idU;
        this.vistaPrincipal=vistaPrincipal;
        this.modelo=modelo;
        
        if(idU==null || idU.equals(controlPrincipal.usuario[2])){
            this.vista.btnEditarPerfil.addActionListener(this);
            pC=false;
        }else{
            pC=true;
            this.vista.btnEditarPerfil.setVisible(false);
        }
        
        this.vista.pnlViajes.setBorder(new EmptyBorder(5, 40, 0, 0));
        datos();
        viajes(modelo.datosViajes(usuarioA()), this.vista.pnlViajes);
                //Manda para arriba el scroll
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() { 
            vista.scrollPerfil.getVerticalScrollBar().setValue(0);
        }
        });
    }
    
    public String usuarioA(){
        String a = controlPrincipal.usuario[2];
        String b = idU;
        if(pC){
            return b;
        }
        return a;
    }
    
    public void datos(){
        String [] uDatos=modelo.usuarioDatos(usuarioA());
        if(uDatos!=null) {
            vista.lblNombre.setText(uDatos[0]+" "+uDatos[1]);
            vista.lblUsuario.setText("@"+uDatos[3]);
            if (uDatos[2]!=null) {
                ImageIcon image = new ImageIcon(uDatos[2]);                
                Icon fondo = new ImageIcon(image.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
                vista.lblImagen.setIcon(fondo);
            }
            vista.txtareaDescripcion.setText(uDatos[4]);
        }
    }
    
    public void viajes(String [][] via, JPanel p){
        //limpia el panel
        p.removeAll();
        p.revalidate();
        p.repaint();
        p.setLayout(new FlowLayout(FlowLayout.LEFT));
        if(via.length > 0){
            
            String [][] temporal = new String [via.length+1][5];
            temporal[0][0]= via[0][0];
            temporal[0][1]= via[0][1];
            temporal[0][2]= via[0][2];
            temporal[0][3]= via[0][3];
            temporal[0][4]= "U" ;
            int c=0;
            int x;

            for(x=1; x<via.length; x++){
                if(temporal[c][0].equals(via[x][0])){
                    temporal[c][3]=temporal[c][3]+" | "+via[x][3];
                    temporal[c][4]= "M";
                }
                else{
                    c++;
                    temporal[c][0]= via[x][0];
                    temporal[c][1]= via[x][1];
                    temporal[c][2]= via[x][2];
                    temporal[c][3]= via[x][3];
                    temporal[c][4]= "U";
                }
            }
            
            temporal[x][0]= null;
            
            for(int i=0; temporal[i][0]!=null; i++){
                System.out.println("id:"+temporal[i][0]);
                System.out.println("nombre:"+temporal[i][1]);
                System.out.println("foto:"+temporal[i][2]);
                System.out.println("destino:"+temporal[i][3]);
                System.out.println("Cuantos:"+temporal[i][4]);
                System.out.println("-----------------------------------------");
            }

            for(int i=0; temporal[i][0]!=null; i++){
                if(i%3==0){
//                    System.out.println("a");
                    //[909, 900]
//////                    vista.pnlViajes.setPreferredSize(new Dimension(vista.pnlViajes.getWidth(), vista.pnlViajes.getHeight()+100));
//                    vista.pnlViajes.setSize(900, vista.pnlViajes.getHeight()+100);
////                    vista.pnlViajes.setLayout(new FlowLayout(FlowLayout.LEFT));
//                    vista.pnlViajes.setPreferredSize(vista.pnlViajes.getSize());
//                    vista.pnlViajes.setVerifyInputWhenFocusTarget(false);
//                    vista.scrollPerfil.setViewportView(vista.pnlViajes);
////                    vista.scrollPerfil.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                }
                //Panel principal: verde
                JPanel principal = new JPanel();
                principal.setLayout(new GridLayout( 2, 1, 0, 5));
                principal.setBackground(new java.awt.Color(156,255,87));
                principal.setSize(250,200);
       
                //Imagen
                ImageIcon image = new ImageIcon(getClass().getResource("../images/icons8-pais-100.png"));
                if(via[i][2] != null){
                    image = new ImageIcon(temporal[i][2]);
                }
                
                Icon fondo = new ImageIcon(image.getImage().getScaledInstance(250, 150, Image.SCALE_DEFAULT));
                btnImagen = new JButton(fondo);
                    //id del viaje
                btnImagen.setName("V"+temporal[i][4]+temporal[i][0]);
                
                    //Para hacerlo invisible
                btnImagen.setBorderPainted(false);
                btnImagen.setContentAreaFilled(false);
                btnImagen.setDefaultCapable(false);
                btnImagen.setFocusPainted(false);
                btnImagen.setFocusable(false);
                btnImagen.setSize(250, 150);
                    //Sin bordes
                btnImagen.setBorder(new EmptyBorder(5, 0, 0, 0));
                
                //Panel de información
                JPanel informacion = new JPanel();
                informacion.setLayout(new BorderLayout());
                informacion.setBackground(new java.awt.Color(156,255,87));
                    //Nombre del viaje
                JLabel nombreV = new JLabel(temporal[i][1]);
                nombreV.setFont(new Font("Candara", Font.PLAIN, 14));
                nombreV.setHorizontalAlignment(SwingConstants.CENTER);
                nombreV.setBorder(new EmptyBorder(10, 5, 0, 0));
                nombreV.setSize(250, 15);
                    //Destino del viaje
                
                JTextArea nombreDA = new JTextArea(temporal[i][3]);
                nombreDA.setFont(new Font("Candara", Font.PLAIN, 12));
                nombreDA.setBorder(new EmptyBorder(10, 5, 0, 0));
                nombreDA.setSize(250, 15); 
                nombreDA.setLineWrap(true);
                nombreDA.setWrapStyleWord(true);
                nombreDA.setOpaque(false);
                nombreDA.setEditable(false);
                nombreDA.setBackground(new java.awt.Color(156,255,87));
                nombreDA.setOpaque(true);
                
                        //añadir el jlabel al panel de información
                informacion.add(nombreV, BorderLayout.NORTH);
                informacion.add(nombreDA, BorderLayout.CENTER); 
                
                //Agregar la imagen y la información
                principal.add(btnImagen);
                principal.add(informacion);
                principal.setPreferredSize(new Dimension(250, 250));
                //Agregar el panel principal al scroll
                p.add(principal);
                //separación en blanco
                JPanel separacion = new JPanel();
                separacion.setBackground(new java.awt.Color(255,255,255));
                separacion.setSize(5,5);
                p.add(separacion);
                if(!pC){
                    btnImagen.addActionListener(this);
                    btnImagen.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    btnImagen.setToolTipText("Ver Viaje");
                }
            }
        }
        else
        {
            //Si no se encuentra un viaje
            String m;
            if(pC){
                m="El usuario no tiene viajes :( Pero, ¡tú puedes crear uno!";
            }
            else{
                m="¡Crea tu Primer Viaje!";
            }
            mensaje.setText(m);
            mensaje.setForeground(new Color(76,2,131));
            mensaje.setFont(new Font("Candara", Font.PLAIN, 20));
            mensaje.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            mensaje.addMouseListener(this);
            p.add(mensaje);
            p.setLayout(new FlowLayout(FlowLayout.CENTER));
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
        try{
            JComponent selectedButton = (JComponent) e.getSource();
            String letra = selectedButton.getName().substring(0, 2);
            System.out.println("cant: "+letra);
            String idV = selectedButton.getName().substring(2);  
        //Botón de Modificar Viaje de un destino
            if(letra.equals("VU")){
               vistaEditarViaje vistaEditarViaje = new vistaEditarViaje();
               modeloEditarViaje modeloEditarViaje = new modeloEditarViaje();
               controlEditarViaje controlEditarViaje= new controlEditarViaje(vistaEditarViaje, vistaPrincipal, modeloEditarViaje, idV);
               CambiaPanel cambiar = new CambiaPanel(vistaPrincipal.panelCambiante, vistaEditarViaje);
            }
            //Botón de Modificar Viaje de varios destinos
            if(letra.equals("VM")){
                vistaEditarViajeMN vistaEditarViaje = new vistaEditarViajeMN();
                modeloEditarViajeMN modeloEditarViaje = new modeloEditarViajeMN();
                controlEditarViajeMN controlEditarViaje= new controlEditarViajeMN(vistaEditarViaje, vistaPrincipal, modeloEditarViaje, idV);
                CambiaPanel cambiar = new CambiaPanel(vistaPrincipal.panelCambiante, vistaEditarViaje);
            }
        }
        catch(NullPointerException ex){}
        
        //Botón Modificar perfil 
        if(this.vista.btnEditarPerfil == e.getSource())
        {
            vistaEditarPerfil vistaEditarPerfil = new vistaEditarPerfil();
            modeloEditarPerfil modeloEditarPerfil = new modeloEditarPerfil();
            controlEditarPerfil controlEditarPerfil = new controlEditarPerfil(vistaEditarPerfil, vistaPrincipal, modeloEditarPerfil);
            CambiaPanel cambiar = new CambiaPanel(vistaPrincipal.panelCambiante, vistaEditarPerfil);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==mensaje){
            vistaNuevoViajeSS vistaNuevoViajeSS = new vistaNuevoViajeSS();
            modeloNuevoViajeSS modNuevoViajeSS = new modeloNuevoViajeSS();
            controlNuevoViajeSS controlNuevoViajeSS = new controlNuevoViajeSS(vistaNuevoViajeSS, vistaPrincipal, modNuevoViajeSS);
            CambiaPanel cambiar = new CambiaPanel(vistaPrincipal.panelCambiante, vistaNuevoViajeSS);
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
        if(e.getSource()==mensaje){
            mensaje.setForeground(new Color(156,77,204));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
         if(e.getSource()==mensaje){
            mensaje.setForeground(new Color(76,2,131));
        }
    }
    
}
