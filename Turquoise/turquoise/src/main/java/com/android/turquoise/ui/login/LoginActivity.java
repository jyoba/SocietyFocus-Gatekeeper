package com.android.turquoise.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.turquoise.R;
import com.android.turquoise.model.LoginCredentials;
import com.android.turquoise.model.response.LoginResponse;
import com.android.turquoise.ui.common.BaseActivity;
import com.android.turquoise.ui.home.HomeActivity;

public class LoginActivity extends BaseActivity implements SocietySelectionFragment.ISocietySelectionListener, LoginHelper.ILoginHelper {

    public static final int AUTH_REQUEST = 8764;

    LoginHelper loginHelper;

//    private Overlay overlay;

    private LinearLayout loginLayout;

    private ProgressBar progressBar;

    private EditText passwordEditText;

    private EditText usernameEditText;

    private EditText societyEditText;

    private Button loginButton;

    private String username;
    private String password;
    private String society;

    private LoginCredentials credentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginHelper = new LoginHelper(this);

        setContentView(R.layout.activity_login);

        passwordEditText = (EditText) findViewById(R.id.etPassword);
        usernameEditText = (EditText) findViewById(R.id.etUsername);
        societyEditText = (EditText) findViewById(R.id.etSociety);
        loginButton = (Button) findViewById(R.id.btn_login);

        societyEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSocieties();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validated()) {
                    login();
                }
            }
        });



        progressBar = (ProgressBar) findViewById(R.id.progress);

        loginLayout = (LinearLayout) findViewById(R.id.ll_login_form);

//        overlay = (Overlay) findViewById(R.id.overlay);


    }

    @Override
    protected void onStart() {
        super.onStart();
//        overlay.drawOverlay();

    }

    private void login() {

        progressBar.setVisibility(View.VISIBLE);
        loginLayout.setAlpha(0.4f);

        loginHelper.loginSociety(LoginActivity.this, new LoginCredentials(society, username, password));


    }

    private boolean validated() {
        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();

        if (TextUtils.isEmpty(username)) {
            usernameEditText.setError("Please enter your email");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Please enter your password");
            return false;
        }

        if (TextUtils.isEmpty(society)) {
            societyEditText.setError("Please select your society");
            return false;
        }

        return true;


    }

    private void showSocieties() {
        BottomSheetDialogFragment bottomSheetDialogFragment = new SocietySelectionFragment();
        bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());

    }

    @Override
    public void onSocietySelected(String societyName, String societyValue) {
        societyEditText.setText(societyName);
        society = societyValue;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginHelper = null;
    }

    @Override
    public void onLoginSuccess(LoginResponse response) {

        progressBar.setVisibility(View.GONE);
        loginLayout.setAlpha(1.0f);


            if (getCallingActivity() != null) {
                setResult(RESULT_OK);
            } else {
                startActivity(new Intent(this, HomeActivity.class));
            }
            finish();

    }

    @Override
    public void onLoginFail(Throwable t) {
        progressBar.setVisibility(View.GONE);
        loginLayout.setAlpha(1.0f);
        Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
    }


    @Override
    protected void load() {
        //do nothing
    }


}
