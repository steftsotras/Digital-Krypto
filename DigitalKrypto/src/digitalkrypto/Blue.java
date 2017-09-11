//Tsotras Stefanos icsd13189
//Kokovidi Athina icsd13078
package digitalkrypto;

import java.awt.Color;

/**
 *
 * @author Stefanos
 */
public class Blue extends Letter{
    
    private boolean trigger;
    
    public Blue(String letter, int value) {
        super(letter, value);
        setColor("Blue");
        setBackground(new Color(0, 191, 255));
        trigger=false;
    }

    @Override
    public int getFinalValue() {
        trigger=true;
        return getValue();
    }
    
    public boolean getTrigger(){
        return trigger;
    }
}
