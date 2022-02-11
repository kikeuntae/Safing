package com.example.safing.home.activity;

import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager2.widget.ViewPager2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.safing.R;
import com.example.safing.home.DAO.CampImgDAO;
import com.example.safing.home.DAO.SafeZoneRecDAO;
import com.example.safing.home.VO.CampImgVO;
import com.example.safing.home.VO.SafeZoneRecVO;
import com.example.safing.home.adapter.CaminfoPager2Adapter;

import net.daum.mf.map.api.CalloutBalloonAdapter;
import net.daum.mf.map.api.CameraUpdateFactory;
import net.daum.mf.map.api.MapLayout;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapPointBounds;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;


public class CamInfoActivity extends AppCompatActivity implements MapView.OpenAPIKeyAuthenticationResultListener, MapView.MapViewEventListener, MapView.POIItemEventListener{
    ViewPager2 pager2 ;
    ImageView badge;
    TextView facltnm, addr1, intro, resvecl, tel, homepage, induty, prmisnde, insrncat, animalcmgcl, facltdivnm,
            mangedivnm,  mgcdiv, operdecl, toiletco, swrmco, wtrplco, sbrscl;


    private MapView mMapView;
    public static final String DAUM_MAPS_ANDROID_APP_API_KEY = "cad9d128e6313db0c6b34ec4aab5209a";
    private static final int MENU_MAP_TYPE = Menu.FIRST + 1;
    private static final int MENU_MAP_MOVE = Menu.FIRST + 2;


    private  SafeZoneRecVO vo;
    private   MapPoint CUSTOM_MARKER_POINT       = null;
    private   MapPoint CUSTOM_MARKER_POINT2 = null;
    private   MapPoint DEFAULT_MARKER_POINT = null;

    private MapPOIItem mDefaultMarker;
    private MapPOIItem mCustomMarker;
    private MapPOIItem mCustomBmMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam_info);

        Intent intent = getIntent();
        vo = (SafeZoneRecVO) intent.getSerializableExtra("vo");

       CUSTOM_MARKER_POINT  = MapPoint.mapPointWithGeoCoord(Double.parseDouble(vo.getMapy()) , (Double.parseDouble(vo.getMapx())));
       CUSTOM_MARKER_POINT2 = MapPoint.mapPointWithGeoCoord(Double.parseDouble(vo.getMapy()) , (Double.parseDouble(vo.getMapx())));
       DEFAULT_MARKER_POINT = MapPoint.mapPointWithGeoCoord(Double.parseDouble(vo.getMapy()) , (Double.parseDouble(vo.getMapx())));
        MapLayout mapLayout = new MapLayout(this);
        mMapView = mapLayout.getMapView();


        mMapView.setDaumMapApiKey(DAUM_MAPS_ANDROID_APP_API_KEY);
        mMapView.setOpenAPIKeyAuthenticationResultListener(this);
        mMapView.setMapViewEventListener(this);
        mMapView.setMapType(MapView.MapType.Standard);

        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapLayout);

        //마커 부분=============================
        mMapView.setPOIItemEventListener(this);

        // 구현한 CalloutBalloonAdapter 등록
        mMapView.setCalloutBalloonAdapter(new CustomCalloutBalloonAdapter());
        createDefaultMarker(mMapView);
        createCustomMarker(mMapView);
        createCustomBitmapMarker(mMapView);
        showAll();




        pager2 = findViewById(R.id.vpcaminfo);
        badge = findViewById(R.id.badge);

        facltnm = findViewById(R.id.facltnm);
        addr1 = findViewById(R.id.addr2);
        intro = findViewById(R.id.intro);
        resvecl = findViewById(R.id.resvecl);
        tel = findViewById(R.id.tel);
        homepage = findViewById(R.id.homepage);
        induty = findViewById(R.id.induty);
        prmisnde = findViewById(R.id.prmisnde);
        insrncat = findViewById(R.id.insrncat);
        animalcmgcl = findViewById(R.id.animalcmgcl);
        facltdivnm = findViewById(R.id.facltdivnm);
        mangedivnm = findViewById(R.id.mangedivnm);
        mgcdiv = findViewById(R.id.mgcdiv);
        operdecl = findViewById(R.id.operdecl);
        toiletco = findViewById(R.id.toiletco);
        swrmco = findViewById(R.id.swrmco);
        wtrplco = findViewById(R.id.wtrplco);
        sbrscl = findViewById(R.id.sbrscl);



        facltnm.setText(vo.getFacltnm());
        addr1.setText(vo.getAddr1());
        intro.setText(vo.getIntro());
        resvecl.setText(vo.getResvecl());
        tel.setText(vo.getTel());
        homepage.setText(vo.getHomepage());
        induty.setText(vo.getInduty());
        prmisnde.setText(vo.getPrmisnde());
        insrncat.setText(vo.getInsrncat());
        animalcmgcl.setText(vo.getAnimalcmgcl());
        facltdivnm.setText(vo.getFacltdivnm());
        mangedivnm.setText(vo.getMangedivnm());
        mgcdiv.setText(vo.getMgcdiv());
        operdecl.setText(vo.getOperdecl());
        toiletco.setText(vo.getToiletco());
        swrmco.setText(vo.getSwrmco());
        wtrplco.setText(vo.getWtrplco());
        sbrscl.setText(vo.getSbrscl());

        CampImgDAO dao = new CampImgDAO();
        ArrayList<CampImgVO> list = dao.campimg_list(vo.getContentid());
        CaminfoPager2Adapter adapter = new CaminfoPager2Adapter(CamInfoActivity.this, list);
        pager2.setAdapter(adapter);

    }

    //위에 옵션 메뉴 부분.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, MENU_MAP_TYPE, Menu.NONE, "MapType");
        menu.add(0, MENU_MAP_MOVE, Menu.NONE, "Move");

        return true;
    }

    //옵션 메뉴 선택했을때 이벤트 ↓
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        final int itemId = item.getItemId();

        switch (itemId) {
            case MENU_MAP_TYPE: {

                //String hdMapTile = mMapView.isHDMapTileEnabled()? "HD Map Tile Off" : "HD Map Tile On";

                String hdMapTile;

                if (mMapView.getMapTileMode() == MapView.MapTileMode.HD2X) {
                    hdMapTile = "Set to Standard Mode";
                } else if (mMapView.getMapTileMode() == MapView.MapTileMode.HD) {
                    hdMapTile = "Set to HD 2X Mode";
                } else {
                    hdMapTile = "Set to HD Mode";
                }

                String[] mapTypeMenuItems = { "Standard", "Satellite", "Hybrid", hdMapTile, "Clear Map Tile Cache"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("MapType");
                dialog.setItems(mapTypeMenuItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        controlMapTile(which);
                    }
                });
                dialog.show();


                return true;
            }

            case MENU_MAP_MOVE: {
                String rotateMapMenu = mMapView.getMapRotationAngle() == 0.0f? "Rotate Map 60" : "Unrotate Map";
                String[] mapMoveMenuItems = { "Move to", "Zoom to", "Move and Zoom to", "Zoom In", "Zoom Out", rotateMapMenu};

                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Move");
                dialog.setItems(mapMoveMenuItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        controlMapMove(which);
                    }

                });
                dialog.show();

                return true;
            }
        }


        return super.onOptionsItemSelected(item);
    }


    private void controlMapMove(int which) {
        switch (which) {
            case 0: // Move to
            {
                mMapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(Double.parseDouble(vo.getMapy()) , (Double.parseDouble(vo.getMapx()))), true);
            }
            break;
            case 1: // Zoom to
            {
                mMapView.setZoomLevel(7, true);
            }
            break;
            case 2: // Move and Zoom to
            {
                mMapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(Double.parseDouble(vo.getMapy()) , (Double.parseDouble(vo.getMapx()))), 9, true);
            }
            break;
            case 3: // Zoom In
            {
                mMapView.zoomIn(true);
            }
            break;
            case 4: // Zoom Out
            {
                mMapView.zoomOut(true);
            }
            break;
            case 5: // Rotate Map 60, Unrotate Map
            {
                if (mMapView.getMapRotationAngle() == 0.0f) {
                    mMapView.setMapRotationAngle(60.0f, true);
                } else {
                    mMapView.setMapRotationAngle(0.0f, true);
                }
            }
            break;
        }
    }

    /**
     * 지도 타일 컨트롤.
     */


    private void controlMapTile(int which) {
        switch (which) {
            case 0: // Standard
            {
                mMapView.setMapType(MapView.MapType.Standard);
            }
            break;
            case 1: // Satellite
            {
                mMapView.setMapType(MapView.MapType.Satellite);
            }
            break;
            case 2: // Hybrid
            {
                mMapView.setMapType(MapView.MapType.Hybrid);
            }
            break;
            case 3: // HD Map Tile On/Off
            {
                if (mMapView.getMapTileMode() == MapView.MapTileMode.HD2X) {
                    //Set to Standard Mode
                    mMapView.setMapTileMode(MapView.MapTileMode.Standard);
                } else if (mMapView.getMapTileMode() == MapView.MapTileMode.HD) {
                    //Set to HD 2X Mode
                    mMapView.setMapTileMode(MapView.MapTileMode.HD2X);
                } else {
                    //Set to HD Mode
                    mMapView.setMapTileMode(MapView.MapTileMode.HD);
                }
            }
            break;
            case 4: // Clear Map Tile Cache
            {
                MapView.clearMapTilePersistentCache();
            }
            break;
        }
    }



    //지도가 초기화 되고나서 실행됩니당.
    @Override
    public void onMapViewInitialized(MapView mapView) {
            //Double.parseDouble(vo.getMapx());
            mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(Double.parseDouble(vo.getMapy()), Double.parseDouble(vo.getMapx())), 2, true);

    }
    //지도의 중심 부분이 이동되었을때 위경도를 알아오기위한 처리를 넣어주심됩니다.
    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {
        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    //맵을 한번클릭했을때. 잘 사용안함;
    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {
        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();

    }

    //맵을 더블클릭했을때 .
    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {
        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("DaumMapLibrarySample");
        alertDialog.setMessage(String.format("Double-Tap on (%f,%f)", mapPointGeo.latitude, mapPointGeo.longitude));
        alertDialog.setPositiveButton("OK", null);
        alertDialog.show();
    }

    //맵을 오래 누르고잇을때.
    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("DaumMapLibrarySample");
        alertDialog.setMessage(String.format("Long-Press on (%f,%f)", mapPointGeo.latitude, mapPointGeo.longitude));
        alertDialog.setPositiveButton("OK", null);
        alertDialog.show();
    }

    //맵을 드래그시작
    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {
        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();

    }
    //끝
    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {
        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();

    }
    //이동마무리
    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {
        MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();

    }

    @Override
    public void onDaumMapOpenAPIKeyAuthenticationResult(MapView mapView, int i, String s) {

    }


    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }


    class CustomCalloutBalloonAdapter implements CalloutBalloonAdapter {
        private final View mCalloutBalloon;

        public CustomCalloutBalloonAdapter() {
            mCalloutBalloon = getLayoutInflater().inflate(R.layout.custom_callout_balloon, null);
        }

        @Override
        public View getCalloutBalloon(MapPOIItem poiItem) {

            ((ImageView) mCalloutBalloon.findViewById(R.id.badge)).setImageResource(R.drawable.campack);
            ((TextView) mCalloutBalloon.findViewById(R.id.m_facltnm)).setText(vo.getFacltnm());
            ((TextView) mCalloutBalloon.findViewById(R.id.m_addr1)).setText(vo.getAddr1());
            return mCalloutBalloon;

           // sbrscl.setText(vo.getSbrscl());
        }

        @Override
        public View getPressedCalloutBalloon(MapPOIItem poiItem) {
            return null;
        }



    }

    private void createDefaultMarker(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "Default Marker";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(0);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
        mapView.selectPOIItem(mDefaultMarker, true);
        mapView.setMapCenterPoint(DEFAULT_MARKER_POINT, false);
    }

    private void createCustomMarker(MapView mapView) {
        mCustomMarker = new MapPOIItem();
        String name = "Custom Marker";
        mCustomMarker.setItemName(name);
        mCustomMarker.setTag(1);
        mCustomMarker.setMapPoint(CUSTOM_MARKER_POINT);

        mCustomMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);


        mCustomMarker.setCustomImageAutoscale(false);
        mCustomMarker.setCustomImageAnchor(0.5f, 1.0f);

        mapView.addPOIItem(mCustomMarker);
        mapView.selectPOIItem(mCustomMarker, true);
        mapView.setMapCenterPoint(CUSTOM_MARKER_POINT, false);

    }

    private void createCustomBitmapMarker(MapView mapView) {
        mCustomBmMarker = new MapPOIItem();
        String name = "Custom Bitmap Marker";
        mCustomBmMarker.setItemName(name);
        mCustomBmMarker.setTag(2);
        mCustomBmMarker.setMapPoint(CUSTOM_MARKER_POINT2);

        mCustomBmMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);


        mCustomBmMarker.setCustomImageAutoscale(false);
        mCustomBmMarker.setCustomImageAnchor(0.5f, 0.5f);

        mapView.addPOIItem(mCustomBmMarker);
        mapView.selectPOIItem(mCustomBmMarker, true);
        mapView.setMapCenterPoint(CUSTOM_MARKER_POINT, false);
    }

    private void showAll() {
        int padding = 20;
        float minZoomLevel = 7;
        float maxZoomLevel = 10;
        MapPointBounds bounds = new MapPointBounds(CUSTOM_MARKER_POINT, DEFAULT_MARKER_POINT);
        mMapView.moveCamera(CameraUpdateFactory.newMapPointBounds(bounds, padding, minZoomLevel, maxZoomLevel));
    }
}