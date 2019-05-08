package main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.assem.ibrahem.adtask.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import model.adInfo;
import presenter.adPresenter;


/**
 *
 * Display The Main Screen
 */
public class MainActivity extends AppCompatActivity implements MainContract.MvpView{

    /**
     *
     * create instance from the class adInfo to hold all the ads in the arraylist myAds
     *
     *
     */
    private RecyclerView rv;
    private RecyclerView.Adapter adabter;
    ArrayList<adInfo> myAds = new ArrayList<>();
    adInfo OneAd ;
    final String API_URL = "https://simswitch.bit68.com/get_ad/?solo=false";

    private MainPresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_ads);
        mPresenter = new MainPresenter(this);


        //initialize my Views
        setViews();


        RequestQueue queue = Volley.newRequestQueue(this);
//      Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        setData(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("solo", "false");
                ;
                return params;
            }
        };

// Add the request to the RequestQueue.y
        queue.add(stringRequest);
    }




    void setViews()
    {
        rv = findViewById(R.id.RecycleViewIdAds);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }


    /**
     *
     * here to unlock all the data that coming from the string response
     * @param response
     */
    void setData(String response)
    { try {
        JSONArray jsonObj = new JSONArray(response);
        for (int i = 0; i < jsonObj.length(); i++) {
            OneAd = new adInfo(
                    jsonObj.getJSONObject(i).getString("title"),
                    jsonObj.getJSONObject(i).getString("url"),
                    jsonObj.getJSONObject(i).getString("picture"),
                    jsonObj.getJSONObject(i).getInt("order")
            );

            myAds.add(OneAd);

        }

        ///here to sort all the ads ascending on the attribute ORDER
        Collections.sort(myAds);


        //notify my Adapter to send the data
        adabter = new adPresenter(this, myAds);
        rv.setAdapter(adabter);


    } catch (JSONException e) {
        e.printStackTrace();

    }

    }

}
