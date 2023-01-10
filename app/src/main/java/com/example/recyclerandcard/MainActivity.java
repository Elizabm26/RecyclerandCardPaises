package com.example.recyclerandcard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import Adaptador.PaisAdapter;
import Model.Pais;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerPais;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerPais = (RecyclerView) findViewById(R.id.rcv_pais);
        recyclerPais.setHasFixedSize(true);
        recyclerPais.setLayoutManager(new LinearLayoutManager(this));
        recyclerPais.setItemAnimator(new DefaultItemAnimator());

        Bundle bundle = this.getIntent().getExtras();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://www.geognos.com/api/en/countries/info/all.json";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<Pais> paises = new ArrayList<Pais>();
                        try {
                            JSONObject Jsonpais = new JSONObject(response);
                            JSONObject Jsonpaises = Jsonpais.getJSONObject("Results");
                            Iterator<String> codigoPais = Jsonpaises.keys();
                            while (codigoPais.hasNext())
                                paises.add(new Pais(Jsonpaises.getJSONObject(codigoPais.next())));

                            int resId = R.anim.layout_animation_down_to_up;
                            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(), resId);
                            recyclerPais.setLayoutAnimation(animation);
                            PaisAdapter adapterpais = new PaisAdapter(getApplicationContext(), paises);
                            recyclerPais.setAdapter(adapterpais);

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}