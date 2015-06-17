/*
 * Changelog
 * 17 de junio 2015:
 * - Agrego el animateCamera y pruebo de pasarle el valor de BA con zoom de 12 y despues de 10.
 * - Modifico para que con una misma variable de Mark pueda ingresar 2 Lat y Long diferentes.
 * 16 de junio 2015:
 * - Prueba de agregar multiples marcas al mapa
 */
package com.dotworld.googlemapstestv1;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

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
			// Aca se pone el tipo de mapa
			googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			// Aca se pueden cargar las coordenadas para las marcas
			final LatLng CIU = new LatLng(35.21843892856462, 33.41662287712097);         
			Marker ciu = googleMap.addMarker(new MarkerOptions().position(CIU).title("My Office"));  
			final LatLng BA = new LatLng(-34.5901256,-58.3952647);           
			ciu = googleMap.addMarker(new MarkerOptions().position(BA).title("Buenos Aires, yo !!!"));
			
			googleMap.setMyLocationEnabled(true); // false to disable
			googleMap.getUiSettings().setCompassEnabled(true);
			
			CameraPosition cameraPosition = new CameraPosition.Builder().target(
					BA).zoom(10).build();

			googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
			
			if (googleMap == null){
				Toast.makeText(getApplicationContext(), "Lo lamento no esta el maps !!!", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		initilizeMap();
	}
}
