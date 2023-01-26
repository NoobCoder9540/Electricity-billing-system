/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electricity_billing_system;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author hp
 */
public class Signup extends JFrame implements ActionListener{
     
    JPanel jp;
    JTextField tf1,tf2,tf3,tf4;
    Choice ch1;
    JButton bt1,bt2;
    
    Signup()
    {
        setBounds(600,250,700,400);
        
        jp = new JPanel();
        jp.setBounds(30,30,650,300);
        jp.setBackground(Color.WHITE);
        jp.setLayout(null);
        jp.setForeground(new Color(34,139,34));
        jp.setBorder(new TitledBorder(new LineBorder(new Color(173,21,230),2),"Create-Account",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(173,21,230)));
        add(jp);
        
        JLabel l1 = new JLabel("UserName");
        l1.setForeground(Color.DARK_GRAY);
        l1.setFont(new Font("Tahoma",Font.BOLD,14));
        l1.setBounds(100,50,100,20);
        jp.add(l1);
        
        tf1 = new JTextField();
        tf1.setBounds(260,50,150,20);
        jp.add(tf1);
        
        
        JLabel l2 = new JLabel("Name");
        l2.setForeground(Color.DARK_GRAY);
        l2.setFont(new Font("Tahoma",Font.BOLD,14));
        l2.setBounds(100,90,100,20);
        jp.add(l2);
        
        tf2 = new JTextField();
        tf2.setBounds(260,90,150,20);
        jp.add(tf2);
        
        
        JLabel l3 = new JLabel("Password");
        l3.setForeground(Color.DARK_GRAY);
        l3.setFont(new Font("Tahoma",Font.BOLD,14));
        l3.setBounds(100,130,100,20);
        jp.add(l3);
        
        tf3 = new JTextField();
        tf3.setBounds(260,130,150,20);
        jp.add(tf3);
        
        
        JLabel l4 = new JLabel("Create Account as");
        l4.setForeground(Color.DARK_GRAY);
        l4.setFont(new Font("Tahoma",Font.BOLD,14));
        l4.setBounds(100,170,140,20);
        jp.add(l4);
        
        
        JLabel l5 = new JLabel("Meter Number");
        l5.setForeground(Color.DARK_GRAY);
        l5.setFont(new Font("Tahoma",Font.BOLD,14));
        l5.setBounds(100,210,100,20);
        l5.setVisible(false);
        jp.add(l5);
        
        tf4 = new JTextField();
        tf4.setBounds(260,210,150,20);
        tf4.setVisible(false);
        jp.add(tf4);
        
        ch1 = new Choice();
        ch1.add("Admin");
        ch1.add("Customer");
        ch1.setBounds(260, 170, 150, 20);
        jp.add(ch1);
        
        ch1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                String user = ch1.getSelectedItem();
                if(user.equals("Customer"))
                {
                    l5.setVisible(true);
                    tf4.setVisible(true);
                }
                else
                {
                    l5.setVisible(false);
                    tf4.setVisible(false);
                }
            }
        });
        
        
        bt1= new JButton("Create");
        bt1.setBackground(Color.BLACK);
        bt1.setForeground(Color.WHITE);
        bt1.setBounds(140, 290, 120, 30);
        bt1.addActionListener(this);
        jp.add(bt1);
        
        bt2 = new JButton("Back");
        bt2.setBackground(Color.BLACK);
        bt2.setForeground(Color.WHITE);
        bt2.setBounds(300, 290, 120, 30);
        bt2.addActionListener(this);
        jp.add(bt2);
        
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image img = icon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(img);
        JLabel l6 = new JLabel(i3);
        l6.setBounds(450, 30, 250, 250);
        jp.add(l6);
        
        
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bt1)
        {
             String username = tf1.getText();
            String name = tf2.getText();
            String password = tf3.getText();
            String user = ch1.getSelectedItem();
            String meter = tf4.getText();
            
            try {
                
             Conn con = new Conn();
            String sql = null;
            
            if(user.equals("Admin"))
            {
                sql = "insert into login values('"+meter+"','"+username+"','"+name+"','"+password+"','"+user+"')";
            }
            else
            {
                sql = "update login set username = '"+username+"', name = '"+name+"', password = '"+password+"', user = '"+user+"' where meter_no = '"+tf4.getText()+"'";
            }
                
                con.st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,"Account Created Successfully");
                this.setVisible(false);
                new Login().setVisible(true);
                
            } catch (SQLException ex) {
                System.out.println("Error : "+ ex);
            }
            
        }
        else if(e.getSource() == bt2)
        {
            this.setVisible(false);
            new Login().setVisible(true);
        }
    }
    
    
    
    public static void main(String[] args)
    {
        new Signup().setVisible(true);
    }
    
}
