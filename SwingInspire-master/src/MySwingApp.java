import javax.swing.*;
import java.awt.event.*;

public class MySwingApp extends JFrame {

  public MySwingApp() {
    setTitle("My Swing App");
    setSize(400, 300); // set the window size to 400x300 pixels
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // create an array of options to recommend
    String[] options = {"apple", "banana", "cherry", "grape", "orange"};

    // create a DefaultComboBoxModel with the options array
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(options);

    // create a JComboBox with the DefaultComboBoxModel
    JComboBox<String> comboBox = new JComboBox<>(model);

    // set the JComboBox to editable so that users can type in it
    comboBox.setEditable(true);

    // add a KeyListener to the JComboBox to listen for key events
    comboBox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
      public void keyReleased(KeyEvent event) {
        String input = comboBox.getEditor().getItem().toString();
        if (input.length() == 0) {
          comboBox.setModel(model);
          comboBox.hidePopup();
        } else {
          DefaultComboBoxModel<String> filteredModel = new DefaultComboBoxModel<>();
          for (String option : options) {
            if (option.toLowerCase().startsWith(input.toLowerCase())) {
              filteredModel.addElement(option);
            }
          }
          comboBox.setModel(filteredModel);
          comboBox.showPopup();
        }
      }
    });

    // add the JComboBox to the JFrame
    add(comboBox);

    setLocationRelativeTo(null); // center the window on the screen
  }

  public static void main(String[] args) {
    MySwingApp app = new MySwingApp();
    app.setVisible(true);
  }
}
