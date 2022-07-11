package com.ll.exam;

public class WiseSaying {
    int id;  // 명언글 번호
    String content; // 명언
    String author;   // 작가명

    public WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    @Override
    public String toString() {
        return "WiseSaying{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

}
