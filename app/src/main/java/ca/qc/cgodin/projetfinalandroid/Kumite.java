package ca.qc.cgodin.projetfinalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class Kumite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kumite);


        TextView lblDojo = findViewById(R.id.lblDojo);
        String htmlString="Clicker <font color='#518aff'><u>ici</u></font> pour retourner au dojo\n";
        lblDojo.setText(Html.fromHtml(htmlString));
    }


    public void onClickDojo(View v) {
        Intent myIntent = new Intent(v.getContext(), MainActivity.class);
        startActivity(myIntent);
    }
}
