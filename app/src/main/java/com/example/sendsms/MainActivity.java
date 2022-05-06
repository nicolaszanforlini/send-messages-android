package com.example.sendsms;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtPhone;
    EditText edtMessage;
    EditText nbSms;
    Button btnSend;
    Button btnReset;
    Timer cdt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permissionPhoneSend();

        edtPhone = findViewById(R.id.edtPhone);
        edtMessage = findViewById(R.id.edtMessage);
        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(btnListener);
        btnReset = findViewById(R.id.btnReset);
        btnReset.setOnClickListener(btnResetListener);

    }

    public View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String msg = edtMessage.getText().toString();
            String phone = edtPhone.getText().toString();
            if(msg.equals("") || phone.equals("")) {
                Toast.makeText(getApplicationContext(), "Error : entrer tous les champs...", Toast.LENGTH_SHORT).show();
            } else {
                cdt = new Timer(150000, 1300, phone, msg);
                cdt.start();
            }
        }
    };

    public View.OnClickListener btnResetListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            edtMessage.setText("");
            edtPhone.setText("");
        }
    };

    public void permissionPhoneSend() {
        final int PERMISSION_REQUEST_CODE = 1;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS)
                    == PackageManager.PERMISSION_DENIED) {
                Log.d("permission", "permission denied to SEND_SMS - requesting it");
                String[] permissions = {Manifest.permission.SEND_SMS};
                requestPermissions(permissions, PERMISSION_REQUEST_CODE);
            }
        }
    }

}