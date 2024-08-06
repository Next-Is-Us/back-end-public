package com.nextisus.project.util.format;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class CreateAtFormat {

    /**
     * n분 전, n일 전, n주일 전, n달 전, n년 전
     */
    public static String formatToRelativeTime(LocalDateTime createAt) {
        LocalDateTime now = LocalDateTime.now();
        long years = ChronoUnit.YEARS.between(createAt, now);
        long months = ChronoUnit.MONTHS.between(createAt, now);
        long weeks = ChronoUnit.WEEKS.between(createAt, now);
        long days = ChronoUnit.DAYS.between(createAt, now);
        long hours = ChronoUnit.HOURS.between(createAt, now);
        long minutes = ChronoUnit.MINUTES.between(createAt, now);

        if (years > 0) {
            return years + "년 전";
        } else if (months > 0) {
            return months + "달 전";
        } else if (weeks > 0) {
            return weeks + "주일 전";
        } else if (days > 0) {
            return days + "일 전";
        } else if (hours > 0) {
            return hours + "시간 전";
        } else if (minutes > 0) {
            return minutes + "분 전";
        } else {
            return "방금 전";
        }
    }

    /**
     * 2024.03.11 형태로
     */
    public static String formatToDate(LocalDateTime createAt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        return createAt.format(formatter);
    }
}
