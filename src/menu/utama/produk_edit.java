package menu.utama;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class produk_edit extends Activity {

	TextView tpback;
	EditText enproduk,ehjual,ehmodal,estock;
	String produkku;
	
	SQLiteDatabase db;
	Cursor cursor;
	database dbproduk;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.produk_edit);
		Bundle eproduk = getIntent().getExtras();
		
		enproduk = (EditText) findViewById(R.id.enproduk);
		ehjual = (EditText) findViewById(R.id.ehjual);
		ehmodal = (EditText) findViewById(R.id.ehmodal);
		estock = (EditText) findViewById(R.id.estock);
		tpback = (TextView) findViewById(R.id.tpback);
		
		produkku = eproduk.getString("kirim");
		
		dbproduk = new database(this);
		db = dbproduk.getWritableDatabase();
		dbproduk.createtable(db);
		
		cursor = db.rawQuery("select * from produk where namaProduk= '"
				+produkku+"' "	, null);
		
		tpback.setOnClickListener(
				new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						finish();
					}
				});
		
		while (cursor.moveToNext()) {
			enproduk.setText(cursor.getString(1));
			ehjual.setText(cursor.getString(2));
			ehmodal.setText(cursor.getString(3));
			estock.setText(cursor.getString(4));
		}
		
		
	}

}
