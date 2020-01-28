package com.example.rxjavastudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /* 반응형 프로그래밍은 크게 3부분으로 구성
    * input 부분: 이벤트가 시작되는 부분
    * operators 부분: 이벤트를 가공하고 조합하여 결과를 만드는 부분
    * output 부분: 가공한 결과를 출력하는 부분*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv=(TextView)findViewById(R.id.hello_text);
        io.reactivex.Observable.just(tv.getText().toString()) //input
                .map(s->s+" Rx!") //operator
                .subscribe(text->tv.setText(text)); //output

    }
}
