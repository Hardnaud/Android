package com.appli.qcm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Question2 extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question2);
        Intent intent = getIntent();
        final Button valider1 = (Button) findViewById(R.id.valider2);

        valider1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Question2.this, Question3.class);
                startActivity(intent);
            }
        });
    }

}
