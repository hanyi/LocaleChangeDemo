package com.android.example;

import java.util.Locale;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.ListPreference;
import android.preference.PreferenceManager;
import android.preference.PreferenceActivity;

public class SettingsActivity extends PreferenceActivity implements
		Preference.OnPreferenceChangeListener {

	PreferenceManager manager;
	ListPreference listPreference;
	SharedPreferences sharedPreference;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.language_option_preference);
		sharedPreference = PreferenceManager.getDefaultSharedPreferences(this);
		
		manager = getPreferenceManager();
		listPreference = (ListPreference) manager.findPreference("language_setting");
		
		listPreference.setOnPreferenceChangeListener(this);
	}

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		
		Resources resource = getResources();
		Configuration config = resource.getConfiguration();
		
		int pos = Integer.parseInt((String) newValue);
    	if (pos == 1) {
    		sharedPreference.edit().putString("language", "en").commit();
			config.locale = Locale.ENGLISH;
			listPreference.setValue("1");
		} else if (pos == 2) {
			sharedPreference.edit().putString("language", "zh").commit();
			config.locale = Locale.CHINA;
			listPreference.setValue("2");
		} else {
			sharedPreference.edit().putString("language", "auto").commit();
			config.locale = Locale.getDefault();
			listPreference.setValue("0");
		}
		
		getBaseContext().getResources().updateConfiguration(config, null);
		
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		this.startActivity(intent);
		return false;
	}
}
