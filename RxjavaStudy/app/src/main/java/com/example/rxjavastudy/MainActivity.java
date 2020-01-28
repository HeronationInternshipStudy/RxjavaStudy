package com.example.rxjavastudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity {

    /* 반응형 프로그래밍은 크게 3부분으로 구성
    * input 부분: 이벤트가 시작되는 부분
    * operators 부분: 이벤트를 가공하고 조합하여 결과를 만드는 부분
    * output 부분: 가공한 결과를 출력하는 부분*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText guguEdit=(EditText)findViewById(R.id.guguEdit);
        EditText guguResult=(EditText)findViewById(R.id.guguResult);
        Button btnPrint=(Button)findViewById(R.id.btnPrint);

        btnPrint.setOnClickListener(view -> {
            guguResult.setText("");
            Observable.just(guguEdit.getText().toString())
                    .map(dan->Integer.parseInt(dan))
                    .filter(dan-> 1<dan && dan<10 )
                    .flatMap(dan->Observable.range(1,9),(dan,row)->dan+" * "+row+" = "+ (dan*row))
                    /*flatMap()은 값을 또 다른 Observable로 변환해주는 Operator. 연속적으로 매핑
                    * 1, 2, 3, 4, 5, 6, 7, 8, 9 값을 먼저 뻥튀기 한 후에,
                    * dan과 row를 연속적으로 매핑해줌 */
                    .map(row->row+"\n")
                    .subscribe(guguResult::append,e-> Toast.makeText(getApplicationContext(),"구구단은 2 에서 9 사이의 숫자를 입력해주셔야 합니다.",Toast.LENGTH_SHORT).show());
        });
    }


}
