package com.example.a20210308_webview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Xml;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());

        String html = Loader.loadFileFromAssets(this, "plantilla.html");
        String titol = "Títol";
        String descripcio = "Lorem Ipsum dolor est";
        html = html.replaceAll("\\{\\{TITOL\\}\\}", titol);
        html = html.replaceAll("\\{\\{DESCRIPCIO\\}\\}", descripcio);
        //html = html.replaceAll("\\{\\{IMG_SRC\\}\\}", "android.resource://com.example.a20210308_webview/"+R.drawable.ic_launcher_background);

        /*String html = "<!DOCTYPE html >\n" +
                "<html>" +
                "<head><link rel=\"stylesheet\" type=\"text/css\" href=\"estils.css\" /></head>"+
                "<body><p>Hola món</p></body></html>";*/
        webView.loadDataWithBaseURL("file:///android_asset/", html, "text/html", "UTF-8", null);

        //webView.loadUrl("file:///android_asset/estils.css");
    }
}