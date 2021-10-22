package src.notepad;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.*;

public class Notepad extends JFrame implements ActionListener{

    public static void main(String[] args) {
        new Notepad(); 
    }
    private JTextArea area;
    private JScrollPane scpane;
    String text = ""; 
    Notepad(){
        setBounds(0, 0, 1500, 850);
        JMenuBar menubar = new JMenuBar();          
        
        JMenu file = new JMenu("File");             

        JMenuItem newdoc = new JMenuItem("New");    
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newdoc.addActionListener(this);

        JMenuItem open = new JMenuItem("Open");    
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        open.addActionListener(this);
        
        JMenuItem save = new JMenuItem("Save");    
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        save.addActionListener(this);

        JMenuItem print = new JMenuItem("Print");    
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        print.addActionListener(this);

        JMenuItem exit = new JMenuItem("Exit");    
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));
        exit.addActionListener(this);
        
        JMenu edit = new JMenu("Edit");
       
        JMenuItem copy = new JMenuItem("Copy");    
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copy.addActionListener(this);

        JMenuItem cut = new JMenuItem("Cut");    
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cut.addActionListener(this);

        JMenuItem paste = new JMenuItem("Paste");    
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        paste.addActionListener(this);
 
        JMenuItem selectall = new JMenuItem("Select All");    
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        selectall.addActionListener(this);

        JMenu about = new JMenu("Help");

        JMenuItem notepad = new JMenuItem("About Notepad");
        notepad.addActionListener(this);
        
        // TextArea 
        area = new JTextArea();
        area.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        scpane = new JScrollPane(area); 
        scpane.setBorder(BorderFactory.createEmptyBorder());

        setJMenuBar(menubar);

        menubar.add(file);
        menubar.add(edit);
        menubar.add(about);

        file.add(newdoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);

        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        edit.add(selectall);

        about.add(notepad);

        add(scpane, BorderLayout.CENTER);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent ae) {
        // Opening a new file
        if (ae.getActionCommand().equals("New")){
            area.setText("");
        } // Open Only Text File
        else if(ae.getActionCommand().equals("Open")){
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter(".txt files", "txt");
            chooser.addChoosableFileFilter(restrict);

            int result = chooser.showOpenDialog(this);
            if(result == JFileChooser.APPROVE_OPTION){
                File file = chooser.getSelectedFile();

                try {
                    System.out.println("HEki");
                    FileReader reader = new FileReader(file);
                    BufferedReader br = new BufferedReader(reader);
                    area.read(br, null);
                    br.close();
                } catch(Exception e){System.out.println(e);}
            }
            
        } // Save a file
        else if(ae.getActionCommand().equals("Save")){    
            JFileChooser saveas = new JFileChooser();
            saveas.setApproveButtonText("Save");
            int action = saveas.showOpenDialog(this);
            if (action != JFileChooser.APPROVE_OPTION){
                return;
            }
            File fileName = new File(saveas.getSelectedFile() + ".txt");
            BufferedWriter outFile = null;
            try{
                outFile = new BufferedWriter(new FileWriter(fileName));
                area.write(outFile);
            } catch(IOException e){System.out.println(e);}
        } // Print file
        else if(ae.getActionCommand().equals("Print")){
            try{
                area.print();
            }catch(Exception e){System.out.println(e);}
        } // Exit
        else if(ae.getActionCommand().equals("Exit")){
            System.exit(0);
        }
        else if (ae.getActionCommand().equals("Copy")) {
            text = area.getSelectedText();
        }
        else if (ae.getActionCommand().equals("Cut")) {
            text = area.getSelectedText();
            area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
        }
        else if (ae.getActionCommand().equals("Paste")) {
            area.insert(text, area.getCaretPosition());
        }
        else if (ae.getActionCommand().equals("Select All")) {
            area.selectAll();
        }
        else if (ae.getActionCommand().equals("About Notepad")) {
            new About().setVisible(true);
            
        }
    }
}
