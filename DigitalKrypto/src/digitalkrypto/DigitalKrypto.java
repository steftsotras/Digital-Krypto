//Tsotras Stefanos icsd13189
//Kokovidi Athina icsd13078
package digitalkrypto;

import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;


/**
 *
 * @author Stefanos
 */
public class DigitalKrypto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        
        JFrame frame = new JFrame("Digital Krypto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JMenuBar menuBar =new JMenuBar();
        JMenu menu =new JMenu("Menu");
        JMenu tools =new JMenu("Tools");
        
        menuBar.add(menu);
        menuBar.add(tools);
        
        frame.setJMenuBar(menuBar);
        
        MainPanel mp = new MainPanel();
        frame.add(mp);
        
        frame.pack();
        frame.setVisible(true);
    }
    
}
