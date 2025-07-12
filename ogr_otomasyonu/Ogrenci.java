//22100011025 Ismail Emirhan Sari
public class Ogrenci {
private int bolNo;
private String ogrAd;
private String ogrSoyad;
public int getBolNo() {
	return bolNo;
}
public void setBolNo(int bolNo) {
	this.bolNo = bolNo;
}
public String getOgrAd() {
	return ogrAd;
}
public void setOgrAd(String ogrAd) {
	this.ogrAd = ogrAd;
}
public String getOgrSoyad() {
	return ogrSoyad;
}
public void setOgrSoyad(String ogrSoyad) {
	this.ogrSoyad = ogrSoyad;
}

public Ogrenci(int bolNo, String ogrAd, String ogrSoyad) {
	this.bolNo = bolNo;
	this.ogrAd = ogrAd;
	this.ogrSoyad = ogrSoyad;
}



}
