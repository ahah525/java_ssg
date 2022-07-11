package com.ll.exam;

public class Rq {
    private String url;

    public Rq(String url) {
        this.url =  url;
    }

    // 파라미터에 해당하는 값 반환
    public int getIntParam(String paramName, int defaultValue) {
        String[] urlBits = url.split("\\?", 2); // ? 기준으로 2개로 분리
        urlBits = urlBits[1].split("&"); // &기준으로 분리

        for(String urlBit : urlBits) {
            String[] param = urlBit.split("=", 2);// = 기준으로 2개로 분리
            String name = param[0];     // 파라미터명
            String value = param[1];    // 값
            // 해당 명령어에 대한 값 반환
            if(paramName.equals(name)) {
                return Integer.parseInt(value);
            }
        }
        return defaultValue;
    }
}
