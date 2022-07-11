package com.ll.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private Scanner sc;
    private List<WiseSaying> wiseSayings;   // 명언을 저장하기 위한 리스트
    private int wiseSayingLastId;           // 가장 마지막 명언글의 번호

    public App() {
        this.sc = new Scanner(System.in);
        this.wiseSayings = new ArrayList<>();
        this.wiseSayingLastId = 0;
    }

    public void run() {
        System.out.println("== 명언 SSG ==");

        outer:
        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();  // 앞뒤 공백 제거
            Rq rq = new Rq(cmd);    // 명령어
            switch (rq.getPath()) {
                case "등록":
                    write(rq);
                    break;
                case "목록":
                    list(rq);
                    break;
                case "삭제":
                    remove(rq);
                    break;
                case "수정":
                    modify(rq);
                    break;
                case "종료":
                    break outer; // outer가 붙은 반복문을 break
            }
        }
        sc.close();
    }

    public void list(Rq rq) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("-----------------");
        // 역순으로 출력(최신순)
        for (int i = wiseSayings.size() - 1; i >= 0; i--) {
            WiseSaying ws = wiseSayings.get(i);
            System.out.printf("%d / %s / %s\n", ws.id, ws.author, ws.content);
        }
    }

    public void write(Rq rq) {
        System.out.print("명언 : ");
        String content = sc.nextLine().trim();   // 명언 입력받기
        System.out.print("작가 : ");
        String author = sc.nextLine().trim();   // 작가 입력받기
        int id = ++wiseSayingLastId;   // 명언 글 번호 1 증가
        // WiseSaying 객체 생성후 리스트에 저장
        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        wiseSayings.add(wiseSaying);
        System.out.printf("%d번 명언이 입력되었습니다.\n", id);
    }

    public void modify(Rq rq) {
        int updateId = rq.getIntParam("id", 0);  // id 파라미터 값
        // 0이 반환되었다는 것은 url에 id가 입력되지 았으므로 다시 입력받기
        if(updateId == 0) {
            System.out.println("id를 입력해주세요.");
            return;
        }
        WiseSaying foundWs = findById(updateId);
        // 수정할 명언 id가 없으면 종료
        if(foundWs == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다..\n", updateId);
            return;
        }
        System.out.printf("%d번 명언을 수정합니다.\n", updateId);
        System.out.printf("기존 명언 : %s\n", foundWs.content);
        System.out.print("새 명언 : ");
        // 명언 수정
        foundWs.content = sc.nextLine();
        System.out.printf("%d번 명언이 수정되었습니다.\n", updateId);
    }

    public void remove(Rq rq) {
        int deleteId = rq.getIntParam("id", 0);  // id 파라미터 값
        // 0이 반환되었다는 것은 url에 id가 입력되지 았으므로 다시 입력받기
        if(deleteId == 0) {
            System.out.println("id를 입력해주세요.");
            return;
        }
        WiseSaying foundWs = findById(deleteId); // 해당 id의 명언
        // 삭제할 명언 id가 없으면 종료
        if(foundWs == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다..\n", deleteId);
            return;
        }
        wiseSayings.remove(foundWs); // 명언 삭제
        System.out.printf("%d번 명언이 삭제되었습니다.\n", deleteId);
    }

    public WiseSaying findById(int paramId) {
        for(WiseSaying ws : wiseSayings) {
            // 명언 ArrayList에 해당 id가 있으면 반환
            if(ws.id == paramId) {
                return ws;
            }
        }
        return null;
    }
}
