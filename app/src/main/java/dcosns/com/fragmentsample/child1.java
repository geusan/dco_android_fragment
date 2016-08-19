package dcosns.com.fragmentsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dnay2 on 2016-08-17.
 */
public class child1 extends Fragment {

    Button btn;
    EditText text;
    RequestQueue volley;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.child1, null);
        btn = (Button) v.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAction();
            }
        });
        volley = Volley.newRequestQueue(getActivity());

        text = (EditText) v.findViewById(R.id.text);
        return v;
    }

    private void doAction(){
        String url = "http://apis.skplanetx.com/melon/charts/realtime?count=10&page=1&version=1&appKey=31c1e579-b9c2-3697-95d3-ed1ba8a82815&format=json";

//        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                text.setText(response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });

        JsonObjectRequest request = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String jsonString = "";
                try{
                    jsonString = response
                            .getJSONObject("melon")
                            .getJSONObject("songs")
                            .getJSONArray("song")
                            .getJSONObject(0)
                            .getString("songName");
                }catch (JSONException e){
                    e.printStackTrace();
                }

                text.setText(jsonString);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        volley.add(request);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
