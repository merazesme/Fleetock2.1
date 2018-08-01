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
import modelo.modeloAgregarViaje;
import modelo.modeloPerfil;
import vistas.vistaActividadComentarios;
import vistas.vistaAgregarViaje;
import vistas.vistaPerfil;
import vistas.vistaPrincipal;

/**
 *
 * @author Holi
 */
public class controlAgregarViaje implements ActionListener, PropertyChangeListener, KeyListener{

    private vistaAgregarViaje vista;
    private vistaPrincipal vPrincipal;
    private modeloAgregarViaje modelo;
    private String idD;
    //Todos los estilos de viaje
    private String [][] estiloViaje;
    private JButton btnImagen;
    
    //Declaración de un arraylist para los checkbox de la actividad
    List<JCheckBox> jnombre = new ArrayList<>();
    //Declaración de un arraylist para los checkbox de las actividades seleccionadas
    List<String> actSelec = new ArrayList<>();
    
    //formato yyyy-MM-dd
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    public controlAgregarViaje(vistaAgregarViaje vista, vistaPrincipal vPrincipal, modeloAgregarViaje modelo, String idD){
        this.vista=vista;
        this.modelo=modelo;
        this.vPrincipal=vPrincipal;
        this.idD = idD;
        inicio();
        
        vista.fechaInicio.addPropertyChangeListener(this);
        vista.fechaFin.addPropertyChangeListener(this);       
        vista.txtBusqueda.addKeyListener(this);
        vista.cmbEstiloViaje.addActionListener(this);
        vista.btnGuardar.addActionListener(this);
    }
    
    public void inicio(){
        String destino=modelo.datosDestinos(idD);
        if(destino!=null){
            //Nombre del viaje
            vista.txtNombreViaje.setText(vista.txtNombreViaje.getText()+destino);
            //Límites de fechas
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
           
            //Cargar actividades
            act(modelo.datosActividades("where tiene.Destino_idDestino = "+idD+" and posee.EstiloViaje_idEstiloViaje = "+estiloSeleccionado()+";"), vista.pnlActividades);
        }
    }
    
    public void act(String [][] act, JPanel p){
        //limpia el panel
        p.removeAll();
        p.revalidate();
        p.repaint();
        p.setLayout(new FlowLayout(FlowLayout.LEFT));
        if(act.length > 0){
            for (String[] act1 : act) {
                //Panel principal: verde
                JPanel principal = new JPanel();
                principal.setLayout(new GridLayout( 2, 1, 0, 5));
                principal.setBackground(new java.awt.Color(156,255,87));
                principal.setSize(250,200);
                principal.setPreferredSize(new Dimension(250, 250));
                //Botón con la Imagen
                    //imagen por defecto
                ImageIcon image = new ImageIcon(getClass().getResource("../images/icons8-billete-con-estrella-100.png"));
                if (act1[2] != null) {
                    image = new ImageIcon(act1[2]);
                }
                Icon fondo = new ImageIcon(image.getImage().getScaledInstance(250, 150, Image.SCALE_DEFAULT));
                btnImagen = new JButton(fondo);
               
                //Para hacer invisible el botón y sin bordes
                btnImagen.setBorderPainted(false);
                btnImagen.setContentAreaFilled(false);
                btnImagen.setDefaultCapable(false);
                btnImagen.setFocusPainted(false);
                btnImagen.setFocusable(false);
                btnImagen.setSize(250, 150);
                btnImagen.addActionListener(this);
                btnImagen.setBorder(new EmptyBorder(5, 0, 0, 0));
                
                //Panel de información: nombre de la actividad, calificación y checkbox
                JPanel informacion = new JPanel(new BorderLayout(0, -70));
                informacion.setBackground(new java.awt.Color(156,255,87));
               
                //Nombre de la actividad
                JLabel nombreA = new JLabel(act1[1]);
                nombreA.setFont(new Font("Candara", Font.PLAIN, 14));
                nombreA.setBorder(new EmptyBorder(10, 5, 0, 0));
                nombreA.setSize(250, 15);
                //id de la actividad
                btnImagen.setName("A" + act1[0]);
                btnImagen.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnImagen.setToolTipText("Ver Actividad");
                //Calificación
                JPanel cal = new JPanel();
                cal.setLayout(new BoxLayout(cal, BoxLayout.X_AXIS));
                cal.setBackground(new java.awt.Color(156,255,87));
                cal.setBorder(new EmptyBorder(10, 5, 0, 0));
                //si tiene calificación que cree los radiobutton
                String calN = modelo.actividadCal(act1[0]);
                if(calN != null){
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
                    double c = Double.parseDouble(calN);
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
                //checkbox para seleccionar actividad
                JCheckBox box = new JCheckBox();
                box.setName(act1[0]); //id actividad
                box.setBackground(new java.awt.Color(156,255,87));
                box.setBorder(new EmptyBorder(10, 0, 0, 10));
                box.addActionListener(this);
                //Para ver si ya se ha seleccionado una actividad
                for (int z = 0; z < actSelec.size(); z++) {
                    if (actSelec.get(z).equals(act1[0])) {
                        box.setSelected(true);
                    }
                }
                //se agrega al List
                jnombre.add(box);
                informacion.add(nombreA, BorderLayout.NORTH); 
                informacion.add(cal, BorderLayout.CENTER);
                informacion.add(box, BorderLayout.EAST);
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
        //combobox
        if(vista.cmbEstiloViaje==e.getSource()){
            act(modelo.datosActividades("where tiene.Destino_idDestino = "+idD+" and posee.EstiloViaje_idEstiloViaje ="+estiloSeleccionado()+";"), vista.pnlActividades);
        }
        
        //Boton de las actividades
        try{
            JComponent selectedButton = (JComponent) e.getSource();
            String letra = selectedButton.getName().substring(0, 1);  
            String idA = selectedButton.getName().substring(1);  
        //Botón de detalles de actividad
            if(letra.equals("A")){
                vistaActividadComentarios vActividadComentarios = new vistaActividadComentarios();
                modeloActividadComentarios mActividadComentarios = new modeloActividadComentarios();
                controlActividadComentarios comentarios = new controlActividadComentarios(vActividadComentarios, vPrincipal, mActividadComentarios, idA, idD);
                CambiaPanel cambiar = new CambiaPanel(vPrincipal.panelCambiante, vActividadComentarios);
            }
        }
        catch(NullPointerException ex){}
        
        //guardar viaje
        if(vista.btnGuardar == e.getSource()){
//            System.out.println("Nombre: " + vista.txtNombreViaje.getText());
//            System.out.println("Fecha inicio: "+vista.fechaInicio.getDate());
//            System.out.println("Fecha fin: "+vista.fechaFin.getDate());
            if(vista.txtNombreViaje.getText().equals("") || vista.fechaFin.getDate()==null || vista.fechaInicio.getDate()==null
                || actSelec.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "No se han acompletado todos los datos.", "¡Atención!", JOptionPane.ERROR_MESSAGE);
            }
            else{
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
                if(modelo.insertarViaje(vista.txtNombreViaje.getText(),sdf.format(vista.fechaInicio.getDate()), sdf.format(vista.fechaFin.getDate()), estado, estiloSeleccionado(), controlPrincipal.usuario[2], idD, actSelec)){
                    JOptionPane.showMessageDialog(null, "Su viaje se ha guardado con éxito", "Viaje guardado", JOptionPane.INFORMATION_MESSAGE);
                    vistaPerfil vistaPerfil = new vistaPerfil();
                    modeloPerfil modeloPerfil = new modeloPerfil();
                    controlPerfil controlPerfil = new controlPerfil(vistaPerfil, vPrincipal, modeloPerfil);
                    CambiaPanel cambiar = new CambiaPanel(vPrincipal.panelCambiante, vistaPerfil);
                }
                else{
                   JOptionPane.showMessageDialog(null, "No se ha podido guardar el viaje. Vuelve a intentarlo", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
        for (int i = 0; i < jnombre.size(); i++) {
            if (jnombre.get(i) == e.getSource()) {
                if(jnombre.get(i).isSelected()){
//                    System.out.println("id: " + jnombre.get(i).getName());
                    actSelec.add(jnombre.get(i).getName());
                }
                else{
                    for(int j = 0; j < actSelec.size(); j++){
                        if(jnombre.get(i).getName().equals(actSelec.get(j))){
                            actSelec.remove(j);
                        }
                    }
                }
            }            
        }
        
//        System.out.println("Arraylist Seleccionados:");
//        for(int p = 0; p < actSelec.size(); p++){
//            try{
//                System.out.println(actSelec.get(p));
//            }catch(IndexOutOfBoundsException event){
////                    System.out.println("j");
//            }
//        }
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
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    //busquedas
    @Override
    public void keyReleased(KeyEvent e) {
        //busqueda por letra
        if(e.getSource()==vista.txtBusqueda){
            //si está vacío, consulta general
            if(vista.txtBusqueda.getText().equals("")){
                //consulta normal por medio del combobox (estilo de viaje)
                act(modelo.datosActividades("where tiene.Destino_idDestino = "+idD+" and posee.EstiloViaje_idEstiloViaje = "+estiloSeleccionado()+";"), vista.pnlActividades);
            }
            else{
                //consulta dependiendo de lo que escriba y el combobox (estilo de viaje)
                act(modelo.datosActividades("WHERE actividad.nombre LIKE '%"+vista.txtBusqueda.getText()+"%' and tiene.Destino_idDestino = "+idD+" and posee.EstiloViaje_idEstiloViaje = "+estiloSeleccionado()+";"), vista.pnlActividades);
            }
        }
    }
    
}
