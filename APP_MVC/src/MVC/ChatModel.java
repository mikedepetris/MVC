package MVC;
import java.util.*;

public class ChatModel implements ChatObservable {
    private Vector messages;

    /**
     * @associates <{ChatObserver}>
     */
    private Vector forumObserversVector = new java.util.Vector();

    public ChatModel() {
        messages = new Vector();
    }

    public void addMessage(String msg) {
        messages.add(msg);
        inform();
    }

    public String getMessages() {
        int length = messages.size();
        String allMessages = "";
        for (int i = 0; i < length; ++i) {
            allMessages += (String)messages.elementAt(i)+"\n";
        }
        return allMessages;
    }

    public void attach(ChatObserver forumObserver){
            forumObserversVector.addElement(forumObserver);
        }

    public void detach(ChatObserver forumObserver){
            forumObserversVector.removeElement(forumObserver);
        }

    public void inform(){
            java.util.Enumeration enumeration = forumObservers();
            while (enumeration.hasMoreElements()) {
                ((ChatObserver)enumeration.nextElement()).update();
            }
        }

    public Enumeration forumObservers(){
            return ((java.util.Vector) forumObserversVector.clone()).elements();
        }
}
