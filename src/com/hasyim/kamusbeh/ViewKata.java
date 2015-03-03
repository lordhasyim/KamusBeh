package com.hasyim.kamusbeh;

import java.util.ArrayList;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ViewKata extends ListActivity implements OnItemLongClickListener {

	// inisialisasi kontroller
	private DBDataSource dataSource;

	// inisalisasi / deklarasi array list
	private ArrayList<Kata> values;

	private Button editButton;
	private Button delButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_kata);

		dataSource = new DBDataSource(this);
		// buka kontroller
		dataSource.open();

		// ambil semua data kata g ada di tabel
		values = dataSource.getAllKata();

		// masukan data barang ke Array adapter
		// pr...(tambahkan listrow agar tampilan lebih bagus)
		ArrayAdapter<Kata> adapter = new ArrayAdapter<Kata>(this,
				android.R.layout.simple_list_item_1, values);

		// set adapter pada list
		setListAdapter(adapter);

		// mengambil listview untuk di set onItemClickListener
		ListView lv = (ListView) findViewById(android.R.id.list);
		lv.setOnItemLongClickListener(this);
	}

	@Override
	public boolean onItemLongClick(final AdapterView<?> adapter, View v,
			int pos, final long id) {
		// TODO Auto-generated method stub

		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialog_view);
		dialog.setTitle("Pilih Aksi");
		dialog.show();

		final Kata K = (Kata) getListAdapter().getItem(pos);
		editButton = (Button) dialog.findViewById(R.id.button_edit_kata);
		delButton = (Button) dialog.findViewById(R.id.button_delete_kata);

		// apabila tombol edit di klik
		editButton.setOnClickListener(new OnClickListener() {

			// @Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switchToEdit(K.getId());
				dialog.dismiss();
			}
		});

		// apabila tombol delete di klik
		delButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// delete kata
				// dataSource.deleteKata(K.getId());
				dataSource.deleteKata(K.getId());
				dialog.dismiss();
				finish();
				startActivity(getIntent());
			}
		});

		return true;
	}

	protected void switchToEdit(long id) {
		// TODO Auto-generated method stub

		Kata k = dataSource.getKata(id);
		Intent i = new Intent(this, EditKata.class);
		Bundle bun = new Bundle();
		bun.putLong("id", k.getId());
		bun.putString("indonesia", k.getIndonesia());
		bun.putString("inggris", k.getInggris());
		bun.putString("jerman", k.getJerman());
		i.putExtras(bun);
		finale();
		startActivity(i);
	}

	//method yang di panggil ketika edit data selesai
	private void finale() {
		// TODO Auto-generated method stub
		ViewKata.this.finish();
		dataSource.close();
	}
	
	@Override
	protected void onResume(){
		dataSource.open();
		super.onResume();
	}
	
	protected void onPause(){
		dataSource.close();
		super.onPause();
	}
}
