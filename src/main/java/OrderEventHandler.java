package handlers;

public class OrderEventHandler {

    public void onEvent(String event) {
        System.out.println("[INFO] received event " + event);
    }
}

