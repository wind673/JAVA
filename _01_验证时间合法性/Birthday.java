package com.Birthday;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Birthday
{

    public static java.util.Scanner input_Birthday;

    public static void main(String[] args)
    {
        String Titel = "��֤��������";
        System.out.println(Titel);

        input_Birthday = new java.util.Scanner(System.in);
        String birthday = input_Birthday.nextLine();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");

        df.setLenient(false);
        try
        {
            df.parse(birthday);
            System.out.println("ʱ��Ϸ�");
        }
        catch (ParseException e)
        {
            System.out.println("ʱ�䲻�Ϸ�");
            e.printStackTrace();
        }
    }

}
