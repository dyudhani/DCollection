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

public class pegawai_tambah extends Activity {

	TextView kback;
	EditText pnama,pemail,ppassword;
	Button daftarp;
	
	SQLiteDatabase db;
	Cursor cursor;
	database dbpegawai;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pegawai_tambah);
	
		kback = (TextView) findViewById(R.id.kback);
		daftarp = (Button)findViewById(R.id.daftarp);
		pnama = (EditText) findViewById(R.id.pnama);
		pemail = (EditText) findViewById(R.id.pemail);
		ppassword = (EditText) findViewById(R.id.ppassword);
		
		dbpegawai = new database(this);
		db = dbpegawai.getWritableDatabase();
		dbpegawai.createtable(db);
		cursor = db.rawQuery("select * from user", null);
		if(!cursor.moveToNext())
			dbpegawai.generatedata(db);
		
		kback.setOnClickListener(
				new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						finish();
					}
				});
		
		daftarp.setOnClickListener(
				new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						db.execSQL("insert into user(nama,email,password) VALUES('"+
								pnama.getText()+"', '"+pemail.getText()+"','"+ ppassword.getText()
								+"')");
						Intent i = new Intent(pegawai_tambah.this, pegawai_home.class);
						startActivity(i);
					}
				});
	}

}
