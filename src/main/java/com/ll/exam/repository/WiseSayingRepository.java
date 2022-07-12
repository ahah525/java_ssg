package com.ll.exam.repository;

import com.ll.exam.WiseSaying;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    public List<WiseSaying> wiseSayings;   // 명언을 저장하기 위한 리스트
    public int wiseSayingLastId;           // 가장 마지막 명언글의 번호
    public WiseSayingRepository() {
        this.wiseSayings = new ArrayList<>();
        this.wiseSayingLastId = 0;  // 0으로 초기화
    }

    public WiseSaying findById(int paramId) {
        for(WiseSaying ws : wiseSayings) {
            // 명언 ArrayList에 해당 id가 있으면 반환
            if(ws.getId() == paramId) {
                return ws;
            }
        }
        return null;
    }
}
