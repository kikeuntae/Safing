package com.example.safing.home.fragment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.safing.MainActivity;
import com.example.safing.R;
import com.example.safing.async.OnItemClick_Theme_Listener;
import com.example.safing.home.DAO.HomeDAO;
import com.example.safing.home.DAO.SafeZoneRecDAO;
import com.example.safing.home.DAO.YoutubeTipDAO;
import com.example.safing.home.DTO.ThemeRecDTO;
import com.example.safing.home.VO.SafeZoneRecVO;
import com.example.safing.home.VO.YoutubeTipVO;
import com.example.safing.home.activity.SafeGuardInfoActivity;
import com.example.safing.home.adapter.SafeZoneRecAdapter;
import com.example.safing.home.adapter.Theme_Pager_Adapter;
import com.example.safing.home.adapter.YouTubeTipRecAdapter;
import com.example.safing.shop.fragment.Product_Package_Fragment;
import com.example.safing.shop.fragment.ShopFragment;
import java.util.ArrayList;

public class HomeFragment extends Fragment{
    RecyclerView recysf, recyoutube ;
    Context context;
    RecyclerView rectheme;
    ImageView home_search, sguse, thumbnails, logo;
    TextView packgemore;
    MainActivity mainActivity = new MainActivity();
    LinearLayout youtubetip1;

    // Channel에 대한 id 생성
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    // Channel을 생성 및 전달해 줄 수 있는 Manager 생성
    private NotificationManager mNotificationManager;
    // Notification에 대한 ID 생성
    private static final int NOTIFICATION_ID = 0;
    // Notification을 호출할 button 변수






    public HomeFragment(Context context){
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        recysf = rootView.findViewById(R.id.recysfzone1);

        mainActivity = (MainActivity) getActivity();

        logo = rootView.findViewById(R.id.logo_btn);
        recyoutube = rootView.findViewById(R.id.youtubetip);
        sguse = rootView.findViewById(R.id.sguse);
        youtubetip1 = rootView.findViewById(R.id.youtubetip1);
        home_search = rootView.findViewById(R.id.home_search);
        packgemore = rootView.findViewById(R.id.packgemore);
        rectheme = rootView.findViewById(R.id.rectheme);
        thumbnails = rootView.findViewById(R.id.thumbnails);

        //====================Recysfzone=====================================//
        recysf.findViewById(R.id.recysfzone1);
        SafeZoneRecDAO dao1 = new SafeZoneRecDAO();
        ArrayList<SafeZoneRecVO> list = dao1.sfzone_list();

        SafeZoneRecAdapter adapter1 = new SafeZoneRecAdapter(context, list);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(
                context, RecyclerView.HORIZONTAL, false
        );
        recysf.setLayoutManager(layoutManager1);
        recysf.setAdapter(adapter1);
        //====================Recysfzone=====================================//

        //=====================RecyclerCamTip========================================//
        recyoutube.findViewById(R.id.youtubetip);
        YoutubeTipDAO dao = new YoutubeTipDAO();
        ArrayList<YoutubeTipVO> list2 = dao.tip_list();

        YouTubeTipRecAdapter adapter2 = new YouTubeTipRecAdapter(context, list2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(
                context, RecyclerView.HORIZONTAL, false
        );
        recyoutube.setLayoutManager(layoutManager2);
        recyoutube.setAdapter(adapter2);


        //=====================RecyclerCamTip========================================//

        //=====================pagerTheme===================================//

        HomeDAO dao2 = new HomeDAO();
        ArrayList<ThemeRecDTO> list1 = dao2.Theme_Pager();


        Theme_Pager_Adapter adapter = new Theme_Pager_Adapter(context, list1);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(
                context, RecyclerView.HORIZONTAL, false
        );
        rectheme.setLayoutManager(layoutManager3);
        rectheme.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClick_Theme_Listener() {
            @Override
            public void onItemClick_package(Theme_Pager_Adapter.ViewHolder holderm, View view, int position) {
                mainActivity.changeFragment(new Product_Package_Fragment(context, list1.get(position).getPackage_num()));
            }
        });
        //=====================pagerTheme===================================//




        sguse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getActivity(), SafeGuardInfoActivity.class);
                startActivity(intent);
            }
        });



        home_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent  = new Intent(context, HomeSearchFragment.class);
                startActivity(intent);*/
                mainActivity = (MainActivity) getActivity();
                mainActivity.changeFragment(new HomeSearchFragment(context));
                mainActivity.overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_left_exit);
            }
        });


        packgemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity = (MainActivity) getActivity();
                mainActivity.changeFragment(new ShopFragment(context));
            }
        });


        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();

            }
        });
        createNotificationChannel();



        return rootView;
    }

    public void changeFragment(Fragment fragment){
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.shop_container1 , fragment).addToBackStack(null).commit();
    }

    //채널을 만드는 메소드
    public void createNotificationChannel()
    {
        //notification manager 생성
        mNotificationManager = (NotificationManager)
                mainActivity.getSystemService(context.NOTIFICATION_SERVICE);
        // 기기(device)의 SDK 버전 확인 ( SDK 26 버전 이상인지 - VERSION_CODES.O = 26)
        if(android.os.Build.VERSION.SDK_INT
                >= android.os.Build.VERSION_CODES.O){
            //Channel 정의 생성자( construct 이용 )
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID
                    ,"Test Notification",mNotificationManager.IMPORTANCE_HIGH);
            //Channel에 대한 기본 설정
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from Mascot");
            // Manager을 이용하여 Channel 생성
            mNotificationManager.createNotificationChannel(notificationChannel);
        }

    }

    // Notification Builder를 만드는 메소드
    private NotificationCompat.Builder getNotificationBuilder() {
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
                .setContentTitle("침입이 감지되었습니다")
                .setContentText("당신이 설치한 기기에서 침입이 감지되었습니다!!!")
                .setSmallIcon(R.drawable.logo2);
        return notifyBuilder;
    }

    // Notification을 보내는 메소드
    public void sendNotification(){
        // Builder 생성
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        // Manager를 통해 notification 디바이스로 전달
        mNotificationManager.notify(NOTIFICATION_ID,notifyBuilder.build());
    }


}