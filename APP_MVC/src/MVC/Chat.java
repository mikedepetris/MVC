package MVC;

public class Chat {
    public static void main(String[] args) {
        ChatModel model = new ChatModel();
        ChatView view1 = new ChatView(model);
        ChatView view2 = new ChatView(model);
        ChatController controller1 = new ChatController(view1, model);
        ChatController controller2 = new ChatController(view2, model);
    }
}
