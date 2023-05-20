package HForm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Test {
    private JFrame frame;
    private JTextField textField;
    private JList<String> list;
    private DefaultListModel<String> model;

    public Test() {
        frame = new JFrame("AutoCompleteTextField Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        model = new DefaultListModel<>();
        model.addElement("Option 1");
        model.addElement("Option 2");
        model.addElement("Option 3");
        model.addElement("Another Option");
        model.addElement("Some Option");

        list = new JList<>(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(3);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String selectedValue = list.getSelectedValue();
                    textField.setText(selectedValue);
                    list.clearSelection();
                    list.setVisible(false);
                }
            }
        });

        textField = new JTextField(20);
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateList();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateList();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateList();
            }

            private void updateList() {
                String searchText = textField.getText().toLowerCase();
                model.clear();
                List<String> matchedElements = new ArrayList<>();
                for (int i = 0; i < list.getModel().getSize(); i++) {
                    String element = list.getModel().getElementAt(i);
                    if (element.toLowerCase().contains(searchText)) {
                        matchedElements.add(element);
                    }
                }
                for (String matchedElement : matchedElements) {
                    model.addElement(matchedElement);
                }
                list.setVisible(!matchedElements.isEmpty());
            }
        });

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(textField, BorderLayout.NORTH);
        contentPane.add(new JScrollPane(list), BorderLayout.CENTER);

        frame.setContentPane(contentPane);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Test();
            }
        });
    }
}
