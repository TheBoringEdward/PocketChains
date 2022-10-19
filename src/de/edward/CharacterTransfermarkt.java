package de.edward;

//TODO: The input file should be encoded in ISO-8859-15.
// The editor kate allows to choose the encoding manually.

// A fully-worked out example for a doubly linked list

// Klaus Wiele September 2022

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CharacterTransfermarkt extends JFrame{

    private List<Character> my_list = new List<Character>();
    private RandomAccessFile database;
    private JTextField JTF_name;
    private JTextField JTF_clas;
    private JTextField JTF_subclas;
    private JTextField JTF_hlth;
    private JTextArea JTA_output;
    private JButton JB_append;
    private JButton JB_print;
    private JButton JB_clear;
    private JButton JB_save;

    CharacterTransfermarkt(){

        // set up the text fields
        JTF_name = new JTextField("name");
        JTF_clas = new JTextField("class");
        JTF_subclas = new JTextField("subclass");
        JTF_hlth = new JTextField("health");

        // set up the output field
        // The JTextArea needs a surrounding JScrollPane for scrolling.
        JTA_output = new JTextArea("");
        JTA_output.setLineWrap(true);
        JTA_output.setWrapStyleWord(true);
        JScrollPane JSP_scroll = new JScrollPane(JTA_output);

        // set up the buttons, each with an action listener
        JB_print = new JButton("print");
        JB_print.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JTA_output.setText( my_list.toString() );
            }
        });
        JB_clear = new JButton("clear");
        JB_clear.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JTF_name.setText("name");
                JTF_clas.setText("class");
                JTF_subclas.setText("subclass");
                JTF_hlth.setText("health");
                JTA_output.setText("");
            }
        });
        JB_append = new JButton("append");
        JB_append.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String name = JTF_name.getText();
                String clas = JTF_clas.getText();
                String subclass = JTF_subclas.getText();
                int value = -1;
                try{
                    value = Integer.valueOf( JTF_hlth.getText() );
                }catch( NumberFormatException nf ){
                    JTA_output.setText("I cannot read the character's value.");
                }
                if( value>=0 ){
                    Character character = new Character( name, clas ,subclass, value );
                    my_list.append( character );
                    JTF_name.setText("name");
                    JTF_clas.setText("class");
                    JTF_subclas.setText("subclass");
                    JTF_hlth.setText("health");
                }
            }
        });
        JB_save = new JButton("save");
        JB_save.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    // delete all content
                    database.setLength(0);
                    // write the new data
                    database.writeBytes( my_list.toString() );
                }catch( IOException io ){
                    JTA_output.setText("I cannot save the data.");
                }
            }
        });

        // All buttons are grouped in one JPanel.
        JPanel JP_buttons = new JPanel( new FlowLayout() );
        JP_buttons.add( JB_append );
        JP_buttons.add( JB_print );
        JP_buttons.add( JB_clear );
        JP_buttons.add( JB_save );

        // add the buttons and the text fields to the JFrame
        setLayout( new GridLayout(6,1) );
        add( JTF_name );
        add(JTF_clas);
        add(JTF_subclas);
        add(JTF_hlth);
        add( JSP_scroll );
        add( JP_buttons );

        // open the input file for reading and writing
        try{
            database = new RandomAccessFile("Characterdata.dat","rw");
        }catch( FileNotFoundException fnf ){
            System.out.println("\n File not found.\n\n");
            JTA_output.setText("I cannot find the input file.");
        }

        // read data from input file
        boolean eof = false;    // Are we at the end of the file?
        while( !eof ){
            String name="";
            String clas="";
            String subclas="";
            int value = -1;
            try{
                name = database.readLine();
                clas = database.readLine();
                subclas = database.readLine();
                String s = database.readLine();
                if( s!=null ){
                    value = Integer.valueOf(s);
                }
                database.readLine();
            }catch( IOException io ){
                System.out.println("\n\n Error reading the data file.\n\n");
                JTA_output.setText("Error reading the data file.");
            }
            if( name==null || value==-1 || clas==null || subclas==null){
                eof = true; // We have reached the end of the file.
            }else{
                Character p = new Character( name, clas ,subclas, value );
                my_list.append( p );
            }
        }

    } // end of constructor

    public static void main(String [] args){
        CharacterTransfermarkt t = new CharacterTransfermarkt();
        t.setSize(600,600);
        t.setResizable(false);
        t.setTitle("CharacterTransfermarkt");
        t.setVisible(true);
        System.out.println("\n\n ======= Transfermarkt2 =======\n");
        System.out.println("\n\n ======= This code has been partially provided by TheBoringEdward =======\n");
    }

}