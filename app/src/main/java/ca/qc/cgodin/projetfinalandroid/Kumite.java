package ca.qc.cgodin.projetfinalandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.client.StompClient;

public class Kumite extends AppCompatActivity {
    MaClasseLogin login = new MaClasseLogin();
    ArrayList<String> messages = new ArrayList<String>();
    ArrayList<String> spectateurs = new ArrayList<String>();
    ArrayList<String> competiteurs = new ArrayList<String>();
    ArrayList<String> arbitres = new ArrayList<String>();
    ArrayList<String> combattants = new ArrayList<String>();//Ceux qui se battent
    HashMap<String,String> combattantsSavedPosition = new HashMap<>();
    String avatarDefault;
    String combatDefault;
    String roche;
    String papier;
    String ciseau;
    int intCountRei = 0;
    String avatarLocal;
    ArrayList<String> resultCombat = new ArrayList<String>();
    String arbitreActuel = "";
    String arbitreTemp = "";
    
    ImageView spec1;
    ImageView spec2;
    ImageView spec3;
    ImageView spec4;
    ImageView spec5;
    ImageView spec6;
    ImageView spec7;
    ImageView spec8;
    ImageView spec9;
    ImageView spec10;
    ImageView spec11;
    ImageView spec12;
    ImageView comp1;
    ImageView comp2;
    ImageView comp3;
    ImageView comp4;
    ImageView comp5;
    ImageView comp6;
    ImageView comp7;
    ImageView comp8;
    ImageView comp9;
    ImageView comp10;
    ImageView comp11;
    ImageView comp12;
    ImageView combat1;
    ImageView combat2;
    ImageView combat3;
    ImageView combat4;
    ImageView combat5;
    ImageView combat6;
    ImageView combat7;
    ImageView combat8;
    ImageView combat9;
    ImageView combat10;
    ImageView combat11;
    TextView combatTxt1;
    TextView combatTxt2;
    TextView combatTxt3;
    TextView combatTxt4;
    TextView combatTxt5;
    TextView combatTxt6;
    TextView combatTxt7;
    TextView combatTxt8;
    TextView combatTxt9;
    TextView combatTxt10;
    TextView combatTxt11;

    Button btnAction;
    Button btnAction0;
    Button btnAction1;
    RadioGroup group1;
    StompClient mStompClient;
    RadioButton rbSpectateur;
    RadioButton rbCompetiteur;
    ImageView avatarIMG;
    TextView tvNom;
    TextView tvCeinture;
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kumite);


        mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://192.168.50.54:8100/webSocket/websocket");
        mStompClient.connect();

        avatarIMG = findViewById(R.id.avatar);
        tvNom = findViewById(R.id.userName);
        tvCeinture = findViewById(R.id.ceinture);
        rbSpectateur = findViewById(R.id.rbSpectateur);
        rbCompetiteur = findViewById(R.id.rbCompetiteur);
        avatarDefault = new String(getString(R.string.avatarDefault));
        combatDefault =  new String(getString(R.string.combatDefault));
        roche = new String(getString(R.string.roche));
        papier = new String(getString(R.string.papier));
        ciseau = new String(getString(R.string.ciseau));
        spec1 = findViewById(R.id.spec1);
        spec2 = findViewById(R.id.spec2);
        spec3 = findViewById(R.id.spec3);
        spec4 = findViewById(R.id.spec4);
        spec5 = findViewById(R.id.spec5);
        spec6 = findViewById(R.id.spec6);
        spec7 = findViewById(R.id.spec7);
        spec8 = findViewById(R.id.spec8);
        spec9 = findViewById(R.id.spec9);
        spec10 = findViewById(R.id.spec10);
        spec11 = findViewById(R.id.spec11);
        spec12 = findViewById(R.id.spec12);
        comp1 = findViewById(R.id.comp1);
        comp2 = findViewById(R.id.comp2);
        comp3 = findViewById(R.id.comp3);
        comp4 = findViewById(R.id.comp4);
        comp5 = findViewById(R.id.comp5);
        comp6 = findViewById(R.id.comp6);
        comp7 = findViewById(R.id.comp7);
        comp8 = findViewById(R.id.comp8);
        comp9 = findViewById(R.id.comp9);
        comp10 = findViewById(R.id.comp10);
        comp11 = findViewById(R.id.comp11);
        comp12 = findViewById(R.id.comp12);
        combat1 = findViewById(R.id.combat1);
        combat2 = findViewById(R.id.combat2);
        combat3 = findViewById(R.id.combat3);
        combat4 = findViewById(R.id.combat4);
        combat5 = findViewById(R.id.combat5);
        combat6 = findViewById(R.id.combat6);
        combat7 = findViewById(R.id.combat7);
        combat8 = findViewById(R.id.combat8);
        combat9 = findViewById(R.id.combat9);
        combat10 = findViewById(R.id.combat10);
        combat11 = findViewById(R.id.combat11);
        combatTxt1 = findViewById(R.id.combatTxt1);
        combatTxt2 = findViewById(R.id.combatTxt2);
        combatTxt3 = findViewById(R.id.combatTxt3);
        combatTxt4 = findViewById(R.id.combatTxt4);
        combatTxt5 = findViewById(R.id.combatTxt5);
        combatTxt6 = findViewById(R.id.combatTxt6);
        combatTxt7 = findViewById(R.id.combatTxt7);
        combatTxt8 = findViewById(R.id.combatTxt8);
        combatTxt9 = findViewById(R.id.combatTxt9);
        combatTxt10 = findViewById(R.id.combatTxt10);
        combatTxt11 = findViewById(R.id.combatTxt11);
        btnAction = findViewById(R.id.btnAction);
        btnAction0 = findViewById(R.id.btnAction0);
        btnAction1 = findViewById(R.id.btnAction1);
        group1 = findViewById(R.id.group1);
        
        TextView lblDojo = findViewById(R.id.lblDojo);
        String htmlString="Clicker <font color='#518aff'><u>ici</u></font> pour retourner au dojo\n";
        lblDojo.setText(Html.fromHtml(htmlString));




        mStompClient.topic("/sujet/positionUpdate").subscribe(topicMessage -> {
            String ajaxReponse = "";
            ajaxReponse = login.get("http://192.168.50.54:8100/getCompetiteurs","");
            competiteurs.clear();
            if(!ajaxReponse.equals("[]"))
                competiteurs = new ArrayList<String>(Arrays.asList(ajaxReponse.replace("[","]").replace("]","").replace("data:image/jpeg;base64,","").split(",")));

            ajaxReponse = login.get("http://192.168.50.54:8100/getSpectateurs","");
            spectateurs.clear();
            if(!ajaxReponse.equals("[]"))
                spectateurs = new ArrayList<String>(Arrays.asList(ajaxReponse.replace("[","]").replace("]","").replace("data:image/jpeg;base64,","").split(",")));

            ajaxReponse = login.get("http://192.168.50.54:8100/getArbitres","");
            arbitres.clear();
            if(!ajaxReponse.equals("[]"))
                arbitres = new ArrayList<String>(Arrays.asList(ajaxReponse.replace("[","]").replace("]","").replace("data:image/jpeg;base64,","").split(",")));
            afficherEstrade();
            String avatar;
            try{
                avatar = (new JSONObject(topicMessage.getPayload()).get("avatar")).toString();
            }catch(Exception e){
                avatar = "";
            }
            if(!avatar.equals("")) {
                if (avatar == avatarLocal)
                    commencerCombat();
            }
        });
        mStompClient.topic("/sujet/rei").subscribe(topicMessage -> {
            String avatar;
            try{
                avatar = (new JSONObject(topicMessage.getPayload()).get("avatar")).toString();
            }catch(Exception e){
                avatar = "";
            }
            intCountRei++;
            String position = combattantsSavedPosition.get(avatar);
            int id = getResources().getIdentifier("combatTxt" + position, "id", this.getBaseContext().getPackageName());
            TextView view = findViewById(id);
            view.setText("Rei!");

            if(avatar.equals(avatarLocal))
                btnAction.setEnabled(false);
            if(intCountRei == 2){//Prêt à commencer
                if(avatar == avatarLocal){
                    login.get("http://192.168.50.54:8100/saveCombatState/"+getCompteIdByAvatar(arbitreActuel)+"/"+getCompteIdByAvatar(combattants.get(0))+"/10/"+getCompteIdByAvatar(combattants.get(1))+"/2","");
                    mStompClient.send("/sujet/updateCombat","");
                }

                if(avatarLocal.equals(arbitreActuel)){//Afficher Hajime! pour l'arbitre
                    btnAction.setText("Hajime!");
                    btnAction.setVisibility(View.VISIBLE);
                }
                if(combattants.contains(avatarLocal))
                    btnAction.setVisibility(View.INVISIBLE);
            }
        });

        mStompClient.topic("/sujet/hajime").subscribe(topicMessage -> {
            String avatar;
            try{
                avatar = (new JSONObject(topicMessage.getPayload()).get("avatar")).toString();
            }catch(Exception e){
                avatar = "";
            }
            clearCombatText();
            combatTxt6.setText("Hajime!");//Afficher hajime pour 2 secondes

            setTimeout(() -> clearCombatText(), 2000);

            if(avatar.equals(avatarLocal)){
                login.get("http://192.168.50.54:8100/saveCombatState/"+getCompteIdByAvatar(arbitreActuel)+"/"+getCompteIdByAvatar(combattants.get(0))+"/9/"+getCompteIdByAvatar(combattants.get(1))+"/3","");
                mStompClient.send("/sujet/updateCombat","");
            }

            if(avatarLocal.equals(arbitreActuel)){
                btnAction.setVisibility(View.INVISIBLE);
            }else{
                btnAction0.setVisibility(View.VISIBLE);
                btnAction.setVisibility(View.VISIBLE);
                btnAction1.setVisibility(View.VISIBLE);
                btnAction.setText("Papier");
                btnAction.setEnabled(true);
            }
        });

        mStompClient.topic("/sujet/hajime").subscribe(topicMessage -> {
            String avatar;
            String position;
            String result;
            try{
                avatar = (new JSONObject(topicMessage.getPayload()).get("avatar")).toString();
                position = (new JSONObject(topicMessage.getPayload()).get("position")).toString();
                result = (new JSONObject(topicMessage.getPayload()).get("result")).toString();
            }catch(Exception e){
                avatar = "";
                position = "";
                result = "";
            }

            if(!position.equals("")) {
                int id = getResources().getIdentifier("'#combat'" + position, "id", this.getBaseContext().getPackageName());
                ImageView view = findViewById(id);
                byte[] decodedString1 = Base64.decode(roche, Base64.DEFAULT);
                Bitmap rocheBIT = BitmapFactory.decodeByteArray(decodedString1, 0,decodedString1.length);
                decodedString1 = Base64.decode(papier, Base64.DEFAULT);
                Bitmap papierBIT = BitmapFactory.decodeByteArray(decodedString1, 0,decodedString1.length);
                decodedString1 = Base64.decode(ciseau, Base64.DEFAULT);
                Bitmap ciseauBIT = BitmapFactory.decodeByteArray(decodedString1, 0,decodedString1.length);
                view.setImageBitmap(result.equals("Roche") ? rocheBIT : result.equals("Papier") ? papierBIT : ciseauBIT);
            }
            if(!avatar.equals("")) {
                combatTxt6.setText("Ipon!");
                btnAction.setVisibility(View.INVISIBLE);
                btnAction0.setVisibility(View.INVISIBLE);
                btnAction1.setVisibility(View.INVISIBLE);
                btnAction.setText("Rei!");
                if (avatar.equals("egal")) {
                    byte[] decodedString1 = Base64.decode(getString(R.string.drapeauVictoire), Base64.DEFAULT);
                    Bitmap victoire = BitmapFactory.decodeByteArray(decodedString1, 0,decodedString1.length);
                    combat7.setTag("victoire");
                    combat7.setImageBitmap(victoire);
                    combat5.setTag("victoire");
                    combat5.setImageBitmap(victoire);
                } else {
                    int id = getResources().getIdentifier("'#combat'" + avatar, "id", this.getBaseContext().getPackageName());
                    ImageView view = findViewById(id);
                    byte[] decodedString1 = Base64.decode(getString(R.string.drapeauVictoire), Base64.DEFAULT);
                    Bitmap victoire = BitmapFactory.decodeByteArray(decodedString1, 0,decodedString1.length);
                    view.setTag("victoire");
                    view.setImageBitmap(victoire);
                }
                if(avatarLocal.equals(arbitreActuel)){
                    new AlertDialog.Builder(this)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("Rester arbitre")
                            .setMessage("Rester arbitre?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String ajaxResult = "";
                                    try {
                                        ajaxResult = login.get("http://192.168.50.54:8100/arbitreResterEnPlace/" + getCompteIdByAvatar(arbitreActuel),"");
                                        if(ajaxResult.equals(arbitreActuel)){//Il reste
                                            arbitreTemp = ajaxResult;
                                        }else
                                            arbitreTemp = "";
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                }

                            })
                            .setNegativeButton("No", null)
                            .show();
                }
            }
        });

        mStompClient.topic("/sujet/updateCombat").subscribe(topicMessage -> {
            updateCombat();
        });

        mStompClient.topic("/sujet/resetCombat").subscribe(topicMessage -> {
            if(!avatarLocal.equals(arbitreActuel)){
                group1.check(R.id.rbCompetiteur);
                group1.setEnabled(true);
            }
            if(arbitreTemp.equals("")){//Reset d'arbitre
                envoyerSpectateur(arbitreActuel,getCompteIdByAvatar(arbitreActuel));
                if(avatarLocal.equals(arbitreActuel)){
                    group1.check(R.id.rbSpectateur);
                    group1.setEnabled(true);
                }
                arbitreActuel = "";
                arbitres = new ArrayList<>();
            }else
                arbitreTemp = "";
            envoyerCompetiteur(combattants.get(0),getCompteIdByAvatar(combattants.get(0)));
            envoyerCompetiteur(combattants.get(1),getCompteIdByAvatar(combattants.get(1)));
            combattants.clear();
            clearCombat();
            clearCombatText();
        });
        Intent intent = getIntent();
        String valeur = intent.getStringExtra("valeur");
        String idCompte = intent.getStringExtra("idCompte");
        String ceintureLive = intent.getStringExtra("ceinture");
        envoyerSpectateur(valeur,idCompte);
        avatarLocal = valeur;

        tvCeinture.setText(ceintureLive);
        String username = "";
        try {
            username=login.get("http://192.168.50.54:8100/getUsername","");
        } catch (IOException e) {
            e.printStackTrace();
        }
        tvNom.setText(username);
        byte[] decodedString1 = Base64.decode(valeur, Base64.DEFAULT);
        Bitmap decodedByte1 = BitmapFactory.decodeByteArray(decodedString1, 0,decodedString1.length);
        avatarIMG.setImageBitmap(decodedByte1);
    }


    public void onClickDojo(View v) {
        Intent myIntent = new Intent(v.getContext(), MainActivity.class);
        startActivity(myIntent);
    }



    private void afficherEstrade(){
        clearSpecComp();
        for(int i =1;i<=spectateurs.size();i++){
            int id = getResources().getIdentifier("spec" + i, "id", this.getBaseContext().getPackageName());
            ImageView view = findViewById(id);

            if(view.getTag().equals("vide")){
                byte[] decodedString1 = Base64.decode(spectateurs.get(i-1), Base64.DEFAULT);
                Bitmap decodedByte1 = BitmapFactory.decodeByteArray(decodedString1, 0,decodedString1.length);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.setImageBitmap(decodedByte1);
                        view.setTag("avatar");
                    }
                });

            }
        }
        for(int i =1;i<=competiteurs.size();i++){
            int id = getResources().getIdentifier("comp" + i, "id", this.getBaseContext().getPackageName());
            ImageView view = findViewById(id);

            if(view.getTag().equals("vide")){
                byte[] decodedString1 = Base64.decode(competiteurs.get(i-1), Base64.DEFAULT);
                Bitmap decodedByte1 = BitmapFactory.decodeByteArray(decodedString1, 0,decodedString1.length);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.setImageBitmap(decodedByte1);
                        view.setTag("avatar");
                    }
                });
            }
        }
    }

    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }

    private void clearSpecComp(){
        for(int i =1;i<=12;i++){
            int id = getResources().getIdentifier("spec" + i, "id", this.getBaseContext().getPackageName());
            final ImageView view = findViewById(id);
            byte[] decodedString1 = Base64.decode(avatarDefault, Base64.DEFAULT);
            Bitmap decodedByte1 = BitmapFactory.decodeByteArray(decodedString1, 0,decodedString1.length);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    view.setImageBitmap(decodedByte1);
                    view.setTag("vide");
                }
            });


            id = getResources().getIdentifier("comp" + i, "id", this.getBaseContext().getPackageName());
            final ImageView view2 = findViewById(id);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    view2.setImageBitmap(decodedByte1);
                    view2.setTag("vide");
                }
            });

        }
    }

    private void clearCombat(){
        for(int i =1;i<=11;i++){
            int id = getResources().getIdentifier("combat" + i, "id", this.getBaseContext().getPackageName());
            ImageView view = findViewById(id);
            byte[] decodedString1 = Base64.decode(avatarDefault, Base64.DEFAULT);
            Bitmap decodedByte1 = BitmapFactory.decodeByteArray(decodedString1, 0,decodedString1.length);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    view.setImageBitmap(decodedByte1);
                    view.setTag("vide");
                }
            });

        }
    }
    private void clearCombatText(){
        for(int i =1;i<=11;i++){
            int id = getResources().getIdentifier("'combatTxt'" + i, "id", this.getBaseContext().getPackageName());
            TextView view = findViewById(id);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    view.setText("");
                }
            });

        }
    }

    private void actionClick(String avatar){
        String btnActionValue = btnAction.getTag().toString();
        switch(btnActionValue){
            case "Rei!":
                envoyerRei(avatar);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        btnAction.setEnabled(false);
                    }
                });

                break;
            case "Hajime!":
                envoyerHajime(avatar);

                break;
            default:
                envoyerResultCombat(avatar,"Papier");

        }
    }



    private void afficherCombat(){
        clearCombat();
        ArrayList<String> keyList = new ArrayList<>();
        if(combattantsSavedPosition.keySet().size()!=0)
            keyList = new ArrayList<>(combattantsSavedPosition.keySet());
        for(int i = 0;i<keyList.size();i++){
            String key = keyList.get(i);
            int id = getResources().getIdentifier("'combat'" + i, "id", this.getBaseContext().getPackageName());
            ImageView view = findViewById(id);
            byte[] decodedString1 = Base64.decode(combattantsSavedPosition.get(key), Base64.DEFAULT);
            Bitmap decodedByte1 = BitmapFactory.decodeByteArray(decodedString1, 0,decodedString1.length);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    view.setImageBitmap(decodedByte1);
                }
            });

        }

        byte[] decodedString1 = Base64.decode(arbitreActuel, Base64.DEFAULT);
        Bitmap decodedByte1 = BitmapFactory.decodeByteArray(decodedString1, 0,decodedString1.length);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                combat6.setImageBitmap(decodedByte1);
            }
        });


        if(avatarLocal != arbitreActuel){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    btnAction.setVisibility(View.VISIBLE);
                }
            });
        }

    }

    private void commencerCombat(){
        if(competiteurs.size() >= 2 && arbitres.size() >= 1){//Il y a assez de monde pour un combat
            //Initier le combat
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    group1.setEnabled(false);
                }
            });


            combattants.set(0,competiteurs.get(0));
            combattants.set(1,competiteurs.get(1));

            try {
                login.get("http://192.168.50.54:8100/saveCombatState/"+getCompteIdByAvatar(arbitres.get(0))+"/"+getCompteIdByAvatar(combattants.get(0))+"/11/"+getCompteIdByAvatar(combattants.get(1))+"/1","");
            } catch (IOException e) {
                e.printStackTrace();
            }

            mStompClient.send("/sujet/updateCombat");
            mStompClient.send("/sujet/positionUpdate","{\"avatar\":\"null\",\"idUser\":\"null\"}");
        }

    }

    private String getCompteIdByAvatar(String avatar){
        String returnString = "";
        try {
            returnString = login.postGetCompteByAvatar("http://192.168.50.54:8100/saveCombatState",avatar);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnString;
    }

    private void envoyerSpectateur(String valeur, String id) {
        try {
            login.get("http://192.168.50.54:8100/saveSpectateur/" + id,"");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mStompClient.send("/sujet/positionUpdate","{\"avatar\":\""+valeur+"\",\"idUser\":\""+id+"\"}");
    }

    private void envoyerCompetiteur(String valeur,String id) {
        try {
            login.get("http://192.168.50.54:8100/saveCompetiteur/" + id,"");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mStompClient.send("/sujet/positionUpdate","{\"avatar\":\""+valeur+"\",\"idUser\":\""+id+"\"}");
    }

    private void envoyerArbitre(String valeur, String id) {
        try {
            login.get("http://192.168.50.54:8100/saveArbitre/" + id,"");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mStompClient.send("/sujet/positionUpdate","{\"avatar\":\""+valeur+"\",\"idUser\":\""+id+"\"}");
    }

    private void envoyerRei(String valeur) {
        mStompClient.send("/sujet/rei","{\"avatar\":\""+valeur+"\"}");
    }

    private void envoyerHajime(String valeur) {
        mStompClient.send("/sujet/hajime","{\"avatar\":\""+valeur+"\"}");
    }

    private void envoyerResultCombat(String valeur,String result) {
        Bitmap bitmap = ((BitmapDrawable)combat3.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        String combat3SRCString = Base64.encodeToString(byteArray, Base64.DEFAULT);

        int position = combat3SRCString == avatarLocal ? 4 : 8;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                btnAction0.setVisibility(View.INVISIBLE);
                btnAction.setVisibility(View.INVISIBLE);
                btnAction1.setVisibility(View.INVISIBLE);
                btnAction.setText("Rei!");
            }
        });

        String resultToSend = "";
        String ajaxResult = "";
        try {
            ajaxResult = login.get("http://192.168.50.54:8100/saveCombatResult/"+ getCompteIdByAvatar(avatarLocal) + "/" + result,"");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(ajaxResult != ""){
            resultToSend = ajaxResult;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    btnAction0.setVisibility(View.INVISIBLE);
                    btnAction.setVisibility(View.INVISIBLE);
                    btnAction1.setVisibility(View.INVISIBLE);
                }
            });

        }
        mStompClient.send("/sujet/resultatCombat","{\"avatar\":\""+resultToSend+"\",\"position\":\""+position+"\",\"'result'\":\""+result+"\"}");
    }

    private void updateCombat(){
        String ajaxReturn = "";
        try {
            ajaxReturn = login.get("http://192.168.50.54:8100/getCombattant","");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Integer> myMap = new HashMap<String, Integer>();
        String[] pairs = ajaxReturn.split(",");
        for (int i=0;i<pairs.length;i++) {
            String pair = pairs[i];
            String[] keyValue = pair.split(":");
            myMap.put(keyValue[0], Integer.valueOf(keyValue[1]));
        }
        ArrayList<String> keyList = new ArrayList<>();
        if(myMap.keySet().size() != 0)
            keyList = new ArrayList<>( myMap.keySet() );
        combattantsSavedPosition = new HashMap();
        for(int i = 0;i<keyList.size();i++){
            combattantsSavedPosition.put(myMap.get(keyList.get(i)).toString(),keyList.get(i));
            combattants.set(i,keyList.get(i));
        }

        try {
            arbitreActuel = login.get("http://192.168.50.54:8100/getArbitreActuel","");
        } catch (IOException e) {
            e.printStackTrace();
        }

        afficherEstrade();
        afficherCombat();
    }


}
