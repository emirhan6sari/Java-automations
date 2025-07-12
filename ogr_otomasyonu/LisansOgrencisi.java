//22100011025 Ismail Emirhan Sari 
public class LisansOgrencisi extends Ogrenci {
	
private static int tempID=0;

private int ogrId; 
private int ogrSinif;
private String durum;
public LisansOgrencisi(int bolNo, String ogrAd, String ogrSoyad, int ogrSinif) {
	super(bolNo, ogrAd, ogrSoyad);
	this.ogrSinif = ogrSinif;
	durum="Lisans";
	this.ogrId=++tempID;
}
public int getOgrSinif() {
	return ogrSinif;
}
public void setOgrSinif(int ogrSinif) {
	this.ogrSinif = ogrSinif;
}
public int getOgrId() {
	return ogrId;
}
public void setOgrId(int ogrId) {
	this.ogrId = ogrId;
}


}
