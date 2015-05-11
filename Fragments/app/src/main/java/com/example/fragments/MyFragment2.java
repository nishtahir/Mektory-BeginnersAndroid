package com.example.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Darja Kasnikova on 25.04.2015.
 */
public class MyFragment2 extends Fragment{

    private WebView webView;
    /**
     * Create a new instance of MyFragment2,
     * initialized to show the text at 'url'
     */
    public static Fragment newInstance(String url){
        Fragment fragment = new MyFragment2();
        // Supply URL input as an argument
        Bundle args = new Bundle();
        args.putString("url", url);
        fragment.setArguments(args);
        return fragment;
        //return new MyFragment2();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.my_fragment_2, container, false);
        webView = (WebView) layout.findViewById(R.id.webview);

        //String url = "https://www.google.ee/";
        String url = getArguments().getString("url");
        // Load the web page in the WebView
        webView.loadUrl(url);
        // Provide a WebViewClient for WebView to open links clicked by the user
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                //return super.shouldOverrideUrlLoading(view, url);
                return true;
            }
        });

        return layout;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
