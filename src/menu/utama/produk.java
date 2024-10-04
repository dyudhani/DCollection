package menu.utama;

import java.util.HashMap;

public class produk extends HashMap<String, String>{
	private static final long serialVersionUID = 12872473L;
	public String namaProduk;
	public String totalMoney;
	public String hargaJual;
	public String stock;
	
	public static String KEYnamaProduk = "namaProduk";
	public static String KEYhargaJual = "hargaJual";
	public static String KEYtotalMoney = "totalMoney";
	public static String KEYstock = "stock";
	
	public produk(String namaProduk, String hargaJual){
		this.namaProduk = namaProduk;
		this.hargaJual = hargaJual;
	}
	
	@Override
	public String get(Object p){
		String produk = (String) p;
		if (KEYnamaProduk.equals(produk)) 
			return namaProduk;
		else if(KEYhargaJual.equals(produk))
			return hargaJual;
		else if (KEYstock.equals(produk)) 
			return stock;
		else if (KEYtotalMoney.equals(produk))
			return totalMoney;
		return null;
	}
}
