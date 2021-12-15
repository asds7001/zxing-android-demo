package com.example.pwd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;


public class MainActivity extends AppCompatActivity {
    private Context context;
    private Button QrOpen;
    private TextView PwdRes;
    private final static int REQUEST_CODE = 1001;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        QrOpen = (Button) findViewById(R.id.QrOpen);
        QrOpen.setOnClickListener(QrOpenListener);

        PwdRes = (TextView) findViewById(R.id.PwdRes);
    }

    private View.OnClickListener QrOpenListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //打开扫描界面
            IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
            intentIntegrator.setOrientationLocked(false);
            intentIntegrator.setCaptureActivity(QRActivity.class); // 设置自定义的activity是QRActivity
            intentIntegrator.setRequestCode(REQUEST_CODE);
            intentIntegrator.initiateScan();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            IntentResult scanResult = IntentIntegrator.parseActivityResult(resultCode, data);
            final String qrContent = scanResult.getContents();

            if (qrContent != null) {
                MD5 md = new MD5();
                final String strRes = md.start(qrContent);
                PwdRes.setText(strRes.substring(0,6));
            }
            //Toast.makeText(context, "扫描结果:" + strRes, Toast.LENGTH_SHORT).show();
        }
    }
}