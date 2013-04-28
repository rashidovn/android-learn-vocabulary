package practica.main;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class PracticaActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void start(View v) {
		Intent intent = new Intent();
		intent.setComponent(new ComponentName(this,Start.class));
		startActivity(intent);
	}

    public void edit(View v) {
		Intent intent = new Intent();
		intent.setComponent(new ComponentName(this,Tria.class));
		startActivity(intent);
	}

    public void options(View v) {
		Intent intent = new Intent();
		intent.setComponent(new ComponentName(this,Options.class));
		startActivity(intent);
	}
    
    public void help(View v) {
		Intent intent = new Intent();
		intent.setComponent(new ComponentName(this,Help.class));
		startActivity(intent);
	}
}