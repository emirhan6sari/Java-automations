//22100011025 Ismail Emirhan Sari
public class Bolum {

	
	
	private int bolNo;
	private String bolADString;
	public Ogrenci Ogrenciler[];
	public Ders dersler[];
	public Bolum(int bolNo, String bolADString,int ogrenciSayisi,int derssayisi) {
		super();
		this.bolNo = bolNo;
		this.bolADString = bolADString;
		this.Ogrenciler = new Ogrenci[ogrenciSayisi];
		this.dersler=new Ders[derssayisi];
	}
	
	
	
	
	
	public int getBolNo() {
		return bolNo;
	}
	public void setBolNo(int bolNo) {
		this.bolNo = bolNo;
	}
	public String getBolADString() {
		return bolADString;
	}
	public void setBolADString(String bolADString) {
		this.bolADString = bolADString;
	}
	
}
