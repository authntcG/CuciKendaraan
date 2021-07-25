/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Connection.ConnectionManager;
import Model.ModelJenisKendaraan;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author Galih Respati P
 */
public class ControllerJenisKendaraan {
    private ConnectionManager conMan;
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    public int simpanData(ModelJenisKendaraan jenis) {
        int hasil = 0;
        String query = "INSERT INTO jenis_kendaraan(id_jenis, tipe_kendaraan, harga) "
                + "VALUES ('"+ jenis.getId_jenis()+"','"+ jenis.getTipe_kendaraan() +"','"+ jenis.getHarga()+"')";
        
        conMan = new ConnectionManager();
        con =   conMan.logOn();
        
        try {
            st = con.createStatement();
            hasil = st.executeUpdate(query);
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ControllerAkun.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return hasil;
    }
    
    public List<ModelJenisKendaraan> ambilData() {
        List<ModelJenisKendaraan> lsJk = new ArrayList<ModelJenisKendaraan>();
        String query = "SELECT * FROM jenis_kendaraan";
        
        conMan = new ConnectionManager();
        con = conMan.logOn();
        
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            
            while (rs.next()) {
                ModelJenisKendaraan jk = new ModelJenisKendaraan();
                jk.setId_jenis(rs.getString("id_jenis"));
                jk.setTipe_kendaraan(rs.getString("tipe_kendaraan"));
                jk.setHarga(rs.getInt("harga"));
                
                lsJk.add(jk);
            }
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ControllerAkun.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return lsJk;
    }
    
    public int updateData(ModelJenisKendaraan jk) {
        int hasil = 0;
        String query = "UPDATE jenis_kendaraan SET tipe_kendaraan = '"+ jk.getTipe_kendaraan() +
                "', harga = '"+ jk.getHarga() +"' WHERE id_jenis = '"+ jk.getId_jenis() +"'";
        
        conMan = new ConnectionManager();
        con = conMan.logOn();
        
        try {
            st = con.createStatement();
            hasil = st.executeUpdate(query);
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ControllerAkun.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return hasil;
    }
    
    public int hapusData(ModelJenisKendaraan jk) {
        int hasil = 0;
        String query = "DELETE FROM jenis_kendaraan WHERE id_jenis = '"+ jk.getId_jenis() +"'";
        
        conMan = new ConnectionManager();
        con = conMan.logOn();
        
        try {
            st = con.createStatement();
            hasil = st.executeUpdate(query);
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ControllerAkun.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return hasil;
    }
    
    public String getIDJenis() {
        String id = null;
        String SQL = "SELECT RIGHT (id_jenis, 2) AS id_jenis FROM jenis_kendaraan ORDER BY id_jenis ASC";
        
        conMan = new ConnectionManager();
        con = conMan.logOn();
        
        try {
            st = con.createStatement();
            rs = st.executeQuery(SQL);
            if (rs.first() == false) {
                id = "KEN-001";
            } else {
                rs.last();
                int no = rs.getInt("id_jenis") + 1;
                String cno = String.valueOf(no);

                cno="00" + cno;
                id = "KEN-"+cno;
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ControllerAkun.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return id;
    }
    
    public List<ModelJenisKendaraan> cariData(ModelJenisKendaraan mk) {
        List<ModelJenisKendaraan> lsJk = new ArrayList<ModelJenisKendaraan>();
        String query = "SELECT * FROM jenis_kendaraan WHERE tipe_kendaraan LIKE '%"+ mk.getTipe_kendaraan() +"%'";
        
        conMan = new ConnectionManager();
        con = conMan.logOn();
        
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            
            while (rs.next()) {
                ModelJenisKendaraan jk = new ModelJenisKendaraan();
                jk.setId_jenis(rs.getString("id_jenis"));
                jk.setTipe_kendaraan(rs.getString("tipe_kendaraan"));
                jk.setHarga(rs.getInt("harga"));
                
                lsJk.add(jk);
            }
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ControllerAkun.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return lsJk;
    }
}
