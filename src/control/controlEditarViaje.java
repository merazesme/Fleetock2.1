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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import modelo.modeloActividadComentarios;
import modelo.modeloActividades;
import modelo.modeloPerfil;
import vistas.vistaActividadComentarios;
import vistas.vistaActividades;
import modelo.modeloEditarViaje;
import vistas.vistaEditarViaje;
import vistas.vistaPerfil;
import vistas.vistaPrincipal;

/**
 *
 * @author Holi
 */
public class controlEditarViaje implements ActionListener, PropertyChangeListener, KeyListener{

    private vistaEditarViaje vista;
    private vistaPrincipal vistaPrincipal;
    private modeloEditarViaje modelo;
    //ID del viaje
    public static String idV;
    //Datos del viaje
    private String [] uDatosV;
    //estado del viaje
    private String estado = "";
    //Todos los estilos de viaje
    private String [][] estiloViaje;
    private JButton btnImagen;
    //Actividad Seleccionada
    private String actS = null;
    
    //ACTIVIDADES PENDIENTES
        //Declaración de un arraylist para los checkbox de la actividad 
    private List<JCheckBox> actP = new ArrayList<>();
    //ACTIVIDADES REALIZADAS
        //Declaración de un arraylist para los checkbox de la actividad
    private List<JCheckBox> actR = new ArrayList<>();
        //TODAS LAS ACTIVIDADES SELECCIONADAS PARA EL VIAJE
    private List<String> actSS = new ArrayList<>();
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    public controlEditarViaje(vistaEditarViaje vista, vistaPrincipal vistaPrincipal, modeloEditarViaje modelo, String idV)
    {
        this.vista=vista;
        this.vistaPrincipal=vistaPrincipal;
        this.modelo=modelo;
        this.idV=idV;
        datosViaje();
        this.vista.cmbEstiloViaje.addActionListener(this);
        this.vista.fechaInicio.addPropertyChangeListener(this);
        this.vista.fechaFin.addPropertyChangeListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnGuardar.addActionListener(this);
        this.vista.txtBusquedaAP.addKeyListener(this);
        this.vista.txtBusquedaAR.addKeyListener(this);
        this.vista.txtFechaActividad.addPropertyChangeListener(this);
        this.vista.btnActividades.addActionListener(this);
        //Actividades 
        act(vista.pnlActividadesP,modelo.actividadesViaje(this.idV,uDatosV[6]," and contiene.fechaActividad IS NULL"));      
        act(vista.pnlActividadesR,modelo.actividadesViaje(this.idV,uDatosV[6]," and NOT contiene.fechaActividad IS NULL"));    
    }
    
    //datos iniciales del viaje
    public void datosViaje(){
        uDatosV=modelo.datosViajes(idV);
        System.out.println("idV: "+idV);
        if(uDatosV!=null){
            vista.txtNombre.setText(uDatosV[0]);
            vista.txtDescripcion.setText(uDatosV[1]);
            try {
                vista.fechaInicio.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(uDatosV[2]));
                vista.fechaFin.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(uDatosV[3]));
            } catch (ParseException ex) {
                vista.lblEstado.setText(uDatosV[4]);
                JOptionPane.showMessageDialog(null, "No se pudo cargar las fechas.", "¡Atención!", JOptionPane.ERROR_MESSAGE);
            }
            vista.lblDestino.setText(uDatosV[7]);

            //Se llena la lista de estilo de viaje
            estiloViaje = modelo.datosEstiloViaje();
            if(estiloViaje.length > 0){
                vista.cmbEstiloViaje.removeAllItems();
                for(int i=0; i<estiloViaje.length; i++){
                    vista.cmbEstiloViaje.addItem(estiloViaje[i][1]);
                }
                vista.cmbEstiloViaje.setSelectedItem(uDatosV[5]);
            }
            else{
                JOptionPane.showMessageDialog(null, "No se pudo cargar el estilo de viaje.", "¡Atención!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    //carga las actividades en los paneles
    public void act(JPanel p, String [][] act){
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
                ImageIcon image = new ImageIcon(getClass().getResource("../images/icons8-billete-con-estrella-100.png"));
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
                JPanel informacion = new JPanel(new BorderLayout(0, -50));
                informacion.setBackground(new java.awt.Color(156,255,87));
                    //Nombre del destino
                JLabel nombreD = new JLabel(act[i][1], SwingConstants.CENTER);
                nombreD.setFont(new Font("Candara", Font.PLAIN, 14));
                nombreD.setBorder(new EmptyBorder(10, 5, 0, 0));
                nombreD.setSize(250, 15);
                nombreD.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                //id de la actividad
                btnImagen.setName("A"+act[i][0]);
                btnImagen.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnImagen.setToolTipText("Ver Actividad");
                
                    //checkbox para seleccionar actividad
                JCheckBox box = new JCheckBox();
                box.setName(act[i][0]);//id actividad
                box.setBackground(new java.awt.Color(156,255,87));
                box.setBorder(new EmptyBorder(10, 5, 0, 0));
                box.addActionListener(this);
                
                    //Nombre del check box
                JLabel nombreCB = new JLabel();
                nombreCB.setFont(new Font("Candara", Font.PLAIN, 12));
                nombreCB.setBorder(new EmptyBorder(10, 5, 0, 0));
                nombreCB.setSize(250, 15);
                
                //Actividades pendientes
                if(act[i][3]==null){
                    if(actP.isEmpty()){
                        p.removeAll();
                        p.revalidate();
                        p.repaint();
                        p.setLayout(new FlowLayout(FlowLayout.LEFT));
                    }
                        //se agrega al List
                    actP.add(box);
                    actSS.add(act[i][0]);
                    
                    nombreCB.setText("Marcar como realizada");
                    informacion.add(nombreD, BorderLayout.NORTH); 
                    informacion.add(nombreCB, BorderLayout.CENTER);
                    informacion.add(box, BorderLayout.WEST);
                }
                else{
                    if(actR.isEmpty()){
                        p.removeAll();
                        p.revalidate();
                        p.repaint();
                        p.setLayout(new FlowLayout(FlowLayout.LEFT));
                    }
                        //se agrega al List
                    actR.add(box);
                    actSS.add(act[i][0]);

                    nombreCB.setText("Marcar como pendientes");
                    String[] parts = act[i][3].split("-");
                    JLabel fecha = new JLabel(parts[2]+"-"+parts[1]+"-"+parts[0], SwingConstants.CENTER);
                    fecha.setFont(new Font("Candara", Font.PLAIN, 12));
                    fecha.setBorder(new EmptyBorder(5, 5, 10, 0));
                    fecha.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                    
                    //panel de la fecha y del nombre
                    JPanel fn = new JPanel();
                    fn.setLayout(new BoxLayout(fn, BoxLayout.Y_AXIS));
                    fn.setBackground(new java.awt.Color(156,255,87));
                    fn.add(nombreD);
                    fn.add(fecha);
                    
                    informacion.add(fn, BorderLayout.NORTH); 
                    informacion.add(nombreCB, BorderLayout.CENTER);
                    informacion.add(box, BorderLayout.WEST);
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
        else{
            JLabel mensaje = new JLabel("<html><p>No tienes actividades :(</p></html>");
            mensaje.setForeground(new Color(76,2,131));
            mensaje.setFont(new Font("Candara", Font.PLAIN, 14));
            p.add(mensaje);
        }
    }
    
    //id del estilo de viaje seleccionado
    public String estiloSeleccionado(){
        for(int y=0; y<estiloViaje.length; y++){
            if(estiloViaje[y][1]==vista.cmbEstiloViaje.getSelectedItem()){
                return estiloViaje[y][0];
            }
        }
        return null;
    }
    
    //carga las actividades por realizar o realizadas
    public void actividades(String idA, String fecha){
        if(modelo.actualizarActividad(idV, idA, fecha)){
            actS=null;
            actP.clear();
            actSS.clear();
            actR.clear();
            vista.txtBusquedaAP.setText("");
            vista.txtBusquedaAR.setText("");
            vista.txtFechaActividad.setDate(null);
            vista.txtFechaActividad.setEnabled(false);
            vista.lblFechaI.setEnabled(false);
            act(vista.pnlActividadesP,modelo.actividadesViaje(this.idV,uDatosV[6]," and contiene.fechaActividad IS NULL"));      
            act(vista.pnlActividadesR,modelo.actividadesViaje(this.idV,uDatosV[6]," and NOT contiene.fechaActividad IS NULL"));
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {   
        
        //botón de más actividades
        if(this.vista.btnActividades == e.getSource())
        {
            System.out.println("id"+uDatosV[6]);
            vistaActividades vActividades = new vistaActividades();
            modeloActividades mActividades = new modeloActividades();
            controlActividades cActividades = new controlActividades(vActividades, vistaPrincipal, mActividades, uDatosV[6], null, actSS);
            CambiaPanel cambiar = new CambiaPanel(vistaPrincipal.panelCambiante, vActividades);
        }
        
        //botón de guardar
        if(e.getSource() == vista.btnGuardar){
            if(vista.txtNombre.getText().equals("") || vista.txtDescripcion.getText().equals("") || 
                    vista.fechaFin.getDate().equals("") || vista.fechaInicio.getDate().equals("")){
                JOptionPane.showMessageDialog(null, "No se han acompletado todos los datos.", "¡Atención!", JOptionPane.ERROR_MESSAGE); 
            }
            else{
                if(modelo.actualizarViaje(idV, vista.txtNombre.getText(), vista.txtDescripcion.getText(), estado, estiloSeleccionado(), sdf.format(vista.fechaInicio.getDate()), sdf.format(vista.fechaFin.getDate()))){
                    JOptionPane.showMessageDialog(null, "Su viaje se ha actualizado con éxito", "Viaje guardado", JOptionPane.INFORMATION_MESSAGE);
                    vistaPerfil vistaPerfil = new vistaPerfil();
                    modeloPerfil modeloPerfil = new modeloPerfil();
                    controlPerfil controlPerfil = new controlPerfil(vistaPerfil, vistaPrincipal, modeloPerfil, null);
                    CambiaPanel cambiar = new CambiaPanel(vistaPrincipal.panelCambiante, vistaPerfil);
                }
                else{
                   JOptionPane.showMessageDialog(null, "No se ha podido actualizar el viaje. Vuelve a intentarlo", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
        //botón eliminar
        if(e.getSource()==vista.btnEliminar){
            String[] options = {"Sí", "No"};
            int x = JOptionPane.showOptionDialog(null, "¿Estás seguro que deseas eliminar el viaje?","¡Atención!",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if (x == JOptionPane.YES_OPTION){
                if(modelo.viajeEliminar(idV)){
                    JOptionPane.showMessageDialog(null, "El viaje se ha eliminado.", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                    vistaPerfil vistaPerfil = new vistaPerfil();
                    modeloPerfil modeloPerfil = new modeloPerfil();
                    controlPerfil controlPerfil = new controlPerfil(vistaPerfil, vistaPrincipal, modeloPerfil, null);
                    CambiaPanel cambiar = new CambiaPanel(vistaPrincipal.panelCambiante, vistaPerfil);
                }
                else{
                    JOptionPane.showMessageDialog(null, "El viaje no se ha eliminado.", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
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
                controlActividadComentarios comentarios = new controlActividadComentarios(vActividadComentarios, vistaPrincipal, mActividadComentarios, idA, uDatosV[6]);
                CambiaPanel cambiar = new CambiaPanel(vistaPrincipal.panelCambiante, vActividadComentarios);
            }
        }
        catch(NullPointerException ex){}
        
        //Actividades Pendientes
        for (int i = 0; i < actP.size(); i++) {
            if(actP.get(i) == e.getSource() && actP.get(i).isSelected()){
                vista.txtFechaActividad.setEnabled(true);
                vista.lblFechaI.setEnabled(true);
                actS="P"+actP.get(i).getName();
                System.out.println("b");
            }
            else{
                //deseleccionar los demás
                actP.get(i).setSelected(false);
            }
        }
        
        //Actividades Realizadas
        for (int i = 0; i < actR.size(); i++) {
            if (actR.get(i) == e.getSource() && actR.get(i).isSelected() ) {
                actS=actR.get(i).getName();
                if(actS!=null){
                    actividades(actS, "NULL");
                }
            }            
        }
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //poner limite a fecha fin
        if(evt.getSource()==vista.fechaInicio){
            vista.fechaFin.setMinSelectableDate(vista.fechaInicio.getDate());
        }
        
        //cambiar el estado del viaje
        if(evt.getSource() == vista.fechaInicio || evt.getSource() == vista.fechaFin){
            //Fecha actual
            Date fechaF = new Date();           
            //Fecha inicio y Fecha fin después
            if(vista.fechaInicio.getDate().after(fechaF) && vista.fechaFin.getDate().after(fechaF)){
                estado = "Por Realizar";
            }

            //Fecha inicio y Fecha fin antes
            if(vista.fechaInicio.getDate().before(fechaF) && vista.fechaFin.getDate().before(fechaF)){
                estado = "Terminado";
            }

            //Fecha inicio antes y fecha fin después
            if(vista.fechaInicio.getDate().before(fechaF) && vista.fechaFin.getDate().after(fechaF)){
                estado = "En Curso";
            }
            
            vista.lblEstado.setText(estado);
            vista.txtFechaActividad.setMinSelectableDate(vista.fechaInicio.getDate());
            vista.txtFechaActividad.setMaxSelectableDate(vista.fechaFin.getDate());
        }
        
        if(evt.getSource()==vista.txtFechaActividad){
            try{
                String letra = actS.substring(0, 1);  
                String idA = actS.substring(1);  
                if(actS!=null && letra.equals("P")){
                    actividades(idA, "'"+sdf.format(vista.txtFechaActividad.getDate())+"'");
                }
            }catch(NullPointerException ex){}
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
        if(!vista.txtBusquedaAP.getText().equals("")){
            act(vista.pnlActividadesP,modelo.actividadesViaje(this.idV, uDatosV[6]," and contiene.fechaActividad IS NULL and actividad.nombre LIKE '%"+vista.txtBusquedaAP.getText()+"%'"));      
            vista.txtBusquedaAP.requestFocus();
        } 
        else{
           act(vista.pnlActividadesP,modelo.actividadesViaje(this.idV,uDatosV[6]," and contiene.fechaActividad IS NULL"));      
        }
        
        if(!vista.txtBusquedaAR.getText().equals("")){
            act(vista.pnlActividadesR,modelo.actividadesViaje(this.idV,uDatosV[6]," and NOT contiene.fechaActividad IS NULL and actividad.nombre LIKE '%"+vista.txtBusquedaAR.getText()+"%'"));      
            vista.txtBusquedaAR.requestFocus();
        } 
        else{
           act(vista.pnlActividadesR,modelo.actividadesViaje(this.idV,uDatosV[6]," and NOT contiene.fechaActividad IS NULL"));      
        }
    }
   
    
}
