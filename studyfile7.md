#day 39（5.2）#
======================
##多线程爬虫##

+ java多线程实现

	在java中要想实现多线程，有两种手段，一种是继续Thread类，另外一种是实现Runable接口
	如果一个类继承Thread，则不适合资源共享。但是如果实现了Runable接口的话，则很容易的实现资源共享。

##hadoop##

+ streaming

	帮助用户创建和运行一类特殊的map/reduce作业， 这些特殊的map/reduce作业是由一些可执行文件或脚本文件充当mapper或者reducer
	（1）通过unix命令使用streaming

		$HADOOP_HOME/bin/hadoop jar $HADOOP_HOME/hadoop-streaming.jar \
		-input myInputDirs \
		-output myOutputDir \
		-mapper 'cut -f 2 -d ,' \    //取input的第二列的数据，并声明列是用逗号隔开的
		-reducer 'uniq'               //对排序后的数进行去重
	（2）通过脚本，同上类似（需要每个节点上都安装python语言）
		
		多一行-file xx.py

+ combiner提升性能

	combiner的调用次数可以是0次，1次或多次。  
	它在数据转换上必须与reducer等价，即去掉它，reducer的输出也会保持不变。  
	另外，driver要为Jobconf对象指定combiner的类：  
		job.setCombinerClass(Combine.class);

##git course##

+ git cherry-pick commitID : 把已经提交的commit从一个分支放到另一个分支上，HEAD此时在需要的节点

+ git commit --amend  可以修改最近一次的提交

-----------------------------------
#day 40（5.3）#
======================
##pig##

+ stream例子
	
	在数据流中插入一个个性化的可执行任务

		define hd 'highdiv.pl' ship('highdiv.pl');
		A = load 'xx' as (xx1,xx2,xx3);
		B = stream A through hd as (xx1,xx2,xx3);

	define先将引用文件定义了别名hd，其次告诉pig需要加载./highdiv.pl，通过ship命令加载到Hadoop中	
	当输入不是默认的stdin，stdout时，用-i inputfile和 -o outputfile来指定输入输出文件

+ mapreduce

	A = load 'xx' as (1,2);
	B = foreach A generate normalize(1);
	C = mapreduce 'xx.jar' store B into 'input' load 'output' as (1,2);
	
	在load语句的后面可以通过''指定需要传递给Java命令的参数	

+ 参数传入
 
	可以用-p指定参数，也可以用-para_file指定参数文件

		例子：A = filter daily by data == '$DATE';
		pig -p DATE=20130501 daily.pig

+ 包含其他pig脚本

	用import 'xx/xx/xx.pig';	

+ pig在emacs中高亮

	git clone https://github.com/cloudera/piglatin-mode.git  
	将piglatin.el复制到登录目录下，修改为.piglatin.el（防止误删）   
	在.emacs（没有就自己新建一个）中加入一行(load-file "pathto/.piglatin.el")    
	再用emacs打开.pig文件就会语法高亮了  		

+ 调试命令

	describe,explain,illustrate

--------------------------
#day 41（5.6）#
======================
##pig##

+ 脚本头部

	在用到自定义函数（如MapRegion）时，需要使用如下命令

		REGISTER 'pigudf.jar';
		DEFINE MapRegion cn.com.admaster.pig.udf.MapRegion();

	如果用到自定义py文件（如 getInterval_new.p），需要如下命令

		Register getInterval_new.py using jython myfuns;  //使用时myfuns.getInterval(xx)来调用

+ MapRegion（自定义UDF）

	pig和hadoop的对接：需要使用eval函数，eval自定义函数主要用来进行常规的数据转换  
	使用的基本方法是编写一个类继承EvalFunc这个抽象类，并在内部重写此类的方法，大体框架：

		import org.apache.pig.EvalFunc;
		import org.apache.pig.data.Tuple;	
		public class Xxxx extends EvalFunc<String> {
		@Override
		public String exec(Tuple input) throws IOException {
			}
		}

+ 调试技巧

	illustrate无法解决时，可以使用本地模式对脚本测试，可以使用一些关闭功能。
##JS##

+ 基本框架

		<html>
		<body BGCOLOR="WHITE">
		<p>Paragraph 1</p>
		<script type="text/javascript">
	  	 document.bgColor="RED";
	 	 alert("second script block");
		</script>
		</body>
		</html>
	代码加在script中间，script加在head或者body中间，通过type标记脚本语言的属性
	
+ 数据类型

	包括数值类型（整型和浮点型）、字符串类型和布尔类型。     
	变量大小写敏感 ，定义：var xxx;  

----------------------------------------
#day 42（5.7~10）#
======================
##python##

+ 需求
		
	每天一个文件夹，例如130506，每天的文件夹底下分项目存放，每个项目一个文件夹， 同一维度的数据存储到一个文件里，合并曝光和点击，具体的对应关系如yaml所示
	ps:注意补零这个细节

+ 基本思路
	
	搜索当前文件夹下所有文件及路径，读取，合并同名文件，后根据yaml的映射关系，将同一key下的四个文件再进行合并，合并时需要比较key和做左右补零的操作，最后写入

+ 问题

	（1）No module named yaml  	
	sudo apt-get install python-yaml
	
	（2）安装ipython来做一些简单的测试

	（3）未完成的需求包括，写入，格式为date/camp/key.csv，以及按照命令行的要求读取相应的文件夹内容

-------------------------