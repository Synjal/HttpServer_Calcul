package clientserveur3;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;

import clientserveur3.model.Home;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

public class Main {

	public static void main(String[] args) throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		server.createContext("/accueil", new Home());
		server.createContext("/calcul", new CalculHandler());
		server.createContext("/img", new ImgHandler());

		server.start();
		/*
		 * 127.0.0.1:8000/accueil
		 */
	}
}

class CalculHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		System.out.println("Calculating");

		String query = exchange.getRequestURI().getQuery();
		String[] subQuery = query.split("=");
		String[] nbr1 = subQuery[1].split("&");
		String nbr2 = subQuery[2];

		int res = Integer.parseInt(nbr1[0]) + Integer.parseInt(nbr2);
		String response = "<p>" + nbr1[0] + " + " + nbr2 + " = " + res + "</p>";

		exchange.sendResponseHeaders(200, 0);
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}
}

class ImgHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		System.out.println("Image");

		File file = new File("C:\\Users\\stagiaire\\Projects\\java\\Test\\socket\\src\\clientserveur3\\assets\\banner.png");
		BufferedImage image = ImageIO.read(file);

		exchange.sendResponseHeaders(200, 0);
		OutputStream os = exchange.getResponseBody();
		ImageIO.write(image, "png", os);
		os.close();
	}
}