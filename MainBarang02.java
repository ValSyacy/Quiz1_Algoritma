import java.util.ArrayList;
import java.util.Scanner;

public class MainBarang02 {
    public TransaksiBarang02 transaksiBarang02;
    public MainBarang02() {
        this.transaksiBarang02 = new TransaksiBarang02();
        transaksiBarang02.barangs.add(new Barang02("K01", "Armor", 5000, 10));
        transaksiBarang02.barangs.add(new Barang02("K02", "Pistol", 12000, 5));
        transaksiBarang02.barangs.add(new Barang02("K03", "Rifle", 15000, 13));
        transaksiBarang02.barangs.add(new Barang02("K04", "Perisai", 1000, 13));
        transaksiBarang02.barangs.add(new Barang02("K05", "Pedang", 13000, 15));
        transaksiBarang02.barangs.add(new Barang02("K06", "HealingKit", 5000, 65));
        transaksiBarang02.barangs.add(new Barang02("K07", "PistolAmmo", 3000, 85));
        transaksiBarang02.barangs.add(new Barang02("K08", "RifleAmmo", 5000, 65));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MainBarang02 mainBarang = new MainBarang02();
        boolean belanjaLagi = true;

        while (belanjaLagi) {
            System.out.println("===============================");
            System.out.println("TOKO ALFITROH SYACY NOVALINO 02");
            System.out.println("===============================");
            System.out.println("1. Tampilkan Barang");
            System.out.println("2. Beli");
            System.out.println("3. Tampilkan pembelian");
            System.out.println("4. Keluar");
            System.out.print("Pilih [1-4]: ");

            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    mainBarang.transaksiBarang02.tampilkanBarang();
                    break;
                case "2":
                    do {
                        mainBarang.transaksiBarang02.tampilkanBarang();
                        System.out.print("Masukkan kode barang: ");
                        String kodeBarang = scanner.nextLine();
                        boolean berhasil = mainBarang.transaksiBarang02.lakukanPembelian(kodeBarang);
                        if (berhasil) {
                            if (mainBarang.transaksiBarang02.barangs.isEmpty()) {
                                System.out.println("Maaf, semua barang sudah habis. Terima kasih!");
                                belanjaLagi = false;
                                break;
                            }
                            System.out.print("Apakah akan belanja lagi (Y/N)? ");
                            String belanjaLagiInput = scanner.nextLine();
                            if (!belanjaLagiInput.equalsIgnoreCase("Y")) {
                                break;  
                            }
                        } else {
                            System.out.println("Kode barang tidak valid. Silakan coba lagi.");
                            break;  
                        }
                    } while (true);
                    break;
                case "3":
                    mainBarang.transaksiBarang02.tampilkanPembelian();
                    break;
                case "4":
                    belanjaLagi = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
        }
    }
}

class Barang02 {
    String kode;
    String nama;
    int harga;
    int stok;

    public Barang02(String kode, String nama, int harga, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }
}

class TransaksiBarang02 {
    ArrayList<Barang02> barangs = new ArrayList<>();
    ArrayList<Barang02> pembelian = new ArrayList<>();

    void tampilkanBarang() {
        System.out.println("===============================");
        System.out.println("\tDaftar Barang");
        System.out.println("===============================");
        System.out.println("Kode   Nama       Harga   Stok");
        for (Barang02 barang : barangs) {
            System.out.printf("%-6s %-10s %-6d %-4d%n", barang.kode, barang.nama, barang.harga, barang.stok);
        }
    }

    void tampilkanPembelian() {
        System.out.println("===============================");
        System.out.println("\tList Belanjaan");
        System.out.println("===============================");
        System.out.println("Kode         Nama          Harga         Stok");
        for (Barang02 barang : pembelian) {
            System.out.printf("%-12s %-14s %-12d %-8d%n", barang.kode, barang.nama, barang.harga, barang.stok);
        }
    }

    boolean lakukanPembelian(String kodeBarang) {
        boolean found = false;
        for (Barang02 barang : barangs) {
            if (barang.kode.equals(kodeBarang)) {
                found = true;
                if (barang.stok > 0) {
                    System.out.printf("Barang %s berhasil ditambahkan ke dalam pembelian.%n", kodeBarang);
                    pembelian.add(barang);
                    barang.stok--; 
                } else {
                    System.out.println("Maaf, barang sudah habis. Datang lagi nanti :)");
                }
                break;
            }
        }
        if (!found) {
            System.out.printf("Barang %s tidak ditemukan.%n", kodeBarang);
        }
        return found;
    }
}


