package facebook;


public class MediaStatistics {
    private String mediaId;
    private int engagement;
    private int impressions;
    private int reach;
    private int saved;

    // Getters and setters
    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public int getEngagement() {
        return engagement;
    }

    public void setEngagement(int engagement) {
        this.engagement = engagement;
    }

    public int getImpressions() {
        return impressions;
    }

    public void setImpressions(int impressions) {
        this.impressions = impressions;
    }

    public int getReach() {
        return reach;
    }

    public void setReach(int reach) {
        this.reach = reach;
    }

    public int getSaved() {
        return saved;
    }

    public void setSaved(int saved) {
        this.saved = saved;
    }
}
