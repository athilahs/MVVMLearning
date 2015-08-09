package com.athila.mvvmlearning.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.athila.mvvmlearning.BR;
import com.athila.mvvmlearning.infrastructure.MyConstants;
import com.athila.mvvmlearning.infrastructure.OperarionError;
import com.athila.mvvmlearning.infrastructure.OperationListener;
import com.athila.mvvmlearning.model.User;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

/**
 * Created by athila on 08/08/15.
 */
public class UserViewModel extends BaseObservable implements Serializable {

    private User user;
    private Context context;

    public UserViewModel(Context context) {
        this.context = context;
        initFromScratch();
    }

    /**
     * Initialize the view model with an "empty" User
     */
    private void initFromScratch() {
        user = new User();
    }

    /**
     * Load user credentials stored on device
     */
    public void initFromStorage() {
        SharedPreferences secureStorage = context.getSharedPreferences(MyConstants.SharedPrefs.SECURE_PREFS_NAME, Context.MODE_PRIVATE);
        user.email = secureStorage.getString(MyConstants.SharedPrefs.KEY_USER_EMAIL, "");
        user.password = secureStorage.getString(MyConstants.SharedPrefs.KEY_USER_PSW, "");
        user.name = secureStorage.getString(MyConstants.SharedPrefs.KEY_USER_NAME, "");
        user.photoUrl = secureStorage.getString(MyConstants.SharedPrefs.KEY_USER_PHOTO_URL, "");
    }

    @Bindable
    public String getEmail() {
        return user.email;
    }

    @Bindable
    public String getPassword() {
        return user.password;
    }

    @Bindable
    public String getName() {
        return user.name;
    }

    @Bindable
    public String getPhotoUrl() {
        return user.photoUrl;
    }

    public void setEmail(String email) {
        user.email = email;
        notifyPropertyChanged(BR.email);
    }

    public void setPassword(String password) {
        user.password = password;
        notifyPropertyChanged(BR.password);
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).into(view);
    }


    public void store() {
        // TODO: implement secure storage
        SharedPreferences secureStorage = context.getSharedPreferences(MyConstants.SharedPrefs.SECURE_PREFS_NAME, Context.MODE_PRIVATE);
        secureStorage.edit()
                .putString(MyConstants.SharedPrefs.KEY_USER_EMAIL, user.email)
                .putString(MyConstants.SharedPrefs.KEY_USER_PSW, user.password)
                .putString(MyConstants.SharedPrefs.KEY_USER_NAME, user.name)
                .putString(MyConstants.SharedPrefs.KEY_USER_PHOTO_URL, user.photoUrl)
                .apply();
    }

    public void login(OperationListener<Void> callback) {
        // TODO: call login WS and get the User entity returned and store it
        if ("athila_test@gmail.com".equals(user.email)
                && "123456".equals(user.password)) {

            // Success MOCK
            User returnedUser = new User();
            returnedUser.email = "athila_test@gmail.com";
            returnedUser.photoUrl = "https://avatars2.githubusercontent.com/u/2580440?v=3&s=460";
            returnedUser.name = "Athila Santos";
            // fill the current model with backend returned informations
            user.completeWith(returnedUser);
            store();

            if (callback != null) {
                callback.onSuccess();
            }
        } else if (callback != null){
            callback.onError(new OperarionError(OperarionError.INVALID_CREDENTIALS));
        }
    }
}
