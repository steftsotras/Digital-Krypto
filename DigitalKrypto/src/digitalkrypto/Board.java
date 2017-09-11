//Tsotras Stefanos icsd13189
//Kokovidi Athina icsd13078
package digitalkrypto;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Collator;
import java.util.*;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Stefanos
 */
public class Board extends JPanel implements ActionListener{
    
    private int x,y;
    private Letter[][] board;
    private ArrayList<String> lex;
    private ArrayList<String> wordstocheck;
    private ArrayList<Letter> letterschecked;
    private List<String> words;
    
    private Hashtable<String,Integer> lexer;
    private ArrayList<Availability> letterstatus;
    private ArrayList<Directions> availablepos;
    
    private int delR;
    private int reR;
    private int reC;
    private boolean reT;
    private boolean swapL;
    private Random rand;
    
    
    
    
    public Board(int x,int y) throws IOException{
        
        delR=0;
        reR=0;
        reC=0;
        reT=false;
        swapL=false;
        
        this.x=x;
        this.y=y;
        board =new Letter[x][y];
        
        rand =new Random();
        letterstatus =new ArrayList<Availability>();
        availablepos =new ArrayList<Directions>();
        lexer = new Hashtable<String,Integer>();
        lex =new ArrayList<String>();
        wordstocheck =new ArrayList<String>();
        letterschecked =new ArrayList<Letter>();
        
        setLayout(new GridLayout(x,y,3,3));
        
        InitializeLex();
        InitializeLexer();
        InitializeBoard();
        
        rePaint();
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                add(board[i][j]);
                board[i][j].addActionListener(this);
            }
        }
    }
    
    public int Howmanywords(){
        return wordstocheck.size();
    }
    
    public void rePaint(){
        revalidate();
        repaint();
    }
    
    //LEXIKO POU PERIEXEI THN VATHMOLOGIA THS KATHE LEKSHS
    public void InitializeLexer(){
        lexer.put("Α", 1);lexer.put("Β", 8); lexer.put("Γ", 4);
        lexer.put("Δ", 4);lexer.put("Ε", 1);lexer.put("Ζ", 8);
        lexer.put("Η", 1);lexer.put("Θ", 8);lexer.put("Ι", 1);
        lexer.put("Κ", 2);lexer.put("Λ", 3); lexer.put("Μ", 3);
        lexer.put("Ν", 1);lexer.put("Ξ", 10);lexer.put("Ο", 1);
        lexer.put("Π", 2);lexer.put("Ρ", 2);lexer.put("Σ", 1);
        lexer.put("Τ", 1);lexer.put("Υ", 2); lexer.put("Φ", 8);
        lexer.put("Χ", 10);lexer.put("Ψ", 10);lexer.put("Ω", 3);
    }
    
    
    //DIAVASMA TWN LEKSEWN POU THA MANTEPSEI O XRHSTHS APO TXT ARXEIO
    public void InitializeLex() throws FileNotFoundException, IOException{
        
        BufferedReader in= new BufferedReader(new FileReader("C:\\Users\\Stefanos\\Documents\\NetBeansProjects\\DigitalKrypto\\ΛΕΞΙΚΟ.txt"));
        String line;
        while((line = in.readLine()) != null){           
            lex.add(line);
        }
        in.close();
        
       
    }
    
    
    public void InitializeBoard(){
        
        //ARXIKA DIALEGW TIS LEKSEIS POU THA
        //PREPEI NA MANTEPSEI 
        ChooseWords(x*y-5);
        
        
        for(int i=0;i<words.size();i++){
            System.out.print(words.get(i)+" ");
        }
        
        
        //DHLWSH TUXAIWN ARITHMWN GIA TON 
        //PROSDIORISMO PLITHOUS TWN DIAFORETIKWN 
        //XRWMATWN-BALADER
        int redcards = rand.nextInt(4);
        int bluecards = rand.nextInt(3);
        int baladercards = rand.nextInt(2);
        
        
        //EISAGWGH STOIXEIWN STON PINAKA
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                
                //EISAGWGH TWN STOIXEIWN ME SEIRA
                if(redcards != 0){
                    board[i][j] = new Red(words.get(0),ValueofLetter(words.get(0)));
                    words.remove(0);
                    redcards--;
                }
                else if(bluecards != 0){
                    board[i][j] = new Blue(words.get(0),ValueofLetter(words.get(0)));
                    words.remove(0);
                    bluecards--;
                }
                else if(baladercards != 0){
                    board[i][j] = new Balader();
                    baladercards--;
                }
                else if(words.size() != 0){
                    board[i][j] = new White(words.get(0),ValueofLetter(words.get(0)));
                    words.remove(0);
                }
                else{
                    //EISAGWGH TYXAIWN GRAMMATWN APTO LEXIKO
                    
                    //RANDOM THESH STO PINAKA
                    int k = rand.nextInt(lexer.size());
                    String letter="";
                    int value=0;
                    int l=0;
                    //PROSPELASH TOU HASHTABLE KAI EISAGWGH TOU SE
                    //PROSWRINO ENTRY
                    for(Map.Entry entry: lexer.entrySet()){
                        //OTAN H RANDOM THESH GINEI IDIA ME 
                        //THN TWRINH THSESH TOTE
                        if(l == k){
                            //TA STOIXEIA AUTOU TOU KELIOU EISAGWNTAI
                            //STON PINAKA
                            value = (Integer) entry.getValue();
                            letter = String.valueOf(entry.getKey());
                            break;
                        }
                        l++;
                    }
                    board[i][j] = new White(letter,value);
                }
                
            } 
        }
        
        
        ShuffleBoard();
        
    }
    
    
    public void Emfanish(){
        System.out.println();
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                System.out.println("Letter : "+board[i][j].getLetter()+"    Value : "+board[i][j].getValue()+"    Color : "+board[i][j].getColor());
            }
        }
        
        for(int i=0;i<wordstocheck.size();i++){
            System.out.println("Word : "+wordstocheck.get(i));
        }
    }
    
    //ANTIKATASTASH TWN GRAMMATWN ME NEA
    public void ReplaceLetters() throws IOException{
        //SVINW TIS LEKSEIS POU HTAN PRIN
        //STON PINAKA
        wordstocheck.clear();
        availablepos =new ArrayList<Directions>();
        letterstatus =new ArrayList<Availability>();
        letterschecked =new ArrayList<Letter>();
        //DIAGRAFH TOU PINAKA KAI
        
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                remove(board[i][j]);
            }
        }
        
        board =new Letter[x][y];
        //EPANADHMIOURGIA TOU
        InitializeBoard();
        Emfanish();
        rePaint();
    }
    
    public void ShuffleBoard(){
        /*
        Random rand =new Random();
        int randnum;
        Letter temp;
        boolean[][] flag= new boolean[x][y];
        
        for(int i=0;i<x;i++){
            while(!Tsek(flag,i)){
                for(int j=0;j<y;j++){
                    //ARXIKA DINW ENA TUXAIO ARITHMO
                    randnum = rand.nextInt(y);
                    //OSO TO FLAG TOU TUXAIOU ARITHMOU
                    //EINAI TRUE TOTE
                    while(flag[i][randnum]){
                        //DWSE ALLO TUXAIO ARITHMO
                        randnum = rand.nextInt(y);
                    }
                    //EFOSON BGHKE APTH WHILE EXOUME
                    //ENAN ARITHMO POU ANTISTOIXEI SE ENA
                    //GRAMMA POU DEN EXEI ALLAKSEI THESH OPOTE
                    
                    //ALLAZEI TUXAIA THESH
                    temp = board[i][j];
                    board[i][j] = board[i][randnum];
                    board[i][randnum]=temp;
                    flag[i][j]=true;
                }
            }
        }
        
        Collections.shuffle(Arrays.asList(board));
        */
        //APO PANW SE SXOLIO EXW THN DIKH MAS ULOPOIHSH ANAKATEMATOS
        //ALLA DEN GINETAI KALA TO ANAKATEMA OPOTE XRHSIMOPOIOUME TELIKA
        //TON ALGORITHMO Fisher–Yates GIA 2D PINAKA
        
        //Fisher–Yates algorithm modified for two-dimensional arrays
        Random random = new Random();

        for (int i = board.length - 1; i > 0; i--) {
            for (int j = board[i].length - 1; j > 0; j--) {
                int m = random.nextInt(i + 1);
                int n = random.nextInt(j + 1);

                Letter temp = board[i][j];
                board[i][j] = board[m][n];
                board[m][n] = temp;
            }
        }
        Emfanish();
        
    }
    
    /*
    
    boolean Tsek(boolean flag[][],int x){


    //GIA OSA EINAI TA GRAMMATA
    //THS SUGKEKRIMENHS GRAMMHS
    for(int i=0;i<y;i++){
        //AN BREIS ESTW KAI ENA GRAMMA
        //POU DEN EXEI ALLAKSEI THESH
        if(!flag[x][i]){
            //EPESTREPSE 0
            return false;
        }
    }
    //AN OLA TA FLAG TWN GRAMMATWN EXOUN TIMH
    //TRUE TOTE OLA EXOUN ALLAKSEI THESH ARA
    //EXEI TELIWSEI ME AUTH THN GRAMMH
    return true;
    }
    */
    
    
    
    //EPISTREFEI THN AKSIA TOU GRAMMATOS
    public int ValueofLetter(String str){
        return lexer.get(str);
    }
    
    
    
    //METHODOS DIALEGMATOS LEKSEWN
    public void ChooseWords(int length){
        
        String lettahs = "";
        //ANAKATEMA GIA NA ERTHEI TYXAIA LEKSH
        Collections.shuffle(lex);
        
        //VAZEI LEKSEIS SE ENA STRING MEXRI AN MPEI KAI H EPOMENH
        //LEKSH NA MHN XWRANE TA GRAMMATA STON PINAKA
        while(lettahs.length()+lex.get(0).length() <= length){
            //PROSTHETW TH LEKSH STH LISTA
            wordstocheck.add(lex.get(0));
            //KAI STO STRING AFERONTAS THN KAI APO
            //TIS DIATHESIMES LEKSEIS
            lettahs = lettahs+lex.remove(0);
            //ANAKATEMA GIA NA ERTHEI TYXAIA LEKSH
            Collections.shuffle(lex);
            
        }
        //DHMIOURGIA LISTAS ME STOIXEIA TO KATHE GRAMMA TOU STRING 
        //POU FTIAXTIKE PROIGOUMENOS
        words = new ArrayList<String>(Arrays.asList(lettahs.split("")));
        //ANAKATEMA THS LISTAS
        Collections.shuffle(words);
        
    }
    
    
    
    public void setdelR(int delR){
        for(int i=0;i<y;i++){
            //EISAGWGH TYXAIWN GRAMMATWN APTO LEXIKO
                    
                    //RANDOM THESH STO PINAKA
                    int k = rand.nextInt(lexer.size());
                    String letter="";
                    int value=0;
                    int l=0;
                    //PROSPELASH TOU HASHTABLE KAI EISAGWGH TOU SE
                    //PROSWRINO ENTRY
                    for(Map.Entry entry: lexer.entrySet()){
                        //OTAN H RANDOM THESH GINEI IDIA ME 
                        //THN TWRINH THESH TOTE
                        if(l == k){
                            //TA STOIXEIA AUTOU TOU KELIOU EISAGWNTAI
                            //STON PINAKA
                            value = (Integer) entry.getValue();
                            letter = String.valueOf(entry.getKey());
                            break;
                        }
                        l++;
                    }
                    
                    board[delR-1][i].setLetter(letter);
                    board[delR-1][i].setValue(value);
                    board[delR-1][i].setText("<html>"+"<font size=8>"+letter+"<font size=4>"+value);
                    
                    
        }
        rePaint();
    }
    
    public void setreR(int reR){
        String letter="";
        int value=0;
        String text="";
        for(int i=0;i<y;i++){
            int k = rand.nextInt(y-1);
            letter=board[reR-1][i].getLetter();
            value=board[reR-1][i].getValue();
            text=board[reR-1][i].getText();
            
            board[reR-1][i].setLetter(board[k][i].getLetter());
            board[reR-1][i].setValue(board[k][i].getValue());
            board[reR-1][i].setText(board[k][i].getText());
            
            board[k][i].setLetter(letter);
            board[k][i].setValue(value);
            board[k][i].setText(text);
            
            
        }
        rePaint();
    }
    public void setreT(boolean reT){
        this.reT=reT;
    }
    public void setrec(int reC){
        this.reR=reC;
    }
    public void setswapL(boolean swapL){
        this.swapL=swapL;
    }
    
    
    
    
    
    public void reset(){
        
        availablepos =new ArrayList<Directions>();
        letterstatus =new ArrayList<Availability>();
        letterschecked =new ArrayList<Letter>();
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                if(board[i][j].getColor() == "Red"){
                            board[i][j].setBackground(new java.awt.Color(250, 0, 0));
                        }
                        else if(board[i][j].getColor() == "Blue"){
                            board[i][j].setBackground(new java.awt.Color(0, 191, 255));
                        }
                        else{
                            board[i][j].setBackground(new java.awt.Color(255, 255, 255));
                        }
                        board[i][j].setChecked(false);
            }
        }
        rePaint();
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                if(e.getSource() == board[i][j]){
                    
                    if(board[i][j].getChecked()){
                        if(board[i][j].getColor() == "Red"){
                            board[i][j].setBackground(new java.awt.Color(250, 0, 0));
                        }
                        else if(board[i][j].getColor() == "Blue"){
                            board[i][j].setBackground(new java.awt.Color(0, 191, 255));
                        }
                        else{
                            board[i][j].setBackground(new java.awt.Color(255, 255, 255));
                        }
                        board[i][j].setChecked(false);
                        for(int k=0;k<letterschecked.size();k++){
                            if(letterschecked.get(k).equals(board[i][j])){
                                letterschecked.remove(k);
                            }
                        }
                        
                    }
                    else{
                        
                        
                        if(letterschecked.isEmpty()){
                            if(board[i][j].getLetter().equals("?")){
                                String newletter = JOptionPane.showInputDialog("Δώσε το γράμμα που θες");
                            }
                            board[i][j].setChecked(true);
                            board[i][j].setBackground(new java.awt.Color(245, 245, 30));
                            letterschecked.add(board[i][j]);
                            letterstatus.add(new Availability(x,y,i,j));
                            setAvailability(letterstatus.get(0));
                        }
                        else{
                            
                            for(int l=0;l<availablepos.size();l++){
                                if(i == availablepos.get(l).getX() && j == availablepos.get(l).getY()){
                                    
                                    //CHECK GIA AN TO SUGKEKRIMENO ANTIKEIMENO UPARXEI
                                    //HDH MES STH LISTA OPOTE DEN 
                                    for(int p=0;p<letterschecked.size();p++){
                                        if(letterschecked.get(p) == (board[i][j]) && letterschecked.get(p).getChecked()){
                                            board[i][j].setChecked(true);
                                            return;
                                        }
                                        else if(letterschecked.get(p) == (board[i][j])){
                                            return;
                                        }
                                    }
                                    
                                    if(board[i][j].getLetter().equals("?")){
                                        String newletter = JOptionPane.showInputDialog("Δώσε το γράμμα που θες");
                                        newletter.toUpperCase();
                                        System.out.println(newletter);
                                        board[i][j].setLetter(newletter);
                                        board[i][j].setValue(ValueofLetter(newletter));
                                        board[i][j].setText(newletter);
                                        rePaint();
                                        
                                    }
                                    
                                    //TO PROHGOUMENO GRAMMA DEN MPOREI NA 
                                    letterschecked.get(letterschecked.size()-1).setChecked(false);
                                    board[i][j].setChecked(true);
                                    board[i][j].setBackground(new java.awt.Color(245, 245, 30));
                                    letterschecked.add(board[i][j]);
                                    Availability av = new Availability(x,y,i,j);
                                    letterstatus.add(av);
                                    setAvailability(av);
                                    break;
                                }
                            }
                                
                                
                        }
                        
                    }
                    
                }
            }
        }
        rePaint();
    }
    
    public void setAvailability(Availability av){
        
        //OI THESEIS POU THA MPOREI NA EPILEKSEI O XRHSTHS
        //AFOU DIALEKSEI TO GRAMMA DIALEGONTE ANALOGA ME TO
        //POU VRISKETE TO SUGKEKRIMENO ANTIKEIMENO
        
        //UPARXOUN 3 PERIPTWSEIS
        
        //NA MHN EXEI DIPLA TOU KANENA TOIXOS ARA MPOREI NA DIALEKSEI 
        //META OLES TIS GEITONIKES THESEIS
        if(av.getCat() == 0){
            for(int i=-1;i<2;i++){
                for(int j=-1;j<2;j++){
                    availablepos.add(new Directions(av.getI()+i,av.getJ()+j));
                }
            }
        }
        
        //NA VRISKETE STO PERITHWRIO TOU TAMPLO ALLA OXI STIS GWNIES
        else if(av.getCat() == 1){
            
            //AN VRISKETE PANW DIALEGEI OLES TIS THESEIS EKTOS APO AYTES POU
            //VRISKONTAI AKRIVWS APO PANW DHLADH i-1
            if(!av.getUp()){
                for(int i=0;i<2;i++){
                    for(int j=-1;j<2;j++){
                        availablepos.add(new Directions(av.getI()+i,av.getJ()+j));
                    }
                }
            }
            
            //ANTISTOIXA DEKSIA TIS j+1
            else if(!av.getRight()){
                for(int i=-1;i<2;i++){
                    for(int j=-1;j<1;j++){
                        availablepos.add(new Directions(av.getI()+i,av.getJ()+j));
                    }
                }
            }
            
            //ARISTERA TIS j-1
            else if(!av.getLeft()){
                for(int i=-1;i<2;i++){
                    for(int j=0;j<2;j++){
                        availablepos.add(new Directions(av.getI()+i,av.getJ()+j));
                    }
                }
            }
            //KAI KATW TIS i+1
            else if(!av.getDown()){
                for(int i=-1;i<1;i++){
                    for(int j=-1;j<2;j++){
                        availablepos.add(new Directions(av.getI()+i,av.getJ()+j));
                    }
                }
            }
        }
        
        //NA VRISKETE SE GWNIA OPOTE MPOREI NA DIALEKSEI META MONO 3 THESEIS 
        //ANALOGA ME THN GWNIA POU VRISKETE
        else{
            if(!av.getUp() && !av.getLeft()){
                availablepos.add(new Directions(av.getI()+1,av.getJ()));
                availablepos.add(new Directions(av.getI()+1,av.getJ()+1));
                availablepos.add(new Directions(av.getI(),av.getJ()+1));
            }
            else if(!av.getUp() && !av.getRight()){
                availablepos.add(new Directions(av.getI(),av.getJ()-1));
                availablepos.add(new Directions(av.getI()+1,av.getJ()-1));
                availablepos.add(new Directions(av.getI()+1,av.getJ()));
            }
            else if(!av.getDown() && !av.getLeft()){
                availablepos.add(new Directions(av.getI()-1,av.getJ()));
                availablepos.add(new Directions(av.getI()-1,av.getJ()+1));
                availablepos.add(new Directions(av.getI(),av.getJ()+1));
            }
            else if(!av.getDown() && !av.getRight()){
                availablepos.add(new Directions(av.getI(),av.getJ()-1));
                availablepos.add(new Directions(av.getI()-1,av.getJ()-1));
                availablepos.add(new Directions(av.getI()-1,av.getJ()));
            }
        }
        
    }
    
    public int CheckWord(){
        
        String s1 = "";
        for (int p = 0; p <letterschecked.size(); p++)
        {
          s1 += letterschecked.get(p).getLetter();
        }
        s1 = sortString(s1);
        String s2 = "";
        for(int o=0;o<wordstocheck.size();o++){
            s2 = wordstocheck.get(o);
            s2 = sortString(s2);
            
            if(s1.equals(s2)){
                JOptionPane.showMessageDialog(this, "Συγχαρητήρια βρήκες τη λέξη: "+wordstocheck.get(o));
                int points = countPoints();
                
                for(int i=0;i<letterschecked.size();i++){
                
                    //EISAGWGH TYXAIWN GRAMMATWN APTO LEXIKO
                    
                    //RANDOM THESH STO PINAKA
                    int k = rand.nextInt(lexer.size());
                    String letter="";
                    int value=0;
                    int l=0;
                    //PROSPELASH TOU HASHTABLE KAI EISAGWGH TOU SE
                    //PROSWRINO ENTRY
                    for(Map.Entry entry: lexer.entrySet()){
                        //OTAN H RANDOM THESH GINEI IDIA ME 
                        //THN TWRINH THESH TOTE
                        if(l == k){
                            //TA STOIXEIA AUTOU TOU KELIOU EISAGWNTAI
                            //STON PINAKA
                            value = (Integer) entry.getValue();
                            letter = String.valueOf(entry.getKey());
                            break;
                        }
                        l++;
                    }
                    
                    letterschecked.get(i).setLetter(letter);
                    letterschecked.get(i).setValue(value);
                    letterschecked.get(i).setText("<html>"+"<font size=8>"+letter+"<font size=4>"+value);
                }
                
                reset();
                return points;
            }
            
        }
        JOptionPane.showMessageDialog(this, "Δυστυχώς η λέξη δεν βρέθηκε");
        return 0;
        
    }
    
    public String sortString(String s){
        
        Collator col = Collator.getInstance(new Locale("en", "EN"));
        String[] s1= s.split("");
        Arrays.sort(s1, col);
        String sorted = "";
        for (int i = 0; i < s1.length; i++)
        {
          sorted += s1[i];
        }
        return sorted;
    }
    
    public int countPoints(){
        
        int points=0;
        boolean dub=false;
        
        for(int i=0;i<letterschecked.size();i++){
            if(letterschecked.get(i).getColor() == "Blue"){
                dub=true;
            }
            points += letterschecked.get(i).getFinalValue();
        }
        if(dub){
            points = points*2;
        }
        return points;
        
    }
    
    
    }

    
    

