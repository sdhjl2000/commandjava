package com.example;

import com.example.lambda.Function;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by kosiara on 3/28/16.
 */
public class Main {
    public static void main(String[] args) throws  Exception{
        //check whether Java8 syntax compiles
        CuratorFramework client = CuratorFrameworkFactory.newClient("108.61.247.160:2181", new ExponentialBackoffRetry(1000, 3));
        client.start();
        InterProcessSemaphoreMutex lock = new InterProcessSemaphoreMutex(client, "/SyncEsData");
        lock.acquire();
        lock.release();
    }

    static Function printHello = () -> {
            System.out.println("Hello world!");
    };
}
