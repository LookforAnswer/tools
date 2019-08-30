package com.qxy.tools.zookeeper.curd;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.List;

/**
 * @Description: 类信息描述
 * @Author: qinxiaoyun001@lianjia.com
 * @Date: 2019/5/2 11:48 PM
 * @Version: 1.0
 */
public class ListGroup extends ConnectWacher {

    public void list(String groupName) throws KeeperException, InterruptedException {
        String path = "/" + groupName;
        try {
            List<String> childList = zooKeeper.getChildren(path, null);
            if (childList == null || childList.size() == 0) {
                System.out.println("/" + groupName + "不存在子节点");
                System.exit(1);
            }
            for (String childPath : childList) {
                System.out.println("child : " + childPath);
            }
        } catch (KeeperException.NodeExistsException e) {
            System.out.println("节点" + groupName + "不存在");
            System.exit(1);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ListGroup listGroup = new ListGroup();
        listGroup.connect("localhost");
        listGroup.list("/zoo");
        listGroup.close();
    }
}
