package com.ll.exam;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Util {
    // 해당 경로에 내용을 담아 파일로 저장
    public static void saveToFile(String path, String body) {
        try {
            RandomAccessFile stream = new RandomAccessFile(path, "rw");
            FileChannel channel = stream.getChannel();
            byte[] strBytes = body.getBytes();  //
            ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
            buffer.put(strBytes);
            buffer.flip();
            channel.write(buffer);
            stream.close();
            channel.close();
        } catch (IOException e) {
        }
    }
    // 폴더 생성
    public static void mkdir(String path) {
        File dir = new File(path);
        dir.mkdirs();
    }

    // 해당 경로의 파일 내용 리턴
    public static String getFromFile(String path) {
        try {
            RandomAccessFile reader = new RandomAccessFile(path, "r");
            String body = reader.readLine();
            reader.close();

            return body;
        } catch (IOException e) {
        }
        return "";
    }
}
