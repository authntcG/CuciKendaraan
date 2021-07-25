/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Galih Respati P
 */
public class ConnectionManager {
    private Connection con;
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/cuci_kendaraan_oop";
    private String username = "root";
    private String password = "";
    
    public Connection logOn(){
        try{
            //Load JDBC Driver
            Class.forName(driver).newInstance();
            //Buat object connection
            con = DriverManager.getConnection(url, username, password);
            System.out.println(con);
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        return con;
    }
    
    public void logOff(){
        try{
            //Tutup koneksi
            con.close();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
