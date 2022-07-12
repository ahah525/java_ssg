package com.ll.exam.controller;

import com.ll.exam.Rq;
import com.ll.exam.WiseSaying;
import com.ll.exam.repository.WiseSayingRepository;

import java.util.Scanner;

// WiseSaying에 관련된 기능을 모두 모음
public class WiseSayingController {
    private Scanner sc;     // 등록, 수정 기능에서 필요함
    private WiseSayingRepository wiseSayingRepository;  // WiseSaying 리퍼지토리

    public WiseSayingController(Scanner sc) {
        this.sc = sc;
        wiseSayingRepository = new WiseSayingRepository();  // WiseSayingRepository 객체 생성 초기화
    }

    public void list(Rq rq) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("-----------------");
        // 역순으로 출력(최신순)
        for (int i = wiseSayingRepository.wiseSayings.size() - 1; i >= 0; i--) {
            WiseSaying ws = wiseSayingRepository.wiseSayings.get(i);
            System.out.printf("%d / %s / %s\n", ws.getId(), ws.getAuthor(), ws.getContent());
        }
    }

    public void write(Rq rq) {
        System.out.print("명언 : ");
        String content = sc.nextLine().trim();   // 명언 입력받기
        System.out.print("작가 : ");
        String author = sc.nextLine().trim();   // 작가 입력받기
        int id = ++wiseSayingRepository.wiseSayingLastId;   // 명언 글 번호 1 증가
        // WiseSaying 객체 생성후 리스트에 저장
        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        wiseSayingRepository.wiseSayings.add(wiseSaying);
        System.out.printf("%d번 명언이 입력되었습니다.\n", id);
    }

    public void modify(Rq rq) {
        int updateId = rq.getIntParam("id", 0);  // id 파라미터 값
        // 0이 반환되었다는 것은 url에 id가 입력되지 았으므로 다시 입력받기
        if(updateId == 0) {
            System.out.println("id를 입력해주세요.");
            return;
        }
        WiseSaying foundWs = wiseSayingRepository.findById(updateId);
        // 수정할 명언 id가 없으면 종료
        if(foundWs == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다..\n", updateId);
            return;
        }
        // 명언, 작가 수정
        System.out.printf("명언(기존) : %s\n", foundWs.getContent());
        System.out.print("명언 : ");
        foundWs.setContent(sc.nextLine());
        System.out.printf("작가(기존) : %s\n", foundWs.getAuthor());
        System.out.print("명언 : ");
        foundWs.setAuthor(sc.nextLine());
        System.out.printf("%d번 명언이 수정되었습니다.\n", updateId);
    }

    public void remove(Rq rq) {
        int deleteId = rq.getIntParam("id", 0);  // id 파라미터 값
        // 0이 반환되었다는 것은 url에 id가 입력되지 았으므로 다시 입력받기
        if(deleteId == 0) {
            System.out.println("id를 입력해주세요.");
            return;
        }
        WiseSaying foundWs = wiseSayingRepository.findById(deleteId); // 해당 id의 명언
        // 삭제할 명언 id가 없으면 종료
        if(foundWs == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다..\n", deleteId);
            return;
        }
        wiseSayingRepository.wiseSayings.remove(foundWs); // 명언 삭제
        System.out.printf("%d번 명언이 삭제되었습니다.\n", deleteId);
    }
}
