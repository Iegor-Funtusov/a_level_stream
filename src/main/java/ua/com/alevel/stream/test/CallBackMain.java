package ua.com.alevel.stream.test;

import ua.com.alevel.stream.callback.ObservListener;
import ua.com.alevel.stream.callback.async.ObservASynchronousListener;
import ua.com.alevel.stream.callback.listener.OnCallbackListener;
import ua.com.alevel.stream.callback.sync.ObservSynchronousListener;

public class CallBackMain {

    public static void run() {
        OnCallbackListener callbackListener = new OnCallbackListener();
        ObservListener synchronousListener = new ObservSynchronousListener();
        synchronousListener.registerCallbackListener(callbackListener);
        synchronousListener.runListener();
        ObservListener aSynchronousListener = new ObservASynchronousListener();
        aSynchronousListener.registerCallbackListener(callbackListener);
        aSynchronousListener.runListener();
    }
}
