package com.qxy.tools.zookeeper.curd;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.Charset;

/**
 * @Description: 类信息描述
 * @Author: qinxiaoyun001@lianjia.com
 * @Date: 2019/5/5 4:39 PM
 * @Version: 1.0
 */
public class ActiveKeyValueStore extends ConnectWacher {

    public static final Charset CHARSET = Charset.forName("UTF-8");

    public void write(String path,String value) throws KeeperException, InterruptedException {

        //false 代表不用监听该节点
        Stat stat = zooKeeper.exists(path,false);
        if (stat == null){
            zooKeeper.create(path,value.getBytes(CHARSET), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        } else {
            zooKeeper.setData(path,value.getBytes(CHARSET),-1);
        }
    }


}
