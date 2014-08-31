/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectonebuilder.gui.components;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

/**
 *
 * @author Juhani Heliö
 */
public class Texture{

    private String name;
    private Image img;
    
    public Texture(String name, Image img){
        this.name=name;
        this.img=img;
    }
    
    public Image getImg(){
        return img;
    }
    
    public String getName(){
        return name;
    }
}
