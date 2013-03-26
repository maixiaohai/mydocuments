#filename: getip.py

import os
import sys

def get_ips():

    tmp=list()
    
    ipcsv_path=os.path.join(sys.path[0],'ip_10896_5w.csv')
    f=open(ipcsv_path,'r')
    for line in f.readlines():
        line=line.strip().split('.')
        line[0]=".".join(line[0:2])
        line[1]=256*int(line[2])+int(line[3])
        tmp.append(line[:2])
    tmp.sort()
    f.close
    
    return tmp

#list=get_ips()
#print list
