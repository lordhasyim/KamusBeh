package com.hasyim.kamusbeh;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CariKata extends Activity {

	//private DBDataSource dataSource;

	private EditText edIndonesia;
	private EditText edInggris;
	private EditText edJerman;
	//private Button buttonCari;

	private Cursor kamusCursor = null;
	private SQLiteDatabase db = null;
	private DBHelper datakamus = null;

	//private Cursor kamusCursor;
	//private SQLiteDatabase db;
	//private DBHelper datakamus;
	
	public static final String INDONESIA = "indonesia";
	public static final String INGGRIS = "inggris";

	public static final String JERMAN = "jerman";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		datakamus = new DBHelper(this);
		db = datakamus.getReadableDatabase();
		setContentView(R.layout.cari_kata);

		edIndonesia = (EditText) findViewById(R.id.edCariIndonesia);
		edInggris = (EditText) findViewById(R.id.edCariInggris);
		edJerman = (EditText) findViewById(R.id.edCariJerman);
		
	final Button buttonCari = (Button) findViewById(R.id.tombolCari);

	

		buttonCari.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				String bhsinggris = "";
				String bhsjerman = "";

				String bhsindonesia = edIndonesia.getText().toString();
				kamusCursor = db.rawQuery("SELECT _id, indonesia, inggris, jerman "
						+ "FROM data_kamus WHERE indonesia='" + bhsindonesia
						+ "' ORDER BY indonesia", null);
				if (kamusCursor.moveToFirst()) {
					for (; !kamusCursor.isAfterLast(); kamusCursor.moveToNext()) {
						bhsinggris = kamusCursor.getString(2);
						bhsjerman = kamusCursor.getString(3);
					}
				} else {
					Toast.makeText(getBaseContext(), "Terjemahan Tidak di temukan",
							Toast.LENGTH_SHORT).show();
				}
				
				edInggris.setText(bhsinggris);
				edJerman.setText(bhsjerman);
			}
		});
	}
	public void onDestroy() {
		super.onDestroy();
		try {
			kamusCursor.close();
			db.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
