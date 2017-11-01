package com.Birthday;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Birthday
{

    public static java.util.Scanner input_Birthday;

    public static void main(String[] args)
    {
        String Titel = "验证出生日期";
        System.out.println(Titel);

        input_Birthday = new java.util.Scanner(System.in);
        String birthday = input_Birthday.nextLine();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");

        df.setLenient(false);
        try
        {
            df.parse(birthday);
            System.out.println("时间合法");
        }
        catch (ParseException e)
        {
            System.out.println("时间不合法");
            e.printStackTrace();
        }
    }

}
