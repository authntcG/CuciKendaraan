/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Connection.ConnectionManager;
import Model.ModelAkun;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JOptionPane;

/**
 *
 * @author Galih Respati P
 */
public class ControllerAkun {
    private ConnectionManager conMan;
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    //Insert Data Akun
    public int simpanData(ModelAkun akun) {
        int hasil = 0;
        String checkdata, query;
        
        checkdata = "SELECT * FROM akun WHERE username LIKE '%"+ akun.getUsername() +"%'";
        query = "INSERT INTO akun(id_akun, nama, alamat, no_telp, username, password, akses) "
                + "VALUES ('"+ akun.getIdakun() +"','"+ akun.getNama() +"','"+ akun.getAlamat() +"','"
                + akun.getNotel() +"','"+ akun.getUsername() +"','"+ akun.getPassword() +"','"+ akun.getAkses() +"')";
        
        conMan = new ConnectionManager();
        con =   conMan.logOn();
        
        try {
            st = con.createStatement();
            rs = st.executeQuery(checkdata);
            
            if (rs.next() == true) {
                hasil = -1;
            } else {
                hasil = st.executeUpdate(query);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            java.util.logging.Logger.getLogger(ControllerAkun.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return hasil;
    }
    
    public List<ModelAkun> ambilAkun(int parameter) {
        List<ModelAkun> lsAkun = new ArrayList<ModelAkun>();
        String query;
        
        if (parameter == 1) {
            query = "SELECT * FROM akun WHERE akses = '2' ORDER BY id_akun ASC";
        } else {
            query = "SELECT * FROM akun ORDER BY id_akun ASC";
        }
        
        
        conMan = new ConnectionManager();
        con = conMan.logOn();
        
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            
            while (rs.next()) {
                ModelAkun ma = new ModelAkun();
                ma.setIdakun(rs.getString("id_akun"));
                ma.setNama(rs.getString("nama"));
                ma.setAlamat(rs.getString("alamat"));
                ma.setNotel(rs.getString("no_telp"));
                ma.setUsername(rs.getString("username"));
                ma.setPassword(rs.getString("password"));
                ma.setAkses(rs.getInt("akses"));
                
                lsAkun.add(ma);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            java.util.logging.Logger.getLogger(ControllerAkun.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return lsAkun;
    }
    
    public List<ModelAkun> caridataAkun(ModelAkun akun) {
        List<ModelAkun> lsAkun = new ArrayList<ModelAkun>();
        String query = "SELECT * FROM akun WHERE nama LIKE '%"+ akun.getNama() +"%' ORDER BY id_akun ASC";
        
        conMan = new ConnectionManager();
        con = conMan.logOn();
        
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            
            while (rs.next()) {
                ModelAkun ma = new ModelAkun();
                ma.setIdakun(rs.getString("id_akun"));
                ma.setNama(rs.getString("nama"));
                ma.setAlamat(rs.getString("alamat"));
                ma.setNotel(rs.getString("no_telp"));
                ma.setUsername(rs.getString("username"));
                ma.setPassword(rs.getString("password"));
                ma.setAkses(rs.getInt("akses"));
                
                lsAkun.add(ma);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            java.util.logging.Logger.getLogger(ControllerAkun.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return lsAkun;
    }
    
    public int updateAkun(ModelAkun akun) {
        int hasil = 0;
        String query, checkdata;
        
        checkdata = "SELECT * FROM akun WHERE username LIKE '%"+ akun.getUsername() +"%' ";
        query = "UPDATE akun SET nama='"+ akun.getNama() +"',alamat='"+ akun.getAlamat() +"',no_telp='"+ akun.getNotel() +""
                + "',username='"+ akun.getUsername() +"',password='"+ akun.getPassword() +"',akses='"+ akun.getAkses() +""
                + "' WHERE id_akun='"+ akun.getIdakun() +"'";
        
        conMan = new ConnectionManager();
        con = conMan.logOn();
        
        try {
            st = con.createStatement();
            rs = st.executeQuery(checkdata);
            
            if (rs.next() == true) {
                if (rs.getString("id_akun").equals(akun.getIdakun())) {
                    hasil = st.executeUpdate(query);
                } else {
                    hasil = -1;
                }
                
            } else {
                hasil = st.executeUpdate(query);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            java.util.logging.Logger.getLogger(ControllerAkun.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return hasil;
    }
    
    public int hapusAkun(ModelAkun akun) {
        int hasil = 0;
        String query = "DELETE FROM akun WHERE id_akun = '"+ akun.getIdakun() +"'";
        
        conMan = new ConnectionManager();
        con = conMan.logOn();
        
        try {
            st = con.createStatement();
            hasil = st.executeUpdate(query);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            java.util.logging.Logger.getLogger(ControllerAkun.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return hasil;
    }
    
    public String getIDAkun() {
        String idakun = null;
        String SQL = "SELECT RIGHT (id_akun, 2) AS id_akun FROM akun ORDER BY id_akun ASC";
        
        conMan = new ConnectionManager();
        con = conMan.logOn();
        
        try {
            st = con.createStatement();
            rs = st.executeQuery(SQL);
            if (rs.first() == false) {
                idakun = "AKN-001";
            } else {
                rs.last();
                int no = rs.getInt("id_akun") + 1;
                String cno = String.valueOf(no);
                
                cno="00"+cno;
                idakun = "AKN-"+cno;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            java.util.logging.Logger.getLogger(ControllerAkun.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return idakun;
    }    
}
