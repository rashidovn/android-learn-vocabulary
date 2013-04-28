package practica.main;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

public class Edicio extends Activity{
	
	private String Oparaula;
	private String Oword;
	private String llengua;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edicio);
        
		Bundle bundle = getIntent().getExtras();
		Oparaula = bundle.getString("paraula");
		Oword = bundle.getString("word");
		llengua = bundle.getString("llengua");
		
		TextView t = new TextView(this);
		t=(TextView)findViewById(R.id.editText1);
		t.setText(Oword);
		t=(TextView)findViewById(R.id.editText2);
		t.setText(Oparaula);
		TextView txt = new TextView(this);
		txt = (TextView)  findViewById(R.id.textView3);
		txt.setText(llengua);
		txt = (TextView)  findViewById(R.id.textView5);
		txt.setText(llengua);
    }
	
	public void change(View v){
		Intent intent = new Intent();
		intent.setComponent(new ComponentName(this,Editar.class));
		TextView t = new TextView(this);
		t=(TextView)findViewById(R.id.editText1);
		String word = t.getText().toString().toLowerCase().trim();
		t=(TextView)findViewById(R.id.editText2);
		String paraula = t.getText().toString().toLowerCase().trim();
		if(word.equals("") || paraula.equals("")){
			//ERROR -> at least one is empty 
			Toast.makeText(getBaseContext(), 
					"You've forgot to fill both text fields", 
					Toast.LENGTH_SHORT).show();
		}
		else{
			DatabaseHelper databaseHelper = new DatabaseHelper(this);
			SQLiteDatabase db = databaseHelper.getWritableDatabase();
			db.execSQL("UPDATE traduccions SET paraula = \""+paraula+"\", word = \""+ word +
					"\" WHERE llengua =\""+llengua +"\" AND paraula = \""+ Oparaula +"\" AND word = \""+ Oword +"\"");
			Toast.makeText(getBaseContext(), "This words have been edited from our database",	Toast.LENGTH_SHORT).show();
			espera();
	        cancel(v);
		}
	}
	
	public void borra(View v){
		DatabaseHelper databaseHelper = new DatabaseHelper(this);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		db.execSQL("DELETE FROM traduccions  WHERE llengua =\""+llengua +"\" AND paraula = \""+ Oparaula +"\" AND word = \""+ Oword +"\"");
		Toast.makeText(getBaseContext(), "This words have been removed from our database",	Toast.LENGTH_SHORT).show();
		espera();
		cancel(v);
	}
	
	public void cancel(View v) {
		Intent intent = new Intent();
		intent.setComponent(new ComponentName(this,Editar.class));
		startActivity(intent);
	}
	
	private void espera(){
		long t0,t1;
        t0=System.currentTimeMillis();
        do{ t1=System.currentTimeMillis(); } while (t1-t0<1000); //wait 1 sec
	}

}
