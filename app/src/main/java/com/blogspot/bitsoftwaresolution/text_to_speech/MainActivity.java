package com.blogspot.bitsoftwaresolution.text_to_speech;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private TextToSpeech tts;
    private Button btnSubmit;
    private Button btnCanada;
    private Button btnChina;
    private Button btnFrench;
    private Button btnGerman;
    private Button btnJapan;
    private EditText inputText;
    public int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tts=new TextToSpeech(this, (TextToSpeech.OnInitListener) this);
        btnSubmit=findViewById(R.id.btnSubmit);
        btnCanada=findViewById(R.id.btnCanada);
        btnChina=findViewById(R.id.btnChina);
        btnFrench=findViewById(R.id.btnFrance);
        btnGerman=findViewById(R.id.btnGerman);
        btnJapan=findViewById(R.id.btnJapan);
        inputText=findViewById(R.id.textInput);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                i=1;
                voiceOutput();
            }
        });
        btnCanada.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                i=2;
                voiceOutput();
            }
        });
        btnChina.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                i=3;
                voiceOutput();
            }
        });
        btnFrench.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                i=4;
                voiceOutput();
            }
        });
        btnGerman.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                i=5;
                voiceOutput();
            }
        });
        btnJapan.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                i=6;
                voiceOutput();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void voiceOutput() {
        CharSequence text=inputText.getText();
        tts.speak(text,TextToSpeech.QUEUE_FLUSH,null,"id1");
    }
    
    
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onInit(int status) {
        int result = 0;
        if(status==TextToSpeech.SUCCESS) {
            if(i==1){
               result =tts.setLanguage(Locale.US);
            }else if (i==2){
                result=tts.setLanguage(Locale.CANADA);
            }else if(i==3){
                result=tts.setLanguage(Locale.CHINA);
            }else if (i==4){
                result=tts.setLanguage(Locale.FRENCH);
            }else if (i==5){
                result=tts.setLanguage(Locale.GERMANY);
            }else if (i==6){
                result=tts.setLanguage(Locale.JAPANESE);
            }
            
            if(result==TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(MainActivity.this,"Language not supported!",Toast.LENGTH_SHORT).show();
            }else {
                btnSubmit.setEnabled(true);
                voiceOutput();
            }
        }else {
            Toast.makeText(MainActivity.this,"Initilization failed",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy(){
        if(tts!=null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}