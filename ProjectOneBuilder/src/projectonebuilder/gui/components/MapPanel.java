package projectonebuilder.gui.components;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author Juhani Heliö
 */
public class MapPanel extends JPanel {

    private int[][] terrainGridInt;
    private MapTile[][] terrainGrid;
    private Image currentTerrainTexture;
    private boolean mouseIsPressed=false;

    public MapPanel(int[][] terrainGridInt, MapTile[][] terrainGrid) {
        this.terrainGrid=terrainGrid;
        this.terrainGridInt=terrainGridInt;
        this.setLayout(new GridLayout(terrainGrid.length, terrainGrid[0].length));
        for (int i = 0; i < terrainGrid.length; i++) {
            for (int j = 0; j < terrainGrid[0].length; j++) {
                this.add(terrainGrid[i][j]);
                addMouseListenerToPanel(i, j);
            }
        }
    }

    private void addMouseListenerToPanel(int i, int j) {
        terrainGrid[i][j].addMouseListener(new MouseListener() {            
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                paintTexture(i, j);
                mouseIsPressed=true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                paintTexture(i, j);
                mouseIsPressed=false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(mouseIsPressed){
                    paintTexture(i, j);
                }
            }
        });
    }

    
    private void paintTexture(int i, int j){
        if (currentTerrainTexture != null) {
            terrainGrid[i][j].setCurrentTerrainTexture(currentTerrainTexture);
        }
    }

    public void setCurrentTerrainTexture(Image currentTerrainTexture) {
        this.currentTerrainTexture = currentTerrainTexture;
    }
    
}
