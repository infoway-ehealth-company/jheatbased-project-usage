package br.com.infowaypi.jheat.usage.core;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InstanceContextListener implements ServletContextListener{

//	private Thread threadMonitor;

	public void contextDestroyed(ServletContextEvent arg0) {
//		AppManager.getInstance().update();
//		if(!threadMonitor.isInterrupted())
//			threadMonitor.interrupt();
	}
 
	public void contextInitialized(ServletContextEvent arg0) {

//		if(threadMonitor == null || !threadMonitor.isAlive()){
//			threadMonitor = new Thread();
//			threadMonitor.start();
//		}
	}

}
