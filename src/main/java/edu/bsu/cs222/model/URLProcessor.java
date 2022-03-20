package edu.bsu.cs222.model;

import edu.bsu.cs222.view.ErrorWindow;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class URLProcessor {

    public URLProcessor() {}

    public URL getPokemonURL(String pokemon) {
        String urlString = getURLString("https://pokeapi.co/api/v2/pokemon/%s", pokemon);
        return verifyURL(urlString);
    }

    public URL getGameURL(String game) {
        String urlString = getURLString("https://pokeapi.co/api/v2/version-group/%s", game);
        return verifyURL(urlString);
    }

    // There's a couple pokemon later on that will have spaces in their name, so it's important to use URLEncoder
    private String getURLString(String url, String name) {
        String nameEncoded = URLEncoder.encode(name, Charset.defaultCharset());
        return String.format(url, nameEncoded);
    }

    public URL verifyURL(String urlString) {
        try {
            return new URL(urlString);
        }
        catch(MalformedURLException malformedURLException) {
            throw new IllegalStateException(malformedURLException);
        }
    }

    public InputStream getInputStream(URL url) {
        try {
            URLConnection urlConnection = openURLConnection(url);
            return urlConnection.getInputStream();
        }
        catch(IOException ioException) {
            throw new IllegalStateException(ioException);
        }
    }

    private URLConnection openURLConnection(URL url) {
        try {
            return url.openConnection();
        }
        catch(IOException networkError) {
            ErrorWindow networkErrorWindow = new ErrorWindow("A network error has occurred");
            networkErrorWindow.display();
            System.exit(1);
        }

        return null;
    }
}
