package practica.main;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Editar extends Activity {
	
	private ArrayList<String> edicio;
	private String llengua;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editar);
		
		edicio = new ArrayList<String>();
		ListView lv = (ListView) findViewById(R.id.ListView01);
		
		//########## Get the language
		//	and get all words from the DB
		DatabaseHelper databaseHelper = new DatabaseHelper(this);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		Cursor c = db.rawQuery("SELECT llengua FROM llengues WHERE actual = \"si\"",  null );
		if(c.moveToFirst()) llengua = c.getString(c.getColumnIndex("llengua"));
		else llengua = "catalan";
		TextView t=new TextView(this); 
        t=(TextView)findViewById(R.id.llengua);
		t.setText(llengua.toString());
		c= db.rawQuery("SELECT word, paraula FROM traduccions WHERE llengua = \""+llengua+"\"", null);
		if(c.moveToFirst()){
			do{
				edicio.add(c.getString(c.getColumnIndex("word"))+" - "+c.getString(c.getColumnIndex("paraula")));
			}while(c.moveToNext());
		}
		lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, edicio));

		lv.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				edita(view, position);
				return true; //there must be a return, so I did ;)
			}
		});
			
	}	
	
	private void edita(View v, int i){
		Intent intent = new Intent();
		intent.setComponent(new ComponentName(this,Edicio.class));
		String aux = edicio.get(i);
		String[] paraules = aux.split(" - ");
		intent.putExtra("llengua", llengua);
		intent.putExtra("word", paraules[0]);
		intent.putExtra("paraula", paraules[1]);
		startActivity(intent);
	}
	
	public void onListItemClick(ListView parent, View v, int position, long id) {
		TextView t=new TextView(this); 
        t=(TextView)findViewById(R.id.llengua);
        t.setText(edicio.get(position).toString());
	}

}
