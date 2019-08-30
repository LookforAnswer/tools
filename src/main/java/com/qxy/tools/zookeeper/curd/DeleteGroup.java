package com.qxy.tools.zookeeper.curd;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.List;

/**
 * @Description: 类信息描述
 * @Author: qinxiaoyun001@lianjia.com
 * @Date: 2019/5/4 1:28 PM
 * @Version: 1.0
 */
public class DeleteGroup extends ConnectWacher {

    public void delete(String groupName) throws KeeperException, InterruptedException {
        String path = "/"+groupName;

        try {
            List<String> children = zooKeeper.getChildren(path,false);
            for (String childPath : children){
                delete(childPath);
            }
            //删除节点时，需要制定版本号，使用乐观锁机制，解决读写冲突问题，如果设置为-1，则忽略版本号
            zooKeeper.delete(path,-1);
        } catch (KeeperException.NodeExistsException e) {
            System.out.println(String.format("node %s is not exist",groupName));
            System.exit(1);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        DeleteGroup deleteGroup = new DeleteGroup();
        deleteGroup.connect("localhost");
        deleteGroup.delete("zoo");
    }
}
