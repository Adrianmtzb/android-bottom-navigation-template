package mx.adrianmb.bottom_navigation;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import mx.adrianmb.bottom_navigation.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) {
                Log.d("Timer", "Tick");
            }

            @Override
            public void onFinish() {
                this.cancel();

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}
