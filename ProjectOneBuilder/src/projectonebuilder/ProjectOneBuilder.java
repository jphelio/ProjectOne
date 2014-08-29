/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectonebuilder;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import projectonebuilder.gui.components.MapTile;

/**
 *
 * @author Juhani Heliö
 */
public class ProjectOneBuilder {

    
    private static int[][] terrainGridInt;
    private static MapTile[][] terrainGrid;
    private static HashMap<String, Image> terrainTextures;
    static final File dir = new File("textures");
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        initialize();
        MainPanel mp=new MainPanel(terrainGridInt, terrainGrid, terrainTextures);
    }
    
    private static void initialize(){
        
        terrainTextures=new HashMap<>();
        
        BufferedImage img = null;
        if(dir.isDirectory()){
            for (final File f : dir.listFiles()) {

                try {
                    img = ImageIO.read(f);
                    terrainTextures.put(f.getName(), img);
                } catch (final IOException e) {
                    System.out.println("No Textures!!");
                }
            }
        }
        
        
        terrainGridInt=new int[20][20];
        terrainGrid=new MapTile[20][20];
        
        for(int i=0;i<terrainGrid.length;i++){
            for(int j=0;j<terrainGrid[0].length;j++){
                terrainGrid[i][j]=new MapTile();
            }
        }
    }
    
}
