package ua.com.alevel.stream.callback.async;

import ua.com.alevel.stream.callback.ObservListener;
import ua.com.alevel.stream.callback.listener.CallbackListener;

public class ObservASynchronousListener implements ObservListener {

    private CallbackListener listener;

    @Override
    public void registerCallbackListener(CallbackListener listener) {
        this.listener = listener;
    }

    @Override
    public void runListener() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("ObservASynchronousListener.run");
                if (listener != null) {
                    listener.onEvent();
                }
            }
        }).start();
    }
}
