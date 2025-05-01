package instagramme;

public class InstagramPostStats {
    private String postId;
    private int impressions;
    private int reach;
    private int likes;
    private int comments;
    private int saved;

    public InstagramPostStats(String postId, int impressions, int reach, int likes, int comments, int saved) {
        this.postId = postId;
        this.impressions = impressions;
        this.reach = reach;
        this.likes = likes;
        this.comments = comments;
        this.saved = saved;
    }

    public String getPostId() {
        return postId;
    }

    public int getImpressions() {
        return impressions;
    }

    public int getReach() {
        return reach;
    }

    public int getLikes() {
        return likes;
    }

    public int getComments() {
        return comments;
    }

    public int getSaved() {
        return saved;
    }
}
