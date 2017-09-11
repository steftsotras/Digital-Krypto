/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Tsotras Stefanos icsd13189
//Kokovidi Athina icsd13078
package digitalkrypto;

/**
 *
 * @author Stefanos
 */
public class Availability {
    
    private boolean up;
    private boolean down;
    private boolean right;
    private boolean left;
    private int x,y,i,j,counter;
    
    private Letter letter;
    
    public Availability(int x,int y,int i,int j){
        
        this.x=x;
        this.y=y;
        this.i=i;
        this.j=j;
                
        counter=0;
        up=true;
        down=true;
        right=true;
        left=true;
        
        setAvailability();
        
    }
    
    public void setAvailability(){
        if(i == 0){
            counter++;
            up=false;
        }
        if(i == x){
            counter++;
            right=false;
        }
        if(j == 0){
            counter++;
            left=false;
        }
        if(j == y){
            counter++;
            down=false;
        }
    }
    
    public int getI(){
        return i;
    }
    public int getJ(){
        return j;
    }
    public int getCat(){
        return counter;
    }
    public boolean getUp(){
        return up;
    }
    public boolean getDown(){
        return down;
    }
    public boolean getRight(){
        return right;
    }
    public boolean getLeft(){
        return left;
    }
    
    
}
