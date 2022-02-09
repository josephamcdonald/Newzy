/*
 * Copyright (c) 2022. josephamcdonald - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by josephamcdonald
 */

package com.josephamcdonald.android.newzy;


import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SupportFragment extends Fragment implements View.OnClickListener {

    public SupportFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the support_main screen view.
        View rootView = inflater.inflate(R.layout.support_main, container, false);

        // Find NewsAPI.org views.
        TextView newsApi = rootView.findViewById(R.id.news_api);
        TextView newsApiReg = rootView.findViewById(R.id.news_api_reg);

        // Set click listeners on NewsAPI.org views.
        newsApi.setOnClickListener(this);
        newsApiReg.setOnClickListener(this);

        // Set current app version name.
        TextView version = rootView.findViewById(R.id.ver_name);
        version.setText(BuildConfig.VERSION_NAME);

        return rootView;
    }

    @Override
    public void onClick(View view) {

        String newzyUrl;

        if (view.getId() == R.id.news_api) {
            newzyUrl = getString(R.string.news_api_url);

        } else {
            newzyUrl = getString(R.string.news_api_reg_url);
        }

        // Use CustomTabColorSchemeParams to set Toolbar color.
        CustomTabColorSchemeParams toolbarColor = new CustomTabColorSchemeParams.Builder()
                .setToolbarColor(requireActivity().getColor(R.color.colorPrimary))
                .build();

        // Create a CustomTabsIntent and launch the NewsAPI.org Url with intent.launchUrl()
        CustomTabsIntent intent = new CustomTabsIntent.Builder()
                .setDefaultColorSchemeParams(toolbarColor)
                .setShareState(CustomTabsIntent.SHARE_STATE_ON)
                .setCloseButtonIcon(BitmapFactory.decodeResource(
                        getResources(), R.drawable.ic_arrow_back))
                .setShowTitle(true)
                .build();
        intent.launchUrl(requireActivity(), Uri.parse(newzyUrl));
    }
}
