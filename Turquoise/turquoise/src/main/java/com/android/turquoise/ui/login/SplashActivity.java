package com.android.turquoise.ui.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.android.turquoise.R;
import com.android.turquoise.model.response.LoginResponse;
import com.android.turquoise.ui.home.HomeActivity;
import com.android.turquoise.utils.AccountUtils;
import com.android.turquoise.utils.ui.DisplayUtils;
import com.android.turquoise.utils.ui.ForceUpdateHelper;


public class SplashActivity extends AppCompatActivity implements LoginHelper.ILoginHelper {

    LoginHelper loginHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Fabric.with(this, new Crashlytics());
        loginHelper = new LoginHelper(this);
        setContentView(R.layout.activity_splash);

        switch (ForceUpdateHelper.checkUpdateStatus(SplashActivity.this)) {
            case ForceUpdateHelper.UpdateType.FORCED_UPDATE:
                DisplayUtils.showUpdateDialog(SplashActivity.this, true, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        NavigationUtils.quitApp(SplashActivity.this);
                    }
                });
                break;
            case ForceUpdateHelper.UpdateType.OPTIONAL_UPDATE:
                DisplayUtils.showUpdateDialog(SplashActivity.this, false, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        checkAutoLogin();
                    }
                });
                break;
            default:
                checkAutoLogin();
        }

    }

    private void checkAutoLogin() {
        if (AccountUtils.isAutoLogin()) {
                loginHelper.loginSociety(SplashActivity.this, AccountUtils.getLoginCredentials());
        } else {
            startTimer();
        }

    }

    private void startTimer() {
        CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent loginIntent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        };
        countDownTimer.start();
    }

    @Override
    public void onLoginSuccess( LoginResponse response) {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    @Override
    public void onLoginFail(Throwable t) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();

    }
}
