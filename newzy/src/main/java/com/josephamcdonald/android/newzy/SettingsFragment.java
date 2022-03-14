/*
 * Copyright (c) 2019-2022. josephamcdonald - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by josephamcdonald
 */

package com.josephamcdonald.android.newzy;

import android.os.Bundle;
import android.text.InputType;

import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
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

        //Find Newzy Preferences.
        ListPreference prefCountry = findPreference(getString(R.string.settings_country_key));
        ListPreference prefTopic = findPreference(getString(R.string.settings_top_headlines_key));

        EditTextPreference prefApiKey = findPreference(getString(R.string.settings_api_key_key));
        EditTextPreference prefFromDate = findPreference(getString(R.string.settings_newzys_from_date_key));
        EditTextPreference prefToDate = findPreference(getString(R.string.settings_newzys_to_date_key));

        EditTextPreference prefTitleOne = findPreference(getString(R.string.settings_menu_title_one_key));
        EditTextPreference prefTitleTwo = findPreference(getString(R.string.settings_menu_title_two_key));
        EditTextPreference prefTitleThree = findPreference(getString(R.string.settings_menu_title_three_key));
        EditTextPreference prefTitleFour = findPreference(getString(R.string.settings_menu_title_four_key));
        EditTextPreference prefTitleFive = findPreference(getString(R.string.settings_menu_title_five_key));
        EditTextPreference prefTitleSix = findPreference(getString(R.string.settings_menu_title_six_key));

        EditTextPreference prefSearchOne = findPreference(getString(R.string.settings_newzy_search_one_key));
        EditTextPreference prefSearchTwo = findPreference(getString(R.string.settings_newzy_search_two_key));
        EditTextPreference prefSearchThree = findPreference(getString(R.string.settings_newzy_search_three_key));
        EditTextPreference prefSearchFour = findPreference(getString(R.string.settings_newzy_search_four_key));
        EditTextPreference prefSearchFive = findPreference(getString(R.string.settings_newzy_search_five_key));
        EditTextPreference prefSearchSix = findPreference(getString(R.string.settings_newzy_search_six_key));

        // Create listener to set input type and hint for News API key.
        EditTextPreference.OnBindEditTextListener apiKeyBindEditTextListener = editText -> {
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
            editText.setHint(R.string.provide_api_key);
            editText.selectAll();
        };

        // Create listener to set input type and hint for Newzy titles.
        EditTextPreference.OnBindEditTextListener titleBindEditTextListener = editText -> {
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
            editText.setHint(R.string.provide_menu_title);
            editText.selectAll();
        };

        // Create listener to set input type and hint for Newzy searches.
        EditTextPreference.OnBindEditTextListener searchBindEditTextListener = editText -> {
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
            editText.setHint(R.string.provide_newzy_search);
            editText.selectAll();
        };

        // Create listener to set input type and hint for Newzy dates.
        EditTextPreference.OnBindEditTextListener dateBindEditTextListener = editText -> {
            editText.setInputType(InputType.TYPE_CLASS_DATETIME);
            editText.setHint(R.string.date_format);
            editText.selectAll();
        };

        // Set listener on News API key preference.
        Objects.requireNonNull(prefApiKey).setOnBindEditTextListener(apiKeyBindEditTextListener);

        // Set listeners on Newzy title preferences.
        Objects.requireNonNull(prefTitleOne).setOnBindEditTextListener(titleBindEditTextListener);
        Objects.requireNonNull(prefTitleTwo).setOnBindEditTextListener(titleBindEditTextListener);
        Objects.requireNonNull(prefTitleThree).setOnBindEditTextListener(titleBindEditTextListener);
        Objects.requireNonNull(prefTitleFour).setOnBindEditTextListener(titleBindEditTextListener);
        Objects.requireNonNull(prefTitleFive).setOnBindEditTextListener(titleBindEditTextListener);
        Objects.requireNonNull(prefTitleSix).setOnBindEditTextListener(titleBindEditTextListener);

        // Set listeners on Newzy search preferences.
        Objects.requireNonNull(prefSearchOne).setOnBindEditTextListener(searchBindEditTextListener);
        Objects.requireNonNull(prefSearchTwo).setOnBindEditTextListener(searchBindEditTextListener);
        Objects.requireNonNull(prefSearchThree).setOnBindEditTextListener(searchBindEditTextListener);
        Objects.requireNonNull(prefSearchFour).setOnBindEditTextListener(searchBindEditTextListener);
        Objects.requireNonNull(prefSearchFive).setOnBindEditTextListener(searchBindEditTextListener);
        Objects.requireNonNull(prefSearchSix).setOnBindEditTextListener(searchBindEditTextListener);

        // Set listeners on Newzy date preferences.
        Objects.requireNonNull(prefFromDate).setOnBindEditTextListener(dateBindEditTextListener);
        Objects.requireNonNull(prefToDate).setOnBindEditTextListener(dateBindEditTextListener);

        // Set listener on Newzy country preference.
        Objects.requireNonNull(prefCountry).setOnPreferenceChangeListener(this);

        // Set listener on Newzy topic preference.
        Objects.requireNonNull(prefTopic).setOnPreferenceChangeListener(this);

        // Set preference change listeners on Newzy title preferences.
        Objects.requireNonNull(prefTitleOne).setOnPreferenceChangeListener(this);
        Objects.requireNonNull(prefTitleTwo).setOnPreferenceChangeListener(this);
        Objects.requireNonNull(prefTitleThree).setOnPreferenceChangeListener(this);
        Objects.requireNonNull(prefTitleFour).setOnPreferenceChangeListener(this);
        Objects.requireNonNull(prefTitleFive).setOnPreferenceChangeListener(this);
        Objects.requireNonNull(prefTitleSix).setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {

        String preferenceKey = preference.getKey();
        String stringValue = newValue.toString();

        // If Country setting is changed, update its menu item.
        // If Country setting is changed, update its menu item.
        if (preferenceKey.equals(getString(R.string.settings_country_key))) {
            if (stringValue.equals(getString(R.string.settings_au_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_au_label));
            } else if (stringValue.equals(getString(R.string.settings_br_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_br_label));
            } else if (stringValue.equals(getString(R.string.settings_ca_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_ca_label));
            } else if (stringValue.equals(getString(R.string.settings_cn_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_cn_label));
            } else if (stringValue.equals(getString(R.string.settings_eg_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_eg_label));
            } else if (stringValue.equals(getString(R.string.settings_fr_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_fr_label));
            } else if (stringValue.equals(getString(R.string.settings_de_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_de_label));
            } else if (stringValue.equals(getString(R.string.settings_gr_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_gr_label));
            } else if (stringValue.equals(getString(R.string.settings_hk_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_hk_label));
            } else if (stringValue.equals(getString(R.string.settings_in_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_in_label));
            } else if (stringValue.equals(getString(R.string.settings_ie_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_ie_label));
            } else if (stringValue.equals(getString(R.string.settings_il_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_il_label));
            } else if (stringValue.equals(getString(R.string.settings_it_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_it_label));
            } else if (stringValue.equals(getString(R.string.settings_jp_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_jp_label));
            } else if (stringValue.equals(getString(R.string.settings_nl_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_nl_label));
            } else if (stringValue.equals(getString(R.string.settings_no_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_no_label));
            } else if (stringValue.equals(getString(R.string.settings_pk_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_pk_label));
            } else if (stringValue.equals(getString(R.string.settings_pe_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_pe_label));
            } else if (stringValue.equals(getString(R.string.settings_ph_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_ph_label));
            } else if (stringValue.equals(getString(R.string.settings_pt_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_pt_label));
            } else if (stringValue.equals(getString(R.string.settings_ro_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_ro_label));
            } else if (stringValue.equals(getString(R.string.settings_ru_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_ru_label));
            } else if (stringValue.equals(getString(R.string.settings_sg_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_sg_label));
            } else if (stringValue.equals(getString(R.string.settings_se_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_se_label));
            } else if (stringValue.equals(getString(R.string.settings_ch_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_ch_label));
            } else if (stringValue.equals(getString(R.string.settings_tw_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_tw_label));
            } else if (stringValue.equals(getString(R.string.settings_ua_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_ua_label));
            } else if (stringValue.equals(getString(R.string.settings_gb_value))) {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_gb_label));
            } else {
                MainActivity.countryItem.setTitle(getString(R.string.country_colon) + getString(R.string.space) + getString(R.string.settings_us_label));
            }

        } else if (preferenceKey.equals(getString(R.string.settings_menu_title_one_key))) {
            // If Newzy Title is changed, update its menu item.
            if (stringValue.isEmpty()) {
                MainActivity.titleItem1.setVisible(false);

            } else {
                MainActivity.titleItem1.setTitle(stringValue);
                MainActivity.titleItem1.setVisible(true);
            }
        } else if (preferenceKey.equals(getString(R.string.settings_menu_title_two_key))) {
            if (stringValue.isEmpty()) {
                MainActivity.titleItem2.setVisible(false);

            } else {
                MainActivity.titleItem2.setTitle(stringValue);
                MainActivity.titleItem2.setVisible(true);
            }
        } else if (preferenceKey.equals(getString(R.string.settings_menu_title_three_key))) {
            if (stringValue.isEmpty()) {
                MainActivity.titleItem3.setVisible(false);

            } else {
                MainActivity.titleItem3.setTitle(stringValue);
                MainActivity.titleItem3.setVisible(true);
            }
        } else if (preferenceKey.equals(getString(R.string.settings_menu_title_four_key))) {
            if (stringValue.isEmpty()) {
                MainActivity.titleItem4.setVisible(false);

            } else {
                MainActivity.titleItem4.setTitle(stringValue);
                MainActivity.titleItem4.setVisible(true);
            }
        } else if (preferenceKey.equals(getString(R.string.settings_menu_title_five_key))) {
            if (stringValue.isEmpty()) {
                MainActivity.titleItem5.setVisible(false);

            } else {
                MainActivity.titleItem5.setTitle(stringValue);
                MainActivity.titleItem5.setVisible(true);
            }
        } else if (preferenceKey.equals(getString(R.string.settings_menu_title_six_key))) {
            if (stringValue.isEmpty()) {
                MainActivity.titleItem6.setVisible(false);

            } else {
                MainActivity.titleItem6.setTitle(stringValue);
                MainActivity.titleItem6.setVisible(true);
            }

        } else {
            // A Topic setting changed, update its menu item.
            if (stringValue.equals(getString(R.string.settings_world_value))) {
                MainActivity.topicItem.setTitle(getString(R.string.top) + getString(R.string.space) + getString(R.string.settings_world_label) + getString(R.string.space) + getString(R.string.news));
                MainActivity.topicItem.setIcon(R.drawable.ic_world);
            } else if (stringValue.equals(getString(R.string.settings_nation_value))) {
                MainActivity.topicItem.setTitle(getString(R.string.top) + getString(R.string.space) + getString(R.string.settings_nation_label) + getString(R.string.space) + getString(R.string.news));
                MainActivity.topicItem.setIcon(R.drawable.ic_national);
            } else if (stringValue.equals(getString(R.string.settings_business_value))) {
                MainActivity.topicItem.setTitle(getString(R.string.top) + getString(R.string.space) + getString(R.string.settings_business_label) + getString(R.string.space) + getString(R.string.news));
                MainActivity.topicItem.setIcon(R.drawable.ic_business);
            } else if (stringValue.equals(getString(R.string.settings_technology_value))) {
                MainActivity.topicItem.setTitle(getString(R.string.top) + getString(R.string.space) + getString(R.string.settings_technology_label) + getString(R.string.space) + getString(R.string.news));
                MainActivity.topicItem.setIcon(R.drawable.ic_technology);
            } else if (stringValue.equals(getString(R.string.settings_entertainment_value))) {
                MainActivity.topicItem.setTitle(getString(R.string.top) + getString(R.string.space) + getString(R.string.settings_entertainment_label) + getString(R.string.space) + getString(R.string.news));
                MainActivity.topicItem.setIcon(R.drawable.ic_entertainment);
            } else if (stringValue.equals(getString(R.string.settings_sports_value))) {
                MainActivity.topicItem.setTitle(getString(R.string.top) + getString(R.string.space) + getString(R.string.settings_sports_label) + getString(R.string.space) + getString(R.string.news));
                MainActivity.topicItem.setIcon(R.drawable.ic_sports);
            } else if (stringValue.equals(getString(R.string.settings_science_value))) {
                MainActivity.topicItem.setTitle(getString(R.string.top) + getString(R.string.space) + getString(R.string.settings_science_label) + getString(R.string.space) + getString(R.string.news));
                MainActivity.topicItem.setIcon(R.drawable.ic_science);
            } else if (stringValue.equals(getString(R.string.settings_health_value))) {
                MainActivity.topicItem.setTitle(getString(R.string.top) + getString(R.string.space) + getString(R.string.settings_health_label) + getString(R.string.space) + getString(R.string.news));
                MainActivity.topicItem.setIcon(R.drawable.ic_health);
            } else {
                MainActivity.topicItem.setTitle(getString(R.string.top) + getString(R.string.space) + getString(R.string.settings_breaking_news_label));
                MainActivity.topicItem.setIcon(R.drawable.ic_breaking);
            }
        }
        return true;
    }
}