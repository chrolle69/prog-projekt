package presentation;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import domain.*;
import domain.MediaOverview.SearchType;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;

public class UserInterface
{
    private JFrame frame;
    private MediaOverviewImpl mediaOverview;
    private JPanel displayPanel;
    private JTextField searchField;
    private JButton favButton;
    private ArrayList<JCheckBox> categorieCheckBoxes;
    private JScrollPane displayScrollPane;
    private JRadioButton typeAll;

    private boolean isFavoritesOn;
    private List<String> selectedCategories;
    private SearchType selectedType;

    private int displayBoxes;
    private int displayBoxWidth;
    private int displayBoxHeight;
    private int displayBoxGap;
    private int popUpWidth;
    private int popUpHeight;

    private UserInterface()
    {
        this.displayBoxWidth = 140;
        this.displayBoxHeight = 280;
        this.displayBoxGap = 20;
        this.popUpWidth = 650;
        this.popUpHeight = 500;
        this.isFavoritesOn = false;
        this.selectedCategories = new ArrayList<>();
        this.selectedType = SearchType.ALL;
        this.mediaOverview = new MediaOverviewImpl();
        initialize();
    }   

    public static void main(String[] args) throws Exception 
    {
        new UserInterface();
    }

    private void initialize()
    {
        //Make frame
        frame = new JFrame("FlixNet");
        frame.setSize(1200, 800);
        frame.setMinimumSize(new Dimension(1100, 480));

        //Get the content pane from the frame. This where all other components get added to.
        Container pane = frame.getContentPane();

        //Make the top area of the UI. (Searchbar, favorite-button, categories drop-down)
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        //Home Button (Reset Button)
        JButton homebutton = new JButton("Home");
        homebutton.setPreferredSize(new Dimension(100, 30));
        homebutton.addActionListener(e -> {resetDisplayedMedia();});
        topPanel.add(homebutton);

        //Searchbar
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        searchField = new JTextField("Search");
        searchField.setPreferredSize(new Dimension(400, 30));
        searchPanel.add(searchField);
        
        //Search button
        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(80, 30));
        searchButton.addActionListener(e -> {search(searchField.getText());});
        searchPanel.add(searchButton);
        topPanel.add(searchPanel);

        //Favorite Button
        favButton = new JButton("Favorites");
        favButton.setPreferredSize(new Dimension(100, 30));
        favButton.setBackground(new Color(225, 0, 0));
        favButton.addActionListener(e -> {toggleFavorites(favButton);});
        topPanel.add(favButton);
        
        //Adding top area to the frames content pane. 
        pane.add(topPanel, BorderLayout.PAGE_START);

        //Sidebar for categorie selection
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.PAGE_AXIS));
        sidebar.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //Type Label
        JLabel typeLabel = new JLabel("Type");
        typeLabel.setMaximumSize(new Dimension(200, 20));
        typeLabel.setHorizontalAlignment(JLabel.CENTER);
        typeLabel.setAlignmentX(0.1f);
        sidebar.add(typeLabel);

        //Type radio buttons
        typeAll = new JRadioButton("All");
        JRadioButton typeMovie = new JRadioButton("Movie");
        JRadioButton typeSeries = new JRadioButton("Series");
        ButtonGroup bg = new ButtonGroup();
        bg.add(typeAll);
        bg.add(typeMovie);
        bg.add(typeSeries);
        typeAll.setAlignmentX(Component.LEFT_ALIGNMENT);
        typeMovie.setAlignmentX(Component.LEFT_ALIGNMENT);
        typeSeries.setAlignmentX(Component.LEFT_ALIGNMENT);
        typeAll.setSelected(true);
        typeAll.addItemListener(e -> {selectedType = SearchType.ALL;});
        typeMovie.addItemListener(e -> {selectedType = SearchType.MOVIE;});
        typeSeries.addItemListener(e -> {selectedType = SearchType.SERIES;});
        sidebar.add(typeAll);
        sidebar.add(typeMovie);
        sidebar.add(typeSeries);

        //Categorie label
        JLabel categorieLabel = new JLabel("Categories");
        categorieLabel.setMaximumSize(new Dimension(200, 20));
        categorieLabel.setHorizontalAlignment(JLabel.CENTER);
        categorieLabel.setAlignmentX(0.1f);
        sidebar.add(categorieLabel);

        //Filler to align the categorie label and categorie checkboxes properly
        Dimension d = new Dimension(200, 10);
        Box.Filler filler = new Box.Filler(d, d, d);
        filler.setAlignmentX(0.1f);
        sidebar.add(filler);

        //Categorie Checkboxes
        categorieCheckBoxes = new ArrayList<>();
        Set<String> cats = mediaOverview.getCategories();
        for(String cat : cats)
        {
            JCheckBox catcheck = new JCheckBox(cat);
            catcheck.addItemListener(e -> {categorieCheckBoxClicked(e, cat);});
            catcheck.setAlignmentX(Component.LEFT_ALIGNMENT);
            categorieCheckBoxes.add(catcheck);
            sidebar.add(catcheck);
        }
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));

        //Button for applying categorie and type filtering
        JButton applybutton = new JButton("Apply");
        applybutton.setMaximumSize(new Dimension(160, 20));
        applybutton.setAlignmentX(0f);
        applybutton.addActionListener(e -> {applyCategoriesAndType();});
        sidebar.add(applybutton);

        pane.add(sidebar, BorderLayout.LINE_START);

        //Making the area that displays media. 
        displayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, displayBoxGap, displayBoxGap));
        displayPanel.addComponentListener(new ResizeListener());
        displayScrollPane = new JScrollPane();
        displayScrollPane.setViewportView(displayPanel);
        displayScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        displayScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        List<Media> mediaList = mediaOverview.getMediaList();
        updateDisplay(mediaList);

        pane.add(displayScrollPane, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    //Resets searchbar, favorite button and categorie dropdown menu. Shows Everything.
    private void resetDisplayedMedia()
    {
        searchField.setText("Search");
        favButton.setBackground(new Color(225, 0, 0));
        isFavoritesOn = false;
        for(JCheckBox box : categorieCheckBoxes) {box.setSelected(false);}
        selectedCategories.clear();
        selectedType = SearchType.ALL;
        typeAll.setSelected(true);
        List<Media> mediaList = mediaOverview.getMediaList();
        updateDisplay(mediaList);
    }

    //Show/Hides favorite movies and series. 
    private void toggleFavorites(JButton favoriteButton)
    {
        for(JCheckBox box : categorieCheckBoxes) {box.setSelected(false);}
        selectedCategories.clear();
        searchField.setText("Search");
        selectedType = SearchType.ALL;
        typeAll.setSelected(true);

        if(!isFavoritesOn)
            {
                isFavoritesOn = true;
                favoriteButton.setBackground(new Color(0, 225, 0));
                List<Media> fml = mediaOverview.getFavoriteMedia();
                updateDisplay(fml);
            }
        else
            {
                isFavoritesOn = false;
                favoriteButton.setBackground(new Color(225, 0, 0));
                List<Media> cml = mediaOverview.getMediaList();
                updateDisplay(cml);
            }
    }

    //Show movies and series bases on search keyword. 
    private void search(String keyword)
    {
        favButton.setBackground(new Color(225, 0, 0));
        isFavoritesOn = false;
        for(JCheckBox box : categorieCheckBoxes) {box.setSelected(false);}
        selectedCategories.clear();
        selectedType = SearchType.ALL;
        typeAll.setSelected(true);
        List<Media> ml = mediaOverview.searchMedia(keyword);
        updateDisplay(ml);
    }
    
    //Add or remove categorie from list of selected categories
    private void categorieCheckBoxClicked(ItemEvent e, String categorie)
    {
        if(e.getStateChange() == ItemEvent.SELECTED)
        {
            selectedCategories.add(categorie);
        }
        if(e.getStateChange()  == ItemEvent.DESELECTED)
        {
            selectedCategories.remove(categorie);
        }
    }

    //Show media with selected categories
    private void applyCategoriesAndType()
    {
        favButton.setBackground(new Color(225, 0, 0));
        isFavoritesOn = false;
        searchField.setText("Search");
        List<Media> mediaList = mediaOverview.searchCategories(selectedCategories, selectedType);
        updateDisplay(mediaList);
    }

    //Updates the panel that displays media with movies and series in the lists given.
    private void updateDisplay(List<Media> mediaList)
    {
        //Update the amount of displayboxes and clear the display panel
        displayBoxes = mediaList.size();
        displayPanel.removeAll();
        resizeDisplayPanel();

        for(Media media : mediaList)
        {
            //Panel for displaybox
            JPanel da = new JPanel();
            da.setLayout(new BoxLayout(da, BoxLayout.PAGE_AXIS));
            da.setPreferredSize(new Dimension(displayBoxWidth, displayBoxHeight));
            da.setBackground(new Color(255, 255, 255));

            //Poster
            JLabel poster = new JLabel(new ImageIcon(media.getPoster()));
            poster.setMaximumSize(new Dimension(media.getPoster().getWidth(), media.getPoster().getHeight()));
            poster.setAlignmentX(Component.CENTER_ALIGNMENT);
            poster.addMouseListener(new MediaClickListener(media));
            da.add(poster);
            da.add(Box.createVerticalGlue());

            //Title Label
            JLabel title = new JLabel(media.getName());
            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            title.setMaximumSize(new Dimension(120, 50));
            title.setHorizontalAlignment(JLabel.CENTER);
            da.add(title);
            da.add(Box.createVerticalGlue());

            //Button for saving as favorite
            JButton saveFavorite = new JButton();
            saveFavorite.setMaximumSize(new Dimension(125, 40));
            saveFavorite.setAlignmentX(Component.CENTER_ALIGNMENT);
            if(mediaOverview.isFavorite(media))
            {
                saveFavorite.setText("Remove Favorite");
                saveFavorite.setBackground(new Color(225, 0, 0));
            }
            else
            {
                saveFavorite.setText("Add Favorite");
                saveFavorite.setBackground(new Color(0, 225, 0));
            }
            saveFavorite.addActionListener(e -> {addRemoveFavorite(media, saveFavorite);});
            da.add(saveFavorite);
            da.add(Box.createRigidArea(new Dimension(0, 5)));
            
            displayPanel.add(da);
        }

        //Updates what is shown in the panel
        displayPanel.revalidate();
        displayPanel.repaint();
    }

    //Creates the popup window for the given movie or series. 
    private void createMediaPopUp(Media media)
    {
        //Instantiating frame and sets size and location
        JFrame mediaFrame = new JFrame();
        mediaFrame.setSize(popUpWidth, popUpHeight);
        mediaFrame.setMinimumSize(new Dimension(popUpWidth, popUpHeight));
        int popUpHoriLoc = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - popUpWidth / 2;
        int popUpVerLoc = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - popUpHeight / 2;
        mediaFrame.setLocation(popUpHoriLoc, popUpVerLoc);

        //Gets the container which components will be in and sets layout. 
        Container mediaPane = mediaFrame.getContentPane();
        mediaPane.setLayout(new GridBagLayout());
        //Object to store constraints that specifies how components look and behave in the GridBagLayout.
        GridBagConstraints constraints = new GridBagConstraints();

        //Poster
        JLabel poster = new JLabel(new ImageIcon(media.getPoster()));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.insets = new Insets(10, 10, 10, 25);
        mediaPane.add(poster, constraints);

        //Media Info Textbox
        String categories = "";
        for(String categorie : media.getCategories()) {categories += categorie + ", ";}
        categories = categories.substring(0, categories.length() - 2);
        JTextArea info = new JTextArea(
            "Title: " + media.getName() + "\n" +
            "Release Year: " + media.getYear() + "\n" +
            "Categories: " + categories + "\n" + 
            "IMDB Rating: " + media.getRating()
        );
        info.setBackground(frame.getBackground());
        info.setEditable(false);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridheight = 1;
        constraints.insets = new Insets(10, 10, 10, 25);
        mediaPane.add(info, constraints);

        //Video List
        Map<String, List<Video>> mediaVideoInfo = media.getInfoMap();
        JPanel videoList = new JPanel();
        videoList.setLayout(new BoxLayout(videoList, BoxLayout.PAGE_AXIS));

        //Scroll pane for the video list. Makes it possible to scroll.
        JScrollPane videoListScroll = new JScrollPane();
        videoListScroll.setViewportView(videoList);
        videoListScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        videoListScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        videoListScroll.getVerticalScrollBar().setUnitIncrement(10);
        videoListScroll.setPreferredSize(new Dimension(250, 300));

        //Looping through the keys of the LinkedHashMap with the video info
        for(String name : mediaVideoInfo.keySet())
        {
            //Textarea for the name of the list of videos. eg. "Season 1"
            JLabel nameTA = new JLabel(name + ":");
            nameTA.setMaximumSize(new Dimension(200, 20));
            nameTA.setBackground(frame.getBackground());
            nameTA.setAlignmentX(0f);
            videoList.add(nameTA);

            //Looping through the list of videos associated with the specific key
            for(Video episode : mediaVideoInfo.get(name))
            {
                //Panel for video title and play button
                JPanel video = new JPanel();
                video.setLayout(new BoxLayout(video, BoxLayout.LINE_AXIS));
                video.setAlignmentX(0f);
                Dimension size = new Dimension(25, 20);
                video.add(new Box.Filler(size, size, size));

                //Video title textarea
                JLabel videoTitle = new JLabel(episode.getName() + ":");
                videoTitle.setMaximumSize(new Dimension(200, 20));
                videoTitle.setBackground(frame.getBackground());
                video.add(videoTitle);

                //Play Button
                JButton playButton = new JButton("Play");
                playButton.setMaximumSize(new Dimension(80, 20));
                playButton.addActionListener((ActionEvent e) -> {playVideo(episode.getPlayMessage());});
                video.add(playButton);
                videoList.add(video);
            }
            videoList.add(Box.createVerticalGlue());
        }

        //Moves the scrollbar to the top.
        SwingUtilities.invokeLater(() -> {videoListScroll.getVerticalScrollBar().setValue(0);});
        
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridheight = 2;
        constraints.insets = new Insets(10, 25, 10, 10);
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        mediaPane.add(videoListScroll, constraints);

        //Sets frame close operation and visibility
        mediaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mediaFrame.setVisible(true);
    }

    //Shows the play message given. 
    private void playVideo(String playMessage)
    {
        //Frame and panel for the window
        JFrame playBackFrame = new JFrame();
        playBackFrame.setSize(1200, 800);
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(0, 0, 0));

        //TextArea for the play message
        JTextArea video = new JTextArea(playMessage);
        float fontsize = video.getFont().getSize() + 25f;
        video.setFont(video.getFont().deriveFont(fontsize));
        video.setBackground(new Color(0, 0, 0));
        video.setForeground(new Color(255, 255, 255));
        video.setEditable(false);
        panel.add(video);
        playBackFrame.getContentPane().add(panel);

        //Sets default close operation, visibility and maximized screen.
        playBackFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        playBackFrame.setVisible(true);
        playBackFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    //Add or Remove specified media from favorite list
    private void addRemoveFavorite(Media media, JButton button)
    {
        if(mediaOverview.isFavorite(media))
        {
            button.setText("Add Favorite");
            button.setBackground(new Color(0, 225, 0));
            mediaOverview.removeFavorite(media);
        }
        else
        {
            button.setText("Remove Favorite");
            button.setBackground(new Color(225, 0, 0));
            mediaOverview.addFavorite(media);
        }
    }

    //Resize the display panel
    private void resizeDisplayPanel()
    {
        //Calculating and sets the new size of the frame. 
        int displayBoxColoumns = (displayScrollPane.getViewport().getWidth()) / (displayBoxWidth + displayBoxGap);
        int displayBoxRows = (int) Math.ceil(displayBoxes * 1.0 / displayBoxColoumns);
        int panelheight = displayBoxRows * (displayBoxHeight + displayBoxGap);
        displayPanel.setPreferredSize(new Dimension(500, panelheight));
    }

    //ComponentListener for reacting to the window being resized. Ensures all media is shown.
    private class ResizeListener extends ComponentAdapter
    {
        @Override
        public void componentResized(ComponentEvent e)
        {
            resizeDisplayPanel();
        }
    }

    //MouseListener for reacting to clicking on a movie or series.
    private class MediaClickListener extends MouseAdapter
    {
        Media media;

        public MediaClickListener(Media media)
        {
            this.media = media;
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            createMediaPopUp(media);
        }
    }

}
