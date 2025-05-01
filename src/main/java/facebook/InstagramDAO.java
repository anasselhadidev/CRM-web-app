package facebook;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class InstagramDAO {
    private static final String ACCESS_TOKEN = "IGAASVaR2tPcNBZAE5BNWw3bHRHR2xVZAGRtM0RXR2JfaWd2NmF5WEUxYXBEN3F6R2xNd1RWaVlrZA3Vvbk5nYWJ1c3dyR2JzaUtobW00dGlndzk0Qm9YdnN1MXRBS0Rsdm1MRWdUbHZAzN1lJZA3dNOXQ3S0RuUWdWbmY3Q0tHUmVOcwZDZD"; // Remplacez par votre access token
    private static final String GRAPH_API_URL = "https://graph.facebook.com/v17.0";

    // Récupérer l'ID du compte Instagram lié à une page Facebook
    public String getInstagramAccountId(String pageId) throws Exception {
        String url = GRAPH_API_URL + "/" + pageId + "?fields=connected_instagram_account&access_token=" + ACCESS_TOKEN;
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        JSONObject jsonResponse = new JSONObject(response.toString());
        if (jsonResponse.has("connected_instagram_account")) {
            JSONObject instagramAccount = jsonResponse.getJSONObject("connected_instagram_account");
            return instagramAccount.getString("id");
        } else {
            throw new Exception("Aucun compte Instagram n'est lié à cette page Facebook.");
        }
    }

    // Récupérer les mediaId d'un compte Instagram
    public List<String> getMediaIds(String instagramAccountId) throws Exception {
        String url = GRAPH_API_URL + "/" + instagramAccountId + "/media?access_token=" + ACCESS_TOKEN;
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        JSONObject jsonResponse = new JSONObject(response.toString());
        JSONArray data = jsonResponse.getJSONArray("data");

        List<String> mediaIds = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            JSONObject media = data.getJSONObject(i);
            mediaIds.add(media.getString("id"));
        }

        return mediaIds;
    }

    // Récupérer les statistiques d'un media spécifique
    public MediaStatistics getMediaStatistics(String mediaId) throws Exception {
        String url = GRAPH_API_URL + "/" + mediaId + "/insights?metric=engagement,impressions,reach,saved&access_token=" + ACCESS_TOKEN;
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        JSONObject jsonResponse = new JSONObject(response.toString());
        JSONArray data = jsonResponse.getJSONArray("data");

        MediaStatistics stats = new MediaStatistics();
        stats.setMediaId(mediaId);

        for (int i = 0; i < data.length(); i++) {
            JSONObject metric = data.getJSONObject(i);
            String name = metric.getString("name");
            int value = metric.getJSONArray("values").getJSONObject(0).getInt("value");

            switch (name) {
                case "engagement":
                    stats.setEngagement(value);
                    break;
                case "impressions":
                    stats.setImpressions(value);
                    break;
                case "reach":
                    stats.setReach(value);
                    break;
                case "saved":
                    stats.setSaved(value);
                    break;
            }
        }

        return stats;
    }
}
