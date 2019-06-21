package edu.skku.sejin.tetris_game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result_Activity extends Activity {
    private static int RESULT_BACK = 1;
    private static int RESULT_REPLAY = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_);

        Intent intent = new Intent(this.getIntent());
        int score = intent.getIntExtra("score", 0);
        TextView tv = findViewById(R.id.myscore);
        tv.setText(String.valueOf(score));

        Button btn_home = findViewById(R.id.gotohome);
        Button btn_game = findViewById(R.id.gotogame);

        btn_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_BACK);
                finish();
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_REPLAY);
                finish();
            }
        });
    }
}
