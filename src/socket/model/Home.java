package clientserveur3.model;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

public class Home implements HttpHandler {

    private final String path = "C:\\Users\\stagiaire\\Projects\\java\\Test\\socket\\src\\clientserveur3\\component\\accueil.txt";
    String line = "";

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("Home page");

        OutputStream os = exchange.getResponseBody();
        exchange.sendResponseHeaders(200, 0);

        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            while((line = reader.readLine()) != null){
                os.write(line.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        os.close();
    }
}
