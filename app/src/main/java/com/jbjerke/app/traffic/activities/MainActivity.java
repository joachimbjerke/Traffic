package com.jbjerke.app.traffic.activities;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.jbjerke.app.traffic.R;
import com.jbjerke.app.traffic.adapters.CustomAdapter;
import com.jbjerke.app.traffic.models.ItemModel;
import com.jbjerke.app.traffic.singeltons.TrafficInfo;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends Activity {

    private ItemModel[] listData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String absooluteUrl = "http://api.dit.no";
        String searchstring = "/Norge/Akershus/Asker/Deviations";
        String apiKey = "?ApiKey=LLBBX6WZ";

        new AsyncTaskRunner().execute(absooluteUrl+searchstring+apiKey);

        //TrafficInfo.getInstance().getTrafficInfo();
        //this.generateDummyData();
        ListView listView = (ListView) this.findViewById(R.id.postListView);
        CustomAdapter customAdapter = new CustomAdapter(this,
                R.layout.listitem, listData);
        listView.setAdapter(customAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    private void generateDummyData() {
        ItemModel data = null;
        listData = new ItemModel[4];

        for (int i = 0; i<listData.length; i++){
            data = new ItemModel();
            data.setHeader("Slemmestad - Sundbykrysset, i Royken kommune");
            data.setDescription("Lysregulering pa grunn av vedlikeholdsarbeid i periodene: Mandag til torsdag fra 07:00 til 19:00.");
            data.setIcon(null);

            listData[i] = data;
        }
    }

    /****** Function to set data in ArrayList *************/


    private class AsyncTaskRunner extends AsyncTask<String, Void, ItemModel[]>{

        private ItemModel[] response;

        @Override
        protected ItemModel[] doInBackground(String... strings) {
            response = TrafficInfo.getInstance().getTrafficInfo(strings[0]);

            return  response;
        }

        @Override
        protected void onPostExecute(ItemModel[] result){
            listData = result;
        }
    }

}
