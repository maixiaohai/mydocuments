#day 23（4.8）#
======================
##java##

+ 数据存储

	+ 当只有自己写的java程序用到这些数据时，使用序列化；当数据需要被其他程序引用时，写成纯文本文件。

	+ 对象只有实现了序列化的接口后 才能被序列化，使用FileOutputStream链接的ObjectOutputStream来将对象序列化到文件上。

	+ 若某实例变量不能或不应该被序列化，可标记为transient，其在还原的时候返回null或者primitive类型的默认值

	+ 解序列化过程返回的是object类型，需要转换成原类型

	+ 用file建立新目录：

		File dir = new File("chaper3");
	
		dir.mkdir();

	+ **代码大纲是个不错的选择，有助于理清思路**

	+ 使用serialver加类名来查询某个类的serialVersionUID，serialVersionUID对于版本控制很重要，可以将其放入类中，让类在演化过程中维持相同的id

##databank commands##

+ Q1：有关命令行的参数顺序

	A1：顺序对执行没有影响

+ Q2：有关文档中panel需求的region要求

	A2：将region_bak改为region_alt

+ Q3:有关FREQ需求中unit的问题，选用cookie还是ip

	A3:一般用cookie，除非特殊需求

+ Q4：有关两两重合和独占的问题

	A4：参见新版databank中的文档说明，overlap2heunit2，因为原版需求度大于2时，写起来会很复杂

------------------------------------------------------------------
#day 24 (4.9)#
===================
##java##

+ 网络与线程

	+ 客户端读写程序：

		Socket s = new Socket("127.0.01",5000);    //与服务器建立联系，端口号5000

		InputStreamReader stream = new InputStreamReader(s.getInputStream());  //从s取得输入串流

		BufferedReader reader = new BufferedReader(stream);

		String message = reader.readLine();

		PrintWriter writer = new PrintWriter(s.getOutputStream());

		wrtier.println("xxx");

		writer.print("xx");

	+ 简单服务器程序

		ServerSocket serverSock = new ServerSocket(5000); //服务器监听5000端口的客户端请求

		Socket s = new Socket("192.156.2.135", 5000);  //客户端对服务器建立连接

		Socket sock = serverSock.accept();  //服务器创建出与客户端通信的新socket
		
	+ 多线程

		java语言中有内置多线程的功能：

		Runnable threadJob = new MyRunnable();        //建立runnable接口对象，它只有一个方法，public void run()

		Thread t = new Thread(threadJob);                   //建立thread对象并赋值Runnable任务

		t.start();                                                                //启动线程

		当同时有两个或两个以上的线程存取相同的对象时，可能会引发数据损毁，
		此时，使用synchronized这个keyword来防止两个线程同时进入同一对象的同一方法。

-------------------------------------------------------------------------------------------------------
#day 25(4.10)#
=======================
##java##

+ Collections.sort()会把list中的String按照字母排序

+ 关于泛型

	 + 创建被泛型化类的实例：

		new ArrayList<Song>()

	+ 声明与指定泛型类型的变量：

		List<Song> songList = new ArrayList<Song>()

	+ 声明与调用（取用）泛型类型的方法：

		void foo(List<Song> list);
		x.foo(sonList);

+ 泛类比较的例子

import java.util.*;

public class SortMountains {

    LinkedList<Mountain> mtn = new LinkedList<Mountain> ();

    class NameCompare implements Comparator<Mountain> {

	public int compare(Mountain one, Mountain two) {

	    return one.name.compareTo(two.name);
	}
     }
    class HeightCompare implements Comparator<Mountain> {

	public int compare(Mountain one, Mountain two) {

	    return (two.height-one.height);
	}
    }
    public static void main(String [] args) {

	new SortMountains().go();
    }

    public void go() {
	mtn.add(new Mountain("Longs", 14255));
	mtn.add(new Mountain("Elbert", 14433));
	mtn.add(new Mountain("Maroon", 14156));
	mtn.add(new Mountain("Castle", 14265));

	System.out.println("as entered:\n" + mtn);
	NameCompare nc = new NameCompare();
	Collections.sort(mtn, nc);
	System.out.println("by name:\n" + mtn);
	HeightCompare hc = new HeightCompare();
	Collections.sort(mtn, hc);
	System.out.println("by height:\n" + mtn);
    }
}

class Mountain {
    String name;
    int height;

    Mountain(String n, int h){
	name = n;
	height = h;
    }

    public String toString() {
	return name + " " + height;
    }
}


+ Collections的API的主要接口：List、Map、Set

##计算广告学##

+ 品牌广告主要为了提高离线转化率

+ SEM:Search Engine Marketing，搜索引擎营销

--------------------------------------------------------------------------------------------
#day 26(4.11)#
=======================
##计算广告学##

+ ATD：automatic trading desk

+ SSP：Sell-Side Platform

+ CTR:click through rate，点击率

##databank##

+ **关于生成的.csv的文件名，可不可以自动生成？省去每次输入很长文件名的麻烦**

+ 内部流程

	顾问通过工单自动生成需求的命令行（特殊需求除外）—>通过redmine提交给跑数据的—>跑数据的check命令行后在服务器上运行—>将生成结果文件scp到自己pc—>通过redmine提交给顾问

##java##

+ hashCode()和equals()

	+ 如果两个对象相等，hashCode必须相等

	+ 若equals()被覆盖过，则hashCode()也必须被覆盖过

	+ hashCode()相等的对象不一定相等

+ 使用TreeSet的必要条件

	+ TreeSet的元素必须是Comparable,因为TreeSet返回的是经过去重和排序的，如下例

import java.util.*;

public class TestTree {
    public static void main(String [] args) {
	new TestTree().go();
    }

    public void go() {
	Book b1 = new Book("gone with the wind");
	Book b2 = new Book("cats");
	Book b3 = new Book("Tina's world");

	TreeSet<Book> tree = new TreeSet<Book>();
	tree.add(b1);
	tree.add(b2);
	tree.add(b3);
	System.out.println(tree);
    }
}

class Book **implements Comparable** {
    String title;
    public Book(String t){
	title = t;
    }

    public int compareTo(Object b) {
	Book book = (Book) b;
	return (title.compareTo(book.title));
     }
}

	或者使用重载、取用Comparator参数的构造函数的方法来创建TreeSet

-------------------------------------------------------------------------------------------------
#day 27(4.12)#
=======================
##java##

+ 项目目录规范

	源代码（.java）存储在source目录下，.class存储在classes目录下，这需要在编译时加上-d选项

	javac -d .../classess xx.java  (执行时记得进到classes里面)

+ 创建可执行的JAR文件

	（1）确定所有的类文件都在classes目录下

	（2）创建mainfest.txt来描述哪个类带有main()方法：Main-Class: MyApp(类名，不带后缀)后加换行，文件放在classes目录下

	（3）执行jar命令创建（在classes目录下）：
	
	jar -cvmf  mainfest.txt app.jar *.class 或 jar -cvmf  mainfest.txt app.jar MyApp.class 

+ 执行JAR文件

	java -jar XX.JAR

+ 类中加入包指令，防止重名

	放在第一行：package 1.2
	
	源文件放在名为2的目录下，次目录必须在1目录下

+ jar命令

	jar -tf xx.jar  列出文件的列表
	
	jar -xf xx.jar  解包

##DMP##

+ definition：A unified technology platform that intakes disparate first-, second-, and third-party data sets, provides normalization and segmentation on that data, and allows a user to push the resulting segmentation into live interactive channel environments.

+ core elements：

	+ data intake and aggregation: in a unified manner（统一的处理）

	+ segment building and manipulation: functionality that allows a marketer to flexibly combine
data sources to build custom audiences（灵活的需求）

	+ universal container tag：A universal tagging solution provides many benefits including consistent
and accurate data collection, increased tagging flexibility, and reduced organizational friction（是说除了处理灵活需求还要有统一的需求定义？？）

	+ Self-serve user interface

	+ Linkages to relevant channel environments（渠道集成）

+ good insight

	+ DMPs to go “back to the future” recombining audience data, analytics reporting, and multichannel execution, but this time with audience segmentation — rather than media data — at the core.

+ **trackmaster是一个初级版的DMP**

-----------------------------------------------------------------------------------------------------------------------------------------------------------