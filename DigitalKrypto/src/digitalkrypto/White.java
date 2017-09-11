//Tsotras Stefanos icsd13189
//Kokovidi Athina icsd13078
package digitalkrypto;


/**
 *
 * @author Stefanos
 */
public class White extends Letter{
    
    
    
    public White(String letter, int value) {
        super(letter, value);
        setColor("White");
    }

    @Override
    public int getFinalValue() {
        return getValue();
    }
    
    
    
    
    
}
