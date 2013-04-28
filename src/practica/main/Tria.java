package practica.main;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Tria extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tria);
    }
    
    public void add(View v) {
		Intent intent = new Intent();
		intent.setComponent(new ComponentName(this,Add.class));
		startActivity(intent);
	}

    public void editar(View v) {
		Intent intent = new Intent();
		intent.setComponent(new ComponentName(this,Editar.class));
		startActivity(intent);
	}

}
