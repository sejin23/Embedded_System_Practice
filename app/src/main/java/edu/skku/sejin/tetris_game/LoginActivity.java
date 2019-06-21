package edu.skku.sejin.tetris_game;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btn_login = findViewById(R.id.btn_login);
        final TextView tv_email = findViewById(R.id.email_login);
        final TextView tv_pw = findViewById(R.id.pw_login);
        ImageView iv = findViewById(R.id.arrowback_login);

        final SharedPreferences sp = getSharedPreferences("userFile", MODE_PRIVATE);
        final String text_email = sp.getString("email", "");
        final String text_pw = sp.getString("pw", "");
        tv_email.setText(text_email);
        tv_pw.setText(text_pw);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MainActivity().sendStringtoJNI("tetris");
                LoginActivity.super.onBackPressed();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_email.getText().toString().equals(text_email) && tv_pw.getText().toString().equals(text_pw)) {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt("isLogin", 1);
                    editor.commit();

                    Intent intent = new Intent(getApplicationContext(), ReadyActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
