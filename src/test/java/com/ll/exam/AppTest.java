package com.ll.exam;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    @Test
    public void Rq__getIntParam() {
        Rq rq = new Rq("삭제?id=1");
        int id = rq.getIntParam("id", 0);

        assertEquals(1, id);    // id 값이 1이 맞는지 검증
    }

    @Test
    public void Rq__getIntParam__2() {
        Rq rq = new Rq("검색?id=10&no=1");
        int id = rq.getIntParam("id", 0);
        int no = rq.getIntParam("no", 0);

        assertEquals(10, id);    // id 값이 10이 맞는지 검증
        assertEquals(1, no);    // no 값이 1이 맞는지 검증
    }

    @Test
    public void Rq__getPath() {
        Rq rq = new Rq("삭제?id=1");
        String path = rq.getPath();

        assertEquals("삭제", path);   // path가 삭제가 맞는지 검증
    }

    @Test
    public void 테스트_실험() {
        int rs = 10 + 20;
        assertEquals(30, rs);
    }

    @Test
    public void 문자열을_스캐너의_입력으로_설정() {
        // '\n' 대신 엔터 허용
        // stripIndent(): 각 줄의 앞 공백 제거
        // 키보드 입력 자동화
        String input = """
                등록
                명언1
                작가1
                """.stripIndent();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner sc = new Scanner(in);
        // trim(): 앞뒤 공백 제거
        String cmd = sc.nextLine().trim();
        String content = sc.nextLine().trim();
        String author = sc.nextLine().trim();

        assertEquals(cmd, "등록");
        assertEquals(content, "명언1");
        assertEquals(author, "작가1");
    }
    @Test
    public void 표준출력을_리다이렉션하여_결과를_문자열로_받기() throws IOException {
        // 표준출력을 리다이렉션
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        System.out.println("안녕");

        String rs = output.toString().trim();

        // 표준출력을 원상복구
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        output.close();

        assertEquals("안녕", rs);
    }
}
