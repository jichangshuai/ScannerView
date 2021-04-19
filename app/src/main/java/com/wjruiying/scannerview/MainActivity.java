package com.wjruiying.scannerview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wjruiying.scannerview.activity.CaptureActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements OnResultEventListener {

    private Button scanner_view_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanner_view_button = findViewById(R.id.scanner_view_button);
        scanner_view_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(
                        MainActivity.this,
                        Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                    startActivityForResult(intent, 100);
                }else{
                    requestPermission();
                }
            }
        });
    }

    private void requestPermission(){
        requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(ContextCompat.checkSelfPermission(
                        MainActivity.this,
                        Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                    startActivityForResult(intent, 100);
                }else{
                    Toast.makeText(this, "只有允许请求相机权限才能正常使用", Toast.LENGTH_SHORT).show();
                }
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null){
            switch (requestCode){
                case 100:
                    Bundle bundle = data.getExtras();
                    ResultModel resultModel = bundle.getParcelable("resultModel");
                    getResult(resultModel);
                    break;
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void getResult(ResultModel resultModel) {
        try {

            String result = resultModel.getResult();
            String type = new JSONObject(result).getString("type");
            String id = new JSONObject(result).getString("id");

            Log.e("TAG", type+"--"+id);

        } catch (JSONException exception){

        }
    }
}