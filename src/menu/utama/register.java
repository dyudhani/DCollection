package menu.utama;

import menu.utama.R;
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

public class register extends Activity {

	TextView rback;
	EditText rnama,remail,rpassword;
	Button daftar;
	
	SQLiteDatabase db;
	Cursor cursor;
	database dbuser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		rback = (TextView) findViewById(R.id.rback);
		rnama = (EditText) findViewById(R.id.rnama);
		remail = (EditText) findViewById(R.id.remail);
		rpassword = (EditText) findViewById(R.id.rpassword);
		daftar = (Button) findViewById(R.id.daftar);
		
		dbuser = new database(this);
		db = dbuser.getWritableDatabase();
		dbuser.createtable(db);
		
		cursor = db.rawQuery("select * from user", null);
		if(!cursor.moveToNext())
			dbuser.generatedata(db);
		
		rback.setOnClickListener(
				new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						finish();
					}
				});
		
		daftar.setOnClickListener(
				new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						 db.execSQL("insert into user(nama,email,password) VALUES('"+
								rnama.getText()+"', '"+remail.getText()+"','"+ rpassword.getText()
								+"')");
						 createUser();
					}
				});
	}
	
	public void createUser(){
		Intent i = new Intent(this, login.class);
		startActivity(i);
	}

}
