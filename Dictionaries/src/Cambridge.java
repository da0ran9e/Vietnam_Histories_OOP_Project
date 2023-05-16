
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Cambridge extends javax.swing.JFrame{
    public Cambridge(){
        initComponents();
    }
    //Variables declaration
    private JPanel mainBody;
    private JPanel searchBar;
    private JPanel contentBox;
    private JTextField searchBox;
    private JButton searchButton;
    private JLabel content;


    private void initComponents(){
        searchButton = new javax.swing.JButton("🔎");
        searchBar = new javax.swing.JPanel();
        mainBody = new javax.swing.JPanel();
        contentBox = new javax.swing.JPanel();
        searchBox = new javax.swing.JTextField();
        content = new JLabel();

        content.setBackground(new java.awt.Color(35, 39, 46));
        content.setForeground(Color.white);

        mainBody.setBackground(new java.awt.Color(35, 39, 46));
        mainBody.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        mainBody.setMinimumSize(new java.awt.Dimension(375, 1000));

        searchBar.setBackground(new java.awt.Color(35, 35, 35));
        searchBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        searchBar.setMinimumSize(new java.awt.Dimension(375, 120));

        searchBox.setBackground(new java.awt.Color(35, 39, 46));
        searchBox.setForeground(Color.WHITE);
        searchBox.setFont(new java.awt.Font("Segoe UI", 0, 20));

        searchButton.setSize(new Dimension(70, 70));
        searchButton.setBackground(new java.awt.Color(66, 154, 189));
        searchButton.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MouseExited(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonClicked(evt);
            }
        });

        searchBar.add(searchBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 280, 70));
        searchBar.add(searchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(295, 20, 70, 70));
        mainBody.add(searchBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0, 375, 120));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }


    public void MouseExited(MouseEvent evt){
            searchButton.setBackground(new java.awt.Color(66, 154, 189));
    }
    public void MouseEntered(MouseEvent evt){
            searchButton.setBackground(new java.awt.Color(180, 154, 189));
    }
    public void ButtonClicked(MouseEvent evt){
            String word = searchBox.getText();
            List<String>datas = Search.Go(word);

            contentBox.setBackground(new java.awt.Color(35, 39, 46));
            contentBox.setPreferredSize(new Dimension(375, 1000));
            contentBox.setSize(375, 1000);

            JScrollPane contents = new JScrollPane(contentBox);
            contents.setPreferredSize(new Dimension(375, 505));
            contents.setBackground(new java.awt.Color(180, 154, 189));


                StringBuilder sb = new StringBuilder();
                sb.append("<html>");
            for(String data:datas){

                if (data.length()>=47) {
                    sb.append("<p>");
                    for (int i = 0; i < data.length(); i++) {
                        sb.append(data.charAt(i));
                        if (i % 47 == 0) {
                            sb.append("</br>");
                        }
                    }
                    sb.append("</p>");
                }
                else {
                    sb.append("<h3>"+data+"</br></h3>");
                }
            }
            sb.append("</html>");
            content.setText(sb.toString());
            System.out.println(sb.toString());

            contentBox.add(content, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 375, 1000));
            mainBody.add(content, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 375, 505));

            mainBody.revalidate();
            mainBody.repaint();
    }
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cambridge.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cambridge.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cambridge.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cambridge.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cambridge().setVisible(true);
            }
        });
    }
}
