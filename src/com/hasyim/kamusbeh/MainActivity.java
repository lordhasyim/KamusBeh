package com.hasyim.kamusbeh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		final Button btnTambah = (Button) findViewById(R.id.btnTambahKata);
		btnTambah.setOnClickListener(this);

		btnTambah.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intentTambah = new Intent(btnTambah.getContext(), CreateKata.class);
				startActivity(intentTambah);
			}
		});
		
		final Button btnLihat = (Button) findViewById(R.id.btnLihatKata);
		btnLihat.setOnClickListener(this);
		btnLihat.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intentLihat = new Intent(btnLihat.getContext(), ViewKata.class);
				startActivity(intentLihat);
			}
		});
		
		final Button btnCariKata = (Button)findViewById(R.id.btnCariKata);
		btnCariKata.setOnClickListener(this);
		btnCariKata.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent IntentCari = new Intent(btnCariKata.getContext(), CariKata.class);
				startActivity(IntentCari);
			}
		});
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
}
