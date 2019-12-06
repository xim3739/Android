package com.example.smsreceiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {

    public static String NOTIFICATION_ID = "notification_id";

    //디버그를 위한 값
    private static final String TAG = "SmsReceiver";
    private static final String NOTI_TAG = "notification";
    //날짜의 형식을 정하기 위한 값
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm");

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);

        if(messages.length == 0) {
            return;
        }

        //문자를 보낸 사람의 전화번호를 가져온다.
        String sender = messages[0].getOriginatingAddress();
        Log.d(TAG, "sender: "+sender);
        //문자의 내용을 가져온다.
        String contents = messages[0].getMessageBody().toString();
        Log.d(TAG, "contents: "+contents);
        //문자를 받은 날짜를 받는다.
        String receivedDate = new SimpleDateFormat().format(new Date(messages[0].getTimestampMillis()));
        Log.d(TAG, "received date: "+receivedDate);
        //인텐트로 보낸다.
        //sendToActivity(context, sender+'\n'+contents+'\n'+format.format(receivedDate));
        Log.d("notification", contents);

//        Intent sendIntent = new Intent(context, MainActivity.class);
//        sendIntent.putExtra("string",contents);
//        ///////////////////////////////////////////////////////
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, sendIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, "10001");
//        notificationBuilder.setContentTitle(sender).setContentText(sender+contents).setContentIntent(pendingIntent).setAutoCancel(true);
//
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//
//            CharSequence charSequence = sender;
//            String description = contents;
//            int importnace = NotificationManager.IMPORTANCE_HIGH;
//
//            NotificationChannel channel = new NotificationChannel("10001", charSequence, importnace);
//            channel.setDescription(description);
//
//            assert notificationManager != null;
//            notificationManager.createNotificationChannel(channel);
//
//        } else {
//
//            notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
//
//        }
//
//        assert notificationManager != null;
//        notificationManager.notify(1234, notificationBuilder.build());

        Intent sendIntent = new Intent(context, MainActivity.class);
        sendIntent.putExtra("date", receivedDate);
        sendIntent.putExtra("sender", sender);
        sendIntent.putExtra("contents", contents);
        sendIntent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
//        stackBuilder.addParentStack( MainActivity.class );
//        stackBuilder.addNextIntent(sendIntent);

        //notification 을 위한 pendingIntent를 만든다.                        이 값은 꼭 기억하자
        //PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, sendIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        PendingIntent pendingIntent = TaskStackBuilder.create(context).addNextIntentWithParentStack(sendIntent).getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        //notificationManager 를 만든다.
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //notification 화면 구성을 위해 빌더를 만든다.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notify_001");

        //빌더 세팅
        builder.setContentIntent(pendingIntent);
        //이 부분도 버전에 따라 다르다... mipmap과 drawable 두개중 하나를 써야한다...
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(sender);
        builder.setContentText(contents);
        builder.setPriority(Notification.PRIORITY_MAX);

        //버전을 물어본다. 버전에 따라 사용하는것이 다르다 26 이상은 notificationchannel 이 필요하다.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //notification의 중요도를 설정
            int importance = NotificationManager.IMPORTANCE_HIGH;
            //채널을 만든다.                                                    이 아이디 값은 꼭 기억하자.
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_ID, "NOTIFICATION_CHNNEL_NAME", importance);
            //assert 등록하는 부분
            assert notificationManager != null;
            //채널을 생성후 채널을 세팅한다.
            notificationManager.createNotificationChannel(notificationChannel);
            //채널의 아이디를 생성한다. 아마 이 부분이 없어서 안됐던거 같다. 채널만 넣어주고 아이디를 세팅을 안해서 그런지... 아닌지...
            builder.setChannelId(NOTIFICATION_ID);

        }

        assert notificationManager != null;
        //빌더로 notify 시켜준다.
        notificationManager.notify(0, builder.build());

    }

//    private void sendToActivity(Context context, String string) {
//
//        Log.d("notification", string);
//
//        Intent intent = new Intent(context, MainActivity.class);
//
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//        intent.putExtra("string", string);
//        context.startActivity(intent);
//
//    }

    //이미 형식이 정해져 있다. 그대로 쓰면 된다.
    private SmsMessage[] parseSmsMessage(Bundle bundle){

        //번들에서 pdus 라는 키로 값이 넘어오는거 같다.
        Object[] objs = (Object[])bundle.get("pdus");
        //번들에 있는 내용을 담을 변수이다.
        SmsMessage[] messages = new SmsMessage[objs.length];

        for(int i=0;i<objs.length;i++){
            //버전 체크를 통해 형식에 맞게 변환해서 값을 저장한다.
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                String format = bundle.getString("format");
                //pdu 라는 폼이 있다. 검색하면 잘 나온당 검색 고고
                messages[i] = SmsMessage.createFromPdu((byte[])objs[i], format);

            }

            else{

                messages[i] = SmsMessage.createFromPdu((byte[])objs[i]);

            }

        }

        return messages;
    }
}
