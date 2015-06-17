package com.dotworld.googlemapstestv1;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	private GoogleMap googleMap;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		try {
			initilizeMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void initilizeMap(){
		if (googleMap == null){
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
			if (googleMap == null){
				Toast.makeText(getApplicationContext(), "Jodido.. no esta el maps !!!", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		initilizeMap();
	}
}
