package com.example.smsreceivertest;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SMSReceiverService extends BroadcastReceiver {

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        if (intent.getAction().equals(SMS_RECEIVED)) {
            Bundle bundle = intent.getExtras();

            String senderNumber = "";
            String messageBody = "";
            String date = "";

            if (bundle != null) {

                SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);

                for (int i = 0; i < messages.length; i++) {
                    SmsMessage message = messages[i];
                    senderNumber = message.getDisplayOriginatingAddress();
                    messageBody = message.getDisplayMessageBody();
                    date = getDate(message.getTimestampMillis());
                }

                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                        .setAutoCancel(true)
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setContentTitle("New message from " + senderNumber)
                        .setContentText(messageBody)
                        .setSubText(date)
                        .setSmallIcon(R.drawable.ic_launcher_background);

                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(002, mBuilder.build());
            }
        }
    }

    private String getDate(long timestampMillis) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy hh:mm a");
            Date date = new Date(timestampMillis);
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
