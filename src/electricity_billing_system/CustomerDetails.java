/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electricity_billing_system;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

/**
 *
 * @author hp
 */
public class CustomerDetails extends JFrame implements ActionListener{

    JTable t1;
    JButton b1;
    String x[] = {"Customer Name","Meter Number","Address","City","State","Email","Phone"};
    String y[][] = new String[40][8];
    int i=0, j=0;
    
    public CustomerDetails(){
        
        super("Customer Details");
        setSize(1200,650);
        setLocation(400,150);
        
        try{
            Conn con  = new Conn();
            String s1 = "select * from customer";
            ResultSet rs  = con.st.executeQuery(s1);
            while(rs.next()){
                y[i][j++]=rs.getString("name");
                y[i][j++]=rs.getString("meter");
                y[i][j++]=rs.getString("address");
                y[i][j++]=rs.getString("city");
                y[i][j++]=rs.getString("state");
                y[i][j++]=rs.getString("email");
                y[i][j++]=rs.getString("phone");
                i++;
                j=0;
            }
            t1 = new JTable(y,x);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        b1 = new JButton("Print");
        add(b1,"South");
        JScrollPane sp = new JScrollPane(t1);
        add(sp);
        b1.addActionListener(this);
        
    }    
    
    @Override
    public void actionPerformed(ActionEvent e) {
         try{
            t1.print();
        }catch(Exception ex){
        
             System.out.println("error : "+ ex);
        }
    }
    
    
    public static void main(String[] args) {
        new CustomerDetails().setVisible(true);
    }
}
