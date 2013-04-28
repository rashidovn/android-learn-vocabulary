package practica.main;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Add extends Activity{

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_words);
    }
	
	public void catalan(View v){
		TextView t=new TextView(this); 
        t=(TextView)findViewById(R.id.textView2);
		t.setText("catalan");
	}
	
	public void spanish(View v){
		TextView t=new TextView(this); 
        t=(TextView)findViewById(R.id.textView2);
		t.setText("spanish");
	}
	
	public void german(View v){
		TextView t=new TextView(this); 
        t=(TextView)findViewById(R.id.textView2);
		t.setText("german");
	}
	
	public void add(View v){
		String eng;
		String idioma;
		EditText e = new EditText(this);
		e = (EditText)findViewById(R.id.editText1);
		eng = e.getText().toString().toLowerCase().trim();
		e = (EditText)findViewById(R.id.editText2);
		idioma = e.getText().toString().toLowerCase().trim();
		if(eng.equals("") || idioma.equals("")){
			//ERROR -> at least one is empty 
			Toast.makeText(getBaseContext(), 
					"You've forgot to fill both text fields", 
					Toast.LENGTH_SHORT).show();
		}
		else{
			DatabaseHelper databaseHelper = new DatabaseHelper(this);
			SQLiteDatabase db = databaseHelper.getWritableDatabase();
			TextView t = new TextView(this);
			t = (TextView)findViewById(R.id.textView2);
			String llengua = t.getText().toString();
			//check if identical strings are already there
			Cursor c = db.rawQuery("SELECT * FROM traduccions WHERE llengua = \""+llengua+"\" AND word =\""+ eng + "\" AND paraula=\""+idioma+"\"", null);
			if(c.getCount() <= 0){
				db.execSQL("INSERT INTO traduccions VALUES (NULL, \""+llengua+"\",\""+eng+"\",\""+idioma+"\")");
				Toast.makeText(getBaseContext(), "This words have been added correctly in our database",	Toast.LENGTH_SHORT).show();
				clean(v);
			}
			else Toast.makeText(getBaseContext(), "You've already inserted that pair of words",	Toast.LENGTH_SHORT).show(); 
			c.close(); db.close();
			
		}
		
	}
	
	public void clean(View v){
		EditText e = new EditText(this);
		e = (EditText)findViewById(R.id.editText1);
		e.setText("");
		e = (EditText)findViewById(R.id.editText2);
		e.setText("");
	}
}
