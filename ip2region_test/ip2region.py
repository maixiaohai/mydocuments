import os,sys
import getip
import get_regionback

def ip2region():
    ips_list=getip.get_ips()
    regionback=get_regionback.readfile_regionback()
    

    output_path=os.path.join(sys.path[0],'output.csv')
    f=open(output_path,'w')
    
    for item in ips_list:
        for key in regionback:
            if (item[0]==key):
                for k,v in regionback[key].items():
                    if ((item[1]>int(k[0])) and (item[1]<int(k[1]))):  
                        result=item[0]+"."+str(item[1]/256)+"."+str(item[1]%256)+":"+str(v)+"\n"
                    
                        f.writelines(result)
                        
    f.close()


ip2region()

