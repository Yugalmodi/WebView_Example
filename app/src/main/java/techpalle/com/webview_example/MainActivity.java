package techpalle.com.webview_example;

import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView1);
        //CHECK FOR INTERNET CONNECTION
        //a. get connectivity manager object

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //b. from network manager, get active network information

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        //c. Check if network i connected or not
        if (networkInfo == null || networkInfo.isConnected() == false)
        {
            //Means there is no Internet
            webView.loadData("<h1>NO INTERNET Connection - CHECK YOUR CONNECTION<h1>", "text/html", null);
            return;
        }


        webView.getSettings().setJavaScriptEnabled(true);
        //below line will make sure that onclicking any hyper link, next page
        //will be loaded in our activity.
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://techpalle.com");
    }

    //We wil handle Back Button clicks on WebView

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            //Ask WebView- If There are any Previously Navigated Page
            if (webView.canGoBack() == true) {
                //CONTROL COMES HERE IF THERE IS ANY PREVIOUS PAGE
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


}
