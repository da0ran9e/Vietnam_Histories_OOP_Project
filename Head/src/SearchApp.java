import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.event.ActionListener;

public class SearchApp extends JFrame {
  private JTextField searchField;
  public SearchApp() {
    setTitle("My Swing App");
    setSize(1000, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    JPanel mainPanel = new JPanel(new BorderLayout());

    JPanel menuPanel = new JPanel(new GridLayout(2,1));
    menuPanel.setSize(300, mainPanel.getHeight());

    JPanel contentPanel = new JPanel(new GridLayout(2,1));
    contentPanel.setSize(mainPanel.getWidth()-menuPanel.getWidth(), mainPanel.getHeight());

    JPanel recentPanel = new JPanel(new GridLayout(0,2));
    JScrollPane recentScrollPanel = new JScrollPane(recentPanel);
    recentScrollPanel.setPreferredSize(new Dimension(menuPanel.getWidth()-20,mainPanel.getHeight()-20));

    ImageIcon menuIcon = new ImageIcon("../assets/menu.png");
    JButton menuButton = new JButton(menuIcon);
    menuButton.setPreferredSize(new Dimension(24,24));

    JPanel headBar = new JPanel(new GridLayout(1,2));
    headBar.setPreferredSize(new Dimension(menuPanel.getWidth()-20,24));

    searchField = new JTextField();
    searchField.setPreferredSize(new Dimension(headBar.getWidth()-100,20));

    ImageIcon searchIcon = new ImageIcon("../assets/search.png");
    JButton searchButton = new JButton(searchIcon+"Search");
    searchButton.setPreferredSize(new Dimension(50,24));
    searchButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String key = searchField.getText();
        Search(key);
      }
    });

    headBar.add(searchField);
    headBar.add(searchButton);
    menuPanel.add(menuButton);
    menuPanel.add(recentScrollPanel);
    contentPanel.add(headBar);
    mainPanel.add(menuPanel, BorderLayout.WEST);
    mainPanel.add(contentPanel, BorderLayout.CENTER);
  add(mainPanel);




  }

  public static void ToggleMenu(){

      System.out.println("ToggleMenu");
  }
  public static void Search(String key){
    JPanel recent = new JPanel(new GridLayout(1,2));
    ImageIcon recentIcon = new ImageIcon("../assets/icons8.png");
    recentIcon.setDescription(key);
    recent.add(new JLabel(recentIcon));
    recent.add(new JLabel("1"+ key));

  }
  public static void main(String[] args) {
    SearchApp app = new SearchApp();
    app.setVisible(true);
  }
}
