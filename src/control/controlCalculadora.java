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
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import modelo.modelocalculadora;
import vistas.vistaCalculadora;
import vistas.vistaPerfil;
import vistas.vistaPrincipal;

/**
 *
 * @author Holi
 */
public class controlCalculadora implements ActionListener
{

    vistaCalculadora vista;
    vistaPrincipal vistaPrincipal;
    modelocalculadora modelo;
    private int idDestino;
    JButton btnImagen, btnNuevoViaje;
    JButton btnImagen1, btnNuevoViaje1;
    JButton btnImagen2, btnNuevoViaje2;
    
    private String [] guardar;
    private double valor=0;
    private double valor2=0;
    private double resultado=0;
    private int obtener=0;
    private double resultadofinal=0;
    
    List<JCheckBox> nombres = new ArrayList<>();  
    //Declaración de un arraylist para los checkbox de las actividades seleccionadas
    List<String> select = new ArrayList<>();
    
    List<JCheckBox> transporte = new ArrayList<>();
    List<String> elegir = new ArrayList<>();
    
    boolean bandera;
    boolean bandera2;

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }
    
    public controlCalculadora(vistaCalculadora vista, vistaPrincipal vistaPrincipal, modelocalculadora modelo)
    {
        this.vista=vista;
        this.modelo=modelo;
        this.vistaPrincipal=vistaPrincipal;
        this.vista.calcular.addActionListener(this);
    }
    
    public void iniciarvista()
    {
        vista.setVisible(true);
        mostrardestinos("select idDestino, nombre, foto, 1 from destino;");
       
    }
    
    public void mostrardestinos(String sentencia)
    {
        vista.panel_destinos.removeAll();
        vista.panel_destinos.revalidate();
        vista.panel_destinos.repaint();
        vista.panel_destinos.setLayout(new FlowLayout(FlowLayout.LEFT));
        if(modelo.todas(sentencia).length > 0)
        {
            String [][] a = modelo.todas(sentencia);
            for(int i=0; i<a.length; i++)
            {
                //System.out.println("entro: " + a[i][1]);
                //Panel principal: verde
                JPanel principal = new JPanel();
                principal.setLayout(new GridLayout( 2, 1, 0, 5));
                principal.setBackground(new java.awt.Color(156,255,87));
                principal.setSize(250,200);
        
                //Imagen
                ImageIcon image = new ImageIcon(getClass().getResource("../images/icons8-pais-100.png"));
                if(a[i][2] != null)
                {
                    image = new ImageIcon(a[i][2]);
                }
                
                Icon fondo = new ImageIcon(image.getImage().getScaledInstance(250, 150, Image.SCALE_DEFAULT));
                btnImagen = new JButton(fondo);
                //id del destino
                btnImagen.setName("D"+a[i][0]);
                
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
                JLabel nombreD = new JLabel(a[i][1]);
                nombreD.setFont(new Font("Candara", Font.PLAIN, 14));
                nombreD.setBorder(new EmptyBorder(10, 5, 0, 0));
                nombreD.setSize(250, 15);
                
                //Calificación
                JPanel cal = new JPanel();
                cal.setLayout(new BoxLayout(cal, BoxLayout.X_AXIS));
                cal.setBackground(new java.awt.Color(156,255,87));
                cal.setBorder(new EmptyBorder(10, 5, 0, 0));
                
                //si tiene calificación que cree los radiobutton              
                if(modelo.destinoCal(a[i][0]) != null)
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
                    double c = Double.parseDouble(modelo.destinoCal(a[i][0]));
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
                btnNuevoViaje.setName("N"+a[i][0]);
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
                vista.panel_destinos.add(principal);
                
                //separación en blanco
                JPanel separacion = new JPanel();
                separacion.setBackground(new java.awt.Color(255,255,255));
                separacion.setSize(5,5);
                vista.panel_destinos.add(separacion);
            }
        }
        else
        {
            //Si no se encuentra destino
            JLabel mensaje = new JLabel("<html><p>No se ha encontrado nada :(</p></html>");
            mensaje.setForeground(new Color(76,2,131));
            mensaje.setFont(new Font("Candara", Font.PLAIN, 14));
            vista.panel_destinos.add(mensaje);
        }
    }
    
     public void mostraractividades(String sentencia)
    {
        bandera=false;
        nombres.clear();
        select.clear();
        vista.panel_actividades.removeAll();
        vista.panel_actividades.revalidate();
        vista.panel_actividades.repaint();
        vista.panel_actividades.setLayout(new FlowLayout(FlowLayout.LEFT));
        if(modelo.todas(sentencia).length > 0)
        {
            String [][] a = modelo.todas(sentencia);
            for(int i=0; i<a.length; i++)
            {
                //System.out.println("entro: " + a[i][1]);
                //Panel principal: verde
                JPanel principal = new JPanel();
                principal.setLayout(new GridLayout( 2, 1, 0, 5));
                principal.setBackground(new java.awt.Color(156,255,87));
                principal.setSize(250,200);
        
                //Imagen
                ImageIcon image = new ImageIcon(getClass().getResource("../images/icons8-pais-100.png"));
                if(a[i][2] != null)
                {
                    //foto de la actividad
                    image = new ImageIcon(a[i][2]);
                }
                
                Icon fondo = new ImageIcon(image.getImage().getScaledInstance(250, 150, Image.SCALE_DEFAULT));
                btnImagen1 = new JButton(fondo);
                //id de la actividad
                btnImagen1.setName("A"+a[i][0]);
                
                //Para hacerlo invisible
                btnImagen1.setBorderPainted(false);
                btnImagen1.setContentAreaFilled(false);
                btnImagen1.setDefaultCapable(false);
                btnImagen1.setFocusPainted(false);
                btnImagen1.setFocusable(false);
                btnImagen1.setSize(250, 150);
                btnImagen1.addActionListener(this);
                btnImagen1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnImagen1.setToolTipText("Ver Destino");
                //Sin bordes
                btnImagen1.setBorder(new EmptyBorder(5, 0, 0, 0));
                
                
                //Panel de información
                JPanel informacion = new JPanel(new BorderLayout(0, -70));
                informacion.setBackground(new java.awt.Color(156,255,87));
                
                //Nombre del destino
                //JLabel nombreD = new JLabel(a[i][1]);
                JLabel nombreD = new JLabel("<html>"+ a[i][1] +"<p>Precio: "+ a[i][3] +"</p></html>");
                nombreD.setFont(new Font("Candara", Font.PLAIN, 14));
                nombreD.setBorder(new EmptyBorder(10, 5, 0, 0));
                nombreD.setSize(250, 15);
                
                //Precio de la actividad
                //JLabel precio = new JLabel("Precio: " + a[i][3]);
                //precio.setBorder(new EmptyBorder(10, 5, 0, 0));
                
                //Calificación
                JPanel cal = new JPanel();
                cal.setLayout(new BoxLayout(cal, BoxLayout.X_AXIS));
                cal.setBackground(new java.awt.Color(156,255,87));
                cal.setBorder(new EmptyBorder(10, 5, 0, 0));                
                
                //si tiene calificación que cree los radiobutton              
                if(modelo.actividadCal(a[i][0]) != null)
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
                    double c = Double.parseDouble(modelo.actividadCal(a[i][0]));
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
                
                //Aaqui se crea los checkbox para las actividades
                JCheckBox check = new JCheckBox();
                check.setBackground(new java.awt.Color(156,255,87));
                check.setBorder(new EmptyBorder(10, 5, 0, 0)); 
                //check.setName(a[i][0] + a[i][3]);//id y precio de la actividad
                check.setName(a[i][3]);//id y precio de la actividad
                nombres.add(check);               
                
                //añadir el jlabel al panel de información
                informacion.add(nombreD, BorderLayout.NORTH); 
                informacion.add(cal, BorderLayout.CENTER);
                //informacion.add(btnNuevoViaje1, BorderLayout.EAST);
                informacion.add(check, BorderLayout.EAST);
                //informacion.add(precio, BorderLayout.NORTH);
              
                //Agregar la imagen y la información
                principal.add(btnImagen1);
                principal.add(informacion);
                principal.setPreferredSize(new Dimension(250, 250));
                //Agregar el panel principal al scroll
                check.addActionListener(this);
                vista.panel_actividades.add(principal);
                
                //separación en blanco
                JPanel separacion = new JPanel();
                separacion.setBackground(new java.awt.Color(255,255,255));
                separacion.setSize(5,5);
                vista.panel_actividades.add(separacion);
            }
        }
        else
        {
            //Si no se encuentra destino
            JLabel mensaje = new JLabel("<html><p>No se ha encontrado nada :(</p></html>");
            mensaje.setForeground(new Color(76,2,131));
            mensaje.setFont(new Font("Candara", Font.PLAIN, 14));
            vista.panel_actividades.add(mensaje);
        }
    }
    
     public void mostrartransportes(String sentencia)
    {
        bandera2=false;
        transporte.clear();
        elegir.clear();
        vista.panel_transportes.removeAll();
        vista.panel_transportes.revalidate();
        vista.panel_transportes.repaint();
        vista.panel_transportes.setLayout(new FlowLayout(FlowLayout.LEFT));
        if(modelo.todas(sentencia).length > 0)
        {
            String [][] a = modelo.todas(sentencia);
            for(int i=0; i<a.length; i++)
            {
                //System.out.println("entro: " + a[i][1]);
                //Panel principal: verde
                JPanel principal = new JPanel();
                principal.setLayout(new GridLayout( 2, 1, 0, 5));
                principal.setBackground(new java.awt.Color(156,255,87));
                principal.setSize(250,200);
        
                //Imagen
                ImageIcon image = new ImageIcon(getClass().getResource("../images/icons8-pais-100.png"));
                if(a[i][2] != null)
                {
                    image = new ImageIcon(a[i][2]);
                }
                
                Icon fondo = new ImageIcon(image.getImage().getScaledInstance(250, 150, Image.SCALE_DEFAULT));
                btnImagen2 = new JButton(fondo);
                //id del destino
                btnImagen2.setName("T"+a[i][0]);
                
                //Para hacerlo invisible
                btnImagen2.setBorderPainted(false);
                btnImagen2.setContentAreaFilled(false);
                btnImagen2.setDefaultCapable(false);
                btnImagen2.setFocusPainted(false);
                btnImagen2.setFocusable(false);
                btnImagen2.setSize(250, 150);
                btnImagen2.addActionListener(this);
                btnImagen2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnImagen2.setToolTipText("Ver Destino");
                //Sin bordes
                btnImagen2.setBorder(new EmptyBorder(5, 0, 0, 0));
                
                
                //Panel de información
                JPanel informacion = new JPanel(new BorderLayout(0, -70));
                informacion.setBackground(new java.awt.Color(156,255,87));
                
                //Nombre del destino
               // JLabel nombreD = new JLabel(a[i][1]);
                JLabel nombreD = new JLabel("<html>"+ a[i][1] +"<p>Precio: "+ a[i][3] +"</p></html>");
                nombreD.setFont(new Font("Candara", Font.PLAIN, 14));
                nombreD.setBorder(new EmptyBorder(10, 5, 0, 0));
                nombreD.setSize(250, 15);
                
                //Calificación
                JPanel cal = new JPanel();
                cal.setLayout(new BoxLayout(cal, BoxLayout.X_AXIS));
                cal.setBackground(new java.awt.Color(156,255,87));
                cal.setBorder(new EmptyBorder(10, 5, 0, 0));
                
                //si tiene calificación que cree los radiobutton              
                if(modelo.actividadCal(a[i][0]) != null)
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
                    double c = Double.parseDouble(modelo.actividadCal(a[i][0]));
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
                
                //Aaqui se crea los checkbox para las actividades
                JCheckBox check = new JCheckBox();
                check.setBackground(new java.awt.Color(156,255,87));
                check.setBorder(new EmptyBorder(10, 5, 0, 0)); 
                check.setName(a[i][3]);//id actividad
                transporte.add(check);               
                
                //añadir el jlabel al panel de información
                informacion.add(nombreD, BorderLayout.NORTH); 
                informacion.add(cal, BorderLayout.CENTER);
                //informacion.add(btnNuevoViaje1, BorderLayout.EAST);
                informacion.add(check, BorderLayout.EAST);
              
                //Agregar la imagen y la información
                principal.add(btnImagen2);
                principal.add(informacion);
                principal.setPreferredSize(new Dimension(250, 250));
                //Agregar el panel principal al scroll
                check.addActionListener(this);
                vista.panel_transportes.add(principal);
                
                //separación en blanco
                JPanel separacion = new JPanel();
                separacion.setBackground(new java.awt.Color(255,255,255));
                separacion.setSize(5,5);
                vista.panel_transportes.add(separacion);
            }
        }
        else
        {
            //Si no se encuentra destino
            JLabel mensaje = new JLabel("<html><p>No se ha encontrado nada :(</p></html>");
            mensaje.setForeground(new Color(76,2,131));
            mensaje.setFont(new Font("Candara", Font.PLAIN, 14));
            vista.panel_transportes.add(mensaje);
        }
    }
     
     
    public static boolean isNumeric(String str)
    {
      try
      {
        double d = Integer.parseInt(str);
      }
      catch(NumberFormatException nfe)
      {
        return false;
      }
      return true;
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(vista.calcular == e.getSource())
        {
            resultado=0;
            valor=0;
            valor2=0;
            if(bandera && bandera2)
            {              
                   resultado=0;
                   valor=0;
                   valor2=0;
                   obtener=0;
                  // obtener = (int) vista.spinner.setValue(obtener);
//                    System.out.println("desde aqui comienza");
                    for(int p = 0; p < select.size(); p++)
                    {
                        try
                        {
                            //System.out.println("Los precios de las actividades son: "+ select.get(p));
                            //valor=valor+250;
                            String i = select.get(p);
                            double f = Integer.parseInt(i);
                            valor = valor + f;
                           // System.out.println("valor: "+ valor);
                        }catch(IndexOutOfBoundsException event)
                        {
                            System.out.println("j");
                        }
                    }
                    for(int p = 0; p < elegir.size(); p++)
                    {
                        try
                        {
//                          System.out.println("Las transporte son: "+ elegir.get(p));
                            //valor2=valor2+250;
                            String k = elegir.get(p);
                            double c = Double.parseDouble(k);
                            valor2 = valor2 + c;
                            //System.out.println("valor2: "+ valor2);
                        }catch(IndexOutOfBoundsException event)
                        {
                            System.out.println("j");
                        }
                    }
//                    System.out.println("el valor 1: " + valor);
//                    System.out.println("el valor 2: " + valor2);
                    resultado=valor+valor2;
                    obtener = (int) vista.spinner.getValue();
                    obtener*=500;
                    //JOptionPane.showMessageDialog(null, "Los dias son: "+obtener);
                    resultadofinal=resultado+obtener;
                    System.out.println("El resutado de los transportes y actividades es: " + resultado);
                    System.out.println("el resultado final es: " + resultadofinal);
                    if(resultadofinal == 0 && obtener == 0)
                    {
                        JOptionPane.showMessageDialog(null, "Selecciona al menos una actividad, transporte y dia", "¡Atención!", JOptionPane.ERROR_MESSAGE);                       
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Calculo finalizado");    
                        vista.presupuesto.setText(""+resultadofinal);
                        if(resultadofinal >=500 && resultadofinal <= 2000)
                        {
                            vista.estilo.setText("Mochilero");
                        }
                        else if(resultadofinal >=2001 && resultadofinal <= 5000)
                        {
                            vista.estilo.setText("Promedio");
                        }
                        else
                        {
                            vista.estilo.setText("Lujoso");
                        }                                   
                    }
//                vista.spinner.setValue(0);
//                vista.panel_actividades.removeAll();
//                vista.panel_transportes.removeAll();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Selecciona al menos una actividad y transporte", "¡Atención!", JOptionPane.ERROR_MESSAGE);
            }                      
        }
        
        try
        {
            JComponent selectedButton = (JComponent) e.getSource();
            String letra = selectedButton.getName().substring(0, 1);  
            String idD = selectedButton.getName().substring(1);                      
            if(isNumeric(idD))
            {   
                this.idDestino = Integer.parseInt(idD);                      
                if(letra.equals("D"))
                {                  
                    System.out.println("el id es: " + idDestino);
                    vista.presupuesto.setText("");
                    vista.estilo.setText("");
                    vista.spinner.setValue(0);
                    mostraractividades("SELECT actividad.idActividad, actividad.nombre, tiene.foto, posee.costo "
                            + "FROM actividad "
                            + "INNER JOIN tiene ON tiene.Actividad_idActividad = actividad.idActividad "
                            + "INNER JOIN posee ON posee.tiene_idTiene = tiene.idTiene "
                            + "WHERE tiene.Destino_idDestino = " + idDestino); 
                    if(letra.equals("A"))
                    {
                        System.out.println("detalles"); 
                    }
                    mostrartransportes("SELECT transporte.idTransporte, transporte.tipo, transporte.foto, corresponde.costo "
                            + "FROM transporte "
                            + "INNER JOIN sedesplazaen on sedesplazaen.Transporte_idTransporte = transporte.idTransporte "
                            + "INNER JOIN corresponde ON corresponde.sedesplazaen_idSeDesplazaEn = sedesplazaen.idSeDesplazaEn "
                            + "WHERE sedesplazaen.Destino_idDestino = " + idDestino);
                    if(letra.equals("T"))
                    {
                        System.out.println("Detalles");
                    }
                }
                if(letra.equals("N"))
                {                  
                    System.out.println("el id es: " + idDestino);
                    vista.presupuesto.setText("");
                    vista.estilo.setText("");
                    vista.spinner.setValue(0);
                     mostraractividades("SELECT actividad.idActividad, actividad.nombre, tiene.foto, posee.costo "
                            + "FROM actividad "
                            + "INNER JOIN tiene ON tiene.Actividad_idActividad = actividad.idActividad "
                            + "INNER JOIN posee ON posee.tiene_idTiene = tiene.idTiene "
                            + "WHERE tiene.Destino_idDestino = " + idDestino); 
                    if(letra.equals("A"))
                    {
                        System.out.println("detalles"); 
                    }
                    mostrartransportes("SELECT transporte.idTransporte, transporte.tipo, transporte.foto, corresponde.costo "
                            + "FROM transporte "
                            + "INNER JOIN sedesplazaen on sedesplazaen.Transporte_idTransporte = transporte.idTransporte "
                            + "INNER JOIN corresponde ON corresponde.sedesplazaen_idSeDesplazaEn = sedesplazaen.idSeDesplazaEn "
                            + "WHERE sedesplazaen.Destino_idDestino = " + idDestino);
                    if(letra.equals("T"))
                    {
                        System.out.println("Detalles");
                    }
                }
            }
        }
        catch(NullPointerException ex){}
        
        //Esto es para los checkbox de las actividades
        for (int i = 0; i < nombres.size(); i++) 
        {
            if (nombres.get(i) == e.getSource()) 
            {                              
                if(nombres.get(i).isSelected())
                {
                    bandera=true;
                    select.add(nombres.get(i).getName());
                    System.out.println("se seleccionó el: " + nombres.get(i).getName());
                }
                else
                {
                    for(int j = 0; j < select.size(); j++)
                    {
                        System.out.println("No se seleccionó el: " + nombres.get(i).getName());
                        if(nombres.get(i).getName().equals(select.get(j)))
                        {
                            select.remove(j);
                        }
                    }                   
                }
            }
        }
        System.out.println("");
        System.out.println("-------------------------------------------");
        for(int p = 0; p < select.size(); p++){
            try{
                System.out.println("Las actividades son: "+ select.get(p));
            }catch(IndexOutOfBoundsException event){
                System.out.println("j");
            }
        }
        //System.out.println("ESTO ES PARA LOS TRANSPORTES");
        //Esto es para los checkbox de los transporte
        for (int i = 0; i < transporte.size(); i++) 
        {
            if (transporte.get(i) == e.getSource()) 
            {                              
                if(transporte.get(i).isSelected())
                {
                    bandera2=true;
                    elegir.add(transporte.get(i).getName());
                    System.out.println("se seleccionó el: " + transporte.get(i).getName());
                }
                else
                {
                    for(int j = 0; j < elegir.size(); j++)
                    {
                        System.out.println("No se seleccionó el: " + transporte.get(i).getName());
                        if(transporte.get(i).getName().equals(elegir.get(j)))
                        {
                            elegir.remove(j);
                        }
                    }                   
                }
            }
        }
        System.out.println("");
        System.out.println("-------------------------------------------");
        for(int p = 0; p < elegir.size(); p++){
            try{
                System.out.println("Las transporte son: "+ elegir.get(p));
            }catch(IndexOutOfBoundsException event){
                System.out.println("j");
            }
        }
        
    }
    
}
