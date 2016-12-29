package com.example.shadab.homeautomation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class HomePage extends AppCompatActivity {
    private Toolbar toolbar;
    private Switch fanswitch, lightswitch, musicswitch, acswitch;
    private int fanflag = 0, lightflag = 0, musicflag = 0, acflag = 0;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private RequestQueue queue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nvView);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("My Home");

        fanswitch = (Switch) findViewById(R.id.fanswitch);
        lightswitch = (Switch) findViewById(R.id.lightswitch);
        musicswitch = (Switch) findViewById(R.id.musicswitch);
        acswitch = (Switch) findViewById(R.id.acswitch);

        VolleySingleton volleySingleton=VolleySingleton.getsInstance();
        // Instantiate the RequestQueue.
         queue =volleySingleton.getRequestQueue() ;
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                return false;

            }
        });

        View headerLayout =
                mNavigationView.inflateHeaderView(R.layout.nv_header);
        fanswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    sendRequest("https://api.thingspeak.com/update?api_key=QIJ86HUNRI2MPH9R&field1=1");

                }else{
                    Toast.makeText(HomePage.this,"trigger false",Toast.LENGTH_SHORT).show();
                    sendRequest("https://api.thingspeak.com/update?api_key=QIJ86HUNRI2MPH9R&field1=0");

                }


            }
        });


      /*  // Instantiate the RequestQueue.
       queue = Volley.newRequestQueue(this);*/


        fanswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  if(fanflag==0)
                    fanflag=1;
                else
                    fanflag=0;
                String url = "https://api.thingspeak.com/update?api_key=QIJ86HUNRI2MPH9R&field1=fanflag";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                Toast.makeText(HomePage.this, "Fan api triggered successfully", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                });
                // Add the request to the RequestQueue.
                queue.add(stringRequest);
*/
               /* if(fanflag==0)
                {

                    Toast.makeText(HomePage.this,"Fan is on",Toast.LENGTH_SHORT).show();
                    fanflag=1;
                }
                else
                {
                    Toast.makeText(HomePage.this,"Fan is off",Toast.LENGTH_SHORT).show();
                    fanflag=0;
                }*/
            }
        });

        lightswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lightflag == 0) {
                    Toast.makeText(HomePage.this, "Light is on", Toast.LENGTH_SHORT).show();
                    lightflag = 1;
                } else {
                    Toast.makeText(HomePage.this, "Light is off", Toast.LENGTH_SHORT).show();
                    lightflag = 0;
                }
            }
        });
        musicswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (musicflag == 0) {
                    Toast.makeText(HomePage.this, "Music is on", Toast.LENGTH_SHORT).show();
                    musicflag = 1;
                } else {
                    Toast.makeText(HomePage.this, "Music is off", Toast.LENGTH_SHORT).show();
                    musicflag = 0;
                }
            }
        });
        acswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (acflag == 0) {
                    Toast.makeText(HomePage.this, "AC is on", Toast.LENGTH_SHORT).show();
                    acflag = 1;
                } else {
                    Toast.makeText(HomePage.this, "AC is off", Toast.LENGTH_SHORT).show();
                    acflag = 0;
                }
            }
        });

    }

    private void sendRequest(final String url) {

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("logg","url "+url+"\n"+response);
                    Toast.makeText(HomePage.this,"successful trigger",Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


      /*  @Override
    public void onClick(View view) {
        if(view.getId()==R.id.fanswitch)
        {
            if(fanflag==0) {
                Toast.makeText(HomePage.this, "Fan is on", Toast.LENGTH_SHORT).show();
                fanflag=1;
            }
            if(fanflag==1) {
                Toast.makeText(HomePage.this, "Fan is off", Toast.LENGTH_SHORT).show();
                fanflag=0;
            }

        }
        if(view.getId()==R.id.lightswitch)
        {
            if(lightflag==0) {
                Toast.makeText(HomePage.this, "Light is on", Toast.LENGTH_SHORT).show();
                lightflag=1;
            }
            if(lightflag==1) {
                Toast.makeText(HomePage.this, "Lighth is off", Toast.LENGTH_SHORT).show();
                lightflag=0;
            }

        }
        if(view.getId()==R.id.musicswitch)
        {
            if(musicflag==0) {
                Toast.makeText(HomePage.this, "Music is on", Toast.LENGTH_SHORT).show();
                musicflag=1;
            }
            if(musicflag==1) {
                Toast.makeText(HomePage.this, "Music is off", Toast.LENGTH_SHORT).show();
                musicflag=0;
            }

        }
        if(view.getId()==R.id.acswitch)
        {
            if(acflag==0) {
                Toast.makeText(HomePage.this, "AC is on", Toast.LENGTH_SHORT).show();
                acflag=1;
            }
            if(acflag==1) {
                Toast.makeText(HomePage.this, "AC is off", Toast.LENGTH_SHORT).show();
                acflag=0;
            }

        }

    }*/
}
