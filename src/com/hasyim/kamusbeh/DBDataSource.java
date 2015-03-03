package com.hasyim.kamusbeh;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBDataSource {
	
	//inisialisasi SQLite Database
	private SQLiteDatabase database;
	
	//inisialisasi class DBHelper
	private DBHelper dbHelper;
	
	//ambil semua nama kolom
	private String[] allColumns = {DBHelper.COLUMN_ID,DBHelper.COLUMN_INDONESIA,
			DBHelper.COLUMN_INGGRIS,DBHelper.COLUMN_JERMAN};
	
	//DBHelper diinstansi pada konstruktor
	public DBDataSource (Context context){
		dbHelper = new DBHelper(context);
	}
	
	//membuka / membuat koneksi baru ke database
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	//menutup sambungan ke database
	public void close (){
		dbHelper.close();
	}
	
	//method untuk create / insert kata ke database
	public Kata createKata(String indonesia, String inggris, String jerman){
		//membuat sebuah Content Values, yang berfungsi 
		//memasangkan data dengan nama - nama kolom pada database
		ContentValues values = new ContentValues();
		values.put(DBHelper.COLUMN_INDONESIA, indonesia);
		values.put(DBHelper.COLUMN_INGGRIS, inggris);
		values.put(DBHelper.COLUMN_JERMAN, jerman);
		
		//mengeksekusi perintah SQL insert data
		//yang akan mengembalikan sebuah insert ID
		long insertId = database.insert(DBHelper.TABLE_NAME, null, values);
		
		//setelah data dimasukkan, memanggil
		//perintah SQL select menggunakan Cursor
		//untuk melihat apakah data tadi benar2 sudah masuk
		//dengan menyesuaikan ID = insertID
		
		Cursor cursor = database.query(DBHelper.TABLE_NAME, allColumns, DBHelper.COLUMN_ID +"="+ insertId, null, null, null,null);
		
		
		//pindah ke data palin pertama
		cursor.moveToFirst();
		
		//mengubah objek pada kursor pertama tadi ke dala objek kata
		Kata newKata = cursorToKata(cursor);
		
		//close cursor
		cursor.close();
		
		//mengembalikan kata baru
		return newKata;
	}
	
	
	private Kata cursorToKata(Cursor cursor) {
		// TODO Auto-generated method stub
		//buat objek kata baru
		Kata kata = new Kata();
		//debug LOGCAT
		Log.v("info", "The getLong" + cursor.getLong(0));
		Log.v("info", "The setLangLng" +cursor.getString(1) + "," +cursor.getString(2));
		
		//set Atribut pada objek kata dengan
		//data kursor yang di ambil dari database
		kata.setId(cursor.getLong(0));
		kata.setIndonesia(cursor.getString(1));
		kata.setInggris(cursor.getString(2));
		kata.setJerman(cursor.getString(3));
		
		//mengembalikan objek kata
		return kata;
	}
	
	//mengambil semua data kata
	public ArrayList<Kata> getAllKata(){
		ArrayList<Kata> daftarKata = new ArrayList<Kata>();
		
		//select all SQL Query
		Cursor cursor = database.query(DBHelper.TABLE_NAME, allColumns, null, null, null, null, null);
		
		//pindah cursor ke data paling pertama
		cursor.moveToFirst();
		
		//jika masih ada data, masukkan data kata ke daftar kata
		while(!cursor.isAfterLast()){
			Kata kata = cursorToKata(cursor);
			daftarKata.add(kata);
			cursor.moveToNext();
		}
		//pastikan untuk meng - close cursor
		cursor.close();
		return daftarKata;
	}
	
	//ambil satu kata sesuai id
	public Kata getKata(long id){
		
		Kata kata = new Kata(); //inisialisasi kata
		//select query
		Cursor cursor = database.query(DBHelper.TABLE_NAME, allColumns, "_id = "+id, null, null, null, null);
		//ambil data yang pertama
		cursor.moveToFirst();
		//masukkan data cursor ke objek kata
		kata = cursorToKata(cursor);
		//tutup sambungan
		cursor.close();
		//kembalikan nilai kata
		return kata;
	}
	//update kata yg di edit
	public void updateKata(Kata k){
		//ambil id kata
		String strFilter = "_id=" + k.getId();
		//memasukkan ke content values
		ContentValues args= new ContentValues();
		//masukkan data sesuai kolom
		args.put(DBHelper.COLUMN_INDONESIA, k.getIndonesia());
		args.put(DBHelper.COLUMN_INGGRIS, k.getInggris());
		args.put(DBHelper.COLUMN_JERMAN, k.getJerman());
		
		//update query
		database.update(DBHelper.TABLE_NAME, args, strFilter, null);
	}
	
	//delete kata sesuai id
	public void deleteKata(long id){
		String strFilter = "_id=" +id;
		database.delete(DBHelper.TABLE_NAME, strFilter, null);
	}
	

}














