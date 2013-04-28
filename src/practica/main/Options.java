package practica.main;

import practica.main.R;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import android.R.array;


public class Options extends Activity{

	private TextView t;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);
        
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
		DatabaseHelper databaseHelper = new DatabaseHelper(this);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		Cursor c = db.query("score", null, null, null, null, null, null);
		if (c.getCount() > 0 && c.moveToFirst() && c.getString(3).equals("si")) checkBox.setChecked(true);
		else checkBox.setChecked(false);
		

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( this, R.array.Languages, android.R.layout.simple_spinner_item );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        final Spinner s = (Spinner) findViewById( R.id.spinnerLang );
        s.setAdapter( adapter );
        s.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

				Object item = parent.getItemAtPosition(pos);
		        changeLanguage(item.toString());
		        
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
        	
        });
        c.close();
        db.close();
	}
	
	private void changeLanguage(String llengua) {

		DatabaseHelper databaseHelper = new DatabaseHelper(this);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		
		db.execSQL("UPDATE llengues SET actual='no' WHERE actual='si'");
		db.execSQL("UPDATE llengues SET actual='si' WHERE llengua='" + llengua + "'");
		
		db.close();
	}
	
	public void checking(View v){
		CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
		DatabaseHelper databaseHelper = new DatabaseHelper(this);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();

		if (checkBox.isChecked()) {
            checkBox.setChecked(true);
            db.execSQL("UPDATE score SET activat='si'");
        }
		else{
			checkBox.setChecked(false);
			db.execSQL("UPDATE score SET activat='no'");
		}
		


	}


	
	
}
