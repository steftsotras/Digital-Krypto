//Tsotras Stefanos icsd13189
//Kokovidi Athina icsd13078
package digitalkrypto;

import java.awt.Color;

/**
 *
 * @author Stefanos
 */
public class Red extends Letter{
    
    public Red(String letter, int value) {
        super(letter, value);
        setColor("Red");
        setBackground(new Color(250, 0, 0));
    }

    @Override
    public int getFinalValue() {
        return getValue()*2;
    }
    
    
}
