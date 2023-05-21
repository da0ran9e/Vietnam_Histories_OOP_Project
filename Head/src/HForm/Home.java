package HForm;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Home extends javax.swing.JFrame{
    public Home(){
        initComponents();
    }
    //Variables declaration
    private DefaultListModel<String>keywords = new DefaultListModel<>();
    private DefaultListModel<String> updatedList = new DefaultListModel<>();
    private javax.swing.JLabel title;
    private javax.swing.JLabel closeButton;
    private javax.swing.JLabel hideButton;
    private javax.swing.JLabel windowButton;
    private javax.swing.JLabel searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JLabel menuButton;
    private javax.swing.JLabel addButton;
    private javax.swing.JLabel homeLabel;
    private javax.swing.JPanel homePanel;
    private javax.swing.JPanel titleBar;
    private javax.swing.JPanel searchBar;
    private javax.swing.JPanel mainBody;
    private javax.swing.JPanel sideBar;
    private javax.swing.JPanel subSideBar;
    private javax.swing.JPanel contentBox;
    private javax.swing.JPanel addPanel;
    private javax.swing.JPanel contentSearchPanel;
    private javax.swing.JTextField contentSearchField;
    private javax.swing.JLabel contentSearchButton;
    private javax.swing.JScrollPane contentScrollBox;
    private javax.swing.JPanel contentBody;
    private javax.swing.JLabel conTents;
    private javax.swing.JList keywordsList;
    private javax.swing.JScrollPane keywordsListScroll;

    private void initComponents(){
        title = new javax.swing.JLabel();
        closeButton = new javax.swing.JLabel();
        hideButton = new javax.swing.JLabel();
        windowButton = new javax.swing.JLabel();
        searchButton = new javax.swing.JLabel();
        menuButton = new javax.swing.JLabel();
        addButton = new javax.swing.JLabel();
        homeLabel = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        homePanel = new javax.swing.JPanel();
        titleBar = new javax.swing.JPanel();
        searchBar = new javax.swing.JPanel();
        mainBody = new javax.swing.JPanel();
        sideBar = new javax.swing.JPanel(new BorderLayout());
        subSideBar = new javax.swing.JPanel();
        contentBox = new javax.swing.JPanel();
        addPanel = new javax.swing.JPanel();

        contentSearchPanel = new javax.swing.JPanel();
        contentSearchButton = new javax.swing.JLabel();
        contentSearchField = new javax.swing.JTextField();

        contentBody = new javax.swing.JPanel();
        conTents = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        try {
            keywords = SeleniumTest.keywordsList();
            updatedList = SeleniumTest.keywordsList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        keywordsList = new JList<>(updatedList);
        keywordsListScroll = new JScrollPane(keywordsList);
        keywordsListScroll.setPreferredSize(new Dimension(157,314));


        //container setting
        homePanel.setBackground(new java.awt.Color(35, 39, 46));
        homePanel.setMinimumSize(new java.awt.Dimension(1000, 625));
        homePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        //sideBar setting
        sideBar.setBackground(new java.awt.Color( 66, 66, 66));
        sideBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        //titleBar setting
        titleBar.setBackground(new java.awt.Color(33, 33, 33));
        titleBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        //add functions to handle with dragging the window
        titleBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt){
                getMouseDownCompCoords(evt);
            }
        });
        titleBar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                getCursorCoords(evt);
            }
        });
        //mainBody setting
        mainBody.setBackground(new java.awt.Color(43, 58, 64));
        mainBody.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        //searchBar setting
        searchBar.setBackground(new java.awt.Color(28, 31, 38));
        searchBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        //homeLabel setting
        javax.swing.ImageIcon originalBgIcon = new ImageIcon(getClass().getResource("/assets/bg.jpg"));
        java.awt.Image resizedBgImage = originalBgIcon.getImage().getScaledInstance(1000, 660, Image.SCALE_SMOOTH);
        homeLabel.setIcon(new javax.swing.ImageIcon(resizedBgImage));
        //contentBox setting
        contentBox.setBackground(new java.awt.Color(66, 154, 189));
        contentBox.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        //title label setting
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new java.awt.Font("Calibri", 1, 22));
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("Catalogs");
        titleBar.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 4, 150, 50));
        //buttons setting
        closeButton.setBackground(new java.awt.Color(19, 22, 28));
        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Multiply.png"))); // NOI18N
        closeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        closeButton.setOpaque(true);
        closeButton.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeButtonMouseExited(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeButtonClicked(evt);
            }
        });
        //windowButton.setHorizontalAlignment(SwingConstants.LEADING);
        windowButton.setBackground(new java.awt.Color(19, 22, 28));
        windowButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/fullscreen.png"))); // NOI18N
        windowButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        windowButton.setOpaque(true);
        windowButton.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                windowButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                windowButtonMouseExited(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                windowButtonClicked(evt);
            }
        });
        //hideButton.setHorizontalAlignment(SwingConstants.LEADING);
        hideButton.setBackground(new java.awt.Color(19, 22, 28));
        hideButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/minimize.png"))); // NOI18N
        hideButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hideButton.setOpaque(true);
        hideButton.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hideButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hideButtonMouseExited(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hideButtonClicked(evt);
            }
        });
        titleBar.add(closeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(975,0,25,25));
        titleBar.add(windowButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(948,0,25,25));
        titleBar.add(hideButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(921,0,25,25));
        //searchField setting
        searchField.setBackground(new java.awt.Color(19, 22, 28));
        searchField.setForeground(Color.WHITE);
//        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
//            public void keyPressed(java.awt.event.KeyEvent evt) {
//                java.awt.event.MouseAdapter() e = new java.awt.event.MouseAdapter();
//                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) searchButtonClicked();
//            }
//        });
        //searchButton setting
        searchButton.setHorizontalAlignment(SwingConstants.CENTER);
        searchButton.setBackground(new java.awt.Color(19, 22, 28));
        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Search.png"))); // NOI18N
        searchButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchButton.setOpaque(true);
        searchButton.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchButtonMouseExited(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchButtonClicked(evt);
            }
        });
        searchBar.add(searchField, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 5, 100,30));
        searchBar.add(searchButton,  new org.netbeans.lib.awtextra.AbsoluteConstraints(900,5,30,30));
        //addPanel setting
        addPanel.setBackground(new java.awt.Color(85, 107, 104));
        addPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        addPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addPanel.setOpaque(true);
        addPanel.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addPanelMouseExited(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addPanelClicked(evt);
            }
        });
        //addButton setting
        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Water.png"))); // NOI18N
        addButton.setBackground(new java.awt.Color(85, 107, 104));
        addButton.setBorder(new javax.swing.border.EmptyBorder(10,10,10,10));
        addButton.setHorizontalAlignment(SwingConstants.CENTER);
        addButton.setOpaque(true);
        addPanel.add(addButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,50,50));
        //menuButton setting
        menuButton.setBackground(new java.awt.Color(19, 22, 28));
        menuButton.setHorizontalAlignment(SwingConstants.CENTER);
        menuButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/menu.png"))); // NOI18N
        menuButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuButton.setOpaque(true);
        menuButton.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuButtonMouseExited(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt){menuButtonMouseClicked(evt);}
        });
        //scrollSideBar setting
        subSideBar.setBackground(new java.awt.Color(66,66,66));
        subSideBar.setLayout(new BoxLayout(subSideBar, BoxLayout.Y_AXIS));
        sideBar.add(menuButton,  new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,50,50));
        sideBar.add(addPanel,  new org.netbeans.lib.awtextra.AbsoluteConstraints(0,50,205,50));
        javax.swing.JScrollPane scrollSideBar = new javax.swing.JScrollPane(subSideBar);
        sideBar.add(scrollSideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 205, 395));
        //contentSearchField setting
        //contentSearchField.setBackground(new java.awt.Color(19, 22, 28));
        //contentSearchButton setting
        contentSearchButton.setBackground(new java.awt.Color(48, 107, 201));
        javax.swing.ImageIcon originalIcon = new ImageIcon(getClass().getResource("/assets/Search.png"));
        java.awt.Image resizedImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        contentSearchButton.setIcon(new javax.swing.ImageIcon(resizedImage));
        contentSearchButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentSearchButton.setOpaque(true);
        contentSearchButton.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                contentSearchButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                contentSearchButtonMouseExited(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    contentSearchButtonClicked(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        contentSearchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String input = contentSearchField.getText();
                updatedList.clear();
                updateList(input, keywords);
                keywordsListScroll.setVisible(true);
            }
        });
        keywordsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String selectedValue = (String) keywordsList.getSelectedValue();
                    contentSearchField.setText(selectedValue);
                    keywordsList.clearSelection();
                    keywordsListScroll.setVisible(false);
                }
            }
        });
        //contentSearchPanel setting
        contentSearchPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        contentSearchPanel.add(contentSearchField, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,157,50));
        contentSearchPanel.add(contentSearchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(160,0,50,50));
        //contentScrollBox setting
        contentBody.setBackground(new java.awt.Color( 48, 48, 48));
        contentBody.setLayout(new java.awt.GridLayout(1,1));
        contentBody.setPreferredSize(new Dimension(450, conTents.getPreferredSize().height));
        contentBody.setMaximumSize(new Dimension(450, Integer.MAX_VALUE));
        conTents.setHorizontalAlignment(SwingConstants.LEFT);
        conTents.setForeground(Color.WHITE);
        contentBody.add(conTents, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,450,500));
        contentScrollBox = new javax.swing.JScrollPane(contentBody);

        contentBox.add(contentSearchPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(385, 20, 210, 50));
        contentBox.add(contentScrollBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 943, 400));
        contentBox.add(homeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,1000,660));

        //add to homepanel
        homePanel.add(titleBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 54));
        homePanel.add(searchBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 54, 1000, 54));

        homePanel.add(keywordsListScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 175, 157,-1));
        keywordsListScroll.setVisible(true);

        mainBody.add(sideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 500));
        mainBody.add(contentBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 0, 943, 500));
        homePanel.add(mainBody, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 108, 1000, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(homePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(homePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    java.awt.Point mouseDownCoords;
    private void getMouseDownCompCoords(java.awt.event.MouseEvent evt){
        mouseDownCoords = evt.getPoint();
    }
    private void getCursorCoords(java.awt.event.MouseEvent evt){
        java.awt.Point currCoords = evt.getLocationOnScreen();
        setLocation(currCoords.x - mouseDownCoords.x, currCoords.y - mouseDownCoords.y);
    }
    int x = 50;
    int a = 50;
    private void menuButtonMouseEntered(java.awt.event.MouseEvent evt){
        menuButton.setBackground(new java.awt.Color(32, 37, 46));
    }
    private void menuButtonMouseExited(java.awt.event.MouseEvent evt){
        menuButton.setBackground(new java.awt.Color(19, 22, 28));
    }
    private void menuButtonMouseClicked(java.awt.event.MouseEvent evt){
         if ( x == 210 ) {
            sideBar.setSize(210, 552);
            Thread th = new Thread() {
                @Override
                public void run(){
                    try {

                        for ( int i = 210; i >= 50; i--){
                            Thread.sleep(1);
                            sideBar.setSize(i, 552);
                            if(i <= 100) {
                                addButton.setLocation(i-50,1);
                            }

                            a++;
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                }
            };th.start();
            x=50;
        } else if( x == 50 ){
            sideBar.setSize(x, 552);
            Thread th = new Thread(){
                @Override
                public void run(){
                    try {
                        for (int i = 50; i <= x; i++){
                            Thread.sleep(1);
                            sideBar.setSize(i, 552);
                            if(i <= 120) {
                                addButton.setLocation(i-50,1);
                            }
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                }
            };th.start();
            x = 210;
        }
    }
    private void closeButtonMouseEntered(java.awt.event.MouseEvent evt){
        closeButton.setBackground(new java.awt.Color(227, 52, 75));
    }
    private void closeButtonMouseExited(java.awt.event.MouseEvent evt){
        closeButton.setBackground(new java.awt.Color(19, 22, 28));
    }
    private void closeButtonClicked(java.awt.event.MouseEvent evt){
        System.exit(0);
    }
    private void windowButtonMouseEntered(java.awt.event.MouseEvent evt){
        windowButton.setBackground(new java.awt.Color(58, 194, 240));
    }
    private void windowButtonMouseExited(java.awt.event.MouseEvent evt){
        windowButton.setBackground(new java.awt.Color(19, 22, 28));
    }
    private void windowButtonClicked(java.awt.event.MouseEvent evt){

    }
    private void hideButtonMouseEntered(java.awt.event.MouseEvent evt){
        hideButton.setBackground(new java.awt.Color(69, 247, 140));
    }
    private void hideButtonMouseExited(java.awt.event.MouseEvent evt){
        hideButton.setBackground(new java.awt.Color(19, 22, 28));
    }
    private void hideButtonClicked(java.awt.event.MouseEvent evt){
        setState(Frame.ICONIFIED);
    }
    private void searchButtonMouseEntered(java.awt.event.MouseEvent evt){
        searchButton.setBackground(new java.awt.Color(32, 37, 46));
    }
    private void searchButtonMouseExited(java.awt.event.MouseEvent evt){
        searchButton.setBackground(new java.awt.Color(19, 22, 28));
    }
    private void searchButtonClicked(java.awt.event.MouseEvent evt){

    }
    private void addPanelMouseEntered(java.awt.event.MouseEvent evt){
        addPanel.setBackground(new java.awt.Color(51, 64, 62));
    }
    private void addPanelMouseExited(java.awt.event.MouseEvent evt){
        addPanel.setBackground(new java.awt.Color(85, 107, 104));
    }
    int colorR = 77;
    private void addPanelClicked(java.awt.event.MouseEvent evt){
        if (colorR == 77) colorR = 33;
        else colorR = 77;
        System.out.println("add");
        javax.swing.JPanel newPanel = new javax.swing.JPanel(new java.awt.GridLayout(1, 2));
        javax.swing.JLabel image = new javax.swing.JLabel();
        javax.swing.JLabel text = new javax.swing.JLabel();

        newPanel.setBackground(new java.awt.Color(colorR, 94, 81));
        newPanel.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
        newPanel.setPreferredSize(new java.awt.Dimension(180, 50));

        newPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newPanel.setOpaque(true);

        image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/wikipedia.png")));
        image.setBackground(new java.awt.Color(227, 52, 75));

        text.setText("<html><h2>Text</h2> </br><b>text</b></html>");
        text.setBackground(new java.awt.Color(227, 52, 75));
        newPanel.add(image);
        newPanel.add(text);
        subSideBar.add(newPanel, 0);

        subSideBar.revalidate();
        subSideBar.repaint();
    }
    private void contentSearchButtonMouseEntered(java.awt.event.MouseEvent evt){
        contentSearchButton.setBackground(new java.awt.Color(31, 75, 145));
    }
    private void contentSearchButtonMouseExited(java.awt.event.MouseEvent evt){
        contentSearchButton.setBackground(new java.awt.Color(48, 107, 201));
    }
    private void contentSearchButtonClicked(java.awt.event.MouseEvent evt) throws IOException {
        String name = contentSearchField.getText();
        contentBody.removeAll();

//        List<String> strList = Wikipedia_Crawler.Go(name);
//        String Pg="";
//        for(String data:strList){
//            Pg += data + "<br />";
//        }
//        conTents.setText("<html>"+Pg+"</html>");
//        javax.swing.JLabel imageLabel = new javax.swing.JLabel();
//        URL url = new URL(Wikipedia_Crawler.getImage(name));
//        BufferedImage image = ImageIO.read(url);
//        imageLabel.setIcon(new javax.swing.ImageIcon(image.getScaledInstance(250, 250, Image.SCALE_SMOOTH)));
//        contentBody.add(imageLabel);

        javax.swing.JPanel contentBodyUpdate = new javax.swing.JPanel();
        contentBodyUpdate.setBackground(new java.awt.Color(252, 3, 11));
        contentBodyUpdate.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        contentBody.add(contentBodyUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 1000));

        javax.swing.JPanel contentBodyText = new javax.swing.JPanel();
        javax.swing.JPanel contentBodyPic = new javax.swing.JPanel();
        contentBodyText.setBackground(new java.awt.Color(0, 242, 255));
        contentBodyPic.setBackground(new java.awt.Color(0, 255, 21));
        contentBodyText.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        contentBodyPic.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        List<String> strList = Wikipedia_Crawler.Go(name);
        List<String> contentList = Wikipedia_Crawler.mainContents(name);

        int textID=0;
        int contentID=0;

        javax.swing.JPanel newTextPanel = new javax.swing.JPanel(new java.awt.GridLayout(strList.size(),1));
        newTextPanel.setSize(440, 1000);
        javax.swing.JScrollPane newScrollText = new javax.swing.JScrollPane(newTextPanel);
        for(String data:strList){
            javax.swing.JPanel textPanel_p = new javax.swing.JPanel();
            textPanel_p.setName("text " + ++textID);
            javax.swing.JLabel  text_p = new javax.swing.JLabel("<html>"+data+"</html>", SwingConstants.LEFT);
            textPanel_p.add(text_p, LEFT_ALIGNMENT);
            newTextPanel.add(textPanel_p);
        }
        for(String content:contentList){
            javax.swing.JPanel contentPanel_p = new javax.swing.JPanel();
            contentPanel_p.setName("content " + ++contentID);
            javax.swing.JLabel  content_p = new javax.swing.JLabel("<html><p>"+content+"</p></html>", SwingConstants.LEFT);
            content_p.setHorizontalAlignment(SwingConstants.LEFT);
            contentPanel_p.add(content_p);
            newTextPanel.add(contentPanel_p);
        }

        List<String> imgList = Wikipedia_Crawler.getImage(name);

        int imgID=0;
        javax.swing.JPanel newImgPanel = new javax.swing.JPanel(new java.awt.GridLayout(imgList.size(),1));
        javax.swing.JScrollPane newScrollImg = new javax.swing.JScrollPane(newImgPanel);
        newScrollImg.setPreferredSize(new Dimension(447, 450));
        newScrollImg.getVerticalScrollBar().setBlockIncrement(500);
        for(String img:imgList){
            try{
                URL url = new URL(img);
                BufferedImage image = ImageIO.read(url);
                System.out.println(image.getWidth());
                if(image.getHeight()>100){
                    javax.swing.JPanel imgPanel_p = new javax.swing.JPanel();
                    imgPanel_p.setName("img " + ++imgID);
                    javax.swing.JLabel imageLabel = new javax.swing.JLabel();
                    imageLabel.setIcon(new javax.swing.ImageIcon(image.getScaledInstance(400, 400, Image.SCALE_SMOOTH)));

                    imgPanel_p.add(imageLabel);
                    newImgPanel.add(imgPanel_p);
                }
            }catch (IOException e){
                throw e;
            }
        }
        contentBodyText.add(newScrollText, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 495, 400));
        contentBodyPic.add(newScrollImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 400));

        contentBodyUpdate.add(contentBodyText, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 1000));
        contentBodyUpdate.add(contentBodyPic, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 500, 1000));

        contentBody.revalidate();
        contentBody.repaint();
    }
    public void updateList(String input, DefaultListModel<String> defaultList){
        for (int i = 0; i < defaultList.getSize(); i++) {
            String keyword = (String) defaultList.getElementAt(i);
            if (WikipediaAPIRequest.removeDiacritics(keyword.toLowerCase()).contains(input)) {
                updatedList.addElement(keyword);
            }
        }
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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }
}
