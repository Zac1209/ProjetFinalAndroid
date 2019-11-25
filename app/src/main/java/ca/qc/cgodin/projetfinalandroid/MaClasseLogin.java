package ca.qc.cgodin.projetfinalandroid;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MaClasseLogin {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    static public String JSESSIONID = new String();

    public String JSESSIONIDETTOUTLERESTE = new String();

    OkHttpClient client = new OkHttpClient();
    static String user;
    static String pass;


    public Boolean etablirConnexion(String user1, String pass1)   {
//https://square.github.io/okhttp/
        boolean binSuccess = true;
        user = user1;
        pass = pass1;
        Log.d("STOMP", "etablirConnexion()");
        String json = "";
        String url = "http://10.0.2.2:8100/login";
        try {
            postLogin(url ,json,user, pass);
        } catch (IOException e) {
            e.printStackTrace();
            binSuccess = false;
        }

        Log.d("STOMP", "JSESSIONID=" + JSESSIONID);
        return binSuccess;
    }

    public void terminerConnexion(String user, String pass)   {
//https://square.github.io/okhttp/
//https://github.com/square/okhttp/wiki/Recipes
        Log.d("STOMP", "terminerConnexion()");
        String json = "";
        String url = "http://10.0.2.2:8100/logout";
        try {
            postLogin(url ,json,user, pass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSESSIONID="";
    }



    public void test()   {
//https://square.github.io/okhttp/
        //Log.d("STOMP", "dateHeure()");
        String json = "";
        String url = "http://10.0.2.2:8100/test";
        try {
            postLogin(url ,json,null,null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    String postLogin(String url, String json, String user, String pass) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("username", user)
                .add("password", pass)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("rest","oui")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful())
        {
            String cookies = response.header("Set-Cookie");
            Login.SESSIONREST = response.header("SESSIONREST");
            Log.d("STOMP", "mainActivity.SESSIONREST:" + Login.SESSIONREST);
            if (cookies != null) {
                JSESSIONIDETTOUTLERESTE=cookies;
                String[]  cookiesSplit = cookies.split(";");
                JSESSIONID = "JSESSIONID=PAS DE JSESSIONID";
                for (String cookie : cookiesSplit) {
                    String[] cookieSplit = cookie.split("=");
                    if ((cookieSplit[0] != null) && (cookieSplit[0].trim().matches("JSESSIONID"))) {
                        if (cookieSplit[1] != null)
                            JSESSIONID = cookieSplit[1].trim();
                        else
                            JSESSIONID = "JSESSIONID=VIDE";
                    }
                }
            }
        }
        else
            JSESSIONID = "ERREUR LOGIN";
        return response.body().string();
    }

    String postAfterLogin(String url, String json) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("username", "v1@dojo")
                .add("password", "Patate123")

                .build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("cookie", "JSESSIONID=" + JSESSIONID )
                .addHeader("rest","oui")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(formBody)

                .build();

        Response response = client.newCall(request).execute();
        //Attention, on ne peut pas lire plus d'une fois la valeur de response.body().string()????
        //Voilà pourquoi, j'utilise et retourne responseData.

        final String responseData = response.body().string();
        if (response.isSuccessful()) {
            Log.d("STOMP", " post2()=" + responseData);

        }
        else {

            Log.d("STOMP", " post2()=ERREUR");
        }
        return responseData;
    }

    String postGetCompteByAvatar(String url, String avatar) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .addEncoded("avatar", avatar)

                .build();
        RequestBody tes = RequestBody.create(MediaType.get("application/octet-stream"),avatar.getBytes());

        Request request = new Request.Builder()
                .url(url)
                .addHeader("cookie", "JSESSIONID=" + JSESSIONID )
                .addHeader("rest","oui")
                .addHeader("Content-Type", "text/plain")
                .post(tes)

                .build();

        Response response = client.newCall(request).execute();
        //Attention, on ne peut pas lire plus d'une fois la valeur de response.body().string()????
        //Voilà pourquoi, j'utilise et retourne responseData.

        final String responseData = response.body().string();
        if (response.isSuccessful()) {
            Log.d("STOMP", " post2()=" + responseData);

        }
        else {

            Log.d("STOMP", " post2()=ERREUR");
        }
        return responseData;
    }

    String get(String url, String json) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("cookie", "JSESSIONID=" + JSESSIONID )
                .addHeader("rest","oui")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")

                .build();

        Response response = client.newCall(request).execute();
        //Attention, on ne peut pas lire plus d'une fois la valeur de response.body().string()????
        //Voilà pourquoi, j'utilise et retourne responseData.

        String responseData = response.body().string();
        if (response.isSuccessful()) {
            Log.d("STOMP", " post2()=" + responseData);

        }
        else {
            try {
                String response1 = (new JSONObject(responseData)).getString("error");
                if(response1.equals("Unauthorized")){
                    responseData= "Unauthorized";
                }else{
                    responseData = "";
                    Log.d("STOMP", " get()=ERREUR");
                }

            } catch (JSONException e) {
                responseData = "";
                Log.d("STOMP", " get()=ERREUR");
                e.printStackTrace();
            }

        }
        return responseData;
    }

}
