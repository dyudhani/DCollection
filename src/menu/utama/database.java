package menu.utama; 

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {

	public database(Context context) {
		super(context, "dinaCasir", null, 1);
		// TODO Auto-generated constructor stub
	}

	public void createtable(SQLiteDatabase db){
		db.execSQL("create table if not exists user (" +
				"_id integer primary key autoincrement,"+
				"nama text,"+
				"email text,"+
				"password text)");
			
		db.execSQL("create table if not exists produk(" +
				"_id integer primary key autoincrement," +
				"namaProduk text,"+
				"hargaJual text,"+
				"hargaModal text,"+
				"stock text)");
		db.execSQL("create table if not exists temp(" +
				"_id integer primary key autoincrement," +
				"namaProduk text," +
				"hargaJual text," +
				"jumlah text)");
		db.execSQL("create table if not exists money(" +
				"_id integer primary key autoincrement," +
				"namaProduk text," +
				"jumlah text," +
				"totalMoney text)");
		db.execSQL("create table if not exists simpanduit(" +
				"_id integer primary key autoincrement," +
				"totalHarga text)");
		
	}
	
	public void generatedata(SQLiteDatabase db){
		ContentValues user = new ContentValues();
		user.put("nama", "admin");
		user.put("email", "admin");
		user.put("password", "123456");
		db.insert("user", "email", user);
		
		ContentValues produk = new ContentValues();
		produk.put("namaProduk", "Milkita");
		produk.put("hargaJual", "Rp. 50000");
		produk.put("hargaModal", "Rp. 45000");
		produk.put("stock", "5");
		db.insert("produk", "namaProduk", produk);
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
