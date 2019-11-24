package ca.qc.cgodin.projetfinalandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.qc.cgodin.projetfinalandroid.RecyclerView.CompteAdapter;

public class Ecole extends AppCompatActivity {

    RecyclerView venerable;
    RecyclerView honte;
    RecyclerView prof;
    RecyclerView ancien;
    RecyclerView nouveau;
    MaClasseLogin login = new MaClasseLogin();
    public static RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecole);

        TextView lblDojo = findViewById(R.id.lblDojo2);
        String htmlString="Clicker <font color='#518aff'><u>ici</u></font> pour retourner au dojo\n";
        lblDojo.setText(Html.fromHtml(htmlString));

        venerable = findViewById(R.id.venerable);
        honte = findViewById(R.id.honte);
        prof = findViewById(R.id.prof);
        ancien = findViewById(R.id.ancien);
        nouveau = findViewById(R.id.nouveau);
        ArrayList<Compte> lstVenerable = new ArrayList<>();
        ArrayList<Compte> lstHonte = new ArrayList<>();
        ArrayList<Compte> lstprof = new ArrayList<>();
        ArrayList<Compte> lstAncien = new ArrayList<>();
        ArrayList<Compte> lstNouveau = new ArrayList<>();

        venerable.addItemDecoration(new DividerItemDecoration(venerable.getContext(),
                DividerItemDecoration.VERTICAL));
        honte.addItemDecoration(new DividerItemDecoration(honte.getContext(),
                DividerItemDecoration.VERTICAL));
        prof.addItemDecoration(new DividerItemDecoration(prof.getContext(),
                DividerItemDecoration.VERTICAL));
        ancien.addItemDecoration(new DividerItemDecoration(ancien.getContext(),
                DividerItemDecoration.VERTICAL));
        nouveau.addItemDecoration(new DividerItemDecoration(nouveau.getContext(),
                DividerItemDecoration.VERTICAL));

        String ajaxReturn = "";
        try {
            ajaxReturn = login.get("http://192.168.50.54:8100/getVenerable","");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String pair1 = ajaxReturn.replace("[","").replace("\"","").replace("]","").replace("{","").replace("}","").replace("data:image/jpeg;base64,","").replace("{","").replace("}","");
        String[] keyValue1 = pair1.split(":");
        Compte compte1 = new Compte(keyValue1[0], keyValue1[1]);
        lstVenerable.add(compte1);

        ajaxReturn = "";
        try {
            ajaxReturn = login.get("http://192.168.50.54:8100/getHonte","");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String [] pairs = ajaxReturn.replace("[","").replace("\"","").replace("]","").replace("{","").replace("}","").replace("data:image/jpeg;base64,","").split(",");
        for (int i=0;i<pairs.length && pairs.length>=2;i++) {
            String pair = pairs[i];
            String[] keyValue = pair.split(":");
            Compte compte = new Compte(keyValue[0], keyValue[1]);
            lstHonte.add(compte);
        }

        ajaxReturn = "";
        try {
            ajaxReturn = login.get("http://192.168.50.54:8100/getProf","");
        } catch (IOException e) {
            e.printStackTrace();
        }
        pairs = ajaxReturn.replace("[","").replace("\"","").replace("]","").replace("{","").replace("}","").replace("data:image/jpeg;base64,","").split(",");
        for (int i=0;i<pairs.length && pairs.length>=2;i++) {
            String pair = pairs[i];
            String[] keyValue = pair.split(":");
            Compte compte = new Compte(keyValue[0], keyValue[1]);
            lstprof.add(compte);
        }

        ajaxReturn = "";
        try {
            ajaxReturn = login.get("http://192.168.50.54:8100/getAncien","");
        } catch (IOException e) {
            e.printStackTrace();
        }
        pairs = ajaxReturn.replace("[","").replace("\"","").replace("]","").replace("{","").replace("}","").replace("data:image/jpeg;base64,","").split(",");
        for (int i=0;i<pairs.length && pairs.length>=2;i++) {
            String pair = pairs[i];
            String[] keyValue = pair.split(":");
            Compte compte = new Compte(keyValue[0], keyValue[1]);
            lstAncien.add(compte);
        }

        ajaxReturn = "";
        try {
            ajaxReturn = login.get("http://192.168.50.54:8100/getNouveau","");
        } catch (IOException e) {
            e.printStackTrace();
        }
        pairs = ajaxReturn.replace("[","").replace("\"","").replace("]","").replace("{","").replace("}","").replace("data:image/jpeg;base64,","").split(",");
        for (int i=0;i<pairs.length && pairs.length>=2;i++) {
            String pair = pairs[i];
            String[] keyValue = pair.split(":");
            Compte compte = new Compte(keyValue[0], keyValue[1]);
            lstNouveau.add(compte);
        }

        layoutManager = new LinearLayoutManager(Ecole.this);
        venerable.setLayoutManager(layoutManager);
        CompteAdapter adapter = new CompteAdapter(lstVenerable,getApplicationContext());
        venerable.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager1 = layoutManager = new LinearLayoutManager(Ecole.this);
        honte.setLayoutManager(layoutManager1);
        CompteAdapter adapter1 = new CompteAdapter(lstHonte,getApplicationContext());
        honte.setAdapter(adapter1);

        layoutManager = new LinearLayoutManager(Ecole.this);
        prof.setLayoutManager(layoutManager);
        CompteAdapter adapter2 = new CompteAdapter(lstprof,getApplicationContext());
        prof.setAdapter(adapter2);

        layoutManager = new LinearLayoutManager(Ecole.this);
        ancien.setLayoutManager(layoutManager);
        CompteAdapter adapter3 = new CompteAdapter(lstAncien,getApplicationContext());
        ancien.setAdapter(adapter3);

        layoutManager = new LinearLayoutManager(Ecole.this);
        nouveau.setLayoutManager(layoutManager);
        CompteAdapter adapter4 = new CompteAdapter(lstNouveau,getApplicationContext());
        nouveau.setAdapter(adapter4);

    }


}
