package instagramme;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class InstagramDAO {
    private static final String API_URL = "https://graph.facebook.com/v17.0";

    // Méthode pour récupérer toutes les publications
    public List<String> getAllPostIds(String accountId, String accessToken) throws Exception {
        String apiUrl = API_URL + "/" + accountId + "/media?access_token=" + accessToken;

        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject jsonResponse = new JSONObject(response.toString());

        if (!jsonResponse.has("data")) {
            throw new Exception("Aucune publication trouvée pour ce compte.");
        }

        JSONArray dataArray = jsonResponse.getJSONArray("data");
        List<String> postIds = new ArrayList<>();

        for (int i = 0; i < dataArray.length(); i++) {
            postIds.add(dataArray.getJSONObject(i).getString("id"));
        }

        return postIds;
    }

    // Méthode pour récupérer les statistiques d'une publication
    public InstagramPostStats getPostStats(String postId, String accessToken) throws Exception {
        String apiUrl = API_URL + "/" + postId + "/insights?metric=impressions,reach,likes,comments,saved&access_token=" + accessToken;

        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject jsonResponse = new JSONObject(response.toString());
        JSONArray statsArray = jsonResponse.getJSONArray("data");

        int impressions = statsArray.getJSONObject(0).getJSONArray("values").getJSONObject(0).getInt("value");
        int reach = statsArray.getJSONObject(1).getJSONArray("values").getJSONObject(0).getInt("value");
        int likes = statsArray.getJSONObject(2).getJSONArray("values").getJSONObject(0).getInt("value");
        int comments = statsArray.getJSONObject(3).getJSONArray("values").getJSONObject(0).getInt("value");
        int saved = statsArray.getJSONObject(4).getJSONArray("values").getJSONObject(0).getInt("value");

        return new InstagramPostStats(postId, impressions, reach, likes, comments, saved);
    }

    // Méthode pour récupérer les statistiques de toutes les publications
    public List<InstagramPostStats> getAllPostStats(String accountId, String accessToken) throws Exception {
        List<String> postIds = getAllPostIds(accountId, accessToken);
        List<InstagramPostStats> statsList = new ArrayList<>();

        for (String postId : postIds) {
            statsList.add(getPostStats(postId, accessToken));
        }

        return statsList;
    }
}
