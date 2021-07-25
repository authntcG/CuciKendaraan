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
public class ModelAkun {
    private String idakun;
    private String nama;
    private String alamat;
    private String username;
    private String password;
    private String notel;
    private int akses;

    public ModelAkun() {
    }

    public ModelAkun(String idakun, String nama, String alamat, String username, String password, String notel, int akses) {
        this.idakun = idakun;
        this.nama = nama;
        this.alamat = alamat;
        this.username = username;
        this.password = password;
        this.notel = notel;
        this.akses = akses;
    }

    public String getIdakun() {
        return idakun;
    }

    public void setIdakun(String idakun) {
        this.idakun = idakun;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNotel() {
        return notel;
    }

    public void setNotel(String notel) {
        this.notel = notel;
    }

    public int getAkses() {
        return akses;
    }

    public void setAkses(int akses) {
        this.akses = akses;
    }

    @Override
    public String toString() {
        return "ModelAkun{" + "idakun=" + idakun + ", nama=" + nama + ", alamat=" + alamat + ", username=" + username + ", password=" + password + ", notel=" + notel + ", akses=" + akses + '}';
    }
}
