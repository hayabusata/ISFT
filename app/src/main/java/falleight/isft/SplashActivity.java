package falleight.isft;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // タイトルを非表示。
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // activity_splash.xmlをViewに指定。
        setContentView(R.layout.activity_splash);
        Handler hdl = new Handler();
        // 1000ms遅延させてsplashHandlerを実行。
        hdl.postDelayed(new splashHandler(), 2000);
    }
    class splashHandler implements Runnable {
        public void run() {
            // スプラッシュ完了後に実行するActivityを指定。
            Intent intent = new Intent(getApplication(), StartMenuActivity.class);
            startActivity(intent);
            // SplashActivityを終了。
            SplashActivity.this.finish();
        }
    }
}
