package ca.qc.cgodin.projetfinalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }



    public void onClick(View v) {
        Intent myIntent = new Intent(v.getContext(), MainActivity.class);
        startActivity(myIntent);



        /* TEST ET LOGIN */

    }
}
