package util;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tools {

    public static void pressAnyKey() {
        try {
            System.out.println("請按任一鍵......");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //取得今天日期
    public static String getToday() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        return dtf.format(now);

    }

    /**
     * @param strDate
     * @return "yyyy-MM-dd"
     */
    public static Date strToDate(String strDate) {
        String str = strDate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        java.sql.Date date = new java.sql.Date(d.getTime());
        return date;
    }


}