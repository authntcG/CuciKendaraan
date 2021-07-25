/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Connection.ConnectionManager;
import Model.ModelTransaksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JOptionPane;

/**
 *
 * @author Galih Respati P
 */
public class ControllerTransaksi {
    private ConnectionManager conMan;
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    public List<ModelTransaksi> ambilTransaksi(int param, ModelTransaksi trx) {
        
        /*
            param = 0 -> no filtered data -> ignore modeltransaksi
            param = 1 -> filtered data -> read modeltransaksi
        */
        
        List<ModelTransaksi> lsTransaksi = new ArrayList<ModelTransaksi>();
        String query;
        
        if (param == 1) {
            query = "SELECT * FROM vtransaksi WHERE id_user LIKE '%"+ trx.getId_user() +"%' ORDER BY id_transaksi ASC";
        } else {
            query = "SELECT * FROM vtransaksi ORDER BY id_transaksi ASC";
        }
        
        conMan = new ConnectionManager();
        con = conMan.logOn();
        
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            
            while (rs.next()) {                
                ModelTransaksi mt = new ModelTransaksi();
                mt.setId_trx(rs.getString("id_transaksi"));
                mt.setStatus(rs.getString("status_cuci"));
                mt.setId_user(rs.getString("id_user"));
                mt.setNama_user(rs.getString("nama"));
                mt.setTgl_cuci(rs.getString("tgl_cuci"));
                mt.setId_jenis(rs.getString("id_jenis"));
                mt.setNama_ken(rs.getString("tipe_kendaraan"));
                mt.setTot_bayar(rs.getString("total_bayar"));
                mt.setBayar(rs.getString("bayar"));
                mt.setKembali(rs.getString("kembali"));
                
                lsTransaksi.add(mt);
            }
            
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ControllerAkun.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return lsTransaksi;
    }
    
    public String getIDTransaksi() {
        String idakun = null, ran2;
        int ran1;
        String SQL = "SELECT RIGHT (id_transaksi, 2) AS id_transaksi FROM transaksi ORDER BY id_transaksi ASC";
        
        conMan = new ConnectionManager();
        con = conMan.logOn();
        
        Date date = new Date();
        ran1 = date.getDate();
        ran2 = date.getHours()+""+date.getMinutes()+""+date.getSeconds();
        
        try {
            st = con.createStatement();
            rs = st.executeQuery(SQL);
            if (rs.first() == false) {
                idakun = "TRX-0"+ ran1 + "" + ran2 +"001";
            } else {
                rs.last();
                int no = rs.getInt(1) + 1;
                String cno = String.valueOf(no);
                cno="00" +cno;
                idakun = "TRX-0"+ ran1 + "" + ran2 + ""+cno;
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ControllerAkun.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return idakun;
    }
    
    public int transaksiBaru(ModelTransaksi mt, int mode) {
        /*
            mode = 1 : mode new data from user
            mode = 2 : mode new data from admin
            mode = 3 : modify data from admin / continue transaction
        */
        
        int hasil = 0;
        String query = null;
        
        switch (mode) {
            case 1:
                query = "INSERT INTO `transaksi`(`id_transaksi`, `id_user`, `id_jenis`, `total_bayar`, `status_cuci`)"
                        + " VALUES ('"+ mt.getId_trx() +"','"+ mt.getId_user() +"','"+ mt.getId_jenis() +"','"+ mt.getTot_bayar() +"','"+ mt.getStatus() +"')";
                break;
            case 2:
                query = "INSERT INTO `transaksi`(`id_transaksi`, `id_user`, `id_jenis`, `total_bayar`, `bayar`, `kembali`, `tgl_cuci`, `status_cuci`)"
                        + " VALUES ('"+ mt.getId_trx() +"','"+ mt.getId_user() +"','"+ mt.getId_jenis() +"','"+ mt.getTot_bayar() +"','"+ mt.getBayar() +"',"
                        + "'"+ mt.getKembali() +"','"+ mt.getTgl_cuci() +"','"+ mt.getStatus() +"')";
                break;
            case 3:
                query = "UPDATE `transaksi` SET `bayar`='"+ mt.getBayar() +"',`kembali`='"+ mt.getKembali() +"',`tgl_cuci`='"+ mt.getTgl_cuci() +"',"
                        + "`status_cuci`='"+ mt.getStatus() +"' WHERE id_transaksi LIKE '%"+ mt.getId_trx() +"%'";
                break;
            default:
                break;
        }
        
        try {
            conMan = new ConnectionManager();
            con = conMan.logOn();
            
            st = con.createStatement();
            hasil = st.executeUpdate(query);
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ControllerAkun.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return hasil;
    }
    
    public int cekDataTransaksi(ModelTransaksi mt) {
        int hasil = 0;
        
        String query = "SELECT * FROM transaksi WHERE id_transaksi = '"+ mt.getId_trx() +"'";
        
        try {
            conMan = new ConnectionManager();
            con = conMan.logOn();
            
            st = con.createStatement();
            rs = st.executeQuery(query);
            
            if (rs.next()) {
                hasil = 1;
            }
            
            
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ControllerAkun.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return hasil;
    }
    
    public List<ModelTransaksi> cariData(ModelTransaksi mt) {
        List<ModelTransaksi> lsTrx = new ArrayList<ModelTransaksi>();
        String query = "SELECT * FROM vtransaksi WHERE id_transaksi LIKE '%"+ mt.getId_trx()+"%'";
        
        conMan = new ConnectionManager();
        con = conMan.logOn();
        
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            
            while (rs.next()) {
                ModelTransaksi mta = new ModelTransaksi();
                mta.setId_trx(rs.getString("id_transaksi"));
                mta.setId_user(rs.getString("id_user"));
                mta.setNama_user(rs.getString("nama"));
                mta.setId_jenis(rs.getString("id_jenis"));
                mta.setNama_ken(rs.getString("tipe_kendaraan"));
                mta.setTot_bayar(rs.getString("total_bayar"));
                mta.setBayar(rs.getString("bayar"));
                mta.setKembali(rs.getString("kembali"));
                mta.setStatus(rs.getString("status_cuci"));
                
                lsTrx.add(mta);
            }
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ControllerAkun.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return lsTrx;
    }
}
