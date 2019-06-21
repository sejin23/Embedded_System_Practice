package edu.skku.sejin.tetris_game;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SignupActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ImageView iv_back = findViewById(R.id.arrowback_signup);
        final EditText et_email = findViewById(R.id.email_signup);
        final EditText et_pw = findViewById(R.id.pw_signup);
        final EditText et_pwcon = findViewById(R.id.pwconfirm_signup);
        Button btn_signup = findViewById(R.id.btn_signup);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MainActivity().sendStringtoJNI("tetris");
                finish();
            }
        });

        et_pwcon.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String pw = et_pw.getText().toString();
                String confirm = et_pwcon.getText().toString();
                if(pw.equals(confirm)) {
                    et_pw.setTextColor(Color.rgb(112, 241, 112));
                    et_pwcon.setTextColor(Color.rgb(112, 241, 112));
                } else {
                    et_pw.setTextColor(Color.rgb(255, 80, 80));
                    et_pwcon.setTextColor(Color.rgb(255, 80, 80));
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_email.getText().toString().length() == 0) {
                    Toast.makeText(SignupActivity.this, "Email을 입력하세요.", Toast.LENGTH_SHORT).show();
                    et_email.requestFocus();
                    return;
                }
                if(et_pw.getText().toString().length() == 0) {
                    Toast.makeText(SignupActivity.this, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                    et_pw.requestFocus();
                    return;
                }
                if(et_pwcon.getText().toString().length() == 0) {
                    Toast.makeText(SignupActivity.this, "비밀번호 확인을 입력하세요.", Toast.LENGTH_SHORT).show();
                    et_pwcon.requestFocus();
                    return;
                }
                if(!et_pw.getText().toString().equals(et_pwcon.getText().toString())) {
                    Toast.makeText(SignupActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    et_pwcon.setText("");
                    et_pwcon.requestFocus();
                    return;
                }
                SharedPreferences spref = getSharedPreferences("userFile", MODE_PRIVATE);

                SharedPreferences.Editor editor = spref.edit();
                String str_email = et_email.getText().toString();
                String str_pw = et_pw.getText().toString();
                editor.putString("email", str_email);
                editor.putString("pw", str_pw);
                editor.commit();

                finish();
            }
        });
    }
}
