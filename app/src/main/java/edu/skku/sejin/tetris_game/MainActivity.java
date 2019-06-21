package edu.skku.sejin.tetris_game;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendStringtoJNI("TETRIS");

        SharedPreferences spref = getSharedPreferences("userFile", MODE_PRIVATE);
        int isLogin = spref.getInt("isLogin", 0);
        if(isLogin == 1) {
            Intent intent = new Intent(getApplicationContext(), ReadyActivity.class);
            startActivity(intent);
        }

        Button loginbtn = (Button)findViewById(R.id.login_btn);
        TextView signupbtn = (TextView)findViewById(R.id.signup_btn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                sendStringtoJNI("Login");
                startActivity(intent);
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                sendStringtoJNI("Sign up");
                startActivity(intent);
            }
        });
    }

    public native void sendStringtoJNI(String s);
}
