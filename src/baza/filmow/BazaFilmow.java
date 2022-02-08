package baza.filmów;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.ArrayList;


import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class BazaFilmów extends JFrame{
    
    int Rok = 0;
    int dodS = 0;
    int dodF = 0;
    int dodO = 0;
    public boolean czyMyszNaMenu;
       
    JPanel              PanelOne        = new JPanel();
    JPanel              PanelFilter     = new JPanel();
    
    JLabel              EtykietaDo      = new JLabel("Do obejrzenia");
    JLabel              EtykietaOb      = new JLabel("Obejrzane");
    JLabel              EtykietaS       = new JLabel("Seriale");
    
    JLabel              E_Tytul         = new JLabel("Tytuł");
    JLabel              E_Rezyser       = new JLabel("Reżyser");
    JLabel              E_RokProdukcji  = new JLabel("Rok Produkcji");
    JLabel              E_Odcinek       = new JLabel("Odcinek");
    JLabel              E_Sezon         = new JLabel("Sezon");
    JLabel              E_Rodzaj        = new JLabel("Gatunek");

    JTextField          TytulFilmu      = new JTextField(15);
    JTextField          Rezyser         = new JTextField(10);
    JComboBox           RokProdukcji    = new JComboBox();
    
    JComboBox           Rodzaj          = new JComboBox();
    JRadioButton        Film            = new JRadioButton("Film");
    JRadioButton        Serial          = new JRadioButton("Serial");
    JFormattedTextField Sezon           = new JFormattedTextField();
    JFormattedTextField Odcinek         = new JFormattedTextField();
   
    JTextArea           WidokListyDo    = new JTextArea();
    JTextArea           WidokListyOb    = new JTextArea();
    JTextArea           WidokListyS     = new JTextArea();
    
    JButton             Dodaj           = new JButton ("Dodaj");
    JButton             Obejrzany       = new JButton ("Obejrzany");
    
    JButton             Inne            = new JButton ("Inne");
    
    JButton             Akcja           = new JButton ("Akcja");
    JButton             Animowany       = new JButton ("Animowany");
    JButton             Dramat          = new JButton ("Dramat");
    JButton             Dokumentalny    = new JButton ("Dokumentalny");
    JButton             Familijny       = new JButton ("Familijny");
    JButton             Fantasy         = new JButton ("Fantasy");
    JButton             Historyczny     = new JButton ("Historyczny");
    JButton             Horror          = new JButton ("Horror");
    JButton             Katastroficzny  = new JButton ("Katastroficzny");
    JButton             Komedia         = new JButton ("Komedia");
    JButton             Kostiumowy      = new JButton ("Kostiumowy");
    JButton             Kryminał        = new JButton ("Kryminał");
    JButton             Musical         = new JButton ("Musical");
    JButton             Obyczajowy      = new JButton ("Obyczajowy");
    JButton             Przygodowy      = new JButton ("Przygodowy");
    JButton             SciFi           = new JButton ("SciFi");
    JButton             Sensacyjny      = new JButton ("Sensacyjny");
    JButton             Thriller        = new JButton ("Thriller");
    JButton             Western         = new JButton ("Western");
    JButton             Wojenny         = new JButton ("Wojenny");
    JButton             Wszystkie       = new JButton ("Wszystkie");
    
    JPopupMenu          PPM_Do          = new JPopupMenu();
    JPopupMenu          PPM_Ob          = new JPopupMenu();
    JPopupMenu          PPM_Se          = new JPopupMenu();
    
    List                ListaDo         = new List(20);
    List                ListaOb         = new List(20);
    List                ListaS          = new List(20);

    Font                czcionka        = new Font("Arial", Font.BOLD, 12);
       
    Date                Data        = new Date();
    Calendar            kalendarz   = Calendar.getInstance();
    SimpleDateFormat    dataFormat  = new SimpleDateFormat("yyyy");
    
    
    
    GridBagConstraints gbc = new GridBagConstraints();
   
    public BazaFilmów() throws IOException{

        
        // Ustawienie layoutu dla całego okna
        GridBagLayout Layout = new GridBagLayout();
        setLayout(Layout);
        
        File plikDo = new File("ListaDo.txt");
        File plikOb = new File("ListaOb.txt");
        File plikS  = new File("ListaS.txt");
        
        if(plikDo.exists()==false){
            plikDo.createNewFile();
        }
        
        if(plikOb.exists()==false){
            plikOb.createNewFile();
        }
        
        if(plikS.exists()==false){
            plikS.createNewFile();
        }               
        
        FileReader plikDoCzyt   = new FileReader(plikDo);
        FileReader plikObCzyt   = new FileReader(plikOb);
        FileReader plikSCzyt    = new FileReader(plikS);
        
        BufferedReader DoCzytBuf    = new BufferedReader(plikDoCzyt);
        BufferedReader ObCzytBuf    = new BufferedReader(plikObCzyt);
        BufferedReader SCzytBuf     = new BufferedReader(plikSCzyt);
        
        String  czytDo  = DoCzytBuf.readLine();
        String  czytOb  = ObCzytBuf.readLine();
        String  czytS   = SCzytBuf.readLine();
  
        while(czytDo!=null){
            ListaDo.add(czytDo);
            czytDo = DoCzytBuf.readLine();
        }
        
        while(czytOb!=null){
            ListaOb.add(czytOb);
            czytOb = ObCzytBuf.readLine();
        }
        
        while(czytS!=null){
            ListaS.add(czytS);
            czytS = SCzytBuf.readLine();
        }
            
        plikDoCzyt.close();
        DoCzytBuf.close();
        plikObCzyt.close();
        ObCzytBuf.close();
        plikSCzyt.close();        
        SCzytBuf.close();  
        
        Rok = kalendarz.get(kalendarz.YEAR);
               
        // Uzupełnienie list rowzijanych
        RokProdukcji.addItem("");
        RokProdukcji.setLightWeightPopupEnabled(false);
            for(int i = Rok; i > 1900-1 ; i--){
                RokProdukcji.addItem(i);
            }
            
        Rodzaj.setLightWeightPopupEnabled(false);
        Rodzaj.addItem("");
        Rodzaj.addItem("Akcja");
        Rodzaj.addItem("Animowany");
        Rodzaj.addItem("Dramat");
        Rodzaj.addItem("Dokumentalny");
        Rodzaj.addItem("Familijny");
        Rodzaj.addItem("Fantasy");
        Rodzaj.addItem("Historyczny");
        Rodzaj.addItem("Horror");
        Rodzaj.addItem("Katastroficzny");
        Rodzaj.addItem("Komedia");
        Rodzaj.addItem("Kostiumowy");
        Rodzaj.addItem("Kryminał");
        Rodzaj.addItem("Musical");
        Rodzaj.addItem("Obyczajowy");
        Rodzaj.addItem("Przygodowy");
        Rodzaj.addItem("SciFi");
        Rodzaj.addItem("Sensacyjny");
        Rodzaj.addItem("Thriller");
        Rodzaj.addItem("Western");
        Rodzaj.addItem("Wojenny");
        Rodzaj.addItem("Wszystkie");
        Rodzaj.addItem("Inne");

        // Setting wszystkich komponentów    
        
        PPM_Do.setLightWeightPopupEnabled( false );
        PPM_Ob.setLightWeightPopupEnabled( false );
        PPM_Se.setLightWeightPopupEnabled( false );
        
        JMenuItem EdytujD    = new JMenuItem("Edytuj");
        JMenuItem EdytujO    = new JMenuItem("Edytuj");
        JMenuItem EdytujS    = new JMenuItem("Edytuj");
        
        JMenuItem UsunD      = new JMenuItem("Usuń");
        JMenuItem UsunO      = new JMenuItem("Usuń");
        JMenuItem UsunS      = new JMenuItem("Usuń");
        
//        Sezon.addMouseListener(new MouseAdapter(){
//            public void mousePressed(MouseEvent ME){
//                if(SwingUtilities.isLeftMouseButton(ME)){
//
//                }
//            }
//        });
//        
//        Odcinek.addMouseListener(new MouseAdapter(){
//            public void mousePressed(MouseEvent ME){
//                if(SwingUtilities.isLeftMouseButton(ME)){
//                }
//            }
//        });
        
        EdytujD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String temp = Edytuj(ListaDo.getSelectedItem());
                Usun(ListaDo, ListaDo.getSelectedIndex(), "ListaDo.txt", ListaDo.getSelectedItem());
                Dodaj(ListaDo, "ListaDo.txt", temp);
            
            }
        });
        
        EdytujO.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String temp = Edytuj(ListaOb.getSelectedItem());
                Usun(ListaOb, ListaOb.getSelectedIndex(), "ListaOb.txt", ListaOb.getSelectedItem());
                Dodaj(ListaOb, "ListaOb.txt", temp);
             
            }
        });
                
        EdytujS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String temp = Edytuj(ListaS.getSelectedItem());
                Usun(ListaS, ListaS.getSelectedIndex(), "ListaS.txt", ListaS.getSelectedItem());
                Dodaj(ListaS, "ListaS.txt", temp);
            }
        });
        
        UsunD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(ListaDo.getSelectedItem()!=null)
                {
                    int yn = JOptionPane.showConfirmDialog(
                            PanelOne, "Czy napewno chcesz usunąć "
                            + ListaDo.getSelectedItem()+ "?","Usuń",
                                 JOptionPane.YES_NO_OPTION);
                    if(yn == JOptionPane.YES_OPTION) {
                         Usun(ListaDo, ListaDo.getSelectedIndex(), "ListaDo.txt", ListaDo.getSelectedItem());
                    }
                }
            }
        });
        
        UsunO.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               if(ListaOb.getSelectedItem()!=null)
               {
                    int yn = JOptionPane.showConfirmDialog(
                            PanelOne, "Czy napewno chcesz usunąć "
                            + ListaOb.getSelectedItem()+ "?","Usuń",
                                 JOptionPane.YES_NO_OPTION);
                    if(yn == JOptionPane.YES_OPTION) {
                         Usun(ListaOb, ListaOb.getSelectedIndex(), "ListaOb.txt", ListaOb.getSelectedItem());
                    }
               }
            }
        });
        
        UsunS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    int yn = JOptionPane.showConfirmDialog(
                            PanelOne, "Czy napewno chcesz usunąć "
                            + ListaS.getSelectedItem()+ "?","Usuń",
                                 JOptionPane.YES_NO_OPTION);
                    if(yn == JOptionPane.YES_OPTION) {
                         Usun(ListaS, ListaS.getSelectedIndex(), "ListaS.txt", ListaS.getSelectedItem());
                    }              
            }
        });
        
        JMenuItem ObejrzyjPonownie = new JMenuItem("Obejrzyj ponownie");
        ObejrzyjPonownie.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Przerzuc(ListaOb,ListaDo,"ListaOb.txt", "ListaDo.txt");
              }
        });
   
        PPM_Do.add(EdytujD);
        PPM_Do.add(UsunD);
        PPM_Ob.add(EdytujO);
        PPM_Ob.add(UsunO);
        PPM_Ob.add(ObejrzyjPonownie);
        PPM_Se.add(EdytujS);
        PPM_Se.add(UsunS);

        
        
        
        ListaDo.setFont(czcionka);
        ListaOb.setFont(czcionka);
        ListaOb.setForeground(Color.gray);
        ListaS.setFont(czcionka);
        
        Sezon.setValue(new Integer(0));
        Sezon.setColumns(2);
        Odcinek.setValue(new Integer(0));
        Odcinek.setColumns(2);
        Sezon.setValue(null);
        Odcinek.setValue(null);
    
        ButtonGroup filmSerial = new ButtonGroup();
        filmSerial.add(Film);
        filmSerial.add(Serial);
        
        Film.setSelected(true);
        
        E_Odcinek.setEnabled(false);
        Odcinek.setEnabled(false);
        E_Sezon.setEnabled(false);
        Sezon.setEnabled(false);
        
        // Ustawianie paneli
        PanelOne.setLayout(Layout);
        PanelFilter.setLayout(Layout);
        
        // PanelOne ustawianie komponentów
                gbc.insets = new Insets(1, 1, 1, 1);
                gbc.fill=GridBagConstraints.HORIZONTAL;
                gbc.gridx=0;
                gbc.gridy=0;
            PanelFilter.add(Akcja, gbc);
                gbc.gridy=1;
            PanelFilter.add(Sensacyjny, gbc);
                gbc.gridy=2;
            PanelFilter.add(Katastroficzny, gbc);
                gbc.gridy=3;
            PanelFilter.add(Dramat, gbc);
                gbc.gridy=4;
            PanelFilter.add(Komedia, gbc);
                gbc.gridy=5;
            PanelFilter.add(SciFi, gbc);
                gbc.gridy=6;
            PanelFilter.add(Fantasy, gbc);
                gbc.gridy=7;
            PanelFilter.add(Przygodowy, gbc);
                gbc.gridy=8;
            PanelFilter.add(Obyczajowy, gbc);
                gbc.gridy=9;
            PanelFilter.add(Kostiumowy, gbc);
                gbc.gridy=10;
            PanelFilter.add(Familijny, gbc);
            
                gbc.gridx=1;
                gbc.gridy=0;
            PanelFilter.add(Western, gbc);
                gbc.gridy=1;
            PanelFilter.add(Wojenny, gbc);
                gbc.gridy=2;
            PanelFilter.add(Musical, gbc);
                gbc.gridy=3;
            PanelFilter.add(Kryminał, gbc);
                gbc.gridy=4;
            PanelFilter.add(Animowany, gbc);
                gbc.gridy=5;
            PanelFilter.add(Dokumentalny, gbc);
                gbc.gridy=6;
            PanelFilter.add(Historyczny, gbc);
                gbc.gridy=7;
            PanelFilter.add(Thriller, gbc);
                gbc.gridy=8;
            PanelFilter.add(Horror, gbc);
                gbc.gridy=9;
            PanelFilter.add(Inne, gbc);
            gbc.gridy=10;
            PanelFilter.add(Wszystkie,gbc);
            
            gbc.insets = new Insets(30, 5, 5, 5);
            
                gbc.gridx=14;
                gbc.gridy=0;
                gbc.gridwidth=1;
                gbc.gridheight=2;
                gbc.fill = GridBagConstraints.VERTICAL;
            PanelOne.add(PanelFilter, gbc);
            
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.gridheight=1;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            gbc.gridx=0;
            gbc.gridy=3;
        PanelOne.add(E_Tytul, gbc);
        
            
            gbc.gridy=4;
        PanelOne.add(TytulFilmu, gbc);
        
            gbc.gridx=1;
            gbc.gridy=3;
        PanelOne.add(E_Rezyser, gbc);
        
            gbc.gridy=4;
        PanelOne.add(Rezyser, gbc);
        
            gbc.gridx=2;
            gbc.gridy=3;
        PanelOne.add(E_RokProdukcji, gbc);
        
            gbc.gridy=4;
        PanelOne.add(RokProdukcji, gbc);
        
            gbc.gridx=3;
            gbc.gridy=3;
        PanelOne.add(E_Rodzaj, gbc);
        
            gbc.gridy=4;
        PanelOne.add(Rodzaj, gbc);
        
            gbc.gridx=4;
            gbc.gridy=3;
        PanelOne.add(Film, gbc);
        
            gbc.gridy=4;
        PanelOne.add(Serial, gbc);
        
            gbc.gridx = 5;
            gbc.gridy = 3;
        PanelOne.add(E_Sezon, gbc);
        
            gbc.gridy=4;
        PanelOne.add(Sezon, gbc);
        
            gbc.gridx=6;
            gbc.gridy=3;
        PanelOne.add(E_Odcinek,gbc);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridwidth=1;
            gbc.gridy=4;
        PanelOne.add(Odcinek, gbc);
        
            gbc.gridx=7;
            gbc.gridy=3;
            gbc.gridheight = 2;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.BOTH;
        PanelOne.add(Dodaj, gbc);
        
        //PanelTwo wstawianie komponentów
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            
            gbc.gridx=0;
            gbc.gridy=0;     
        PanelOne.add(EtykietaOb, gbc);

            gbc.gridy=1;      
        PanelOne.add(ListaOb, gbc);
                  
            gbc.gridx=2;
            gbc.gridy=0; 
        PanelOne.add(EtykietaDo, gbc);
     
            gbc.gridwidth = 3;
            gbc.gridx=1;
            gbc.gridy=1; 
        PanelOne.add(ListaDo, gbc);
        
            gbc.gridwidth = 5;
            gbc.gridx=4;
            gbc.gridy=0; 
        PanelOne.add(EtykietaS, gbc);
            
            gbc.gridy=1; 
        PanelOne.add(ListaS, gbc);
        
            gbc.gridheight = 1;
            gbc.gridwidth = 3;

            gbc.gridx=1;
            gbc.gridy=2;
        PanelOne.add(Obejrzany, gbc);
        
        // Ustawianie wyglądu głównego okna
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.fill = ABORT;
            gbc.gridx=0;
            gbc.gridy=0;
        this.add(PanelOne, gbc);
        
        // Wszystkie listenery do buttonów  
                
        Serial.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent ME){
                if(SwingUtilities.isLeftMouseButton(ME)){
                    Odcinek.setEnabled(true);
                    Sezon.setEnabled(true);
                    E_Sezon.setEnabled(true);
                    E_Odcinek.setEnabled(true);
                }
            }
        });
        
        Film.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent ME){
                if(SwingUtilities.isLeftMouseButton(ME)){
                    E_Sezon.setEnabled(false);
                    E_Odcinek.setEnabled(false);
                    Odcinek.setEnabled(false);
                    Sezon.setEnabled(false);
                    Sezon.setValue(null);
                    Odcinek.setValue(null);
                }
            }
        });
        
//Wszystkie buttony filtru
            Akcja.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent ME){
                    
                    String nazwa = ", Akcja";
                    if(SwingUtilities.isLeftMouseButton(ME)){
                        czytajDo(nazwa);
                    }
                }
            }); 
            
            Animowany.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent ME){
                    
                    String nazwa = ", Animowany";
                    if(SwingUtilities.isLeftMouseButton(ME)){
                        czytajDo(nazwa);
                    }
                }
            });
            
            Dramat.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent ME){
                    
                    String nazwa = ", Dramat";
                    if(SwingUtilities.isLeftMouseButton(ME)){
                        czytajDo(nazwa);
                    }
                }
            });
            
            Dokumentalny.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent ME){
                    
                    String nazwa = ", Dokumentalny";
                    if(SwingUtilities.isLeftMouseButton(ME)){
                        czytajDo(nazwa);
                    }
                }
            });
            
            Familijny.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent ME){
                    
                    String nazwa = ", Familijny";
                    if(SwingUtilities.isLeftMouseButton(ME)){
                        czytajDo(nazwa);
                    }
                }
            });
            
            Fantasy.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent ME){
                    
                    String nazwa = ", Fantasy";
                    if(SwingUtilities.isLeftMouseButton(ME)){
                        czytajDo(nazwa);
                    }
                }
            });
            
            Historyczny.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent ME){
                    
                    String nazwa = ", Historyczny";
                    if(SwingUtilities.isLeftMouseButton(ME)){
                        czytajDo(nazwa);
                    }
                }
            });
            
            Horror.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent ME){
                    
                    String nazwa = ", Horror";
                    if(SwingUtilities.isLeftMouseButton(ME)){
                        czytajDo(nazwa);
                    }
                }
            });
            
            Katastroficzny.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent ME){
                    
                    String nazwa = ", Katastroficzny";
                    if(SwingUtilities.isLeftMouseButton(ME)){
                        czytajDo(nazwa);
                    }
                }
            });
            
            Komedia.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent ME){
                    
                    String nazwa = ", Komedia";
                    if(SwingUtilities.isLeftMouseButton(ME)){
                        czytajDo(nazwa);
                    }
                }
            });
            
            Kryminał.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent ME){
                    
                    String nazwa = ", Kryminał";
                    if(SwingUtilities.isLeftMouseButton(ME)){
                        czytajDo(nazwa);
                    }
                }
            });
            
            Musical.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent ME){
                    
                    String nazwa = ", Musical";
                    if(SwingUtilities.isLeftMouseButton(ME)){
                        czytajDo(nazwa);
                    }
                }
            });
            
            Obyczajowy.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent ME){
                    
                    String nazwa = ", Obyczajowy";
                    if(SwingUtilities.isLeftMouseButton(ME)){
                        czytajDo(nazwa);
                    }
                }
            });
            
            Przygodowy.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent ME){
                    
                    String nazwa = ", Przygodowy";
                    if(SwingUtilities.isLeftMouseButton(ME)){
                        czytajDo(nazwa);
                    }
                }
            });
            
            SciFi.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent ME){
                    
                    String nazwa = ", SciFi";
                    if(SwingUtilities.isLeftMouseButton(ME)){
                        czytajDo(nazwa);
                    }
                }
            });
            
            Sensacyjny.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent ME){                   
                    String nazwa = ", Sensacyjny";
                    if(SwingUtilities.isLeftMouseButton(ME)){
                        czytajDo(nazwa);
                    }
                }
            });
            
            Thriller.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent ME){
                    String nazwa = ", Thriller";
                    if(SwingUtilities.isLeftMouseButton(ME)){
                        czytajDo(nazwa);
                    }
                }
            });
            
            Western.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent ME){
                    String nazwa = ", Western";
                    if(SwingUtilities.isLeftMouseButton(ME)){
                        czytajDo(nazwa);
                    }
                }
            });
            
            Wojenny.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent ME){
                    String nazwa = ", Wojenny";
                    if(SwingUtilities.isLeftMouseButton(ME)){
                        czytajDo(nazwa);
                    }
                }
            });
            
        Wszystkie.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent ME){
                czytajDo("");
            }
        });
        
        this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent ME){
                
                PPM_Do.setVisible(false);
            }
        });
        // Do obejrzenia
        ListaDo.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent ME){
                    if (ME.isPopupTrigger()) {
                        PPM_Do.show(ME.getComponent(), ME.getX(), ME.getY());
                    }
                }
        });
        
        ListaDo.addMouseListener(new MouseAdapter(){
            public void mouseReleased(MouseEvent ME){
                if(ListaDo.getSelectedItem()!=null){
                    if (ME.isPopupTrigger()){
                        PPM_Do.show(ME.getComponent(), ME.getX(), ME.getY());
                    }
                }
            }
            
        });
        // Obejrzane
        ListaOb.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent ME){
                if (ME.isPopupTrigger()) {
                    PPM_Ob.show(ME.getComponent(), ME.getX(), ME.getY());
                }
            }
        });
        
        ListaOb.addMouseListener(new MouseAdapter(){
            public void mouseReleased(MouseEvent ME){
                if(ListaOb.getSelectedItem()!=null)
                {
                    if (ME.isPopupTrigger()) {
                        PPM_Ob.show(ME.getComponent(), ME.getX(), ME.getY());
                    }
                }
            }
        });
        // Seriale
        ListaS.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent ME){
                    if (ME.isPopupTrigger()) {
                        PPM_Se.show(ME.getComponent(), ME.getX(), ME.getY());
                    }
            }
        });
        
        ListaS.addMouseListener(new MouseAdapter(){
            public void mouseReleased(MouseEvent ME){
                if(ListaS.getSelectedItem()!=null)
                {
                    if (ME.isPopupTrigger()) {
                        PPM_Se.show(ME.getComponent(), ME.getX(), ME.getY());
                    }
                }
            }
        });
        
        // Obejrzany, dodaj itd.

        Obejrzany.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent ME){
                if(SwingUtilities.isLeftMouseButton(ME)){
                    if(ListaDo.getSelectedItem()!=null){
                        Przerzuc(ListaDo,ListaOb,"ListaDo.txt","ListaOb.txt");
                     }
                }
            }
        });
        
        Dodaj.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent ME){
                if(SwingUtilities.isLeftMouseButton(ME))
                {
                    String element;
                    if(Film.isSelected()==true){
                        element=TytulFilmu.getText().toUpperCase() + ", " + Rezyser.getText() + ", " + RokProdukcji.getSelectedItem() + ", " + Rodzaj.getSelectedItem();
                        //JOptionPane.showMessageDialog(PanelOne,"<html><font face='Arial' size='8' color='black'>Wpisz tytuł filmu");
                        boolean czyJestF=false;
                        for(int i = 0 ; i < ListaDo.getItemCount(); i++)
                        {
                            if(ListaDo.getItem(i).equals(element))
                            {
                                czyJestF=true;
                            }
                        }
                        for(int i = 0 ; i < ListaOb.getItemCount(); i++)
                        {
                            if(ListaOb.getItem(i).equals(element))
                            {
                                czyJestF=true;
                            }
                        }
                        if(czyJestF)
                        {
                            JOptionPane.showMessageDialog(PanelOne,"<html><font face='Arial' size='5' color='black'>Już masz tę pozycję na liście");       
                        }
                        else
                        {
                            Dodaj(ListaDo,"ListaDo.txt", element);     
                        }
                    }
                    if(Serial.isSelected()==true){
                        element = TytulFilmu.getText().toUpperCase() + ", " + Rezyser.getText() + ", " + RokProdukcji.getSelectedItem() + ", " + Rodzaj.getSelectedItem() + ", " + Sezon.getText() + ", " + Odcinek.getText();
                        boolean czyJestS=false;
                        for(int i = 0 ; i < ListaS.getItemCount(); i++)
                        {
                            if(ListaS.getItem(i).equals(element))
                            {
                                czyJestS=true;
                            }
                        }
                        if(czyJestS)
                        {
                            JOptionPane.showMessageDialog(PanelOne,"<html><font face='Arial' size='5' color='black'>Już masz tę pozycję na liście");       
                        }
                        else
                        {
                            Dodaj(ListaS, "ListaS.txt", element);
                        }
                    }
                    TytulFilmu.setText(null);
                    Rezyser.setText(null);
                    RokProdukcji.setSelectedIndex(0);
                    Rodzaj.setSelectedIndex(0);
                    Sezon.setValue(null);
                    Odcinek.setValue(null);   
                } 
            }
        });  
        
     
    }
    
    public static void main(String[] args) throws IOException{
        
        BazaFilmów Okno = new BazaFilmów();
        Okno.setTitle("Baza filmów");
        Okno.setSize(1000,500);
        Okno.setLocation(40,200);
        Okno.setResizable(false);
        Okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Okno.setVisible(true);        
    }
    
    public void Usun(List Lista, int Index, String file, String lineToRemove){
        Lista.remove(Index);
        try {
            File inFile = new File(file);
            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return;
            }
            //Construct the new file that will later be renamed to the original filename. 
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
            BufferedReader br = new BufferedReader(new FileReader(file));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
            String line;
            //Read from the original file and write to the new 
            //unless content matches data to be removed.
            while ((line = br.readLine()) != null) {
                if (!line.equals(lineToRemove)) {
                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();
 
            //Delete the original file
            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inFile))
                System.out.println("Could not rename file");

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void Dodaj(List Lista, String Plik, String element){
        Lista.add(element);
        File plik = new File(Plik);
        try{
            FileWriter write = new FileWriter(plik, true);
            BufferedWriter Buf = new BufferedWriter(write);
            Buf.write(element);
            Buf.newLine();
            Buf.flush();
            Buf.close();
            write.close();
        }
        catch(IOException e){   
        }
    }
    public void Przerzuc(List ListaZ, List ListaD, String PlikListaZ, String PlikListaD){
        ListaD.add(ListaZ.getSelectedItem());
        File plik = new File(PlikListaD);
        try{
            FileWriter write = new FileWriter(plik, true);
            BufferedWriter Buf = new BufferedWriter(write);
            Buf.write(ListaZ.getSelectedItem());
            Buf.newLine();
            Buf.flush();
            Buf.close();
            write.close();
        }
        catch(IOException e){   
        }
        Usun(ListaZ, ListaZ.getSelectedIndex(), PlikListaZ, ListaZ.getSelectedItem());
    }
    
    public String Edytuj(String selectedItem){
        String s = (String)JOptionPane.showInputDialog(
            PanelOne,
            "",
            "Edytuj",
            JOptionPane.PLAIN_MESSAGE,
            null,
            null,
            selectedItem);
        
        while(s.length() == 0) {
            s = (String)JOptionPane.showInputDialog(
            PanelOne,
            "PISZ POPRAWNIE!",
            "Edytuj",
            JOptionPane.PLAIN_MESSAGE,
            null,
            null,
            selectedItem);
        }
        return s;
    }
    
    public void czytajDo(String filtr){
        ListaDo.removeAll();
        String czyt;
        File plik = new File("ListaDo.txt");
        try{
            FileReader read = new FileReader(plik);
            BufferedReader buf = new BufferedReader(read);
            czyt = buf.readLine();
            while(czyt!=null){
                if (czyt.contains(filtr)){
                    ListaDo.add(czyt); 
                }
                czyt = buf.readLine();
            }
        buf.close();
        read.close();
        }
        catch(IOException e){           
        }
    }
}