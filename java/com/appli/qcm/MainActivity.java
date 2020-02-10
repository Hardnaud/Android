package com.appli.qcm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    final String EXTRA_LOGIN = "user_login";
    final String EXTRA_PASSWORD = "user_password";
    private static final int REQUEST_CODE = 10;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText login = (EditText) findViewById(R.id.user_email);
        final EditText pass = (EditText) findViewById(R.id.user_password);
        final Button loginButton = (Button) findViewById(R.id.connect);
        final Button createButton = (Button) findViewById(R.id.create_account);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String loginTxt = login.getText().toString();
                final String passTxt = pass.getText().toString();
                if (loginTxt.equals("") || passTxt.equals("")) {
                    Toast.makeText(MainActivity.this,
                            R.string.email_or_password_empty,
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
                Matcher m = p.matcher(loginTxt);
                if (!m.matches()) {
                    Toast.makeText(MainActivity.this,
                            R.string.email_format_error,
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (passTxt.length() < 8) {
                    Toast.makeText(MainActivity.this,
                            R.string.pass_format_error,
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this,
                        LoginDisplayActivity.class);
                intent.putExtra(EXTRA_LOGIN, loginTxt);
                intent.putExtra(EXTRA_PASSWORD, passTxt);
                startActivity(intent);
            }
        });
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String loginTxt = "";
                final String passTxt = "";
                Intent intent1 = new Intent(MainActivity.this,
                        CreateLogin.class);
                intent1.putExtra(EXTRA_LOGIN, loginTxt);
                intent1.putExtra(EXTRA_PASSWORD, passTxt);
                startActivityForResult(intent1,REQUEST_CODE );
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        final EditText login = (EditText) findViewById(R.id.user_email);
        final EditText pass = (EditText) findViewById(R.id.user_password);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data.hasExtra("user_login")) {
                String result = data.getExtras().getString("user_login");
                if (result != null && result.length() > 0) {
                    login.setText(result);
                }
            }
            if (data.hasExtra("user_password")) {
                String result =
                        data.getExtras().getString("user_password");
                if (result != null && result.length() > 0) {
                    pass.setText(result);
                }
            }
        }
    }
}

