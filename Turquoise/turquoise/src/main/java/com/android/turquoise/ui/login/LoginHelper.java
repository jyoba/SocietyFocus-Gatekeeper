package com.android.turquoise.ui.login;

import android.content.Context;
import android.content.Intent;
import android.util.Log;


import com.android.turquoise.model.LoginCredentials;
import com.android.turquoise.model.response.LoginResponse;
import com.android.turquoise.utils.API;
import com.android.turquoise.utils.AccountUtils;
import com.android.turquoise.utils.DeviceUtils;
import com.android.turquoise.utils.HTTP;
import com.android.turquoise.utils.ks.KeyStoreUtils;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jikoobaruah on 07/04/17.
 */

class LoginHelper {

    WeakReference<ILoginHelper> callbackWeakReference;


    public LoginHelper(ILoginHelper callback) {
        callbackWeakReference = new WeakReference<ILoginHelper>(callback);
    }

    public void loginSociety(final Context context, final LoginCredentials loginCredentials) {

        API api = HTTP.getAPI();
        if (api == null)
            return;

        Call<LoginResponse> response = api.login(loginCredentials.society, loginCredentials.username, loginCredentials.password, FirebaseInstanceId.getInstance().getToken(), DeviceUtils.getUniqueDeviceID());
        response.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Response<LoginResponse> response) {
                ILoginHelper callback = callbackWeakReference.get();
                if (callback != null) {
                    if (response.isSuccess()) {
                        AccountUtils.saveSocietyLogin(loginCredentials.getEncrypted(KeyStoreUtils.getInstance(context)));
                        AccountUtils.saveLoggedInUser(response.body().body.userDetails.user);
                        AccountUtils.saveLoggedInSociety(response.body().body.society);
                        AccountUtils.saveAuthToken(response.body().body.token);
                        callback.onLoginSuccess(response.body());
                    } else {
                        try {
                            callback.onLoginFail( new Throwable(response.errorBody().string()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

            @Override
            public void onFailure(Throwable t) {
                ILoginHelper callback = callbackWeakReference.get();
                if (callback != null) {
                    callback.onLoginFail( t);
                }
            }
        });

    }



    public interface ILoginHelper {

        void onLoginSuccess(LoginResponse response);

        void onLoginFail(Throwable t);
    }
}
