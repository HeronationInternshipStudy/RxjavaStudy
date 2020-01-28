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
            int dan= Integer.parseInt(guguEdit.getText().toString());
            guguResult.setText("");
            Observable.range(1,9)
                    .map(row-> {
                        if (dan < 2 || dan > 9) throw new NumberFormatException("");
                        if (row==1) guguResult.setText("");
                        return dan + " * " + row + " = " + (dan * row);
                    })
                    .map(row->row+"\n")
                    .subscribe(guguResult::append,e-> Toast.makeText(getApplicationContext(),"구구단은 2 에서 9 사이의 숫자를 입력해주셔야 합니다.",Toast.LENGTH_SHORT).show());
        });
    }


}
