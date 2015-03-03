package com.hasyim.kamusbeh;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateKata extends Activity implements OnClickListener {
	
	//inisialisasi elemen elemen pada layout
	private Button btnSubmit;
	private EditText edIndonesia;
	private EditText edInggris;
	private EditText edJerman;
	//inisialisasi Kontroller / Data Source
	private DBDataSource dataSource;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_kata);
        
        edIndonesia = (EditText)findViewById(R.id.edIndonesia);
        edInggris = (EditText)findViewById(R.id.edInggris);
        edJerman = (EditText)findViewById(R.id.edJerman);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
        
        //instansi kelas DBDataSource
        dataSource = new DBDataSource(this);
        
        //membuat sambungan baru ke database
        dataSource.open();
	}
	
	//ketika tombol submit di klik
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//inisialisasi data barang
		String indonesia = null;
		String inggris = null;
		String jerman  = null;
		
		//inisialisas kata baru (masih kosong)
		Kata kata = null;
		if (edIndonesia.getText()!=null && edInggris.getText()!=null && edJerman.getText()!=null) {
			//juka field indonesia, inggris, jerman tidak mask maka masukkan ke dalam data kata
			indonesia = edIndonesia.getText().toString();
			inggris = edInggris.getText().toString();
			jerman = edJerman.getText().toString();
		}
		
		switch (v.getId()) {
		case R.id.btnSubmit:
			//insert data kata baru
			kata = dataSource.createKata(indonesia, inggris, jerman);
			//konfirmasi kesuksesan
			Toast.makeText(this, "masuk Kata " +
					" indonesia " + kata.getIndonesia() +
					" inggris " + kata.getInggris() +
					" jerman " + kata.getJerman(), Toast.LENGTH_LONG).show();
			break;
		}
	
	
	
	}
}