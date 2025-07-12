//22100011025 Ismail Emirhan Sari
public class YLOgrencisi extends Ogrenci{
private static int tempID=100;
private int ogrID;
private String durumString;



public YLOgrencisi(int bolNo, String ogrAd, String ogrSoyad) {
	super(bolNo, ogrAd, ogrSoyad);
	this.ogrID=tempID++;
	this.durumString="Yuksek Lisans";
}


public static int getTempID() {
	return tempID;
}

public int getOgrID() {
	return ogrID;
}
public void setOgrID(int ogrID) {
	this.ogrID = ogrID;
}


public String getDurumString() {
	return durumString;
}
public void setDurumString(String durumString) {
	this.durumString = durumString;
}

}
