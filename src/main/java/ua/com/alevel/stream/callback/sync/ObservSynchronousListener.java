package ua.com.alevel.stream.callback.sync;

import ua.com.alevel.stream.callback.ObservListener;
import ua.com.alevel.stream.callback.listener.CallbackListener;

public class ObservSynchronousListener implements ObservListener {

    private CallbackListener listener;

    @Override
    public void registerCallbackListener(CallbackListener listener) {
        this.listener = listener;
    }

    @Override
    public void runListener() {
        System.out.println("ObservListener.runListener");
        if (listener != null) {
            this.listener.onEvent();
        }
    }
}
