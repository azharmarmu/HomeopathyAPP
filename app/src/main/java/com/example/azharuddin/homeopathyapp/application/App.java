package com.example.azharuddin.homeopathyapp.application;


import android.app.Application;

import com.vanniktech.emoji.EmojiManager;
import com.vanniktech.emoji.google.GoogleEmojiProvider;

/**
 * Created by azharuddin on 12/3/18.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        EmojiManager.install(new GoogleEmojiProvider());
    }
}
