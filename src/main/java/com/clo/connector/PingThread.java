/*
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clo.connector;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author christian
 */
public abstract class PingThread implements Callable<Boolean> {

  private String ipAdress = "";

  public PingThread(String ipAdress) {
    this.ipAdress = ipAdress;
  }

  public Boolean call() {
    return _reachable();
  }

  private Boolean _reachable(){
    Boolean isReachable = false;
    try {
      isReachable = new Boolean(InetAddress.getByName(ipAdress).isReachable(1000));
    } catch (UnknownHostException ex) {
      Logger.getLogger(PingThread.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      return isReachable;
    }
  }

}
