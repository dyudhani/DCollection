package menu.utama;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class produk_tambah extends Activity {

	EditText nproduk,hmodal,hjual;
	TextView tpback;
	Button sproduk;
	
	SQLiteDatabase db;
	Cursor cursor;
	database dbproduk;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.produk_tambah);
		
		nproduk = (EditText) findViewById(R.id.nproduk);
		hmodal = (EditText) findViewById(R.id.hmodal);
		hjual = (EditText) findViewById(R.id.hjual);
		tpback = (TextView) findViewById(R.id.tpback);
		sproduk = (Button) findViewById(R.id.sproduk);
		
		dbproduk = new database(this);
		db = dbproduk.getWritableDatabase();
		dbproduk.createtable(db);
		
		cursor = db.rawQuery("select * from produk", null);
		if(!cursor.moveToNext())
			dbproduk.generatedata(db);
		
		tpback.setOnClickListener(
				new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						finish();
					}
				});
		
		sproduk.setOnClickListener(
				new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						db.execSQL("insert into produk(namaProduk,hargaJual,hargaModal) " +
								"VALUES('"+
								nproduk.getText()+"', '"+ hjual.getText()+"','"+ hmodal.getText()
								+"')");
						Intent i = new Intent(produk_tambah.this, produk_home.class);
						startActivity(i);
					}
				});
	} 
}
