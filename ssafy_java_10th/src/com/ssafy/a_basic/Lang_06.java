package com.ssafy.a_basic;

public class Lang_06 {
    public static void main(String[] args) {
        int num = 3;

        switch (num) {
        case 1:
            System.out.println(num);
        case 2:
            System.out.println(num);
        case 3:
            System.out.println(num);
        case 4:
            System.out.println(num);
        case 5:
            break;
        case 6:
            break;
        default:
            System.out.println(num);
        }

        int month = 3;
        int day = -1;
        // TODO: 월별 날의 수가 정확히 출력되도록 수정해보자.
        switch (month) {
        case 2:
            day = 29;
        case 4:
        case 6:
        case 9:
        case 11:
            day = 30;
        default:
            day = 31;
        }
        System.out.printf("%d월은 %d까지 있다.%n", month, day);
    }
}
