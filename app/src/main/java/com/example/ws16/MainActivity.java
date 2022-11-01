package com.example.ws16;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        textView=findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTask mt=new MyTask();
                mt.execute("https://ru.wikipedia.org/");
            }
        });
    }
    class MyTask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... params){
            String title;
            Document doc=null;
            try {
                doc= Jsoup.connect(params[0]).get();
            } catch(IOException e) {
                e.printStackTrace();
            }
            if (doc!=null){
                title=doc.title();
            }
            else{
                title ="Error";
            }
            return title;
        }
        @Override
        protected void onPostExecute (String result){
            super.onPostExecute(result);
            textView.setText(result);
        }
    }
}