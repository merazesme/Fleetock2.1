/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.administrador;

import com.sun.istack.internal.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import modelo.Conexion;
import modelo.administrador.modeloActividad;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import vistas.administrador.avistaActividades;

/**
 *
 * @author Fabiola Paez
 */
public class acontrolActividad implements ActionListener, MouseListener, KeyListener{
    private modeloActividad modelo; // Se crea un objeto tipo modelo 
    private avistaActividades vista; // Se crea un objeto para la vista
    private int idEstilo; // Variable para guardar el id del estilo de viaje que se desee agregar o eliminar 
    // Se crea un constructor de parametros 
    public acontrolActividad(modeloActividad  modelo, avistaActividades vista)
    {   this.modelo= modelo; 
        this.vista= vista;  
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnCancelar.addActionListener(this);
        this.vista.btnReporte.addActionListener(this);
        this.vista.tblActividad.addMouseListener(this);
        this.vista.txtBuscar.addKeyListener(this);
        deshabilitar();
    }
    public void habilitar() // Metodo para habilitar los botones 
    {   vista.btnActualizar.setEnabled(true);
        vista.btnAgregar.setEnabled(true);
        vista.btnEliminar.setEnabled(true);
        vista.btnCancelar.setEnabled(true);
    }
    public void deshabilitar() // Metodo para deshabilitar los botones 
    {   vista.btnActualizar.setEnabled(false);
        vista.btnEliminar.setEnabled(false);
    }
    public void limpiar() // Metodo para limpiar los cuadros de texto 
    {   
        vista.tblActividad.setModel(modelo.administradorConsultar()); 
        ocultaColumnaActividad();
        vista.txtBuscar.setText(""); 
        vista.txtId.setText(""); 
        vista.txtDescripcion.setText("");
        vista.txtNombre.setText(""); 
        deshabilitar();
        vista.btnAgregar.setEnabled(true);
    }
    public void ocultaColumnaActividad() // Oculta la columna del id en la tabla de actividades 
    {   this.vista.tblActividad.getColumnModel().getColumn(0).setMaxWidth(0);
        this.vista.tblActividad.getColumnModel().getColumn(0).setMinWidth(0);
        this.vista.tblActividad.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        this.vista.tblActividad.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        this.vista.tblActividad.getColumnModel().getColumn(0).setPreferredWidth(0);
    }
    public void iniciarVista() // Muetra la vista principal 
    {   
        limpiar();
        vista.setVisible(true); 
    }
    public String validacionCamposVacios() // Verifica que no haya campos vacios 
    {   if(vista.txtNombre.getText().isEmpty() || vista.txtDescripcion.getText().isEmpty())
            return "Favor de llenar todos los campos"; 
        else return null;
    }
    @Override
    public void actionPerformed(ActionEvent evento) 
    {   
        if(vista.btnAgregar == evento.getSource())
        {   
            // Manda los datos para generar un registro 
            if(validacionCamposVacios()==null)
            {    
                if(modelo.administradorInsertarA(vista.txtNombre.getText(),vista.txtDescripcion.getText()))
                {   JOptionPane.showMessageDialog(null, "Registro agregado exitosamente");
                    limpiar(); 
                }
                else 
                    JOptionPane.showMessageDialog(null, "Error al insertar los datos");
            }
            else
               JOptionPane.showMessageDialog(null, ""+validacionCamposVacios());
        }
        else if(vista.btnEliminar == evento.getSource())
        {   
            // Manda el id del registro que se va ha de eliminar 
            if (JOptionPane.showConfirmDialog(vista,"¿Estás seguro que deseas eliminar este registro?", "Fleetock",
                JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
            {   
            if (modelo.administradorEliminarA(Integer.parseInt(vista.txtId.getText())))
                {   JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente");
                    limpiar(); 
                }
                else 
                {
                    JOptionPane.showMessageDialog(null, "No se puede eliminar, este registro se encuentra en otra tabla");
                    limpiar();
                }
            }
        }
        else if(vista.btnActualizar == evento.getSource())
            {   
                // Manda el id del registro que se ha de modificar 
                if(validacionCamposVacios()==null)
                {   if(modelo.administradorActualizarA(Integer.parseInt(vista.txtId.getText()), 
                           vista.txtNombre.getText(),vista.txtDescripcion.getText()))
                    {   JOptionPane.showMessageDialog(null, "Registro actualizado exitosamente");
                        limpiar(); 
                    }
                    else 
                        JOptionPane.showMessageDialog(null, "Error al actualizar los datos");
                }
                else 
                    JOptionPane.showMessageDialog(null, ""+validacionCamposVacios());
            }
        else if(vista.btnCancelar == evento.getSource())
        {   
            // Limpia los cuadros de texto y deshabilita los botones cuando cancela 
            limpiar(); 
            deshabilitar(); 
        }
        else if(vista.btnReporte == evento.getSource()) // Genera un reporte de todas las actividades 
            {   try
                {   try
                    {   Conexion conexion = new Conexion(); 
                        Connection con = conexion.abrirConexion();
                        if( con != null)
                        {   JasperReport reporte= null; 
                            String path="src\\reportes\\reporteActividad.jasper"; 
                            reporte = (JasperReport) JRLoader.loadObjectFromFile(path); 
                            JasperPrint jprint = JasperFillManager.fillReport(reporte, null,con); 
                            JasperViewer view = new JasperViewer(jprint, false); 
                            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
                            view.setVisible(true);
                        }
                    }
                    catch(SQLException e)
                    {   JOptionPane.showMessageDialog(null, "Error al intentar generar el reporte");
                    }
                }catch(JRException ex)
                {   JOptionPane.showMessageDialog(null, "Error al intentar generar el reporte"); 
                }
            }     
    }
    @Override
    public void mouseClicked(MouseEvent e) 
    {   
        if(vista.tblActividad == e.getSource())
        {   
            // Carga la fila seleccionada a los campos de texto 
            int fila = vista.tblActividad.rowAtPoint(e.getPoint()); 
            if( fila > -1)
            {   vista.txtId.setText(String.valueOf(vista.tblActividad.getValueAt(fila, 0)));
                vista.txtNombre.setText(String.valueOf(vista.tblActividad.getValueAt(fila, 1)));
                vista.txtDescripcion.setText(String.valueOf(vista.tblActividad.getValueAt(fila, 2)));
                habilitar();
                vista.btnAgregar.setEnabled(false);
            }
        }
    }
    @Override
    public void mousePressed(MouseEvent me) {}
    @Override
    public void mouseReleased(MouseEvent me) {}
    @Override
    public void mouseEntered(MouseEvent me) {}
    @Override
    public void mouseExited(MouseEvent me) {}
    @Override
    public void keyTyped(KeyEvent ke) {}
    @Override
    public void keyPressed(KeyEvent ke) {}
    @Override
    // Buscador en tiempo real (busca lo que se esta tecleando)
    public void keyReleased(KeyEvent ke) 
    {   vista.tblActividad.setModel(modelo.Buscador(String.valueOf(vista.txtBuscar.getText())));
        ocultaColumnaActividad();
    }
}