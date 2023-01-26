package electricity_billing_system;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
/**
 *
 * @author hp
 */
public class Conn {
    Connection con;
    Statement st;
    
    public Conn()
        {
            try{
                
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql:///ebs","root","9540");
                st = con.createStatement();
            
                
            }catch(Exception e)
            {
                System.out.println(e);
            }
    }
}
