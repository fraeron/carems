package carems.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JPanel;


class SidebarPanel extends JPanel {
    private final Dimension panelSize = new Dimension(800,600);
    private final Color clrAshGrey = new Color(42, 42, 42);    
    private final Color clrMagmaOrange = new Color(255, 127, 39);
    
    public SidebarPanel(){
        this.setPreferredSize(panelSize);
        this.setBackground(clrAshGrey);   
    }
    
    public void AddButtons(JButton btnToAdd) {
        
    }
    
    // Paint lines in the panel.
    @Override
    public void paint(Graphics g) { 
        Graphics2D line1 = (Graphics2D) g;
        Graphics2D line2 = (Graphics2D) g;
        line1.setColor(clrMagmaOrange);
        line2.setColor(clrMagmaOrange);
        line1.drawLine(200, 200, 0, 200);
        line2.drawLine(200, 0, 200, 800);
    }
}
