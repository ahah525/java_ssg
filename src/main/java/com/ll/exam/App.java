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

            Rq rq = new Rq(cmd);    // 명령어
            switch (rq.getPath()) {
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
                case "삭제":
                    int deleteId = rq.getIntParam("id", 0);  // id 파라미터 값
                    // 0이 반환되었다는 것은 url에 id가 입력되지 았으므로 다시 입력받기
                    if(deleteId == 0) {
                        System.out.println("id를 입력해주세요.");
                        continue;
                    }
                    boolean isRemove = false;     // 삭제할 명언
                    for(WiseSaying ws : wiseSayings) {
                        // 명언 ArrayList에 해당 id가 있으면 삭제
                        if(ws.id == deleteId) {
                            wiseSayings.remove(ws); // 명언 삭제
                            System.out.printf("%d번 명언이 삭제되었습니다.\n", deleteId);
                            isRemove = true;
                            break;
                        }
                    }
                    // 명언을 삭제하지 않았으면
                    if(!isRemove) {
                        System.out.printf("%d번 명언은 존재하지 않습니다..\n", deleteId);
                    }
                    break;
                case "수정":
                    int updateId = rq.getIntParam("id", 0);  // id 파라미터 값
                    // 0이 반환되었다는 것은 url에 id가 입력되지 았으므로 다시 입력받기
                    if(updateId == 0) {
                        System.out.println("id를 입력해주세요.");
                        continue;
                    }
                    boolean isUpdate = false;     // 수정할 명언
                    for(WiseSaying ws : wiseSayings) {
                        // 명언 ArrayList에 해당 id가 있으면 수정
                        if(ws.id == updateId) {
                            System.out.printf("%d번 명언을 수정합니다.\n", updateId);
                            System.out.printf("기존 명언 : %s\n", ws.content);
                            System.out.print("새 명언 : ");
                            // 명언 수정
                            ws.content = sc.nextLine();
                            System.out.printf("%d번 명언이 수정되었습니다.\n", updateId);
                            isUpdate = true;
                            break;
                        }
                    }
                    // 해당 id가 없으면
                    if(!isUpdate) {
                        System.out.printf("%d번 명언은 존재하지 않습니다..\n", updateId);
                    }
                    break;
                case "종료":
                    break outer; // outer가 붙은 반복문을 break
            }
        }
        sc.close();
    }
}
