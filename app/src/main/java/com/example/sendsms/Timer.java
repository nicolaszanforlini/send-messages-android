package com.example.sendsms;

import android.os.CountDownTimer;
import android.telephony.SmsManager;
import android.util.Log;

public class Timer extends CountDownTimer {

    private final String phone;
    private final String msg;
    private int i = 0;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public Timer(long millisInFuture, long countDownInterval, String phone, String msg) {
        super(millisInFuture, countDownInterval);
        this.phone = phone;
        this.msg = msg;
    }

    @Override
    public void onTick(long millisUntilFinished) {

        for(int j=0;j<=7;j++) {
            SmsManager.getDefault().sendTextMessage(phone, null, msg, null, null);
            Log.i("msg ", String.valueOf(this.i));
            this.i++;
        }
    }

    @Override
    public void onFinish() {
        Log.i("finish", String.valueOf(this.i));
        this.cancel();
    }

}
