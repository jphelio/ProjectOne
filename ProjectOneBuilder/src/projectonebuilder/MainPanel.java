/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectonebuilder;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiConsumer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import jdk.nashorn.internal.objects.NativeArray;
import projectonebuilder.gui.components.MapPanel;
import projectonebuilder.gui.components.MapTile;

/**
 *
 * @author Juhani Heliö
 */
public class MainPanel extends JFrame{
    
    private MapPanel mapPanel;
    
    private JMenuBar menuBar;
    private JMenu mainMenu;
    private JMenuItem newFile;
    private JMenuItem saveFile;
    private JMenuItem loadFile;
    private JMenuItem exit;
    private JMenu terrainSelectorMenu;
    private ArrayList<JMenuItem> terrains;
    
    private HashMap<String, Image> terrainTextures;
    
    
    public MainPanel(int[][] terrainGridInt, MapTile[][] terrainGrid, HashMap<String, Image> terrainTextures){
        
        this.terrainTextures=terrainTextures;
        
        addMenus();
        
        mapPanel=new MapPanel(terrainGridInt, terrainGrid);
        this.add(mapPanel);
        this.setSize(360, 400);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void addMenus(){
        menuBar=new JMenuBar();
        mainMenu=new JMenu("File");
        newFile=new JMenuItem("New map");
        saveFile=new JMenuItem("Save");
        loadFile=new JMenuItem("Load...");
        exit=new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }); 
        
        mainMenu.add(newFile);
        mainMenu.add(saveFile);
        mainMenu.add(loadFile);
        mainMenu.addSeparator();
        mainMenu.add(exit);
        
        terrainSelectorMenu=new JMenu("Terrain");
        terrains=new ArrayList<>();
        terrainTextures.forEach(new BiConsumer<String, Image>() {

            @Override
            public void accept(String t, Image u) {
                Icon icon = new ImageIcon(u);
                JMenuItem jmi=new JMenuItem(t, icon);
                jmi.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {}
                    @Override
                    public void mousePressed(MouseEvent e) {}
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        mapPanel.setCurrentTerrainTexture(u);
                    }
                    @Override
                    public void mouseEntered(MouseEvent e) {}
                    @Override
                    public void mouseExited(MouseEvent e) {}
                }); 
                terrains.add(jmi);
                
            }
        });
        
        terrains.forEach(item->terrainSelectorMenu.add(item));
        
        menuBar.add(mainMenu);
        menuBar.add(terrainSelectorMenu);
        
        this.setJMenuBar(menuBar);
    }
    
}
