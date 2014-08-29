/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectonebuilder.gui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author Juhani Heliö
 */
public class MapTile extends JPanel{
    
    Image currentTerrainTexture;
    
    public MapTile(){
        this.setSize(32, 32);
        this.setBackground(Color.LIGHT_GRAY);
    }
    
    public void setCurrentTerrainTexture(Image currentTerrainTexture){
        this.currentTerrainTexture=currentTerrainTexture;
        paintComponent(this.getGraphics());
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(currentTerrainTexture, 0, 0, null);
    }
}
