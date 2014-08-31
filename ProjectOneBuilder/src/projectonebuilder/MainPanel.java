/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectonebuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileSystemView;
import projectonebuilder.gui.components.MapPanel;
import projectonebuilder.gui.components.MapTile;
import projectonebuilder.gui.components.Texture;

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
    
    private int[][] terrainGridInt;
    
    private ArrayList<Texture> terrainTextures;
    
    
    public MainPanel(int[][] terrainGridInt, MapTile[][] terrainGrid, ArrayList<Texture> terrainTextures){
        
        this.terrainTextures=terrainTextures;
        
        this.terrainGridInt=terrainGridInt;
        
        addMenus();
        
        mapPanel=new MapPanel(terrainGridInt, terrainGrid);
        mapPanel.setTerrainTextures(terrainTextures);
        
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
        saveFile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fr = new JFileChooser();
                FileSystemView fw = fr.getFileSystemView();
                System.out.println(fw.getDefaultDirectory().getAbsolutePath());
                File saveDir=new File(fw.getDefaultDirectory().getAbsolutePath()+"/ProjectOne/maps");
                saveDir.mkdirs();
                JFileChooser jfc=new JFileChooser(saveDir);
                int retVal=jfc.showDialog(mapPanel, "Save");
                if(jfc.getSelectedFile()!=saveDir){
                        File saveFile=jfc.getSelectedFile();
                    try {
                        if(retVal==JFileChooser.APPROVE_OPTION){
                        Writer w=new FileWriter(saveFile);
                        System.out.println(terrainGridInt.toString());
                        w.write(terrainGridInt.length+","+terrainGridInt[0].length+",");
                        for(int i=0;i<terrainGridInt.length;i++){
                            for(int j=0;j<terrainGridInt[0].length;j++){
                                w.write(terrainGridInt[i][j]+"");
                            }
                        }
                        w.close();
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }); 
        
        mainMenu.add(loadFile);
        mainMenu.addSeparator();
        mainMenu.add(exit);
        
        terrainSelectorMenu=new JMenu("Terrain");
        terrains=new ArrayList<>();
        terrainTextures.forEach(item->{
                Icon icon = new ImageIcon(item.getImg());
                System.out.println(item.getName());
                JMenuItem jmi=new JMenuItem(item.getName(), icon);
                jmi.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mapPanel.setCurrentTerrainTexture(item);
                    }
                }); 
                terrains.add(jmi);
                
        });
        
        terrains.forEach(item->terrainSelectorMenu.add(item));
        
        menuBar.add(mainMenu);
        menuBar.add(terrainSelectorMenu);
        
        this.setJMenuBar(menuBar);
    }

    public ArrayList<JMenuItem> getTerrains() {
        return terrains;
    }
    
}
