package com.example.ws16;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
public class MainActivity extends AppCompatActivity {
    Button button;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        imageView=findViewById(R.id.imageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTask mt=new MyTask();
                mt.execute("https://img2.freepng.ru/20180629/ph/kisspng-beak-platypus-goose-cygnini-duck-amon-5b367a571c1603.2293852915302969191151.jpg");
            }
        });
    }
    class MyTask extends AsyncTask<String, Void, Bitmap>{
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
            imageView.setI(result);
        }
    }
}