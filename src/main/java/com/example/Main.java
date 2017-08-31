package com.example;

import com.example.lambda.Function;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by kosiara on 3/28/16.
 */
public class Main {
    public static void main(String[] args) throws  Exception{
        //check whether Java8 syntax compiles
        CuratorFramework client = CuratorFrameworkFactory.newClient("108.61.247.160:2181", 1000,5000, new ExponentialBackoffRetry(1000, 3));
        client.start();
        InterProcessSemaphoreMutex lock = new InterProcessSemaphoreMutex(client, "/SyncEsData");
        InterProcessMutex interProcessMutex=new InterProcessMutex(client, "/SyncEsData");
        System.out.print("-----try get");
        interProcessMutex.acquire();
        System.out.print("----- get");
        Thread.sleep(10000);
        System.out.print("----- after sleep");
        System.out.print("----- after sleep");
        System.out.print(interProcessMutex.isAcquiredInThisProcess());
    }

    static Function printHello = () -> {
            System.out.println("Hello world!");
    };
}
