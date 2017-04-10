/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopcoursework2;

/**
 *
 * @author Manul Singhe 2014254
 */
public class symbol implements iSymbol {
     String img ;
     int value ;
     
    @Override
    public void setImage(String imgLoc){
        img = imgLoc;
    }
    
    @Override
    public String getImage(){
        return img;
    }
    
    @Override
    public void setValue(int val){
        value = val;
    }
    
    @Override
    public int getValue(){
        return value;
    }
}
