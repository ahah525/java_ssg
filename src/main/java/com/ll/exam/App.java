package com.ll.exam;

import com.ll.exam.controller.WiseSayingController;

import java.util.Scanner;

public class App {
    private Scanner sc;

    public App() {
        sc = new Scanner(System.in);
    }

    public void run() {
        // WiseSayingController 객체 생성
        WiseSayingController wiseSayingController = new WiseSayingController(sc);

        System.out.println("== 명언 SSG ==");

        outer:
        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();  // 앞뒤 공백 제거
            Rq rq = new Rq(cmd);    // 명령어
            switch (rq.getPath()) {
                case "등록":
                    wiseSayingController.write(rq);
                    break;
                case "목록":
                    wiseSayingController.list(rq);
                    break;
                case "삭제":
                    wiseSayingController.remove(rq);
                    break;
                case "수정":
                    wiseSayingController.modify(rq);
                    break;
                case "종료":
                    break outer; // outer가 붙은 반복문을 break
            }
        }
        sc.close();
    }


}
