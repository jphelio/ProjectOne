package projectonebuilder.gui.components;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Juhani Heliö
 */
public class MapPanel extends JPanel {

    private int[][] terrainGridInt;
    private MapTile[][] terrainGrid;
    private Texture currentTerrainTexture;
    private boolean mouseIsPressed=false;
    private ArrayList<Texture> terrainTextures;

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
                mouseIsPressed=false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(mouseIsPressed){
                    paintTexture(i, j);
                    
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    
    private void paintTexture(int i, int j){
        if (currentTerrainTexture != null) {
            terrainGrid[i][j].setCurrentTerrainTexture(currentTerrainTexture.getImg());
            updateTerrainGridIntWithCorrectnumberForTerrain(i,j);
        }
    }
    
    private void updateTerrainGridIntWithCorrectnumberForTerrain(int i, int j){
        int a=0;
        while(!terrainTextures.get(a).getName().equals(currentTerrainTexture.getName())){
            a++;
        }
        terrainGridInt[i][j]=a;
    }

    public void setCurrentTerrainTexture(Texture currentTerrainTexture) {
        this.currentTerrainTexture = currentTerrainTexture;
    }

    public void setTerrainTextures(ArrayList<Texture> terrainTextures) {
        this.terrainTextures = terrainTextures;
    }
    
    public int[][] getTerrainGridInt(){
        return terrainGridInt;
    }
    
}
