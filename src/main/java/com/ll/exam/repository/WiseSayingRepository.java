package com.ll.exam.repository;

import com.ll.exam.WiseSaying;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    private List<WiseSaying> wiseSayings;   // 명언을 저장하기 위한 리스트
    private int wiseSayingLastId;           // 가장 마지막 명언글의 번호
    public WiseSayingRepository() {
        this.wiseSayings = new ArrayList<>();
        this.wiseSayingLastId = 0;  // 0으로 초기화
    }

    // 명언 등록
    public WiseSaying write(String content, String author) {
        // wiseSaying 객체 생성 후 저장
        WiseSaying wiseSaying = new WiseSaying(++wiseSayingLastId, content, author);
        wiseSayings.add(wiseSaying);

        return wiseSaying;  // 저장한 wiseSaying 반환
    }

    // id로 명언 1개 조회
    public WiseSaying findById(int paramId) {
        for(WiseSaying ws : wiseSayings) {
            // 명언 ArrayList에 해당 id가 있으면 반환
            if(ws.getId() == paramId) {
                return ws;
            }
        }
        return null;
    }

    // 모든 명언 조회
    public List<WiseSaying> findAll() {
        return wiseSayings;
    }

    // 명언 수정
    public void modify(int id, String content, String author) {
        WiseSaying wiseSaying = findById(id);
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);
    }

    // 명언 삭제
    public void remove(int id) {
        WiseSaying wiseSaying = findById(id);
        wiseSayings.remove(wiseSaying);
    }

    public int getWiseSayingLastId() {
        return wiseSayingLastId;
    }

    public void setWiseSayingLastId(int wiseSayingLastId) {
        this.wiseSayingLastId = wiseSayingLastId;
    }
}
