package com.pringstudio.smsclient.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.RequiresApi;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.pringstudio.smsclient.events.NewMessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class SMSReceiverService extends BroadcastReceiver {

    private static final String TAG = "MESSAGE";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {

        // Incoming Message
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            Log.d("SMSReceiverService","Incoming SMS tut...tut..tut...");

            Bundle bundle = intent.getExtras();
            SmsMessage[] msgs = null;
            String msgFrom = "";
            String strMessage = "";
            String format = bundle.getString("format");
            boolean isVersion = false;
            if (bundle != null){

                try{
                    Object[] pdus = (Object[]) bundle.get("pdus");

                    if(pdus != null) {
                        isVersion = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
                    }
                    msgs = new SmsMessage[pdus.length];

                    for(int i=0; i<msgs.length; i++){
                        if(isVersion) {
                            msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i], format);

                        } else {
                            msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                        }

//                        Log.d("Incoming Message", "SMS From " + msgFrom + ", Content: "+ msgPart);

                        strMessage += "SMS from : " + msgs[i].getOriginatingAddress();
                        strMessage += " : " + msgs[i].getMessageBody() + "\n";

                        Log.d(TAG, "onReceive : " + strMessage);
                        Toast.makeText(context, strMessage, Toast.LENGTH_LONG).show();
                    }
                    // Dispatch Event
                    EventBus.getDefault().post(new NewMessageEvent(msgFrom, format);

                    Log.d("Wrapped message", "Wrapped Message from "+msgFrom+" is: "+ format);

//                    this.abortBroadcast();
                }catch(Exception e){
                    Log.d("Exception caught",e.getMessage());
                }
            }
        }else{
            Log.d("SMS Receiver", "Intent is not sms"+intent.getAction());
        }
    }
}
