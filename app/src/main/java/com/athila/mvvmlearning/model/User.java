package com.athila.mvvmlearning.model;

import android.text.TextUtils;

import org.json.JSONObject;

/**
 * Created by athila on 08/08/15.
 */
public class User {
    private static final String JSON_KEY_EMAIL = "Email";
    private static final String JSON_KEY_NAME = "Name";
    private static final String JSON_KEY_PHOTO_URL = "PhotoUrl";

    public String email = "";
    public String password = "";
    public String name = "";
    public String photoUrl = "";

    public User() {}

    public User(JSONObject jsonUser) {
        email = jsonUser.optString(JSON_KEY_EMAIL);
        name = jsonUser.optString(JSON_KEY_NAME);
        photoUrl = jsonUser.optString(JSON_KEY_PHOTO_URL);
    }

    public void completeWith(User anotherUser) {
        if (TextUtils.isEmpty(email)) {
            email = anotherUser.email;
        }

        if (TextUtils.isEmpty(password)) {
            password = anotherUser.password;
        }

        if (TextUtils.isEmpty(name)) {
            name = anotherUser.name;
        }

        if (TextUtils.isEmpty(photoUrl)) {
            photoUrl = anotherUser.photoUrl;
        }
    }
}
