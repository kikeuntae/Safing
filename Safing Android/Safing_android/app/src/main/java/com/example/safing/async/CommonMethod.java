package com.example.safing.async;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

public class CommonMethod {
    public static InputStream excuteAsk(AsyncTask<String, String, InputStream> ask) {
        InputStream in = null;
        try {
            in = ask.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  in;
    }

    public static String rtnString(InputStream inputStream)  {
        try{


            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while( (line = reader.readLine()) != null  ){
                stringBuilder.append( line );
            }

            return stringBuilder.toString();
        }catch (IOException e){

        }
        return "";

    }
}
