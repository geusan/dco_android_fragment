package dcosns.com.fragmentsample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends FragmentActivity {

    LinearLayout frame;
    Button[] btns = new Button[3];
    View.OnClickListener bHandler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            initBg();
            switch (view.getId()){
                case R.id.btn1:
                    changeView(0);
                    break;
                case R.id.btn2:
                    changeView(1);
                    break;
                case R.id.btn3:
                    changeView(2);
                    break;
            }
        }
    };

    private Fragment getFragmnet(int position){
        Fragment f= null;
        switch (position){
            case 0: f = new child1(); break;
            case 1: f = new child2(); break;
            case 2: f = new child3(); break;
        }
        return f;
    }

    private void changeView(int position){
        btns[position].setBackgroundResource(R.drawable.btn_clicked);
        Fragment f = getFragmnet(position);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, f).commit();
    }
    private void initBg(){
        for(Button b : btns) b.setBackgroundResource(R.drawable.btn_unclicked);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btns[0] = (Button) findViewById(R.id.btn1);
        btns[1] = (Button) findViewById(R.id.btn2);
        btns[2] = (Button) findViewById(R.id.btn3);
        for(Button b : btns) b.setOnClickListener(bHandler);

    }
}
