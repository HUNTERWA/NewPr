package com.example.rohit.newpr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity
{
    String url="http://188.166.50.216:9000/UserLogin";
    MediaType mediaType=MediaType.parse("application/json");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpClient okHttpClient=new OkHttpClient();
        JSONObject jsonObject=new JSONObject();
        try
        {
            jsonObject.put("emailId","a@gmail.com");
            jsonObject.put("password","a");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        RequestBody requestBody=RequestBody.create(mediaType,jsonObject.toString());

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {
                Log.d("causeOfErrorIs",""+e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                Log.d("responseIs",response.message()+"\n"+response.code());
            }
        });
    }
}
