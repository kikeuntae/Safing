package com.example.safing.mypage.fragment;

import static android.app.Activity.RESULT_OK;
import static com.example.safing.async.CommonAsk.MEMBER_ID;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.safing.MainActivity;
import com.example.safing.R;
import com.example.safing.async.AskParam;
import com.example.safing.async.CommonAsk;
import com.example.safing.async.CommonMethod;
import com.example.safing.movie.DAO.Comment_DAO;
import com.example.safing.movie.DAO.Movie_DAO;
import com.example.safing.movie.DTO.Board_Movie_DTO;
import com.example.safing.movie.fragment.Movie_insertFragment;
import com.example.safing.mypage.VO.MemberVO;
import com.example.safing.mypage.adapter.Mypage_Adapter;
import com.example.safing.shop.DAO.ShopDAO;
import com.example.safing.shop.VO.CartVO;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class MypageFragment extends Fragment {
    Context context;
    LinearLayoutManager manager;
    ImageView mypage_setting,mypage_add;
    Button mypage_login;
    TextView mypage_id , mypage_board_cnt, mypage_like_sum, mypage_shoppingbasket_sum;
    CircleImageView mypage_profile;
    AlertDialog dialog;
    final int GALLERY_IMG = 1001;
    final int CAMERA_REQ = 1002;
    final int GALLERY_VIDEO = 1003;
    String img_filepath;//이미지 경로를 저장하기위한 String변수.
    // Spinner spn_camera;//ComboBox 형태로 쓸때만 사용.
    String[] spn_item = {"카메라", "갤러리"};
    Movie_DAO dao = new Movie_DAO();
    Mypage_Adapter adapter;
    RecyclerView rec;



    public MypageFragment(Context context){
        this.context = context;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_mypage, container, false);

        checkDangerousPermissions();


        MainActivity mainActivity = (MainActivity) getActivity();
        mypage_profile = rootView.findViewById(R.id.mypage_profile);
        mypage_setting = rootView.findViewById(R.id.mypage_setting);
        mypage_add = rootView.findViewById(R.id.mypage_add);
        mypage_id = rootView.findViewById(R.id.mypage_id);
        rec= rootView.findViewById(R.id.mypage_recyclerView);
        mypage_board_cnt= rootView.findViewById(R.id.mypage_board_cnt);
        mypage_like_sum= rootView.findViewById(R.id.mypage_like_sum);
        mypage_shoppingbasket_sum= rootView.findViewById(R.id.mypage_shoppingbasket_sum);
        //셋팅메뉴 이동

        List<Board_Movie_DTO> videoItems  = new ArrayList<>();
        MemberVO vo = new MemberVO();
        vo = MainActivity.getLogin_member();
        videoItems = dao.list_mypage(vo);
        adapter = new Mypage_Adapter(getContext() , videoItems);
        rec.setAdapter(adapter);

        mypage_board_cnt.setText(videoItems.size()+"");
        int like_sum= 0;
        for (Board_Movie_DTO like:videoItems){
            like_sum= like_sum + like.getBoard_like_cnt();
        }
        mypage_like_sum.setText(like_sum+"");

        ShopDAO dao_shop = new ShopDAO();


        ArrayList<CartVO> cart_list = dao_shop.cart_list(vo.getMember_id());

        mypage_shoppingbasket_sum.setText(cart_list.size()+"");







        Comment_DAO dao = new Comment_DAO();
        String url = dao.memberImg(vo.getMember_id());
        if(url!=null){
            Glide.with(context)
                    .load(Uri.parse(url))
                    .into(mypage_profile);
        }else {
            Glide.with(context)
                    .load(R.drawable.profile)
                    .into(mypage_profile);
        }

        mypage_id.setText(vo.getMember_id());

        mypage_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });



        mypage_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainActivity.changeFragment(new Movie_insertFragment(context));
            }
        });

        mypage_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SettingActivity.class);
                startActivity(intent);
            }
        });



        return rootView;
    }


    //사용자가 프로필사진을 클릭(OnClick) 했을때 카메라로 사진을 추가할건지,
    //또는 갤러리로 추가할건지를 선택하게함.
    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("사진을 어떤것으로 추가 하시겠습니까?")
                .setSingleChoiceItems(spn_item, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if (spn_item[i].equals("카메라")) {
                            Toast.makeText(context, "카메라 선택됨", Toast.LENGTH_SHORT).show();
                            //API 요즘 버전에서는 카메라 관련 된 서비스 이용이 복잡함.
                            //provider라는 카메라의 사진을 가지고올수있는 객체도 필요하고 File객체도 필요함.
                            go_Camera();

                        } else if (spn_item[i].equals("갤러리")) {
                            Toast.makeText(context, "갤러리 선택됨", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_PICK);
                            startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_IMG);
                        }
                        dialog.dismiss();
                    }
                });
        dialog = builder.create();
        dialog.show();
    }

    public void go_Camera() {
        //카메라 앱을 실행할때는 파일을 만들어서 보내줘야함. (그파일 형태에 이미지 채움)
        Intent cIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //권한을 가지고 있으면서 해당하는 인텐트 서비스를 사용할수있는지 체크
        if (cIntent.resolveActivity(context.getPackageManager()) != null) {
            File imgFile = createFile();
            if (imgFile != null) {
                Uri imgUri = FileProvider.getUriForFile(context.getApplicationContext(),
                        context.getApplicationContext().getPackageName() + ".fileprovider", imgFile);
                //만약에 API24 이상이면
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    cIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
                } else {
                    cIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imgFile));
                }
                startActivityForResult(cIntent, CAMERA_REQ);
            }

        }
    }


    File createFile() {
        //파일 이름을 만들기 위한 처리.
        String timeStamp = new SimpleDateFormat("yyyyMMdd HHmmss")
                .format(new Date());
        String uid = UUID.randomUUID().toString();
        //어떤 파일이나 값을 식별하기위한 고유식별자로 많이 사용됨 (중복값이 나오면 안되는 경우에 )
        String imageFileName = "My" + timeStamp;

        //사진 파일을 저장하기 위한 경로
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File curFile = null;
        try {
            // 임시 파일을 생성함(전체경로 ) , 2번째 suffix 확장자
            curFile = File.createTempFile(imageFileName, ".jpg", storageDir);
        } catch (IOException e) {

        }
        img_filepath = curFile.getAbsolutePath();//
        //Multipart에 보내기 위한 임시파일이 있는 곳의 절대경로를 저장하는 로직이 필요함.(String)

        return curFile;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_IMG && resultCode == RESULT_OK) {
            Toast.makeText(context, "이미지 가져오기 성공.", Toast.LENGTH_SHORT).show();
            Uri galleryUri = data.getData();
            img_filepath = getPathFromURI(galleryUri);
            //img_filepath = galleryUri;
            Glide.with(context).load(galleryUri).into(mypage_profile);
            upload();
        } else if (requestCode == CAMERA_REQ && resultCode == RESULT_OK) {
            Toast.makeText(context, "사진을 찍어서 가지고오긴함.", Toast.LENGTH_SHORT).show();
            //갤러리에 사진을 저장하고 저장한 Uri를 통해서 다시 Glide를 통해 붙이기
            //Uri gellaryAddpic메소드 하기전 .. 담주진행.
            //Uri cameraUri = data.getData();
            Glide.with(context).load(img_filepath).into(mypage_profile);
            upload();
        } else if (requestCode == GALLERY_VIDEO && resultCode == RESULT_OK) {
            Toast.makeText(context, "동영상 가지고오긴함.", Toast.LENGTH_SHORT).show();
            Uri galleryUri = data.getData();
            img_filepath = getPathFromURI(galleryUri);
            //갤러리에 사진을 저장하고 저장한 Uri를 통해서 다시 Glide를 통해 붙이기
            //Uri gellaryAddpic메소드 하기전 .. 담주진행.
            //Uri cameraUri = data.getData();
            Glide.with(context).load(img_filepath).into(mypage_profile);
            upload();
        }


    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cusor = context.getContentResolver().query(contentUri, proj, null, null, null);
        if (cusor.moveToFirst()) {
            int column_index = cusor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cusor.getString(column_index);
        }
        return res;
    }


    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_MEDIA_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(context, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {

        } else {
            Toast.makeText(context, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permissions[0])) {
                Toast.makeText(context, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions((Activity) context, permissions, 1);
            }
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void upload(){
    //MemberVO라는것을만들고 Spring도 MemberVO => Mapper추가해서 DB에 추가로직
    //Vo를 보내서 인서트하게끔 만들어야함.
        MemberVO vo = new MemberVO();
        vo = MainActivity.getLogin_member();
        Gson gson = new Gson();

    CommonAsk commonAsk = new CommonAsk("memberimg_up.me");
                    commonAsk.params.add(new AskParam("vo", gson.toJson(vo)));
                    commonAsk.fileprams.add(new AskParam("file", img_filepath));
    InputStream in =  CommonMethod.excuteAsk(commonAsk);
    String result = gson.fromJson(new InputStreamReader(in) , String.class);
                    if(result != null && result.equals("1")){
                        Toast.makeText(context, "이미지를 서버에 보냈습니다", Toast.LENGTH_SHORT).show();
    }else{
        Toast.makeText(context, "오류발생", Toast.LENGTH_SHORT).show();
    }
    }

    @Override
    public void onResume() {
        super.onResume();
        rec.setAdapter(adapter);
    }
}