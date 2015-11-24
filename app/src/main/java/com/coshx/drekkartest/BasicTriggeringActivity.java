package com.coshx.drekkartest;

import android.os.Bundle;
import android.webkit.WebView;

import com.coshx.drekkar.Callback;
import com.coshx.drekkar.Drekkar;
import com.coshx.drekkar.EventBus;
import com.coshx.drekkar.WhenReady;

/**
 * @class BasicTriggeringActivity
 * @brief
 */
public class BasicTriggeringActivity extends WebActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_triggering);

        WebView webView = (WebView) findViewById(R.id.basic_triggering);

        Drekkar.getDefault(
            this, webView, new WhenReady() {
                @Override
                public void run(final EventBus bus) {
                    bus.register(
                        "From JS", new Callback() {
                            @Override
                            public void run(String name, Object data) {
                                bus.post("From Android");
                            }
                        }
                    );
                }
            }
        );

        loadURL(webView, "basic_triggering");
    }
}
