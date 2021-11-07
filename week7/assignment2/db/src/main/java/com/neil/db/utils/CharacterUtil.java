package com.neil.db.utils;

import java.util.Random;

public class CharacterUtil {

    public static String getRandomString(int length){

        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";

        Random random=new Random();

        StringBuffer sb=new StringBuffer();


        for(int i=0; i < length; i++) {

            int number = random.nextInt(62);


            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static double getRandomDouble(){

        Random random=new Random();

        return random.nextDouble();
    }


    public static int getRandomInt(int bound){

        Random random=new Random();

        return random.nextInt(bound);
    }

    public static void main (String[] args) {
        for (int i = 0; i < 10; i++ ) {
            String result = getRandomString(15);
            System.out.println("random string: " + result);
        }
    }

}

