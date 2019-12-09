package com.example.smsreceiver;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.rey.material.widget.Button;
import com.rey.material.widget.EditText;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    //퍼미션을 위한 값
    static final int SMS_RECEIVE_PERMISSION = 1;

    static Context staticContext;

    EditText editText;

    String placeName = null;
    String placeURI = null;
    String locationX = null;
    String locationY = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        staticContext = this;

        editText = findViewById(R.id.editText);
        Button btClick = findViewById(R.id.btClick);

        //http://apis.map.kakao.com/android/guide/#step2 등록 하는 방법
        //해시키를 가져오는 함수
        getHash();

        //권한 설정 부분
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);

        if(permissionCheck == PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this, "Already SMS Accept", Toast.LENGTH_LONG).show();

        }else {

            Toast.makeText(this, "No SMS Accept", Toast.LENGTH_LONG).show();

            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {

                Toast.makeText(this, "Need SMS Accept", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, SMS_RECEIVE_PERMISSION);

            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, SMS_RECEIVE_PERMISSION);

            }
        }

//        Intent passedIntent = getIntent();
//        processIntent(passedIntent);



        btClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleBtOnClick();
            }
        });
    }

    @Override
    protected void onResume() {

        super.onResume();

        String date = "";
        String sender = "";
        String contents;
        String resultCredit = "";
        String resultLocation = "";

        Bundle bundle = getIntent().getExtras();

        if(bundle == null) {

            contents = "no received message";
            Log.d("check", "bundle null" + contents);

        } else {

            date = bundle.getString("date", "no date");
            sender = bundle.getString("sender", "no sender");
            contents = bundle.getString("contents", "no contents");
            Log.d("check", "bundle not null" + contents);

            resultCredit = checkSMSCredit(contents);
            resultLocation = checkSMSLocation(contents);

        }

        editText.setText(resultCredit + "\n" + resultLocation);

        NotificationManagerCompat.from(this).cancel(0);

    }

    //버튼 클릭 핸들러
    private void handleBtOnClick() {

        //네트워크 스레드는 메인 스레드에서 작동할 수 없기 때문에 스레드를 만든다.
        // TODO should block user interactions.
        new AsyncTask<Void, Void, String>() {

            //백그라운드에서 동작하는 스레드
            //API 요청과 함께 요청 후 돌아오는 데이터 값을 받아서 리턴한다.
            @Override
            protected String doInBackground(Void... voids) {

                //TODO on background thread

                //Uri 를 만든다. Url은 encoding 을 해야하기 때문에 자동으로 해주는 Uri를 만든다.
                String domain = "https://dapi.kakao.com/v2/local/search/keyword.json";
                Uri.Builder builder = Uri.parse(domain).buildUpon();
                //위치 정보를 쿼리에 담는다.
                builder = addCurrentLocationQueryAt(builder);
                //키워드 정보를 쿼리에 담는다.
                builder = addSearchKeywordQueryAt(builder);
//                builder.authority("e67b96e3b9ce1c62328509075e81ae5b");

                try {
                    //만든 uri를 url로 바꾼다.
                    URL requestURL = new URL(builder.build().toString());

                    //커넥터를 만든다.
                    HttpsURLConnection connection = (HttpsURLConnection) requestURL.openConnection();
                    //헤더에 API키를 넣어줘야 하므로 씀
                    connection.setRequestProperty("Authorization", "KakaoAK e67b96e3b9ce1c62328509075e81ae5b");

                    //연결이 잘 됐는지 확인
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        //데이터는 stream으로 오기 때문에 stream으로 받는다. 그리고 string 으로 변환한다.
                        InputStreamReader reader = new InputStreamReader(connection.getInputStream(), "UTF-8");
                        BufferedReader bufferedReader = new BufferedReader(reader);
                        StringBuffer buffer = new StringBuffer();

                        String str = null;
                        String resultString = null;

                        while((str = bufferedReader.readLine()) != null) {
                            buffer.append(str);
                        }

                        resultString = buffer.toString();
                        return resultString;

                        // 요청에 오류가 있는 경우
                    } else {
                        Log.e("!!", "error" + connection.getResponseCode());
                    }

                } catch(IOException e) {

                }

                return null;
            }

            //백그라운드 스레드가 끝나면 해야 할 동작
            @Override
            protected void onPostExecute(String resultString) {

                super.onPostExecute(resultString);

                // TODO should recover user interactions

                //요청 후 받은 데이터를 해석하는 함수
                handleSearchResult(resultString);

            }

        }.execute();

    }

    //위치 정보를 uri에 저장하는 곳
    private Uri.Builder addCurrentLocationQueryAt(Uri.Builder builder) {

        builder.appendQueryParameter("x", "37.5642135")
                .appendQueryParameter("y", "127.0016985");

        return builder;

    }

    //키워드 정보를 uri에 저장하는곳
    private Uri.Builder addSearchKeywordQueryAt(Uri.Builder builder) {

        builder.appendQueryParameter("query", editText.getText().toString());

        return builder;

    }

    //요청 후 데이터를 해석하는 함수
    private void handleSearchResult(String resultString) {

        try {
            //데이터가 돌아오는 방식이 Json 이다 그래서 Json 방식에 맞게 원하는 값을 가져온다.
            //데이터 형식을 담을 수 있는 공간을 만든다.
            //https://developers.kakao.com/docs/restapi/local#%ED%82%A4%EC%9B%8C%EB%93%9C%EB%A1%9C-%EC%9E%A5%EC%86%8C-%EA%B2%80%EC%83%89
            //response 부분을 보면 documents 부분을 가져와야 한다.
            JSONArray jsonArray = new JSONObject(resultString).getJSONArray("documents");

            for(int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                //데이터를 가져온다. name 부분은 이미 정해져있다. 카카오 API설명에 가보면 리턴되는 형식이 있는데 그곳의 key 값으로 찾아온다.
                //https://developers.kakao.com/docs/restapi/local#%ED%82%A4%EC%9B%8C%EB%93%9C%EB%A1%9C-%EC%9E%A5%EC%86%8C-%EA%B2%80%EC%83%89
                //response 부분을 보면 documents 안의 내용 중 필요한 것들 만 가져온다.
                placeName = jsonObject.optString("place_name");
                placeURI = jsonObject.optString("place_url");
                locationX = jsonObject.optString("x");
                locationY = jsonObject.optString("y");

            }

            // 데이터를 받을 때 오류
        } catch (JSONException e) {

            Log.e("!!!", "error : " + e.getMessage());
            e.printStackTrace();

        }


        Log.e("!!!!", placeName + " : " + placeURI);
        editText.setText(placeName + "\n" + placeURI + "\n" +locationX + " : " + locationY);

        //맵뷰 객체를 만든다.
        MapView mapView = new MapView(staticContext);

        //API 키를 세팅한다.
        mapView.setDaumMapApiKey("c165ac5d77f7efdb29717ecf790ddb06");
        ViewGroup mapViewGroup = findViewById(R.id.mapView);

        //맵에서 보여줄 위치를 지정한다.
        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(Double.parseDouble(locationY), Double.parseDouble(locationX));
        mapView.setMapCenterPoint(mapPoint, true);
        mapViewGroup.addView(mapView);

        //위치에 마커를 찍는다.
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("ex");
        marker.setTag(0);
        marker.setMapPoint(mapPoint);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(marker);

    }

    //문자에서 지점 이름을 가져오는 함수
    private String checkSMSLocation(String contents) {

        String resultLocation = "";

        if(contents.contains("신한") && contents.contains("승인")) {
            String[] location = contents.split("원 ");
            for(String checkLocation : location) {
                resultLocation = checkLocation;
            }
            int index = resultLocation.indexOf("[");
            resultLocation = resultLocation.substring(0, index);
        } else {
            return resultLocation;
        }

        return resultLocation;
    }

    //문자에서 금액을 가져오는 함수
    private String checkSMSCredit(String contents) {

        String resultCredit = "";

        if(contents.contains("신한") && contents.contains("승인")) {
            String[] credit = contents.split("액\\)");
            for(String checkCredit : credit) {
                resultCredit = checkCredit;
            }
            int index = resultCredit.indexOf("원");
            resultCredit  = resultCredit.substring(0, index);
        } else{

            return resultCredit;
        }

        return resultCredit;
    }

    //권한 설정
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {

            case SMS_RECEIVE_PERMISSION:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(getApplicationContext(), "SMS Accept", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(getApplicationContext(), "SMS not Accept", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

//        //인텐트가 오면 작업할 부분
//    private void processIntent(Intent passedIntent) {
//        if(passedIntent != null) {
//            string = passedIntent.getStringExtra("string");
//            editText.setText(string);
//            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            notificationManager.cancel(0);
//        }
//    }
//
//    //브로드캐스트 리시버가 앱이 죽어있어도 핸도폰의 바뀌는 내용을 인텐트로 전달한다.
//    //앱이 죽어있어도 인텐트가 전달되면 자동으로 인텐트를 실행하면서 앱을 실행 시킨다.
//    @Override
//    protected void onNewIntent(Intent intent) {
//        processIntent(intent);
//        super.onNewIntent(intent);
//    }

    //해시키를 가져오는 함수
    private void getHash() {

        try {

            PackageInfo packageInfoCompat = getPackageManager().getPackageInfo("com.example.smsreceiver", PackageManager.GET_SIGNATURES);

            for(Signature signature : packageInfoCompat.signatures) {

                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("hash", "key : " + Base64.encodeToString(md.digest(), Base64.DEFAULT));

            }

        } catch (Exception e){

            e.printStackTrace();

        }
    }

}
