package ui;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import model.*;
import rss.RssItem;
import rss.RssParser;

public class ProFrame extends JFrame {

    static int width = 800;
    static int height = 600;
    private TableFeedModel model;

    public static void main(String... args) {
        ProFrame proFrame = new ProFrame();
        proFrame.init(width, height);
    }

    private void init(int width, int height) {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(width, height);
        setTitle("Programování 2");

        GridBagConstraints c = new GridBagConstraints();
        boolean shouldFill = true;


        JPanel feedData = new JPanel();
        feedData.setPreferredSize(new Dimension(800, 600));


        JPanel toolBarAndTable = new JPanel();
        toolBarAndTable.setLayout(new BoxLayout(toolBarAndTable, BoxLayout.Y_AXIS));

        JPanel toolbar = new JPanel();
        toolbar.setPreferredSize(new Dimension(500, 30));

        JTextField urlTextField = new JTextField();
        urlTextField.setPreferredSize( new Dimension( 350, 24 ) );
        toolbar.add(urlTextField);

        JButton addUrlButton = new JButton();
        addUrlButton.setText("Add Url");
        toolbar.add(addUrlButton);
        toolBarAndTable.add(toolbar);

        model = new TableFeedModel();
        JTable table = new JTable(model);
        JScrollPane scrollPaneTable = new JScrollPane(table);
        toolBarAndTable.add(scrollPaneTable);

        JButton settingsButton = new JButton("             Nastaveni            ");
        settingsButton.setAlignmentX(CENTER_ALIGNMENT);
        Insets insets = new Insets(0, 10, 0, 10);
        settingsButton.setMargin(insets);

        toolBarAndTable.add(settingsButton);

        add(feedData, BorderLayout.LINE_START);
        add(toolBarAndTable, BorderLayout.LINE_END);

        pack();

        setLocationRelativeTo(null); //center okna na monitoru


        readFeeds();



//        JButton button = new JButton();
//        button.setText("Přidat poznámku");
//        toolbar.add(button);
//
//        JButton saveButton = new JButton();
//        saveButton.setText("Uložit");
//        toolbar.add(saveButton);
//
//        JButton loadButton = new JButton();
//        loadButton.setText("Načíst");
//        toolbar.add(loadButton);


        addUrlButton.addActionListener(action -> {
            String url = urlTextField.getText();
            UrlItem urlItem = new UrlItem(url, "",30, true);
            model.add(urlItem);
            this.addFeed(url);
            this.parse(url);
        });

//        button.addActionListener(action -> {
//            ToDoItem item = new ProDialog().getItem();
//            model.add(item);
//        });
//        saveButton.addActionListener(action -> {
//            saveItems();
//        });
//        loadButton.addActionListener(action -> {
//            loadItems();
//        });



    }

    private void parse(String url){
        try{
//            RssParser parser = new RssParser(
//                    new FileInputStream(
//                            new File("src\\data\\download.xml")));
//            String url = "http://www.eurofotbal.cz/feed/rss/premier-league/";
//            String url1 = "http://servis.idnes.cz/rss.aspx?c=zpravodaj";
//            String url2 = "http://servis.idnes.cz/rss.aspx?c=zpravodaj";
            URLConnection connection = new URL(url).openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            RssParser parser = new RssParser(stream);

            List<RssItem> rssItems = parser.parseItems();

            for(RssItem rssItem : rssItems){
                System.out.println(rssItem.rssItemToString());
            }
            stream.close();

        }catch (Exception e){
            System.out.println(e);
        }

    }

    private void saveItems() {
        try {
            ObjectOutputStream stream =
                    new ObjectOutputStream(
                            new FileOutputStream(
                                    new File("our.db")
                            )
                    );
            stream.writeObject(model.getItems());
            stream.flush();
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadItems() {
        try {
            ObjectInputStream stream = new ObjectInputStream(
                    new FileInputStream(
                            new File("our.db")
                    )
            );
            List<UrlItem> urlItems = (List<UrlItem>) stream.readObject();
            stream.close();
            model.setItems(urlItems);
            model.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param url
     */
    private void addFeed(String url){
        try{
            File file = new File("feed.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write(url);
            writer.newLine();
            writer.flush();

        }catch (Exception e){

        }

    }

    private void readFeeds(){ // casem list string
        try{
            List<String> urls = new ArrayList<>();
            File file = new File("feed.txt");

            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while((line = reader.readLine()) != null){
                urls.add(line);
            }

            for(String url : urls){
                System.out.println(url);
            }


        }catch (Exception e){
            System.out.print(e);
        }
    }

    private List<FeedItem> getAllFeeds(){
        List<FeedItem> feedItems = new ArrayList<>();
        try{
            File file = new File("feedItems.csv");
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            bufferedReader.readLine(); // preskoci prvni radek
            String line;
            while((line = bufferedReader.readLine()) != null){
                feedItems.add(FeedItem.parseFromCSV(line));
            }


        }catch (Exception e){

        }
//        parse souboru TODO ulozit komplet feedy do seznamu
        return feedItems;
    }

    private void saveAllFeeds(List<FeedItem> items){
        try {
            File file = new File("feedItems.csv");
            FileWriter writer = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write("url; addedMilis; shouldShow; alias");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            for(FeedItem item : items){
                bufferedWriter.write(item.toString());
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }catch (Exception e){

        }
    }
}
