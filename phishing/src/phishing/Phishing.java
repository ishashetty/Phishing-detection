
package phishing;

/**
 *
 * @author Acer PC
 */
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

class Phishing
{
    private static final String USERNAME="root";
    private static final String PASS="";
    private static final String database="database path";
    
	public static void main(String args[]) throws Exception
	{
          int chance=3;
         // while(chance!=0){
            String suspect=JOptionPane.showInputDialog("Enter the URL you want to check:");
            if (!isValid(suspect)) 
               JOptionPane.showMessageDialog(null,"Not a valid URL.Please check it while entering","Phishing checker:",JOptionPane.PLAIN_MESSAGE);
            else
            {
             connect(suspect);
            }
           // chance--;
            
          Class.forName("com.mysql.jdbc.Driver");
        }
        static void connect(String suspect) throws MalformedURLException, ClassNotFoundException{
            Connection conn=null;
            PreparedStatement pst =null;
            ResultSet rs=null;
            try{
                conn=DriverManager.getConnection(database, USERNAME, PASS);
                Statement st=conn.createStatement();
                System.out.println("Connected");
                String sql="select * from checkphishing where url like ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1,suspect);
                rs=pst.executeQuery();
                if(rs.next()){
                    try {
                        //System.out.println(rs.next());
                        InetAddress address = InetAddress.getByName(new URL(suspect).getHost());
                        String ip = address.getHostAddress();
                    JOptionPane.showMessageDialog(null,"Not Secure..!!"+" and IP address: "+ip,"Phishing checker:",JOptionPane.ERROR_MESSAGE);
                    show w=new show();
                   // w.setVisible1(suspect);
                    
                    try{
                       // w.setVisible1(suspect);
                        w.setVisible1(rs.getString("Company"));
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(Phishing.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                else{
                    JOptionPane.showMessageDialog(null,"This website is trusted..!","Phishing checker:",JOptionPane.OK_OPTION);
//		
                }
            }
            catch(SQLException e){
                System.out.println(e);
            }
        }
        public static boolean isValid(String url)
    {
        /* Try creating a valid URL */
        try {
            new URL(url).toURI();
            return true;
        }
        // If there was an Exception
        // while creating URL object
        catch (Exception e) {
            return false;
        }
    }
            
//		
	}
        
