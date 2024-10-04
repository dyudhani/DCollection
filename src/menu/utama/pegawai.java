package menu.utama;

import java.util.HashMap;

import android.graphics.Point;

public class pegawai extends HashMap<String, String> {
	private static final long serialVersionUID = 12872473L;
	public String nama,email;
	
	public static String KEYnama = "strnama";
	public static String KEYemail = "stremail";
	
	public pegawai(String strnama, String stremail){
		this.nama = strnama;
		this.email = stremail;
	}
	
	@Override
	public String get(Object p){
		String pegawai = (String) p;
		if (KEYnama.equals(pegawai)) 
			return nama;
		else if(KEYemail.equals(pegawai))
			return email;
		return null;
	}
	
	
}
