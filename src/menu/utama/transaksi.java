package menu.utama;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class transaksi extends ListActivity {

	String JumlahBeli;
	TextView menu,jumlahProduk,totagih,tagih;
	LinearLayout keTagih;
	Integer jumlah=1,jumlah1,countuang1;
	
	ListAdapter daftarproduk;
	SQLiteDatabase db;
	Cursor cursor,cursor1,cursoruang;
	database dbproduk,dbbayar;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transaksi);
	
		menu = (TextView) findViewById(R.id.menu);
		totagih = (TextView) findViewById(R.id.tagih);
		jumlahProduk = (TextView)findViewById(R.id.jumlahProduk);
		tagih = (TextView) findViewById(R.id.tagih);
		keTagih = (LinearLayout) findViewById(R.id.keTagih);
		
		dbproduk = new database(this);
		db = dbproduk.getWritableDatabase();
		dbproduk.createtable(db);
		cursor = db.rawQuery("select * from produk", null);
		if(!cursor.moveToNext())
			dbproduk.generatedata(db);
		fillProduk();
		registerForContextMenu(getListView());
		JumlahBeli();
		tagih();
		
		dbbayar=new database(this);
        db=dbbayar.getWritableDatabase();
        dbbayar.createtable(db);
        cursor=db.rawQuery("SELECT *, COUNT( * ) AS totaljumlah FROM temp GROUP BY namaProduk", null);
		
		menu.setOnClickListener(
        		new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						db.execSQL("delete from temp");
						Intent i = new Intent(transaksi.this, tampilmenu.class);
						startActivity(i);
						overridePendingTransition(R.anim.keluarkanan, R.anim.keluarkiri);
					}
				});
		
		keTagih.setOnClickListener(
        		new OnClickListener() {
					
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(jumlah==0){
							nullbarang();
						}
						else{
							kebayar(tagih.getText().toString());
							Intent i = new Intent(transaksi.this, transaksi2.class);
							startActivity(i);
							finish();
						}
					}
				}
        		);
	}
	
	void kebayar(String a){
    	Intent i=new Intent(this,transaksi2.class);
    	i.putExtra("bayar", a);
    	startActivity(i);
    }
	
	private void fillProduk(){
		List<produk> dataproduk = getData();
		daftarproduk = new SimpleAdapter(this, dataproduk, android.R.layout.simple_list_item_2,
				new String[]{produk.KEYnamaProduk, produk.KEYhargaJual, produk.KEYstock},
				new int[]{android.R.id.text1, android.R.id.text2});
		setListAdapter(daftarproduk);	
	}

	private List<produk> getData(){
		List<produk> lproduk = new ArrayList<produk>();
		do
			lproduk.add(new produk(cursor.getString(1), cursor.getString(2)));
		while (cursor.moveToNext());
		return lproduk;
	}

	public void nullbarang() {
		// TODO Auto-generated method stub
		AlertDialog al=new AlertDialog.Builder(this).create();
		al.setTitle("Perhatian");
		al.setMessage("Silahkan Pilih Menu Terlebih Dahulu");
		al.setButton("Ok", new DialogInterface.OnClickListener() {
			
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});
		al.show();
	}
	
	public void JumlahBeli(){
		dbproduk=new database(this);
        db=dbproduk.getWritableDatabase();
        dbproduk.createtable(db);
        
	    JumlahBeli= "SELECT count(*) FROM temp";
	    cursor1 = db.rawQuery(JumlahBeli, null);
	    cursor1.moveToFirst();
	    jumlah1 = cursor1.getInt(0);
	    jumlahProduk.setText(jumlah1.toString());
	}
	
	public void tagih(){
		dbproduk = new database(this);
		db = dbproduk.getWritableDatabase();
		dbproduk.createtable(db);
		
		String count = "SELECT sum(hargaJual) FROM temp";
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int count1 = mcursor.getInt(0);
        tagih.setText(""+count1);
        
        String countuang = "SELECT sum(totalMoney) FROM money";
        Cursor cursoruang = db.rawQuery(countuang, null);
        cursoruang.moveToFirst();
        countuang1 = cursoruang.getInt(0);
        
        db.execSQL("insert into simpanduit (totalHarga) values" +
				"('"+countuang1.toString()+"')");
	}
	
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		HashMap<String, Object> object=(HashMap<String, Object>) daftarproduk.getItem(position);
		
		db.execSQL("insert into temp (namaProduk,hargaJual,jumlah) values" +
				"('"+object.get("namaProduk").toString()+"','"+object.get("hargaJual").toString()+"','"+jumlahProduk.toString()+"')");
		db.execSQL("insert into money (namaProduk,jumlah,totalMoney) values" +
				"('"+object.get("namaProduk").toString()+"','"+tagih.toString()+"','"+object.get("totalMoney").toString()+"')");	
		JumlahBeli();
		tagih();
	}
	
	public void onBackPressed() {
		// TODO Auto-generated method stub
		db.execSQL("delete from temp");
		Intent i = new Intent(transaksi.this, utama.class);
		startActivity(i);
		finish();
		super.onBackPressed();
	}
}
