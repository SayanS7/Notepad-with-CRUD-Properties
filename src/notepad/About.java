package src.notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.ClassLoader;

public class About extends JFrame implements ActionListener{
    JButton b1;
    About(){
        setBounds(600, 200, 700,600);
        setLayout(null);
        // "D:/000000-MyProjects/Notepad with CRUD properties/src/notepad/icons/windows.png"
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/windows.png"));
        Image i2 = i1.getImage().getScaledInstance(400, 80, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(150, 40, 400, 80);
        add(l1);
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/notepad.png"));
        Image i5 = i4.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l2 = new JLabel(i6);
        l2.setBounds(50, 180, 70, 70);
        add(l2);
        
        JLabel l3 = new JLabel("<html>Notepad<br>Developed by Sayan Sen<br>Notepad is a simple text editor for basic text-editing program<br> which enables computer users to create documents. </html>");
        l3.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
        l3.setBounds(150, 130, 500, 300);
        add(l3);
        
        b1 = new JButton("OK");
        b1.setBounds(580, 500, 80, 25);
        b1.addActionListener(this);
        add(b1);
    }
    public void actionPerformed(ActionEvent ae){
        this.setVisible(false);
    }
    public static void main(String[] args){
        new About().setVisible(true);
    } 
}
