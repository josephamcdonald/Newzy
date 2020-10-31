/*
 * Copyright (c) 2020. josephamcdonald - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by josephamcdonald
 */

package com.josephamcdonald.android.newzy;

import android.content.SharedPreferences;
import android.net.Network;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.app.LoaderManager;
import androidx.loader.app.LoaderManager.LoaderCallbacks;
import androidx.loader.content.Loader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

public class NewzyFragment extends Fragment implements LoaderCallbacks<List<Newzy>> {

    // Newzy Loader ID constant.
    private static final int NEWZY_LOADER_ID = 0;

    // Declare the progress views.
    private ProgressBar progressCircle;
    private ImageView progressImage;

    // Declare the empty Newzys views.
    private LinearLayoutCompat emptyNewzysView;
    private ImageView emptyNewzysImage;
    private TextView emptyNewzysText;

    // Declare the newzy recycler view.
    private RecyclerView newzyRecyclerView;

    // Declare the swipe refresh layout.
    private SwipeRefreshLayout swipeLayout;

    // Declare the newzy adapter.
    private NewzyAdapter newzyAdapter;

    public NewzyFragment() {
        // Required empty constructor.
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the root newzy item view.
        View rootView = inflater.inflate(R.layout.newzy_list, container, false);

        // Find the progress views.
        progressCircle = rootView.findViewById(R.id.progress_circle);
        progressImage = rootView.findViewById(R.id.progress_image);

        // Find the empty list views.
        emptyNewzysView = rootView.findViewById(R.id.empty_view);
        emptyNewzysImage = rootView.findViewById(R.id.empty_view_image);
        emptyNewzysText = rootView.findViewById(R.id.empty_view_text);

        // Set the root newzy item view to the newzy recycler view.
        newzyRecyclerView = rootView.findViewById(R.id.newzy_recycler_view);

        // Declare and set the RecyclerView LayoutManager.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        newzyRecyclerView.setLayoutManager(layoutManager);

        // For performance, tell newzyRecyclerView its size is fixed.
        newzyRecyclerView.setHasFixedSize(true);

        // Find the swipe refresh layout and set color scheme.
        swipeLayout = rootView.findViewById(R.id.swipe_layout);
        swipeLayout.setColorSchemeColors(ContextCompat.getColor(requireActivity(), R.color.colorAccent));

        // Declare and set the OnRefreshListener to the swipe refresh layout.
        SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // Populate the Newzy list.
                getTheNewzys();
            }
        };
        swipeLayout.setOnRefreshListener(onRefreshListener);

        // Show the progress views.
        progressCircle.setVisibility(View.VISIBLE);
        progressImage.setVisibility(View.VISIBLE);

        // Populate the Newzy list.
        getTheNewzys();

        return rootView;
    }

    private void getTheNewzys() {

        // Check Internet connectivity.
        ConnectivityManager cm = (ConnectivityManager) requireActivity().
                getSystemService(Context.CONNECTIVITY_SERVICE);
        Network activeNetwork = Objects.requireNonNull(cm).getActiveNetwork();

        if (activeNetwork != null) {
            // Initialize the loader. Pass in the Newzy ID constant defined above and pass in null
            // bundle. Pass in this fragment for the LoaderCallbacks parameter.
            LoaderManager.getInstance(this).restartLoader(NEWZY_LOADER_ID, null, this);

        } else {
            // Hide the progress views.
            progressCircle.setVisibility(View.GONE);
            progressImage.setVisibility(View.GONE);

            // Hide the Newzy list view.
            newzyRecyclerView.setVisibility(View.GONE);

            // Set "No Internet" image and instructions.
            // Show the empty list views.
            emptyNewzysImage.setImageResource(R.drawable.dinosaur);
            emptyNewzysText.setText(R.string.no_internet_available);
            emptyNewzysView.setVisibility(View.VISIBLE);

            // Stop the swipe refresh animation.
            swipeLayout.setRefreshing(false);
        }
    }

    @NonNull
    @Override
    public Loader<List<Newzy>> onCreateLoader(int i, Bundle bundle) {

        // Hide the empty list view.
        emptyNewzysView.setVisibility(View.GONE);

        // Declare and assign The Headlines default request URL.
        String REQUEST_URL = getString(R.string.default_request_url);

        // If Newzys chosen are not the Newzy Headlines default, assign custom request URL.
        if (!MainActivity.newzysTitle.equals(getString(R.string.headlines))) {
            REQUEST_URL = getString(R.string.custom_request_url);
        }

        // Build the requested URL. Load the Newzy list. Return the Loader.
        return new NewzyLoader(getContext(), buildRequestUrl(REQUEST_URL));
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Newzy>> loader) {

        // Clear the newzy adapter.
        newzyAdapter = null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Newzy>> loader, List<Newzy> newzyData) {

        // Hide the progress views.
        progressCircle.setVisibility(View.GONE);
        progressImage.setVisibility(View.GONE);

        // If there is a valid list of newzyData returned, then add it to the adapter's data set.
        if (newzyData != null && !newzyData.isEmpty()) {

            // Hide the empty list view.
            emptyNewzysView.setVisibility(View.GONE);

            // Assign the data to the adapter and RecyclerView.
            newzyAdapter = new NewzyAdapter(getContext(), newzyData);
            newzyAdapter.notifyDataSetChanged();
            newzyRecyclerView.setAdapter(newzyAdapter);

            // Show the Newzy RecyclerView.
            newzyRecyclerView.setVisibility(View.VISIBLE);

        } else {
            // Hide the Newzy recycler view.
            newzyRecyclerView.setVisibility(View.GONE);

            // Set "No Newzys" image and instructions.
            // Show the empty list views.
            emptyNewzysImage.setImageResource(R.drawable.empty_box);
            emptyNewzysText.setText(R.string.no_newzys_to_display);
            emptyNewzysView.setVisibility(View.VISIBLE);
        }
        // Stop the swipe refresh animation.
        swipeLayout.setRefreshing(false);
    }

    private String buildRequestUrl(String baseUrl) {

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(requireContext());

        // Preferences string variables.
        String newzysSortBy = sp.getString(
                getString(R.string.settings_sort_newzys_by_key),
                getString(R.string.settings_sort_newzys_by_default));

        String newzysToRetrieve = sp.getString(
                getString(R.string.settings_newzys_to_retrieve_key),
                getString(R.string.settings_newzys_to_retrieve_default));

        String newzysFromDate = sp.getString(
                getString(R.string.settings_newzys_from_date_key),
                getString(R.string.settings_newzys_from_date_default));

        String newzysToDate = sp.getString(
                getString(R.string.settings_newzys_to_date_key),
                getString(R.string.settings_newzys_to_date_default));

        String token = sp.getString(
                getString(R.string.settings_api_key_key),
                getString(R.string.settings_api_key_default));

        // Break apart the URI string that's passed into its parameter.
        Uri baseUri = Uri.parse(baseUrl);

        // Prepare the baseUri that we just parsed so we can add query parameters to it.
        Uri.Builder uriBuilder = baseUri.buildUpon();

        if (MainActivity.newzysTitle.equals(getString(R.string.headlines))) {
            // Append DEFAULT QUERY parameter.
            uriBuilder.appendQueryParameter(getString(R.string.country), MainActivity.newzysQuery);

        } else {
            // Append CUSTOM QUERY parameter.
            uriBuilder.appendQueryParameter(getString(R.string.q), MainActivity.newzysQuery);

            // If NOT empty, append FROM DATE parameter.
            if (!Objects.requireNonNull(newzysFromDate).isEmpty()) {
                uriBuilder.appendQueryParameter(getString(R.string.from), newzysFromDate);
            }
            // If NOT empty, append TO DATE parameter.
            if (!Objects.requireNonNull(newzysToDate).isEmpty()) {
                uriBuilder.appendQueryParameter(getString(R.string.to), newzysToDate);
            }
            // Append SORT BY parameter.
            uriBuilder.appendQueryParameter(getString(R.string.sortby), newzysSortBy);
        }
        // Append PAGE SIZE parameter.
        uriBuilder.appendQueryParameter(getString(R.string.max), newzysToRetrieve);

        // Append News API Key parameter.
        uriBuilder.appendQueryParameter(getString(R.string.token), token);

        return uriBuilder.toString();
    }
}