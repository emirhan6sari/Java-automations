//22100011025 Ismail Emirhan Sari
public class Ders {
	
private static int tempID=50;
private int bolNo;
private  int dersID;
private String dersAdString;
private int dersKredi;




public Ders(int bolNo, String dersAdString, int dersKredi) {
	super();
	this.bolNo = bolNo;
	this.dersAdString = dersAdString;
	this.dersKredi = dersKredi;
	this.dersID=++tempID;
}
public int getBolNo() {
	return bolNo;
}
public void setBolNo(int bolNo) {
	this.bolNo = bolNo;
}
public int getDersID() {
	return dersID;
}
/*public void setDersID(int dersID) {
	this.dersID = dersID;
}*/
public String getDersAdString() {
	return dersAdString;
}
public void setDersAdString(String dersAdString) {
	this.dersAdString = dersAdString;
}
public int getDersKredi() {
	return dersKredi;
}
public void setDersKredi(int dersKredi) {
	this.dersKredi = dersKredi;
}
	

}
