/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electricity_billing_system;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

/**
 *
 * @author hp
 */
public class GenerateBill extends JFrame implements ActionListener{

    JLabel l1,l2;
    JTextArea t1;
    JButton b1;
    Choice c1;
    JPanel p1;
    
    String meter;
    
    public GenerateBill(String meter) {
        
        this.meter = meter;
        
        setSize(500,900);
        setLayout(new BorderLayout());
        
        p1= new JPanel();
        
        l1 = new JLabel("Generate Bill");
        l1= new JLabel(meter);
        c1 = new Choice();
        
        c1.add("January");
        c1.add("February");
        c1.add("March");
        c1.add("April");
        c1.add("May");
        c1.add("June");
        c1.add("July");
        c1.add("August");
        c1.add("September");
        c1.add("October");
        c1.add("November");
        c1.add("December");
        
        t1 = new JTextArea(50, 15);
        t1.setText("\n\n\t------- Click on the -------\n\t Generate Bill Button to get\n\tthe bill of the Selected Month\n\n");
        JScrollPane jsp = new JScrollPane(t1);
        t1.setFont(new Font("Senserif", Font.ITALIC,18));
        
        b1 = new JButton("Generate Bill");
        p1.add(l1);
        p1.add(l2);
        p1.add(c1);
        add(p1,"North");
        
        add(jsp,"Center");
        
        add(b1,"south");
        
        b1.addActionListener(this);
        setLocation(750,100);    
        
        
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1){
        try {
            Conn c = new Conn();
            String month = c1.getSelectedItem();
            t1.setText("\tReliance Power Limited\nELECTRICITY BILL FOR THE MONTH OF "+month+" ,2023\n\n\n");;
            
            ResultSet rs = c.st.executeQuery("select * from customer where meter = " + meter);
            
            if(rs.next()){
                t1.append("\n    Customer Name:"+rs.getString("name"));
                t1.append("\n    Meter Number:  "+rs.getString("meter"));
                t1.append("\n    Address:            "+rs.getString("address"));
                t1.append("\n    State:                 "+rs.getString("state"));
                t1.append("\n    City:                   "+rs.getString("city"));
                t1.append("\n    Email:                "+rs.getString("email"));
                t1.append("\n    Phone Number  "+rs.getString("phone"));
                t1.append("\n-------------------------------------------------------------");
                t1.append("\n");
            }
            
            rs = c.st.executeQuery("select * from meter_info where meter_number = " + meter);
            
            if(rs.next()){
                t1.append("\n    Meter Location:"+rs.getString("meter_location"));
                t1.append("\n    Meter Type:      "+rs.getString("meter_type"));
                t1.append("\n    Phase Code:    "+rs.getString("phase_code"));
                t1.append("\n    Bill Type:         "+rs.getString("bill_type"));
                t1.append("\n    Days:               "+rs.getString("days"));
                t1.append("\n");
            }
            rs = c.st.executeQuery("select * from tax");
            if(rs.next()){
                t1.append("---------------------------------------------------------------");
                t1.append("\n\n");
                t1.append("\n Cost per Unit:             Rs "+rs.getString("cost_per_unit"));
                t1.append("\n Meter Rent:                Rs "+rs.getString("meter_rent"));
                t1.append("\n Service Charge:            Rs "+rs.getString("service_charge"));
                t1.append("\n Service Tax:               Rs "+rs.getString("service_tax"));
                t1.append("\n Swacch Bharat Cess:        Rs "+rs.getString("swacch_bharat_cess"));
                t1.append("\n Fixed Tax:                 Rs "+rs.getString("fixed_tax"));
                t1.append("\n");
                
            }
            
            rs = c.st.executeQuery("select * from bill where meter="+meter+" AND month = '"+c1.getSelectedItem()+"'");
            
            if(rs.next()){
                t1.append("\n    Current Month :\t"+rs.getString("month"));
                t1.append("\n    Units Consumed:\t"+rs.getString("units"));
                t1.append("\n    Total Charges :\t"+rs.getString("total_bill"));
                t1.append("\n---------------------------------------------------------------");
                t1.append("\n    TOTAL PAYABLE :\t"+rs.getString("total_bill"));
            }
                      
        } catch (Exception ex) {
            System.out.println("Error :" + ex);
        }
        }
    }
    
    public static void main(String[] args) {
        new GenerateBill("").setVisible(true);
    }
    
}
