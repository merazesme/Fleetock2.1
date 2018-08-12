/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.administrador;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Fabiola Paez
 */
public class Imagen extends javax.swing.JPanel{
    String ruta; 
    public Imagen(int x, int y, String ruta)
    {
        this.setSize(x, y);
        this.ruta=ruta; 
    }
    @Override
    public void paint(Graphics g)
    {
        Dimension height = getSize(); 
//        ImageIcon img = new ImageIcon(getClass().getResource(ruta));
        Image imgExt = new ImageIcon(ruta).getImage(); 
        g.drawImage(imgExt, 0, 0, height.width, height.height, null); 
        setOpaque(false); 
        super.paintComponent(g);
    }
}
