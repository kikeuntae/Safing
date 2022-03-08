package com.example.safing.mypage.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.safing.R;
import com.example.safing.async.AskParam;
import com.example.safing.async.CommonAsk;
import com.example.safing.async.CommonMethod;
import com.example.safing.mypage.VO.MemberVO;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class FindFragment extends Fragment {
    EditText phone,phone2,find_phone_edit2;
    TextView find_phone_id1, find_phone_id2;
    Button frag_find_id,find_phone_edit_ok, send_sms_button;
    Gson gson = new Gson();
    MemberVO dto = new MemberVO();
    Context context;
    String chk_number;
    boolean check= false;
    public FindFragment(Context context) {
        this.context = context;
    }

    static final int SMS_SEND_PERMISSON = 1;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout. fragment_find, container, false);
        find_phone_edit2 = rootView.findViewById(R.id.find_phone_edit2);
        frag_find_id = rootView.findViewById(R.id.frag_find_id);
        phone = rootView.findViewById(R.id.phone);
        phone2 = rootView.findViewById(R.id.phone2);
        find_phone_edit_ok = rootView.findViewById(R.id.find_phone_edit_ok);
        find_phone_id1 = rootView.findViewById(R.id.find_phone_id1);
        find_phone_id2 = rootView.findViewById(R.id.find_phone_id2);
        send_sms_button =  rootView.findViewById(R.id.send_sms_button);




        // [스피너 사용 전역 변수 선언 부분]
        Spinner spinner; // 컴포넌트
        ArrayList<String> arrayList; // 스피너 메뉴 목록을 담을 배열
        ArrayAdapter<String> arrayAdapter; // 스피너 메뉴 목록 배열을 연결할 어댑터


// [스피너 메뉴 목록 배열 객체 생성 및 메뉴 데이터 삽입]
        arrayList  = new ArrayList<>(); // 배열 생성
        arrayList.add("010"); // 메뉴 데이터 삽입
        arrayList.add("062"); // 메뉴 데이터 삽입
        arrayList.add("063"); // 메뉴 데이터 삽입
        arrayList.add("02"); // 메뉴 데이터 삽입
        arrayList.add("011"); // 메뉴 데이터 삽입


// [스피너 메뉴 목록 배열 어댑터와 매핑]
        arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, arrayList);


// [스피너 컴포넌트 XML과 매핑]
        spinner = rootView.findViewById(R.id.spinner);
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

                String spinnerMenu = String.valueOf(arrayList.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });

        send_sms_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonAsk ask = new CommonAsk("sendsms.me");
                InputStream in = CommonMethod.excuteAsk(ask);
                chk_number =CommonMethod.rtnString(in);
                Toast.makeText(context, "인증번호가 발송되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        //인증번호 확인
        find_phone_edit_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((find_phone_edit2.getText() + "").equals(chk_number)){
                    check = true;
                    Toast.makeText(context, "인증되었습니다.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "인증번호가 틀립니다.", Toast.LENGTH_SHORT).show();
                    find_phone_edit2.requestFocus();
                    return;
                }
            }
        });



        frag_find_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!check){
                    Toast.makeText(context, "인증번호를 인증하세요.", Toast.LENGTH_SHORT).show();
                    find_phone_edit2.requestFocus();
                    return;
                } else{
                    CommonAsk ask = new CommonAsk("find_id.me");
                    String phone_num = (spinner.getSelectedItem()+"-"+phone.getText().toString()+"-"+phone2.getText().toString());
                    ask.params.add(new AskParam("phone", phone_num));
                    InputStream in = CommonMethod.excuteAsk(ask);
                    MemberVO vo = gson.fromJson(new InputStreamReader(in), MemberVO.class);
                    if(vo.getMember_id().equals("null")){
                            find_phone_id1.setVisibility(View.GONE);
                        find_phone_id2.setVisibility(View.VISIBLE);
                        find_phone_id2.setText("일치하는 정보가 없습니다.");
                    } else{
                        find_phone_id1.setVisibility(View.VISIBLE);
                        find_phone_id2.setVisibility(View.VISIBLE);
                        find_phone_id2.setText(vo.getMember_id());

                    }
                }



            }
        });

        return rootView;
    }

}


