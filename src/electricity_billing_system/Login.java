/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electricity_billing_system;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

/**
 *
 * @author hp
 */
public class Login extends JFrame implements ActionListener {   
    JLabel l1,l2,l3,l4;
    JTextField tf1;
    JPasswordField pf2;
    JButton bt1,bt2,bt3;
    JPanel p1,p2,p3,p4;
    Choice c1;
    
    Login()
    {
        super("Login Page");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        l1 = new JLabel("UserName");
        l1.setBounds(300,20,100,20);
        add(l1);
        l2 = new JLabel("Password");
        l2.setBounds(300,60,100,20);
        add(l2);
        
        
        tf1 = new JTextField(15);
        tf1.setBounds(400,20,150,20);
        add(tf1);
        
        pf2 = new JPasswordField(15);
        pf2.setBounds(400,60,150,20);
        add(pf2);
        
        l3 = new JLabel("Logging in as");
        l3.setBounds(300,100,100,20);
        add(l3);
        
        c1 = new Choice();
        c1.add("Admin");
        c1.add("Customer");
        c1.setBounds(400,100,150,20);
        add(c1);
        
        
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image img1 = icon1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        bt1 = new JButton("Login",new ImageIcon(img1));
        bt1.setBounds(330, 160, 100, 20);
        add(bt1);
        
        ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image img2 = icon2.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        bt2 = new JButton("Cancel",new ImageIcon(img2));
        bt2.setBounds(450, 160, 100, 20);
        add(bt2);
        
        ImageIcon icon3 = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image img3 = icon3.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        bt3 = new JButton("Signup",new ImageIcon(img3));
        bt3.setBounds(380, 200, 130, 20);
        add(bt3);
        
        
        bt1.addActionListener(this);
        bt2.addActionListener(this);
        bt3.addActionListener(this);
        
        ImageIcon icon4 = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image img4 = icon4.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon icon4_1 = new ImageIcon(img4);
        l4 = new JLabel(icon4_1);
        l4.setBounds(0, 0, 250, 250);
        add(l4);     
        
        setLayout(new BorderLayout());
        
        setSize(640,300);
        setLocation(600,300);
        setVisible(true);
        
        
    } 
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bt1)
        {
            try{
                
                Conn c = new Conn();
                String a = tf1.getText();
                String b = pf2.getText();
                String user = c1.getSelectedItem();
                String sql = "select * from login where username = '"+a+"' and password = '"+b+"' and user = '"+user+"'" ;
                ResultSet rs = c.st.executeQuery(sql);
                
                if(rs.next())
                {
                    String meter = rs.getString("meter_no");
                    new Project(meter,user).setVisible(true);
                    this.setVisible(false);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                    tf1.setText("");
                    pf2.setText("");
                }
                
            }catch(Exception ex)
            {
                System.out.println("Error : "+ ex);
            }
        }
        
        else if(e.getSource() == bt2)
        {
            this.setVisible(false);
        }
        else if(e.getSource() == bt3)
        {
            this.setVisible(false);
            new Signup().setVisible(true);
        }
    }
    
    public static void main(String[] args)
    {
        new Login().setVisible(true);
    }
    
}
