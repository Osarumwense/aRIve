package com.enghack.aRIve.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.entity.StrictContentLengthStrategy;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.json.JSONArray;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;

/**
 * Created by 37264 on 6/25/16.
 */
@Service
public class TtcService {

    public double[] executeGet(String url, String id) throws Exception {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {
            URIBuilder builder = new URIBuilder(url);
            URI uri = builder.build();

            HttpGet method = new HttpGet(uri);
            HttpPost postMethod = new HttpPost(uri);
            HttpDelete deleteMethod = new HttpDelete(uri);
            method.addHeader("Accept", "application/xml");
            method.addHeader("Content-Type", "application/xml");




            // Parse and check response
            HttpResponse response = httpClient.execute(method);
            ResponseHandler<String> handler = new BasicResponseHandler();
            String body = handler.handleResponse(response);
            body = body.replace("\n","");
            JSONObject xmlJSONObj = XML.toJSONObject(body);
//            JSONObject xmlJSONObj = XML.toJSONObject(body);

//            JSONObject jsonObject = (JSONObject) JSONValue.parseWithException(xmlJSONObj.toString());

            JSONArray buses = (JSONArray) xmlJSONObj.getJSONObject("body").get("vehicle");

            double[] latLongArray = returnLatLong(buses, id);



            return latLongArray;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            // Close the connection
            try {
                httpClient.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    private double[] returnLatLong(final JSONArray standings, String idNumber) {

        double[] x= new double[2];
        for (Object standing : standings) {
            JSONObject jsonStanding = (JSONObject) standing;
            if(Integer.parseInt(jsonStanding.get("id").toString())==Integer.parseInt(idNumber))
            {
                x[0] = Double.parseDouble(jsonStanding.get("lat").toString());
                x[1] = Double.parseDouble(jsonStanding.get("lon").toString());

                return x;

            }




//            standingDao.saveStanding(standingPopulator.populateStandingFromJson(jsonStanding));
        }
        return x;
    }
}
