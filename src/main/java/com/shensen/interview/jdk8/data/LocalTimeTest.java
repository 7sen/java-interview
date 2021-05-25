package com.shensen.interview.jdk8.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Date-Time API
 * 这是对java.util.Date强有力的补充，解决了 Date 类的大部分痛点：
 *
 * 1、非线程安全
 * 2、时区处理麻烦
 * 3、各种格式化、和时间计算繁琐
 * 4、设计有缺陷，Date 类同时包含日期和时间；还有一个 java.sql.Date，容易混淆。
 *
 * @author Alwyn
 * @date 2021-05-25 07:14
 */
public class LocalTimeTest {

    public static void main(String[] args) {
        // 格式化
        dateTimeFormatter();
        // 字符串转为日期
        dateParse();
    }

    private static void dateParse() {
        LocalDate date = LocalDate.of(2021, 1, 26);
        LocalDate.parse("2021-01-26");

        LocalTime time = LocalTime.of(12, 12, 22);
        LocalTime.parse("12:12:22");

        LocalDateTime dateTime = LocalDateTime.of(2021, 1, 26, 12, 12, 22);
        LocalDateTime.parse("2021-01-26 12:12:22");
    }

    private static void dateTimeFormatter() {
        //format yyyy-MM-dd
        LocalDate date = LocalDate.now();
        System.out.println(String.format("date format : %s", date));

        //format HH:mm:ss
        LocalTime time = LocalTime.now().withNano(0);
        System.out.println(String.format("time format : %s", time));

        //format yyyy-MM-dd HH:mm:ss
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeStr = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(String.format("dateTime format : %s", dateTimeStr.format(dateTime)));
    }
}
