package com.example.safing.mypage.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.safing.MainActivity;
import com.example.safing.R;
import com.example.safing.movie.DAO.Movie_DAO;
import com.example.safing.movie.DTO.Board_Movie_DTO;
import com.example.safing.movie.fragment.Movie_updateFragment;

import java.util.List;

public class Mypage_Adapter extends RecyclerView.Adapter<Mypage_Adapter.ViewHolder> {

    MainActivity mainActivity = new MainActivity();
    Context context;
    List<Board_Movie_DTO> list;
    LayoutInflater inflater;
    Movie_DAO dao = new Movie_DAO();



    public Mypage_Adapter(Context context, List<Board_Movie_DTO> list) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mainActivity = (MainActivity) context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.item_mypage, parent , false );
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        bind(holder,position);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mypage_list_img ;
        ImageButton mypage_list_delete, mypage_list_update;
        TextView  mypage_list_title,  mypage_list_content, mypage_list_like_cnt, mypage_list_coment_cnt, mypage_list_writedate;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mypage_list_img= itemView.findViewById(R.id.mypage_list_img);
            mypage_list_title= itemView.findViewById(R.id.mypage_list_title);
            mypage_list_content= itemView.findViewById(R.id.mypage_list_content);
            mypage_list_like_cnt= itemView.findViewById(R.id.mypage_list_like_cnt);
            mypage_list_coment_cnt= itemView.findViewById(R.id.mypage_list_coment_cnt);
            mypage_list_delete= itemView.findViewById(R.id.mypage_list_delete);
            mypage_list_update= itemView.findViewById(R.id.mypage_list_update);
            mypage_list_writedate=itemView.findViewById(R.id.mypage_list_writedete);

        }

    }
    public void bind(ViewHolder holder, int i){
        Uri uri= Uri.parse(list.get(i).getFile_path());
        Glide.with(context).load(uri).into(holder.mypage_list_img);
        holder.mypage_list_title.setText(list.get(i).getBoard_title()+"");
        holder.mypage_list_content.setText(list.get(i).getBoard_content()+"");
        holder.mypage_list_like_cnt.setText(list.get(i).getBoard_like_cnt()+"");
        holder.mypage_list_coment_cnt.setText(list.get(i).getBoard_comment_cnt()+"");
        holder.mypage_list_writedate.setText(list.get(i).getBoard_writedate());



        holder.mypage_list_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("삭제하시겠습니까?");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dao.delete(list.get(i));
                        list.remove(i);
                        notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {

                    }
                });


                AlertDialog alertDialog = builder.create();
                alertDialog.show();


            }
        });

        holder.mypage_list_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.changeFragment(new Movie_updateFragment(context,list.get(i)));
                notifyDataSetChanged();

            }
        });



    }
    public void chang(){
        notifyDataSetChanged();
    }

}
