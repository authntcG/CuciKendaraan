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
public class ModelLogin {
    private static String id_akun, nama, alamat, no_telp, username, password;
    private static int access;

    public static String getId_akun() {
        return id_akun;
    }

    public static void setId_akun(String id_akun) {
        ModelLogin.id_akun = id_akun;
    }

    public static String getNama() {
        return nama;
    }

    public static void setNama(String nama) {
        ModelLogin.nama = nama;
    }

    public static String getAlamat() {
        return alamat;
    }

    public static void setAlamat(String alamat) {
        ModelLogin.alamat = alamat;
    }

    public static String getNo_telp() {
        return no_telp;
    }

    public static void setNo_telp(String no_telp) {
        ModelLogin.no_telp = no_telp;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        ModelLogin.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        ModelLogin.password = password;
    }

    public static int getAccess() {
        return access;
    }

    public static void setAccess(int access) {
        ModelLogin.access = access;
    }
}
