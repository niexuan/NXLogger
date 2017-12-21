[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Logger-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/1658) [![](https://img.shields.io/badge/AndroidWeekly-%23147-blue.svg)](http://androidweekly.net/issues/issue-147)
[![Join the chat at https://gitter.im/orhanobut/logger](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/orhanobut/logger?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge) <a href="http://www.methodscount.com/?lib=com.orhanobut%3Alogger%3A2.0.0"><img src="https://img.shields.io/badge/Methods and size-198 | 18 KB-e91e63.svg"/></a> [![Build Status](https://travis-ci.org/orhanobut/logger.svg?branch=master)](https://travis-ci.org/orhanobut/logger)

<img align="right" src='https://github.com/orhanobut/logger/blob/master/art/logger-logo.png' width='128' height='128'/>

### Logger
Android log打印，支持打印xml，json和对象，并且格式化输出

### Setup
Download
```groovy
compile 'com.orhanobut:logger:2.1.1'
```
使用方法参考：  https://github.com/niexuan/android-ueueo-log


初始化

UELog共提供了4个初始化方法：

Logger.init("AAA");\n
Logger.init("AAA", 2);\n
Logger.init("AAA", 3, UELogLevel.INFO);\n
Logger.init("AAA", 4, UELogLevel.INFO, true);\n
第一个参数：全局日志打印的Tag（默认为UEUEO）;

第二个参数：打印方法调用栈的数量（默认为 1）；

第三个参数：指定日志打印级别，只有要输出的日志级别大于等于(>=)此参数值，才会打印。 日志级别从低到高分别为： VERBOSE=1,DEBUG=2,INFO=3,WARN=4,ERROR=5,ASSERT=6,NONE=7，当指定为NONE时就不会输出任何日志了；

第四个参数：指定日志是否保存到文件中（默认为false不保存）。 日志文件存储路径为外部存储空间的根目录下 UEUEO文件夹里，日志文件会根据不同的Tag而存储在不同的文件夹中，当程序运行打印第一条日志时会根据当前时间创建日志文件，并且此次运行都存储在此日志文件中，当退出应用重新启动进程，则会创建新的日志文件。

如果不进行任何初始化操作，则所有参数都为默认值。

输出Json，Xml和Java对象

//输出Json字符串
UELog.json("{\"id\":221,\"name\":\"my name is ueueo\",\"desc\":\"this is description!\"}");
//输出Xml字符串
UELog.xml("<?xml version=\"1.0\" encoding=\"UTF-8\"?><html><title>this is a title</title><body>这个是网页</body></html>");

//创建Java对象
User user = new User();
user.id = 102;
user.name = "UEUEO";
user.age = 22;
//输出对象
UELog.object(user);   
             
输出结果分别如下图：



输出打印日志的方法调用栈信息



上图分别为输出1级方法调用栈，输入3级方法调用栈和不输出方法调用栈的日志输出结果。

前两条日志当有方法调用栈输出时，日志信息会通过边框美化输出，而第三条日志因为不需要输出方法调用栈信息，而且日志信息是单行日志，输出时为了不占用控制台输出空间，所以不会添加边框。但是如果输出多行日志则会有边框，例如：

UELog.i("第一行日志 \n 换行输出日志");
输出结果如下：



Exception输出

try {
    Object obj = null;
    obj.toString();
} catch (Exception e) {
    UELog.e(e, "空指针异常");
}
输出结果如下：



输出有不定参数的字符串日志

     UELog.i("指定参数的日志输出  参数1:%d  参数2：%s   参数3：%s", 110, "apple", "ueueo");
输出结果如下：



设置当前要打印日志的Tag，方法调用栈数量和文件存储

上面说明了，当调用UELog的init方法进行初始化时，可以指定日志的Tag等配置信息，这些配置影响的是全局的日志输出，但是有些时候我们可能希望当前要输出的日志与init方法指定的配置不一样，例如：

init时指定Tag为AAA，但是当前的日志希望Tag为BBB，则：

UELog.init("AAA");            
UELog.i("输出的日志Tag是AAA");
UELog.tag("BBB").i("输出的日志Tag是AAA");
UELog.i("再次输出的日志Tag是AAA");
输出结果如下：



除了可以单独指定Tag外，还可以指定方法调用栈显示数量和是否存储到文件：

UELog.tag("BBB").method(3).file(true).i("输出的日志Tag是BBB，显示方法数量为3，并且保存到文件中");
日志的拼接组合输出

为了方便大家理解我所说的日志的拼接组合，我先来举个例子：

当发送网络请求时，需要打印请求的URL、请求参数和返回结果，一般的做法是：

//打印请求地址
UELog.i("POST  http://www.baidu.com/api/gps");
//打印请求参数
UELog.json("{\"id\":221}");
//打印返回结果
UELog.json("{\"name\":\"my name is ueueo\",\"desc\":\"this is description!\"}");
也就是分步打印数据，这样打印出来的结果如下：



这样看其实也挺清楚明白的，但是当网络请求多线程并发时，上面的日志就有可能变成如下：



这时候你还能看出谁是谁的参数，谁是谁的结果吗，肯定是不行的，而日志的拼接组合就是为了解决这个问题，我们先来看看下面的日志输出：



看到这样的输出是不是更加的清楚明白，那这个日志是怎样输出的呢？如下：

//拼接合并输出
UELog.append("POST  http://www.baidu.com/api/gps");
UELog.append("请求参数");
UELog.appendJson("{\"id\":221}");
UELog.append("返回结果");
UELog.json("{\"name\":\"my name is ueueo\",\"desc\":\"this is description!\"}");
UELog提供了append方法，可以对多次要输出的内容进行拼接，然后最后一次行的输出，append方法有：

UELog.append("字符串");//拼接字符串
UELog.appendJson("{\"id\":221}");//拼接Json字符串
UELog.appendXml("<html></html>");//拼接Xml字符串
UELog.appendObject(obj);//拼接对象
也可以这样拼接：

UELog.append("字符串").appendJson("{\"id\":221}").appendXml("<html></html>").appendObject(obj).i("输出");
append方法并不会进行日志输出，只有调用了日志输出方法才会最终输出的控制台，输出方法就是：

UELog.v("verbose level log");
UELog.d("debug level log");
UELog.i("info level log");
UELog.w("warn level log");
UELog.e("error level log");
UELog.wtf("assert level log");
UELog.json("json string log");
UELog.xml("xml string log");
UELog.object(obj);
注意：

其中json，xml和object的输出都是以debug等级输出的；
append方法的调用必须是在同一线程内才有效，所以最好保证你的append方法的调用都是在同一个方法里，而且调用日志输出方法输出日志之后，append拼接的日志将被清空，再次打印的日志将没有之前的拼接信息；

### License
<pre>
Copyright 2017 Orhan Obut

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</pre>
