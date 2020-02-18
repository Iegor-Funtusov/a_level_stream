package ua.com.alevel.stream.callback;

import ua.com.alevel.stream.callback.listener.CallbackListener;

public interface ObservListener {

    void runListener();
    void registerCallbackListener(CallbackListener listener);
}
