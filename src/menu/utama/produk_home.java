package menu.utama;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.w3c.dom.Text;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class produk_home extends ListActivity {

	TextView kback,ketamproduk;
	String simpan;
	
	ListAdapter daftarproduk;
	SQLiteDatabase db;
	Cursor cursor;
	database dbproduk;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.produk_home);
		
		kback = (TextView) findViewById(R.id.kback);
		ketamproduk = (TextView) findViewById(R.id.ketamproduk);
		
		dbproduk = new database(this);
		db = dbproduk.getWritableDatabase();
		dbproduk.createtable(db);
		cursor = db.rawQuery("select * from produk", null);
		if(!cursor.moveToNext())
			dbproduk.generatedata(db);
		fillProduk();
		registerForContextMenu(getListView());
		
		kback.setOnClickListener(
				new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						finish();
					}
				});
		ketamproduk.setOnClickListener(
				new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i = new Intent(produk_home.this, produk_tambah.class);
						startActivity(i);
					}
				});
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

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0,1,0, "Delete");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		HashMap<String, Object> 
			object = (HashMap<String, Object>) daftarproduk.getItem(info.position);
		switch (item.getItemId()) {
		case 1:
			Toast.makeText(this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
			simpan = object.get("namaProduk").toString();
			db.execSQL("delete from produk where namaProduk = " +
					"('"+simpan+"')");
			cursor = db.rawQuery("select * from produk", null);
			
			if(!cursor.moveToNext())
				dbproduk.generatedata(db);
			fillProduk();
			registerForContextMenu(getListView());
			break;

		}
		return super.onContextItemSelected(item);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		HashMap<String, Object> object=
			(HashMap<String, Object>) daftarproduk.getItem(position);
		linked(object.get("namaProduk").toString());
	}
	public void linked(String z){
		Intent i = new Intent(this, produk_edit.class);
		i.putExtra("kirim", z);
		startActivity(i);
	}
}
