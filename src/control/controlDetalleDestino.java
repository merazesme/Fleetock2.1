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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private int cali=0;
    private int calificacion=0;
    boolean bandera;
    public static String [] usuario;
    JButton btnImagen, imagen;    
    
    public controlDetalleDestino(vistaDetallesDestino vista, vistaPrincipal vistaPrincipal, modeloDetalleDestino modelo, String idD, String [] usuario)
    {
        this.vista=vista;
        this.modelo=modelo;
        this.vistaPrincipal=vistaPrincipal;
        this.idD = idD;
        this.usuario = usuario;
        this.vista.btnActividades.addActionListener(this);
        this.vista.btnViaje.addActionListener(this);
        this.vista.boton_enviar.addActionListener(this);
        this.vista.pesimo.addActionListener(this);
        this.vista.malo.addActionListener(this);
        this.vista.regular.addActionListener(this);
        this.vista.muybueno.addActionListener(this);
        this.vista.excelente.addActionListener(this);
        imagenN();
       
        actTrans(modelo.datosTransportes(this.idD), vista.pnlTransportes, "autobus");
        actTrans(modelo.datosActividades(this.idD), vista.pnlActividades, "museo");
        mostrarcomentarios("SELECT comentarios.comentario, usuario.nombre, usuario.foto, destino.idDestino, "
                + "comentarios.calificacion, comentarios.titulo, comentarios.fecha, comentarios.idComentarios, "
                + "usuario.idUsuario FROM usuario INNER JOIN comentarios "
                + "ON comentarios.Usuario_idUsuario = usuario.idUsuario INNER JOIN destino on destino.idDestino "
                + "= comentarios.Destino_idDestino WHERE comentarios.Destino_idDestino = " + idD);
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
            for(int i=0; i<act.length; i++)
            {
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
                EmptyBorder border1 = new EmptyBorder(5, 0, 0, 0);
                btnImagen.setBorder(border1);
                
                
                //Panel de información
                JPanel informacion = new JPanel(new BorderLayout(0, -70));
                informacion.setBackground(new java.awt.Color(156,255,87));
                //Nombre del destino
                JLabel nombreD = new JLabel(act[i][1]);
                nombreD.setFont(new Font("Candara", Font.PLAIN, 14));
                EmptyBorder border2 = new EmptyBorder(10, 5, 0, 0);
                nombreD.setBorder(border2);
                nombreD.setSize(250, 15);
                if(img.equals("museo"))
                {
                    //id de la actividad
                    btnImagen.setName("A"+act[i][0]);
                    btnImagen.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    btnImagen.setToolTipText("Ver Actividad");
                    
                     //Calificación
                    JPanel cal = new JPanel();
                    cal.setLayout(new BoxLayout(cal, BoxLayout.X_AXIS));
                    cal.setBackground(new java.awt.Color(156,255,87));
                    cal.setBorder(border2);

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
                        mensajec.setBorder(border2);
                        cal.add(mensajec);
                    }
                    informacion.add(nombreD, BorderLayout.NORTH); 
                    informacion.add(cal, BorderLayout.CENTER);
                }
                else
                {
                     informacion.add(nombreD, BorderLayout.NORTH); 
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
    
    public void mostrarcomentarios(String sentencia)
    {
        GridBagConstraints g1 = new GridBagConstraints();
        GridBagConstraints g2 = new GridBagConstraints();
        GridBagConstraints g3 = new GridBagConstraints();
        GridBagConstraints g4 = new GridBagConstraints();
        GridBagConstraints g5 = new GridBagConstraints();
        GridBagConstraints g6 = new GridBagConstraints();
        
        vista.panel_comentarios.removeAll();
        vista.panel_comentarios.revalidate();
        vista.panel_comentarios.repaint();
        
        vista.panel_comentarios.setLayout(new BoxLayout(vista.panel_comentarios, BoxLayout.Y_AXIS));
        
         if(modelo.Comentarios(sentencia).length > 0)
         {
            String [][] a = modelo.Comentarios(sentencia);
            for(int i=0; i<a.length; i++)
            {
                //Este es el panel principal donde va todo
                JPanel principal = new JPanel();
                //principal.setLayout(new GridLayout( 2, 6, 0, 5));
                principal.setLayout(new BorderLayout());
                principal.setBackground(new java.awt.Color(240,240,240));
                principal.setPreferredSize(new Dimension(390, 195));

                ImageIcon image = new ImageIcon(getClass().getResource("../images/sin-foto-perfil.jpg"));
                //ImageIcon image = new ImageIcon(getClass().getResource("C:\\Users\\alfredo\\Documents\\GitHub\\Fleetock\\Fleetock2.1\\src\\images\\sin-foto-perfil.jpg"));               
                if(a[i][2] != null)
                {
                    //se le pasa la foto
                    image = new ImageIcon(a[i][2]);
                }
                
                Icon fondo = new ImageIcon(image.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
                imagen = new JButton(fondo);
                //id del destino
                imagen.setName("u"+a[i][8]);
                
                //Para hacerlo invisible
                imagen.setBorderPainted(false);
                imagen.setContentAreaFilled(false);
                imagen.setDefaultCapable(false);
                imagen.setFocusPainted(false);
                imagen.setFocusable(false);
                imagen.setSize(250, 150);
                imagen.addActionListener(this);
                imagen.setCursor(new Cursor(Cursor.HAND_CURSOR));
                imagen.setToolTipText("Ver Perfil");
                //Sin bordes
                imagen.setBorder(new EmptyBorder(20, 20, 0, 0));
                
                //panel donde ira la imagen del usuario
                JPanel ima = new JPanel();
                //ima.setBackground(new java.awt.Color(111,111,111));
                ima.setBackground(new java.awt.Color(240,240,240));
                ima.add(imagen);
                principal.add(ima, BorderLayout.WEST);
                
                
                //panel donde ira el titulo, calificacion y comentario
                JPanel titulo = new JPanel();
                //titulo.setBackground(new java.awt.Color(102,110,100));
                titulo.setBackground(new java.awt.Color(240,240,240));
                titulo.setLayout(new BoxLayout(titulo, BoxLayout.Y_AXIS));
                
                //Aqui va el titulo
                JLabel ti = new JLabel(a[i][5]);
                ti.setBorder(new EmptyBorder(20, 10, 0, 0));
                ti.setAlignmentX(JLabel.LEFT_ALIGNMENT);
                titulo.add(ti);
                
                //panel donde ira la calificacion
                JPanel calificacion = new JPanel();
                
                //calificacion.setBackground(new java.awt.Color(156,255,87));
                calificacion.setBackground(new java.awt.Color(240,240,240));
                calificacion.setLayout(new BoxLayout(calificacion, BoxLayout.X_AXIS));
                calificacion.setAlignmentX(JPanel.LEFT_ALIGNMENT);
                calificacion.setBorder(new EmptyBorder(5, 10, 0, 0));
                if(modelo.ComentariosCalificacion(a[i][7]) != null)
                {
                    JRadioButton r1 = new JRadioButton();
                    JRadioButton r2 = new JRadioButton();
                    JRadioButton r3 = new JRadioButton();
                    JRadioButton r4 = new JRadioButton();
                    JRadioButton r5 = new JRadioButton();
                   
                    r1.setBackground(new java.awt.Color(240,240,240));
                    r2.setBackground(new java.awt.Color(240,240,240));
                    r3.setBackground(new java.awt.Color(240,240,240));
                    r4.setBackground(new java.awt.Color(240,240,240));
                    r5.setBackground(new java.awt.Color(240,240,240));

                    r1.setEnabled(false);
                    r2.setEnabled(false);
                    r3.setEnabled(false);
                    r4.setEnabled(false);
                    r5.setEnabled(false);

                    calificacion.add(r1);
                    calificacion.add(r2);
                    calificacion.add(r3);
                    calificacion.add(r4);
                    calificacion.add(r5);
                    
                    //Rellenar los radiobutton
                    double c = Double.parseDouble(modelo.ComentariosCalificacion(a[i][7]));
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
                    calificacion.add(mensajec);
                }
                //Aqui se agrega la calificacion
                titulo.add(calificacion);
               
                
               //panel donde ira el texto de los comentarios                
                JLabel te = new JLabel(a[i][0]);//los comentarios
                te.setAlignmentX(JLabel.LEFT_ALIGNMENT);
                te.setBorder(new EmptyBorder(5, 10, 0, 0));
                titulo.add(te);
                
                principal.add(titulo, BorderLayout.CENTER);  
                
                //panel donde ira la fecha
                JPanel fecha = new JPanel();
                //fecha.setBackground(new java.awt.Color(153,153,153));
                fecha.setBackground(new java.awt.Color(240,240,240));
                //La fecha
                JLabel fe = new JLabel(a[i][6]);
                fe.setBorder(new EmptyBorder(40, 0, 0, 0));
                fecha.add(fe);
                principal.add(fecha, BorderLayout.EAST);

                vista.panel_comentarios.add(principal);
                
                 //separación en blanco
                JPanel separacion = new JPanel();
                separacion.setBackground(new java.awt.Color(255,255,255));
                separacion.setSize(5,5);
                vista.panel_comentarios.add(separacion);
            }
        }
        else
        {
            //Si no se encuentra 
            JLabel mensaje = new JLabel("<html><p>No se ha encontrado nada :(</p></html>");
            mensaje.setForeground(new Color(76,2,131));
            mensaje.setFont(new Font("Candara", Font.PLAIN, 14));
            vista.panel_comentarios.add(mensaje);
        }
    }
    
    public void limpiar()
    {
        vista.texto_opinion.setText("");
        vista.texto_titulo.setText("");
        vista.pesimo.setSelected(true);
        vista.malo.setSelected(true);
        vista.regular.setSelected(true);
        vista.muybueno.setSelected(true);
        vista.excelente.setSelected(true);

    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {                   
        if(vista.pesimo == e.getSource())
        {           
            cali=1;
            bandera=true;            
            vista.pesimo.setSelected(true);
            vista.malo.setSelected(false);
            vista.regular.setSelected(false);
            vista.muybueno.setSelected(false);
            vista.excelente.setSelected(false);
        }
        if(vista.malo == e.getSource())
        {
            cali=2;
            bandera=true;          
            vista.pesimo.setSelected(true);
            vista.malo.setSelected(true);
            vista.regular.setSelected(false);
            vista.muybueno.setSelected(false);
            vista.excelente.setSelected(false);
        }
        if(vista.regular == e.getSource())
        {
            cali=3;
            bandera=true;
            vista.pesimo.setSelected(true);
            vista.malo.setSelected(true);
            vista.regular.setSelected(true);
            vista.muybueno.setSelected(false);
            vista.excelente.setSelected(false);
        }
        if(vista.muybueno == e.getSource())
        {
            cali=4;
            bandera=true;
            vista.pesimo.setSelected(true);
            vista.malo.setSelected(true);
            vista.regular.setSelected(true);
            vista.muybueno.setSelected(true);
            vista.excelente.setSelected(false);
        }
        if(vista.excelente == e.getSource())
        {
            cali=5;
            bandera=true;            
            vista.pesimo.setSelected(true);
            vista.malo.setSelected(true);
            vista.regular.setSelected(true);
            vista.muybueno.setSelected(true);
            vista.excelente.setSelected(true);
        }      
        System.out.println("cali : " + cali);
        if(vista.boton_enviar == e.getSource())
        {
            if(cali >=1 && bandera)
            {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(new Date());             
                if(modelo.insertarComentarios(vista.texto_opinion.getText(), cali, usuario[0], Integer.parseInt(idD), vista.texto_titulo.getText(), date))    
                {
                    JOptionPane.showMessageDialog(null, "Comentario agregado");
                    limpiar();
                    cali=0;
                    calificacion=0;
                    mostrarcomentarios("SELECT comentarios.comentario, usuario.nombre, usuario.foto, destino.idDestino, "
                    + "comentarios.calificacion, comentarios.titulo, comentarios.fecha, comentarios.idComentarios, "
                    + "usuario.idUsuario FROM usuario "
                            + "INNER JOIN comentarios "
                    + "ON comentarios.Usuario_idUsuario = usuario.idUsuario "
                            + "INNER JOIN destino on destino.idDestino "
                    + "= comentarios.Destino_idDestino WHERE comentarios.Destino_idDestino = " + idD);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Selecciona una calificacion", "¡Atención!", JOptionPane.ERROR_MESSAGE);
                 limpiar();
                cali=0;
                vista.pesimo.setSelected(false);
                vista.malo.setSelected(false);
                vista.regular.setSelected(false);
                vista.muybueno.setSelected(false);
                vista.excelente.setSelected(false);
            }
        }
     
        try
        {    
            JComponent selectedButton = (JComponent) e.getSource();
            String letra = selectedButton.getName().substring(0, 1);  
            String idA = selectedButton.getName().substring(1);
            //Botón de detalles de actividad
            if(letra.equals("A")){
                System.out.println("entra");
                vistaActividadComentarios vActividadComentarios = new vistaActividadComentarios();
                modeloActividadComentarios mActividadComentarios = new modeloActividadComentarios();
                controlActividadComentarios comentarios = new controlActividadComentarios(vActividadComentarios, vistaPrincipal, mActividadComentarios, idA,usuario);
                CambiaPanel cambiar = new CambiaPanel(vistaPrincipal.panelCambiante, vActividadComentarios);
            }
        }
        catch(NullPointerException ex){}
        try
        {           
            JComponent selectedButton1 = (JComponent) e.getSource();
            String letra1 = selectedButton1.getName().substring(0, 1);  
            String idU = selectedButton1.getName().substring(1);
            //Botón de detalles de actividad
            if(letra1.equals("u")){
                System.out.println("Entra al perfil del usuario: " + idU);
            }
        }
        catch(NullPointerException ex){}
   
    
        
    if(this.vista.btnActividades == e.getSource())
    {
        
        vistaActividades vActividades = new vistaActividades();
        modeloActividades mActividades = new modeloActividades();
        controlActividades cActividades = new controlActividades(vActividades, vistaPrincipal, mActividades, idD, usuario);
        CambiaPanel cambiar = new CambiaPanel(vistaPrincipal.panelCambiante, vActividades);
    }
    
    if(this.vista.btnViaje == e.getSource())
    {
        vistaAgregarViaje vAgregarViaje = new vistaAgregarViaje();
        controlAgregarViaje cAgregarViaje = new controlAgregarViaje(vAgregarViaje, vistaPrincipal, idD);
        CambiaPanel cambiar = new CambiaPanel(vistaPrincipal.panelCambiante, vAgregarViaje);
        
    }

    }
    
}
