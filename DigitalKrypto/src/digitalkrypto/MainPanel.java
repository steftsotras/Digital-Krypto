//Tsotras Stefanos icsd13189
//Kokovidi Athina icsd13078
package digitalkrypto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import static javax.swing.SwingUtilities.isRightMouseButton;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Stefanos
 */
public class MainPanel extends JPanel implements ActionListener,MouseListener{
    
    //STOIXEIA TOU PANEL HELP OPOU THA EMFANIZONTAI 
    //OI VOITHIES POU THA EXEI O PAIXTHS
    JPanel row1,row2,row3,row4,row5,row6,row7,row8,row9,row10,row11;
    private JButton deleteRow,reRow,reCol,reBoard,swapLet,checkWord;
    private JTextField dR1,rR1,rC1;
    private JLabel dR,rR,rC,rB,sL,space,halp;
    
    private JLabel goaal,curv,valueofword,wordsf;
    private JLabel goaal2,curv2,valueofword2,wordsf2;
    private int goal,cur,value,wordsfound;
    
    private int countD,countR,countC,countB,countL;
    
    
    //TA DUO VASIKA PANELS
    private Board board;
    private JPanel help;
    
    public MainPanel() throws IOException{
        
        countD=0;countR=0;countC=0;countB=0;countL=0;
        goal=0;cur=0;value=0;wordsfound=0;
        
        
        
        GridBagConstraints c =new GridBagConstraints();
        setLayout(new GridBagLayout());
        
        c.insets = new Insets(5,5,5,5);
        c.weighty = 1.0;
        c.weightx = 1.0;
        
        //ARXIKA O PAIXTHS DIALEGEI DUSKOLIA KAI DHMIOURGEITE
        //ANALOGO MEGETHOUS TAMPLO
        String[] difficulties ={"Easy","Medium","Hard"};
        String dif = (String) JOptionPane.showInputDialog(this,"Choose Difficulty","Difficulty",JOptionPane.QUESTION_MESSAGE,null,difficulties,difficulties[0]);
        
        
        if(dif.equalsIgnoreCase("easy")){
            CreateBoard(5,5);
        }
        else if(dif.equalsIgnoreCase("medium")){
            CreateBoard(8,8);
        }
        else{
            CreateBoard(10,10);
        }
        goal = 10*board.Howmanywords();
        CreateHelp();
        
        
        
        board.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(board);
        
        help.setBorder(new EmptyBorder(1, 2, 2, 2));
        add(help);
        
        addMouseListener(this);
        
    }
    
    public void CreateHelp(){
        
        help= new JPanel();
        
        help.setLayout(new GridLayout(12,4));
        FlowLayout flowlayout = new FlowLayout();
        
        
        
        row1=new JPanel();
        row2=new JPanel();
        row3=new JPanel();
        row4=new JPanel();
        row5=new JPanel();
        row6=new JPanel();
        row7=new JPanel();
        row8=new JPanel();
        row9=new JPanel();
        row10=new JPanel();
        row11=new JPanel();
        
        deleteRow =new JButton(" Διαγραφή Γραμμής ");
        deleteRow.addActionListener(this);
        reRow =new JButton("Αναδιάταξη Γραμμής");
        reRow.addActionListener(this);
        reCol =new JButton(" Αναδιάταξη Στήλης  ");
        reCol.addActionListener(this);
        reBoard =new JButton("Αναδιάταξη Ταμπλό ");
        reBoard.addActionListener(this);
        swapLet =new JButton("Εναλλαγή Γραμμάτων");
        swapLet.addActionListener(this);
        checkWord =new JButton("Έλεγχος Λέξης");
        checkWord.addActionListener(this);
        
        dR1 =new JTextField(2);
        dR1.setText("0");
        rR1 =new JTextField(2);
        rR1.setText("0");
        rC1 =new JTextField(2);
        rC1.setText("0");
        
        dR =new JLabel("0/3");
        rR =new JLabel("0/3");
        rC =new JLabel("0/3");
        rB =new JLabel("          0/5");
        sL =new JLabel("0/6");
        
        space =new JLabel("     ");
        JLabel ln =new JLabel("\n\n\n\n\n\n");
        halp =new JLabel("Βοήθειες");
        
        goaal =new JLabel("  Στόχος:");
        curv =new JLabel("Συνολική Βαθμολογία:");               
        valueofword =new JLabel("Βαθμολογία Λέξης:");
        wordsf =new JLabel("  Λέξεις που βρέθηκαν:");
        goaal2 =new JLabel(String.valueOf(goal));
        curv2 =new JLabel("0");
        valueofword2 =new JLabel("0");
        wordsf2 =new JLabel("0/"+String.valueOf(board.Howmanywords()));
        
        row1.setLayout(flowlayout);
        row1.add(ln);
        row1.add(halp);
        help.add(row1);
        
        row2.setLayout(flowlayout);
        row2.add(deleteRow);
        row2.add(dR1);
        row2.add(dR);
        help.add(row2);
        
        row3.setLayout(flowlayout);
        row3.add(reRow);
        row3.add(rR1);
        row3.add(rR);
        help.add(row3);
        
        row4.setLayout(flowlayout);
        row4.add(reCol);
        row4.add(rC1);
        row4.add(rC);
        help.add(row4);
        
        row5.setLayout(flowlayout);
        row5.add(reBoard);
        row5.add(space);
        row5.add(rB);
        help.add(row5);
        
        row6.setLayout(flowlayout);
        row6.add(swapLet);
        row6.add(space);
        row6.add(sL);
        help.add(row6);
        
        FlowLayout fl = new FlowLayout(FlowLayout.LEFT, 140, 10);
        
        row7.setLayout(fl);
        row7.add(goaal);
        row7.add(goaal2);
        help.add(row7);
        
        FlowLayout fl2 = new FlowLayout(FlowLayout.CENTER, 60, 5);
        
        row8.setLayout(fl2);
        row8.add(curv);
        row8.add(curv2);
        help.add(row8);
        
        FlowLayout fl3 = new FlowLayout(FlowLayout.CENTER, 80, 5);
        
        row9.setLayout(fl3);
        row9.add(valueofword);
        row9.add(valueofword2);
        help.add(row9);
        
        FlowLayout fl4 = new FlowLayout(FlowLayout.CENTER, 50, 5);
        
        row10.setLayout(fl4);
        row10.add(wordsf);
        row10.add(wordsf2);
        help.add(row10);
        
        row11.setLayout(flowlayout);
        row11.add(checkWord);
        help.add(row11);
        
        
    }
    
    public void CreateBoard(int x,int y) throws IOException{
        board =new Board(x,y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //DIAGRAFH GRAMMHS
        if(e.getSource() == deleteRow){
            
            //PERNEI TO KEIMENO APTO TEXTFIELD KAI TO 
            //STELNEI SAN ORISMA STHN DELR TOU TAMPLO
            board.setdelR(Integer.valueOf(dR1.getText()));
            dR1.setText("0");
            countD++;
            dR.setText(countD+"/3");
            if(countD == 3){
                deleteRow.setEnabled(false);
                dR1.setEnabled(false);
            }
        }
        else if(e.getSource() == checkWord){
            int points = board.CheckWord();
            if(points != 0){
                cur += points;
                curv2.setText(String.valueOf(cur));
                wordsfound++;
                wordsf2.setText(String.valueOf(wordsfound)+"/"+String.valueOf(board.Howmanywords()));
                if(cur >= goal){
                    JOptionPane.showMessageDialog(this, "Game over! You won");
                    System.exit(1);
                }
            }
        }
        else if(e.getSource() == reBoard){
            try {
                board.ReplaceLetters();
            } catch (IOException ex) {
                Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            countB++;
            rB.setText("          "+countB+"/5");
            if(countD == 5){
                reBoard.setEnabled(false);
            }
        }
        
        else if(e.getSource() == reRow){
            //PERNEI TO KEIMENO APTO TEXTFIELD KAI TO 
            //STELNEI SAN ORISMA STHN DELR TOU TAMPLO
            board.setreR(Integer.valueOf(rR1.getText()));
            rR1.setText("0");
            countR++;
            rR.setText(countR+"/3");
            if(countR == 3){
                reRow.setEnabled(false);
                rR1.setEnabled(false);
            }
        }
        
        
    }

    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(isRightMouseButton(e)){
            board.reset();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(isRightMouseButton(e)){
            board.reset();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        return;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(isRightMouseButton(e)){
            board.reset();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        return;
    }
    
    
}
