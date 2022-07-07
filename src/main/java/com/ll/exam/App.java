package com.ll.exam;

import java.util.Scanner;

public class App {
    public void run() {
        System.out.println("== 명언 SSG ==");

        Scanner sc = new Scanner(System.in);

        outer:
        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();  // 앞뒤 공백 제거

            switch (cmd) {
                case "등록":
                    System.out.print("명언 : ");
                    String content = sc.nextLine().trim();   // 명언 입력받기
                    System.out.print("작가 : ");
                    String author = sc.nextLine().trim();   // 작가 입력받기
                    System.out.println("1번 명언이 입력되었습니다.");
                    break;
                case "종료":
                    break outer ; // outer가 붙은 반복문을 break
            }
        }
        sc.close();
    }
}
