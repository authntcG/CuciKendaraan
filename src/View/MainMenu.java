/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.ModelLogin;
import com.formdev.flatlaf.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

/**
 *
 * @author Galih Respati P
 */
public class MainMenu extends javax.swing.JFrame {
    
    private Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
    private Color hover = new Color(59, 62, 64);
    private Color normal = new Color(31, 31, 31);
    
    private MainJenisKendaraan jeniskendaraan;
    private MainAkun akun;
    private MainTransaksiAdmin trxadmin;
    private MainTransaksiUser trxuser;
    private MainRiwayatTransaksi rwyttrx;
    private int menuChoosen = 0;

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        setUndecorated(true);
        FlatLightLaf.install();
        System.setProperty("flatlaf.useWindowDecorations", "true");
        FlatLaf.updateUI();
        initComponents();
        //this.setLocation(dm.width / 2 - this.getWidth() / 2, dm.height / 2 - this.getWidth() / 2);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        
        
        initContent();
    }
    
    private void initContent() {
        //Display For My Laptop : 1628x1080
        Dimension dm = new Dimension(1590, 1080);
        //Layout Manager
        GridBagLayout gbl = new GridBagLayout();
        
        jeniskendaraan = new MainJenisKendaraan();
        jeniskendaraan.setPreferredSize(dm);
        akun = new MainAkun();
        akun.setPreferredSize(dm);
        trxadmin = new MainTransaksiAdmin();
        trxadmin.setPreferredSize(dm);
        trxuser = new MainTransaksiUser();
        trxuser.setPreferredSize(dm);
        rwyttrx = new MainRiwayatTransaksi();
        rwyttrx.setPreferredSize(dm);
        
        
        pnlKonten.setLayout(gbl);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        
        pnlKonten.add(jeniskendaraan, gbc);
        pnlKonten.add(akun, gbc);
        pnlKonten.add(trxadmin, gbc);
        pnlKonten.add(trxuser, gbc);
        pnlKonten.add(rwyttrx, gbc);
        
        viewForm(0);
    }
    
    private void viewForm(int form_no) {
        /*
            OTHER THAN LIST BELOW : OFF aka NO DISPLAY
            Number 1 : Data Akun
            Number 2 : Data Jenis Kendaraaan
            Number 3 : Transaksi Admin
            Number 4 : Transaksi (User Only)
            Number 5 : Riwayat Transaksi (User Only)
         */
        switch (form_no) {
            case 1:
                menuChoosen = 1;
                jeniskendaraan.setVisible(false);
                akun.setVisible(true);
                trxadmin.setVisible(false);
                trxuser.setVisible(false);
                rwyttrx.setVisible(false);
                break;
            case 2:
                menuChoosen = 2;
                jeniskendaraan.setVisible(true);
                akun.setVisible(false);
                trxadmin.setVisible(false);
                trxuser.setVisible(false);
                rwyttrx.setVisible(false);
                break;
            case 3:
                menuChoosen = 3;
                jeniskendaraan.setVisible(false);
                akun.setVisible(false);
                trxadmin.setVisible(true);
                trxuser.setVisible(false);
                rwyttrx.setVisible(false);
                break;
            case 4:
                menuChoosen = 4;
                jeniskendaraan.setVisible(false);
                akun.setVisible(false);
                trxadmin.setVisible(false);
                trxuser.setVisible(true);
                rwyttrx.setVisible(false);
                break;
            case 5:
                menuChoosen = 5;
                jeniskendaraan.setVisible(false);
                akun.setVisible(false);
                trxadmin.setVisible(false);
                trxuser.setVisible(false);
                rwyttrx.setVisible(true);
                break;
            default:
                jeniskendaraan.setVisible(false);
                akun. setVisible(false);
                trxadmin.setVisible(false);
                trxuser.setVisible(false);
                rwyttrx.setVisible(false);
                break;
        }
    }
    
    private void riwayatTransaksiHighlighted(boolean q) {
        Font f = lblRiwayatTransaksi.getFont();
        
        if (q == true) {
            pnlRiwayatTransaksi.setBackground(hover);
            lblRiwayatTransaksi.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        } else {
            pnlRiwayatTransaksi.setBackground(normal);
            lblRiwayatTransaksi.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
        }
    }
    
    private void transaksiUserHighlighted(boolean r) {
        Font f = lblTransaksiUser.getFont();
        
        if (r == true) {
            pnlTransaksiUser.setBackground(hover);
            lblTransaksiUser.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        } else {
            pnlTransaksiUser.setBackground(normal);
            lblTransaksiUser.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
        }
    }
    
    private void transaksiAdminHighlighted(boolean s) {
        Font f = lblTransaksiAdmin.getFont();
        
        if (s == true) {
            pnlTransaksiAdmin.setBackground(hover);
            lblTransaksiAdmin.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        } else {
            pnlTransaksiAdmin.setBackground(normal);
            lblTransaksiAdmin.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
        }
    }
    
    private void jenisKendaraanHighlighted(boolean t) {
        Font f = lblJenisKendaraan.getFont();
        
        if (t == true) {
            pnlJenisKendaraan.setBackground(hover);
            lblJenisKendaraan.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        } else {
            pnlJenisKendaraan.setBackground(normal);
            lblJenisKendaraan.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
        }
    }
    
    private void dataAkunHighlighted(boolean u) {
        Font f = lblDataAkun.getFont();
        
        if (u == true) {
            pnlDataAkun.setBackground(hover);
            lblDataAkun.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        } else {
            pnlDataAkun.setBackground(normal);
            lblDataAkun.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
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

        pnlMenu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pnlTransaksiAdmin = new javax.swing.JPanel();
        lblTransaksiAdmin = new javax.swing.JLabel();
        pnlRiwayatTransaksi = new javax.swing.JPanel();
        lblRiwayatTransaksi = new javax.swing.JLabel();
        pnlJenisKendaraan = new javax.swing.JPanel();
        lblJenisKendaraan = new javax.swing.JLabel();
        pnlTransaksiUser = new javax.swing.JPanel();
        lblTransaksiUser = new javax.swing.JLabel();
        pnlKeluar = new javax.swing.JPanel();
        lblKeluar = new javax.swing.JLabel();
        pnlDataAkun = new javax.swing.JPanel();
        lblDataAkun = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblNamaUser = new javax.swing.JLabel();
        pnlKonten = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Menu Utama");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        pnlMenu.setBackground(new java.awt.Color(31, 31, 31));
        pnlMenu.setMaximumSize(new java.awt.Dimension(291, 32767));
        pnlMenu.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                pnlMenuMouseMoved(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/car-wash-iconapp.png"))); // NOI18N
        jLabel1.setText("myVWash");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Main Form");

        pnlTransaksiAdmin.setBackground(new java.awt.Color(31, 31, 31));

        lblTransaksiAdmin.setForeground(new java.awt.Color(255, 255, 255));
        lblTransaksiAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/online-transfer.png"))); // NOI18N
        lblTransaksiAdmin.setText("Transaksi (Administrator)");
        lblTransaksiAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTransaksiAdminMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTransaksiAdminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblTransaksiAdminMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlTransaksiAdminLayout = new javax.swing.GroupLayout(pnlTransaksiAdmin);
        pnlTransaksiAdmin.setLayout(pnlTransaksiAdminLayout);
        pnlTransaksiAdminLayout.setHorizontalGroup(
            pnlTransaksiAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTransaksiAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTransaksiAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTransaksiAdminLayout.setVerticalGroup(
            pnlTransaksiAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTransaksiAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTransaksiAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlRiwayatTransaksi.setBackground(new java.awt.Color(31, 31, 31));

        lblRiwayatTransaksi.setForeground(new java.awt.Color(255, 255, 255));
        lblRiwayatTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/clock.png"))); // NOI18N
        lblRiwayatTransaksi.setText("Riwayat Transaksi");
        lblRiwayatTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRiwayatTransaksiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblRiwayatTransaksiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblRiwayatTransaksiMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlRiwayatTransaksiLayout = new javax.swing.GroupLayout(pnlRiwayatTransaksi);
        pnlRiwayatTransaksi.setLayout(pnlRiwayatTransaksiLayout);
        pnlRiwayatTransaksiLayout.setHorizontalGroup(
            pnlRiwayatTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRiwayatTransaksiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRiwayatTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlRiwayatTransaksiLayout.setVerticalGroup(
            pnlRiwayatTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRiwayatTransaksiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRiwayatTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlJenisKendaraan.setBackground(new java.awt.Color(31, 31, 31));

        lblJenisKendaraan.setForeground(new java.awt.Color(255, 255, 255));
        lblJenisKendaraan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/car-insurance.png"))); // NOI18N
        lblJenisKendaraan.setText("Data Jenis Kendaraan");
        lblJenisKendaraan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblJenisKendaraanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblJenisKendaraanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblJenisKendaraanMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlJenisKendaraanLayout = new javax.swing.GroupLayout(pnlJenisKendaraan);
        pnlJenisKendaraan.setLayout(pnlJenisKendaraanLayout);
        pnlJenisKendaraanLayout.setHorizontalGroup(
            pnlJenisKendaraanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlJenisKendaraanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblJenisKendaraan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlJenisKendaraanLayout.setVerticalGroup(
            pnlJenisKendaraanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlJenisKendaraanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblJenisKendaraan, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlTransaksiUser.setBackground(new java.awt.Color(31, 31, 31));

        lblTransaksiUser.setForeground(new java.awt.Color(255, 255, 255));
        lblTransaksiUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/online-transfer.png"))); // NOI18N
        lblTransaksiUser.setText("Transaksi");
        lblTransaksiUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTransaksiUserMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTransaksiUserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblTransaksiUserMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlTransaksiUserLayout = new javax.swing.GroupLayout(pnlTransaksiUser);
        pnlTransaksiUser.setLayout(pnlTransaksiUserLayout);
        pnlTransaksiUserLayout.setHorizontalGroup(
            pnlTransaksiUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTransaksiUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTransaksiUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTransaksiUserLayout.setVerticalGroup(
            pnlTransaksiUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTransaksiUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTransaksiUser, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlKeluar.setBackground(new java.awt.Color(31, 31, 31));

        lblKeluar.setForeground(new java.awt.Color(255, 255, 255));
        lblKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/exit.png"))); // NOI18N
        lblKeluar.setText("Keluar");
        lblKeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblKeluarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblKeluarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblKeluarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlKeluarLayout = new javax.swing.GroupLayout(pnlKeluar);
        pnlKeluar.setLayout(pnlKeluarLayout);
        pnlKeluarLayout.setHorizontalGroup(
            pnlKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKeluarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlKeluarLayout.setVerticalGroup(
            pnlKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKeluarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlDataAkun.setBackground(new java.awt.Color(31, 31, 31));

        lblDataAkun.setForeground(new java.awt.Color(255, 255, 255));
        lblDataAkun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/private.png"))); // NOI18N
        lblDataAkun.setText("Data Akun");
        lblDataAkun.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDataAkunMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDataAkunMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDataAkunMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlDataAkunLayout = new javax.swing.GroupLayout(pnlDataAkun);
        pnlDataAkun.setLayout(pnlDataAkunLayout);
        pnlDataAkunLayout.setHorizontalGroup(
            pnlDataAkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataAkunLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDataAkun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDataAkunLayout.setVerticalGroup(
            pnlDataAkunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataAkunLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDataAkun, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/user.png"))); // NOI18N
        jLabel3.setText("Selamat Datang,");

        lblNamaUser.setForeground(new java.awt.Color(255, 255, 255));
        lblNamaUser.setText("namapengguna");
        lblNamaUser.setToolTipText(ModelLogin.getNama());

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(pnlMenuLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNamaUser, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addContainerGap(28, Short.MAX_VALUE))
            .addComponent(pnlTransaksiAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlRiwayatTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlJenisKendaraan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlTransaksiUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlDataAkun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblNamaUser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(162, 162, 162)
                .addComponent(pnlDataAkun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlJenisKendaraan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTransaksiAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTransaksiUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRiwayatTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 490, Short.MAX_VALUE)
                .addComponent(pnlKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlKontenLayout = new javax.swing.GroupLayout(pnlKonten);
        pnlKonten.setLayout(pnlKontenLayout);
        pnlKontenLayout.setHorizontalGroup(
            pnlKontenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 942, Short.MAX_VALUE)
        );
        pnlKontenLayout.setVerticalGroup(
            pnlKontenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlKonten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlKonten, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblKeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKeluarMouseClicked
        // TODO add your handling code here:
        int quest = JOptionPane.showConfirmDialog(this, "Keluar Aplikasi?", "Pertanyaan", JOptionPane.YES_NO_OPTION);
        
        if (quest == JOptionPane.YES_OPTION) {
            this.dispose();
            new MainLogin().setVisible(true);
        }
    }//GEN-LAST:event_lblKeluarMouseClicked

    private void lblKeluarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKeluarMouseEntered
        // TODO add your handling code here:
        Font f = lblKeluar.getFont();
        pnlKeluar.setBackground(hover);
        lblKeluar.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
    }//GEN-LAST:event_lblKeluarMouseEntered

    private void lblKeluarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKeluarMouseExited
        // TODO add your handling code here:
        Font f = lblKeluar.getFont();
        pnlKeluar.setBackground(normal);
        lblKeluar.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
    }//GEN-LAST:event_lblKeluarMouseExited

    private void lblDataAkunMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDataAkunMouseClicked
        // TODO add your handling code here:
        viewForm(1);
    }//GEN-LAST:event_lblDataAkunMouseClicked

    private void lblJenisKendaraanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblJenisKendaraanMouseClicked
        // TODO add your handling code here:
        viewForm(2);
    }//GEN-LAST:event_lblJenisKendaraanMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        lblNamaUser.setText(ModelLogin.getNama());
        switch (ModelLogin.getAccess()) {
            case 1:
                lblDataAkun.setVisible(true);
                pnlDataAkun.setVisible(true);
                lblJenisKendaraan.setVisible(true); 
                pnlJenisKendaraan.setVisible(true);
                lblTransaksiAdmin.setVisible(true);
                pnlTransaksiAdmin.setVisible(true);
                lblTransaksiUser.setVisible(false);
                pnlTransaksiUser.setVisible(false);
                lblRiwayatTransaksi.setVisible(false);
                pnlRiwayatTransaksi.setVisible(false);
                break;
            case 2:
                lblDataAkun.setVisible(false);
                pnlDataAkun.setVisible(false);
                lblJenisKendaraan.setVisible(false);
                pnlJenisKendaraan.setVisible(false);
                lblTransaksiAdmin.setVisible(false);
                pnlTransaksiAdmin.setVisible(false);
                lblTransaksiUser.setVisible(true);
                pnlTransaksiUser.setVisible(true);
                lblRiwayatTransaksi.setVisible(true);
                pnlRiwayatTransaksi.setVisible(true);
                break;
            default:
                lblDataAkun.setVisible(false);
                pnlDataAkun.setVisible(false);
                lblJenisKendaraan.setVisible(false);
                pnlJenisKendaraan.setVisible(false);
                lblTransaksiAdmin.setVisible(false);
                pnlTransaksiAdmin.setVisible(false);
                lblTransaksiUser.setVisible(false);
                pnlTransaksiUser.setVisible(false);
                lblRiwayatTransaksi.setVisible(false);
                pnlRiwayatTransaksi.setVisible(false);
                JOptionPane.showMessageDialog(this, "Akses Masuk Illegal Diketahui!");
                this.dispose();
                new MainLogin().setVisible(true);
                break;
        }
    }//GEN-LAST:event_formWindowActivated

    private void lblRiwayatTransaksiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRiwayatTransaksiMouseEntered
        // TODO add your handling code here:
        riwayatTransaksiHighlighted(true);
    }//GEN-LAST:event_lblRiwayatTransaksiMouseEntered

    private void lblRiwayatTransaksiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRiwayatTransaksiMouseExited
        // TODO add your handling code here:
        riwayatTransaksiHighlighted(false);
    }//GEN-LAST:event_lblRiwayatTransaksiMouseExited

    private void lblTransaksiUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTransaksiUserMouseEntered
        // TODO add your handling code here:
        transaksiUserHighlighted(true);
    }//GEN-LAST:event_lblTransaksiUserMouseEntered

    private void lblTransaksiUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTransaksiUserMouseExited
        // TODO add your handling code here:
        transaksiUserHighlighted(false);
    }//GEN-LAST:event_lblTransaksiUserMouseExited

    private void lblTransaksiAdminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTransaksiAdminMouseEntered
        // TODO add your handling code here:
        transaksiAdminHighlighted(true);
    }//GEN-LAST:event_lblTransaksiAdminMouseEntered

    private void lblTransaksiAdminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTransaksiAdminMouseExited
        // TODO add your handling code here:
        transaksiAdminHighlighted(false);
    }//GEN-LAST:event_lblTransaksiAdminMouseExited

    private void lblJenisKendaraanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblJenisKendaraanMouseEntered
        // TODO add your handling code here:
        jenisKendaraanHighlighted(true);
    }//GEN-LAST:event_lblJenisKendaraanMouseEntered

    private void lblJenisKendaraanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblJenisKendaraanMouseExited
        // TODO add your handling code here:
        jenisKendaraanHighlighted(false);
    }//GEN-LAST:event_lblJenisKendaraanMouseExited

    private void lblDataAkunMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDataAkunMouseEntered
        // TODO add your handling code here:
        dataAkunHighlighted(true);
    }//GEN-LAST:event_lblDataAkunMouseEntered

    private void lblDataAkunMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDataAkunMouseExited
        // TODO add your handling code here:
        dataAkunHighlighted(false);
    }//GEN-LAST:event_lblDataAkunMouseExited

    private void pnlMenuMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenuMouseMoved
        // TODO add your handling code here:
        switch (menuChoosen) {
            case 1:
                dataAkunHighlighted(true);
                jenisKendaraanHighlighted(false);
                transaksiAdminHighlighted(false);
                transaksiUserHighlighted(false);
                riwayatTransaksiHighlighted(false);
                break;
            case 2:
                dataAkunHighlighted(false);
                jenisKendaraanHighlighted(true);
                transaksiAdminHighlighted(false);
                transaksiUserHighlighted(false);
                riwayatTransaksiHighlighted(false);
                break;
            case 3:
                dataAkunHighlighted(false);
                jenisKendaraanHighlighted(false);
                transaksiAdminHighlighted(true);
                transaksiUserHighlighted(false);
                riwayatTransaksiHighlighted(false);
                break;
            case 4:
                dataAkunHighlighted(false);
                jenisKendaraanHighlighted(false);
                transaksiAdminHighlighted(false);
                transaksiUserHighlighted(true);
                riwayatTransaksiHighlighted(false);
                break;
            case 5:
                dataAkunHighlighted(false);
                jenisKendaraanHighlighted(false);
                transaksiAdminHighlighted(false);
                transaksiUserHighlighted(false);
                riwayatTransaksiHighlighted(true);
                break;
            default:
                dataAkunHighlighted(false);
                jenisKendaraanHighlighted(false);
                transaksiAdminHighlighted(false);
                transaksiUserHighlighted(false);
                riwayatTransaksiHighlighted(false);
                break;
        }
    }//GEN-LAST:event_pnlMenuMouseMoved

    private void lblTransaksiAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTransaksiAdminMouseClicked
        // TODO add your handling code here:
        viewForm(3);
    }//GEN-LAST:event_lblTransaksiAdminMouseClicked

    private void lblTransaksiUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTransaksiUserMouseClicked
        // TODO add your handling code here:
        viewForm(4);
    }//GEN-LAST:event_lblTransaksiUserMouseClicked

    private void lblRiwayatTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRiwayatTransaksiMouseClicked
        // TODO add your handling code here:
        viewForm(5);
    }//GEN-LAST:event_lblRiwayatTransaksiMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblDataAkun;
    private javax.swing.JLabel lblJenisKendaraan;
    private javax.swing.JLabel lblKeluar;
    private javax.swing.JLabel lblNamaUser;
    private javax.swing.JLabel lblRiwayatTransaksi;
    private javax.swing.JLabel lblTransaksiAdmin;
    private javax.swing.JLabel lblTransaksiUser;
    private javax.swing.JPanel pnlDataAkun;
    private javax.swing.JPanel pnlJenisKendaraan;
    private javax.swing.JPanel pnlKeluar;
    private javax.swing.JPanel pnlKonten;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlRiwayatTransaksi;
    private javax.swing.JPanel pnlTransaksiAdmin;
    private javax.swing.JPanel pnlTransaksiUser;
    // End of variables declaration//GEN-END:variables
}
