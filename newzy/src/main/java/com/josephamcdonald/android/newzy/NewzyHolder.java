/*
 * Copyright (c) 2019-2022. josephamcdonald - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by josephamcdonald
 */

package com.josephamcdonald.android.newzy;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;

import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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

        // If no Newzy image provided, use a stock image.
        if (currentNewzy.getNewzyImage().equals(context.getString(R.string.null_string)) || currentNewzy.getNewzyImage().equals(context.getString(R.string.empty_string))) {
            newzyImageView.setImageResource(R.drawable.newzy_banner);

        } else {
            int IMAGE_TARGET_WIDTH = 500;
            int IMAGE_TARGET_HEIGHT = 300;
            Picasso.get().load(currentNewzy.getNewzyImage())
                    .resize(IMAGE_TARGET_WIDTH, IMAGE_TARGET_HEIGHT)
                    .centerCrop()
                    .error(R.drawable.newzy_banner)
                    .into(newzyImageView);
        }
    }

    @Override
    public void onClick(View v) {

        // If no Newzy URL provided, toast it so.
        if (currentNewzy.getNewzyUrl().equals(context.getString(R.string.null_string)) || currentNewzy.getNewzyUrl().equals(context.getString(R.string.empty_string))) {
            // Create the "No Newzy Provided!" toast message and show it.
            Toast.makeText(context, context.getString(R.string.no_newzy_provided), Toast.LENGTH_SHORT).show();

        } else {
            // Create the "Loading Newzy..." toast message and show it.
            Toast.makeText(context, context.getString(R.string.loading_newzy), Toast.LENGTH_SHORT).show();

            // Use CustomTabColorSchemeParams to set Toolbar color.
            CustomTabColorSchemeParams toolbarColor = new CustomTabColorSchemeParams.Builder()
                    .setToolbarColor(context.getColor(R.color.colorPrimary))
                    .build();

            // Use a CustomTabsIntent.Builder to configure CustomTabsIntent.
            // Once ready, create a CustomTabsIntent and launch the current
            // Newzy Url with intent.launchUrl()
            CustomTabsIntent intent = new CustomTabsIntent.Builder()
                    .setDefaultColorSchemeParams(toolbarColor)
                    .setShareState(CustomTabsIntent.SHARE_STATE_ON)
                    .setCloseButtonIcon(BitmapFactory.decodeResource(
                            context.getResources(), R.drawable.ic_arrow_back))
                    .setShowTitle(true)
                    .build();
            intent.launchUrl(context, Uri.parse(currentNewzy.getNewzyUrl()));
        }
    }

    private String formatDate(String dateStringIn) {

        Instant instantIn = Instant.parse(dateStringIn);
        LocalDateTime dateTimeIn = LocalDateTime.ofInstant(instantIn, ZoneId.systemDefault());
        String datePatternOut = context.getString(R.string.date_pattern_out);
        DateTimeFormatter dateFormatOut = DateTimeFormatter.ofPattern(datePatternOut);

        return dateFormatOut.format(dateTimeIn);
    }
}