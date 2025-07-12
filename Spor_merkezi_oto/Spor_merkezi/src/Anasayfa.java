import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class Anasayfa {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Kursiyer> kursiyerler = new ArrayList<>();
    ArrayList<Kurs> kurslar = new ArrayList<>();

    public Anasayfa() {
        kursiyerler = dosyadanOku();
        kurslar=kursDosyasiniOku();
    }
    
    public void KursEkle() {
        System.out.println("Yeni kurs bilgilerini giriniz:");

        // Kullanıcıdan kurs bilgilerini al
        System.out.print("Kurs ID: ");
        int yeniKursId = scanner.nextInt();
        scanner.nextLine(); // Dummy nextLine to consume the newline character
        System.out.print("Kurs Adı: ");
        String yeniKursAd = scanner.nextLine();

        // Aynı ID ile başka bir kursun olup olmadığını kontrol et
        boolean ayniIdVar = kurslar.stream().anyMatch(kurs -> kurs.getKursId()== yeniKursId);

        if (ayniIdVar) {
            System.out.println("Hata: Bu ID ile zaten bir kurs bulunmaktadır.");
        } else {
            // Yeni kursu oluştur ve kurslar ArrayList'ine ekle
            Kurs yeniKurs = new Kurs(yeniKursId, yeniKursAd);
            kurslar.add(yeniKurs);
            System.out.println("Yeni kurs başarıyla eklendi.");
        }
    }


    private ArrayList<Kursiyer> dosyadanOku() {
        ArrayList<Kursiyer> kursiyerlistesi = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileReader("kursiyer.txt"))) {
            Kursiyer kursiyer = null;

            while (scanner.hasNextLine()) {
                String lineString = scanner.nextLine();
                if (lineString.startsWith("*")) {
                    if (kursiyer != null) {
                        kursiyerlistesi.add(kursiyer);
                    }

                    String[] veri = lineString.substring(1).split("\\+"); // Ayracı düzelt
                    String adsoyad = veri[1].trim();
                    int Id = Integer.parseInt(veri[0].trim());
                    int yas = Integer.parseInt(veri[2].trim());

                    kursiyer = new Kursiyer(Id, adsoyad, yas);
                } else if (lineString.startsWith("%")) {
                    if (kursiyer != null) {
                        String[] kursVeri = lineString.substring(1).split("\\+"); // Ayracı düzelt
                        int kursId = Integer.parseInt(kursVeri[0].trim());
                        Kurs kurs = new Kurs(kursId, kursVeri[1].trim());
                        kursiyer.alinanKurslar.add(kurs);
                    }
                }
            }

            // Son kursiyeri listeye eklemek için kontrol eklendi
            if (kursiyer != null) {
                kursiyerlistesi.add(kursiyer);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı. Yeni bir dosya oluşturuluyor.");
        }
        return kursiyerlistesi;
    }
    private ArrayList<Kurs> kursDosyasiniOku() {
        ArrayList<Kurs> kursListesi = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileReader("kurs.txt"))) { // Burada dosya adını "kurs.txt" olarak değiştirdim
            while (scanner.hasNextLine()) {
                String lineString = scanner.nextLine();
                String[] kursVeri = lineString.split("\\+");
                int kursId = Integer.parseInt(kursVeri[0].trim());
                Kurs kurs = new Kurs(kursId, kursVeri[1].trim());
                kursListesi.add(kurs);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı. Yeni bir dosya oluşturuluyor.");
        }
        return kursListesi;
    }
    public void KursiyerListele() {
        System.out.println("Tüm Kursiyerler");

        for (Kursiyer kursiyer : kursiyerler) {
            System.out.println(kursiyer.getKursiyerId() + " " + kursiyer.getKursiyerAdSoyad() + " " + kursiyer.getKursiyerYas());
        }
    }
    public void KursListele() {
        System.out.printf(/*"%-10s%-20s%n",*/ "Kurs Id\t"+"Kurs Adı\n");

        for (Kurs kurs : kurslar) {
            System.out.printf(/*"%-10d%-20s%n", */kurs.getKursId()+"\t"+ kurs.getKursAd()+"\n");
        }
    }
    public void KursiyerAyrintiliListele() {
        System.out.println("Tüm Kursiyerler ve Aldıkları Kurslar");

        for (Kursiyer kursiyer : kursiyerler) {
            System.out.println(kursiyer.getKursiyerId() + " " + kursiyer.getKursiyerAdSoyad() + " " + kursiyer.getKursiyerYas());

            for (Kurs kurs : kursiyer.alinanKurslar) {
                System.out.println(" " + kurs.getKursId() + " " + kurs.getKursAd());
            }
        }
    }
    public void KursAra() {//kurs aramasi buyuk kucuk harf duyarliligi yoktur bunun icin equalsIgnoreCase methodu kullanmaliyiz.
        scanner.nextLine();
        System.out.print("Aranacak Kurs Adı: ");
        String arananKursAdi = scanner.nextLine().trim();//trim methodunu bastaki ve sondaki bosluklari kaldirmak icin kullandim:
        int tik =0 ;
        for (Kurs kurs : kurslar) {
            if (kurs.getKursAd().equals(arananKursAdi)) {
                System.out.println("Aranan Kurs Bilgileri:");
                System.out.printf("Kurs Id: %d\t\tKurs Adı: %s\n", kurs.getKursId(), kurs.getKursAd());
                tik = 1;
                break;
            }
        }

        if (tik!=1) {//tik==0 şeklinde de condition (sart) yazabilirdik.
            System.out.println("Aranan kurs bulunamadı.");
        }
    }
    
    
    public void KursSil() {
        scanner.nextLine(); // Dummy nextLine to consume the newline character
        Anasayfa anasayfa = new Anasayfa();
        System.out.print("Silinecek Kurs Adı: ");
        String silinecekKursAdi = scanner.nextLine().trim();
        int kursArama = 0;

        for (Kurs kurs : kurslar) {
            int kursAlanVarMiKontrol = 0;

            if (kurs.getKursAd().equalsIgnoreCase(silinecekKursAdi)) {
                for (Kursiyer kursiyer : anasayfa.kursiyerler) {
                    for (Kurs alinanKurs : kursiyer.alinanKurslar) {
                        if (alinanKurs.getKursAd().equalsIgnoreCase(silinecekKursAdi)) {
                            kursAlanVarMiKontrol = 1;
                            break;
                        }
                    }

                    if (kursAlanVarMiKontrol == 1) {
                        break;
                    }
                }

                if (kursAlanVarMiKontrol == 0) {
                    kurslar.remove(kurs); // Kursu listeden kaldır
                    System.out.println("Kurs başarıyla silindi.");
                } else {
                    System.out.println("Bu kursu alan kursiyer bulunduğu için silme işlemi yapılamaz.");
                }

                kursArama = 1;
                break;
            }
        }

        if (kursArama == 0) {
            System.out.println("Silinecek kurs bulunamadı.");
        }
    }
    
    
    
    public void KursiyerEkle() {
    	Anasayfa anasayfa=new Anasayfa();
        System.out.println("Yeni kursiyer bilgilerini giriniz:");

        // Kullanıcıdan kursiyer bilgilerini al
        System.out.print("Kursiyer ID: ");
        int yeniKursiyerId = scanner.nextInt();
        scanner.nextLine(); // Dummy nextLine to consume the newline character
        System.out.print("Ad Soyad: ");
        String yeniKursiyerAdSoyad = scanner.nextLine();
        System.out.print("Yaş: ");
        int yeniKursiyerYas = scanner.nextInt();

        // Aynı ID ile başka bir kursiyer olup olmadığını kontrol et
        boolean ayniIdVar = kursiyerler.stream().anyMatch(kursiyer -> kursiyer.getKursiyerId() == yeniKursiyerId);

        if (ayniIdVar) {
            System.out.println("Hata: Bu ID ile zaten bir kursiyer bulunmaktadır.");
        } else {
            // Yeni kursiyeri oluştur
            Kursiyer yeniKursiyer = new Kursiyer(yeniKursiyerId, yeniKursiyerAdSoyad, yeniKursiyerYas);

            // Kurslara kayıt işlemleri
            //System.out.println("Kaydolacağı kursları giriniz ");
            while (true) {
            	int kontrol=0;
            	System.out.println("kurs girmek icin 1'e kurs girmeyecekseniz herhangi bir sayiya basiniz:");
            	kontrol=scanner.nextInt();
            	if (kontrol==1) {
            		anasayfa.KursListele();
            		System.out.print("girecegi Kurs ID : ");
                    scanner.nextLine();
                    int kursIdIn = scanner.nextInt();
                    String kursadiString;
                    scanner.nextLine();
                    System.out.println("girecegi Kurs Adini yaziniz:");
                    kursadiString=scanner.nextLine();
                    
                    Kurs kurs=new Kurs(kursIdIn, kursadiString);
                    yeniKursiyer.alinanKurslar.add(kurs);                       
				}
            	else {
					break;
				}
                /**/
            }

            // Yeni kursiyeri kursiyerler ArrayList'ine ekliyorum.
            kursiyerler.add(yeniKursiyer);
            System.out.println("Yeni kursiyer başarıyla eklendi.");
        }
    }

    
    
    public void KursiyerAra() {
        scanner.nextLine(); // Dummy nextLine to consume the newline character
        Anasayfa anasayfa=new Anasayfa();
        anasayfa.KursiyerListele();
        System.out.print("Arayacaginiz Kursiyer Ad-Soyadi giriniz: ");
        String arananKursiyerAdSoyad = scanner.nextLine().trim();

        int kursiyerkontrol = 0;

        for (Kursiyer kursiyer : kursiyerler) {
            if (kursiyer.getKursiyerAdSoyad().equals(arananKursiyerAdSoyad)) {
                kursiyerkontrol = 1;

                System.out.println("Aranan Kursiyer Bilgileri:");
                System.out.printf("Kursiyer ID: %d \tAd-Soyad: %s \tYaş: %d\t\n", kursiyer.getKursiyerId(), kursiyer.getKursiyerAdSoyad(), kursiyer.getKursiyerYas());
                
                if (!kursiyer.alinanKurslar.isEmpty()) {
                    System.out.println("Alınan Kurs&Kurslar:");
                    for (Kurs kurs : kursiyer.alinanKurslar) {
                        System.out.printf("  Kurs ID: %d \t Kurs Adı: %s\t\n", kurs .getKursId(), kurs .getKursAd());
                    }
                } else {
                    System.out.println("Kurs alınmamış.");
                }
                
                break;
            }
        }

        if (kursiyerkontrol==0) {
            System.out.println("Aranan kursiyer bulunamadı.");
        }
    }

   
    public void KursiyerSil() {
    	Anasayfa anasayfa =new Anasayfa();
    	anasayfa.KursiyerListele();
        scanner.nextLine(); // Dummy nextLine to consume the newline character

        System.out.print("Silinecek Kursiyer ID: ");
        int silinecekKursiyerId = scanner.nextInt();
        
         int delKursiyer=0;

        for (int i = 0; i < kursiyerler.size(); i++) {
            Kursiyer kursiyer = kursiyerler.get(i);

            if (kursiyer.getKursiyerId() == silinecekKursiyerId) {
            	delKursiyer = 1;
                kursiyerler.remove(i);
                System.out.println("Kursiyer başarıyla silindi.");      
                break;
            }
        }
        if (delKursiyer==0) {
            System.out.println("Silinecek kursiyer bulunamadı.");
        }
    }
    
    public void Hesaplama() {
    	scanner.nextLine();
    	System.out.println("hesabini istediginiz kisinin Idsini giriniz:");
    	int kursiyerId=scanner.nextInt();
        Kursiyer kursiyer = null;

        for (Kursiyer kursiyer2 : kursiyerler) {
            if (kursiyer2.getKursiyerId() == kursiyerId) {
                kursiyer = kursiyer2;
                break;
            }
        }

        if (kursiyer == null) {
            System.out.println("Girilen Idye sahip kimse yoktur:\n");
            return;
        }

        double toplamUcret = kursiyer.BorcHesapla();
        System.out.printf("Kursiyerin ödeyeceği toplam ücret: %.2f TL\t\n", toplamUcret);
    }

    
    public void dosyayaYaz() {
        try (PrintWriter kursiyerWriter = new PrintWriter(new FileWriter("kursiyer.txt"));
             PrintWriter kursWriter = new PrintWriter(new FileWriter("kurs.txt"))) 
        {
            for (Kursiyer kisiKursiyer : kursiyerler) 
            {
                kursiyerWriter.println("*" + kisiKursiyer.getKursiyerId() + "+"
                		 +kisiKursiyer.getKursiyerAdSoyad() + "+"
                		 +kisiKursiyer.getKursiyerYas());
                for (Kurs kisininkursu : kisiKursiyer.getAlinanKurslar()) 
                {
                    kursiyerWriter.println("%" + kisininkursu.getKursId() + "+" + kisininkursu.getKursAd());
                }
            }
            for (Kurs kurs : kurslar) 
            {
                kursWriter.println(kurs.getKursId() + "+" + kurs.getKursAd());
            }

            System.out.println("Dosyalara çıkış başarıyla yapıldı.");
        } 
        catch (IOException e)
        {
            System.out.println("Dosyaya yazma işlemi sırasında bir hata oluştu: " + e.getMessage());
        }
    }

    
    
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		int kontrol = 1;
		Anasayfa anasayfa = new Anasayfa();
		anasayfa.kursiyerler=anasayfa.dosyadanOku();
		anasayfa.kurslar=anasayfa.kursDosyasiniOku();
        while (kontrol > 0 && kontrol < 11) {
            System.out.println("yapmak istediginiz islemi seciniz:\n"
                    + "1-kurs ekle\n"//
                    + "2-kurs listele\n"//
                    + "3-kurs ara\n"//
                    + "4-kurs sil\n"//
                    + "5-kursiyer ekle\n"//
                    + "6-kursiyer ara\n"//
                    + "7-kursiyer sil\n"
                    + "8-kursiyerleri listele\n"//
                    + "9-kursiyerleri ayrintili listele\n"//
                    + "10-tutar hesapla\n"
                    + "cikis icin farkli bir sayiya basiniz:\n");
            kontrol = scanner.nextInt();

            if (kontrol == 1) {
            	anasayfa.KursEkle();
            } else if (kontrol == 2) {
            	if (anasayfa.kurslar.isEmpty()) {
                    System.out.println("Kurs bulunmamaktadır.");
                } else {
                    anasayfa.KursListele();
                }
            } else if (kontrol == 3) {
                anasayfa.KursAra();
            } else if (kontrol == 4) {
                anasayfa.KursSil();
            } else if (kontrol == 5) {
            	anasayfa.KursiyerEkle();
            } else if (kontrol == 6) {
                anasayfa.KursiyerAra();
            } else if (kontrol == 7) {
                anasayfa.KursiyerSil();
            } else if (kontrol == 8) {
                if (anasayfa.kursiyerler.isEmpty()) {
                    System.out.println("Kursiyer bulunmamaktadır.");
                } else {
                    anasayfa.KursiyerListele();
                }
            } else if (kontrol == 9) {
            	if (anasayfa.kursiyerler.isEmpty()) {
                    System.out.println("Kursiyer bulunmamaktadır.");
                } else {
                    anasayfa.KursiyerAyrintiliListele();
                }
            } else if (kontrol == 10) {
                anasayfa.Hesaplama();
            } else {
                System.out.println("program kapatiliyor...");
                anasayfa.dosyayaYaz();
            }
        }
        scanner.close();
        

		
	}

}
