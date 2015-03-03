package com.hasyim.kamusbeh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Activity EditKata.java pada dasarnya berfungsi untuk mengambil data kata yang
 * mau diedit, seperti indonesia, inggris, dan jerman. Untuk kemudian data
 * tersebut ditempatkan pada field EditText supaya bisa kita ganti. Setelah
 * selesai, maka kita mengklik tombol Save dan kemudian activity akan memanggil
 * method updateBarang() pada kontroller, yang akan melakukan update data lewat
 * SQL Query.
 * */

public class EditKata extends Activity implements OnClickListener {

	private DBDataSource dataSource;
	private long id;
	private String indonesia;
	private String inggris;
	private String jerman;

	private EditText edInggris;
	private EditText edIndonesia;
	private EditText edJerman;

	private TextView txId;

	private Button btnSave;
	private Button btnCancel;
	
	private Kata kata;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_kata);
		
		// inisialisasi variabel
		
		edIndonesia = (EditText)findViewById(R.id.editText_indonesia);
		edInggris = (EditText)findViewById(R.id.editText_inggris);
		edJerman = (EditText)findViewById(R.id.editText_jerman);
		txId = (TextView)findViewById(R.id.text_id_barang);
		
		//buat sambungan baru ke datbase
		dataSource = new DBDataSource(this);
		dataSource.open();
		
		//ambil data kata dari extras
		Bundle bun = this.getIntent().getExtras();
		id = bun.getLong("id");
		indonesia = bun.getString("indonesia");
		inggris = bun.getString("inggris");
		jerman = bun.getString("jerman");
		
		//masukkan data data kata tsb ke field editor
		txId.append(String.valueOf(id));
		edIndonesia.setText(indonesia);
		edInggris.setText(inggris);
		edJerman.setText(jerman);
		
		//setListener pada tombol
		btnSave = (Button)findViewById(R.id.button_save_update);
		btnSave.setOnClickListener(this);
		btnCancel = (Button)findViewById(R.id.button_cancel_update);
		btnCancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
			//apbila tombol save di klk (update kata)
		case R.id.button_save_update:
			kata = new Kata();
			kata.setIndonesia(edIndonesia.getText().toString());
			kata.setInggris(edInggris.getText().toString());
			kata.setJerman(edJerman.getText().toString());
			kata.setId(id);
			dataSource.updateKata(kata);
			Intent i = new Intent(this, ViewKata.class);
			startActivity(i);
			EditKata.this.finish();
			dataSource.close();
			break;
		//apabila tombol cancel di klik, finish activity
		case R.id.button_cancel_update:
			finish();
			dataSource.close();
			break;
		}

	}

}















