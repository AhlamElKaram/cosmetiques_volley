package com.example.cosmetiques_volley;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.util.HashMap;



public class MainActivity extends AppCompatActivity {
private EditText x1,x2,x3;
private Button enregistrer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enregistrer = (Button) findViewById(R.id.enregistrer);
        x1=(EditText)findViewById(R.id.name);
        x2=(EditText)findViewById(R.id.type);
        x3=(EditText)findViewById(R.id.ref);




        enregistrer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String name = x1.getText().toString();
                String type = x2.getText().toString();
                String ref = x3.getText().toString();

                if (name.equals(null)) {
                    x1.setError("please enter name");
                    x1.requestFocus();
                }

                if (type.equals(null)) {
                    x2.setError("please enter type");
                    x2.requestFocus();
                }
                if (ref.equals(null)) {
                    x3.setError("please enter ref");
                    x3.requestFocus();
                }
                if (!name.equals(null) && !type.equals(null) && !ref.equals(null)) {
                    submitUsersToDatabase(name, type, ref);
                }
            }

    });
        }
    private void submitUsersToDatabase(final String name,final String type,final String ref){
            String url="http://172.20.160.1/Android/InsertingDATA.php";

            RequestQueue requestQueue=Volley.newRequestQueue(this);

            StringRequest stringRequest=new StringRequest(Request.Method.POST,url,new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                Log.d("response",response);
            }
            },new Response.ErrorListener(){

                @Override
                public void  onErrorResponse(VolleyError error){
                Log.d("error",""+error);
                }


        }){
                protected HashMap<String, String>getParams() throws AuthFailureError {

                    HashMap<String,String> map=new HashMap<>();
                    map.put("name",name);
                    map.put("type",type);
                    map.put("ref",ref);

                    return map;

            }

    };
            requestQueue.add(stringRequest);

    }
}



