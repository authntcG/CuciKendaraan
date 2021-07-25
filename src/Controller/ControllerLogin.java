/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Connection.ConnectionManager;
import Model.ModelLogin;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

/**
 *
 * @author Galih Respati P
 */
public class ControllerLogin {
    private ConnectionManager conMan;
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    public int fungsiLogin(ModelLogin login){
        /*
            hasil = 0 : uname / pass invalid
            hasil = -1 : pass invalid
            hasil = 1 : pass
        */
        
        int hasil = 0;
        String query = "SELECT * FROM akun WHERE username = '"+ login.getUsername() +"'";
        
        conMan = new ConnectionManager();
        con = conMan.logOn();
        
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            
            while (rs.next()) {                
                if (!rs.getString("password").equals(login.getPassword())){
                    hasil = -1;
                } else if (rs.getString("password").equals(login.getPassword())) {
                    login.setId_akun(rs.getString("id_akun"));
                    login.setNama(rs.getString("nama"));
                    login.setAlamat(rs.getString("alamat"));
                    login.setNo_telp(rs.getString("no_telp"));
                    login.setAccess(rs.getInt("akses"));
                    hasil = 1;
                } else {
                    hasil = 0;
                }
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ControllerLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conMan.logOff();
        return hasil;
    }
}
