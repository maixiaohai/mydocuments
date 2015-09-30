#install of hive#
===================
##OS version: OS X 10.10##
##hadoop version: 2.0.0-cdh4.5.0##
##JAVA version: 1.7.0_79##
##HIVE version: apache-hive-1.0.1##
====================
###1.dependencies
hadoop and mysql
###2.hive install    
download tarballs, and unzip on path `/Users/username/Library/cloudera/apache-hive-1.0.1-bin`

    emacs ~/.zshrc
add the following code:

    export HIVE_HOME="/Users/username/Library/cloudera/apache-hive-1.0.1-bin"
    export PATH="/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:${HADOOP_HOME}/bin:${HADOOP_HOME}/sbin:${HBASE_HOME}/bin:${HIVE_HOME}/bin"
    source ~/.zshrc

modify conf:
    cd /Users/username/Library/cloudera/apache-hive-1.0.1-bin/conf
		modify as follows:
    <property> 
      <name>javax.jdo.option.ConnectionURL </name> 
      <value>jdbc:mysql://localhost:3306/hive </value> 
    </property> 
 
    <property> 
      <name>javax.jdo.option.ConnectionDriverName </name> 
      <value>com.mysql.jdbc.Driver </value> 
    </property>
    // 1234对应hive用户的密码
    <property> 
      <name>javax.jdo.option.ConnectionPassword </name> 
      <value>1234 </value> 
    </property>

    <property>
      <name>javax.jdo.option.ConnectionUserName</name>
      <value>hive</value>
      <description>Username to use against metastore database</description>
    </property>

    then modify all values which contains ${system:java.io.tmpdir} to /Users/username/cloudera/hive

###mysql
install mysql

    mysql -u root -p
    // add user hive and password
    create user 'hive' identified by '1234';
    // add Authorization of hive
    GRANT ALL PRIVILEGES ON *.* TO 'hive'@'localhost' IDENTIFIED BY '1234' WITH GRANT OPTION;
    flush privileges;
    exit

    //create database for hive
    mysql -u hive -p
    create database hive;

download mysql-connector-java-5.0.8.jar
    cp mysql-connector-java-5.0.8.jar /Users/username/Library/cloudera/apache-hive-1.0.1-bin/lib

###4.test
test for hive:

    cd /Users/username/Library/cloudera/hadoop-2.0.0-cdh4.5.0
    ./sbin/start-all.sh
    
		hive
