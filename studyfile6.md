#day 33（4.22）#
======================
##pig高阶操作##

+ 内嵌foreach

	语法：foreach XX {语句1;语句2;};  
	内嵌语句中，关系操作符也只可以单独应用与关系，不可以用于表达式，比如distinct daily.symbol就是错误的语法 
	可以用如下语法实现：sym=daily.symbol;uniq_sym=distinct sym; 
	内嵌结构的最后一行一般是一个generate语句

+ 不同的join实现方法

	+ 小数据和大数据进行join操作

		jnd=join A by (sd,ss), B by (sd,ss) using 'replicated';
		using语句告诉pig使用分片—复制算法，不出发reduce过程，直接在map阶段完成。
	
	+ 对倾斜的数据进行join操作	

		jnd=join A by (ss), B by (ss) using 'skewed';  比较适用于有一个输入有数据倾斜的问题

	+ 对排好序的数据进行join操作

			jnd=join A by (ss), B by (ss) using 'merge';  	

+ union 

	对于两个不同模式的集合，使用union onschema来实现 

	A = load 'input1' as (w:chararray,x:int,y:float);  

	B = load 'input2' as (x:int,y:double,z:chararray);  

	C = union onschema A,B;

	C的schema为{w: chararray,x: int,y: double,z: chararray}

+ cross

		cross A,B parallel 10;  cross会产生大量数据，如A有n条记录，B有m条，则输出会有nxm条记录
	
##databank##

+ 两个分离panel数据的.py文件的区别：

	只是域方面，sample脚本多了一个样本量

+ 各个维度的样本量是一样的

--------------------------
#day 34（4.23）#
======================
##hadoop##

+ 执行jar时的一些参数选择

	-conf configuration file  指定一个配置文件
	
	-D property=value 给JobConf属性赋值

	-fs local|namenode:port  指定一个NameNode，可以是"local"

	-jt  local|jobtracker:port  指定一额JobTracker

	-files list of files  指定一个以逗号分隔的文件列表，用于mapreduce作业。这些文件自动分布到所有节点，使之可从本地获取

	-libjars list of jars  指定一个以逗号分隔的jar文件，使之包含在所有任务的JVM的classpath中

	-archives list of archives  指定一个以逗号分隔 的存档文件列表，使之可在所有任务节点打开

##有关java和javac 版本不一样的问题##

+ java -version 和javac -version得到的版本不同，有可能造成class编译通过但无法运行，解决方法是把JAVA_HOME/bin加入path中

+ sudo update-alternatives --config java

	会显示java的路径，可以通过输入编号改变默认选项

-------------------------
#day 35（4.24）#
======================
##my first MapReduce##

+ 问题一

	map的输出K，V必须与reduce的输入K，V一致

+ 问题二

	java.lang.ArrayIndexOutOfBoundsException: 1

	数组下标越界错误，1表示对应的越界下标，经过查看发现input文件的行的最后是空格，这样读入的数组[1]的后面就会有很多空格，造成越界，而前面使用的split(",")

+ 问题三

	map的默认输入，一般设置为TextInputFormat，即读取的key为当前行号，value为当前行内容

	所以读取时要用value.toString()做转换，如果对应为IntWritable类型，还要做强制转换

+ 问题四

	output.collect()的输出格式，不能直接写成output.collect(date，one)，应为output.collect(new Text(date),one)

+ 问题五

	有关groupby的问题，reduce的输出会自然对key进行groupby
##IDEA##

+ 快捷键

	Alt+Enter对着红色部分，会出现解决方案

-----------------------
#day 36（4.25）#
======================
##redis##

+ 操作手册：<https://github.com/antirez/redis>

+ steps

	git clone https://github.com/antirez/redis.git

	cd redis   make   make install
	
	**redis-server**
	
	cd src

	./redis-cli

	测试及结果：

	redis> ping
   	
	PONG
  	
	redis> set foo bar
 	
	OK
 	
	redis> get foo
    	
	"bar"

##idea##

入门：<http://confluence.jetbrains.com/display/IntelliJIDEA/Tutorials>

---------------------------------------------
#day 37（4.26）#
======================
##Jsoup##

+ 简介
		
	jsoup 是一款Java 的HTML解析器，可直接解析某个URL地址、HTML文本内容。它提供了一套非常省力的API，可通过DOM，CSS以及类似于jQuery的操作方法来取出和操作数据。<http://jsoup.org/>

+ 基本用法

	例子：Fetch the Wikipedia homepage, parse it to a DOM, and select the headlines from the In the news section into a list of Elements (online sample):

		Document doc = Jsoup.connect("http://en.wikipedia.org/").get();

		Elements newsHeadlines = doc.select("#mp-itn b a");

+ 在Maven下的POM's依赖dependencies下（无需下载）

		<dependency >
 	  	 <!-- jsoup HTML parser library @ http://jsoup.org/ -->
  		<groupId>org.jsoup</groupId>
  		<artifactId>jsoup</artifactId>
 		 <version>1.7.2</version>
		</dependency>
##myfirstcrawler##

+ 问题一

	连接问题，因为redis-server没启动。。。

+ 问题二	

	readtimeout，原因是没有建立一个读的缓冲区

	StringBuffer sff = new StringBuffer();
	
	Document doc = Jsoup.connect(url.toString()).get();
	
	sff.append(doc.body().text());	

+ 问题三

	类型不匹配问题，url要toString一下

--------------------------------------------------------------
#day 38（4.27）#
======================
##多线程java爬虫的一些准备工作##

	jedis在多线程下操作是不安全的，这时要使用JedisPool，一个线程安全的网络连接池	

+ jedispool配置

	JedisPoolConfig config = new JedisPoolConfig();
	        config.setMaxActive(100);
	        config.setMaxIdle(20);
	        config.setMaxWait(1000l);
	JedisPool pool = new JedisPool(config, "127.0.0.1", 6379);
	 
	使用时：
	Jedis jedis = pool.getResource();
	 
	使用结束后要将jedis放回pool中：
	pool.returnResource(jedis);

##Jersey##

user-guide<https://jersey.java.net/nonav/documentation/latest/user-guide.html>

+ 首先在idea的pom.xml中的dependencies添加依赖
		
               	 <dependency>
                   	 <groupId>com.sun.jersey</groupId>
                    	<artifactId>jersey-server2</artifactId>
                    	<version>1.17</version>
                	</dependency>
                	<dependency>
                    	<groupId>com.sun.jersey</groupId>
                    	<artifactId>jersey-grizzly2</artifactId>
                    	<version>1.17</version>
              	  </dependency>
      
 + 主程序      

	private static URI getBaseURI() {
         	
	 return UriBuilder.fromUri("http://localhost/").port(9998).build();

     	 }
  
      	public static final URI BASE_URI = getBaseURI();
  
      	protected static HttpServer startServer() throws IOException {
	 System.out.println("Starting grizzly...");
         	 ResourceConfig rc = new PackagesResourceConfig("com.sun.jersey.samples.helloworld.resources");
          	return GrizzlyServerFactory.createHttpServer(BASE_URI, rc);
    	  }
     
      	public static void main(String[] args) throws IOException {
        	HttpServer httpServer = startServer();
          	System.out.println(String.format("Jersey app started with WADL available at "
                  	+ "%sapplication.wadl\nTry out %shelloworld\nHit enter to stop it...",
                  	BASE_URI, BASE_URI));
          	System.in.read();
         	httpServer.stop();
	}

+ roor resource类使用JAX-RS annotations

	HTTPMETHODS，在javax.ws.rs中  
	@Path，标注资源类或者方法的相对路径    
	@GET，@PUT，@POST，@DELETE，标注方法是HTTP请求的类型。  
	@Produces，标注返回的MIME媒体类型  
	@Consumes，标注可接受请求的MIME媒体类型	 

##Ctrl+e##

+ word：光标居中

+ markdown：预览

+ idea：打开最近浏览的文件夹选项

##git##

git checkout master^  Head会指向parent of master

git checkout HEAD^具有同样的效果（注意是大写的）

git checkout HEAD~3 移动回三个

git branch -f master HEAD~3   强行将master分支移动到HEAD后面的三位

-------------------------------------------------------------