#install of cloudera hadoop and hbase#
===================
##CDH version: 4.5.0##
##JAVA version: 1.6.0_65##
====================
###1.dependencies
java and ssh 
###2.CDH
download tarballs, and unzip on path `/Users/zhangxu/Library/cloudera/hadoop-2.0.0-cdh4.5.0` and `/Users/zhangxu/Library/cloudera/hbase-0.94.6-cdh4.5.0`

    cd /Users/zhangxu/cloudera/cdh4.5.0 

    ln -s hadoop /Users/zhangxu/Library/cloudera/hadoop-2.0.0-cdh4.5.0

    ln -s hbase /Users/zhangxu/Library/cloudera/hbase-0.94.6-cdh4.5.0
    
    cd /Users/zhangxu/cloudera/ops
    mkdir logs
    mkdir dn
    mkdir nn
    mkdir pids
    mkdir tmp
    mkdir zk
    cd logs
    mkdir hadoop
    mkdir hbase
    mkdir yarn
    
 
    emacs ~/.zshrc
add the following code:

    CDH="cdh4.5.0"
    export HADOOP_HOME="/Users/zhangxu/cloudera/${CDH}/hadoop"
    export HBASE_HOME="/Users/zhangxu/cloudera/${CDH}/hbase"
    export JAVA_HOME="/System/Library/Frameworks/JavaVM.framework/Versions/CurrentJDK/Home"
    
    export PATH="${HADOOP_HOME}/bin:${HADOOP_HOME}/sbin:${HBASE_HOME}/bin"
    
replace core-site.xml, hdfs-site.xml, mapred-site.xml, yarn-site.xml and hadoop-env.sh of `/Users/zhangxu/Library/cloudera/hadoop-2.0.0-cdh4.5.0/etc/hadoop/` with files of current directory

replace hbase-site.xml and hbase-env.sh of `/Users/zhangxu/Library/cloudera/hbase-0.94.6-cdh4.5.0/conf` with files of current directory

###3.test
test for hadoop:

    cd /Users/zhangxu/Library/cloudera/hadoop-2.0.0-cdh4.5.0
    ./sbin/start-all.sh
    jps
    
check if there are process NameNode, SecondaryNameNode, NodeManager, ResourceManagerand DataNode

test for hbase:

    cd /Users/zhangxu/Library/cloudera/hbase-0.94.6-cdh4.5.0
    ./bin/start-hbase.sh
    jps
   
check if there are process HRegionServer, HMaster and HQuorumPeer
