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
public class ModelJenisKendaraan {
    private String id_jenis, tipe_kendaraan;
    private int harga;

    public ModelJenisKendaraan() {
    }

    public ModelJenisKendaraan(String id_jenis, String tipe_kendaraan, int harga) {
        this.id_jenis = id_jenis;
        this.tipe_kendaraan = tipe_kendaraan;
        this.harga = harga;
    }

    public String getId_jenis() {
        return id_jenis;
    }

    public void setId_jenis(String id_jenis) {
        this.id_jenis = id_jenis;
    }

    public String getTipe_kendaraan() {
        return tipe_kendaraan;
    }

    public void setTipe_kendaraan(String tipe_kendaraan) {
        this.tipe_kendaraan = tipe_kendaraan;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    @Override
    public String toString() {
        return "ModelJenisKendaraan{" + "id_jenis=" + id_jenis + ", tipe_kendaraan=" + tipe_kendaraan + ", harga=" + harga + '}';
    }
}
