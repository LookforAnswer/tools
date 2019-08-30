package com.qxy.tools.zookeeper.curd;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: 类信息描述
 * @Author: qinxiaoyun001@lianjia.com
 * @Date: 2019/5/2 8:31 PM
 * @Version: 1.0
 */
public class CreateGroup implements Watcher {

    public static final int SESSION_TIMEOUT = 5000;


    private ZooKeeper zooKeeper;

    /**
     * CountDownLatch这个类能够使一个线程等待其他线程完成各自的工作后再执行。
     * 例如，应用程序的主线程希望在负责启动框架服务的线程已经启动所有的框架服务之后再执行。
     * 1）主线程调用 CountDownLatch.await()
     * 2）其他线程调用 CountDownLatch.countDown()
     * 3）通过一个计数器来实现的，当计数为0时，主线程恢复
     */
    private CountDownLatch connetedSignal = new CountDownLatch(1);

    public void connect(String host) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper(host,SESSION_TIMEOUT,this);
        //主线程调用 await 方法
        connetedSignal.await();
    }

    /**
     * 通过 自身 实现 监听器，来检测zk 是否连接成功，很巧妙
     * @param event
     */
    @Override
    public void process(WatchedEvent event) {
        if (Objects.equals(event.getState(),Event.KeeperState.SyncConnected)){
            //子线程执行完成
            connetedSignal.countDown();
        }
    }




    public void create(String groupName) throws KeeperException, InterruptedException {
        String path = "/" + groupName;
        String createdPath = zooKeeper.create(path,null, ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        System.out.println("Created " + createdPath);
    }

    public void close() throws InterruptedException {
        zooKeeper.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        CreateGroup createGroup = new CreateGroup();
        createGroup.connect(args[0]);
        createGroup.create(args[1]);
        createGroup.close();
    }
}
