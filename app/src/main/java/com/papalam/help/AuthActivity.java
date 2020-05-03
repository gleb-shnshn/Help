package com.papalam.help;

import android.content.Intent;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class AuthActivity extends AppCompatActivity implements View.OnClickListener {
    private LoadingButton loadingButton;
    private EditText loginField, passwordField, nameField;
    private TextView nameLabel;
    private TextView switchButton;
    private boolean isLoginOrRegistration = true;

    private void updateContentViewOnUiThread(final int layout, final boolean needInit) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setContentView(layout);
                if (needInit)
                    initializeViews();
            }
        });

    }

    private void initializeViews() {
        loginField = findViewById(R.id.loginInput);
        passwordField = findViewById(R.id.passInput);
        loadingButton = findViewById(R.id.progressButton);
        switchButton = findViewById(R.id.switchButton);
        nameLabel = findViewById(R.id.nameLabel);
        nameField = findViewById(R.id.nameInput);
        loadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performRequest();
            }
        });
        switchButton.setOnClickListener(this);
        updateTextOnButtons();
    }

    public void onClick(View view) {
        isLoginOrRegistration = !isLoginOrRegistration;
        updateTextOnButtons();
    }

    private void updateTextOnButtons() {
        switchButton.setText(isLoginOrRegistration ? getString(R.string.create_an_account) : getString(R.string.already_registered));
        loadingButton.setText(isLoginOrRegistration ? getString(R.string.sign_in) : getString(R.string.create_an_account));
        nameLabel.setVisibility(isLoginOrRegistration ? View.GONE : View.VISIBLE);
        nameField.setVisibility(isLoginOrRegistration ? View.GONE : View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Thread(new Runnable() {
            @Override
            public void run() {
                updateContentViewOnUiThread(R.layout.loading_layout, false);
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (App.getInstance().getDataHandler().getAccessToken().equals("")) {
                    updateContentViewOnUiThread(R.layout.activity_auth, true);
                } else {
                    switchToStockCase();
                }
            }

        }).start();
    }

    private void performRequest() {
        if (isFieldsEmpty()) {
            App.getInstance().getUtils().showError(getString(R.string.empty_fields));
            return;
        }
        if (loginField.getText().toString().equals("admin") && passwordField.getText().toString().equals("admin")) {
            switchToStockCase();
            App.getInstance().getDataHandler().saveTokens("logined", "logined");
        }

    }

    private boolean isFieldsEmpty() {
        return ((loginField.getText().length() == 0)
                || (passwordField.getText().length() == 0)
                || (!isLoginOrRegistration && nameField.getText().length() == 0));
    }

    private void showProgress(boolean visible) {
        if (visible) {
            loadingButton.startLoading();
            switchButton.setOnClickListener(null);
        } else {
            loadingButton.stopLoading();
            switchButton.setOnClickListener(this);
        }

    }

    private void switchToStockCase() {
        Intent intent = new Intent(AuthActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private LoginAndPassword getLoginDataFromFields() {
        String login = loginField.getText().toString();
        String password = passwordField.getText().toString();
        return new LoginAndPassword(login, password);
    }

    private RegistrationData getRegDataFromFields() {
        String login = loginField.getText().toString();
        String password = passwordField.getText().toString();
        String name = nameField.getText().toString();
        return new RegistrationData(login, password, name);
    }

}
