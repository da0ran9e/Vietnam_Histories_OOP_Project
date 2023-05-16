import javax.swing.*;
import java.awt.*;

public class test extends JFrame {

  private JPanel menuPanel;
  private JButton toggleButton;

  public test() {
    setTitle("My Swing App");
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // create a menu panel with a BoxLayout
    menuPanel = new JPanel();
    menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

    // add some menu items to the menu panel
    for (int i = 1; i <= 5; i++) {
      JButton menuItem = new JButton("Menu Item " + i);
      menuPanel.add(menuItem);
    }

    // create a toggle button to show/hide the menu panel
    toggleButton = new JButton("Show Menu");
    toggleButton.addActionListener(e -> toggleMenu());

    // create a panel to hold the toggle button and the menu panel
    JPanel mainPanel = new JPanel(new BorderLayout());
    mainPanel.add(toggleButton, BorderLayout.NORTH);
    mainPanel.add(menuPanel, BorderLayout.CENTER);

    // add the main panel to the JFrame
    add(mainPanel);

    setLocationRelativeTo(null);
  }

  private void toggleMenu() {
    CardLayout cardLayout = (CardLayout) menuPanel.getLayout();
    if (toggleButton.getText().equals("Show Menu")) {
      cardLayout.show(menuPanel, "menu");
      toggleButton.setText("Hide Menu");
    } else {
      cardLayout.show(menuPanel, "empty");
      toggleButton.setText("Show Menu");
    }
  }

  public static void main(String[] args) {
    test app = new test();
    app.setVisible(true);
  }
}
