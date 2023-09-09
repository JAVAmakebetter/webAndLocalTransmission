package superSendFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class sendFile {
    public static String IP="127.0.0.1";
    public static int PORT=12345;
    public static Scanner input=new Scanner(System.in);
    public static Socket socket;
    //public static Socket socket=new Socket(IP,PORT);
    static {
        try {
            socket = new Socket(IP,PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        try {
            menu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String URLGetUserFileName(){
        Scanner input=new Scanner(System.in);
        System.out.println("请输入网址名称:");
        String URLName = input.next();
        return URLName;
    }

    public static void menu() throws Exception{
        System.out.println("请输入您的选择:");
        System.out.println("1.获取网络文件   2.获取这台电脑的本机文件   3.退出系统");

        Integer userChoose = input.nextInt();
        switch (userChoose){
            case 1:
                String urlName = URLGetUserFileName();
                sendURLFile(new URL(urlName));
                cyclicOperation();
            case 2:
                String localFileName = getLocalFileName();
                sendLocalFile(localFileName);
                cyclicOperation();
                break;
            case 3:
                break;
            default:
                System.out.println("您输入错误!!!");
                break;
        }
    }
    public static void sendURLFile(URL url) throws Exception{
        URLConnection urlConnection = url.openConnection();
        HttpURLConnection connection = (HttpURLConnection) urlConnection;
        InputStream is = connection.getInputStream();
        int len=0;
        byte[] b=new byte[1024];
        System.out.println("开始在网站下载资源");
        while ((len=is.read(b))!=-1){
            OutputStream os = socket.getOutputStream();
            os.write(b,0,len);
        }
        socket.shutdownOutput();
        System.out.println("下载结束");
    }
    public static void sendLocalFile(String LocalFile)throws Exception{
        FileInputStream fis=new FileInputStream(LocalFile);
        int len=0;
        byte[] b=new byte[1024];
        System.out.println("开始发送本机资源");
        while ((len=fis.read(b))!=-1){
            OutputStream os = socket.getOutputStream();
            os.write(b,0,len);
        }
        socket.shutdownOutput();
        System.out.println("本机资源发送完毕");
    }
    public static String getLocalFileName(){
        System.out.println("请输入文件地址:");
        return input.next();
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

