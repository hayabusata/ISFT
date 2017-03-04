package falleight.isft;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by syunta on 2017/03/04.
 */

public class WebViewFromTextView extends AppCompatActivity {
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        // WebViewを置いているのはweb_view.xml
        setContentView(R.layout.web_view);
        if( savedInstanceState == null ) {
            // SampleActivity.javaからurlを受け取っている
            String url = getIntent().getDataString();
            Log.d("WebViewFromTextView:", url);

            // WebViewの設定
            // 表示するWebViewのidをwebViewとしている
            WebView webView = (WebView)findViewById(R.id.webView);
            // focusが当たるように。いらないかも
            webView.requestFocus();
            // jsの有効化(XSSの恐れがあるため怒られるが...)
            webView.getSettings().setJavaScriptEnabled(true);
            // ズーム出来るように
            webView.getSettings().setBuiltInZoomControls(true);
            // クライアント作成
            webView.setWebViewClient(new WebViewClient());
            // ここでurl読み込み
            webView.loadUrl(url);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // webviewでbackボタンが押下された時、履歴を戻れるなら戻り、戻れないなら前のActivityに戻る
        WebView webview = (WebView)findViewById(R.id.webView);
        if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
