package ca.qc.cgodin.projetfinalandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import ca.qc.cgodin.projetfinalandroid.RecyclerView.CompteAdapter;
import ca.qc.cgodin.projetfinalandroid.RecyclerView.CompteAdapterPassageGrade;

public class Grades extends AppCompatActivity {
    static MaClasseLogin login = new MaClasseLogin();
    static String currentUser = "";
    RecyclerView rcMembre;
    RecyclerView rcHonte;
    static Grades context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);
        rcMembre = findViewById(R.id.rcMembreAdmissible);
        rcHonte = findViewById(R.id.rcListeHonte);

        Intent intent = getIntent();
        currentUser = intent.getStringExtra("user");

        loadData();
        context= this;
    }

    static public void passer(String user){
        try {
            login.get("http://192.168.50.54:8100/Exam/" + user + "/" + currentUser + "/true","");
        } catch (IOException e) {
            e.printStackTrace();
        }
        context.loadData();
    }

    static public void echec(String user){
        try {
            login.get("http://192.168.50.54:8100/Exam/" + user + "/" + currentUser + "/false","");
        } catch (IOException e) {
            e.printStackTrace();
        }
        context.loadData();
    }

    private void loadData(){
        ArrayList<Compte> lstMbAdmissible = new ArrayList<>();
        ArrayList<Compte> lstHonte = new ArrayList<>();
        String ajaxReturn = "";
        try {
            ajaxReturn = login.get("http://192.168.50.54:8100/getMembreAdmissible","");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] pairs = ajaxReturn.replace("[","").replace("\"","").replace("]","").replace("{","").replace("}","").replace("data:image/jpeg;base64,","").split(",");
        if(pairs.length>=2){
            for (int i=0;i<pairs.length && pairs.length>=2;i++) {
                String pair = pairs[i];
                String[] keyValue = pair.split(":");
                Compte compte = new Compte(keyValue[0], keyValue[1]);
                lstMbAdmissible.add(compte);
            }
        }else{
            String pair1 = ajaxReturn.replace("[","").replace("\"","").replace("]","").replace("{","").replace("}","").replace("data:image/jpeg;base64,","").replace("{","").replace("}","");
            if(pair1.length() != 0){
                String[] keyValue1 = pair1.split(":");
                Compte compte1 = new Compte(keyValue1[0], keyValue1[1]);
                lstMbAdmissible.add(compte1);
            }

        }

        ajaxReturn = "";
        try {
            ajaxReturn = login.get("http://192.168.50.54:8100/getHonte","");
        } catch (IOException e) {
            e.printStackTrace();
        }
        pairs = ajaxReturn.replace("[","").replace("\"","").replace("]","").replace("{","").replace("}","").replace("data:image/jpeg;base64,","").split(",");
        if(pairs.length>=2){
            for (int i=0;i<pairs.length && pairs.length>=2;i++) {
                String pair = pairs[i];
                String[] keyValue = pair.split(":");
                Compte compte = new Compte(keyValue[0], keyValue[1]);
                lstHonte.add(compte);
            }
        }else{
            String pair1 = ajaxReturn.replace("[","").replace("\"","").replace("]","").replace("{","").replace("}","").replace("data:image/jpeg;base64,","").replace("{","").replace("}","");
            if(pair1.length() != 0){
                String[] keyValue1 = pair1.split(":");
                Compte compte1 = new Compte(keyValue1[0], keyValue1[1]);
                lstHonte.add(compte1);
            }

        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(Grades.this);
        rcMembre.setLayoutManager(layoutManager);
        CompteAdapterPassageGrade adapter = new CompteAdapterPassageGrade(lstMbAdmissible,getApplicationContext());
        rcMembre.setAdapter(adapter);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(Grades.this);
        rcHonte.setLayoutManager(layoutManager2);
        CompteAdapter adapter1 = new CompteAdapter(lstHonte,getApplicationContext(),false);
        rcHonte.setAdapter(adapter1);
    }
}
