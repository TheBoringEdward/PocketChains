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

    private List<Player> my_list = new List<Player>();
    private List<Character> my_second_list = new List<Character>();
    private RandomAccessFile database;
    private JTextField JTF_name;
    private JTextField JTF_value;
    private JTextField JTF_team;
    private JTextArea JTA_output;
    private JButton JB_append;
    private JButton JB_print;
    private JButton JB_clear;
    private JButton JB_save;

    CharacterTransfermarkt(){

        // set up the text fields
        JTF_name = new JTextField("name");
        JTF_value = new JTextField("value");
        JTF_team = new JTextField("team");

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
                JTF_value.setText("value");
                JTF_team.setText("team");
                JTA_output.setText("");
            }
        });
        JB_append = new JButton("append");
        JB_append.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String name = JTF_name.getText();
                double value = -1.;
                try{
                    value = Double.valueOf( JTF_value.getText() );
                }catch( NumberFormatException nf ){
                    JTA_output.setText("I cannot read the player's value.");
                }
                String team = JTF_team.getText();
                if( value>=0 ){
                    Player player = new Player( name, value, team );
                    my_list.append( player );
                    JTF_name.setText("name");
                    JTF_value.setText("value");
                    JTF_team.setText("team");
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
        setLayout( new GridLayout(5,1) );
        add( JTF_name );
        add( JTF_value );
        add( JTF_team );
        add( JSP_scroll );
        add( JP_buttons );

        // open the input file for reading and writing
        try{
            database = new RandomAccessFile("Spielerdaten.dat","rw");
        }catch( FileNotFoundException fnf ){
            System.out.println("\n File not found.\n\n");
            JTA_output.setText("I cannot find the input file.");
        }

        // read data from input file
        boolean eof = false;    // Are we at the end of the file?
        while( !eof ){
            String name="";
            double value = -1.;
            String team="";
            try{
                name = database.readLine();
                String s = database.readLine();
                if( s!=null ){
                    value = Double.valueOf(s);
                }
                team = database.readLine();
                database.readLine();
            }catch( IOException io ){
                System.out.println("\n\n Error reading the data file.\n\n");
                JTA_output.setText("Error reading the data file.");
            }
            if( name==null || value==-1. || team==null ){
                eof = true; // We have reached the end of the file.
            }else{
                Player p = new Player( name, value, team );
                my_list.append( p );
            }
        }

    } // end of constructor

    public static void main(String [] args){
        Transfermarkt2 t = new Transfermarkt2();
        t.setSize(600,600);
        t.setResizable(false);
        t.setTitle("Transfermarkt2");
        t.setVisible(true);
        System.out.println("\n\n ======= Transfermarkt2 =======\n");
        System.out.println("\n\n ======= This code has been partially provided by TheBoringEdward =======\n");
    }

}