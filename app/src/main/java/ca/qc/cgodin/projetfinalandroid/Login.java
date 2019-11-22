package ca.qc.cgodin.projetfinalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    public static String SESSIONREST = new String();
    MaClasseLogin login = new MaClasseLogin();
    EditText edUser;
    EditText edPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUser = findViewById(R.id.edUser);
        edPass = findViewById(R.id.edPass);
    }



    public void onClick(View v) {
        if(login.etablirConnexion(edUser.getText().toString(),edPass.getText().toString())){
            Intent intent = new Intent(Login.this,MainActivity.class);
            intent.putExtra("login","true");
            startActivity(intent);
        }



        /* TEST ET LOGIN */

    }
    public void clicktest(View v) {
        login.test();



        /* TEST ET LOGIN */

    }
}
