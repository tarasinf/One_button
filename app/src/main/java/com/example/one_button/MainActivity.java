package com.example.one_button;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


import org.json.JSONArray;
import org.json.JSONException;

/*Це Сєва і я редагую твій проект*/
public class MainActivity extends ActionBarActivity {

    private EditText editText;
    private EditText editText2;
    private TextView textView;
    private TextView button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText =(EditText)findViewById(R.id.editText);
        editText2 =(EditText)findViewById(R.id.editText2);
        textView = (TextView)findViewById(R.id.textView);
        button = (Button)findViewById(R.id.button);

        initListeners();
       // button.setOnClickListener((View.OnClickListener) this);

        //editText.setText("132456");
    }



    private void initListeners(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num_1,num_2;
                num_1 = Integer.parseInt(editText.getText().toString());
                num_2 = Integer.parseInt(editText2.getText().toString());

                /*Тут можна б було
                int num_1 = Integer.parseInt(editText.getText().toString());
                int num_2 = Integer.parseInt(editText2.getText().toString());*/

                /*І ще чогось всі надають перевагу Integer.valueOf(String str)*/

                textView.setText(String.valueOf(num_1+num_2));
                /*
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("sum",String.valueOf(num_1+num_2));
                startActivity(intent);
                */
                post_t();
            }
        });
    }

    private void post_t(){
        String URL = "/volley/resource/all?count=20";
        JsonArrayRequest req = new JsonArrayRequest(URL, new Response.Listener<JSONArray> () {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    VolleyLog.v("Response:%n %s", response.toString(4));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

// add the request object to the queue to be executed
        ApplicationController.getInstance().addToRequestQueue(req);
    }


    // ці штуки можна видалити, вони в тебе не потрібні. Це меню в екшн барі


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
