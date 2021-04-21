/*
 * Copyright (c) 2021. josephamcdonald - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by josephamcdonald
 */

package com.josephamcdonald.android.newzy;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;

import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Initiate Newzys query string.
    static String newzysQuery = "";

    //Declare Newzys search switch.
    static boolean newzysSearch;

    // Declare Navigation Drawer menu items.
    static MenuItem country;
    static MenuItem topic;
    static MenuItem mi1;
    static MenuItem mi2;
    static MenuItem mi3;
    static MenuItem mi4;
    static MenuItem mi5;
    static MenuItem mi6;

    // Declare Newzys title.
    private String newzysTitle;

    // Declare Newzys topic.
    private String newzysTopic;

    // Declare Navigation Drawer.
    private DrawerLayout drawer;

    // Declare Shared Preferences.
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find and setup the Toolbar.
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        // Find and setup the navigation drawer and open/close toggle.
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, tb, R.string.open_navigation_drawer, R.string.close_navigation_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Find NavigationView and set its Listener.
        NavigationView nv = findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);

        // Get current preferences.
        sp = PreferenceManager.getDefaultSharedPreferences(this);

        // Set menu items with current preferences.
        setMenuItems(nv.getMenu());

        // Get "Show Support Screen" switch preference.
        boolean showSupport = sp.getBoolean(getString(R.string.settings_show_support_key), true);

        // Launch startup fragment.
        if (showSupport) {
            // Show Support screen on startup.
            newzysTitle = getString(R.string.support);
            launchFragment(R.id.support);

        } else {
            newzysSearch = false;
            launchFragment(R.id.newzys_topic);
        }
    }

    private void setMenuItems(Menu menu) {
        // Find the Menu Items.
        country = menu.findItem(R.id.newzys_country);
        topic = menu.findItem(R.id.newzys_topic);
        mi1 = menu.findItem(R.id.newzys_one);
        mi2 = menu.findItem(R.id.newzys_two);
        mi3 = menu.findItem(R.id.newzys_three);
        mi4 = menu.findItem(R.id.newzys_four);
        mi5 = menu.findItem(R.id.newzys_five);
        mi6 = menu.findItem(R.id.newzys_six);

        String newzysCountry = sp.getString(getString(R.string.settings_country_key),
                getString(R.string.settings_country_default));

        if (Objects.requireNonNull(newzysCountry).equals(getString(R.string.settings_au_value))) {
            country.setTitle(getString(R.string.settings_au_label) + getString(R.string.space) + getString(R.string.newzys));
        } else if (newzysCountry.equals(getString(R.string.settings_br_value))) {
            country.setTitle(getString(R.string.settings_br_label) + getString(R.string.space) + getString(R.string.newzys));
        } else if (newzysCountry.equals(getString(R.string.settings_ca_value))) {
            country.setTitle(getString(R.string.settings_ca_label) + getString(R.string.space) + getString(R.string.newzys));
        } else if (newzysCountry.equals(getString(R.string.settings_cn_value))) {
            country.setTitle(getString(R.string.settings_cn_label) + getString(R.string.space) + getString(R.string.newzys));
        } else if (newzysCountry.equals(getString(R.string.settings_eg_value))) {
            country.setTitle(getString(R.string.settings_eg_label) + getString(R.string.space) + getString(R.string.newzys));
        } else if (newzysCountry.equals(getString(R.string.settings_fr_value))) {
            country.setTitle(getString(R.string.settings_fr_label) + getString(R.string.space) + getString(R.string.newzys));
        } else if (newzysCountry.equals(getString(R.string.settings_de_value))) {
            country.setTitle(getString(R.string.settings_de_label) + getString(R.string.space) + getString(R.string.newzys));
        } else if (newzysCountry.equals(getString(R.string.settings_gr_value))) {
            country.setTitle(getString(R.string.settings_gr_label) + getString(R.string.space) + getString(R.string.newzys));
        } else if (newzysCountry.equals(getString(R.string.settings_hk_value))) {
            country.setTitle(getString(R.string.settings_hk_label) + getString(R.string.space) + getString(R.string.newzys));
        } else if (newzysCountry.equals(getString(R.string.settings_in_value))) {
            country.setTitle(getString(R.string.settings_in_label) + getString(R.string.space) + getString(R.string.newzys));
        } else if (newzysCountry.equals(getString(R.string.settings_ie_value))) {
            country.setTitle(getString(R.string.settings_ie_label) + getString(R.string.space) + getString(R.string.newzys));
        } else if (newzysCountry.equals(getString(R.string.settings_il_value))) {
            country.setTitle(getString(R.string.settings_il_label) + getString(R.string.space) + getString(R.string.newzys));
        } else if (newzysCountry.equals(getString(R.string.settings_it_value))) {
            country.setTitle(getString(R.string.settings_it_label) + getString(R.string.space) + getString(R.string.newzys));
        } else if (newzysCountry.equals(getString(R.string.settings_jp_value))) {
            country.setTitle(getString(R.string.settings_jp_label) + getString(R.string.space) + getString(R.string.newzys));
        } else if (newzysCountry.equals(getString(R.string.settings_nl_value))) {
            country.setTitle(getString(R.string.settings_nl_label) + getString(R.string.space) + getString(R.string.newzys));
        } else if (newzysCountry.equals(getString(R.string.settings_no_value))) {
            country.setTitle(getString(R.string.settings_no_label) + getString(R.string.space) + getString(R.string.newzys));
        } else if (newzysCountry.equals(getString(R.string.settings_pk_value))) {
            country.setTitle(getString(R.string.settings_pk_label) + getString(R.string.space) + getString(R.string.newzys));
        } else if (newzysCountry.equals(getString(R.string.settings_pe_value))) {
            country.setTitle(getString(R.string.settings_pe_label) + getString(R.string.space) + getString(R.string.newzys));
        } else if (newzysCountry.equals(getString(R.string.settings_ph_value))) {
            country.setTitle(getString(R.string.settings_ph_label) + getString(R.string.space) + getString(R.string.newzys));
        } else if (newzysCountry.equals(getString(R.string.settings_pt_value))) {
            country.setTitle(getString(R.string.settings_pt_label) + getString(R.string.space) + getString(R.string.newzys));
        } else if (newzysCountry.equals(getString(R.string.settings_ro_value))) {
            country.setTitle(getString(R.string.settings_ro_label) + getString(R.string.space) + getString(R.string.newzys));
        } else if (newzysCountry.equals(getString(R.string.settings_ru_value))) {
            country.setTitle(getString(R.string.settings_ru_label) + getString(R.string.space) + getString(R.string.newzys));
        } else if (newzysCountry.equals(getString(R.string.settings_sg_value))) {
            country.setTitle(getString(R.string.settings_sg_label) + getString(R.string.space) + getString(R.string.newzys));
        } else if (newzysCountry.equals(getString(R.string.settings_se_value))) {
            country.setTitle(getString(R.string.settings_se_label) + getString(R.string.space) + getString(R.string.newzys));
        } else if (newzysCountry.equals(getString(R.string.settings_ch_value))) {
            country.setTitle(getString(R.string.settings_ch_label) + getString(R.string.space) + getString(R.string.newzys));
        } else if (newzysCountry.equals(getString(R.string.settings_tw_value))) {
            country.setTitle(getString(R.string.settings_tw_label) + getString(R.string.space) + getString(R.string.newzys));
        } else if (newzysCountry.equals(getString(R.string.settings_ua_value))) {
            country.setTitle(getString(R.string.settings_ua_label) + getString(R.string.space) + getString(R.string.newzys));
        } else if (newzysCountry.equals(getString(R.string.settings_gb_value))) {
            country.setTitle(getString(R.string.settings_gb_label) + getString(R.string.space) + getString(R.string.newzys));
        } else {
            country.setTitle(getString(R.string.settings_us_label) + getString(R.string.space) + getString(R.string.newzys));
        }

        newzysTopic = sp.getString(getString(R.string.settings_topic_key),
                getString(R.string.settings_topic_default));
        Drawable newzysTopicIcon;

        if (Objects.requireNonNull(newzysTopic).equals(getString(R.string.settings_world_value))) {
            newzysTitle = getString(R.string.settings_world_label) + getString(R.string.space) + getString(R.string.news);
            newzysTopicIcon = ContextCompat.getDrawable(this, R.drawable.ic_world);

        } else if (newzysTopic.equals(getString(R.string.settings_nation_value))) {
            newzysTitle = getString(R.string.settings_nation_label) + getString(R.string.space) + getString(R.string.news);
            newzysTopicIcon = ContextCompat.getDrawable(this, R.drawable.ic_national);

        } else if (newzysTopic.equals(getString(R.string.settings_business_value))) {
            newzysTitle = getString(R.string.settings_business_label) + getString(R.string.space) + getString(R.string.news);
            newzysTopicIcon = ContextCompat.getDrawable(this, R.drawable.ic_business);

        } else if (newzysTopic.equals(getString(R.string.settings_technology_value))) {
            newzysTitle = getString(R.string.settings_technology_label) + getString(R.string.space) + getString(R.string.news);
            newzysTopicIcon = ContextCompat.getDrawable(this, R.drawable.ic_technology);

        } else if (newzysTopic.equals(getString(R.string.settings_entertainment_value))) {
            newzysTitle = getString(R.string.settings_entertainment_label) + getString(R.string.space) + getString(R.string.news);
            newzysTopicIcon = ContextCompat.getDrawable(this, R.drawable.ic_entertainment);

        } else if (newzysTopic.equals(getString(R.string.settings_sports_value))) {
            newzysTitle = getString(R.string.settings_sports_label) + getString(R.string.space) + getString(R.string.news);
            newzysTopicIcon = ContextCompat.getDrawable(this, R.drawable.ic_sports);

        } else if (newzysTopic.equals(getString(R.string.settings_science_value))) {
            newzysTitle = getString(R.string.settings_science_label) + getString(R.string.space) + getString(R.string.news);
            newzysTopicIcon = ContextCompat.getDrawable(this, R.drawable.ic_science);

        } else if (newzysTopic.equals(getString(R.string.settings_health_value))) {
            newzysTitle = getString(R.string.settings_health_label) + getString(R.string.space) + getString(R.string.news);
            newzysTopicIcon = ContextCompat.getDrawable(this, R.drawable.ic_health);

        } else {
            newzysTitle = getString(R.string.settings_breaking_news_label);
            newzysTopicIcon = ContextCompat.getDrawable(this, R.drawable.ic_breaking);
        }

        topic.setTitle(newzysTitle);
        topic.setIcon(newzysTopicIcon);

        String title1 = sp.getString(getString(R.string.settings_newzy_title_one_key),
                getString(R.string.settings_newzy_title_one_default));
        String title2 = sp.getString(getString(R.string.settings_newzy_title_two_key),
                getString(R.string.settings_newzy_title_two_default));
        String title3 = sp.getString(getString(R.string.settings_newzy_title_three_key),
                getString(R.string.settings_newzy_title_three_default));
        String title4 = sp.getString(getString(R.string.settings_newzy_title_four_key),
                getString(R.string.settings_newzy_title_four_default));
        String title5 = sp.getString(getString(R.string.settings_newzy_title_five_key),
                getString(R.string.settings_newzy_title_five_default));
        String title6 = sp.getString(getString(R.string.settings_newzy_title_six_key),
                getString(R.string.settings_newzy_title_six_default));

        if (Objects.requireNonNull(title1).isEmpty()) {
            mi1.setVisible(false);
        } else {
            mi1.setTitle(title1);
            mi1.setVisible(true);
        }
        if (Objects.requireNonNull(title2).isEmpty()) {
            mi2.setVisible(false);
        } else {
            mi2.setTitle(title2);
            mi2.setVisible(true);
        }
        if (Objects.requireNonNull(title3).isEmpty()) {
            mi3.setVisible(false);
        } else {
            mi3.setTitle(title3);
            mi3.setVisible(true);
        }
        if (Objects.requireNonNull(title4).isEmpty()) {
            mi4.setVisible(false);
        } else {
            mi4.setTitle(title4);
            mi4.setVisible(true);
        }
        if (Objects.requireNonNull(title5).isEmpty()) {
            mi5.setVisible(false);
        } else {
            mi5.setTitle(title5);
            mi5.setVisible(true);
        }
        if (Objects.requireNonNull(title6).isEmpty()) {
            mi6.setVisible(false);
        } else {
            mi6.setTitle(title6);
            mi6.setVisible(true);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.newzys_topic) {
            newzysSearch = false;
            newzysTopic = sp.getString(getString(R.string.settings_topic_key),
                    getString(R.string.settings_topic_default));

            if (Objects.requireNonNull(newzysTopic).equals(getString(R.string.settings_world_value))) {
                newzysTitle = getString(R.string.settings_world_label) + getString(R.string.space) + getString(R.string.news);
            } else if (newzysTopic.equals(getString(R.string.settings_nation_value))) {
                newzysTitle = getString(R.string.settings_nation_label) + getString(R.string.space) + getString(R.string.news);
            } else if (newzysTopic.equals(getString(R.string.settings_business_value))) {
                newzysTitle = getString(R.string.settings_business_label) + getString(R.string.space) + getString(R.string.news);
            } else if (newzysTopic.equals(getString(R.string.settings_technology_value))) {
                newzysTitle = getString(R.string.settings_technology_label) + getString(R.string.space) + getString(R.string.news);
            } else if (newzysTopic.equals(getString(R.string.settings_entertainment_value))) {
                newzysTitle = getString(R.string.settings_entertainment_label) + getString(R.string.space) + getString(R.string.news);
            } else if (newzysTopic.equals(getString(R.string.settings_sports_value))) {
                newzysTitle = getString(R.string.settings_sports_label) + getString(R.string.space) + getString(R.string.news);
            } else if (newzysTopic.equals(getString(R.string.settings_science_value))) {
                newzysTitle = getString(R.string.settings_science_label) + getString(R.string.space) + getString(R.string.news);
            } else if (newzysTopic.equals(getString(R.string.settings_health_value))) {
                newzysTitle = getString(R.string.settings_health_label) + getString(R.string.space) + getString(R.string.news);
            } else {
                newzysTitle = getString(R.string.settings_breaking_news_label);
            }

        } else if (item.getItemId() == R.id.newzys_one) {
            newzysSearch = true;
            newzysTitle = sp.getString(
                    getString(R.string.settings_newzy_title_one_key),
                    getString(R.string.settings_newzy_title_one_default));
            newzysQuery = sp.getString(
                    getString(R.string.settings_newzy_search_one_key),
                    getString(R.string.settings_newzy_search_one_default));

        } else if (item.getItemId() == R.id.newzys_two) {
            newzysSearch = true;
            newzysTitle = sp.getString(
                    getString(R.string.settings_newzy_title_two_key),
                    getString(R.string.settings_newzy_title_two_default));
            newzysQuery = sp.getString(
                    getString(R.string.settings_newzy_search_two_key),
                    getString(R.string.settings_newzy_search_two_default));

        } else if (item.getItemId() == R.id.newzys_three) {
            newzysSearch = true;
            newzysTitle = sp.getString(
                    getString(R.string.settings_newzy_title_three_key),
                    getString(R.string.settings_newzy_title_three_default));
            newzysQuery = sp.getString(
                    getString(R.string.settings_newzy_search_three_key),
                    getString(R.string.settings_newzy_search_three_default));

        } else if (item.getItemId() == R.id.newzys_four) {
            newzysSearch = true;
            newzysTitle = sp.getString(
                    getString(R.string.settings_newzy_title_four_key),
                    getString(R.string.settings_newzy_title_four_default));
            newzysQuery = sp.getString(
                    getString(R.string.settings_newzy_search_four_key),
                    getString(R.string.settings_newzy_search_four_default));

        } else if (item.getItemId() == R.id.newzys_five) {
            newzysSearch = true;
            newzysTitle = sp.getString(
                    getString(R.string.settings_newzy_title_five_key),
                    getString(R.string.settings_newzy_title_five_default));
            newzysQuery = sp.getString(
                    getString(R.string.settings_newzy_search_five_key),
                    getString(R.string.settings_newzy_search_five_default));

        } else if (item.getItemId() == R.id.newzys_six) {
            newzysSearch = true;
            newzysTitle = sp.getString(
                    getString(R.string.settings_newzy_title_six_key),
                    getString(R.string.settings_newzy_title_six_default));
            newzysQuery = sp.getString(
                    getString(R.string.settings_newzy_search_six_key),
                    getString(R.string.settings_newzy_search_six_default));

        } else if (item.getItemId() == R.id.settings) {
            newzysTitle = getString(R.string.settings);

        } else {
            newzysTitle = getString(R.string.support);
        }
        launchFragment(item.getItemId());

        // Close the drawer after selecting a destination.
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void launchFragment(int itemId) {
        // Set current title.
        setTitle(newzysTitle);

        // Start fragment transaction.
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        switch (itemId) {
            case (R.id.settings):
                ft.replace(R.id.frame_layout_content, new SettingsFragment());
                break;
            case (R.id.support):
                ft.replace(R.id.frame_layout_content, new SupportFragment());
                break;
            default:
                ft.replace(R.id.frame_layout_content, new NewzyFragment());
        }
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        // If destination drawer is open, then close it.
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}