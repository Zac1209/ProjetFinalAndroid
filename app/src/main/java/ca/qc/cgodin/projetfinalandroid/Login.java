package ca.qc.cgodin.projetfinalandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.ArrayList;

import ca.qc.cgodin.projetfinalandroid.RecyclerView.CompteAdapter;

public class Login extends AppCompatActivity {
    public static String SESSIONREST = new String();
    MaClasseLogin login = new MaClasseLogin();
    EditText edPass;
    ArrayList<Compte> lstUser;
    RecyclerView recUser;
    CompteAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edPass = findViewById(R.id.edPass);
        lstUser = new ArrayList<>();
        recUser = findViewById(R.id.lstUser);

        String ajaxReturn = "";
        try {
            ajaxReturn = login.get("http://10.0.2.2:8100/getUserList","");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String [] pairs = ajaxReturn.replace("[","").replace("\"","").replace("]","").replace("{","").replace("}","").replace("data:image/jpeg;base64,","").split(",");
        for (int i=0;i<pairs.length && pairs.length>=2;i++) {
            String pair = pairs[i];
            String[] keyValue = pair.split(":");
            Compte compte = new Compte(keyValue[0], keyValue[1]);
            lstUser.add(compte);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(Login.this);
        recUser.setLayoutManager(layoutManager);
        adapter = new CompteAdapter(lstUser,getApplicationContext(),true);
        recUser.setAdapter(adapter);
    }



    public void onClick(View v) {
        if(login.etablirConnexion(CompteAdapter.userSelected,edPass.getText().toString())){
            Intent intent = new Intent(Login.this,MainActivity.class);
            intent.putExtra("login","true");
            startActivity(intent);
        }

    }



}
