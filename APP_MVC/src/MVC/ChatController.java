/* This is the Controller */
package MVC;
public class ChatController implements ChatObserver {
    private ChatModel model;
    private ChatView view;
    private int messageNumber = 0;

    public ChatController(ChatView view, ChatModel model) {
        this.view = view;
        this.model = model;
        view.attach(this);
    }

    public void update() {
        messageNumber++;
        System.out.println(messageNumber);
    }

    public void processSubmitAction(String msg) {
        model.addMessage(msg);
    }

    public void processSelectionAction(int display) {
        view.showDisplay(display);
    }
}
