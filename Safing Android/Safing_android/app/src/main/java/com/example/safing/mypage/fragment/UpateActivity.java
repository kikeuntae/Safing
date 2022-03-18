package com.example.safing.mypage.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.safing.MainActivity;
import com.example.safing.R;
import com.example.safing.async.AskParam;
import com.example.safing.async.CommonAsk;
import com.example.safing.async.CommonMethod;
import com.example.safing.async.CommonVal;
import com.example.safing.mypage.VO.MemberVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UpateActivity extends AppCompatActivity {

    EditText edittext;
    ImageView imageView;
    EditText create_edt_id,edit_up_pw,edit_up_pw2,create_edt_name,create_edt_birth,phone,phone2, input_check_num;
    Button checkbt, id_btn, check_button, update_btn;
    Gson gson = new GsonBuilder().serializeNulls().create();
    MemberVO dto = new MemberVO();
    MemberVO login_vo = new MemberVO();
    ArrayList<MemberVO> list = new ArrayList<>();
    String chk_number;
    boolean check= false;//true 참/ false 거짓
    CommonAsk service;
    Spinner spinner; // 컴포넌트

    static final int SMS_SEND_PERMISSON = 1;
    RadioButton rdo_man , rdo_woman ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_update);

        login_vo=MainActivity.getLogin_member();

        spinner = (Spinner) findViewById(R.id.update_spinner);

        create_edt_id =findViewById(R.id.update_edt_id);
        create_edt_id.setText(login_vo.getMember_id());

        edit_up_pw = findViewById(R.id.update_pw);
        edit_up_pw.setText(login_vo.getMember_pw());

        edit_up_pw2 = findViewById(R.id.update_pw2);
        edit_up_pw2.setText(login_vo.getMember_pw());

        create_edt_name = findViewById(R.id.update_edt_name);
        create_edt_name.setText(login_vo.getMember_name());

        create_edt_birth = findViewById(R.id.update_edt_birth);
        create_edt_birth.setText(login_vo.getMember_age()+"");

        String readStr2 = login_vo.getMember_phone();

        String test1 = readStr2.split("-")[0]; // 010
        String test2 = readStr2.split("-")[1]; //
        String test3 = readStr2.split("-")[2]; //


        phone = findViewById(R.id.update_phone1);
        phone.setText(test2);

        phone2 = findViewById(R.id.update_phone2);
        phone2.setText(test3);

        update_btn = findViewById(R.id.update_btn_mypage);


        rdo_man = findViewById(R.id.update_rdo_man);
        rdo_woman = findViewById(R.id.update_rdo_woman);

        if(login_vo.getMember_gender().equals("남")){
            rdo_man.setChecked(true);
        }else {
            rdo_woman.setChecked(true);
        }


        rdo_man.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    rdo_woman.setChecked(false);
                }
            }
        });

        rdo_woman.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    rdo_man.setChecked(false);
                }
            }
        });


        // [스피너 사용 전역 변수 선언 부분]

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
        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayList);


// [스피너 컴포넌트 XML과 매핑]

        spinner.setAdapter(arrayAdapter); // 어댑터 적용
        spinner.setSelection(0); // 초기 스피너 메뉴 항목 지정
        if(test1.equals("010")){
            spinner.setSelection(0);
        }else if(test1.equals("062")){
            spinner.setSelection(1);
        }else if(test1.equals("063")){
            spinner.setSelection(2);
        }else if(test1.equals("02")){
            spinner.setSelection(3);
        }else if(test1.equals("011")){
            spinner.setSelection(4);
        }



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


                //create_edt_id,edit_up_pw,edit_up_pw2,create_edt_name,create_edt_birth;


        update_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {  //아이디 정규식
            if(create_edt_id.getText().toString().length() == 0){
                Toast.makeText(UpateActivity.this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
                create_edt_id.requestFocus();
                return;
            }

           if(edit_up_pw.getText().toString().length() == 0){
               Toast.makeText(UpateActivity.this, "패스워드를 입력해주세요", Toast.LENGTH_SHORT).show();
               edit_up_pw.requestFocus();
               return;
           }

           if(create_edt_name.getText().toString().length() == 0){ //이름 정규식
               Toast.makeText(UpateActivity.this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
               create_edt_name.requestFocus();
               return;
           }

           /*if(!Pattern.matches("^[가-힣]+$",create_edt_name.getText().toString())){
                Toast.makeText(CreateActivity.this, "한글로 입력해주세요.", Toast.LENGTH_SHORT).show();
                create_edt_name.requestFocus();
                return;
            }*/


            dto.setMember_id(create_edt_id.getText().toString());
            //  dto.setMember_age(Integer.parseInt(create_edt_birth.getText().toString()));
            dto.setMember_pw(edit_up_pw.getText().toString());
            dto.setMember_phone(spinner.getSelectedItem()+"-"+phone.getText().toString()+"-"+phone2.getText().toString());
            dto.setMember_age(Integer.parseInt(create_edt_birth.getText()+""));
            dto.setMember_name(create_edt_name.getText().toString());

            if(rdo_man.isChecked()){
                dto.setMember_gender("남");
            }else if(rdo_woman.isChecked()){
                dto.setMember_gender("여");
            }

            service = new CommonAsk("update.me");
            String str = gson.toJson(dto);
            service.params.add(new AskParam("vo",str));
            //async.AskParam
            CommonMethod.excuteAsk(service);


            Intent intent = new Intent(UpateActivity.this,MainActivity.class);
            startActivity(intent);
            Toast.makeText(UpateActivity.this, "수정이 완료되었습니다", Toast.LENGTH_SHORT).show();
        }
    });

    edit_up_pw2.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String password = edit_up_pw.getText().toString();
        String confirm = edit_up_pw2.getText().toString();

        if(password.equals(confirm)){
         edit_up_pw.setBackgroundColor(Color.GREEN);
         edit_up_pw2.setBackgroundColor(Color.GREEN);
        }
        else {
            edit_up_pw.setBackgroundColor(Color.RED);
            edit_up_pw2.setBackgroundColor(Color.RED);
           }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    });

    }
}