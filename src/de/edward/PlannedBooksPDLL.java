package de.edward;

// The input file should be encoded in ISO-8859-15.
// The editor kate allows to choose the encoding manually.

// A fully-worked out example for a doubly linked list

// Klaus Wiele September 2022

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.awt.Color;

public class PlannedBooksPDLL extends JFrame{

    private final List<PlannedBooks> my_list = new List<PlannedBooks>();
    private RandomAccessFile database;
    private final JTextField JTF_name;
    private final JTextField JTF_desc;
    private final JTextField JTF_hlth;
    private final JTextArea JTA_output;
    private JButton JB_append;
    private JButton JB_print;
    private JButton JB_clear;
    private JButton JB_save;

    PlannedBooksPDLL(){

        setBackground(Color.black); // It flashes black for a second and then just decides to flashbang me.
        // The mentions about 'setOpaque' are useless, 'cause it doesn't even exist for some bloody reason.
        // set up the text fields
        JTF_name = new JTextField("Name");
        JTF_name.setBackground(Color.decode("#2d3842"));
        JTF_name.setForeground(Color.decode("#95aec6"));
        JTF_name.setOpaque(true);
        JTF_desc = new JTextField("Description");
        JTF_desc.setBackground(Color.decode("#2d3842"));
        JTF_desc.setForeground(Color.decode("#95aec6"));
        JTF_desc.setOpaque(true);
        JTF_hlth = new JTextField("Probability");
        JTF_hlth.setBackground(Color.decode("#2d3842"));
        JTF_hlth.setForeground(Color.decode("#95aec6")); // TODO: There HAS to be a better way of doing this.
        JTF_hlth.setOpaque(true);

        // set up the output field
        // The JTextArea needs a surrounding JScrollPane for scrolling.
        JTA_output = new JTextArea("");
        JTA_output.setBackground(Color.decode("#2d3842"));
        JTA_output.setForeground(Color.decode("#95aec6"));
        JTA_output.setOpaque(true);
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
                JTF_name.setText("Name");
                JTF_desc.setText("Description");
                JTF_hlth.setText("Probability");
                JTA_output.setText("");
            }
        });
        JB_append = new JButton("append");
        JB_append.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String name = JTF_name.getText();
                String desc = JTF_desc.getText();
                int value = -1;
                try{
                    value = Integer.parseInt( JTF_hlth.getText() );
                }catch( NumberFormatException nf ){
                    JTA_output.setText("I cannot read the book's probability.");
                }
                if( value>=0 ){
                    PlannedBooks p = new PlannedBooks( name, desc, value );
                    my_list.append( p );
                    JTF_name.setText("Name");
                    JTF_desc.setText("Description");
                    JTF_hlth.setText("Probability");
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
                    JTA_output.setText("Data could not be saved.");
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
        add(JTF_desc);
        add(JTF_hlth);
        add( JSP_scroll );
        add( JP_buttons );

        // open the input file for reading and writing
        try{
            database = new RandomAccessFile("PlannedBooksdata.dat","rw");
        }catch( FileNotFoundException fnf ){
            System.out.println("\n File not found.\n\n");
            JTA_output.setText("Input file could not be found.");
        }

        // read data from input file
        boolean eof = false;    // Are we at the end of the file?
        while( !eof ){
            String name="";
            String desc="";
            int value = -1;
            try{
                name = database.readLine();
                desc = database.readLine();
                String s = database.readLine();
                if( s!=null ){
                    value = Integer.parseInt(s);
                }
                database.readLine();
            }catch( IOException io ){
                System.out.println("\n\n Error reading the data file.\n\n");
                JTA_output.setText("Error reading the data file.");
            }
            if( name==null || value==-1 || desc==null ){
                eof = true; // We have reached the end of the file.
            }else{
                PlannedBooks p = new PlannedBooks( name, desc , value );
                my_list.append( p );
            }
        }

    } // end of constructor

    public static void main(String [] args){
        PlannedBooksPDLL t = new PlannedBooksPDLL();
        t.setSize(600,600);
        t.setResizable(false);
        t.setTitle("PlannedBooksPDLL");
        t.setVisible(true);
        t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        t.setLocationRelativeTo(null); // It just pops back to the origin a split second later.
        System.out.println("\n\n ======= PlannedBooksPDLL =======\n");
        System.out.println("\n\n ======= This code has been modified by TheBoringEdward =======\n");
    }

}
