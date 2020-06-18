package MVC;
import java.util.Vector;
import java.util.Enumeration;
import javax.swing.*;
import java.awt.Container;
import java.awt.event.*;
import java.awt.*;

public class ChatView implements ChatObserver, ChatObservable {
    /** @associates <{ChatObserver}> */
    private Vector chatObserversVector = new java.util.Vector();
    private ChatModel model;
    private JScrollPane scroll;
    private Container container;
    private JButton submit;
    private ButtonGroup group;
    private JRadioButton firstGui;
    private JRadioButton secondGui;
    private JTextArea textArea;
    private JPanel panel;
    private JTextField textField;
    private JFrame frame;
    private static int counter = 0;
    private int id;

    public ChatView(ChatModel model) {
        this.model = model;
        id = ++counter;
        frame = new JFrame("Client " + id);
        scroll = new JScrollPane();
        container = frame.getContentPane();
        textArea = new JTextArea("", 10, 40);
        panel = new JPanel();
        textField = new JTextField(20);
        submit = new JButton("Submit");
        group = new ButtonGroup();
        firstGui = new JRadioButton("FirstGui", true);
        secondGui = new JRadioButton("SecondGui");
        container.setLayout(new BorderLayout());
        secondGui.addItemListener(selectionHandler);
        submit.addActionListener(submitHandler);
        model.attach(this);
        this.setup1();
    }

    public void update() {
        textArea.setText(model.getMessages());
    }

    public void attach(ChatObserver chatObserver) {
        chatObserversVector.addElement(chatObserver);
    }

    public void detach(ChatObserver chatObserver) {
        chatObserversVector.removeElement(chatObserver);
    }

    public void inform() {
        Enumeration enumeration = chatObservers();
        while (enumeration.hasMoreElements()) {
            ((ChatObserver)enumeration.nextElement()).update();
        }
    }

    public Enumeration chatObservers() {
        return ((java.util.Vector) chatObserversVector.clone()).elements();
    }

    public void setup1() {
        scroll.setViewportView(textArea);
        container.add(scroll, BorderLayout.CENTER);
        container.add(panel, BorderLayout.SOUTH);
        group.add(firstGui);
        group.add(secondGui);
        panel.add(firstGui);
        panel.add(secondGui);
        panel.add(textField);
        panel.add(submit);
        frame.pack();
        frame.setVisible(true);
    }

    public void setup2() {
        scroll.setViewportView(textArea);
        container.add(scroll, BorderLayout.CENTER);
        container.add(panel, BorderLayout.NORTH);
        group.add(firstGui);
        group.add(secondGui);
        panel.add(firstGui);
        panel.add(secondGui);
        panel.add(textField);
        panel.add(submit);
        frame.pack();
        frame.setVisible(true);
    }

    public void showDisplay(int display) {
            String text = model.getMessages();
        if (display == ItemEvent.SELECTED) {
            frame.setVisible(false);
            textArea.setText(text);
            setup2();
        }
        else {
            frame.setVisible(false);
            textArea.setText(text);
            setup1();
        }
    }

    ItemListener selectionHandler = new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
            ChatController con;
            for (int i = 0; i < chatObserversVector.size(); i++) {
                con = (ChatController)chatObserversVector.get(i);
                con.processSelectionAction(e.getStateChange());
            }
        }
    };

    ActionListener submitHandler = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            inform();
            ChatController con;
            for (int i = 0; i < chatObserversVector.size(); i++) {
                con = (ChatController)chatObserversVector.get(i);
                con.processSubmitAction("Client"+id+": "+textField.getText());
            }
        }
    };
}