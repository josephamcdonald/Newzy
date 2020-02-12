/*
 * Copyright (c) 2020. josephamcdonald - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by josephamcdonald
 */

package com.josephamcdonald.android.newzy;


import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;

public class SupportFragment extends Fragment implements View.OnClickListener {

    public SupportFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the support_main screen view.
        View rootView = inflater.inflate(R.layout.support_main, container, false);

        // Find NewsAPI.org views.
        TextView newsApiOrg = rootView.findViewById(R.id.news_api_org);
        TextView newsApiReg = rootView.findViewById(R.id.news_api_reg);

        // Set click listeners on NewsAPI.org views.
        newsApiOrg.setOnClickListener(this);
        newsApiReg.setOnClickListener(this);

        // Set current app version name.
        TextView version = rootView.findViewById(R.id.ver_name);
        version.setText(BuildConfig.VERSION_NAME);

        return rootView;
    }

    @Override
    public void onClick(View view) {

        String newzyUrl;

        if (view.getId() == R.id.news_api_org) {
            newzyUrl = getString(R.string.news_api_org_url);

        } else {
            newzyUrl = getString(R.string.news_api_reg_url);
        }

        // Create a CustomTabsIntent and launch the NewsAPI.org Url with CustomTabsIntent.launchUrl()
        CustomTabsIntent cti = new CustomTabsIntent.Builder()
                .addDefaultShareMenuItem()
                .setCloseButtonIcon(BitmapFactory.decodeResource(
                        getResources(), R.drawable.ic_arrow_back))
                .setShowTitle(true)
                .setToolbarColor(Objects.requireNonNull(getActivity()).getColor(R.color.colorPrimary))
                .build();
        cti.launchUrl(getActivity(), Uri.parse(newzyUrl));
    }
}
