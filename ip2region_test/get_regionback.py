import os
import sys

def readfile_regionback():
    tmp = list()

    regionbackcsv_path=os.path.join(sys.path[0],'Region_back.csv')
    f=open(regionbackcsv_path,'r')
    for line in f.readlines():
        line=line.strip('\n').split(';')
        tmp.append(line)

    i=0
    regionback= {}
    while i<len(tmp):
        tmp1 = {}
        for item in tmp[i+1]:
            item = item.split(',')
            tmp1[(item[0],item[1])]=int(item[2])
        regionback[tmp[i][0]]=tmp1
        i += 3
   

    f.close()

    return regionback

#regionback=readfile_regionback()
#print regionback
