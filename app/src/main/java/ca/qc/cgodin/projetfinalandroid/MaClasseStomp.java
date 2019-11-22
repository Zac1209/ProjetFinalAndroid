package ca.qc.cgodin.projetfinalandroid;

import android.util.Log;
import android.widget.TextView;

import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.client.StompClient;



//https://github.com/NaikSoftware/StompProtocolAndroid
public class MaClasseStomp {


    private StompClient mStompClient;


    public void brancherStomp()

    {
        Log.d("STOMP","brancherStomp");
        //mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://10.0.2.2:8100/websocketandroid");

        //mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://10.0.2.2:8100/websocketandroid");
        mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://10.0.2.2:8100/websocketandroid");
/*
        mStompClient.lifecycle().subscribe(lifecycleEvent -> {
            switch (lifecycleEvent.getType()) {

                case OPENED:
                    Log.d("STOMP", "Stomp connection opened");
                    break;

                case ERROR:
                    Log.e("STOMP", "Error", lifecycleEvent.getException());
                    break;

                case CLOSED:
                    Log.d("STOMP", "Stomp connection closed");
                    break;
            }
        });
*/
        Log.d("STOMP","brancherStomp-over");
        mStompClient.connect();
        Log.d("STOMP","brancherStomp-connect");

        mStompClient.topic("/message/reponseandroid").subscribe(topicMessage -> {
            afficherMessage((String)topicMessage.getPayload() );
            Log.d("STOMP", topicMessage.getPayload());
        });

    }
    public void terminerStomp()
    {
        Log.d("STOMP","terminerStomp");
        mStompClient.disconnect();
    }

    public void envoyerStomp()
    {
        Log.d("STOMP","envoyerStomp");
        Long maintenant = System.currentTimeMillis();

        String t = new Long(maintenant).toString() ;

        mStompClient.send("/messages/message/android",  "{\"SESSIONREST\":\"" + Login.SESSIONREST + "\",\"de\":\"Vénérable\",\"texte\":\"envoyé par Android\",\"creation\":" + t + ",\"id_avatar\":\"v1@dojo\"}").subscribe();

    }


    public void afficherMessage(final String message){
        final String s = message;

        /*MainActivity.messageTextView.post(new Runnable() {
            @Override
            public void run() {
                MainActivity.messageTextView.setText(s);
            }
        });*/


    }

}

