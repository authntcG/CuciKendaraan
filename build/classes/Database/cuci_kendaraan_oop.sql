-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 04 Jun 2021 pada 14.07
-- Versi server: 10.4.18-MariaDB
-- Versi PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cuci_kendaraan_oop`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `akun`
--

CREATE TABLE `akun` (
  `id_akun` varchar(10) NOT NULL,
  `nama` varchar(35) NOT NULL,
  `alamat` varchar(35) NOT NULL,
  `no_telp` varchar(15) NOT NULL,
  `username` varchar(35) NOT NULL,
  `password` varchar(35) NOT NULL,
  `akses` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `akun`
--

INSERT INTO `akun` (`id_akun`, `nama`, `alamat`, `no_telp`, `username`, `password`, `akses`) VALUES
('AKN-001', 'Galih Respati Permana', 'Ujungberung', '0813223334455', 'admin', 'admin', 1),
('AKN-002', 'Udin', 'Bandung', '081344556677', 'user', 'user', 2),
('AKN-003', 'Demi', 'Bandung', '081226389503', 'demi', 'demi', 2),
('AKN-004', 'Ucup', 'Bandung', '081536472948', 'ucup', 'ucup', 2),
('AKN-005', 'nafia', 'bandung', '081322554466', 'haikal', 'haikal', 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `jenis_kendaraan`
--

CREATE TABLE `jenis_kendaraan` (
  `id_jenis` varchar(10) NOT NULL,
  `tipe_kendaraan` varchar(35) NOT NULL,
  `harga` bigint(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `jenis_kendaraan`
--

INSERT INTO `jenis_kendaraan` (`id_jenis`, `tipe_kendaraan`, `harga`) VALUES
('KEN-001', 'Motor < 250cc', 15000),
('KEN-002', 'Motor 250cc s/d 600cc', 20000),
('KEN-003', 'Motor > 600cc', 25000),
('KEN-004', 'Mobil : Small Size', 36000),
('KEN-005', 'Mobil : Mid Size', 41000),
('KEN-006', 'Mobil : Large Size', 46000),
('KEN-007', 'Truck', 37500),
('KEN-008', 'Sepeda', 10000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` varchar(30) NOT NULL,
  `id_user` varchar(10) NOT NULL,
  `id_jenis` varchar(10) NOT NULL,
  `total_bayar` bigint(10) NOT NULL,
  `bayar` bigint(10) DEFAULT NULL,
  `kembali` bigint(10) DEFAULT NULL,
  `tgl_cuci` varchar(30) DEFAULT NULL,
  `status_cuci` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_user`, `id_jenis`, `total_bayar`, `bayar`, `kembali`, `tgl_cuci`, `status_cuci`) VALUES
('TRX-012254601', 'AKN-002', 'KEN-008', 10000, 20000, 10000, '2021/06/01 22:06:09', 1),
('TRX-012261102', 'AKN-004', 'KEN-007', 37500, 40000, 2500, '2021/06/01 22:06:22', 1),
('TRX-0216255603', 'AKN-002', 'KEN-002', 20000, 20000, 0, '2021/18/02 17:18:41', 1),
('TRX-0217325703', 'AKN-003', 'KEN-006', 46000, 50000, 4000, '2021/33/02 17:33:13', 1),
('TRX-021841303', 'AKN-002', 'KEN-007', 37500, 40000, 2500, '2021/41/02 18:41:14', 1),
('TRX-021847603', 'AKN-003', 'KEN-004', 36000, 40000, 4000, '2021/47/02 18:47:14', 1),
('TRX-0218521403', 'AKN-004', 'KEN-008', 10000, 10000, 0, '2021/52/02 18:52:22', 1),
('TRX-021854204', 'AKN-002', 'KEN-002', 20000, 20000, 0, '2021/13/03 16:13:15', 1),
('TRX-0315383004', 'AKN-002', 'KEN-001', 15000, 15000, 0, '2021/55/04 16:55:45', 1),
('TRX-0315405304', 'AKN-004', 'KEN-002', 20000, NULL, NULL, NULL, 0),
('TRX-0315433005', 'AKN-003', 'KEN-003', 25000, NULL, NULL, NULL, 0),
('TRX-0315491405', 'AKN-002', 'KEN-004', 36000, NULL, NULL, NULL, 0),
('TRX-0315541505', 'AKN-002', 'KEN-005', 41000, NULL, NULL, NULL, 0),
('TRX-0315571105', 'AKN-002', 'KEN-003', 25000, NULL, NULL, NULL, 0),
('TRX-0315591305', 'AKN-002', 'KEN-006', 46000, NULL, NULL, NULL, 0),
('TRX-031602205', 'AKN-002', 'KEN-001', 15000, 50000, 35000, '2021/14/03 16:14:36', 1),
('TRX-031613205', 'AKN-002', 'KEN-005', 41000, 45000, 4000, '2021/13/03 16:13:43', 1),
('TRX-041594605', 'AKN-002', 'KEN-005', 41000, 41000, 0, '2021/10/04 15:10:06', 1),
('TRX-0416572105', 'AKN-002', 'KEN-004', 36000, 40000, 4000, '2021/01/04 17:01:27', 1),
('TRX-04173105', 'AKN-005', 'KEN-004', 36000, NULL, NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `vtransaksi`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `vtransaksi` (
`id_transaksi` varchar(30)
,`status_cuci` tinyint(1)
,`id_user` varchar(10)
,`nama` varchar(35)
,`tgl_cuci` varchar(30)
,`id_jenis` varchar(10)
,`tipe_kendaraan` varchar(35)
,`total_bayar` bigint(10)
,`bayar` bigint(10)
,`kembali` bigint(10)
);

-- --------------------------------------------------------

--
-- Struktur untuk view `vtransaksi`
--
DROP TABLE IF EXISTS `vtransaksi`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vtransaksi`  AS SELECT `transaksi`.`id_transaksi` AS `id_transaksi`, `transaksi`.`status_cuci` AS `status_cuci`, `transaksi`.`id_user` AS `id_user`, `akun`.`nama` AS `nama`, `transaksi`.`tgl_cuci` AS `tgl_cuci`, `transaksi`.`id_jenis` AS `id_jenis`, `jenis_kendaraan`.`tipe_kendaraan` AS `tipe_kendaraan`, `transaksi`.`total_bayar` AS `total_bayar`, `transaksi`.`bayar` AS `bayar`, `transaksi`.`kembali` AS `kembali` FROM ((`transaksi` join `jenis_kendaraan` on(`transaksi`.`id_jenis` = `jenis_kendaraan`.`id_jenis`)) join `akun` on(`transaksi`.`id_user` = `akun`.`id_akun`)) ;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `akun`
--
ALTER TABLE `akun`
  ADD PRIMARY KEY (`id_akun`);

--
-- Indeks untuk tabel `jenis_kendaraan`
--
ALTER TABLE `jenis_kendaraan`
  ADD PRIMARY KEY (`id_jenis`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_user` (`id_user`) USING BTREE,
  ADD KEY `id_jenis` (`id_jenis`) USING BTREE;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `akun` (`id_akun`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`id_jenis`) REFERENCES `jenis_kendaraan` (`id_jenis`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
