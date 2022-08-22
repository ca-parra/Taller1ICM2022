package com.example.taller1;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class PermissionsActivity extends AppCompatActivity {

    TextView permissionStatus;

    ActivityResultLauncher<String> getSinglePermission = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean result) {
            if(result){
                //granted
                permissionStatus.setText("Granted");
                permissionStatus.setTextColor(Color.GREEN);
            }else{
                //denied
                permissionStatus.setText("Denied");
                permissionStatus.setTextColor(Color.RED);
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);
        permissionStatus = findViewById(R.id.permissionStatus);
        if(shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)){
            Toast.makeText(this, "Permission required to show the contacts", Toast.LENGTH_LONG).show();
        }
        getSinglePermission.launch(Manifest.permission.READ_CONTACTS);
    }
}