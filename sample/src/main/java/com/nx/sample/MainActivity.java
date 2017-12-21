package com.nx.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.nx.logger.androidLog.AndroidLogAdapter;
import com.nx.logger.diskLog.DiskLogAdapter;
import com.nx.logger.androidBase.FormatInterface;
import com.nx.logger.Logger;
import com.nx.logger.androidLog.AndroidLogFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    Log.d("Tag", "I'm a log which you don't see easily, hehe");
    Log.d("json content", "{ \"key\": 3, \n \"value\": something}");
    Log.d("error", "There is a crash somewhere or any warning");

    Logger.init("nx",3, Logger.DEBUG,true);
////    Logger.addLogAdapter(new AndroidLogAdapter());
    Logger.d("开始");
//    Logger.methodCount(1).w("no thread info and only 1 methodCount");
////
////    Logger.clearLogAdapters();
////
////
////    FormatInterface formatInterface = AndroidLogFormatter.newBuilder()
////            .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
////            .methodCount(0)         // (Optional) How many methodCount line to show. Default 2
////            .methodOffset(3)        // (Optional) Skips some methodCount invokes in stack trace. Default 5
//////        .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
////            .tag("My custom tag")   // (Optional) Custom tag for each log. Default PRETTY_LOGGER
////            .build();
////
////    Logger.addLogAdapter(new AndroidLogAdapter(formatInterface));
////
////    Logger.addLogAdapter(new AndroidLogAdapter() {
////      @Override public boolean isLoggable(int priority, String tag) {
////        return BuildConfig.DEBUG;
////      }
////    });
////
////    Logger.addLogAdapter(new DiskLogAdapter());
//
//
//
//
////    Logger.clearLogAdapters();
////    formatInterface = AndroidLogFormatter.newBuilder()
////        .showThreadInfo(false)
////        .methodCount(0)
////        .build();
////
////    Logger.addLogAdapter(new AndroidLogAdapter(formatInterface));
//    Logger.i("no thread info and methodCount info");
//
//    Logger.tag("mytag").e("Custom tag for only one use");

    Logger.json("{ \"key\": 3, \"value\": something}");
    List<String> list = new ArrayList<>();
    list.add("foo");
    Logger.object(list);
    list.add("bar");
    Logger.object(list);


    Map<String, String> map = new HashMap<>();
    map.put("key", "value");
    map.put("key1", "value2");

    Logger.object(map);
    map.put("key2", "value3");
    Logger.object(map);

//    Logger.clearLogAdapters();
//    formatInterface = AndroidLogFormatter.newBuilder()
//        .showThreadInfo(false)
//        .methodCount(0)
//        .tag("MyTag")
//        .build();
//    Logger.addLogAdapter(new AndroidLogAdapter(formatInterface));



    Logger.append("xiaogou: ");
    Logger.appendJson("{ \"key\": 3, \"value\": something}");
    Logger.appendXml("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<RequestAgent>\n" +
            "    <Rsdc>1</Rsdc>\n" +
            "    <Type>dxcx_qg_czrk</Type>\n" +
            "    <Items>\n" +
            "        <Values>\n" +
            "            <Row>\n" +
            "                <Data>c_xm</Data>\n" +
            "                <Data>c_sfzh</Data>\n" +
            "                <Data>c_mz</Data>\n" +
            "                <Data>c_xb</Data>\n" +
            "                <Data>c_zzxz</Data>\n" +
            "                <Data>c_bdrq</Data>\n" +
            "                <Data>c_bdyy</Data>\n" +
            "                <Data>c_csd</Data>\n" +
            "                <Data>c_csdgj</Data>\n" +
            "                <Data>c_csdxz</Data>\n" +
            "                <Data>c_csrq</Data>\n" +
            "                <Data>c_cym</Data>\n" +
            "                <Data>c_fwcs</Data>\n" +
            "                <Data>c_gmsfhm</Data>\n" +
            "                <Data>c_hdqr</Data>\n" +
            "                <Data>c_hh</Data>\n" +
            "                <Data>c_hkszd</Data>\n" +
            "                <Data>c_hlx</Data>\n" +
            "                <Data>c_hyzk</Data>\n" +
            "                <Data>c_jggj</Data>\n" +
            "                <Data>c_jgssx</Data>\n" +
            "                <Data>c_qtzzssx</Data>\n" +
            "                <Data>c_qtzzxz</Data>\n" +
            "                <Data>c_ssgajgjgdm</Data>\n" +
            "                <Data>c_ssgajgmc</Data>\n" +
            "                <Data>c_sspcsjgdm</Data>\n" +
            "                <Data>c_sspcsmc</Data>\n" +
            "                <Data>c_ssssxq_dm</Data>\n" +
            "                <Data>c_whcd</Data>\n" +
            "                <Data>c_yhzgx</Data>\n" +
            "                <Data>c_zy</Data>\n" +
            "                <Data>c_zzssx</Data>\n" +
            "                <Data>c_zzssxq</Data>\n" +
            "                <Data>n_sg</Data>\n" +
            "                <Data>m_zp</Data>\n" +
            "            </Row>\n" +
            "            <Row>\n" +
            "                <Data>姓名</Data>\n" +
            "                <Data>身份证号</Data>\n" +
            "                <Data>民族</Data>\n" +
            "                <Data>性别</Data>\n" +
            "                <Data>住址详址</Data>\n" +
            "                <Data>变动日期(格式为YYYYMMDD</Data>\n" +
            "                <Data>变动原因</Data>\n" +
            "                <Data>出生地</Data>\n" +
            "                <Data>出生地国籍</Data>\n" +
            "                <Data>出生地详址</Data>\n" +
            "                <Data>出生日期(格式为YYYYMMDD</Data>\n" +
            "                <Data>曾用名</Data>\n" +
            "                <Data>服务处所</Data>\n" +
            "                <Data>公民身份号码</Data>\n" +
            "                <Data>何地迁入</Data>\n" +
            "                <Data>户号 (行政区划6 位+派出所编</Data>\n" +
            "                <Data>户口所在地</Data>\n" +
            "                <Data>户类型 (1、家庭户，2、代表集</Data>\n" +
            "                <Data>婚姻状况</Data>\n" +
            "                <Data>籍贯国籍</Data>\n" +
            "                <Data>籍贯省市县区</Data>\n" +
            "                <Data>其他住址省市县 (区)</Data>\n" +
            "                <Data>其他住址详址</Data>\n" +
            "                <Data>所属公安机关机构代码</Data>\n" +
            "                <Data>所属公安机关名称</Data>\n" +
            "                <Data>所属派出所机构代码</Data>\n" +
            "                <Data>所属派出所名称</Data>\n" +
            "                <Data>所属省市县区代码</Data>\n" +
            "                <Data>文化程度</Data>\n" +
            "                <Data>与户主关系</Data>\n" +
            "                <Data>职业</Data>\n" +
            "                <Data>住址省市县</Data>\n" +
            "                <Data>住址省市县(区)</Data>\n" +
            "                <Data>身高(单位：厘米)</Data>\n" +
            "                <Data>相片</Data>\n" +
            "            </Row>\n" +
            "            <Row>\n" +
            "                <Data IsCode=\"0\" CodeValue=\"\">李刚</Data>\n" +
            "                <Data IsCode=\"0\" CodeValue=\"\">152527198607060316</Data>\n" +
            "                <Data IsCode=\"1\" CodeValue=\"01\">汉族</Data>\n" +
            "                <Data IsCode=\"1\" CodeValue=\"1\">男</Data>\n" +
            "                <Data IsCode=\"0\" CodeValue=\"\">宝昌镇建设南街西95号</Data>\n" +
            "                <Data IsCode=\"0\" CodeValue=\"\" />\n" +
            "                <Data IsCode=\"0\" CodeValue=\"\" />\n" +
            "                <Data IsCode=\"1\" CodeValue=\"152527\">内蒙古自治区太仆寺旗</Data>\n" +
            "                <Data IsCode=\"1\" CodeValue=\"156\">中华人民共和国</Data>\n" +
            "                <Data IsCode=\"0\" CodeValue=\"\" />\n" +
            "                <Data IsCode=\"0\" CodeValue=\"\">19860706</Data>\n" +
            "                <Data IsCode=\"0\" CodeValue=\"\">李树刚</Data>\n" +
            "                <Data IsCode=\"0\" CodeValue=\"\" />\n" +
            "                <Data IsCode=\"0\" CodeValue=\"\" />\n" +
            "                <Data IsCode=\"0\" CodeValue=\"\" />\n" +
            "                <Data IsCode=\"0\" CodeValue=\"\">000005014</Data>\n" +
            "                <Data IsCode=\"1\" CodeValue=\"152527\">内蒙古自治区太仆寺旗</Data>\n" +
            "                <Data IsCode=\"0\" CodeValue=\"\" />\n" +
            "                <Data IsCode=\"1\" CodeValue=\"10\">未婚</Data>\n" +
            "                <Data IsCode=\"1\" CodeValue=\"156\">中华人民共和国</Data>\n" +
            "                <Data IsCode=\"1\" CodeValue=\"132532\">132532</Data>\n" +
            "                <Data IsCode=\"0\" CodeValue=\"\" />\n" +
            "                <Data IsCode=\"0\" CodeValue=\"\" />\n" +
            "                <Data IsCode=\"0\" CodeValue=\"\" />\n" +
            "                <Data IsCode=\"0\" CodeValue=\"\" />\n" +
            "                <Data IsCode=\"0\" CodeValue=\"\" />\n" +
            "                <Data IsCode=\"0\" CodeValue=\"\" />\n" +
            "                <Data IsCode=\"0\" CodeValue=\"\" />\n" +
            "                <Data IsCode=\"1\" CodeValue=\"60\">高中</Data>\n" +
            "                <Data IsCode=\"0\" CodeValue=\"\" />\n" +
            "                <Data IsCode=\"0\" CodeValue=\"\" />\n" +
            "                <Data IsCode=\"0\" CodeValue=\"\" />\n" +
            "                <Data IsCode=\"0\" CodeValue=\"\" />\n" +
            "                <Data IsCode=\"0\" CodeValue=\"\" />\n" +
            "                <Data IsCode=\"0\" CodeValue=\"\">/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0a HBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIy MjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAGIAUADASIA AhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQA AAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3 ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWm p6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEA AwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSEx BhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElK U1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmq</Data>\n" +
            "            </Row>\n" +
            "        </Values>\n" +
            "    </Items>\n" +
            "</RequestAgent>");

    Logger.object(map);
    Logger.d("结束！");
  }
}
