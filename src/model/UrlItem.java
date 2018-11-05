package model;

public class UrlItem {
    private String url, alias;
    private long addedMilis;
    private boolean shouldShow;

    public UrlItem(String url, String alias, long addedMilis, boolean shouldShow) {
        this.url = url;
        this.alias = alias;
        this.addedMilis = addedMilis;
        this.shouldShow = shouldShow;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public long getAddedMilis() {
        return addedMilis;
    }

    public void setAddedMilis(long addedMilis) {
        this.addedMilis = addedMilis;
    }

    public boolean isShouldShow() {
        return shouldShow;
    }

    public void setShouldShow(boolean shouldShow) {
        this.shouldShow = shouldShow;
    }
}
