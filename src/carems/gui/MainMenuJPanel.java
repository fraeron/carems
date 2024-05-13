/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carems.gui;

import java.awt.*;
import javax.swing.JPanel;

/**
 *
 * @author gelodrei
 */
public class MainMenuJPanel extends JPanel { //This is the panel for MainMenu.java
    
    private final Dimension panelSize = new Dimension(800,600);
    
    private final Color clrAshGrey = new Color(42, 42, 42);    
    private final Color clrMagmaOrange = new Color(255, 127, 39);
    
    MainMenuJPanel(){
        this.setPreferredSize(panelSize);
        this.setBackground(clrAshGrey);
        
        
    }
    @Override //Method for making lines or any graphics (wag galawin hahaha)
    public void paint(Graphics g) { 
        
        Graphics2D line1 = (Graphics2D) g;
        Graphics2D line2 = (Graphics2D) g;

        line1.setColor(clrMagmaOrange);
        line2.setColor(clrMagmaOrange);
        
        line1.drawLine(200, 200, 0, 200);
        line2.drawLine(200, 0, 200, 800);
    }
}
