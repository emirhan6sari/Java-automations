//22100011025 Ismail Emirhan Sari
import java.util.Iterator;
import java.util.Scanner;

import javax.imageio.plugins.tiff.ExifGPSTagSet;

public class Anasayfa {
	private static void yazdirOgrenciler(String baslik, LisansOgrencisi[] ogrenciListesi, int index) {
	    System.out.println(baslik);
	    for (int i = 0; i < index; i++) {
	        System.out.println(ogrenciListesi[i].getOgrId() + " " + ogrenciListesi[i].getOgrAd() + " " + ogrenciListesi[i].getOgrSoyad());
	    }
	    System.out.println();
	}
	   
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int bolumDegiskeni = 0;
        int yuksekL = 0;
        int lisans = 0;
        int ders = 0;

        System.out.println("Kaç bölüm olacaktır?");
        bolumDegiskeni = scanner.nextInt();
        Bolum Bdizi[] = new Bolum[bolumDegiskeni];

        for (int i = 0; i < bolumDegiskeni; i++) {
            System.out.println("Bölüm " + (i + 1) + " Bilgileri:");
            System.out.println("Bölüm no giriniz:");
            int bolNo = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Bölüm adı giriniz:");
            String bolumAdString = scanner.nextLine(); // nextLine düzeltilmiş
            System.out.println("Kaç yüksek lisans öğrencisi olacaktır?");
            yuksekL = scanner.nextInt();
            System.out.println("Kaç lisans öğrencisi olacaktır?");
            lisans = scanner.nextInt();
            System.out.println("Kaç ders olacaktır?");
            ders = scanner.nextInt();

            Bdizi[i] = new Bolum(bolNo, bolumAdString, yuksekL + lisans, ders);

            for (int j = 0; j < lisans; j++) {
                System.out.println("Lisans " + (j + 1) + " Bilgileri:");
                System.out.println((j + 1) + ". Lisans Öğrencisinin adını giriniz:");
                String ogrAdString = scanner.next();
                System.out.println((j + 1) + ". Lisans Öğrencisinin soyadını giriniz:");
                String ogrSoyadString = scanner.next();
                System.out.println((j + 1) + ". Lisans öğrencisinin sınıfını giriniz:");
                int ogrSinif = scanner.nextInt();
                LisansOgrencisi lisansnesne = new LisansOgrencisi(bolNo, ogrAdString, ogrSoyadString, ogrSinif);
                Bdizi[i].Ogrenciler[j] = lisansnesne;
            }

            int a = 0;
            for (int j = lisans; j < yuksekL + lisans; j++) {
                a = ++a;
                System.out.println("Yüksek lisans " + (a) + " Bilgileri:");
                System.out.println((a) + ". Yüksek Lisans Öğrencisinin adını giriniz:");
                String ogrAdString = scanner.next();
                System.out.println((a) + ". Yüksek Lisans Öğrencisinin soyadını giriniz:");
                String ogrSoyadString = scanner.next();
                YLOgrencisi yOgrencisi = new YLOgrencisi(bolNo, ogrAdString, ogrSoyadString);
                Bdizi[i].Ogrenciler[j] = yOgrencisi;
            }

            System.out.println("Ders bilgileri için:");
            for (int j = 0; j < ders; j++) {
                System.out.println((j + 1) + ". Ders adını giriniz:");
                String dersadiString = scanner.next();
                System.out.println((j + 1) + ". Ders kredisini giriniz:");
                int derskredi = scanner.nextInt();
                Ders dersnesneDers = new Ders(bolNo, dersadiString, derskredi);
                Bdizi[i].dersler[j] = dersnesneDers;
            }
        }

        int kontrol = 0;
        int x = 0;
        LisansOgrencisi[] lisansSinif1 = new LisansOgrencisi[lisans + bolumDegiskeni+50];
        LisansOgrencisi[] lisansSinif2 = new LisansOgrencisi[lisans +bolumDegiskeni+50];
        LisansOgrencisi[] lisansSinif3 = new LisansOgrencisi[lisans + bolumDegiskeni+50];
        LisansOgrencisi[] lisansSinif4 = new LisansOgrencisi[lisans +bolumDegiskeni+50];
        YLOgrencisi[] yuksekLisansOgrencileri = new YLOgrencisi[yuksekL + bolumDegiskeni+50];
        
        while (2 > 1) {
            System.out.println("1-Tüm bilgileri listele\n2-Lisans listele\n3-Yüksek lisans listele\n4-Dersleri krediye göre listele\n5-Arama\n6-Çıkış");
            System.out.println("Yapmak istediğiniz işlemi seçiniz:");
            kontrol = scanner.nextInt();

            if (kontrol == 1) {
            	System.out.println("Tüm Bölümlerin Bilgileri:");
            	for (int i = 0; i < bolumDegiskeni; i++) {
            	    System.out.println("Bölüm No: " + Bdizi[i].getBolNo() + " Bölüm Ad: " + Bdizi[i].getBolADString());

            	    System.out.println("Lisans Öğrencileri:");
            	    for (int j = 0; j < Bdizi[i].Ogrenciler.length; j++) {
            	        if (Bdizi[i].Ogrenciler[j] instanceof LisansOgrencisi) {
            	            LisansOgrencisi lisansOgrencisi = (LisansOgrencisi) Bdizi[i].Ogrenciler[j];
            	            System.out.println((j + 1) + ". Öğrenci: " + lisansOgrencisi.getOgrId() + " " + lisansOgrencisi.getOgrAd() + " " + lisansOgrencisi.getOgrSoyad() + " " + lisansOgrencisi.getOgrSinif());
            	        }
            	    }

            	    System.out.println("Yüksek Lisans Öğrencileri:");
            	    for (int j = 0; j < Bdizi[i].Ogrenciler.length; j++) {
            	        if (Bdizi[i].Ogrenciler[j] instanceof YLOgrencisi) {
            	            YLOgrencisi ylOgrencisi = (YLOgrencisi) Bdizi[i].Ogrenciler[j];
            	            System.out.println((j + 1) + ". Öğrenci: " + ylOgrencisi.getOgrID() + " " + ylOgrencisi.getOgrAd() + " " + ylOgrencisi.getOgrSoyad());
            	        }
            	    }

            	    System.out.println("Dersler:");
            	    for (int j = 0; j < Bdizi[i].dersler.length; j++) {
            	        Ders ders1 = Bdizi[i].dersler[j];
            	        System.out.println((j + 1) + ". Ders: " + ders1.getDersID() + " " + ders1.getDersAdString() + " " + ders1.getDersKredi());
            	    }

            	    System.out.println();
                }
            } else if (kontrol == 2) {
            	int index1 = 0, index2 = 0, index3 = 0, index4 = 0;
                for (int i = 0; i < bolumDegiskeni; i++) {
                    for (int j = 0; j < Bdizi[i].Ogrenciler.length; j++) {
                        if (Bdizi[i].Ogrenciler[j] instanceof LisansOgrencisi) {
                            LisansOgrencisi lisansOgrencisi = (LisansOgrencisi) Bdizi[i].Ogrenciler[j];
                            switch (lisansOgrencisi.getOgrSinif()) {
                                case 1:
                                    lisansSinif1[index1++] = lisansOgrencisi;
                                    break;
                                case 2:
                                    lisansSinif2[index2++] = lisansOgrencisi;
                                    break;
                                case 3:
                                    lisansSinif3[index3++] = lisansOgrencisi;
                                    break;
                                case 4:
                                    lisansSinif4[index4++] = lisansOgrencisi;
                                    break;
                                default:
                                    System.out.println("Hatalı sınıf bilgisi: " + lisansOgrencisi.getOgrSinif());
                            }
                        }
                    }
                }
                yazdirOgrenciler("1. Sınıf Lisans Öğrencileri:", lisansSinif1, index1);
                yazdirOgrenciler("2. Sınıf Lisans Öğrencileri:", lisansSinif2, index2);
                yazdirOgrenciler("3. Sınıf Lisans Öğrencileri:", lisansSinif3, index3);
                yazdirOgrenciler("4. Sınıf Lisans Öğrencileri:", lisansSinif4, index4);
                
            } else if (kontrol == 3) {
            	System.out.println("tum yuksek lisans ogrencileri:");
            	 int indexYL = 0;
            	    for (int i = 0; i < bolumDegiskeni; i++) {
            	        for (int j = 0; j < Bdizi[i].Ogrenciler.length; j++) {
            	            if (Bdizi[i].Ogrenciler[j] instanceof YLOgrencisi) {
            	                YLOgrencisi yLisansOgrencisi = (YLOgrencisi) Bdizi[i].Ogrenciler[j];
            	                yuksekLisansOgrencileri[indexYL++] = yLisansOgrencisi;
            	            }
            	        }
            	    }
            	    for (int i = 0; i < indexYL; i++) {
            	        System.out.println((i + 1) + ". Yüksek Lisans Öğrencisi: " + yuksekLisansOgrencileri[i].getOgrID() + " " + yuksekLisansOgrencileri[i].getOgrAd() + " " + yuksekLisansOgrencileri[i].getOgrSoyad());
            	    }
            	
            } 
            else if (kontrol == 4) {
            	System.out.println("Tüm Bölümlerdeki Dersler Kredi Bilgilerine Göre Sıralı Liste:");
            	
            	int b=0;
            	for(int i=0;i<bolumDegiskeni;i++) {
            		
            		for(int j=0;j<Bdizi[i].dersler.length;j++) {
            		b=b+1;	
            		}
            	}
            	Ders[] tumDersler = new Ders[b];
            	int c=0;
                  for(int i=0;i<bolumDegiskeni;i++) {
            		
            		for(int j=0;j<Bdizi[i].dersler.length;j++) {
            		tumDersler[c]	=Bdizi[i].dersler[j];
            				c=c+1;
            		}
            	}
                  for (int i = 0; i < b - 1; i++) {
                	    for (int j = 0; j < b - i - 1; j++) {
                	        if (tumDersler[j].getDersKredi() > tumDersler[j + 1].getDersKredi()) {
                	            
                	            Ders temp = tumDersler[j];
                	            tumDersler[j] = tumDersler[j + 1];
                	            tumDersler[j + 1] = temp;
                	        }
                	    }
                	}    
                	for (int i = 0; i < b; i++) {
                	    System.out.println(tumDersler[i].getDersKredi() + " kredili ders: " + tumDersler[i].getDersID() + " " + tumDersler[i].getDersAdString());
                	}
            }
            
            	   
            else if (kontrol == 5) {
            	
            	System.out.print("Aranacak kelimeyi girin: ");
            	String kelimeString = scanner.next();
            	int temp = 1;

            	for (Bolum bolum : Bdizi) {
            	    for (Ogrenci ogrenci : bolum.Ogrenciler) {
            	        if (ogrenci.getOgrSoyad().equals(kelimeString) || ogrenci.getOgrAd().equals(kelimeString)) {
            	            System.out.println("Bolum adi: " + bolum.getBolADString() + " Bolum numarasi: " + ogrenci.getBolNo()
            	                    + " Ogrencinin adi: " + ogrenci.getOgrAd() + " Ogrenci soyadi: " + ogrenci.getOgrSoyad());
            	            temp = 0;
            	        }
            	    }

            	    for (Ders ders1 : bolum.dersler) {
            	        if (ders1.getDersAdString().equals(kelimeString)) {
            	            System.out.println(
            	                    "Bolum ismi: " + bolum.getBolADString() + " \nDers ismi: " + ders1.getDersAdString() + "\nDers kredisi: "
            	                            + ders1.getDersKredi());
            	            temp = 0;
            	        }
            	    }
            	}

            	if (temp == 1) {
            	    System.out.println("boyle birsey bulunmadi");
            	}
            } 
            else {
                x = 1;
            }

            if (x == 1) {
                System.out.println("Programdan çıkılıyor.");
                scanner.close();
                System.exit(0);
                break;
            }
        }
    }
}