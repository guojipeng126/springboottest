package com.gjp.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskTest {

    public static void main(String[] args) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("main程序开始时间: " + simpleDateFormat.format(new Date()));

        Date date = simpleDateFormat.parse("2024-06-13 11:58:06");      //执行开始的时间，如果该时间是过去的时间，定时任务会立即执行；
        Long interVal = 2 * 1000L;       //每隔1分钟执行一次

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.println("定时任务开始时间: " + simpleDateFormat.format(new Date()));
                    System.out.println("定时任务程序");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (false) {          // 满足条件的时候，介绍定时任务
                    this.cancel();
                }
            }
        }, date ,interVal);

        System.out.println("main程序结束");
    }
}
