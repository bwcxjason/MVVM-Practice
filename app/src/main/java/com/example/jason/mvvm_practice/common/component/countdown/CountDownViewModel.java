package com.example.jason.mvvm_practice.common.component.countdown;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.os.CountDownTimer;

/**
 * Created by Administrator on 2018/5/15.
 */

public class CountDownViewModel extends ViewModel {

    public ObservableField<String> text = new ObservableField<>();

    public ObservableField<Boolean> isBtnEnabled = new ObservableField<>(true);

    private int millisInFuture = 60000;
    private int countDownInterval = 1000;

    private TimeCount timer;

    public void start() {
        timer = new TimeCount(millisInFuture, countDownInterval);
        timer.start();
    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            text.set(millisUntilFinished / 1000 + "秒后可重新发送");
            isBtnEnabled.set(false);
        }

        @Override
        public void onFinish() {
            isBtnEnabled.set(true);
            millisInFuture = 60000;
        }
    }

}
