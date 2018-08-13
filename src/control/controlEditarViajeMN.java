/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import static control.controlEditarViaje.idV;
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
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import modelo.modeloActividadComentarios;
import modelo.modeloActividades;
import modelo.modeloEditarViajeMN;
import modelo.modeloPerfil;
import vistas.vistaActividadComentarios;
import vistas.vistaActividades;
import vistas.vistaEditarViajeMN;
import vistas.vistaPerfil;
import vistas.vistaPrincipal;

/**
 *
 * @author ITZEL
 */
public class controlEditarViajeMN implements ActionListener, PropertyChangeListener, KeyListener{
    private vistaEditarViajeMN vista;
    private vistaPrincipal vistaPrincipal;
    private modeloEditarViajeMN modelo;
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
    private String actS = null, idD=null;
    
    //DESTINOS
    //Declaración de un arraylist para los checkbox 
    List<JCheckBox> dnombre = new ArrayList<>();
    //Declaración de un arraylist para los checkbox seleccionados
    List<String> desSelec = new ArrayList<>();
    //Antiguos - Declaración de un arraylist para los checkbox seleccionados
    List<String> desSelecA = new ArrayList<>();
    
    //ACTIVIDADES PENDIENTES
        //Declaración de un arraylist para los checkbox de la actividad 
    private List<JCheckBox> actP = new ArrayList<>();
    //ACTIVIDADES REALIZADAS
        //Declaración de un arraylist para los checkbox de la actividad
    private List<JCheckBox> actR = new ArrayList<>();
        //TODAS LAS ACTIVIDADES SELECCIONADAS PARA EL VIAJE
    private List<String> actSS = new ArrayList<>();
    
            //formato yyyy-MM-dd
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    public controlEditarViajeMN(vistaEditarViajeMN vista, vistaPrincipal vistaPrincipal, modeloEditarViajeMN modelo, String idV)
    {
        this.vista=vista;
        this.vistaPrincipal=vistaPrincipal;
        this.modelo=modelo;
        this.idV=idV;
        System.out.println("idV"+idV);
        datosViaje();
        this.vista.cmbEstiloViaje.addActionListener(this);
        this.vista.fechaInicio.addPropertyChangeListener(this);
        this.vista.fechaFin.addPropertyChangeListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnGuardar.addActionListener(this);
        this.vista.txtBusquedaA.addKeyListener(this);
        vista.txtBusquedaD.addKeyListener(this);
        this.vista.fechaActividad.addPropertyChangeListener(this);
        this.vista.btnActividades.addActionListener(this);
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
            
            String [] a;
            a=modelo.destinosViaje(idV);
            for(int i=0; i<a.length; i++){
                desSelec.add(a[i]);
                desSelecA.add(a[i]);
            }
            
            destinos(modelo.datosDestinos(""), vista.pnlDestinos, "pais");
        }
    }
    
    public void destinos(String [][] des, JPanel p, String m){
        //limpia el panel
        p.removeAll();
        p.revalidate();
        p.repaint();
        p.setLayout(new FlowLayout(FlowLayout.LEFT));
        String me="";
        if(m.equals("pais")){
            me="<html><p>No se encontraron destinos :(</p></html>";
        }else{
            me="<html><p>No se encontraron actividades :(</p></html>";
        }
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
                JPanel informacion = new JPanel(new BorderLayout(0, -50));
                informacion.setBackground(new java.awt.Color(156,255,87));
                    //Nombre del destino
                JLabel nombreD = new JLabel(des[i][1]);
                nombreD.setFont(new Font("Candara", Font.PLAIN, 14));
                nombreD.setBorder(new EmptyBorder(10, 5, 0, 0));
                nombreD.setSize(250, 15);
                nombreD.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                
                //checkbox para seleccionar actividad
                JCheckBox box = new JCheckBox();
                box.setName(des[i][0]); //id actividad
                box.setBackground(new java.awt.Color(156,255,87));
                box.setBorder(new EmptyBorder(10, 5, 0, 10));
                box.addActionListener(this);
                
                if(m.equals("pais")){
                        //id del destino
                    btnImagen.setName("D"+des[i][0]);
                    btnImagen.setToolTipText("Ver Destino");
                    //Para ver si ya se ha seleccionado una actividad
                    for (int z = 0; z < desSelec.size(); z++) {
                        if (desSelec.get(z).equals(des[i][0])) {
                            box.setSelected(true);
                        }
                    }
                    //se agrega al List
                    dnombre.add(box);
                        //añadir el jlabel al panel de información
                    informacion.add(nombreD, BorderLayout.NORTH); 
                    informacion.add(box, BorderLayout.EAST);
                }
                //Cuando es actividad
                else{
                        //id de la actividad
                    btnImagen.setName("A"+des[i][0]);
                    btnImagen.setToolTipText("Ver Actividad");
                         //Nombre del check box
                    JLabel nombreCB = new JLabel();
                    nombreCB.setFont(new Font("Candara", Font.PLAIN, 12));
                    nombreCB.setBorder(new EmptyBorder(10, 5, 0, 0));
                    nombreCB.setSize(250, 15);
                    nombreCB.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                    
                    //Actividades pendientes
                    if(des[i][3]==null){
                        if(actP.isEmpty()){
                            p.removeAll();
                            p.revalidate();
                            p.repaint();
                            p.setLayout(new FlowLayout(FlowLayout.LEFT));
                        }
                            //se agrega al List
                        actP.add(box);
                        actSS.add(des[i][0]);
                        
                        nombreD.setHorizontalAlignment(SwingConstants.CENTER);
                        nombreCB.setText("Marcar como realizada");
                        informacion.add(nombreD, BorderLayout.NORTH); 
                        informacion.add(nombreCB, BorderLayout.CENTER);
                        informacion.add(box, BorderLayout.WEST);
                    }
                    //Actividades realizadas
                    else{
                        if(actR.isEmpty()){
                            p.removeAll();
                            p.revalidate();
                            p.repaint();
                            p.setLayout(new FlowLayout(FlowLayout.LEFT));
                        }
                            //se agrega al List
                        actR.add(box);
                        actSS.add(des[i][0]);

                        nombreCB.setText("Marcar como pendientes");
                        String[] parts = des[i][3].split("-");
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
                } 
              
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
            JLabel mensaje = new JLabel(me);
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
    
    public boolean comparacionSelec(String id){        
        System.out.println("Comparación");
        for(int i = 0; i < desSelecA.size(); i++){
            System.out.println("ID SelecA: " + desSelecA.get(i));
            System.out.println("ID Selec: " + id);
            if(desSelecA.get(i).equals(id)){
                return true;
            }
        }
        return false;
    }
    
    public boolean comparacionSelecA(String id){        
        System.out.println("Comparación");
        for(int i = 0; i < desSelec.size(); i++){
            System.out.println("ID Selec: " + desSelec.get(i));
            System.out.println("ID SelecA: " + id);
            if(desSelec.get(i).equals(id)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //boton de más actividades
        if(this.vista.btnActividades == e.getSource())
        {
            System.out.println("id"+idD);
            if(idD==null){
                JOptionPane.showMessageDialog(null, "Selecciona un destino", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                vistaActividades vActividades = new vistaActividades();
                modeloActividades mActividades = new modeloActividades();
                controlActividades cActividades = new controlActividades(vActividades, vistaPrincipal, mActividades, idD, idV, actSS);
                CambiaPanel cambiar = new CambiaPanel(vistaPrincipal.panelCambiante, vActividades);
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
        
        //botón de guardar
        if(e.getSource() == vista.btnGuardar){
            if(vista.txtNombre.getText().equals("") || vista.txtDescripcion.getText().equals("") || 
                    vista.fechaFin.getDate().equals("") || vista.fechaInicio.getDate().equals("") ||
                    desSelec.isEmpty()){
                JOptionPane.showMessageDialog(null, "No se han acompletado todos los datos.", "¡Atención!", JOptionPane.ERROR_MESSAGE); 
            }
            else{
                String a="";
                String [] b = modelo.actRV(idV);
                for(int j=0; j<desSelec.size(); j++){                
                    if(!desSelec.contains(b[j])){
                        a="Has seleccionado actividades de un destino no seleccionado";
                    }
                }
                
                if(!a.equals("")){
                    JOptionPane.showMessageDialog(null, "<html><h3>¡Cuidado!</h3><p>"+a+"</p></html>", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    boolean ban1=false, ban2=false;
                    for(int i=0; i<desSelec.size(); i++){
                        if(!comparacionSelec(desSelec.get(i))){
                            System.out.println("AGREGAR Nuevo ID: "+desSelec.get(i));
                            if(!modelo.insertarDestino(desSelec.get(i), idV)){
                                ban1=true;
                            }
                        }
                        System.out.println("-------------------------------------------------");
                    }

                     System.out.println("********************************************");

                     //Actividades que estaban seleccionadas antes, pero ya no
                    for(int i=0; i<desSelecA.size(); i++){
                        if(!comparacionSelecA(desSelecA.get(i))){
                            if(modelo.eliminarDestino(desSelecA.get(i), idV)){
                                ban2=true;
                            }
                            System.out.println("ELIMINAR Seleccionado antes pero no ahora ID: "+desSelecA.get(i));
                        }
                        System.out.println("-------------------------------------------------");
                    }

                    if(ban1 && ban2){
                        JOptionPane.showMessageDialog(null, "No se ha podido guardar los destinos del viaje :(", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        System.out.println("aa");
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
            }
        }
        
        try{
            JComponent selectedButton = (JComponent) e.getSource();
            String letra = selectedButton.getName().substring(0, 1);  
            String id = selectedButton.getName().substring(1);
            System.out.println("id"+id);
            if(!id.equals("")){
                idD=id;
            }
            //Botón de ver actividades del destino
            if(letra.equals("D")){
                actS=null;
                actP.clear();
                actSS.clear();
                actR.clear();
                destinos(modelo.actividadesViaje(this.idV, idD," and contiene.fechaActividad IS NULL"), vista.pnlActividadesP, "billete-con-estrella");      
                destinos(modelo.actividadesViaje(this.idV, idD," and NOT contiene.fechaActividad IS NULL"), vista.pnlActividadesR, "billete-con-estrella");
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
        
        for (int i = 0; i < dnombre.size(); i++) {
            //Destino selecciondo
            if (dnombre.get(i) == e.getSource()) {
                if(dnombre.get(i).isSelected()){
//                    System.out.println("id: " + jnombre.get(i).getName());
                    desSelec.add(dnombre.get(i).getName());
                    idD=dnombre.get(i).getName();
                    actS=null;
                    actP.clear();
                    actSS.clear();
                    actR.clear();
                    destinos(modelo.actividadesViaje(this.idV, idD," and contiene.fechaActividad IS NULL"), vista.pnlActividadesP, "billete-con-estrella");      
                    destinos(modelo.actividadesViaje(this.idV, idD," and NOT contiene.fechaActividad IS NULL"), vista.pnlActividadesR, "billete-con-estrella");
                }
                //Actividades no seleccionadas
                else{
                    for(int j = 0; j < desSelec.size(); j++){
                        if(dnombre.get(i).getName().equals(desSelec.get(j))){
                            desSelec.remove(j);
                            vista.pnlActividadesR.removeAll();
                            vista.pnlActividadesR.revalidate();
                            vista.pnlActividadesR.repaint();
                        }
                    }
                }
            }            
        }
        
        //Actividades Pendientes
        for (int i = 0; i < actP.size(); i++) {
            if(actP.get(i) == e.getSource() && actP.get(i).isSelected()){
                vista.fechaActividad.setEnabled(true);
                vista.lblFecha.setEnabled(true);
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
    
        //carga las actividades por realizar o realizadas
    public void actividades(String idA, String fecha){
        if(modelo.actualizarActividad(idV, idA, fecha)){
            actS=null;
            actP.clear();
            actSS.clear();
            actR.clear();
            vista.txtBusquedaA.setText("");
            vista.txtBusquedaD.setText("");
            vista.fechaActividad.setDate(null);
            vista.fechaActividad.setEnabled(false);
            vista.lblFecha.setEnabled(false);
            destinos(modelo.actividadesViaje(this.idV, idD," and contiene.fechaActividad IS NULL"), vista.pnlActividadesP, "billete-con-estrella");      
            destinos(modelo.actividadesViaje(this.idV, idD," and NOT contiene.fechaActividad IS NULL"), vista.pnlActividadesR, "billete-con-estrella");
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
            vista.fechaActividad.setMinSelectableDate(vista.fechaInicio.getDate());
            vista.fechaActividad.setMaxSelectableDate(vista.fechaFin.getDate());
        }
        
        if(evt.getSource()==vista.fechaActividad){
            try{
                String letra = actS.substring(0, 1);  
                String idA = actS.substring(1);  
                if(actS!=null && letra.equals("P")){
                    actividades(idA, "'"+sdf.format(vista.fechaActividad.getDate())+"'");
                }
            }catch(NullPointerException ex){}
        }
    }
}
