package menu.utama;


import menu.utama.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class tampilmenu extends Activity {
	
	LinearLayout back,mberanda,mtransaksi,mlogout;
	AlertDialog alert;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tampilmenu);
		
		back =(LinearLayout) findViewById(R.id.back);
		mberanda = (LinearLayout) findViewById(R.id.mberanda);
		mtransaksi = (LinearLayout) findViewById(R.id.mtransaksi);
		mlogout =(LinearLayout) findViewById(R.id.mlogout);
		
		alert = new AlertDialog.Builder(this).create();
		
		back.setOnClickListener(
				new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						finish();
						overridePendingTransition(R.anim.masukkiri, R.anim.keluarkiri);
					}
				});
		mberanda.setOnClickListener(
				new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i = new Intent(tampilmenu.this, utama.class);
						i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(i);
						finish();
					}
				});
		mtransaksi.setOnClickListener(
				new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i = new Intent(tampilmenu.this, transaksi.class);
						i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(i);
						finish();
					}
				});
		mlogout.setOnClickListener(
				new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						alert.setTitle("Perhatian");
						alert.setMessage("Keluar dari akun ini?");
						alert.setButton("Ya", new DialogInterface.OnClickListener() {
							
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Intent i = new Intent(tampilmenu.this, login.class);
								i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
								startActivity(i);
							}
						});
							alert.setButton2("Tidak", new DialogInterface.OnClickListener() {
								
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									
								}
							});
							alert.show();
					}
				});
	}

}
