package com.dhlk.light.factoryconstruction.test;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        Socket socket = null;
        try {
            System.out.println("connecting...");
            socket = new Socket("127.0.0.1", 8003);
            System.out.println("connection success");

            // 输入任意字符发送，输入q退出
            Scanner in = new Scanner(System.in);
            String str = "a5 a5 01 ff ff 01 20 20 50 00 59 62 00 00 00 00 00 00 00 00 ff ff ff ff 12 00 03 92 16 00 00 01 87 00 01 49 e1 00 01 fa fe 01 32 8b 00 5a 5a"; //发送的16进制字符串
            byte[] bytes = hexStringToByteArray(str);
            OutputStream os = socket.getOutputStream();
            while (!(in.nextLine()).equals("q")) { //输入q退出
                os.write(bytes);
            }
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {

                }
            }
        }
    }

    /**
     * 16进制表示的字符串转换为字节数组
     *
     * @param hexString 16进制表示的字符串
     * @return byte[] 字节数组
     */
    public static byte[] hexStringToByteArray(String hexString) {
        hexString = hexString.replaceAll(" ", "");
        int len = hexString.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
            bytes[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character
                    .digit(hexString.charAt(i + 1), 16));
        }
        return bytes;
    }

}
