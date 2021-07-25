/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Galih Respati P
 */
public class ModelTransaksi {
    private String id_trx, id_user, nama_user, id_jenis, nama_ken, tgl_cuci, tot_bayar, bayar, kembali, status;

    public ModelTransaksi() {
    }

    public ModelTransaksi(String id_trx, String id_user, String id_jenis, String tgl_cuci, String status, String tot_bayar, String bayar, String kembali) {
        this.id_trx = id_trx;
        this.id_user = id_user;
        this.id_jenis = id_jenis;
        this.tgl_cuci = tgl_cuci;
        this.status = status;
        this.tot_bayar = tot_bayar;
        this.bayar = bayar;
        this.kembali = kembali;
    }

    public ModelTransaksi(String id_trx, String id_user, String nama_user, String id_jenis, String nama_ken, String tgl_cuci, String status, String tot_bayar, String bayar, String kembali) {
        this.id_trx = id_trx;
        this.id_user = id_user;
        this.nama_user = nama_user;
        this.id_jenis = id_jenis;
        this.nama_ken = nama_ken;
        this.tgl_cuci = tgl_cuci;
        this.status = status;
        this.tot_bayar = tot_bayar;
        this.bayar = bayar;
        this.kembali = kembali;
    }

    public String getId_trx() {
        return id_trx;
    }

    public void setId_trx(String id_trx) {
        this.id_trx = id_trx;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getNama_user() {
        return nama_user;
    }

    public void setNama_user(String nama_user) {
        this.nama_user = nama_user;
    }

    public String getId_jenis() {
        return id_jenis;
    }

    public void setId_jenis(String id_jenis) {
        this.id_jenis = id_jenis;
    }

    public String getNama_ken() {
        return nama_ken;
    }

    public void setNama_ken(String nama_ken) {
        this.nama_ken = nama_ken;
    }

    public String getTgl_cuci() {
        return tgl_cuci;
    }

    public void setTgl_cuci(String tgl_cuci) {
        this.tgl_cuci = tgl_cuci;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTot_bayar() {
        return tot_bayar;
    }

    public void setTot_bayar(String tot_bayar) {
        this.tot_bayar = tot_bayar;
    }

    public String getBayar() {
        return bayar;
    }

    public void setBayar(String bayar) {
        this.bayar = bayar;
    }

    public String getKembali() {
        return kembali;
    }

    public void setKembali(String kembali) {
        this.kembali = kembali;
    }

    @Override
    public String toString() {
        return "ModelTransaksi{" + "id_trx=" + id_trx + ", id_user=" + id_user + ", nama_user=" + nama_user + ", id_jenis=" + id_jenis + ", nama_ken=" + nama_ken + ", tgl_cuci=" + tgl_cuci + ", status=" + status + ", tot_bayar=" + tot_bayar + ", bayar=" + bayar + ", kembali=" + kembali + '}';
    }
}
