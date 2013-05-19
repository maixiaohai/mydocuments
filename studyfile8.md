#5.14#
======================
##python##

+ 有关读写文件操作

	f.seek(偏移量，[起始位置])  比如f.seek(0)就是将文件内部的位置指针指向开头	

+ parsedate.py解析

	(1)使用方法：python parse_date.py 130501-130516  
	(2)作用：将给定的日期段解析成（20130501，20130502，。。。，20130516）的形式并返回  
	(3)大体框架：两个主要方法，一个是main，用于将日期段传给parse_date,一个是parse_date(p_d)   
	p_d又包含了date_expand和parse_date_helper  
	总调用关系为：main->p_d->parse_date_helper->date_expand   
	(4)re.sub的用法：

	re.sub:需要import re，re.sub('[abc]', 'o', 'Mark')将Mark中的a、b、c替换为o  
	此为一般用法，但是本例中替换项是函数 
	测试例子：
		import re

		def myrepl(m):
    		s = m.group(0)
		    if s == 'a':
		        return 'one a'
		    else:
		        return 'two a'

		print (re.sub('a{1,2}', myrepl, 'a aa aaa'))
	
-------------------------
#5.15#
======================
##python##

+ operator
		
	** 多少次方
	//   截断除法  7.0 // 4 返回 1.0，而不是1.75，截断意为取整

+ exec

	exec语句用来执行储存在字符串或文件中的Python语句  
	例子：exec 'print "Hello World"'

+ merge.py解析

	（1）输入路径和输出路径问题的解决：输入路径直接加在config.yaml文件中，具体的选择通过命令行参数输入  
	（2）os.listdir(路径)，会列出“路径”下的所有文件和目录的，但不包括子目录的内容

-------------------------------------------
#5.16#
======================
##python##

+ list操作

	list.pop():会把list的最后一个item推出来，此时，list里面就不会有这个item了

+ merge.py解析

	（1）os.path.basename(路径)，会得到对应的filename   
	（2）zip的用法：

		x=[1, 2, 3, 4, 5 ]  
		y=[6, 7, 8, 9, 10]  
		zip(x, y)就得到了  
		[(1, 6), (2, 7), (3, 8), (4, 9), (5, 10)]
	（3）用dict来存储content，这样解决了补零时的很多复杂问题，值得借鉴    
	（4）写文件时的一个细节，加入了一个判断语句，判断此文件有没有打开，这样可以避免再次写入覆盖，同时，采用keys的方法，用循环将对应的文件关闭      
	（5）lambda：在python中使用lambda来创建匿名函数，lambda是一个表达式，可以直接用于list和dict的成员

		g = lambda x:x*2
		print g(3)
		结果为6

	本程序中，使用如下：

		 map(lambda x: os.path.join(baseDir, x), os.listdir(baseDir))
	（6）map的用法（python的一个特殊函数）     
	map(function, sequence) ：对sequence中的item依次执行function(item)，见执行结果组成一个List返回
	
##跑数据##

+ zip命令

	zip -r xx.zip freq*.csv

+ 解决分天增量的shell脚本

		b=130502
		while [ "$b" -lt 130513 ]; do
		./calc.py --command=FREQ --camp=13084 --date=130408-$b --group-by=ymd,region_alt --filter="type<300" --merge-above=3 --unit=cookie --data-type=view --job-name=freq_13084_130408_$b --output=freq_13084_130408_$b.csv
		b=$(($b+1))
		done
		
	最后要把所有的文件合并成一个

---------------------------
#5.17#
======================
##python##

+ random模块(import random)

	random.random()用于产生0到1间的随机浮点数    
	random.uniform(a, b)用于产生a到b间的随机浮点数     
	random.randint(a, b)产生a到b间的int，含a，b     
	random.randrange(a, b, step)从[a, a + step, a + 2*step, ...]中的一个随机数      
	random.choice(sequence)从sequence中随机选取一个      
	random.shuffle(sequence) 重排   
	random.sample(sequence, k) 从sequence中随机获取k长度的片段，原序列不变
	
+ merge_inc_file.py
	
	用于将某一文件夹下所有文件合并到一个里面   
	py merge_inc_file.py   dirname              
	将生成一个dirname_inc.csv的文件用于上传
	
+ panel_extract.py和panel_sample_extract.py

	唯一区别在于fileds的范围，前者取到了-2位，后者取到了-1位，即后者包含了sample的位     
	取用规则主要看需求，要不要带样本的结果，需要就用后者处理，不需要则为前者    
	另外，如果跑的是TA数据，处理函数和非TA的也不一样，带TA的只需去data[TA]，而不带TA的需要跑六个维度，这个时候通过命令行输入得到对应函数的参数   

------------------------------------------