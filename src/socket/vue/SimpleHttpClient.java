package clientserveur3.vue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * PROTOCOLE HTTP RESPECTE
 */
public class SimpleHttpClient {
    public static void main(String[] args) {
        try {
            // Spécifiez l'URL à laquelle envoyer la requête GET
            String url = "http://google.fr";
            //https://jsonplaceholder.typicode.com/posts/1
            //Introduction à REST
            
            // Créez l'objet URL
            URL apiUrl = new URL(url);

            // Ouvrez la connexion HTTP
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

            // Définissez la méthode de requête (GET dans ce cas)
            connection.setRequestMethod("GET");

            // Obtenez la réponse du serveur
            int responseCode = connection.getResponseCode();

            // Vérifiez si la requête a réussi (code 200)
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Lisez la réponse du serveur
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                // Affichez la réponse
                System.out.println("Server Response:\n" + response.toString());
            } else {
                System.out.println("HTTP Request Failed. Response Code: " + responseCode);
            }

            // Fermez la connexion
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
