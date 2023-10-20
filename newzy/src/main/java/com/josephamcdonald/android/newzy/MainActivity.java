/*
 * Copyright (c) 2019-2023. josephamcdonald - All Rights Reserved
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

import androidx.activity.OnBackPressedCallback;
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
    static MenuItem countryItem;
    static MenuItem topicItem;
    static MenuItem titleItem1;
    static MenuItem titleItem2;
    static MenuItem titleItem3;
    static MenuItem titleItem4;
    static MenuItem titleItem5;
    static MenuItem titleItem6;
    static MenuItem titleItem7;
    static MenuItem titleItem8;

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
            launchFragment(R.id.newzys_top_headlines);
        }

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            // If destination drawer is open, then close it.
            @Override
            public void handleOnBackPressed() {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    finish();
                }
            }
        });
    }

    private void setMenuItems(Menu menu) {
        // Find the Menu Items.
        countryItem = menu.findItem(R.id.newzys_country);
        topicItem = menu.findItem(R.id.newzys_top_headlines);
        titleItem1 = menu.findItem(R.id.newzys_one);
        titleItem2 = menu.findItem(R.id.newzys_two);
        titleItem3 = menu.findItem(R.id.newzys_three);
        titleItem4 = menu.findItem(R.id.newzys_four);
        titleItem5 = menu.findItem(R.id.newzys_five);
        titleItem6 = menu.findItem(R.id.newzys_six);
        titleItem7 = menu.findItem(R.id.newzys_seven);
        titleItem8 = menu.findItem(R.id.newzys_eight);

        String newzysCountry = sp.getString(getString(R.string.settings_country_key),
                getString(R.string.settings_country_default));

        if (Objects.requireNonNull(newzysCountry).equals(getString(R.string.settings_au_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_au_label));
        } else if (newzysCountry.equals(getString(R.string.settings_br_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_br_label));
        } else if (newzysCountry.equals(getString(R.string.settings_ca_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_ca_label));
        } else if (newzysCountry.equals(getString(R.string.settings_cn_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_cn_label));
        } else if (newzysCountry.equals(getString(R.string.settings_eg_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_eg_label));
        } else if (newzysCountry.equals(getString(R.string.settings_fr_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_fr_label));
        } else if (newzysCountry.equals(getString(R.string.settings_de_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_de_label));
        } else if (newzysCountry.equals(getString(R.string.settings_gr_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_gr_label));
        } else if (newzysCountry.equals(getString(R.string.settings_hk_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_hk_label));
        } else if (newzysCountry.equals(getString(R.string.settings_in_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_in_label));
        } else if (newzysCountry.equals(getString(R.string.settings_ie_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_ie_label));
        } else if (newzysCountry.equals(getString(R.string.settings_il_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_il_label));
        } else if (newzysCountry.equals(getString(R.string.settings_it_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_it_label));
        } else if (newzysCountry.equals(getString(R.string.settings_jp_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_jp_label));
        } else if (newzysCountry.equals(getString(R.string.settings_nl_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_nl_label));
        } else if (newzysCountry.equals(getString(R.string.settings_no_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_no_label));
        } else if (newzysCountry.equals(getString(R.string.settings_pk_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_pk_label));
        } else if (newzysCountry.equals(getString(R.string.settings_pe_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_pe_label));
        } else if (newzysCountry.equals(getString(R.string.settings_ph_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_ph_label));
        } else if (newzysCountry.equals(getString(R.string.settings_pt_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_pt_label));
        } else if (newzysCountry.equals(getString(R.string.settings_ro_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_ro_label));
        } else if (newzysCountry.equals(getString(R.string.settings_ru_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_ru_label));
        } else if (newzysCountry.equals(getString(R.string.settings_sg_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_sg_label));
        } else if (newzysCountry.equals(getString(R.string.settings_se_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_se_label));
        } else if (newzysCountry.equals(getString(R.string.settings_ch_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_ch_label));
        } else if (newzysCountry.equals(getString(R.string.settings_tw_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_tw_label));
        } else if (newzysCountry.equals(getString(R.string.settings_ua_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_ua_label));
        } else if (newzysCountry.equals(getString(R.string.settings_gb_value))) {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_gb_label));
        } else {
            countryItem.setTitle(getString(R.string.newzys_from) + getString(R.string.space) + getString(R.string.settings_us_label));
        }

        newzysTopic = sp.getString(getString(R.string.settings_top_headlines_key),
                getString(R.string.settings_top_headlines_default));
        Drawable newzysTopicIcon;

        if (Objects.requireNonNull(newzysTopic).equals(getString(R.string.settings_world_value))) {
            newzysTitle = getString(R.string.top) + getString(R.string.space) + getString(R.string.settings_world_label) + getString(R.string.space) + getString(R.string.newzys);
            newzysTopicIcon = ContextCompat.getDrawable(this, R.drawable.ic_world);

        } else if (newzysTopic.equals(getString(R.string.settings_nation_value))) {
            newzysTitle = getString(R.string.top) + getString(R.string.space) + getString(R.string.settings_nation_label) + getString(R.string.space) + getString(R.string.newzys);
            newzysTopicIcon = ContextCompat.getDrawable(this, R.drawable.ic_national);

        } else if (newzysTopic.equals(getString(R.string.settings_business_value))) {
            newzysTitle = getString(R.string.top) + getString(R.string.space) + getString(R.string.settings_business_label) + getString(R.string.space) + getString(R.string.newzys);
            newzysTopicIcon = ContextCompat.getDrawable(this, R.drawable.ic_business);

        } else if (newzysTopic.equals(getString(R.string.settings_technology_value))) {
            newzysTitle = getString(R.string.top) + getString(R.string.space) + getString(R.string.settings_technology_label) + getString(R.string.space) + getString(R.string.newzys);
            newzysTopicIcon = ContextCompat.getDrawable(this, R.drawable.ic_technology);

        } else if (newzysTopic.equals(getString(R.string.settings_entertainment_value))) {
            newzysTitle = getString(R.string.top) + getString(R.string.space) + getString(R.string.settings_entertainment_label) + getString(R.string.space) + getString(R.string.newzys);
            newzysTopicIcon = ContextCompat.getDrawable(this, R.drawable.ic_entertainment);

        } else if (newzysTopic.equals(getString(R.string.settings_sports_value))) {
            newzysTitle = getString(R.string.top) + getString(R.string.space) + getString(R.string.settings_sports_label) + getString(R.string.space) + getString(R.string.newzys);
            newzysTopicIcon = ContextCompat.getDrawable(this, R.drawable.ic_sports);

        } else if (newzysTopic.equals(getString(R.string.settings_science_value))) {
            newzysTitle = getString(R.string.top) + getString(R.string.space) + getString(R.string.settings_science_label) + getString(R.string.space) + getString(R.string.newzys);
            newzysTopicIcon = ContextCompat.getDrawable(this, R.drawable.ic_science);

        } else if (newzysTopic.equals(getString(R.string.settings_health_value))) {
            newzysTitle = getString(R.string.top) + getString(R.string.space) + getString(R.string.settings_health_label) + getString(R.string.space) + getString(R.string.newzys);
            newzysTopicIcon = ContextCompat.getDrawable(this, R.drawable.ic_health);

        } else {
            newzysTitle = getString(R.string.top) + getString(R.string.space) + getString(R.string.settings_general_label) + getString(R.string.space) + getString(R.string.newzys);
            newzysTopicIcon = ContextCompat.getDrawable(this, R.drawable.ic_breaking);
        }

        topicItem.setTitle(newzysTitle);
        topicItem.setIcon(newzysTopicIcon);

        String title1 = sp.getString(getString(R.string.settings_menu_title_one_key),
                getString(R.string.settings_menu_title_one_default));
        String title2 = sp.getString(getString(R.string.settings_menu_title_two_key),
                getString(R.string.settings_menu_title_two_default));
        String title3 = sp.getString(getString(R.string.settings_menu_title_three_key),
                getString(R.string.settings_menu_title_three_default));
        String title4 = sp.getString(getString(R.string.settings_menu_title_four_key),
                getString(R.string.settings_menu_title_four_default));
        String title5 = sp.getString(getString(R.string.settings_menu_title_five_key),
                getString(R.string.settings_menu_title_five_default));
        String title6 = sp.getString(getString(R.string.settings_menu_title_six_key),
                getString(R.string.settings_menu_title_six_default));
        String title7 = sp.getString(getString(R.string.settings_menu_title_seven_key),
                getString(R.string.settings_menu_title_seven_default));
        String title8 = sp.getString(getString(R.string.settings_menu_title_eight_key),
                getString(R.string.settings_menu_title_eight_default));

        if (Objects.requireNonNull(title1).isEmpty()) {
            titleItem1.setVisible(false);
        } else {
            titleItem1.setTitle(title1);
            titleItem1.setVisible(true);
        }
        if (Objects.requireNonNull(title2).isEmpty()) {
            titleItem2.setVisible(false);
        } else {
            titleItem2.setTitle(title2);
            titleItem2.setVisible(true);
        }
        if (Objects.requireNonNull(title3).isEmpty()) {
            titleItem3.setVisible(false);
        } else {
            titleItem3.setTitle(title3);
            titleItem3.setVisible(true);
        }
        if (Objects.requireNonNull(title4).isEmpty()) {
            titleItem4.setVisible(false);
        } else {
            titleItem4.setTitle(title4);
            titleItem4.setVisible(true);
        }
        if (Objects.requireNonNull(title5).isEmpty()) {
            titleItem5.setVisible(false);
        } else {
            titleItem5.setTitle(title5);
            titleItem5.setVisible(true);
        }
        if (Objects.requireNonNull(title6).isEmpty()) {
            titleItem6.setVisible(false);
        } else {
            titleItem6.setTitle(title6);
            titleItem6.setVisible(true);
        }
        if (Objects.requireNonNull(title7).isEmpty()) {
            titleItem7.setVisible(false);
        } else {
            titleItem7.setTitle(title7);
            titleItem7.setVisible(true);
        }
        if (Objects.requireNonNull(title8).isEmpty()) {
            titleItem8.setVisible(false);
        } else {
            titleItem8.setTitle(title8);
            titleItem8.setVisible(true);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.newzys_top_headlines) {
            newzysSearch = false;
            newzysTopic = sp.getString(getString(R.string.settings_top_headlines_key),
                    getString(R.string.settings_top_headlines_default));

            if (Objects.requireNonNull(newzysTopic).equals(getString(R.string.settings_world_value))) {
                newzysTitle = getString(R.string.top) + getString(R.string.space) + getString(R.string.settings_world_label) + getString(R.string.space) + getString(R.string.newzys);
            } else if (newzysTopic.equals(getString(R.string.settings_nation_value))) {
                newzysTitle = getString(R.string.top) + getString(R.string.space) + getString(R.string.settings_nation_label) + getString(R.string.space) + getString(R.string.newzys);
            } else if (newzysTopic.equals(getString(R.string.settings_business_value))) {
                newzysTitle = getString(R.string.top) + getString(R.string.space) + getString(R.string.settings_business_label) + getString(R.string.space) + getString(R.string.newzys);
            } else if (newzysTopic.equals(getString(R.string.settings_technology_value))) {
                newzysTitle = getString(R.string.top) + getString(R.string.space) + getString(R.string.settings_technology_label) + getString(R.string.space) + getString(R.string.newzys);
            } else if (newzysTopic.equals(getString(R.string.settings_entertainment_value))) {
                newzysTitle = getString(R.string.top) + getString(R.string.space) + getString(R.string.settings_entertainment_label) + getString(R.string.space) + getString(R.string.newzys);
            } else if (newzysTopic.equals(getString(R.string.settings_sports_value))) {
                newzysTitle = getString(R.string.top) + getString(R.string.space) + getString(R.string.settings_sports_label) + getString(R.string.space) + getString(R.string.newzys);
            } else if (newzysTopic.equals(getString(R.string.settings_science_value))) {
                newzysTitle = getString(R.string.top) + getString(R.string.space) + getString(R.string.settings_science_label) + getString(R.string.space) + getString(R.string.newzys);
            } else if (newzysTopic.equals(getString(R.string.settings_health_value))) {
                newzysTitle = getString(R.string.top) + getString(R.string.space) + getString(R.string.settings_health_label) + getString(R.string.space) + getString(R.string.newzys);
            } else {
                newzysTitle = getString(R.string.top) + getString(R.string.space) + getString(R.string.settings_general_label) + getString(R.string.space) + getString(R.string.newzys);
            }

        } else if (item.getItemId() == R.id.newzys_one) {
            newzysSearch = true;
            newzysTitle = sp.getString(
                    getString(R.string.settings_menu_title_one_key),
                    getString(R.string.settings_menu_title_one_default));
            newzysQuery = sp.getString(
                    getString(R.string.settings_newzy_search_one_key),
                    getString(R.string.settings_newzy_search_one_default));

        } else if (item.getItemId() == R.id.newzys_two) {
            newzysSearch = true;
            newzysTitle = sp.getString(
                    getString(R.string.settings_menu_title_two_key),
                    getString(R.string.settings_menu_title_two_default));
            newzysQuery = sp.getString(
                    getString(R.string.settings_newzy_search_two_key),
                    getString(R.string.settings_newzy_search_two_default));

        } else if (item.getItemId() == R.id.newzys_three) {
            newzysSearch = true;
            newzysTitle = sp.getString(
                    getString(R.string.settings_menu_title_three_key),
                    getString(R.string.settings_menu_title_three_default));
            newzysQuery = sp.getString(
                    getString(R.string.settings_newzy_search_three_key),
                    getString(R.string.settings_newzy_search_three_default));

        } else if (item.getItemId() == R.id.newzys_four) {
            newzysSearch = true;
            newzysTitle = sp.getString(
                    getString(R.string.settings_menu_title_four_key),
                    getString(R.string.settings_menu_title_four_default));
            newzysQuery = sp.getString(
                    getString(R.string.settings_newzy_search_four_key),
                    getString(R.string.settings_newzy_search_four_default));

        } else if (item.getItemId() == R.id.newzys_five) {
            newzysSearch = true;
            newzysTitle = sp.getString(
                    getString(R.string.settings_menu_title_five_key),
                    getString(R.string.settings_menu_title_five_default));
            newzysQuery = sp.getString(
                    getString(R.string.settings_newzy_search_five_key),
                    getString(R.string.settings_newzy_search_five_default));

        } else if (item.getItemId() == R.id.newzys_six) {
            newzysSearch = true;
            newzysTitle = sp.getString(
                    getString(R.string.settings_menu_title_six_key),
                    getString(R.string.settings_menu_title_six_default));
            newzysQuery = sp.getString(
                    getString(R.string.settings_newzy_search_six_key),
                    getString(R.string.settings_newzy_search_six_default));

        } else if (item.getItemId() == R.id.newzys_seven) {
            newzysSearch = true;
            newzysTitle = sp.getString(
                    getString(R.string.settings_menu_title_seven_key),
                    getString(R.string.settings_menu_title_seven_default));
            newzysQuery = sp.getString(
                    getString(R.string.settings_newzy_search_seven_key),
                    getString(R.string.settings_newzy_search_seven_default));

        } else if (item.getItemId() == R.id.newzys_eight) {
            newzysSearch = true;
            newzysTitle = sp.getString(
                    getString(R.string.settings_menu_title_eight_key),
                    getString(R.string.settings_menu_title_eight_default));
            newzysQuery = sp.getString(
                    getString(R.string.settings_newzy_search_eight_key),
                    getString(R.string.settings_newzy_search_eight_default));

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
}