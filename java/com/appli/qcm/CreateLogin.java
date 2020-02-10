package com.appli.qcm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateLogin extends Activity {
    final String EXTRA_LOGIN = "user_login";
    final String EXTRA_PASSWORD = "user_password";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_login);
        final Button validerButton = (Button)
                findViewById(R.id.connect);
        validerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Si un des deux champs est vide, alors on affiche l'erreurs
                final EditText login = (EditText)
                        findViewById(R.id.user_email);
                final EditText pass = (EditText)
                        findViewById(R.id.user_password);
                final String loginTxt = login.getText().toString();
                final String passTxt = pass.getText().toString();
                if (loginTxt.equals("") || passTxt.equals("")) {
                    Toast.makeText(CreateLogin.this,
                            R.string.email_or_password_empty,
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Pattern p = Pattern.compile(".+@.+\\.[a-z]+");

                Matcher m = p.matcher(loginTxt);

                if (!m.matches()) {
                    Toast.makeText(CreateLogin.this,
                            R.string.email_format_error,
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (passTxt.length() < 8) {
                    Toast.makeText(CreateLogin.this,
                            R.string.pass_format_error,
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                finish();
            }
        });
    }
    @Override
    public void finish() {
        // Prepare data intent
        EditText loginDisplay = (EditText)
                findViewById(R.id.user_email);
        EditText passwordDisplay = (EditText)
                findViewById(R.id.user_password);
        String loginTxt = loginDisplay.getText().toString();
        String passTxt = passwordDisplay.getText().toString();
        Intent data = new Intent();
        data.putExtra("user_login", loginTxt);
        data.putExtra("user_password", passTxt);
        // Activity finished ok, return the data
        setResult(RESULT_OK, data);
        super.finish();
    }
}
