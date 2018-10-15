package rss;

public class RssItem {

    private String title, link, description;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String rssItemToString(){
        return String.format("%s\n%s\n%s", title, link, description);

    }
}
