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

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {

    public static String NOTIFICATION_ID = "notification_id";
    public static String NOTIFICATION = "notification";

    //디버그를 위한 값
    private static final String TAG = "SmsReceiver";
    private static final String NOTI_TAG = "notification";
    //날짜의 형식을 정하기 위한 값
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

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
        Date receivedDate = new Date(messages[0].getTimestampMillis());
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

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = intent.getParcelableExtra(NOTIFICATION);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_ID, "NOTIFICATION_CHNNEL_NAME", importance);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);
        }
        int id = intent.getIntExtra(NOTIFICATION, 0);
        assert notificationManager != null;
        notificationManager.notify(id, notification);

    }

    private void sendToActivity(Context context, String string) {

        Log.d("notification", string);

        Intent intent = new Intent(context, MainActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.putExtra("string", string);
        context.startActivity(intent);

    }

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
