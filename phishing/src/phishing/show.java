/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phishing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer PC
 */
public class show {
private static final String USERNAME="root";
    private static final String PASS="";
    private static final String database="jdbc:mysql://localhost:3306/phishing?zeroDateTimeBehavior=convertToNull";
    Connection conn=null;
            PreparedStatement pst =null;
            ResultSet rs=null;

    public void setVisible1(String s) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        try{
                conn=DriverManager.getConnection(database, USERNAME, PASS);
                Statement st=conn.createStatement();
                //System.out.println("Connected");
                String sql="select * from checkphishing where Company like ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1,s);
                rs=pst.executeQuery();
                ArrayList<String> al=new ArrayList<String>();
                while(rs.next()){
                    al.add(rs.getString("url"));
                }
              //  System.out.println(al);
                JOptionPane.showMessageDialog(null,"Other phishing sites: "+al,"Phishing checker:",JOptionPane.OK_OPTION);

        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
