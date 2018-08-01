/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.modeloEditarPerfil;
import modelo.modeloPerfil;
import vistas.vistaEditarPerfil;
import vistas.vistaPerfil;
import vistas.vistaPrincipal;

/**
 *
 * @author Holi
 */
public class controlEditarPerfil implements ActionListener{

    private vistaEditarPerfil vista;
    private vistaPrincipal vistaPrincipal;
    private modeloEditarPerfil modelo;
    private String [] uDatos;
    private JFileChooser archi = new JFileChooser();
    private int ventana=0, banderaFoto=0;
    private String destino = "C:/xampp/htdocs/Fleetock/Imagenes/Usuarios/";
    private String archivo="";
    
    //Permite acceder a funciones de la fecha.
    Calendar calendario = Calendar.getInstance();
    //Agarra el año en curso.
    int anioActual = calendario.get(Calendar.YEAR);
    //Agarra el mes en curso.
    int mesActual = calendario.get(Calendar.MONTH);
    //Agarra el dia en curso.
    int diaActual = calendario.get(Calendar.DAY_OF_MONTH);
    
    public controlEditarPerfil(vistaEditarPerfil vista, vistaPrincipal vistaPrincipal, modeloEditarPerfil modelo)
    {
        this.vista=vista;
        this.modelo=modelo;
        this.vistaPrincipal=vistaPrincipal;
        this.uDatos=modelo.jalarDatosUsuario(controlPrincipal.usuario[2]);
        this.vista.btnCambiarContrasenia.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnSeleccionar.addActionListener(this);
        this.vista.btnEditarPerfil.addActionListener(this);
        datosU();
        ValidarCampos(vista.txtNombre);
        ValidarCampos(vista.txtApellidos);
        datosPerfil();
    }
    
    //Datos del usuario
    public void datosU(){
        if (uDatos[2]!=null) {
            ImageIcon image = new ImageIcon(uDatos[7]);                
            Icon fondo = new ImageIcon(image.getImage().getScaledInstance(140, 140, Image.SCALE_DEFAULT));
            vista.lblFoto.setIcon(fondo);
        }
    }
    
    //Datos para modificar perfil
    public void datosPerfil(){
        //Restringo valores de la fecha
        vista.dia.setMinimum(1);
        vista.dia.setMaximum(31);
        
        vista.año.setMinimum(anioActual-100);
        vista.año.setMaximum(anioActual-15);
        
        vista.dia.setValue(1);
        vista.mes.setMonth(0);
        vista.año.setValue(anioActual-15);
        
        vista.mes.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                if(vista.mes.getMonth()==1)
                    vista.dia.setMaximum(28);
                else
                    vista.dia.setMaximum(31);
            }
        });
        
        //agregamos los rb al grupo de botone spara que solo uno se seleccione
        vista.grupobotones.add(vista.rbHombre);
        vista.grupobotones.add(vista.rbMujer);
         
        if(uDatos!=null){
            //Datos
            vista.txtNombre.setText(uDatos[0]);
            vista.txtApellidos.setText(uDatos[1]);
            vista.txtCorreo.setText(uDatos[5]);
            vista.txtareaDescripcion.setText(uDatos[6]);
            vista.txtTockname.setText(controlPrincipal.usuario[1]);
            vista.dia.setValue(modelo.jalarFecha(3,controlPrincipal.usuario[2]));
            vista.mes.setMonth(modelo.jalarFecha(2,controlPrincipal.usuario[2]));
            vista.año.setValue(modelo.jalarFecha(1,controlPrincipal.usuario[2]));

            //Datos del sexo
            try{
                if(uDatos[4].equals("Mujer"))
                    vista.rbMujer.setSelected(true);
                else if(uDatos[4].equals("Hombre"))
                    vista.rbHombre.setSelected(true); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }            
        }
    }
    
    //Validar los campos del nombre
    public void ValidarCampos(JTextField txt){
        txt.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (!Character.isLetter(caracter) && !Character.isSpace(caracter)) {
                    e.consume();
                }
            }
        });
    }
    
    //mensaje de la contraseña
    public int mensaje(String m, JPasswordField pass){
        JPanel panel = new JPanel();
        JLabel label = new JLabel(m);
        panel.add(label);
        panel.add(pass);
        pass.setText("");
        pass.requestFocus();
        String[] options = new String[]{"Aceptar", "Cancelar"};
        int mensaje = JOptionPane.showOptionDialog(null, panel, "Cambiar Contraseña",
                JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, "");
        return mensaje;
    }
    
    //Metodo para crear un fichero en una direccion de la computadora.
    public static void copyFile_Java(String origen, String destino){
        try 
        {
            CopyOption[]  options = new CopyOption[]
            {
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES

            }; 
            Files.copy(Paths.get(origen),Paths.get(destino), options); 
            //JOptionPane.showMessageDialog(null, "Imagen guardada");
        }catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "Error al guardar imagen");
            System.err.println(e.toString());
        }
    }
    
    //Función para validar email
    public boolean validarEmail(String email){
        String emailPattern ="^[a-zA-Z0-9_]+[@]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z0-9]+$";
        
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches())
            return true;
        else
            return false;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //Seleccionar una foto
        if(e.getSource()==vista.btnSeleccionar){
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formato de archivo JPG", "jpg", "jpeg"); 
            archi.addChoosableFileFilter(filtro);
            archi.setDialogTitle("Abrir archivo"); 
            ventana = archi.showOpenDialog(null); 
            if( ventana == JFileChooser.APPROVE_OPTION)
            {
                vista.lblFoto.removeAll();
                vista.lblFoto.revalidate();
                vista.lblFoto.repaint();
                java.io.File file = archi.getSelectedFile(); 
                Image foto = vista.getToolkit().getImage(String.valueOf(file)); 
                foto = foto.getScaledInstance(140, 140, Image.SCALE_DEFAULT); 
                vista.lblFoto.setIcon(new ImageIcon(foto)); 
                banderaFoto=1;
            }
        }
        
        //Regresar al perfil
        if(e.getSource()==vista.btnEditarPerfil){
            if (JOptionPane.showConfirmDialog(vista,
                "¿Estás seguro que deseas regresar al perfil?", "Fleetock",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    vistaPerfil vistaPerfil = new vistaPerfil();
                    modeloPerfil modeloPerfil = new modeloPerfil();
                    controlPerfil controlPerfil = new controlPerfil(vistaPerfil, vistaPrincipal, modeloPerfil);
                    CambiaPanel cambiar = new CambiaPanel(vistaPrincipal.panelCambiante, vistaPerfil);
            } 
        }
        
        //Guardar cambios
        if(e.getSource()==vista.btnActualizar){
            String [] b = modelo.validarTockname(vista.txtTockname.getText());
            String fechanac=vista.año.getValue()+"-"+vista.mes.getMonth()+"-"+vista.dia.getValue();
            //Derivo la edad del usuario a partir de la fecha de nacimiento seleccionada.
            int edad=anioActual-vista.año.getValue();
            if(vista.mes.getMonth()>=mesActual && vista.dia.getValue()>=diaActual)
                edad-=1;
            String sexo="";
            
            //Agarra el texto de los rb
            if(vista.rbHombre.isSelected())
                sexo=vista.rbHombre.getText();
            else if(vista.rbMujer.isSelected())
                sexo=vista.rbMujer.getText();
            
            //Si ha seleccionado alguna foto
            if (banderaFoto==1){
                //Si ha seleccionado alguna foto, la guarda en la variable archivo.
                if(ventana == JFileChooser.APPROVE_OPTION)
                {  
                    File fichero= archi.getSelectedFile();
                    String ruta = fichero.getAbsolutePath();
                    String nombre = fichero.getName();
                    archivo = destino + nombre; 
                    File folder = new File(destino); 
                    if(!folder.exists())
                        folder.mkdirs(); 
                    copyFile_Java(ruta,archivo); 
                }
            }
            //sino ha seleccionado nada, entonces guarda la actual
            else{
                archivo=uDatos[7];
            }

            //Si ha dejado algun campo vacio, se muestra un mensaje de error.
            if(vista.txtNombre.getText().isEmpty() || vista.txtApellidos.getText().isEmpty() || vista.txtTockname.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Favor de llenar todos los campos.");
            }
            
            //Si no exite el usuario ingresado pero el email es invalido.
            else if(validarEmail(vista.txtCorreo.getText())!=true)
            {
                //Limpia la caja de texto.
                vista.txtApellidos.setText("");
                //Muestra un mensaje de error.
                JOptionPane.showMessageDialog(null, "Favor ingresar correctamente su email.");
            }
            
            //Si ya existe el nombre de usuario ingresado.
            else if(Integer.parseInt(b[0])>0 && !b[1].equals(controlPrincipal.usuario[0]))
            {
                
                //Limpia la caja de texto.
                vista.txtTockname.setText("");
                //Manda un mensaje de error.
                JOptionPane.showMessageDialog(null, "Favor de usar otro Tockname.");
            }
            
            //Si no exite el usuario ingresado, el email es valido pero el genero no.
            else if(!vista.rbHombre.isSelected() && !vista.rbMujer.isSelected())
            {
                //Muestra un mensage de error.
                JOptionPane.showMessageDialog(null, "Favor de seleccionar un sexo.");
            }
            
            //Si se realiza con exito la sentencia sql de insertar.
            else if(modelo.actualizarDatosUsuario(controlPrincipal.usuario[0], vista.txtNombre.getText(), 
                    vista.txtApellidos.getText(), fechanac, edad, sexo, vista.txtCorreo.getText(), 
                    vista.txtareaDescripcion.getText(), archivo, vista.txtTockname.getText()))
                {
                    //Muestra un mensaje de exito.
                    JOptionPane.showMessageDialog(null, "Registro modificado satisfactoriamente.");
                    //se va al perfil
                    vistaPerfil vistaPerfil = new vistaPerfil();
                    modeloPerfil modeloPerfil = new modeloPerfil();
                    controlPerfil controlPerfil = new controlPerfil(vistaPerfil, vistaPrincipal, modeloPerfil);
                    CambiaPanel cambiar = new CambiaPanel(vistaPrincipal.panelCambiante, vistaPerfil);
                }
            
                //Si no se ejecuta con exito la sentencia sql se mostrará un mensaje de error.
                else
                {
                    //Muestra un mensaje de error.
                    JOptionPane.showMessageDialog(null, "Error al insertar los datos.");
                }
        }
        
        
        //Cambiar contraseña
        if (e.getSource()==vista.btnCambiarContrasenia) {
            JPasswordField pass = new JPasswordField(10);
            if(mensaje("Ingresa su contraseña actual", pass) == 0) // pressing OK button
            {
                //Contraseña actual
                String contraActual = new String(pass.getPassword());
                //Si no se ingresó nada
                if(contraActual.equals("")){
                    JOptionPane.showMessageDialog(null, "Ingrese su contraseña", "Error", JOptionPane.ERROR_MESSAGE);
                }
                //Si la contra es incorrecta
                else if(!contraActual.equals(uDatos[8])){
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                }
                //Contraseña correcta
                else{
                    boolean bandera=true;
                    //Si el usuario ingresó bien la contraseña, se le pide que introduzca la nueva contraseña.
                    do
                    {
                        //presionar ok
                        if (mensaje("Cambiar Contraseña", pass) == 0) {
                            //Contraseña nueva
                            String contraNueva = new String(pass.getPassword());
                            //si no ingresó nada
                            if(contraNueva.equals("")){
                                JOptionPane.showMessageDialog(null, "Ingrese su contraseña", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            //si la contra nueva es menor a 8 caracteres
                            else if(contraNueva.length() < 8){
                                JOptionPane.showMessageDialog(null, "Ingrese 8 caracteres como minimo.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            //si está bien
                            else{
                                //si dio ok
                                if(mensaje("Ingresa de nuevo la nueva contraseña: ", pass)==0){
                                    //Contraseña nueva2
                                    String contraNueva2 = new String(pass.getPassword());
                                    //si está vacío
                                    if(contraNueva2.equals("")){
                                        JOptionPane.showMessageDialog(null, "Ingrese la contraseña", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                    //si las contras son iguales
                                    else if(contraNueva.equals(contraNueva2)){
                                        if(modelo.actualizarContraseña(controlPrincipal.usuario[2], contraNueva))
                                        {
                                            bandera=false;
                                            JOptionPane.showMessageDialog(null, "Su contraseña ha sido actualizada", "Fleetock", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                    }
                                    //si las contras no son iguales
                                    else{
                                        JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                                //si dio cancelar
                                else{
                                    bandera=false;
                                    JOptionPane.showMessageDialog(null, "Se ha cancelado el proceso", "Error", JOptionPane.WARNING_MESSAGE);
                                }
                            }
                        } 
                        //presionar cancelar
                        else{
                            bandera=false;
                            JOptionPane.showMessageDialog(null, "Se ha cancelado el proceso", "Error", JOptionPane.WARNING_MESSAGE);
                        }
                    } while(bandera);
                }
            }
            //presionar cancelar
            else{
                JOptionPane.showMessageDialog(null, "Se ha cancelado el proceso", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
}
