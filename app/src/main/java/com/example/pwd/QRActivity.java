package com.example.pwd;

import android.os.Bundle;
import android.os.PersistableBundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

public class QRActivity extends AppCompatActivity {

    private DecoratedBarcodeView mDBV;
    private CaptureManager captureManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        mDBV = (DecoratedBarcodeView) findViewById(R.id.dbv);

        captureManager = new CaptureManager(this, mDBV);
        captureManager.initializeFromIntent(getIntent(), savedInstanceState);
        captureManager.decode();
    }

    private View.OnClickListener BackListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        captureManager.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mDBV.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        captureManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        captureManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        captureManager.onDestroy();
    }
}