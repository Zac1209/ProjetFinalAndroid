package ca.qc.cgodin.projetfinalandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;

public class Kumite extends AppCompatActivity {
    MaClasseLogin login = new MaClasseLogin();
    ArrayList<String> messages = new ArrayList<String>();
    ArrayList<String> spectateurs = new ArrayList<String>();
    ArrayList<String> competiteurs = new ArrayList<String>();
    ArrayList<String> arbitres = new ArrayList<String>();
    ArrayList<String> combattants = new ArrayList<String>();//Ceux qui se battent
    HashMap<String,String> combattantsSavedPosition = new HashMap<>();
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
    Boolean binJoue = false;
    Button btnAction;
    Button btnAction0;
    Button btnAction1;
    RadioGroup group1;
    StompClient mStompClient;
    RadioButton rbSpectateur;
    RadioButton rbCompetiteur;
    RadioButton rbArbitre;
    ImageView avatarIMG;
    TextView tvNom;
    TextView tvCeinture;
    Drawable avatarDefault;
    Drawable combatDefault;
    TextView hiddenID;
    TextView tvCredit;
    TextView tvPoint;
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kumite);


        mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://10.0.2.2:8100/webSocket/websocket");
        mStompClient.connect();
        avatarDefault = getDrawable(R.drawable.avatardefault);
        combatDefault = getDrawable(R.drawable.combatdefault);
        avatarIMG = findViewById(R.id.avatar);
        tvNom = findViewById(R.id.userName);
        tvCeinture = findViewById(R.id.ceinture);
        rbSpectateur = findViewById(R.id.rbSpectateur);
        rbCompetiteur = findViewById(R.id.rbCompetiteur);
        hiddenID = findViewById(R.id.hiddenID);
        rbArbitre = findViewById(R.id.rbArbitre);
        tvCredit = findViewById(R.id.tvCreditKum);
        tvPoint = findViewById(R.id.tvPointKum);
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

        clearCombat();
        clearSpecComp();



        View.OnClickListener radio_listener = new View.OnClickListener (){
            public void onClick(View v) {
                //perform your action here
                if(v==rbSpectateur){
                    envoyerSpectateur(hiddenID.getText().toString());
                }else if(v==rbCompetiteur){
                    envoyerCompetiteur(hiddenID.getText().toString());
                }else if(v==rbArbitre){
                    envoyerArbitre(hiddenID.getText().toString());
                }
            }
        };
        rbSpectateur.setOnClickListener(radio_listener);
        rbCompetiteur.setOnClickListener(radio_listener);
        rbArbitre.setOnClickListener(radio_listener);

        mStompClient.topic("/sujet/positionUpdate").subscribe(topicMessage -> {
            String ajaxReponse = "";
            ajaxReponse = login.get("http://10.0.2.2:8100/getCompetiteurs","");
            competiteurs.clear();
            if(!ajaxReponse.equals("[]"))
                competiteurs = new ArrayList<String>(Arrays.asList(ajaxReponse.replace("[","").replace("]","").replace("data:image/jpeg;base64,","").replace("\"","").split(",")));

            ajaxReponse = login.get("http://10.0.2.2:8100/getSpectateurs","");
            spectateurs.clear();
            if(!ajaxReponse.equals("[]"))
                spectateurs = new ArrayList<String>(Arrays.asList(ajaxReponse.replace("[","").replace("]","").replace("data:image/jpeg;base64,","").replace("\"","").split(",")));

            ajaxReponse = login.get("http://10.0.2.2:8100/getArbitres","");
            arbitres.clear();
            if(!ajaxReponse.equals("[]"))
                arbitres = new ArrayList<String>(Arrays.asList(ajaxReponse.replace("[","").replace("]","").replace("data:image/jpeg;base64,","").replace("\"","").split(",")));
            afficherEstrade();
            String avatar;
            try{
                avatar = (new JSONObject(topicMessage.getPayload()).get("idUser")).toString();
                if(!avatar.equals(""))
                    avatar = login.get("http://10.0.2.2:8100/getAvatarById/" + avatar,"");
            }catch(Exception e){
                avatar = "";
            }
            avatar = avatar.replace("data:image/jpeg;base64,","");
            if(!avatar.equals("")) {
                if (avatarLocal.contains(avatar))
                    commencerCombat();
            }
        });
        mStompClient.topic("/sujet/rei").subscribe(topicMessage -> {
            String avatar;
            try{
                avatar = (new JSONObject(topicMessage.getPayload()).get("idUser")).toString();
            }catch(Exception e){
                avatar = "";
            }
            intCountRei++;
            avatar = login.get("http://10.0.2.2:8100/getAvatarById/"+avatar,"");
            avatar = avatar.replace("data:image/jpeg;base64,","");
            String position = "";
            for (Map.Entry<String,String> entry : combattantsSavedPosition.entrySet()) {
                if (Objects.equals(avatar, entry.getValue())) {
                    position = entry.getKey();
                    break;
                }
            }
            int id = getResources().getIdentifier("combatTxt" + position, "id", this.getBaseContext().getPackageName());
            TextView view = findViewById(id);
            view.setText("Rei!");

            if(avatarLocal.contains(avatar))
                btnAction.setEnabled(false);
            if(intCountRei == 2){//Prêt à commencer
                if(avatarLocal.contains(avatar)){
                    login.get("http://10.0.2.2:8100/saveCombatState/"+getCompteIdByAvatar(arbitreActuel)+"/"+getCompteIdByAvatar(combattants.get(0))+"/10/"+getCompteIdByAvatar(combattants.get(1))+"/2","");
                    mStompClient.send("/sujet/updateCombat","").subscribe();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(binJoue){
                            if(avatarLocal.contains(arbitreActuel) || arbitreActuel.contains(avatarLocal)){//Afficher Hajime! pour l'arbitre
                                btnAction.setText("Hajime!");
                                btnAction.setVisibility(View.VISIBLE);
                            }
                            if(combattants.contains(avatarLocal))
                                btnAction.setVisibility(View.INVISIBLE);
                        }

                    }
                });

            }
        });

        mStompClient.topic("/sujet/hajime").subscribe(topicMessage -> {
            String avatar;
            try{
                String userID = (new JSONObject(topicMessage.getPayload()).get("idUser")).toString();
                avatar = login.get("http://10.0.2.2:8100/getAvatarById/" + userID,"");
            }catch(Exception e){
                avatar = "";
            }
            clearCombatText();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    combatTxt6.setText("Hajime!");//Afficher hajime pour 2 secondes
                }
            });


            setTimeout(() -> clearCombatText(), 2000);

            if(avatar.equals(avatarLocal)){
                String compte1 = "";
                String compte2 = "";
                String compte3 = "";
                compte1 = getCompteIdByAvatar("data:image/jpeg;base64," + arbitreActuel.replace("data:image/jpeg;base64,",""));
                compte2 = getCompteIdByAvatar("data:image/jpeg;base64," + combattants.get(0).replace("data:image/jpeg;base64,",""));
                compte3 = getCompteIdByAvatar("data:image/jpeg;base64," + combattants.get(1).replace("data:image/jpeg;base64,",""));
                login.get("http://10.0.2.2:8100/saveCombatState/"+compte1+"/"+compte2+"/9/"+compte3+"/3","");
                mStompClient.send("/sujet/updateCombat","").subscribe();
                mStompClient.send("/sujet/positionUpdate","{\"avatar\":\"\",\"idUser\":\"\"}").subscribe();
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(binJoue){
                        if(avatarLocal.equals(arbitreActuel)){
                            btnAction.setVisibility(View.INVISIBLE);
                        }else{
                            btnAction0.setVisibility(View.VISIBLE);
                            btnAction.setVisibility(View.VISIBLE);
                            btnAction1.setVisibility(View.VISIBLE);
                            btnAction.setText("Papier");
                            btnAction.setEnabled(true);
                        }
                    }

                }
            });

        });

        mStompClient.topic("/sujet/resultatCombat").subscribe(topicMessage -> {
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
                int id = getResources().getIdentifier("combat" + position, "id", this.getBaseContext().getPackageName());
                ImageView view = findViewById(id);

                Drawable rocheD = getResources().getDrawable(R.drawable.roche);
                Drawable papierD = getResources().getDrawable(R.drawable.papier);
                Drawable ciseauD = getResources().getDrawable(R.drawable.ciseau);
                String finalResult = result;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.setImageDrawable(finalResult.equals("Roche") ? rocheD : finalResult.equals("Papier") ? papierD : ciseauD);
                    }
                });


            }
            if(!avatar.equals("")) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        combatTxt6.setText("Ipon!");
                        if(binJoue){
                            btnAction.setVisibility(View.INVISIBLE);
                            btnAction0.setVisibility(View.INVISIBLE);
                            btnAction1.setVisibility(View.INVISIBLE);
                            btnAction.setText("Rei!");
                        }

                    }
                });

                if (avatar.equals("egal")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            combat7.setTag("victoire");
                            combat7.setImageDrawable(getResources().getDrawable(R.drawable.drapeauvictoire));
                            combat5.setTag("victoire");
                            combat5.setImageDrawable(getResources().getDrawable(R.drawable.drapeauvictoire));
                        }
                    });

                } else {
                    int id = getResources().getIdentifier("combat" + avatar, "id", this.getBaseContext().getPackageName());
                    ImageView view = findViewById(id);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            view.setTag("victoire");
                            view.setImageDrawable(getResources().getDrawable(R.drawable.drapeauvictoire));
                        }
                    });

                }
                if(avatarLocal.equals(arbitreActuel)){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new AlertDialog.Builder(Kumite.this)
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .setTitle("Rester arbitre")
                                    .setMessage("Rester arbitre?")
                                    .setPositiveButton("Oui", new DialogInterface.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            String ajaxResult = "";
                                            try {
                                                ajaxResult = login.get("http://10.0.2.2:8100/arbitreResterEnPlace/" + getCompteIdByAvatar(arbitreActuel),"");
                                                if(ajaxResult.equals(arbitreActuel)){//Il reste
                                                    arbitreTemp = ajaxResult;
                                                }else
                                                    arbitreTemp = "";
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }

                                        }

                                    })
                                    .setNegativeButton("Non", null)
                                    .show();
                        }
                    });

                }
            }
        });

        mStompClient.topic("/sujet/updateCombat").subscribe(topicMessage -> {
            updateCombat();
        });

        mStompClient.topic("/sujet/resetCombat").subscribe(topicMessage -> {
            if(!avatarLocal.equals(arbitreActuel)){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        group1.check(R.id.rbCompetiteur);
                        group1.setEnabled(true);
                    }
                });

            }
            if(arbitreTemp.equals("")){//Reset d'arbitre
                envoyerSpectateur(getCompteIdByAvatar(arbitreActuel));
                if(avatarLocal.equals(arbitreActuel)){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            group1.check(R.id.rbSpectateur);
                            group1.setEnabled(true);
                        }
                    });

                }
                arbitreActuel = "";
                arbitres = new ArrayList<>();
            }else
                arbitreTemp = "";
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    btnAction0.setVisibility(View.INVISIBLE);
                    btnAction.setVisibility(View.INVISIBLE);
                    btnAction1.setVisibility(View.INVISIBLE);
                    btnAction.setText("Rei!");
                }
            });
            intCountRei=0;
            combattants.clear();
            clearCombat();
            clearCombatText();
        });


        Intent intent = getIntent();
        String valeur = intent.getStringExtra("valeur");
        String idCompte = intent.getStringExtra("idCompte");
        String ceintureLive = intent.getStringExtra("ceinture");

        avatarLocal = valeur;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvCeinture.setText(ceintureLive);
            }
        });

        String username = "";
        String credit = "";
        String point = "";
        try {
            username=login.get("http://10.0.2.2:8100/getUsername","");
            point = login.get("http://10.0.2.2:8100/getPoint/" + username,"");
            credit = login.get("http://10.0.2.2:8100/getCredit/" + username,"");
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] decodedString1 = Base64.decode(valeur.replace("data:image/jpeg;base64,",""), Base64.DEFAULT);
        Bitmap decodedByte1 = BitmapFactory.decodeByteArray(decodedString1, 0,decodedString1.length);
        String finalUsername = username;
        String finalCredit = credit;
        String finalPoint = point;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvNom.setText(finalUsername);
                avatarIMG.setImageBitmap(decodedByte1);
                hiddenID.setText(idCompte);
                tvCredit.setText(finalCredit);
                tvPoint.setText(finalPoint);
            }
        });

        envoyerSpectateur(idCompte);
        updateCombat();
        mStompClient.topic("/sujet/updateInfoUser").subscribe(topicMessage -> {
            String topic = topicMessage.getPayload();
            String topicFormatted = topic.substring(1, topic.length()-1);
            String user = (new JSONObject(topicFormatted.replace("\\","")).get("user")).toString();
            if(user.equals(finalUsername)){
                String point1 = login.get("http://10.0.2.2:8100/getPoint/" + finalUsername,"");
                String credit1 = login.get("http://10.0.2.2:8100/getCredit/" + finalUsername,"");
                String ceinture1 = login.get("http://10.0.2.2:8100/getCeinture","");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvCredit.setText(credit1);
                        tvPoint.setText(point1);
                        tvCeinture.setText(ceinture1);
                    }
                });

            }

        });
    }


    @Override
    public void onStop(){
        super.onStop();
        try {
            login.get("http:10.0.2.2:8100/exit/" + hiddenID.getText().toString(),"");
        } catch (IOException e) {
            e.printStackTrace();
        }
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

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    view.setImageDrawable(avatarDefault);
                    view.setTag("vide");
                }
            });


            id = getResources().getIdentifier("comp" + i, "id", this.getBaseContext().getPackageName());
            final ImageView view2 = findViewById(id);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    view2.setImageDrawable(getResources().getDrawable(R.drawable.avatardefault));
                    view2.setTag("vide");
                }
            });

        }
    }

    private void clearCombat(){
        for(int i =1;i<=11;i++){
            int id = getResources().getIdentifier("combat" + i, "id", this.getBaseContext().getPackageName());
            ImageView view = findViewById(id);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    view.setImageDrawable(combatDefault);
                    view.setTag("vide");
                }
            });

        }
    }
    private void clearCombatText(){
        for(int i =1;i<=11;i++){
            int id = getResources().getIdentifier("combatTxt" + i, "id", this.getBaseContext().getPackageName());
            TextView view = findViewById(id);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    view.setText("");
                }
            });

        }
    }

    public void btnActionClick(View v){
        actionClick(hiddenID.getText().toString());
    }

    public void btnRocheClick(View v){
        envoyerResultCombat("","Roche");
    }

    public void btnPCiseaulick(View v){
        envoyerResultCombat("","Ciseau");
    }

    private void actionClick(String userID){
        String btnActionValue = btnAction.getText().toString();
        switch(btnActionValue){
            case "Rei!":
                envoyerRei(userID);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        btnAction.setEnabled(false);
                    }
                });

                break;
            case "Hajime!":
                envoyerHajime(userID);

                break;
            default:
                envoyerResultCombat(userID,"Papier");

        }
    }



    private void afficherCombat(){
        clearCombat();
        ArrayList<String> keyList = new ArrayList<>();
        if(combattantsSavedPosition.keySet().size()!=0)
            keyList = new ArrayList<>(combattantsSavedPosition.keySet());
        for(int i = 0;i<keyList.size();i++){
            String key = keyList.get(i);
            int id = getResources().getIdentifier("combat" + key, "id", this.getBaseContext().getPackageName());
            ImageView view = findViewById(id);

            byte[] decodedString1 = Base64.decode(combattantsSavedPosition.get(key), Base64.DEFAULT);
            Bitmap decodedByte1 = BitmapFactory.decodeByteArray(decodedString1, 0,decodedString1.length);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    view.setImageBitmap(decodedByte1);
                    String user = getCompteIdByAvatar("data:image/jpeg;base64," + combattantsSavedPosition.get(key));

                }
            });

        }
        if(keyList.size()>1){
            byte[] decodedString1 = Base64.decode(arbitreActuel.replace("data:image/jpeg;base64,",""), Base64.DEFAULT);
            Bitmap decodedByte1 = BitmapFactory.decodeByteArray(decodedString1, 0,decodedString1.length);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    combat6.setImageBitmap(decodedByte1);
                }
            });
        }




        if(!avatarLocal.contains(arbitreActuel) && !arbitreActuel.contains(avatarLocal)){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(binJoue)
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

            if(combattants.size() != 0){
                combattants.set(0,competiteurs.get(0));
                combattants.set(1,competiteurs.get(1));
            }else{
                combattants.add(competiteurs.get(0));
                combattants.add(competiteurs.get(1));
            }

            if((competiteurs.get(0).equals(avatarLocal.replace("data:image/jpeg;base64,","")) || competiteurs.get(1).equals(avatarLocal.replace("data:image/jpeg;base64,","")))){
                binJoue = true;
            }else{
                binJoue = false;
            }


            String compte1 = "";
            String compte2 = "";
            String compte3 = "";
            try {
                compte1 = getCompteIdByAvatar("data:image/jpeg;base64," + arbitres.get(0).replace("data:image/jpeg;base64,",""));
                compte2 = getCompteIdByAvatar("data:image/jpeg;base64," + combattants.get(0).replace("data:image/jpeg;base64,",""));
                compte3 = getCompteIdByAvatar("data:image/jpeg;base64," + combattants.get(1).replace("data:image/jpeg;base64,",""));
                login.get("http://10.0.2.2:8100/saveCombatState/"+compte1+"/"+compte2+"/11/"+compte3+"/1","");
            } catch (IOException e) {
                e.printStackTrace();
            }

            mStompClient.send("/sujet/updateCombat","{}").subscribe();
            mStompClient.send("/sujet/positionUpdate","{\"avatar\":\"\",\"idUser\":\"\"}").subscribe();
        }

    }

    private String getCompteIdByAvatar(String avatar){
        String returnString = "";
        try {
            returnString = login.postGetCompteByAvatar("http://10.0.2.2:8100/getCompteByAvatar",avatar);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnString;
    }

    private void envoyerSpectateur(String id) {
        try {
            login.get("http://10.0.2.2:8100/saveSpectateur/" + id,"");

        } catch (IOException e) {
            e.printStackTrace();
        }

        mStompClient.send("/sujet/positionUpdate","{\"idUser\":\""+id+"\"}").subscribe();
    }

    private void envoyerCompetiteur(String id) {
        try {
            login.get("http://10.0.2.2:8100/saveCompetiteur/" + id,"");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mStompClient.send("/sujet/positionUpdate","{\"idUser\":\""+id+"\"}").subscribe();
    }

    private void envoyerArbitre(String id) {
        try {
            login.get("http://10.0.2.2:8100/saveArbitre/" + id,"");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mStompClient.send("/sujet/positionUpdate","{\"idUser\":\""+id+"\"}").subscribe();
    }

    private void envoyerRei(String valeur) {
        mStompClient.send("/sujet/rei","{\"idUser\":\""+valeur+"\"}").subscribe();
    }

    private void envoyerHajime(String valeur) {
        mStompClient.send("/sujet/hajime","{\"idUser\":\""+valeur+"\"}").subscribe();
    }

    private void envoyerResultCombat(String valeur,String result) {
        Bitmap bitmap = ((BitmapDrawable)combat3.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        String combat3SRCString = Base64.encodeToString(byteArray, Base64.DEFAULT);

        int position = combat3SRCString.equals(avatarLocal) ? 4 : 8;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(binJoue){
                    btnAction0.setVisibility(View.INVISIBLE);
                    btnAction.setVisibility(View.INVISIBLE);
                    btnAction1.setVisibility(View.INVISIBLE);
                    btnAction.setText("Rei!");
                }

            }
        });

        String resultToSend = "";
        String ajaxResult = "";
        String compteId;
        try {
            ajaxResult = login.get("http://10.0.2.2:8100/saveCombatResult/" + hiddenID.getText().toString() + "/" + result,"");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!ajaxResult.equals("")){
            resultToSend = ajaxResult;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(binJoue){
                        btnAction0.setVisibility(View.INVISIBLE);
                        btnAction.setVisibility(View.INVISIBLE);
                        btnAction1.setVisibility(View.INVISIBLE);
                    }

                }
            });

        }
        mStompClient.send("/sujet/resultatCombat","{\"avatar\":\""+resultToSend+"\",\"position\":\""+position+"\",\"result\":\""+result+"\"}").subscribe();
    }

    private void updateCombat(){
        String ajaxReturn = "";
        try {
            ajaxReturn = login.get("http://10.0.2.2:8100/getCombattant","");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Integer> myMap = new HashMap<String, Integer>();
        String[] pairs = ajaxReturn.replace("[","").replace("\"","")
                .replace("]","").replace("{","")
                .replace("}","").replace("data:image/jpeg;base64,","")
                .split(",");
        for (int i=0;i<pairs.length && pairs.length==2;i++) {
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
            if(combattants.size() == 2){
                combattants.set(i,keyList.get(i));
            }else
                combattants.add(keyList.get(i));
        }

        try {
            arbitreActuel = login.get("http://10.0.2.2:8100/getArbitreActuel","");
        } catch (IOException e) {
            e.printStackTrace();
        }

        afficherEstrade();
        afficherCombat();
    }

    public void clickDojo(View v){
        Intent intent = new Intent(Kumite.this,MainActivity.class);
        startActivity(intent);
    }

    public GradientDrawable creerContourAvatar(String username){
        GradientDrawable gradientDrawableDefault = new GradientDrawable();
        gradientDrawableDefault.setCornerRadius(5);

        String ceinture = "";
        if(!username.equals("")) {
            try {
                ceinture = login.get("http://10.0.2.2:8100/getCeinture/" + username, "");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        switch (ceinture){
            case "BLANCHE":
                gradientDrawableDefault.setColor(Color.WHITE);
                gradientDrawableDefault.setStroke(1,Color.BLACK);
                break;
            case "JAUNE":
                gradientDrawableDefault.setColor(Color.YELLOW);
                break;
            case "ORANGE":
                int color = Color.rgb(100,65,0);
                gradientDrawableDefault.setColor(color);
                break;
            case "VERTE":
                gradientDrawableDefault.setColor(Color.GREEN);
                break;
            case "BLEUE":
                gradientDrawableDefault.setColor(Color.BLUE);
                break;
            case "MARRON":
                int color1 = Color.rgb(181,101,29);
                gradientDrawableDefault.setColor(color1);
                break;
            case "NOIRE":
                gradientDrawableDefault.setColor(Color.BLACK);
                break;
                default:
                    gradientDrawableDefault.setColor(Color.WHITE);
        }

        return gradientDrawableDefault;
    }


}
