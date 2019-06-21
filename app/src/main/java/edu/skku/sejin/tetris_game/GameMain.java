package edu.skku.sejin.tetris_game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class GameMain extends Activity {
    private static int RESULT_BACK = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_BACK) {
            switch (requestCode) {
                case 200:
                    finish();
                    break;
            }
        }
    }

    public void gotoIntent(int score) {
        Intent intent = new Intent(getApplicationContext(), Result_Activity.class);
        intent.putExtra("score", score);
        startActivityForResult(intent, 200);
    }
}