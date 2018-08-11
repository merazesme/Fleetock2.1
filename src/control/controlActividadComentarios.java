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
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import modelo.modeloActividadComentarios;
import vistas.vistaActividadComentarios;
import vistas.vistaPrincipal;

/**
 *
 * @author Holi
 */
public class controlActividadComentarios implements ActionListener, MouseListener{
    
    private vistaActividadComentarios vista;
    private modeloActividadComentarios modelo;
    private vistaPrincipal vPrincipal;
    
    private int cali=0, calificacion=0;
    boolean bandera;
    JButton btnImagen, imagen;
    //ID de la actividad y del destino
    private String idA, idD; 
    //Datos de las actividades
    String [] act;
       
    public controlActividadComentarios(vistaActividadComentarios vista, vistaPrincipal vPrincipal, modeloActividadComentarios modelo, String idA, String idD)
    {
        this.vista=vista;
        this.vPrincipal=vPrincipal;
        this.modelo=modelo;
        this.vista.enviar.addActionListener(this);
        this.vista.pesimo.addActionListener(this);
        this.vista.malo.addActionListener(this);
        this.vista.regular.addActionListener(this);
        this.vista.muybueno.addActionListener(this);
        this.vista.excelente.addActionListener(this);
        //id de la actividad
        this.idA=idA;
        //id del destino
        this.idD=idD;
        //carga los datos de la actividad
        datosA();
        this.vista.lblEstilo.addMouseListener(this);
        
        this.vista.lblEstilo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        mostrarcomentarios("SELECT comentarios.comentario, usuario.nombre, usuario.foto, actividad.idActividad, "
                + "comentarios.calificacion, comentarios.titulo, comentarios.fecha, comentarios.idComentarios, "
                + "usuario.idUsuario FROM usuario INNER JOIN comentarios "
                + "ON comentarios.Usuario_idUsuario = usuario.idUsuario INNER JOIN actividad on actividad.idActividad "
                + "= comentarios.Actividad_idActividad WHERE comentarios.Actividad_idActividad = " + idA);
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

    public void mostrarcomentarios(String sentencia)
    {      
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

                ImageIcon image = new ImageIcon(getClass().getResource("../images/icons8_Cat_Profile_50px_4.png"));
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
        vista.texto_Opinion.setText("");
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
        
        if(vista.enviar == e.getSource())
        {
            if(cali >=1 && bandera)
            {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(new Date());   
                System.out.println("La opinion:" + vista.texto_Opinion);
                System.out.println("La cali: " + cali);
                System.out.println("El usuario: " + controlPrincipal.usuario[0]);
                System.out.println("El id de la actividad: " + idA);
                System.out.println("El titulo: " + vista.texto_titulo);
                System.out.println("La fecha: "+ date);
                if(modelo.insertarComentarios(vista.texto_Opinion.getText(), cali, controlPrincipal.usuario[0], Integer.parseInt(idA), vista.texto_titulo.getText(), date))    
                {
                    JOptionPane.showMessageDialog(null, "Comentario agregado");
                    limpiar();
                    cali=0;
                    calificacion=0;
                    mostrarcomentarios("SELECT comentarios.comentario, usuario.nombre, usuario.foto, actividad.idActividad, "
                    + "comentarios.calificacion, comentarios.titulo, comentarios.fecha, comentarios.idComentarios, "
                    + "usuario.idUsuario FROM usuario INNER JOIN comentarios "
                    + "ON comentarios.Usuario_idUsuario = usuario.idUsuario INNER JOIN actividad on actividad.idActividad "
                    + "= comentarios.Actividad_idActividad WHERE comentarios.Actividad_idActividad = " + idA);
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
