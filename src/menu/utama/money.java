package menu.utama;
import java.util.HashMap;

public class money extends HashMap<String, String> {
	private static final long serialVersionUID=12872473L;
	
	public String namabarang;
	public String harga;
	public String jumlah;
	
	public static String KEYnamabarang="strnamabarang";
	public static String KEYharga="strharga";
	public static String KEYjumlah="strjumlah";
	
	public money(String strnamabarang,String strharga,String strjumlah){
		this.namabarang=strnamabarang;
		this.harga=strharga;
		this.jumlah=strjumlah;
	}
	
	public String get(Object k){
		String key=(String) k;
		if(KEYnamabarang.equals(key)){
			return namabarang;
		}
		else if(KEYharga.equals(key)){
			return harga;
		}
		else if(KEYjumlah.equals(key)){
			return jumlah;
		}
		return null;
	}
}
