/*
 * Copyright (c) 2019-2024. josephamcdonald - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by josephamcdonald
 */

package com.josephamcdonald.android.newzy;

public class Newzy {

    /**
     * Newzy source.
     */
    private final String newzySource;

    /**
     * Newzy publication date.
     */
    private final String newzyDate;

    /**
     * Newzy title.
     */
    private final String newzyTitle;

    /**
     * Newzy web URL.
     */
    private final String newzyUrl;

    /**
     * Newzy author.
     */
    private final String newzyAuthor;

    /**
     * Newzy image URL.
     */
    private final String newzyImage;

    /**
     * Create a Newzy object.
     *
     * @param source  newzy source.
     * @param date    newzy publication date.
     * @param title   newzy publication title.
     * @param url     newzy web URL.
     * @param author  newzy author.
     * @param image   newzy image URL.
     */
    Newzy(String source, String date, String title, String url, String author, String image) {
        newzySource = source;
        newzyDate = date;
        newzyTitle = title;
        newzyUrl = url;
        newzyAuthor = author;
        newzyImage = image;
    }

    /**
     * Get the Newzy source.
     */
    String getNewzySource() {
        return newzySource;
    }

    /**
     * Get the Newzy publication date.
     */
    String getNewzyDate() {
        return newzyDate;
    }

    /**
     * Get the Newzy title.
     */
    String getNewzyTitle() {
        return newzyTitle;
    }

    /**
     * Get the Newzy web URL.
     */
    String getNewzyUrl() { return newzyUrl; }

    /**
     * Get the Newzy author.
     */
    String getNewzyAuthor() {
        return newzyAuthor;
    }

    /**
     * Get the Newzy image URL.
     */
    String getNewzyImage() {
        return newzyImage;
    }
}