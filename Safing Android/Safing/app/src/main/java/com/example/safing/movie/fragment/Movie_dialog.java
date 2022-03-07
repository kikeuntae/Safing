package com.example.safing.movie.fragment;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.safing.MainActivity;
import com.example.safing.R;
import com.example.safing.movie.DAO.Comment_DAO;
import com.example.safing.movie.DAO.Movie_DAO;
import com.example.safing.movie.DTO.Movie_comment_DTO;
import com.example.safing.movie.adapter.Comment_Adapter;
import com.example.safing.mypage.VO.MemberVO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Movie_dialog extends AppCompatActivity {
    private Context context;
    private int position;
    ListView comment_listview;
    int video_id;
    Comment_Adapter adapter;
    Comment_DAO dao= new Comment_DAO();
    EditText editText;
    Movie_DAO dao_movie= new Movie_DAO();
    MemberVO login_vo= new MemberVO();

    public Movie_dialog(Context context, int position, int video_id) {
        this.context = context;
        this.position = position;
        this.video_id = video_id;
    }

    // 호출할 다이얼로그 함수를 정의한다.
    public void callFunction() {

        login_vo = MainActivity.getLogin_member();


        // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.
        final Dialog dlg = new Dialog(context);


        // 액티비티의 타이틀바를 숨긴다.
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView(R.layout.movie_comment_dialog);
        comment_listview = dlg.findViewById(R.id.comment_listview);
        editText = dlg.findViewById(R.id.comment_content);

        Movie_comment_DTO dto = new Movie_comment_DTO();
        ArrayList<Movie_comment_DTO> list  = new ArrayList<>();
        list = dao.list(video_id);
        comment_listview.setAdapter(adapter);


        String memberimg_url = dao.memberImg(login_vo.getMember_id());



        adapter = new Comment_Adapter(context,list);

        // 커스텀 다이얼로그를 노출한다.
        dlg.getWindow().setGravity(Gravity.BOTTOM);
        dlg.show();

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
     //   ImageButton comment_delete = (ImageButton) dlg.findViewById(R.id.comment_delete);
    //    ImageButton comment_update =  (ImageButton) dlg.findViewById(R.id.comment_update);
        ImageButton comment_insrt =  (ImageButton) dlg.findViewById(R.id.comment_insert);
        ImageButton cancelButton = (ImageButton) dlg.findViewById(R.id.movie_comment_exit);
        ImageView memberimg = dlg.findViewById(R.id.comment_img);

        Glide.with(context)
                .load(Uri.parse(memberimg_url))
                .into(memberimg);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 커스텀 다이얼로그를 종료한다.
                dlg.dismiss();
            }
        });

        comment_insrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//입력창
                dto.setMember_id(login_vo.getMember_id());
                dto.setComment_content(editText.getText()+"");
                dto.setBoard_id(video_id);
                dao.create(dto);
                Toast.makeText(context, "입력되었습니다", Toast.LENGTH_SHORT).show();
                ArrayList<Movie_comment_DTO> list  = new ArrayList<>();
                list = dao.list(video_id);
                adapter = new Comment_Adapter(context,list);
                comment_listview.setAdapter(adapter);
                editText.setText("");


                //dao.list(dto.getBoard_id());
            }
        });



    }
}
