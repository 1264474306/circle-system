package com.iswust.util;

import java.io.*;
import java.util.ArrayList;

public class ReaderWriteUtil {

    public static ArrayList<String> readFile(String filepath) throws IOException {
//        FileInputStream fis=new FileInputStream("E:/phsftp/evdokey/evdokey_201103221556.txt");
        FileInputStream fis=new FileInputStream(filepath);
        InputStreamReader isr=new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        //简写如下
        //BufferedReader br = new BufferedReader(new InputStreamReader(
        //        new FileInputStream("E:/phsftp/evdokey/evdokey_201103221556.txt"), "UTF-8"));
        String line="";
        ArrayList<String> arrs = new ArrayList<String>();
        while ((line=br.readLine())!=null) {
            arrs.add(line);
        }
        br.close();
        isr.close();
        fis.close();
        return arrs;
    }


    public static   void writeFile(ArrayList<String> arrs, String filepath) throws IOException {

        //写入中文字符时解决中文乱码问题
        FileOutputStream fos=new FileOutputStream(new File(filepath));
        OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter  bw=new BufferedWriter(osw);
        //简写如下：
        //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
        //        new FileOutputStream(new File("E:/phsftp/evdokey/evdokey_201103221556.txt")), "UTF-8"));

        for(String arr:arrs){
            bw.write(arr+"\t\n");
        }

        //注意关闭的先后顺序，先打开的后关闭，后打开的先关闭
        bw.close();
        osw.close();
        fos.close();
    }
}
