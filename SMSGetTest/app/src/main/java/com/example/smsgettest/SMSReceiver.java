package com.example.smsgettest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {

    private final String TAG = "TAG";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Bundle bundle = intent.getExtras();

        String strMessage = "";

        if(bundle != null) {
            Object[] smsExtra = (Object[]) bundle.get("pdus");

            for(int i = 0; i < smsExtra.length; i++) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) smsExtra[i]);

                String strBody = smsMessage.getMessageBody().toString();
                String strSrc = smsMessage.getOriginatingAddress();

                strMessage += "Sms From " + strSrc + " : " + strBody;

                Log.d(TAG, strMessage);
            }
        }
    }
}
