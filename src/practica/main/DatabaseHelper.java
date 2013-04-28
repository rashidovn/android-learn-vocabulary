package practica.main;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "library.db";
	public static final String LLENGUA = "llengua";
	public static final String ACTUAL = "actual";
	public static final String PARAULA = "paraula";
	public static final String TRADUCCIO = "traduccio";
	public static final String ENCERTS = "encerts";
	public static final String FALLOS = "fallos";
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, 2); 
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(	"CREATE TABLE llengues (_id INTEGER PRIMARY KEY AUTOINCREMENT,llengua TEXT, actual TEXT);");
		
		ContentValues cv = new ContentValues();
		cv.put(DatabaseHelper.LLENGUA, "catalan");
		cv.put(DatabaseHelper.ACTUAL, "si");
		db.insert("llengues", DatabaseHelper.LLENGUA, cv);
		
		cv = new ContentValues();
		cv.put(DatabaseHelper.LLENGUA, "spanish");
		cv.put(DatabaseHelper.ACTUAL, "no");
		db.insert("llengues", DatabaseHelper.LLENGUA, cv);
		
		cv = new ContentValues();
		cv.put(DatabaseHelper.LLENGUA, "german");
		cv.put(DatabaseHelper.ACTUAL, "no");
		db.insert("llengues", DatabaseHelper.LLENGUA, cv);
		
		db.execSQL(	"CREATE TABLE traduccions (_id INTEGER PRIMARY KEY AUTOINCREMENT,llengua TEXT, word TEXT, paraula TEXT);");

		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"catalan\",\"example\",\"exemple\")");
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"spanish\",\"example\",\"ejemplo\")");
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"german\",\"example\",\"beispiel\")");
		
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"catalan\",\"house\",\"casa\")");
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"spanish\",\"house\",\"casa\")");
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"german\",\"house\",\"haus\")");
		
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"catalan\",\"bed\",\"llit\")");
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"spanish\",\"bed\",\"cama\")");
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"german\",\"bed\",\"bett\")");
		
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"catalan\",\"table\",\"taula\")");
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"spanish\",\"table\",\"mesa\")");
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"german\",\"table\",\"tisch\")");
		
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"catalan\",\"chair\",\"cadira\")");
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"spanish\",\"chair\",\"silla\")");
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"german\",\"chair\",\"stuhl\")");
		
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"catalan\",\"computer\",\"ordinador\")");
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"spanish\",\"computer\",\"ordenador\")");
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"german\",\"computer\",\"computer\")");
		
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"catalan\",\"window\",\"finestra\")");
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"spanish\",\"window\",\"ventana\")");
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"german\",\"window\",\"fenster\")");
		
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"catalan\",\"book\",\"llibre\")");
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"spanish\",\"book\",\"libro\")");
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"german\",\"book\",\"buch\")");
		
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"catalan\",\"dog\",\"gos\")");
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"spanish\",\"dog\",\"perro\")");
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"german\",\"dog\",\"hund\")");
		
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"catalan\",\"cat\",\"gat\")");
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"spanish\",\"cat\",\"gato\")");
		db.execSQL("INSERT INTO traduccions VALUES (NULL, \"german\",\"cat\",\"katze\")");
		
		db.execSQL(	"CREATE TABLE score (_id INTEGER PRIMARY KEY,encerts INTEGER, errors INTEGER, activat TEXT);");
		db.execSQL("INSERT INTO score VALUES (NULL, 0, 0, \"si\")");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
}