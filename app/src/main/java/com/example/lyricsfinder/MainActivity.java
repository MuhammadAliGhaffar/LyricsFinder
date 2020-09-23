package com.example.lyricsfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    private EditText edtArtistName;
    private EditText edtSongName;
    private TextView txtLyrics;
    private Button btnGetLyrics;

    RequestQueue requestQueue;

    // https://api.lyrics.ovh/v1/Rihanna/Diamonds#   URLEncoder.encode

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtArtistName=findViewById(R.id.edtArtistName);
        edtSongName=findViewById(R.id.edtSongName);
        txtLyrics=findViewById(R.id.txtLyrics);
        btnGetLyrics=findViewById(R.id.btnGetLyrics);

        btnGetLyrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://api.lyrics.ovh/v1/" + edtArtistName.getText().toString() + "/" + edtSongName.getText().toString();
                url=url.replaceAll(" ","%20");
                //url= URLEncoder.encode(url);


                requestQueue = Volley.newRequestQueue(MainActivity.this);

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response.toString());
                            txtLyrics.setText(jsonObject.getString("lyrics"));
                        }catch (JSONException e){
                            e.printStackTrace();
                        }






                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {



                            }
                        });

                requestQueue.add(jsonObjectRequest);


            }
        });





    }
}
