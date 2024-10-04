package menu.utama;

import menu.utama.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class utama extends Activity {
    /** Called when the activity is first created. */
	
	TextView menu;
	LinearLayout kproduk,pegawai;
	Button totransaksi;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beranda);
        
        menu = (TextView)findViewById(R.id.menu);
        kproduk = (LinearLayout) findViewById(R.id.kproduk);
        pegawai = (LinearLayout) findViewById(R.id.pegawai);
        totransaksi = (Button) findViewById(R.id.totransaksi);
        
        menu.setOnClickListener(
        		new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i = new Intent(utama.this, tampilmenu.class);
						startActivity(i);
						overridePendingTransition(R.anim.keluarkanan, R.anim.keluarkiri);
					}
				});
        
        kproduk.setOnClickListener(
        		new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i = new Intent(utama.this, produk_home.class);
						startActivity(i);
					}
				});
        pegawai.setOnClickListener(
        		new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i = new Intent(utama.this, pegawai_home.class);
						startActivity(i);
					}
				});
        totransaksi.setOnClickListener(
        		new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent a = new Intent(utama.this, transaksi.class);
						startActivity(a);
					}
				});
        
    }
	
}