package superSendFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class acceptFile {
    public static ServerSocket serverSocket;

    static {
        try {
            serverSocket = new ServerSocket(12345);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Scanner input=new Scanner(System.in);
    public static String myLocalFil;
    public static String suffixName;
    public static void main(String[] args) {
        try {
            menu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void menu() throws Exception {
        acceptFile(serverSocket);
    }
    public static void acceptFile(ServerSocket serverSocket)throws Exception{
        System.out.println("等待连接");
        Socket socket = serverSocket.accept();
        System.out.println("连接成功！");
        InputStream is = socket.getInputStream();
        System.out.println("文件保持地址:");
        myLocalFil=input.next();
        System.out.println("请输入后缀名   比如.mp4  .mp3  .exe");
        suffixName=input.next();
        FileOutputStream fos=new FileOutputStream(myLocalFil+suffixName);
        System.out.println("开始接收:");
        int len=0;
        byte[] b=new byte[1024];
        while ((len=is.read(b))!=-1){
            fos.write(b,0,len);
        }
        System.out.println("接收完成");
        cyclicOperation();
    }
    public static void cyclicOperation() throws Exception {
        System.out.println("您是否要退出 确定退出输入Y，不退出输入N");
        char c = input.next().toUpperCase().charAt(0);
        if(c=='N'){
            menu();
        } else if (c=='Y') {

        }
    }

}
