/*
 * Copyright (c) 2020. josephamcdonald - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by josephamcdonald
 */

package com.josephamcdonald.android.newzy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.preference.PreferenceManager;
import androidx.annotation.NonNull;

import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Declare Newzys query string.
    static String newzysQuery;

    // Declare Navigation Drawer menu items.
    static MenuItem mi1;
    static MenuItem mi2;
    static MenuItem mi3;
    static MenuItem mi4;
    static MenuItem mi5;
    static MenuItem mi6;

    // Declare Newzys title.
    static String newzysTitle;

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
                this, drawer, tb, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Find NavigationView and set its Listener.
        NavigationView nv = findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);

        // Get current preferences.
        sp = PreferenceManager.getDefaultSharedPreferences(this);

        // Set menu with current Newzy titles.
        setNewzyTitles(nv.getMenu());

        // Assign startup Newzys query.
        newzysQuery = sp.getString(getString(R.string.settings_country_key),
                getString(R.string.settings_country_default));

        // Get "Show Support on startup" switch preference.
        boolean showSupport = sp.getBoolean(getString(R.string.settings_show_support_key), true);

        // Launch startup fragment.
        if (showSupport) {
            // Show Support screen on startup.
            newzysTitle = getString(R.string.support);
            launchFragment(R.id.support);

        } else {
            // Show Headlines on startup.
            newzysTitle = getString(R.string.headlines);
            launchFragment(R.id.headlines);
        }
    }

    private void setNewzyTitles(Menu menu) {

        // Find the Menu Items.
        mi1 = menu.findItem(R.id.newzys_one);
        mi2 = menu.findItem(R.id.newzys_two);
        mi3 = menu.findItem(R.id.newzys_three);
        mi4 = menu.findItem(R.id.newzys_four);
        mi5 = menu.findItem(R.id.newzys_five);
        mi6 = menu.findItem(R.id.newzys_six);

        // Assign current preference titles.
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.headlines:
                newzysTitle = getString(R.string.headlines);
                newzysQuery = sp.getString(
                        getString(R.string.settings_country_key),
                        getString(R.string.settings_country_default));
                break;

            case R.id.newzys_one:
                newzysTitle = sp.getString(
                        getString(R.string.settings_newzy_title_one_key),
                        getString(R.string.settings_newzy_title_one_default));
                newzysQuery = sp.getString(
                        getString(R.string.settings_newzy_search_one_key),
                        getString(R.string.settings_newzy_search_one_default));
                break;

            case R.id.newzys_two:
                newzysTitle = sp.getString(
                        getString(R.string.settings_newzy_title_two_key),
                        getString(R.string.settings_newzy_title_two_default));
                newzysQuery = sp.getString(
                        getString(R.string.settings_newzy_search_two_key),
                        getString(R.string.settings_newzy_search_two_default));
                break;

            case R.id.newzys_three:
                newzysTitle = sp.getString(
                        getString(R.string.settings_newzy_title_three_key),
                        getString(R.string.settings_newzy_title_three_default));
                newzysQuery = sp.getString(
                        getString(R.string.settings_newzy_search_three_key),
                        getString(R.string.settings_newzy_search_three_default));
                break;

            case R.id.newzys_four:
                newzysTitle = sp.getString(
                        getString(R.string.settings_newzy_title_four_key),
                        getString(R.string.settings_newzy_title_four_default));
                newzysQuery = sp.getString(
                        getString(R.string.settings_newzy_search_four_key),
                        getString(R.string.settings_newzy_search_four_default));
                break;

            case R.id.newzys_five:
                newzysTitle = sp.getString(
                        getString(R.string.settings_newzy_title_five_key),
                        getString(R.string.settings_newzy_title_five_default));
                newzysQuery = sp.getString(
                        getString(R.string.settings_newzy_search_five_key),
                        getString(R.string.settings_newzy_search_five_default));
                break;

            case R.id.newzys_six:
                newzysTitle = sp.getString(
                        getString(R.string.settings_newzy_title_six_key),
                        getString(R.string.settings_newzy_title_six_default));
                newzysQuery = sp.getString(
                        getString(R.string.settings_newzy_search_six_key),
                        getString(R.string.settings_newzy_search_six_default));
                break;

            case R.id.settings:
                newzysTitle = getString(R.string.settings);
                break;

            case R.id.support:
                newzysTitle = getString(R.string.support);
                break;
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