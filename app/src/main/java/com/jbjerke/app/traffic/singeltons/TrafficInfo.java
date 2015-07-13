package com.jbjerke.app.traffic.singeltons;

import com.jbjerke.app.traffic.models.ItemModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jbjerke on 06.07.2015.
 */
public class TrafficInfo {

    private static TrafficInfo objTrafficInfo;


    public static  TrafficInfo getInstance()
    {
        if(objTrafficInfo == null)
            objTrafficInfo = new TrafficInfo();

        return objTrafficInfo;

    }



    public ItemModel[] getTrafficInfo(String urlString)
    {


        try {


            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if(conn.getResponseCode() != 200)
                throw new RuntimeException("Failed: HTTP error code: "+conn.getResponseCode());

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String outputLine;
            StringBuffer response = new StringBuffer();

            while((outputLine = br.readLine()) != null)
                response.append(outputLine);

            br.close();




            JSONObject jsonObject = new JSONObject(response.toString());

            JSONArray jsonArray =jsonObject.getJSONArray("deviations");
            ItemModel[] models = new ItemModel[jsonArray.length()+1];
            for (int i = 0; i < jsonArray.length(); i++) {
                ItemModel model = new ItemModel();
                JSONObject deviationObj = jsonArray.getJSONObject(i);
                model.setSeverity(deviationObj.getString("severity"));
                JSONArray descArray = deviationObj.getJSONArray("descriptions");
                for (int j = 0; j <descArray.length(); j++) {
                    String descType = descArray.getJSONObject(j).getString("name");
                    String descText = descArray.getJSONObject(j).getString("value");
                    if(descType.equals("Heading")){
                        model.setHeader(descText);
                    }
                    else if(descType.equals("Introduction")){
                        model.setDescription(descText);
                    }


                }
                models[i] = model;
            }

            //model.setHeader(jsonObject);


            return models;




        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }


    }


}
