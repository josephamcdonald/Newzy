/*
 * Copyright (c) 2020. josephamcdonald - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by josephamcdonald
 */

package com.josephamcdonald.android.newzy;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class NewzyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final Context context;

    // Declare the current Newzy.
    private Newzy currentNewzy;

    // Declare the current Newzy content views.
    private final TextView newzySectionTextView;
    private final TextView newzyTitleTextView;
    private final TextView newzyAuthorTextView;
    private final TextView newzyDateTextView;
    private final ImageView newzyImageView;

    NewzyHolder(Context context, View newzyView) {

        super(newzyView);

        // Set the context.
        this.context = context;

        // Find and assign the Newzy subviews.
        newzySectionTextView = newzyView.findViewById(R.id.newzy_section_text_view);
        newzyTitleTextView = newzyView.findViewById(R.id.newzy_title_text_view);
        newzyAuthorTextView = newzyView.findViewById(R.id.newzy_author_text_view);
        newzyDateTextView = newzyView.findViewById(R.id.newzy_date_text_view);
        newzyImageView = newzyView.findViewById(R.id.newzy_image_view);

        // Set the "onClick" listener to the Newzy view.
        newzyView.setOnClickListener(this);
    }

    void bindPartner(Newzy newzy) {

        // Assign the current Newzy.
        currentNewzy = newzy;

        // Set the Newzy text views.
        newzySectionTextView.setText(currentNewzy.getNewzySource());
        newzyTitleTextView.setText(currentNewzy.getNewzyTitle());
        newzyAuthorTextView.setText(currentNewzy.getNewzyAuthor());
        newzyDateTextView.setText(formatDate(currentNewzy.getNewzyDate()));

        if (currentNewzy.getNewzyImage().equals(context.getString(R.string.null_string))) {
            newzyImageView.setImageResource(R.drawable.newzy);

        } else {
            int IMAGE_TARGET_WIDTH = 500;
            int IMAGE_TARGET_HEIGHT = 300;
            Picasso.get().load(currentNewzy.getNewzyImage())
                    .resize(IMAGE_TARGET_WIDTH, IMAGE_TARGET_HEIGHT)
                    .centerCrop()
                    .error(R.drawable.newzy)
                    .into(newzyImageView);
        }
    }

    @Override
    public void onClick(View v) {

        // Create the toast message and show it.
        Toast.makeText(context, context.getString(R.string.loading_newzy), Toast.LENGTH_SHORT).show();

        // Use a CustomTabsIntent.Builder to configure CustomTabsIntent.
        // Once ready, create a CustomTabsIntent and launch the current
        // Newzy Url with CustomTabsIntent.launchUrl()
        CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
                .setDefaultShareMenuItemEnabled(true)
                .setCloseButtonIcon(BitmapFactory.decodeResource(
                        context.getResources(), R.drawable.ic_arrow_back))
                .setShowTitle(true)
                .setToolbarColor(context.getColor(R.color.colorPrimary))
                .build();
        customTabsIntent.launchUrl(context, Uri.parse(currentNewzy.getNewzyUrl()));
    }

    private String formatDate(String dateIn) {

        String patternIn = context.getString(R.string.date_pattern_in);
        String patternOut = context.getString(R.string.date_pattern_out);

        SimpleDateFormat dateFormatIn = new SimpleDateFormat(patternIn, Locale.getDefault());
        SimpleDateFormat dateFormatOut = new SimpleDateFormat(patternOut, Locale.getDefault());

        try {
            Date dateOut = dateFormatIn.parse(dateIn);
            return dateFormatOut.format(Objects.requireNonNull(dateOut)) + context.getString(R.string.utc);

        } catch (ParseException e) {
            e.printStackTrace();
            return context.getString(R.string.app_name);
        }
    }
}