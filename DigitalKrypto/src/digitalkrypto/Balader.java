//Tsotras Stefanos icsd13189
//Kokovidi Athina icsd13078
package digitalkrypto;

/**
 *
 * @author Stefanos
 */
public class Balader extends Letter{
    
    
    public Balader() {
        super("?", 0);
        setText("<html>"+"<font size=8> ? ");
    }

    @Override
    public int getFinalValue() {
        return getValue();
    }
    
    public void Change(String letter, int value){
        setLetter(letter);
        setValue(value);
    }
    
}
