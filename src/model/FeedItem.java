package model;

public class FeedItem {

    private String url;
    private long addedMillis;
    private boolean shouldShow;
    private String alias;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getAddedMillis() {
        return addedMillis;
    }

    public void setAddedMillis(long addedMillis) {
        this.addedMillis = addedMillis;
    }

    public boolean isShouldShow() {
        return shouldShow;
    }

    public void setShouldShow(boolean shouldShow) {
        this.shouldShow = shouldShow;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return String.format("%s;%s;%s;%s", this.url, this.addedMillis, this.shouldShow, this.alias);
    }

    public static FeedItem parseFromCSV(String line){
//        TODO: fixme osetrit pole hodnot
// if(names.length == 4){
//
//        }
        String [] names = line.split(";");
        FeedItem feedItem = new FeedItem();
        feedItem.setUrl(names[0]);
        feedItem.setAddedMillis(Long.parseLong(names[1]));
        feedItem.setShouldShow(Boolean.parseBoolean(names[2]));
        feedItem.setAlias(names[3]);
        return feedItem;
    }
}
