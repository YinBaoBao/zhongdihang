package com.zhongdihang.bankdispatch.common.util;

import java.util.Random;

/**
 * Created by win10 on 2017/08/16.
 */
public class RandomUtil {
    //================================================
    //随机生成min与max之间的随机数
    //================================================
    public static Integer getRandom(int min,int max){
        Random random = new Random();
        int s = random.nextInt(max-min+1) + min;
        return s;
    }

    public static void main(String[] org){
       int s =  RandomUtil.getRandom(1,100);
       System.out.println(s);
    }
}


