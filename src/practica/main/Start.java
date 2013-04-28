package practica.main;

import java.util.Random;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Start extends Activity{
	
	private Cursor cursor;
	private int max;
	private Random r;
	
	private String llengua;
	
	private TextView t2;
	private TextView t3;
	private TextView t4;
	private TextView e1;
	private TextView score;
	
	private boolean activat;
	private int encerts;
	private int fallos;
	
	private String paraula;
	private String word;
	
	private String solucio;
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
		
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		Cursor c = db.query("llengues", null, "actual='si'", null, null, null, null);
		if (c.getCount() > 0 && c.moveToFirst()) llengua = c.getString(c.getColumnIndex("llengua"));
		else llengua = "catalan";
		TextView t = new TextView(this);
		t=(TextView)findViewById(R.id.textView1);
		t2 =(TextView)findViewById(R.id.textView2);
		t3 =(TextView)findViewById(R.id.textView3);
		t4 =(TextView)findViewById(R.id.textView4);
		e1 =(TextView)findViewById(R.id.editText1);
		score =(TextView)findViewById(R.id.score);
		t.setText("You're learning "+llengua);
		//#
		encerts=fallos=0;
		c = db.query("score", null, null, null, null, null, null);
		if (c.getCount() > 0 && c.moveToFirst()) encerts = c.getInt(1);
		fallos = c.getInt(2);
		activat = c.getString(3).equals("si");
		if(activat){
			if(fallos==0) score.setText("100 %");
			else score.setText((encerts/(encerts+fallos)) + " %");
		}
		else{
			score.setText("");
			score =(TextView)findViewById(R.id.textView5);
			score.setText("");
			score =(TextView)findViewById(R.id.textView6);
			score.setText("");
		}
		//#
		cursor = db.rawQuery("SELECT paraula, word FROM traduccions WHERE llengua = \""+llengua+"\"", null);
		max = cursor.getCount();
		r = new Random();
		random();
	}
	
	public void next(View v){
		random();
	}
	
	public void aprenc(boolean b){
		if(b){
			t2.setText("Word in "+ llengua);
			t4.setText("Word in "+ "english");
			t3.setText(paraula);
			solucio = word;
		}
		else{
			t2.setText("Word in "+ "english");
			t4.setText("Word in "+ llengua);
			t3.setText(word);
			solucio = paraula;
		}
		e1.setText("");
	}
	
	public void random(){
		//int randomNumber = (int)r.nextFloat()*max;
		int randomNumber = r.nextInt(max);
		cursor.moveToPosition(randomNumber);
		if(cursor.isAfterLast() || cursor.isBeforeFirst()) random();
		else{
			paraula = cursor.getString(cursor.getColumnIndex("paraula"));
			word = cursor.getString(cursor.getColumnIndex("word"));
			aprenc(r.nextBoolean());
		}
	}
	
	public void check(View v){
		if(solucio.equals(e1.getText().toString().toLowerCase().trim())){
			Toast.makeText(getBaseContext(), "Congrats! you made it right!", Toast.LENGTH_SHORT).show();
			random();
			++encerts;
		}
		else{
			Toast.makeText(getBaseContext(), "It's wrong, do you want to try again?", Toast.LENGTH_SHORT).show();
			e1.setText("");
			++fallos;
		}
		if(activat){
			DatabaseHelper databaseHelper = new DatabaseHelper(this);
			SQLiteDatabase db = databaseHelper.getWritableDatabase();
			db.execSQL("UPDATE score SET encerts ="+encerts+", errors="+fallos+";");
			if(fallos==0) score.setText("100 %");
			else {
				float f = (((float)encerts/(float)(encerts+fallos)))*100;
				String s= Float.toString(f) +" %";
				score.setText(s);
			}
		}
	}
	
	public void solution(View v){
		e1.setText(solucio);
		Toast.makeText(getBaseContext(), "The next time will be easyer!",	Toast.LENGTH_SHORT).show();
	}

}
