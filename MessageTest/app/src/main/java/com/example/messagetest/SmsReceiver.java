package com.example.messagetest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Bundle bundle = intent.getExtras();
        SmsMessage[] smsMessage = parseSmsMessage(bundle);

        if(smsMessage.length > 0) {

            String sendMessage = smsMessage[0].getDisplayMessageBody();
            String contents = smsMessage[0].getMessageBody().toString();
            Date receiveDate = new Date(smsMessage[0].getTimestampMillis());

            sendToActivity(context, sendMessage, contents, receiveDate);

            Log.d("message", sendMessage);
            Log.d("contents", contents);
            Log.d("receiveDate", receiveDate.toString());
        }

        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void sendToActivity(Context context, String sendMessage, String contents, Date receiveDate) {
        Intent intent = new Intent(context, MainActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.putExtra("sendMessage", sendMessage);
        intent.putExtra("contents", contents);
        intent.putExtra("receiveDate", format.format(receiveDate));

        context.startActivity(intent);
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        Object[] objects = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objects.length];

        for(int i = 0; i <objects.length; i++) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[])objects[i], format);
            }else {
                messages[i] = SmsMessage.createFromPdu((byte[])objects[i]);
            }
        }
        return messages;
    }
}
