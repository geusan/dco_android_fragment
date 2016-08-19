package dcosns.com.fragmentsample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity/*프래그먼트의 부모는 FragmentActivity로 만들어야한다.*/ {


    Button[] btns = new Button[3];

    /**
     * 한번에 버튼기능을 관리하기 위해
     *
     * 알아보기 쉽도록 메소드로 처리한다.
     */
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

    /**
     * 식별자에 따라서 다른 Fragment를 호출해오는 메소드
     * @param position 식별자, 코드간소화를 하기 위해 버튼 배열과 같은 값을 사용
     * @return postition에 따라서 다른 프래그먼트가 반환된다.
     */
    private Fragment getFragmnet(int position){
        Fragment f= null;
        switch (position){
            case 0: f = new child1(); break;
            case 1: f = new child2(); break;
            case 2: f = new child3(); break;
        }
        return f;
    }

    /**
     * 누른 버튼에 따라서 다른 화면이 나오도록 한다.
     *
     * @param position 누른 버튼에 대한 식별자
     */
    private void changeView(int position){
        btns[position].setBackgroundResource(R.drawable.btn_clicked);
        Fragment f = getFragmnet(position);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, f).commit();
    }

    /**
     * 버튼눌렀을 때마다 호출되는 초기화 메소드
     */
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
