/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electricity_billing_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author hp
 */
public class BillDetails extends JFrame{

    JTable t1;
    String x[] = {"Meter Number","Month","Units","Total Bill","Status"};
    String y[][] = new String[40][8];
    int i=0, j=0;  

    public BillDetails(String meter) throws SQLException{
        super("Bill Details");
        setSize(700,650);
        setLocation(600,150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        t1 = new JTable(y, x);
        
        try{
        Conn c = new Conn();
        String sq = "select * from bill where meter = " + meter;
        ResultSet rs = c.st.executeQuery(sq);
        t1.setModel(DbUtils.resultSetToTableModel(rs));
        
        }catch(Exception e)
        {
            System.out.println("Error : " + e);
        }
        
        JScrollPane jp = new JScrollPane(t1);
        jp.setBounds(0,0,700,650);
        add(jp);
        
        
                
    }
   
    
    public static void main(String[] args) throws SQLException {
        new BillDetails("").setVisible(true);
    }
}
