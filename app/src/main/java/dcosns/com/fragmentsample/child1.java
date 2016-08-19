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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dnay2 on 2016-08-17.
 */
public class child1 extends Fragment/* Fragment를 상속받는다.*/ {

    Button btn;
    EditText text;
    RequestQueue volley; //요청을 담는 큐
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

        /**
         * Http 요청에 대한 응답을 String형태로 받아서 사용하는 방법
         */
        StringRequest request1 = new StringRequest(url/*요청 url*/,
                new Response.Listener<String>()/*응답받을 때 사용하는 리스너(인터페이스)*/ {
            @Override
            public void onResponse(String response) {
                text.setText(response);
            }
        }, new Response.ErrorListener()/*에러가 발생했을 때 사용하는 리스너(인터페이스)*/ {
            @Override
            public void onErrorResponse(VolleyError error) {
                //에러가 발생했을 때 안내창을 띄우는 형식의 반응을 보여주면 된다.
            }
        });

        /**
         *  사용하는 방법은 위의 StringRequest와 동일하다 대신에 받아오는 응답을
         *
         *  JSON으로 받아오기때문에 파싱이 더 쉬울 수 있다.
         */
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

        /**
         * 마지막에 RequestQueue 에 요청을 추가해주어야한다.
         *
         * RequestQueue에 담으면 담긴 순서대로 요청을 수행한다.
         */
        volley.add(request);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
