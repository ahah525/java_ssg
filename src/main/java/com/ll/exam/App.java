package com.ll.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public void run() {
        Scanner sc = new Scanner(System.in);
        List<WiseSaying> wiseSayings = new ArrayList<>();   // 명언을 저장하기 위한 리스트
        int wiseSayingLastId = 0;  // 가장 마지막 명언글의 번호

        System.out.println("== 명언 SSG ==");
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
                    int id = ++wiseSayingLastId;   // 명언 글 번호 1 증가
                    // WiseSaying 객체 생성후 리스트에 저장
                    WiseSaying wiseSaying = new WiseSaying(id, content, author);
                    wiseSayings.add(wiseSaying);
                    System.out.printf("%d번 명언이 입력되었습니다.\n", id);
                    //System.out.println(wiseSaying);
                    break;
                case "목록":
                    System.out.println("번호 / 작가 / 명언");
                    System.out.println("-----------------");
                    // 역순으로 출력(최신순)
                    for (int i = wiseSayings.size() - 1; i >= 0; i--) {
                        WiseSaying ws = wiseSayings.get(i);
                        System.out.printf("%d / %s / %s\n", ws.id, ws.author, ws.content);
                    }
                    break;
                case "종료":
                    break outer; // outer가 붙은 반복문을 break
            }
        }
        sc.close();
    }
}
