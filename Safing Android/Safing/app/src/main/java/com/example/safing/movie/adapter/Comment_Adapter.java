package com.example.safing.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.safing.R;
import com.example.safing.VO.Board_FileVO;
import com.example.safing.home.DTO.SafeZoneRecDTO;
import com.example.safing.home.activity.CamInfoActivity;
import com.example.safing.movie.DAO.Comment_DAO;
import com.example.safing.movie.DTO.Movie_comment_DTO;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class Comment_Adapter extends BaseAdapter {

    Context context;
    ArrayList<Movie_comment_DTO> list;
    LayoutInflater inflater;
    Comment_DAO dao= new Comment_DAO();
    Movie_comment_DTO dto = new Movie_comment_DTO();

    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public Comment_Adapter(Context context, ArrayList<Movie_comment_DTO> list) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    public void addDto(Movie_comment_DTO dto){
        list.add(dto);
    }


    public void removeDto(){
        list.clear();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    // 가장 중요함 : 화면을 생성하고 특정뷰에 대한 클릭이벤트를 만들수 있다
    // 만약 화면 5개를 생성한다면 getView가 5번 실행된다
    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder viewHolder;

        // 캐시된 뷰가 없을 경우 새로 뷰홀더를 생성하고
        // 있으면 그 뷰를 재활용한다
        if(itemView == null){
            // 화면을 붙인다
            itemView = inflater.inflate(R.layout.item_movie_comment,
                    parent, false);

            viewHolder = new ViewHolder();
            // 붙인 화면과 아래에 생성한 뷰홀더를 연결한다

            viewHolder.tv_content = itemView.findViewById(R.id.comment_contents);
            viewHolder.tv_date = itemView.findViewById(R.id.comment_date);
            viewHolder.tv_name = itemView.findViewById(R.id.comment_name);
            viewHolder.cv_userimg = itemView.findViewById(R.id.comment_img);
            viewHolder.ib_delete = itemView.findViewById(R.id.comment_delete);
            viewHolder.ib_update = itemView.findViewById(R.id.comment_update);


            itemView.setTag(viewHolder);
        }else { // 캐시된 뷰가 있을 경우 이미 생성된 뷰홀더를 사용한다
            viewHolder = (ViewHolder) itemView.getTag();
        }

        // 선택한 dto 데이터 가져오기
        Movie_comment_DTO dto = list.get(position);
        String name = dto.getMember_id();
        String date = dto.getComment_regdate();
        String content = dto.getComment_content();
        String usrt_ing = dao.memberImg(name);

        // 화면에 데이터 연결하기
        //viewHolder.cv_userimg.setImageURI(Uri.parse(usrt_ing));
        Glide.with(itemView)
                .load(Uri.parse(usrt_ing))
                .into(viewHolder.cv_userimg);
        viewHolder.tv_name.setText(name);

        viewHolder.tv_date.setText(date);
        viewHolder.tv_content.setText(content);
        
        viewHolder.ib_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dto.setMember_id("master");
                dao.delete(dto);
            }
        });

        viewHolder.ib_update.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dto.setComment_content( viewHolder.tv_content.getText()+"");
                dto.setMember_id("master");
                dao.update(dto);
                viewHolder.tv_content.setEnabled(false);
                Toast.makeText(context, "수정되었습니다", Toast.LENGTH_SHORT).show();
                return false; }
        }
        );

        viewHolder.ib_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    viewHolder.tv_content.setEnabled(true);
                    Toast.makeText(context, "글자를 수정하세요", Toast.LENGTH_SHORT).show();


            }
        });


        // 만들어진 뷰를 반환
        return itemView;
    }
    public class ViewHolder {
       EditText tv_content;

        TextView tv_date, tv_name;
        CircleImageView cv_userimg;
        ImageButton ib_delete, ib_update;
    }



    }

