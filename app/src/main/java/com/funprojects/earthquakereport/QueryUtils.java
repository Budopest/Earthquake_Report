package com.funprojects.earthquakereport;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public final class QueryUtils {

    /** Sample JSON response for a USGS query */
       private static final String urlString = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }
    public static String getUrl(){
        Log.v(QueryUtils.class.getSimpleName(), "url founf");
        return  urlString;
    }

    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Earthquake> extractEarthquakes(String url) {

        URL newUrl = createURL(url);

        /*
         * Make http conection and get the json response               
         */
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpConection(newUrl);
        } catch (IOException e) {
            Log.e(QueryUtils.class.getSimpleName(), "Error closing the stream");
        }
        Log.v("json response",jsonResponse);
        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Earthquake> earthquakes = new ArrayList<Earthquake>();
        // parse the json query
        try {
            JSONArray earthquakeArray = new JSONObject(jsonResponse).getJSONArray("features");
            for(int i=0;i<earthquakeArray.length();i++){
                JSONObject currentEarthquake = earthquakeArray.getJSONObject(i).getJSONObject("properties");
            earthquakes.add(new Earthquake(currentEarthquake.getDouble("mag"),currentEarthquake.getLong("time"),
                    currentEarthquake.getString("place"),currentEarthquake.getString("url")));
            }


        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

    private static String makeHttpConection(URL url) throws IOException{

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        String jsonResponse = "";
        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(QueryUtils.class.getSimpleName(), "Error response code: " + httpURLConnection.getResponseCode());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(httpURLConnection != null) httpURLConnection.disconnect();
            if(inputStream != null) inputStream.close();
        }
        return jsonResponse;

    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        if(inputStream != null){
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = bufferedReader.readLine();
        while (line != null){
            stringBuilder.append(line);
            line = bufferedReader.readLine();
        }
        }
        return  stringBuilder.toString();
    }

    private static URL createURL(String url) {
        URL newUrl = null;
        try {
            newUrl = new URL(url);
        }
        catch (MalformedURLException e) {
            Log.e(QueryUtils.class.getSimpleName(), "Error with creating URL ", e);
        }
        return newUrl;
    }

}
