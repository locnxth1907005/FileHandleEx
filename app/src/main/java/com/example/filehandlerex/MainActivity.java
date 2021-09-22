package com.example.filehandlerex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public final static String FILE_NAME = "note.txt";
    public final static String FILE_PATH = "/sdcard/Demo/";

    EditText editText;
    Button btSave ;
    Button btRead ;
    RadioGroup radGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edText);
        btSave = findViewById(R.id.btnSave);
        btRead = findViewById(R.id.btnRead);
        radGroup = findViewById(R.id.radGroup);
        btSave.setOnClickListener(this);
        btRead.setOnClickListener(this);

    }

    private void onSaveInternal(){
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME,MODE_PRIVATE);
            fos.write(editText.getText().toString().getBytes());
            fos.flush();
            fos.close();
            Toast.makeText(this, "Thanh cong", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void onRoadInternal(){
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            BufferedReader reader =  new BufferedReader(new InputStreamReader(fis));
            StringBuffer sb = new StringBuffer();
            String line = reader.readLine();
            while (line != null){
                sb.append(line);
                line = reader.readLine();
            }
            Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void onReadExternal(){
        File file = new File(FILE_PATH+FILE_NAME);
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuffer sb = new StringBuffer();
            String line = reader.readLine();
            while (line != null){
                sb.append(line);
                line = reader.readLine();
            }
            Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void onSaveExternal(){
        File directory = new File(FILE_PATH);
        File file = new File(FILE_PATH+FILE_NAME);
        try {
        //Create folder demo
            if (directory.exists()){
                directory.mkdir();
            }
            //Create File
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(editText.getText().toString().getBytes(StandardCharsets.UTF_8));
            fos.flush();
            fos.close();
            Toast.makeText(this, "Thanh cong", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onClick(View view) {
    int idChecked = radGroup.getCheckedRadioButtonId();
    switch (view.getId()){
        case R.id.btnSave:
            if (idChecked == R.id.radInternal){
                onSaveInternal();
            }else {
                onSaveExternal();
            }
            break;
        case R.id.btnRead:
            if (idChecked==R.id.radInternal){
                onRoadInternal();
            }else {
                onSaveExternal();
            }
            break;
          }
    }
}