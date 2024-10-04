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

public class pegawai_home extends ListActivity {

	TextView kback,ketampegawai;
	ListAdapter daftarpegawai;
	String simpan;
	
	SQLiteDatabase db;
	Cursor cursor;
	database dbpegawai;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pegawai_home);
		
		kback = (TextView) findViewById(R.id.kback);
		ketampegawai = (TextView) findViewById(R.id.ketampegawai);
//		k = (TextView) findViewById(R.id.ketamproduk);
		
		dbpegawai = new database(this);
		db = dbpegawai.getWritableDatabase();
		dbpegawai.createtable(db);
		cursor = db.rawQuery("select * from user", null);
		if(!cursor.moveToNext())
			dbpegawai.generatedata(db);
		fillPegawai();
		registerForContextMenu(getListView());
		
		kback.setOnClickListener(
				new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						finish();
					}
				});
		
		ketampegawai.setOnClickListener(
				new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i = new Intent(pegawai_home.this, pegawai_tambah.class);
						startActivity(i);
					}
				});
	}
	
	private void fillPegawai(){
		List<pegawai> datapegawai = getData();
		daftarpegawai = new SimpleAdapter(this, datapegawai, android.R.layout.simple_list_item_2,
				new String[]{ pegawai.KEYnama, pegawai.KEYemail},
				new int[]{android.R.id.text1, android.R.id.text2});
		setListAdapter(daftarpegawai);	
	}

	private List<pegawai> getData(){
		List<pegawai> lpegawai = new ArrayList<pegawai>();
		do
			lpegawai.add(new pegawai(cursor.getString(1), cursor.getString(2)));
		while (cursor.moveToNext());
		return lpegawai;
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
			object = (HashMap<String, Object>) daftarpegawai.getItem(info.position);
		switch (item.getItemId()) {
		case 1:
			Toast.makeText(this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
			simpan = object.get("email").toString();
			db.execSQL("delete from user where email = " +
					"('"+simpan+"')");
			cursor = db.rawQuery("select * from user", null);
			
			if(!cursor.moveToNext())
				dbpegawai.generatedata(db);
			fillPegawai();
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
			(HashMap<String, Object>) daftarpegawai.getItem(position);
		linked(object.get("email").toString());
	}
	public void linked(String z){
		Intent i = new Intent(this, pegawai_tambah.class);
		i.putExtra("kirim", z);
		startActivity(i);
	}
}
