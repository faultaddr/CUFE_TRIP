package com.example.panda.myapplication.Data;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class database extends Service {
    public database() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
