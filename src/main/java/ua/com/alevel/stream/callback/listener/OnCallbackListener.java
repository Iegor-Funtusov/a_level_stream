package ua.com.alevel.stream.callback.listener;

public class OnCallbackListener implements CallbackListener {

    @Override
    public void onEvent() {
        System.out.println("OnCallbackListener.onEvent");
    }
}
