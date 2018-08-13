/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistas.vistaDetallesDestino;
import vistas.vistaInicio;
import vistas.vistaPrincipal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import modelo.modeloAgregarViaje;
import modelo.modeloDetalleDestino;
import modelo.modeloInicio;
import vistas.vistaAgregarViaje;
/**
 *
 * @author Holi
 */
public class controlInicio implements ActionListener, KeyListener{
    
    private vistaInicio vista;
    private vistaPrincipal vPrincipal;
    private modeloInicio modelo;
    //Boton de la imagen del destino
    JButton btnImagen, btnNuevoViaje;
    
    public controlInicio(vistaInicio vista, vistaPrincipal vPrincipal, modeloInicio modelo)
    {
        this.vista = vista;
        this.vPrincipal=vPrincipal;
        this.modelo = modelo;
        this.vista.txtBusqueda.addKeyListener(this);  
        this.vista.btnSugerencias.addActionListener(this);
        this.vista.btnTendencias.addActionListener(this);
        principal();
    }
    
    public void activarPrincipal(boolean b){
        vista.pnlBusqueda.setVisible(!b);
        //label
        this.vista.jLabel11.setVisible(b);
        this.vista.jLabel3.setVisible(b);
        this.vista.jLabel12.setVisible(b);
        this.vista.jLabel18.setVisible(b);
        this.vista.jLabel19.setVisible(b);
        this.vista.jLabel20.setVisible(b);
        this.vista.jLabel21.setVisible(b);
        //scroll
        this.vista.scrollMontania1.setVisible(b);
        this.vista.scrollPlaya1.setVisible(b);
        this.vista.scrollCiudad1.setVisible(b);
        this.vista.scrollBosque1.setVisible(b);
        this.vista.scrollSelva1.setVisible(b);
        this.vista.scrollDesierto1.setVisible(b);
        this.vista.scrollManglar1.setVisible(b);
    }

    public void principal(){ 
        activarPrincipal(true);
         
        destinos(this.modelo.datosDestinos(1), vista.pnlPlaya1);
        destinos(this.modelo.datosDestinos(2), vista.pnlDesierto1);
        destinos(this.modelo.datosDestinos(3), vista.pnlBosque1);
        destinos(this.modelo.datosDestinos(4), vista.pnl_Montania1);
        destinos(this.modelo.datosDestinos(5), vista.pnlSelva1);
        destinos(this.modelo.datosDestinos(6), vista.pnlManglar1);
        destinos(this.modelo.datosDestinos(7), vista.pnlCiudad1);
        destinos(this.modelo.datosDestinos(8), vista.pnlVolcan1);
    }
    
    public void busquedas(String sentencia){
        activarPrincipal(false); 
        destinos(modelo.datosBusqueda(sentencia) , vista.pnlBusqueda);
        vista.pnlBusqueda.setBorder(new EmptyBorder(5, 40, 0, 0));
        vista.txtBusqueda.requestFocus();
    }
    
    public void destinos(String [][] des, JPanel p)
    {
        //limpia el panel
        p.removeAll();
        p.revalidate();
        p.repaint();
        p.setLayout(new FlowLayout(FlowLayout.LEFT));
        if(des.length > 0)
        {
            for(int i=0; i<des.length; i++)
            {
                
                //Panel principal: verde
                JPanel principal = new JPanel();
                principal.setLayout(new GridLayout( 2, 1, 0, 5));
                principal.setBackground(new java.awt.Color(156,255,87));
                principal.setSize(250,200);
        
                //Imagen
                ImageIcon image = new ImageIcon(getClass().getResource("../images/icons8-pais-100.png"));
                if(des[i][2] != null)
                {
                    image = new ImageIcon(des[i][2]);
                }
                
                Icon fondo = new ImageIcon(image.getImage().getScaledInstance(250, 150, Image.SCALE_DEFAULT));
                btnImagen = new JButton(fondo);
                //id del destino
                btnImagen.setName("D"+des[i][0]);
                
                //Para hacerlo invisible
                btnImagen.setBorderPainted(false);
                btnImagen.setContentAreaFilled(false);
                btnImagen.setDefaultCapable(false);
                btnImagen.setFocusPainted(false);
                btnImagen.setFocusable(false);
                btnImagen.setSize(250, 150);
                btnImagen.addActionListener(this);
                btnImagen.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnImagen.setToolTipText("Ver Destino");
                //Sin bordes
                btnImagen.setBorder(new EmptyBorder(5, 0, 0, 0));
                
                
                //Panel de información
                JPanel informacion = new JPanel(new BorderLayout(0, -70));
                informacion.setBackground(new java.awt.Color(156,255,87));
                
                //Nombre del destino
                JLabel nombreD = new JLabel(des[i][1]);
                nombreD.setFont(new Font("Candara", Font.PLAIN, 14));
                nombreD.setBorder(new EmptyBorder(10, 5, 0, 0));
                nombreD.setSize(250, 15);
                
                //Calificación
                JPanel cal = new JPanel();
                cal.setLayout(new BoxLayout(cal, BoxLayout.X_AXIS));
                cal.setBackground(new java.awt.Color(156,255,87));
                cal.setBorder(new EmptyBorder(10, 5, 0, 0));
                
                //si tiene calificación que cree los radiobutton              
                if(modelo.destinoCal(des[i][0]) != null)
                {
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
                    double c = Double.parseDouble(modelo.destinoCal(des[i][0]));
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
                else
                {
                    JLabel mensajec = new JLabel("¡Sé el primero en calificarlo!");
                    mensajec.setForeground(new Color(76,2,131));
                    mensajec.setFont(new Font("Candara", Font.PLAIN, 14));
                    mensajec.setHorizontalAlignment(SwingConstants.LEFT);
                    mensajec.setBorder(new EmptyBorder(10, 5, 0, 0));
                    cal.add(mensajec);
                }
                
                //Añadir Nuevo viaje
                //Imagen
                ImageIcon image2 = new ImageIcon(getClass().getResource("../images/icons8_Plus_Math_30px_2.png"));
                Icon fondo2 = new ImageIcon(image2.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
                btnNuevoViaje = new JButton(fondo2);
                
                //efecto del mouse
                ImageIcon image3 = new ImageIcon(getClass().getResource("../images/icons8_Plus_Math_32px.png"));
                Icon fondo3 = new ImageIcon(image3.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
                btnNuevoViaje.setRolloverIcon(fondo3);
                
                //id del destino
                btnNuevoViaje.setName("N"+des[i][0]);
                //Para hacerlo invisible
                btnNuevoViaje.setBorderPainted(false);
                btnNuevoViaje.setContentAreaFilled(false);
                btnNuevoViaje.setDefaultCapable(false);
                btnNuevoViaje.setFocusPainted(false);
                btnNuevoViaje.setFocusable(false);
                btnNuevoViaje.setSize(250, 150);
                btnNuevoViaje.addActionListener(this);
                btnNuevoViaje.setToolTipText("Nuevo Viaje");
                btnNuevoViaje.setCursor(new Cursor(Cursor.HAND_CURSOR));
                
                //añadir el jlabel al panel de información
                informacion.add(nombreD, BorderLayout.NORTH); 
                informacion.add(cal, BorderLayout.CENTER);
                informacion.add(btnNuevoViaje, BorderLayout.EAST);
              
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
            }
        }
        else
        {
            //Si no se encuentra destino
            JLabel mensaje = new JLabel("<html><p>No se ha encontrado nada :(</p></html>");
            mensaje.setForeground(new Color(76,2,131));
            mensaje.setFont(new Font("Candara", Font.PLAIN, 14));
            p.add(mensaje);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            JButton selectedButton = (JButton) e.getSource();
            String letra = selectedButton.getName().substring(0, 1);  
            String idD = selectedButton.getName().substring(1);  
        //Botón de detalles de destino
            if(letra.equals("D"))
            {
                vistaDetallesDestino vDetallesDestino = new vistaDetallesDestino();
                modeloDetalleDestino mDetallesDestino = new modeloDetalleDestino();
                controlDetalleDestino cDetalleDestino = new controlDetalleDestino(vDetallesDestino, vPrincipal, mDetallesDestino, idD);
                CambiaPanel cambiar = new CambiaPanel(vPrincipal.panelCambiante, vDetallesDestino);
            }
        //Botón de agregar a viaje    
            if(letra.equals("N")){
                vistaAgregarViaje vAgregarViaje = new vistaAgregarViaje();
                modeloAgregarViaje mAgregarViaje = new modeloAgregarViaje();
                controlAgregarViaje cAgregarViaje = new controlAgregarViaje(vAgregarViaje, vPrincipal, mAgregarViaje, idD);
                CambiaPanel cambiar = new CambiaPanel(vPrincipal.panelCambiante, vAgregarViaje);
            }
        }
        catch(NullPointerException ex){}

        if(vista.btnTendencias == e.getSource()){
            busquedas("select `idDestino`, `nombre`, `foto` from destino INNER JOIN pertenece on idDestino = Destino_idDestino GROUP BY nombre HAVING COUNT(*) > 1;");
        }
        
        if(vista.btnSugerencias == e.getSource()){
            busquedas("select destino.idDestino, destino.nombre, destino.foto "
                    + "FROM destino INNER JOIN sedivideen ON sedivideen.Destino_idDestino = idDestino "
                    + "INNER JOIN tipositio ON tipositio.idTipoSitio = sedivideen.TipoSitio_idTipoSitio "
                    + "INNER JOIN prefiere ON tipositio.idTipoSitio = prefiere.TipoSitio_idTipoSitio "
                    + "INNER JOIN usuario ON prefiere.Usuario_idUsuario = idUsuario "
                    + "WHERE usuario.idUsuario = " + controlPrincipal.usuario[2] + ";");
        }
    
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource() == vista.txtBusqueda){
            //si escribió algo
            if(!vista.txtBusqueda.getText().equals("")){
                busquedas("select `idDestino`, `nombre`, `foto` from destino where nombre like '%"+vista.txtBusqueda.getText()+"%';");
            } 
            else{
                principal();
            }
        }
    }
}
