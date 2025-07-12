
import java.util.ArrayList;
public class Kursiyer implements Hesaplama {
	
private int kursiyerId;
private String kursiyerAdSoyad;
private int kursiyerYas;
public ArrayList<Kurs> alinanKurslar=new ArrayList<>();


public Kursiyer(int kursiyerId, String kursiyerAdSoyad, int kursiyerYas) {
	super();
	this.kursiyerId = kursiyerId;
	this.kursiyerAdSoyad = kursiyerAdSoyad;
	this.kursiyerYas = kursiyerYas;
	//this.kurs = kurs;
}


public int getKursiyerId() {
	return kursiyerId;
}


public void setKursiyerId(int kursiyerId) {
	this.kursiyerId = kursiyerId;
}


public String getKursiyerAdSoyad() {
	return kursiyerAdSoyad;
}


public void setKursiyerAdSoyad(String kursiyerAdSoyad) {
	this.kursiyerAdSoyad = kursiyerAdSoyad;
}


public int getKursiyerYas() {
	return kursiyerYas;
}


public void setKursiyerYas(int kursiyerYas) {
	this.kursiyerYas = kursiyerYas;
}


public ArrayList<Kurs> getAlinanKurslar() {
	return alinanKurslar;
}


public void setAlinanKurslar(ArrayList<Kurs> alinanKurslar) {
	this.alinanKurslar = alinanKurslar;
}

public double BorcHesapla() {
    double haftalikUcret = 500.0;
    
	int kursSayisi=alinanKurslar.size();
    double toplamUcret = haftalikUcret * 4 * kursSayisi;

    // Kampanya kontrolleri
    if (kursSayisi == 2) {
        // Kampanya 1: İkinci kurs %20 indirimli
        toplamUcret -= haftalikUcret * 0.20;
        System.out.println("Kampanya 1: İkinci kurs %20 indirimli.");
    } else if (kursSayisi == 3) {
        // Kampanya 2: Üçüncü kurs %25 indirimli
        toplamUcret -= haftalikUcret * 0.25;
        System.out.println("Kampanya 2: Üçüncü kurs %25 indirimli.");
    } else if (kursSayisi > 3) {
        // Kampanya 3: Her kurs %10 indirimli
        toplamUcret -= haftalikUcret * 0.10 * kursSayisi;
        System.out.println("Kampanya 3: Her kurs %10 indirimli.");
    } else {
        System.out.println("Tek kurs alan kursiyer için kampanya bulunmamaktadır.");
    }

    return toplamUcret;
}
}
