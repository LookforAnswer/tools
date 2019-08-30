package com.qxy.tools.zookeeper.curd;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;

import java.io.IOException;

/**
 * @Description: 类信息描述
 * @Author: qinxiaoyun001@lianjia.com
 * @Date: 2019/5/2 9:10 PM
 * @Version: 1.0
 */
public class JoinGroup extends ConnectWacher {

    public void join(String groupName, String memberName) throws KeeperException, InterruptedException {
        String path = "/" + groupName + "/" + memberName;
        String createdPath = zooKeeper.create(path, null, ZooDefs.Ids.READ_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("memberPath:" + createdPath);
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        JoinGroup joinGroup = new JoinGroup();
        joinGroup.connect(args[0]);
        joinGroup.join(args[1], args[2]);
        Thread.sleep(Long.MAX_VALUE);
    }

}
