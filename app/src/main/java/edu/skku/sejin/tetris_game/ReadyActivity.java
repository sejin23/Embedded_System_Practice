package edu.skku.sejin.tetris_game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReadyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready);

        new MainActivity().sendStringtoJNI("ready");

        Button btn_start = findViewById(R.id.btn_start);
        TextView tv_rule = findViewById(R.id.tv_rules);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GameMain.class);
                startActivity(intent);
            }
        });

        tv_rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RuleActivity.class);
                startActivity(intent);
            }
        });
    }
}
