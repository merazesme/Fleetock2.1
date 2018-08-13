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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import modelo.modeloActividadComentarios;
import modelo.modeloNuevoViajeSS;
import modelo.modeloPerfil;
import vistas.vistaActividadComentarios;
import vistas.vistaNuevoViajeSS;
import vistas.vistaPerfil;
import vistas.vistaPrincipal;

/**
 *
 * @author Holi
 */
public class controlNuevoViajeSS implements ActionListener, PropertyChangeListener, ItemListener, KeyListener{

    private vistaNuevoViajeSS vista;
    private vistaPrincipal vistaPrincipal;
    private modeloNuevoViajeSS modelo;
       //Todos los estilos de viaje
    private String [][] estiloViaje;
    private JButton btnImagen;
    private String idD;
    
    //DESTINOS
    //Declaración de un arraylist para los checkbox 
    List<JCheckBox> dnombre = new ArrayList<>();
    //Declaración de un arraylist para los checkbox seleccionadas
    List<String> desSelec = new ArrayList<>();

    //ACTIVIDADES
    //Declaración de un arraylist para los checkbox de la actividad
    List<JCheckBox> anombre = new ArrayList<>();
    //Declaración de un arraylist para los checkbox de las actividades seleccionadas
    List<String> actSelec = new ArrayList<>();
    //Declaración de un arraylist para los checkbox de las actividades seleccionadas
    List<String> actSelecD = new ArrayList<>();
    
        //formato yyyy-MM-dd
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    public controlNuevoViajeSS(vistaNuevoViajeSS vista, vistaPrincipal vistaPrincipal, modeloNuevoViajeSS modelo)
    {
        this.vista=vista;
        this.vistaPrincipal=vistaPrincipal;
        this.modelo=modelo;
        this.vista.cmbEstiloViaje.addItemListener(this);
        vista.fechaInicio.addPropertyChangeListener(this);
        vista.fechaFin.addPropertyChangeListener(this); 
        vista.txtBusquedaA.addKeyListener(this);
        vista.txtBusquedaD.addKeyListener(this);
        vista.btnGuardar.addActionListener(this);
        inicio();
    }
    
    public void inicio(){
        //fecha de inicio a partir de hoy
        vista.fechaInicio.setMinSelectableDate(new Date());
            //fecha fin igual a partir de hoy
        vista.fechaFin.setMinSelectableDate(new Date());
        
        //Se llena la lista de estilo de viaje
        estiloViaje = modelo.datosEstiloViaje();
        if(estiloViaje.length > 0){
            vista.cmbEstiloViaje.removeAllItems();
            for (String[] estiloViaje1 : estiloViaje) {
                vista.cmbEstiloViaje.addItem(estiloViaje1[1]);
            }
        }
        else{
            vista.cmbEstiloViaje.addItem("No se ha encontrado ningún estilo :(");
        }
        destinos(modelo.datosDestinos(""), vista.pnlDestinos, "pais");
    }
    
    public void destinos(String [][] des, JPanel p, String m){
        //limpia el panel
        p.removeAll();
        p.revalidate();
        p.repaint();
        p.setLayout(new FlowLayout(FlowLayout.LEFT));
        if(des.length > 0){
            for(int i=0; i<des.length; i++){
                //Panel principal: verde
                JPanel principal = new JPanel();
                principal.setLayout(new GridLayout( 2, 1, 0, 5));
                principal.setBackground(new java.awt.Color(156,255,87));
                principal.setSize(250,200);
        
                //Imagen
                ImageIcon image = new ImageIcon(getClass().getResource("../images/icons8-"+m+"-100.png"));
                if(des[i][2] != null){
                    image = new ImageIcon(des[i][2]);
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
                btnImagen.setCursor(new Cursor(Cursor.HAND_CURSOR));
                
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
                
                //Calificación
                String nCalif = null;
                //checkbox para seleccionar actividad
                JCheckBox box = new JCheckBox();
                if(m.equals("pais")){
                        //id del destino
                    btnImagen.setName("D"+des[i][0]);
                    btnImagen.setToolTipText("Ver Destino");
                    nCalif=modelo.destinoCal(des[i][0]);
                    //Para ver si ya se ha seleccionado una actividad
                    for (int z = 0; z < desSelec.size(); z++) {
                        if (desSelec.get(z).equals(des[i][0])) {
                            box.setSelected(true);
                        }
                    }
                    //se agrega al List
                    dnombre.add(box);
                }
                else{
                        //id de la actividad
                    btnImagen.setName("A"+des[i][0]);
                    btnImagen.setToolTipText("Ver Actividad");
                    nCalif=modelo.actividadCal(des[i][0]);
                    //Para ver si ya se ha seleccionado una actividad
                    for (int z = 0; z < actSelec.size(); z++) {
                        if (actSelec.get(z).equals(des[i][0]) && actSelecD.get(z).equals(idD)) {
                            box.setSelected(true);
                        }
                    }
                    //se agrega al List
                    anombre.add(box);
                }
                    //si tiene calificación que cree los radiobutton              
                if(nCalif != null){
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
                    double c = Double.parseDouble(nCalif);
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
                
                box.setName(des[i][0]); //id actividad
                box.setBackground(new java.awt.Color(156,255,87));
                box.setBorder(new EmptyBorder(10, 0, 0, 10));
                box.addActionListener(this);
 
                        //añadir el jlabel al panel de información
                informacion.add(nombreD, BorderLayout.NORTH); 
                informacion.add(cal, BorderLayout.CENTER);
                informacion.add(box, BorderLayout.EAST);
              
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
    
        //te regresa la sentencia dependiendo del estilo seleccionado
    public String estiloSeleccionado(){
        for (String[] estiloViaje1 : estiloViaje) {
            if (estiloViaje1[1] == vista.cmbEstiloViaje.getSelectedItem()) {
                return estiloViaje1[0];
            }
        }
        return null;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            JComponent selectedButton = (JComponent) e.getSource();
            String letra = selectedButton.getName().substring(0, 1);  
            String id = selectedButton.getName().substring(1);
            System.out.println("id"+id);
            
            //Botón de ver actividades del destino
            if(letra.equals("D")){
                if(!id.equals("")){
                    idD=id;
                }
               destinos(modelo.datosActividades("where tiene.Destino_idDestino = "+id+" and posee.EstiloViaje_idEstiloViaje ="+estiloSeleccionado()+";"), vista.pnlActividades, "billete-con-estrella");
            }
            
            //Botón de detalles de actividad
            if(letra.equals("A")){
                vistaActividadComentarios vActividadComentarios = new vistaActividadComentarios();
                modeloActividadComentarios mActividadComentarios = new modeloActividadComentarios();
                controlActividadComentarios comentarios = new controlActividadComentarios(vActividadComentarios, vistaPrincipal, mActividadComentarios, id, idD);
                CambiaPanel cambiar = new CambiaPanel(vistaPrincipal.panelCambiante, vActividadComentarios);
            }
        }
        catch(NullPointerException ex){}   
        
        //Botón de guardar
        if(e.getSource() == vista.btnGuardar){
            if(vista.txtNombre.equals("") || vista.fechaInicio.getDate()==null || vista.fechaFin.getDate()==null 
                    || actSelec.isEmpty() || actSelecD.isEmpty() || desSelec.isEmpty()){
                JOptionPane.showMessageDialog(null, "No se han acompletado todos los datos.", "¡Atención!", JOptionPane.ERROR_MESSAGE);
            }
            else{                
                String a="";
                for(int j=0; j<desSelec.size(); j++){                
                    if(!actSelecD.contains(desSelec.get(j))){
                        a="No se han seleccionado actividades para un destino";
                    }
                }
                
                String b="";
                for(int j=0; j<actSelecD.size(); j++){                
                    if(!desSelec.contains(actSelecD.get(j))){
                        b="Has seleccionado actividades de un destino no seleccionado";
                    }
                }
                
                if(!a.equals("") || !b.equals("")){
                    JOptionPane.showMessageDialog(null, "<html><h3>¡Cuidado!</h3><p>"+a+"</p><p>"+b+"</p></html>", "Error", JOptionPane.ERROR_MESSAGE);
                } else{
                    //Fecha actual
                    Date fechaA = new Date();
                    String estado = "";
                    //Fecha inicio y Fecha fin después
                    if(vista.fechaInicio.getDate().after(fechaA) && vista.fechaFin.getDate().after(fechaA)){
                        estado = "Por Realizar";
                    }

                    //Fecha inicio y Fecha fin antes
                    if(vista.fechaInicio.getDate().before(fechaA) && vista.fechaFin.getDate().before(fechaA)){
                        estado = "Terminado";
                    }

                    //Fecha inicio antes y fecha fin después
                    if(vista.fechaInicio.getDate().before(fechaA) && vista.fechaFin.getDate().after(fechaA)){
                        estado = "En Curso";
                    }
                     //metodo para guardar
                    if(modelo.insertarViaje(vista.txtNombre.getText(),sdf.format(vista.fechaInicio.getDate()), 
                            sdf.format(vista.fechaFin.getDate()), estado, estiloSeleccionado(), controlPrincipal.usuario[2], 
                            desSelec, actSelec, actSelecD)){

                        JOptionPane.showMessageDialog(null, "Su viaje se ha guardado con éxito", "Viaje guardado", JOptionPane.INFORMATION_MESSAGE);
                        vistaPerfil vistaPerfil = new vistaPerfil();
                        modeloPerfil modeloPerfil = new modeloPerfil();
                        controlPerfil controlPerfil = new controlPerfil(vistaPerfil, vistaPrincipal, modeloPerfil, null);
                        CambiaPanel cambiar = new CambiaPanel(vistaPrincipal.panelCambiante, vistaPerfil);
                    }
                    else{
                       JOptionPane.showMessageDialog(null, "No se ha podido guardar el viaje. Vuelve a intentarlo", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        
                //Destinos
        for (int i = 0; i < dnombre.size(); i++) {
            //Actividades selecciondas
            if (dnombre.get(i) == e.getSource()) {
                if(dnombre.get(i).isSelected()){
//                    System.out.println("id: " + jnombre.get(i).getName());
                    desSelec.add(dnombre.get(i).getName());
                    idD=dnombre.get(i).getName();
                    destinos(modelo.datosActividades("where tiene.Destino_idDestino = "+idD+" and posee.EstiloViaje_idEstiloViaje ="+estiloSeleccionado()+";"), vista.pnlActividades, "billete-con-estrella");
                }
                //Actividades no seleccionadas
                else{
                    for(int j = 0; j < desSelec.size(); j++){
                        if(dnombre.get(i).getName().equals(desSelec.get(j))){
                            desSelec.remove(j);
                            vista.pnlActividades.removeAll();
                            vista.pnlActividades.revalidate();
                            vista.pnlActividades.repaint();
                        }
                    }
                }
            }            
        }
        
                //Actividades
        for (int i = 0; i < anombre.size(); i++) {
            //Actividades selecciondas
            if (anombre.get(i) == e.getSource()) {
                if(anombre.get(i).isSelected()){
                    System.out.println("idA: " + anombre.get(i).getName());
                    System.out.println("idD: " + idD);
                    actSelec.add(anombre.get(i).getName());
                    actSelecD.add(idD);
                }
                //Actividades no seleccionadas
                else{
                    for(int j = 0; j < actSelec.size(); j++){
                        if(anombre.get(i).getName().equals(actSelec.get(j))){
                            actSelec.remove(j);
                            actSelecD.remove(j);
                        }
                    }
                }
            }            
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //fecha limite de fecha fin dependiendo de la fecha de inicio
        if(evt.getSource() == this.vista.fechaInicio){
            //Especificas el formato de la fecha que coincide con el dado
            Calendar c = Calendar.getInstance();
            try{
               //Configuras la fecha con la fecha dada
               c.setTime(sdf.parse(sdf.format(vista.fechaInicio.getDate())));
            }catch(NullPointerException | ParseException e){}
            //se establece el limite de la fecha fin
            vista.fechaFin.setMinSelectableDate(c.getTime());
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        //Combobox
        if(e.getSource()==vista.cmbEstiloViaje && idD!=null){
            destinos(modelo.datosActividades("where tiene.Destino_idDestino = "+idD+" and posee.EstiloViaje_idEstiloViaje ="+estiloSeleccionado()+";"), vista.pnlActividades, "billete-con-estrella");
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
     //busqueda por letra
        if(e.getSource()==vista.txtBusquedaD){
            //si está vacío, consulta general
            if(vista.txtBusquedaD.getText().equals("")){
                //consulta normal por medio del combobox (estilo de viaje)
                 destinos(modelo.datosDestinos(""), vista.pnlDestinos, "pais");
            }
            else{
                //consulta dependiendo de lo que escriba y el combobox (estilo de viaje)
                destinos(modelo.datosDestinos("where destino.nombre LIKE '%"+vista.txtBusquedaD.getText()+"%'"), vista.pnlDestinos, "pais");
            }
        }
        
        if(e.getSource()==vista.txtBusquedaA){
            //si está vacío, consulta general
            if(vista.txtBusquedaA.getText().equals("")){
                //consulta normal por medio del combobox (estilo de viaje)
                destinos(modelo.datosActividades("where tiene.Destino_idDestino = "+idD+" and posee.EstiloViaje_idEstiloViaje ="+estiloSeleccionado()+";"), vista.pnlActividades, "billete-con-estrella");
            }
            else{
                //consulta dependiendo de lo que escriba y el combobox (estilo de viaje)
                destinos(modelo.datosActividades("where actividad.nombre LIKE '%"+vista.txtBusquedaA.getText()+"%' and tiene.Destino_idDestino = "+idD+" and posee.EstiloViaje_idEstiloViaje ="+estiloSeleccionado()+";"), vista.pnlActividades, "billete-con-estrella");
            }
        }
    }
}
