/*
 * Copyright (c) 2020. josephamcdonald - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by josephamcdonald
 */

package com.josephamcdonald.android.newzy;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import java.util.Objects;

public class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        //Find Newzy EditTextPreferences.
        EditTextPreference newzyApiKey = findPreference(getString(R.string.settings_api_key_key));
        EditTextPreference newzyFromDate = findPreference(getString(R.string.settings_newzys_from_date_key));
        EditTextPreference newzyToDate = findPreference(getString(R.string.settings_newzys_to_date_key));

        EditTextPreference newzyTitleOne = findPreference(getString(R.string.settings_newzy_title_one_key));
        EditTextPreference newzyTitleTwo = findPreference(getString(R.string.settings_newzy_title_two_key));
        EditTextPreference newzyTitleThree = findPreference(getString(R.string.settings_newzy_title_three_key));
        EditTextPreference newzyTitleFour = findPreference(getString(R.string.settings_newzy_title_four_key));
        EditTextPreference newzyTitleFive = findPreference(getString(R.string.settings_newzy_title_five_key));
        EditTextPreference newzyTitleSix = findPreference(getString(R.string.settings_newzy_title_six_key));

        EditTextPreference newzySearchOne = findPreference(getString(R.string.settings_newzy_search_one_key));
        EditTextPreference newzySearchTwo = findPreference(getString(R.string.settings_newzy_search_two_key));
        EditTextPreference newzySearchThree = findPreference(getString(R.string.settings_newzy_search_three_key));
        EditTextPreference newzySearchFour = findPreference(getString(R.string.settings_newzy_search_four_key));
        EditTextPreference newzySearchFive = findPreference(getString(R.string.settings_newzy_search_five_key));
        EditTextPreference newzySearchSix = findPreference(getString(R.string.settings_newzy_search_six_key));

        // Create listener to set input type and hint for News API key.
        EditTextPreference.OnBindEditTextListener apiKeyBindEditTextListener = new EditTextPreference.OnBindEditTextListener() {
            @Override
            public void onBindEditText(@NonNull EditText editText) {
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                editText.setHint(R.string.provide_api_key);
                editText.selectAll();
            }
        };

        // Create listener to set input type and hint for Newzy titles.
        EditTextPreference.OnBindEditTextListener titleBindEditTextListener = new EditTextPreference.OnBindEditTextListener() {
            @Override
            public void onBindEditText(@NonNull EditText editText) {
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                editText.setHint(R.string.provide_newzy_title);
                editText.selectAll();
            }
        };

        // Create listener to set input type and hint for Newzy searches.
        EditTextPreference.OnBindEditTextListener searchBindEditTextListener = new EditTextPreference.OnBindEditTextListener() {
            @Override
            public void onBindEditText(@NonNull EditText editText) {
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                editText.setHint(R.string.provide_newzy_search);
                editText.selectAll();
            }
        };

        // Create listener to set input type and hint for Newzy dates.
        EditTextPreference.OnBindEditTextListener dateBindEditTextListener = new EditTextPreference.OnBindEditTextListener() {
            @Override
            public void onBindEditText(@NonNull EditText editText) {
                editText.setInputType(InputType.TYPE_CLASS_DATETIME);
                editText.setHint(R.string.date_format);
                editText.selectAll();
            }
        };

        // Set listener on News API key preference.
        Objects.requireNonNull(newzyApiKey).setOnBindEditTextListener(apiKeyBindEditTextListener);

        // Set listeners on Newzy title preferences.
        Objects.requireNonNull(newzyTitleOne).setOnBindEditTextListener(titleBindEditTextListener);
        Objects.requireNonNull(newzyTitleTwo).setOnBindEditTextListener(titleBindEditTextListener);
        Objects.requireNonNull(newzyTitleThree).setOnBindEditTextListener(titleBindEditTextListener);
        Objects.requireNonNull(newzyTitleFour).setOnBindEditTextListener(titleBindEditTextListener);
        Objects.requireNonNull(newzyTitleFive).setOnBindEditTextListener(titleBindEditTextListener);
        Objects.requireNonNull(newzyTitleSix).setOnBindEditTextListener(titleBindEditTextListener);

        // Set listeners on Newzy search preferences.
        Objects.requireNonNull(newzySearchOne).setOnBindEditTextListener(searchBindEditTextListener);
        Objects.requireNonNull(newzySearchTwo).setOnBindEditTextListener(searchBindEditTextListener);
        Objects.requireNonNull(newzySearchThree).setOnBindEditTextListener(searchBindEditTextListener);
        Objects.requireNonNull(newzySearchFour).setOnBindEditTextListener(searchBindEditTextListener);
        Objects.requireNonNull(newzySearchFive).setOnBindEditTextListener(searchBindEditTextListener);
        Objects.requireNonNull(newzySearchSix).setOnBindEditTextListener(searchBindEditTextListener);

        // Set listeners on Newzy date preferences.
        Objects.requireNonNull(newzyFromDate).setOnBindEditTextListener(dateBindEditTextListener);
        Objects.requireNonNull(newzyToDate).setOnBindEditTextListener(dateBindEditTextListener);

        // Set preference change listeners on Newzy title preferences.
        Objects.requireNonNull(newzyTitleOne).setOnPreferenceChangeListener(this);
        Objects.requireNonNull(newzyTitleTwo).setOnPreferenceChangeListener(this);
        Objects.requireNonNull(newzyTitleThree).setOnPreferenceChangeListener(this);
        Objects.requireNonNull(newzyTitleFour).setOnPreferenceChangeListener(this);
        Objects.requireNonNull(newzyTitleFive).setOnPreferenceChangeListener(this);
        Objects.requireNonNull(newzyTitleSix).setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {

        String preferenceKey = preference.getKey();
        String stringValue = newValue.toString();

        // If a Newzy title preference is changed, update its menu title.
        if (preferenceKey.equals(getString(R.string.settings_newzy_title_one_key))) {
            if (stringValue.isEmpty()) {
                MainActivity.mi1.setVisible(false);

            } else {
                MainActivity.mi1.setTitle(stringValue);
                MainActivity.mi1.setVisible(true);
            }
        } else if (preferenceKey.equals(getString(R.string.settings_newzy_title_two_key))) {
            if (stringValue.isEmpty()) {
                MainActivity.mi2.setVisible(false);

            } else {
                MainActivity.mi2.setTitle(stringValue);
                MainActivity.mi2.setVisible(true);
            }
        } else if (preferenceKey.equals(getString(R.string.settings_newzy_title_three_key))) {
            if (stringValue.isEmpty()) {
                MainActivity.mi3.setVisible(false);

            } else {
                MainActivity.mi3.setTitle(stringValue);
                MainActivity.mi3.setVisible(true);
            }
        } else if (preferenceKey.equals(getString(R.string.settings_newzy_title_four_key))) {
            if (stringValue.isEmpty()) {
                MainActivity.mi4.setVisible(false);

            } else {
                MainActivity.mi4.setTitle(stringValue);
                MainActivity.mi4.setVisible(true);
            }
        } else if (preferenceKey.equals(getString(R.string.settings_newzy_title_five_key))) {
            if (stringValue.isEmpty()) {
                MainActivity.mi5.setVisible(false);

            } else {
                MainActivity.mi5.setTitle(stringValue);
                MainActivity.mi5.setVisible(true);
            }
        } else if (preferenceKey.equals(getString(R.string.settings_newzy_title_six_key))) {
            if (stringValue.isEmpty()) {
                MainActivity.mi6.setVisible(false);

            } else {
                MainActivity.mi6.setTitle(stringValue);
                MainActivity.mi6.setVisible(true);
            }
        }
        return true;
    }
}