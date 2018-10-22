package com.catolica.salesianoapp.ucv.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.catolica.salesianoapp.ucv.model.Noticia;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "AddtoFav";
	private static final String TABLE_NAME = "Favoritos";
	private static final String ID = "id";
	private static final String CATID = "idcat";
	private static final String KEY_CID = "idnoticia";
	private static final String KEY_CATEGORYNAME = "categoryname";
 	private static final String KEY_NEWSHEADING = "newsheading";
	private static final String KEY_NEWSIMAGE = "newsimage";
	private static final String KEY_NEWSDESC = "newsdesc";
	private static final String KEY_NEWSDATE = "newsdate";



	public DatabaseHandler(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Criação da Tabela
			@Override
			public void onCreate(SQLiteDatabase db)
			{
				String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
						+ ID + " INTEGER PRIMARY KEY,"
						+ CATID + " TEXT,"
						+ KEY_CID + " TEXT," 
						+ KEY_CATEGORYNAME + " TEXT,"
 						+ KEY_NEWSHEADING + " TEXT,"
						+ KEY_NEWSIMAGE + " TEXT,"
						+ KEY_NEWSDESC + " TEXT,"
						+ KEY_NEWSDATE + " TEXT"
					 	+ ")";
				db.execSQL(CREATE_CONTACTS_TABLE);		
				
			}

			// Atualizando o BD
			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				// TODO Auto-generated method stub
				// Drop o BD se existir
						db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

						// Cria a tabela novamente.
						onCreate(db);
			}
			
			//Adicionando registro ao BD
			public	void AddtoFavorite(Noticia noticia)
			{
				SQLiteDatabase db = this.getWritableDatabase();

				ContentValues values = new ContentValues();
				values.put(CATID, noticia.getIdCategoria());
				values.put(KEY_CID,noticia.getIdNoticia());
				values.put(KEY_CATEGORYNAME,noticia.getCategoria());
 				values.put(KEY_NEWSHEADING, noticia.getTituloNoticia());
				values.put(KEY_NEWSIMAGE,noticia.getImgNoticia());
				values.put(KEY_NEWSDESC, noticia.getDescricaoNoticia());
				values.put(KEY_NEWSDATE, noticia.getDataNoticia());

			 
				// Insere a linha.
				db.insert(TABLE_NAME, null, values);
				db.close(); // Fecha a conexão com o BD
				
			}
			
			// Getting All Data
			public List<Noticia> getAllData()
			{
				List<Noticia> dataList = new ArrayList<Noticia>();
				// Select All Query
				String selectQuery = "SELECT  * FROM " + TABLE_NAME;

				SQLiteDatabase db = this.getWritableDatabase();
				Cursor cursor = db.rawQuery(selectQuery, null);

				// looping through all rows and adding to list
				if (cursor.moveToFirst()) 
				{
					do {
						Noticia noticia = new Noticia();
						noticia.setId(Integer.parseInt(cursor.getString(0)));
						noticia.setIdCategoria(cursor.getString(1));
						noticia.setIdNoticia(cursor.getString(2));
						noticia.setCategoria(cursor.getString(3));
						noticia.setTituloNoticia(cursor.getString(4));
						noticia.setImgNoticia(cursor.getString(5));
						noticia.setDescricaoNoticia(cursor.getString(6));
						noticia.setDataNoticia(cursor.getString(7));
						 
						// Adding contact to list
						dataList.add(noticia);
					} while (cursor.moveToNext());
				}

				// return contact list
				return dataList;
			}
			
		//getting single row
			
			public List<Noticia> getFavRow(String id)
			{
				List<Noticia> dataList = new ArrayList<Noticia>();
				// Select All Query
				String selectQuery = "SELECT  * FROM " + TABLE_NAME +" WHERE catid="+id;

				SQLiteDatabase db = this.getWritableDatabase();
				Cursor cursor = db.rawQuery(selectQuery, null);

				// looping through all rows and adding to list
				if (cursor.moveToFirst()) 
				{
					do {
						Noticia noticia = new Noticia();
						noticia.setId(Integer.parseInt(cursor.getString(0)));
						noticia.setIdCategoria(cursor.getString(1));
						noticia.setIdNoticia(cursor.getString(2));
						noticia.setCategoria(cursor.getString(3));
						noticia.setTituloNoticia(cursor.getString(4));
						noticia.setImgNoticia(cursor.getString(5));
						noticia.setDescricaoNoticia(cursor.getString(6));
						noticia.setDataNoticia(cursor.getString(7));

						// Adding contact to list
						dataList.add(noticia);
					} while (cursor.moveToNext());
				}

				// return contact list
				return dataList;
			}
			
		//for remove favorite
			
			public void RemoveFav(Noticia noticia)
			{
			    SQLiteDatabase db = this.getWritableDatabase();
			    db.delete(TABLE_NAME, CATID + " = ?",
			            new String[] { String.valueOf(noticia.getIdCategoria()) });
			    db.close();
			}
			
			public enum DatabaseManager {
				INSTANCE;
				private SQLiteDatabase db;
				private boolean isDbClosed =true;
				DatabaseHandler dbHelper;
				public void init(Context context) {
					dbHelper = new DatabaseHandler(context);
				if(isDbClosed){
				isDbClosed =false;
				this.db = dbHelper.getWritableDatabase();
				}

				}


				public boolean isDatabaseClosed(){
				return isDbClosed;
				}

				public void closeDatabase(){
				if(!isDbClosed && db != null){
				isDbClosed =true;
				db.close();
				dbHelper.close();
				}
				}
			}
}
