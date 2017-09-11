//Tsotras Stefanos icsd13189
//Kokovidi Athina icsd13078
package digitalkrypto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;

/**
 *
 * @author Stefanos
 */
public abstract class Letter extends JButton{
    
    
    private String letter;
    private int value;
    private String color;
    private boolean checked;
    
    public Letter(String letter,int value){
        
        super("<html>"+"<font size=8>"+letter+"<font size=4>"+value);
        
        //getting inserted values 
        color="";
        this.value=value;
        this.letter=letter;
        
        //Font properties
        setBackground(new Color(255, 255, 255));
        setForeground(Color.BLACK);
        setFocusPainted(false);
        setFont(new Font("Tahoma", Font.PLAIN, 25));
        setPreferredSize(new Dimension(66,60));
    }
    
    //sets-gets
    public void setLetter(String letter){
        this.letter=letter;
    }
    public String getLetter(){
        return letter;
    }
    public void setValue(int value){
        this.value=value;
    }
    public int getValue(){
        return value;
    }
    public void setColor(String color){
        this.color =color;
    }
    public String getColor(){
        return color;
    }
    public void setChecked(boolean checked){
        this.checked=checked;
    }
    public boolean getChecked(){
        return checked;
    }
    
    public abstract int getFinalValue();
    
    
}
