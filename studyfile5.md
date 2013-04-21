#day 28（4.15）#
======================
##hadoop##

+ **强调把代码向数据迁移**

+ 修改路径后，输入hadoop回车，即可得到基本的指令操作介绍

+ hadoop配置文件

	一般都在hadoop/conf/下面，主要为XML文件
	default.xml中的默认设置，会被site.xml中的设置显示覆盖，因此一般只需要修改xx-site.xml文件

+ hadoop模式

	+ 本地模式
		
		此模式下，三个site配置文件都为空，该模式主要用于开发调试MapReduce程序

	+ 伪分布模式：在“单节点集群”上运行hadoop

		除了修改三个site.xml文件，还要指定masters中SNN的位置，slaves中从节点的位置

	+ 全分布模式：真正的集群模式

+ **模式切换**

	修改xml文件很麻烦，可以使用符号链接来切换hadoop模式。需要为每种模式分别生成一个配置目录，并放入恰当版本的xml文件，使用如下命令切换：

	ln -s conf.xx conf

+ hadoop基本文件命令

	hadoop fs(=dfs) -com
	
	com可以为ls、mkdir、lsr（看到所有子目录）、put、get、cat、rm
	
	hadoop fs -put xx.txt /xx/xxx  （将本地文件系统的文件复制到HDFS中）	

	hadoop fs -get xx.txt

-----------------------------------------------------------------------------------------------------------------------------------------------------------
#day 29（4.16）#
======================
##hadoop##

+ 伪分布模式建立过程

	ln -s conf.pesudo conf

	hadoop namenode -formate

	start-all.sh

	http://localhost:50070/dfshealth.jsp  可以查看namenode

	http://localhost:50030/jobtracker.jsp 可查看jobtracker情况

+ hadoop默认工作目录

	/user/zhangxu ，如果没有需要建立hadoop fs -mkdir /user/zhangxu

+ .jar文件可以在本地，input和output必须在HDFS上，本地模式比伪分布模式快很多

+ **伪分布模式到本地模式的切换**

	stop-all.sh  关闭hadoop守护进程

	rm -rf conf

	ln -s conf.local conf

##pig##

+ pig的运行模式

	+ 本地模式：pig -x local x.pig

	+ hadoop模式：
	
		export PIG_HADOOP_VERSION=1.0  允许pig连接到1.0.*的hadoop版本上

		export PIG_CLASSPATH=$HADOOP_INSTALL/conf/     将hadoop的conf地址添加到pig的classpath上

		
------------------------------------------------------
#day 30（4.17）#
======================
##pig##

+ grunt

	pig -x local进入本地模式，pig进入hadoop模式，具有命令自动补全功能
	
	quit或者Ctrl-D可以退出grunt

	kill jobid  终止指定jobid的MapReduce任务
+ pig数据类型

	+ 基本类型：int long float double chararray bytearray

	+ 复杂类型：map tuple bag

		map: ['name'#'bob','age'#55] 键值间通过#划分

		tuple: ('bob',55) 一个两字段的tuple

		bag: {('bob',55),('sally',34)}  一个两tuple的bag

	+ null值，在pig中表示未知

+ pig latin之加载（存储类似）

	使用using句式指定加载函数，一般为PigStorage，如要使用HBase的加载函数，using HBaseStorage()

	divs = load 'xx' using PigStorage(',');   读取用，分隔的文本文件数据

	load as语句，指定加载的数据模式
	
+ foreach

	+ ..的用法

		prices = load 'xx' as (1x, 2x, 3x, 4x, 5x, 6x, 7x);
		beginning = foreach prices generate ..4x;    --从1x到4x 

	+ map的映射用#，tuple的映射用.	
	
---------------------------------------------
#day 31（4.18）#
======================
##databank##

+ 登录过程

	ssh zhangxu@58.215.168.157
	
	密码：admaster

	下载databank源码到home/zhangxu下

	ctrl+D退出

+ 杀进程

	hadoop job -kill jobid(check form [server ip:50030])

+ scp

	+ 本地到服务器：scp file zhangxu@ip:/home/zhangxu/

	+ 服务器到本地：scp zhangxu@ip:/home/zhangxu/output/xx.csv

+ panel数据处理

	带TA属性的panel数据跑完后，要用panel_extract.py来处理一下

	python panel_extract.py xx.csv > xxx.csv

+ 处理长日期段的parse_date.py

	python parse_date.py 130307-130402

	可以得到130307，130308。。。，130402	

##hadoop##

+ 工作流引擎oozie

	连接多个map/reduce job，完成复杂的数据处理，使用hPDL来定义DAG工作流

##pig##

+ filter

	filter A by symbol matches 'CM.*';  filter symbol匹配CM字符的选项
+ group：将具有相同键值的数据聚合成一个bag
	
	group A by xx; 
	
	也可以使用all对所有字段进行分组：group A all;

##RTB##

+ 常见受众定向方式

	受众定向，即为AUC（adusercontext）打标签的过程

	重定向retargeting，效果最好

	Hyper-local:地域细化更加细，比如北京海淀区清华园，效果很好

	其他定向方式还有人口属性，网站/频道，地域，行为，上下文，look-alike

+ **我们公司的数据标签是出售还是用在自己的network上？**

+ 行为定向

	longterm行为定向多日累积方式：滑动窗方式和时间衰减方式

--------------------------
#day 32（4.19）#
======================
##databank##

+ hadoop fs -getmerge /tmp/pigout/xxx.csv  xxx.csv   将HDFS中的某个结果文件弄到本地

+ nohup命令：普通进程用&符号放到后台运行，如果启动该程序的控制台logout，则该进程随即终止

	nohup 命令 &

	用jobs来查看任务，fg %n  切换不同的任务到前台

+ 在github上clone问题

	ssh-keygen -t rsa     
	cd ~    
	cd .ssh    
	cat id_rsa.pub 将出来的东西复制到github上（在github上add new sshkey）     
	**git clone git@github.com:AdMaster/Hadoop-Databank.git**	

+ panel无TA的六个维度的处理（注明需要一些样本的）

	六个维度分别为：age,sex,salary,gender_age,marriage,education   
	使用命令：python panel_sample_extract.py xx.csv age > xx_age.csv

+ **panel的data-type一般为view，无特殊说明**

##pig##

+ order by

	默认的排序是升序，要用降序字段后加desc，其余字段还是默认的升序

+ join

	将一个输入中的记录和另一个输入中的数据放在一起，通过指定键入的值达到目的

	join A by stock, B by stock;
	
	**如果合并后需要使用之前的div，可以用A::div来获得**

+ parallel

	任意可以触发reducer的关系操作符后面都可以加

	group A by stock parallel 10;  pig触发的MapReduce任务具有10个reducer

	在pig脚本最开头加入set default_parallel 10; 作用范围为脚本中每个触发reduce过程的操作符。如果某个操作符比较特殊，可以显式在操作符后面设置。

##受众定向##

+ data highway工具

	+ scribe：是Facebook开源的日志收集系统，为日志的“分布式收集，统一处理”提供了一个可扩展的，高容错的方案。通常与Hadoop结合使用，scribe用于向HDFS中push日志，而Hadoop通过MapReduce作业进行定期处理。

		利用Thrift实现底层服务

		其余类似工具还有flume，chukwa

+ DMP
		
	+ 目的：

		（1）为网站提供数据加工和对外交易能力

		（2）加工跨媒体用户标签，在交易市场售卖（bluekai）

	+ 特征：

		（1）定制化用户划分

		（2）统一的对外数据接口

+ 对于数据加工的一些理解

---------------------------------------------------------------------------------------------------------