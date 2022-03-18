package com.example.safing.mypage.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.safing.R;

import java.util.ArrayList;

public class QNAActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qnaactivity);

        // [스피너 사용 전역 변수 선언 부분]
        Spinner spinner; // 컴포넌트
        ArrayList<String> arrayList; // 스피너 메뉴 목록을 담을 배열
        ArrayAdapter<String> arrayAdapter; // 스피너 메뉴 목록 배열을 연결할 어댑터


// [스피너 메뉴 목록 배열 객체 생성 및 메뉴 데이터 삽입]
        arrayList  = new ArrayList<>(); // 배열 생성
        arrayList.add("일반문의"); // 메뉴 데이터 삽입
        arrayList.add("결제"); // 메뉴 데이터 삽입
        arrayList.add("로그인"); // 메뉴 데이터 삽입
        arrayList.add("회원가입"); // 메뉴 데이터 삽입
        arrayList.add("이용제한/보호조치"); // 메뉴 데이터 삽입


// [스피너 메뉴 목록 배열 어댑터와 매핑]
        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayList);


// [스피너 컴포넌트 XML과 매핑]
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(arrayAdapter); // 어댑터 적용
        spinner.setSelection(0); // 초기 스피너 메뉴 항목 지정


// [스피너 메뉴 목록 선택 및 변경 이벤트 정의]
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO 하위 버전 텍스트 색상 지원하기 위해 선언
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView) adapterView.getChildAt(0)).setTextSize(13);

                // 선택한 메뉴 목록 확인 실시
                Toast.makeText(getApplicationContext(), arrayList.get(i) + " 선택", Toast.LENGTH_SHORT).show();
                String spinnerMenu = String.valueOf(arrayList.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
}