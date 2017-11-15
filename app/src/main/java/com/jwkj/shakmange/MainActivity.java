package com.jwkj.shakmange;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jwkj.shakmanger.LocalDevice;
import com.jwkj.shakmanger.ShakeListener;
import com.jwkj.shakmanger.ShakeManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvConsole;
    private List<LocalDevice> devices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvConsole = (TextView) findViewById(R.id.tv_console);

    }

    public void onShak(View view) {
        ShakeManager.getInstance()
                .setSearchTime(5000)//设置搜索时间（时间的毫秒值），默认10s
//                .schedule(2, 3000)//（默认只会执行一次）执行两次扫描任务，间隔3s执行（上一次执行结束到下一次开始之间的时间）
                .shaking(new ShakeListener() {//开始搜索，并回调
                    @Override
                    public void onStart() {
                        Log.e("hdltag", "onStart(MainActivity.java:37):开始了。。。。");
                    }

                    @Override
                    public void onNext(LocalDevice device) {
                        Log.e("hdltag", "onNext(MainActivity.java:37):" + device);
                        //去重处理
                        boolean isExisted = false;
                        if (devices != null && devices.size() > 0) {
                            for (LocalDevice localDevice : devices) {
                                if (localDevice.getId().equals(device.getId())) {
                                    isExisted = true;
                                    break;
                                }
                            }
                        }
                        if (!isExisted) {
                            devices.add(device);
                            Collections.sort(devices);
                            for (LocalDevice localDevice : devices) {
                                tvConsole.append(localDevice.toString() + "\n\n");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e("hdltag", "onError(MainActivity.java:54):" + throwable);
                    }

                    @Override
                    public void onCompleted() {
                        Log.e("hdltag", "onCompleted(MainActivity.java:61):完成了");
                    }
                });
    }

}
