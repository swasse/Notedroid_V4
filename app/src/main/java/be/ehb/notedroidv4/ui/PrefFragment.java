package be.ehb.notedroidv4.ui;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import be.ehb.notedroidv4.R;

/**
 * Created by Banaan on 20/01/2038. ;)
 */
public class PrefFragment extends PreferenceFragmentCompat {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
    }
}
