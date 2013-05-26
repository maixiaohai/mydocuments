#5.20#
======================
##python##

+ nose——python测试框架

	安装：sudo pip install nose（前提：安装了pip）  
	成功：命令行输入nosetests后，显示结果	

	测试框架例子：     
	（1）待测试程序：  

		def to_celsius(t):
		      return (t-32.0)*5.0/9.0
		def above_freezing(t):
		     return t>0	
	（2）测试框架：注意名字要以test_开头，这样nosetest才能检测到
		
		import nose
 		import temperature

		def test_to_celsius():
		          '''Test function for to_celsius'''
		          pass
		def test_above_freezing():
		          '''Test function for above_freezing.'''
		          pass

		if __name__ == '__main__':
		          nose.runmodule()

	（3）to_celsius（）的测试函数

		import nose
		import temperature

		def test_freezing():
		    assert temperature.to_celsius(32) == 0

		def test_boiling():
		    assert temperature.to_celsius(212) == 100

		def test_roundoff():
		    assert temperature.to_celsius(100) == 38

		if __name__ == '__main__':
		    nose.runmodule()
		运行后可以得到测试时间，被测试模块，以及成功失败个数的详细信息
	
	（4）more details in<https://github.com/nose-devs/nose>

------------------------------------------
#5.21#
======================
##python##

+ web

	sudo pip install lpthw.web

		import web

		urls = ('/', 'index')

		app = web.application(urls, globals())

		class index:
		    def GET(self):
		        greeting = "Hello World"
		        return greeting

		if __name__ == "__main__":
		    app.run()

	运行后，登录0.0.0.0:8080可以看到hello world     
	可以加入render = web.template.render('templates/')来对文字进行处理      
	return后面写入render.index(greeting = greeting)   

-------------------------------------
#5.22#
======================
##emacs##

+ .emacs (basic setting)

	(setq inhibit-startup-message t)    不显示启动信息  
	(setq default-tab-width 4)    
 	(setq tab-width 4)                             默认tab-width为4   
	(require 'linum)                
	(global-linum-mode t)                      显示行号
	(fset 'yes-or-no-p 'y-or-n-p)              将强制输入yes或no改为简写          
	(show-paren-mode t)                      显示匹配的括号  
	(setq-default make-backup-files nil)    不生成临时文件    

	安装自动补全的插件：                              
	下载auto-complete-1.3.1.zip，解压                                
	进入emacs，meta+x，load-file  ，输入对应的文件路径/etc/install.el                    
	安装到.emacs.d/config/autocomplete                                      
	完成后会提醒 将如下命令加入.emacs文件：                               
	(add-to-list 'load-path "~/.emacs.d/config/autocomplete/")                 
	(require 'auto-complete-config)                
	(add-to-list 'ac-dictionary-directories "~/.emacs.d/config/autocomplete//ac-dict")               
	(ac-config-default)              
	成功后，会自动补齐，或者meta+tab 		

+ 一些基本操作

	加-nw直接在shell里显示                    
	ctrl+x  +1，全屏                 
	ctrl+x  +0，退出当前屏             
	ctrl+x  +2，当前屏分为上下屏          
	ctrl+x  +3，当前屏分为左右屏                
	meta+x shell，进入shell            
	ctrl+x  +o，可以在各个屏间快速移动

##go##

+ 入门编辑器（环境）

	使用IDEA做go语言的编辑器   
	打开idea，在file的settings里找到plulgins，点broswer repo，找到golang，下载，然后在plugins里点apply即可         
	最后重启idea，新建project就可以看到go module  

+ 下载：<http://code.google.com/p/go/downloads/list>

	此处说是要安装mercurial(轻量级分布式版本控制系统)，其实不需要，tar zxvf go1.1.linux-amd64.tar.gz后，直接生成了go文件夹                   
	进入go/src，sudo ./all.bash，此处较慢                
	完成后，将go/bin加入$PATH中（注意是zshrc），重启终端                 
	运行go version检查是否成功              
		

##系统操作##

	uname -a 可以查看系统是多少位，x86_64表示是64位

--------------------------
#5.23#
======================
##有关很多命令not found的解决办法##

	首先应该确定的是这种问题是由于环境变量引起的，可以使用echo $PATH来看一下            
	查看path是否含有：/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin           
	如果没有，使用临时环境变量（重启后消失）        
	export PATH=$PATH:/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin                        
	此时，恢复正常，emacs .zshrc（出问题的配置文件），发现是之前的一个go的环境变量命令写错了，改正后，重启，恢复正常即可            
	如果无法恢复，可以修改etc/profile或者是.bashrc文件，加入export PATH=$PATH:/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin，然后source一下                   
	
##go与idea##

	打开idea，新建project，此时，需要引入new sdk，一般输入go的install path，/home/zhangxu/go      
	其余操作和一般新建过程类似，名字叫helloworld，finish后会出现src下面的main.go  
	并且会自动生成如下代码：    
		
		package main     //声明文件的package名

		import "fmt"           //import语言的fmt库—用于输出

		func main() {
		       fmt.Printf("Hello world!")
		}	
	编译运行成功，可运行文件放在out文件夹下              
	如果不用idea，需要使用go run main.go来解释执行                
	或者先编译go build hello.go                                       
	产生hello执行文件，再./hello    
      
---------------------------------------------------------
#5.24#
======================
##常用版本控制工具对比##

	<http://www.oschina.net/question/84514_9508> svn,hg,git

##go##

+ 格式化代码

	输入gofmt -w **.go 会格式化该源文件的代码然后将格式化后的代码覆盖原始内容   
	如果不加-w，则只会打印格式化后的源代码                                     
	命令行最后如果是目录名，则格式化目录下所有go文件            
	gofmt -r 可以实现简单重构，具体例子：
	gofmt -r "(a) -> a" -w *.go    去掉源代码中没有意义的括号             
 
+ 生成代码文档

	用法：（1）go doc package，可以获得此包的文档注释（例：go doc fmt）                       
	（2） go doc package function，获取此包下某函数的文档注释   
	在命令行输入godoc -http=:6060，即可在http://localhost:6060看到文档                      

+ 自定义类型

	type，定义自己的类型，type IZ int;var a IZ =5
             
-------------------------------------------------------