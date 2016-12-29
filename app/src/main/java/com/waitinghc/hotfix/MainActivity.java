package com.waitinghc.hotfix;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_throw_exception).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test();
            }
        });

        findViewById(R.id.btn_hot_fix).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                } else {
                    addPatch();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                addPatch();
                return;
            }
        }
    }


    private void addPatch() {
        HotFixApplication.getInstance().getPatchManager().removeAllPatch();
        try {
            HotFixApplication.getInstance().getPatchManager().addPatch("/mnt/sdcard/hotfix/fix.apatch");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void test() {
        Toast.makeText(this, "发现一个bug ，赶紧解决", Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "bug已解决", Toast.LENGTH_SHORT).show();
    }
}
