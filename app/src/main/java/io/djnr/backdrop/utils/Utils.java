package io.djnr.backdrop.utils;

import java.util.concurrent.TimeUnit;

/**
 * Created by Dj on 9/29/2016.
 */
public class Utils {

    public static String largeSCImg(String imgUrl){
        return imgUrl.replace("large.jpg", "t500x500.jpg");
    }

    public static String getMinutesFormat(int time){
        return String.format("%d:%02d", TimeUnit.MILLISECONDS.toMinutes((long) time),
                TimeUnit.MILLISECONDS.toSeconds((long) time) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) time)));
    }
}
