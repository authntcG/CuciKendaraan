/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerAkun;
import Controller.ControllerJenisKendaraan;
import Controller.ControllerTransaksi;
import Model.ModelAkun;
import Model.ModelJenisKendaraan;
import Model.ModelTransaksi;
import com.sun.glass.events.KeyEvent;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import Connection.ConnectionManager;

/**
 *
 * @author Galih Respati P
 */
public class MainTransaksiAdmin extends javax.swing.JPanel {

    /**
     * Creates new form MainTransaksi
     */
    
    ControllerTransaksi conTransaksi;
    ControllerAkun conAkun;
    ControllerJenisKendaraan conJenis;
    ConnectionManager conMan;
    Connection con;
    
    public MainTransaksiAdmin() {
        initComponents();
        loadData();
        activateForm(false);
        activateButton(0);
    }
    
    private void hitungKembali() {
        int value1, value2, hasil;
        
        value1 = Integer.parseInt(txtTotBayar.getText());
        value2 = Integer.parseInt(txtBayar.getText());

        hasil = value2 - value1;
        txtKembali.setText(Integer.toString(hasil));
    }
    
    private void loadPengguna(int mode) {
        /*
            0 -> show data
            1 -> filter data 
        */
        List<ModelAkun> lsAkun = new ArrayList<ModelAkun>();
        conAkun = new ControllerAkun();
        
        if (mode == 1) {
            String data = txtCariPelanggan.getText();
            ModelAkun ma = new ModelAkun();
            ma.setNama(data);
            lsAkun = conAkun.caridataAkun(ma);
        } else {
            lsAkun = conAkun.ambilAkun(1);
        }
        
        Object [][] obAkun =    new Object[lsAkun.size()][4];
        
        int i = 0;
        
        for (ModelAkun ma : lsAkun) {
            obAkun[i][0] = ma.getIdakun();
            obAkun[i][1] = ma.getNama();
            obAkun[i][2] = ma.getAlamat();
            obAkun[i][3] = ma.getNotel();
            i++;
        }
        tblPelanggan.setModel(new javax.swing.table.DefaultTableModel(
            obAkun,
            new String [] {
                "ID Pelanggan", "Nama Pelanggan", "Alamat", "No. Telepon"
            }
        ));
        jScrollPane2.setViewportView(tblPelanggan);
        tblPelanggan.setDefaultEditor(Object.class, null);
    }
    
    private void loadKendaraan(int mode) {
        /*
            0 -> show data
            1 -> filter data 
        */
        List<ModelJenisKendaraan> lsJk = new ArrayList<ModelJenisKendaraan>();
        conJenis = new ControllerJenisKendaraan();
        
            
        if (mode == 1) {
            String query = txtCariKendaraan.getText();
            ModelJenisKendaraan mjk = new ModelJenisKendaraan();
            mjk.setTipe_kendaraan(query);
            lsJk = conJenis.cariData(mjk);
        } else {
            lsJk = conJenis.ambilData();
        }
        
        
        Object [][] obJk = new Object[lsJk.size()][3];
        
        int i = 0;
        
        for (ModelJenisKendaraan jk : lsJk) {
            obJk[i][0] = jk.getId_jenis();
            obJk[i][1] = jk.getTipe_kendaraan();
            obJk[i][2] = jk.getHarga();
            i++;
        }
        
        tblJenisKendaraan.setModel(new javax.swing.table.DefaultTableModel(
            obJk,
            new String [] {
                "ID Jenis", "Nama Kendaraan", "Harga"
            }
        ));
        jScrollPane1.setViewportView(tblJenisKendaraan);
        tblJenisKendaraan.setDefaultEditor(Object.class, null);
    }
    
    private void loadData() {
        List<ModelTransaksi> lsTransaksi = new ArrayList<ModelTransaksi>();
        conTransaksi = new ControllerTransaksi();
        
        lsTransaksi = conTransaksi.ambilTransaksi(0, null);
        
        Object obTransaksi[][] = new Object[lsTransaksi.size()][8];
        
        int i = 0;
        
        for (ModelTransaksi mt : lsTransaksi) {
            obTransaksi[i][0] = mt.getId_trx();
            if (mt.getStatus().equals("0")) {
                obTransaksi[i][1] = "Belum Selesai";
            } else {
                obTransaksi[i][1] = "Selesai!";
            }
            obTransaksi[i][2] = mt.getId_user() + " | " + mt.getNama_user();
            obTransaksi[i][3] = mt.getTgl_cuci();
            obTransaksi[i][4] = mt.getId_jenis() + " | " + mt.getNama_ken();
            obTransaksi[i][5] = mt.getTot_bayar();
            obTransaksi[i][6] = mt.getBayar();
            obTransaksi[i][7] = mt.getKembali();
            i++;
        }
        
        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            obTransaksi,
            new String [] {
                "ID Transaksi", "Status Pencucian", "Pelanggan", "Tanggal Cuci", "Jenis Kendaraan", "Total Pembayaran", "Bayar", "Kembali"
            }
        ));
        jScrollPane3.setViewportView(tblTransaksi);
        tblTransaksi.setDefaultEditor(Object.class, null);
    }
    
    private void activateForm(boolean a) {
        btnCariJKendaraan.setEnabled(a);
        btnCariPelanggan.setEnabled(a);
    }
    
    private void activateButton(int param) {
        if (param == 1) {
            txtIDTrx.setEditable(false);
            btnTrxBaru.setEnabled(false);
            btnBatal.setEnabled(true);
            btnBayar.setEnabled(true);
        } else {
            txtIDTrx.setEditable(true);
            btnTrxBaru.setEnabled(true);
            btnBatal.setEnabled(false);
            btnBayar.setEnabled(false);
        }
    }
    
    private void getIdTrx() {
        txtIDTrx.setText(conTransaksi.getIDTransaksi());
    }
    
    private void clearData() {
        txtIDTrx.setText("");
        txtIDPel.setText("");
        txtNamaPel.setText("");
        txtIDJKen.setText("");
        txtNamaKen.setText("");
        txtTotBayar.setText("");
        txtBayar.setText("");
        txtKembali.setText("");
    }
    
    private void tampilStruk(String id_transaksi) {
        try {
            String NamaFile = "src/Report/StrukTransaksi.jrxml"; //Lokasi File jasper
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            conMan = new ConnectionManager();
            con = conMan.logOn();
            HashMap hash = new HashMap();
            
            //Mengambil parameter dari ireport
            hash.put("id_trx", id_transaksi);
            JasperReport jasperReport = JasperCompileManager.compileReport(NamaFile);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hash, con);
            JasperViewer.viewReport(jasperPrint, false);
            conMan.logOff();
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogPelanggan = new javax.swing.JDialog();
        pnlPelanggan = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPelanggan = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        txtCariPelanggan = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        dialogJenisKendaraan = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblJenisKendaraan = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtCariKendaraan = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtIDTrx = new javax.swing.JTextField();
        txtIDPel = new javax.swing.JTextField();
        txtNamaPel = new javax.swing.JTextField();
        txtIDJKen = new javax.swing.JTextField();
        txtNamaKen = new javax.swing.JTextField();
        txtTotBayar = new javax.swing.JTextField();
        txtBayar = new javax.swing.JTextField();
        txtKembali = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnCariPelanggan = new javax.swing.JButton();
        btnCariJKendaraan = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btnTrxBaru = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnBayar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();

        dialogPelanggan.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dialogPelanggan.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                dialogPelangganWindowLostFocus(evt);
            }
        });

        tblPelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"AKN-0000", "Test", "Test", "Test"},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Pelanggan", "Nama Pelanggan", "Alamat", "No. Telepon"
            }
        ));
        tblPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPelangganMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPelanggan);

        jLabel12.setText("Cari Pelanggan");

        txtCariPelanggan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariPelangganKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlPelangganLayout = new javax.swing.GroupLayout(pnlPelanggan);
        pnlPelanggan.setLayout(pnlPelangganLayout);
        pnlPelangganLayout.setHorizontalGroup(
            pnlPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
            .addGroup(pnlPelangganLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCariPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlPelangganLayout.setVerticalGroup(
            pnlPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPelangganLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtCariPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(31, 31, 31));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Form Transaksi");

        jLabel10.setText("Tabel Pelanggan");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout dialogPelangganLayout = new javax.swing.GroupLayout(dialogPelanggan.getContentPane());
        dialogPelanggan.getContentPane().setLayout(dialogPelangganLayout);
        dialogPelangganLayout.setHorizontalGroup(
            dialogPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dialogPelangganLayout.setVerticalGroup(
            dialogPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogPelangganLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlPelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dialogJenisKendaraan.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dialogJenisKendaraan.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                dialogJenisKendaraanWindowLostFocus(evt);
            }
        });

        tblJenisKendaraan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"KEN-0000", "Test", "0"},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID Jenis", "Nama Kendaraan", "Harga"
            }
        ));
        tblJenisKendaraan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblJenisKendaraanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblJenisKendaraan);

        jLabel9.setText("Cari Data");

        txtCariKendaraan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariKendaraanKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCariKendaraan, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtCariKendaraan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(31, 31, 31));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Form Transaksi");

        jLabel15.setText("Tabel Jenis Kendaraan");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout dialogJenisKendaraanLayout = new javax.swing.GroupLayout(dialogJenisKendaraan.getContentPane());
        dialogJenisKendaraan.getContentPane().setLayout(dialogJenisKendaraanLayout);
        dialogJenisKendaraanLayout.setHorizontalGroup(
            dialogJenisKendaraanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dialogJenisKendaraanLayout.setVerticalGroup(
            dialogJenisKendaraanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogJenisKendaraanLayout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtIDTrx.setToolTipText("Tekan Tombol Enter Untuk Mencari Data");
        txtIDTrx.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIDTrxKeyPressed(evt);
            }
        });

        txtIDPel.setEditable(false);

        txtNamaPel.setEditable(false);

        txtIDJKen.setEditable(false);

        txtNamaKen.setEditable(false);

        txtTotBayar.setEditable(false);

        txtBayar.setEditable(false);
        txtBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBayarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBayarKeyTyped(evt);
            }
        });

        txtKembali.setEditable(false);

        jLabel3.setText("Pelanggan");

        jLabel2.setText("ID Transaksi");

        jLabel4.setText("Jenis Kendaraan");

        jLabel5.setText("Total Penanganan");

        jLabel6.setText("Bayar");

        jLabel7.setText("Kembalian");

        btnCariPelanggan.setText("Cari Data");
        btnCariPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPelangganActionPerformed(evt);
            }
        });

        btnCariJKendaraan.setText("Cari Data");
        btnCariJKendaraan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariJKendaraanActionPerformed(evt);
            }
        });

        btnTrxBaru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/new-transaction.png"))); // NOI18N
        btnTrxBaru.setText("Transaksi Baru");
        btnTrxBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrxBaruActionPerformed(evt);
            }
        });

        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/cancel.png"))); // NOI18N
        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/pay.png"))); // NOI18N
        btnBayar.setText("Bayar");
        btnBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTrxBaru, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnTrxBaru)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBatal)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtKembali, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtBayar, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtTotBayar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIDPel)
                            .addComponent(txtIDJKen, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNamaPel)
                            .addComponent(txtNamaKen, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCariPelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                            .addComponent(btnCariJKendaraan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtIDTrx, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDTrx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDPel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamaPel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(btnCariPelanggan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDJKen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamaKen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(btnCariJKendaraan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"TRX-0000", "Tercuci", "PEL-0000 | Test", "01/01/2001 12:00:00", "Testing", "0", "0", "0"},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Transaksi", "Status Pencucian", "Pelanggan", "Tanggal Cuci", "Jenis Kendaraan", "Total Pembayaran", "Bayar", "Kembali"
            }
        ));
        jScrollPane3.setViewportView(tblTransaksi);

        jLabel8.setText("Tabel Transaksi");

        jLabel1.setText("Cari Data");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(31, 31, 31));

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Form Transaksi");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCariPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPelangganActionPerformed
        // TODO add your handling code here:
        loadPengguna(0);
        dialogPelanggan.setSize(834, 478);
        dialogPelanggan.setLocationRelativeTo(this);
        dialogPelanggan.setVisible(true);
        dialogPelanggan.setResizable(false);
        dialogPelanggan.setTitle("Cari Pelanggan");
    }//GEN-LAST:event_btnCariPelangganActionPerformed

    private void btnCariJKendaraanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariJKendaraanActionPerformed
        // TODO add your handling code here:
        loadKendaraan(0);
        dialogJenisKendaraan.setSize(700, 424);
        dialogJenisKendaraan.setLocationRelativeTo(this);
        dialogJenisKendaraan.setVisible(true);
        dialogJenisKendaraan.setResizable(false);
        dialogJenisKendaraan.setTitle("Cari Jenis Kendaraan");
    }//GEN-LAST:event_btnCariJKendaraanActionPerformed

    private void tblPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPelangganMouseClicked
        // TODO add your handling code here:
        int row = tblPelanggan.getSelectedRow();
        
        txtIDPel.setText((String) tblPelanggan.getValueAt(row, 0));
        txtNamaPel.setText((String) tblPelanggan.getValueAt(row, 1));
        
        dialogPelanggan.dispose();
    }//GEN-LAST:event_tblPelangganMouseClicked

    private void btnTrxBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrxBaruActionPerformed
        // TODO add your handling code here:
        activateForm(true);
        activateButton(1);
        getIdTrx();
    }//GEN-LAST:event_btnTrxBaruActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        clearData();
        activateButton(0);
        activateForm(false);
        txtBayar.setEditable(false);
    }//GEN-LAST:event_btnBatalActionPerformed

    private void tblJenisKendaraanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblJenisKendaraanMouseClicked
        // TODO add your handling code here:
        int row = tblJenisKendaraan.getSelectedRow();
        
        txtIDJKen.setText((String) tblJenisKendaraan.getValueAt(row, 0));
        txtNamaKen.setText((String) tblJenisKendaraan.getValueAt(row, 1));
        txtTotBayar.setText(Integer.toString((int) tblJenisKendaraan.getValueAt(row, 2)));
        txtBayar.setEditable(true);
        
        dialogJenisKendaraan.dispose();
    }//GEN-LAST:event_tblJenisKendaraanMouseClicked

    private void txtBayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBayarKeyReleased
        // TODO add your handling code here:
        float x;
        try {
            x = Float.parseFloat(txtBayar.getText());
            hitungKembali();
        } catch (NumberFormatException e) {    
            System.out.println(e);
            getToolkit().beep();
            //OptionPane.showMessageDialog(this, "Hanya Masukkan Angka!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            txtBayar.setText("");
            txtKembali.setText("");
        }
    }//GEN-LAST:event_txtBayarKeyReleased

    private void txtBayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBayarKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtBayarKeyTyped

    private void dialogPelangganWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_dialogPelangganWindowLostFocus
        // TODO add your handling code here:
        dialogPelanggan.dispose();
    }//GEN-LAST:event_dialogPelangganWindowLostFocus

    private void dialogJenisKendaraanWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_dialogJenisKendaraanWindowLostFocus
        // TODO add your handling code here:
        dialogJenisKendaraan.dispose();
    }//GEN-LAST:event_dialogJenisKendaraanWindowLostFocus

    private void btnBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarActionPerformed
        // TODO add your handling code here:
        String idtrx, idpel, idken, totbayar, bayar, kembali, date;
        int hasil = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/mm/dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.now();

        idtrx = txtIDTrx.getText();
        idpel = txtIDPel.getText();
        idken = txtIDJKen.getText();
        totbayar = txtTotBayar.getText();
        bayar = txtBayar.getText();
        kembali = txtKembali.getText();
        date = ldt.format(dtf);
        
        ModelTransaksi mta = new ModelTransaksi();
        mta.setId_trx(idtrx);
        hasil = conTransaksi.cekDataTransaksi(mta);
        
        switch (hasil) {
            case 0:
                if (idtrx.equals("") || idken.equals("") || idpel.equals("") || totbayar.equals("") || bayar.equals("") || kembali.equals("")) {
                    JOptionPane.showMessageDialog(this, "Data Tidak Lengkap, Silahkan Cek Kembali!", "Error",  JOptionPane.ERROR_MESSAGE);
                } else if (Integer.parseInt(kembali) < 0){
                    JOptionPane.showMessageDialog(this, "Uang Kurang!", "Error",  JOptionPane.ERROR_MESSAGE);
                    txtBayar.setFocusable(true);
                } else {
                    ModelTransaksi mt = new ModelTransaksi(idtrx, idpel, idken, date, "1", totbayar, bayar, kembali);
                    hasil = conTransaksi.transaksiBaru(mt, 2);
                    
                    if (hasil == 1) {
                        JOptionPane.showMessageDialog(this, "Transaksi Berhasil!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                        tampilStruk(idtrx);
                        clearData();
                        loadData();
                        activateForm(false);
                        activateButton(0);
                        txtBayar.setEditable(false);
                    } else {
                        JOptionPane.showMessageDialog(this, "Transaksi Gagal, Silahkan Coba Lagi.", "Error",  JOptionPane.ERROR_MESSAGE);
                    }
                }   
                break;
            case 1:
                if (idtrx.equals("") || idken.equals("") || idpel.equals("") || totbayar.equals("") || bayar.equals("") || kembali.equals("")) {
                    JOptionPane.showMessageDialog(this, "Data Tidak Lengkap, Silahkan Cek Kembali!", "Error",  JOptionPane.ERROR_MESSAGE);
                } else if (Integer.parseInt(kembali) < 0){
                    JOptionPane.showMessageDialog(this, "Uang Kurang!", "Error",  JOptionPane.ERROR_MESSAGE);
                    txtBayar.setFocusable(true);
                } else {
                    ModelTransaksi mt = new ModelTransaksi(idtrx, idpel, idken, date, "1", totbayar, bayar, kembali);
                    hasil = conTransaksi.transaksiBaru(mt, 3);
                    
                    if (hasil == 1) {
                        JOptionPane.showMessageDialog(this, "Transaksi Berhasil!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                        tampilStruk(idtrx);
                        clearData();
                        loadData();
                        activateForm(false);
                        activateButton(0);
                        txtBayar.setEditable(false);
                    } else {
                        JOptionPane.showMessageDialog(this, "Transaksi Gagal, Silahkan Coba Lagi.", "Error",  JOptionPane.ERROR_MESSAGE);
                    }
                }  
                break;
            default:
                JOptionPane.showMessageDialog(this, "Tidak Dapat Menemukan Transaksi Dengan ID : " +  idtrx, "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }//GEN-LAST:event_btnBayarActionPerformed

    private void txtIDTrxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDTrxKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String idtrx, status = null;
            int hasil = 0;
            
            idtrx = txtIDTrx.getText();
            
            ModelTransaksi mt = new ModelTransaksi();
            mt.setId_trx(idtrx);
            
            hasil = conTransaksi.cekDataTransaksi(mt);
            
            if (hasil == 1) {
                List<ModelTransaksi> lsTrx = new ArrayList<ModelTransaksi>();
                conTransaksi = new ControllerTransaksi();
                
                lsTrx = conTransaksi.cariData(mt);
                
                int i = 0;
                
                for (ModelTransaksi mta : lsTrx) {
                    txtIDPel.setText(mta.getId_user());
                    txtNamaPel.setText(mta.getNama_user());
                    txtIDJKen.setText(mta.getId_jenis());
                    txtNamaKen.setText(mta.getNama_ken());
                    txtTotBayar.setText(mta.getTot_bayar());
                    txtBayar.setText(mta.getBayar());
                    txtKembali.setText(mta.getKembali());
                    status = mta.getStatus();
                    
                    i++;
                }
                
                if (!status.equals("0")) {
                    activateButton(1);
                    activateForm(false);
                    txtBayar.setEditable(false);
                    btnBayar.setEnabled(false);
                } else {
                    activateButton(1);
                    activateForm(false);
                    txtBayar.setEditable(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Transaksi Dengan ID : " + idtrx + " Tidak Ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
                txtIDTrx.setText("");
            }
        }
    }//GEN-LAST:event_txtIDTrxKeyPressed

    private void txtCariPelangganKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariPelangganKeyTyped
        // TODO add your handling code here:
        loadPengguna(1);
    }//GEN-LAST:event_txtCariPelangganKeyTyped

    private void txtCariKendaraanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKendaraanKeyTyped
        // TODO add your handling code here:
        loadKendaraan(1);
    }//GEN-LAST:event_txtCariKendaraanKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnBayar;
    private javax.swing.JButton btnCariJKendaraan;
    private javax.swing.JButton btnCariPelanggan;
    private javax.swing.JButton btnTrxBaru;
    private javax.swing.JDialog dialogJenisKendaraan;
    private javax.swing.JDialog dialogPelanggan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel pnlPelanggan;
    private javax.swing.JTable tblJenisKendaraan;
    private javax.swing.JTable tblPelanggan;
    private javax.swing.JTable tblTransaksi;
    private javax.swing.JTextField txtBayar;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtCariKendaraan;
    private javax.swing.JTextField txtCariPelanggan;
    private javax.swing.JTextField txtIDJKen;
    private javax.swing.JTextField txtIDPel;
    private javax.swing.JTextField txtIDTrx;
    private javax.swing.JTextField txtKembali;
    private javax.swing.JTextField txtNamaKen;
    private javax.swing.JTextField txtNamaPel;
    private javax.swing.JTextField txtTotBayar;
    // End of variables declaration//GEN-END:variables
}
