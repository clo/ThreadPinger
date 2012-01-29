package com.clo.connector;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Hello world!
 *
 */
public class App {

  public static void main(String[] args) throws Exception {
    System.out.println(new Date());
    String host = "192.168.1.";
    int nrOfThreads = 200;
    HashMap<String, Future> ipList = new HashMap<String, Future>();
    List<Future<Boolean>> list = new ArrayList<Future<Boolean>>();
    ExecutorService pool = Executors.newFixedThreadPool(nrOfThreads);
    for (int i = 1; i <= 255; i++) {
      String ipAddress = host + i;
      PingThread ping = new PingThread(ipAddress) {
      };
      Future<Boolean> submit = pool.submit(ping);
      list.add(submit);
      ipList.put(ipAddress, submit);
    }
    for (String key : ipList.keySet()) {
      Future<Boolean> future = ipList.get(key);
      if (future.get().equals(true)) {
        System.out.println(key + "=" + future.get());
      }
    }
    System.out.println(new Date());
  }
}
