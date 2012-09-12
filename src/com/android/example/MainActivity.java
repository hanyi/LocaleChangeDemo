package com.android.example;

import java.util.Locale;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends Activity {
	
	Button btnLanguageSetting;
	SharedPreferences sharedPreference;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /**
         * bellow code set config's locale from pre saved sharedPreference,
         * if not do this, the app could forget the user's language set after he clear the memory.
         */
        Resources resource = getResources();
		Configuration config = resource.getConfiguration();
		sharedPreference = PreferenceManager.getDefaultSharedPreferences(this);
        if ("zh".equalsIgnoreCase(sharedPreference.getString("language", null))) {
        	config.locale = Locale.CHINA;
		} else if ("en".equalsIgnoreCase(sharedPreference.getString("language", null))) {
			config.locale = Locale.ENGLISH;
		} else {
			config.locale = Locale.getDefault();
		}
        getBaseContext().getResources().updateConfiguration(config, null);
        
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	if (item.getItemId() == R.id.menu_settings) {
    		Intent intent = new Intent().setClass(this, SettingsActivity.class);
    		this.startActivity(intent);
    	}
    	return true;
    }
}
