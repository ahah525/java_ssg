package com.ll.exam;

public class Rq {
    private String url;     // path + query 문
    private String path;
    private String queryStr;

    public Rq(String url) {
        this.url =  url;
        String[] urlBits = url.split("\\?", 2);// ? 기준으로 2개로 분리
        this.path = urlBits[0]; // path
        // query 문이 있으면 저장
        if(urlBits.length == 2) {
            this.queryStr = urlBits[1];
        }
    }

    // url의 파라미터에 해당하는 값 반환
    public int getIntParam(String paramName, int defaultValue) {
        // query문이 null이면 디폴트값 반환
        if(queryStr == null) return defaultValue;

        String[] queryBits = queryStr.split("&");// &기준으로 분리
        for(String queryBit : queryBits) {
            String[] param = queryBit.split("=", 2);// = 기준으로 2개로 분리
            String name = param[0];     // 파라미터명
            String value = param[1];    // 값
            // 해당 명령어에 대한 값 반환
            if(paramName.equals(name)) {
                return Integer.parseInt(value);
            }
        }
        return defaultValue;
    }

    public String getPath() {
        return path;
    }
}
