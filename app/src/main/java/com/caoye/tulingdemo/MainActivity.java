package com.caoye.tulingdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity { //implements HttpURLConnListener {
    //private HttpURLConnRequest httpURLConnRequest;
    private List<ListData> list = new ArrayList<>();
    private ListData listData;
    private ListView listView;
    private EditText sendText;
    private Button sendButton;

    private String content_Str;
    private TextAdapter adapter;

    private double curTime, oldTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        sendText = (EditText) findViewById(R.id.sendText);
        sendButton = (Button) findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkListSize();

                content_Str = sendText.getText().toString();
                sendText.setText(null);
                listData = new ListData(content_Str, ListData.SEND, getTime());
                list.add(listData);
                adapter.notifyDataSetChanged();
                String content = content_Str.replace(" ", "").replace("\n", "");

                /**
                *HttpURLConnection Request
                httpURLConnRequest = (HttpURLConnRequest) new HttpURLConnRequest(AppConstant.API_URL, content, MainActivity.this).execute();
                */

                HashMap<String, String> params = getVolleyReqData(content);
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest jsonReq = new JsonObjectRequest(AppConstant.API_URL, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    parseJson(response);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                System.out.println(error.toString());
                            }
                        });
                requestQueue.add(jsonReq);
            }
        });
        adapter = new TextAdapter(list, this);
        listView.setAdapter(adapter);
        showWelcomeTips();
    }

    private void showWelcomeTips() {
        String welcome = this.getResources().getString(R.string.welcome);
        listData = new ListData(welcome, ListData.RECEIVE, getTime());
        list.add(listData);
    }

    /**
     * Get Volley request parameters
     * @param content
     * @return
     */
    private HashMap<String, String> getVolleyReqData(String content) {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", AppConstant.API_KEY);
        map.put("info", content);
        return map;
    }

    /**
     * HttpURLConnRequest callback method
     * @param data

    @Override
    public void getDataFromURL(String data) {
        parseText(data);
    }
     */

    /**
     * Parse text:String response
     * @param data
     */
    private void parseText(String data) throws JSONException {
        JSONObject json = new JSONObject(data);
        parseJson(json);
    }

    /**
     * Parse json:JSONObject response
     * @param json
     */
    private void parseJson(JSONObject json) throws JSONException {
        listData = new ListData(json.getString("text"), ListData.RECEIVE, getTime());
        list.add(listData);
        adapter.notifyDataSetChanged();
    }


    private void checkListSize() {
        if (list.size() >= 30) {
            for (int i = 0; i < 10; i++) {
                list.remove(i);
            }
        }
    }

    private String getTime() {
        curTime = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm");
        Date curDate = new Date();
        String str = format.format(curDate);
        if (curTime - oldTime >= 5 * 60 * 1000) {
            oldTime = curTime;
            return str;
        }
        return "";
    }
}
