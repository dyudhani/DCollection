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

public class login extends Activity {

	Button masuk;
	EditText lemail,lpassword;
	TextView ldaftar;
	
	SQLiteDatabase db;
	Cursor cursor;
	database dbuser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		masuk = (Button) findViewById(R.id.masuk);
		lemail = (EditText)findViewById(R.id.lemail);
		lpassword = (EditText) findViewById(R.id.lpassword);
		ldaftar = (TextView) findViewById(R.id.ldaftar);
		
		dbuser = new database(this);
		db = dbuser.getWritableDatabase();
		dbuser.createtable(db);
		
		cursor = db .rawQuery("select * from user", null);
		if(!cursor.moveToNext())
			dbuser.generatedata(db);
		
		masuk.setOnClickListener(
				new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						cursor = db.rawQuery("select * from user where " +
								"email='"+lemail.getText()+"' and " +
								"password='"+lpassword.getText()+"'", null);
						if(cursor.moveToNext())
							menujuBeranda();
						finish();
					}
				});
		
		
		ldaftar.setOnClickListener(
				new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						menujudaftar();
					}
				});
	}
	void menujuBeranda(){
		Intent i = new Intent(this, splash2.class);
		startActivity(i);
	}
	
	
	void menujudaftar(){
		Intent i = new Intent(this, register.class);
		startActivity(i);
 	}

}
