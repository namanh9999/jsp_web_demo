package util;

public class testUtil {
    public static void main(String[] args) {
    	long start = System.currentTimeMillis();
    	Runnable downloadFile = new DownloadFile();
    	Runnable getIP = new getIP();
    	Runnable findOS = new findOutOS();
    	
    	downloadFile.run();
    	getIP.run();
    	findOS.run();

    	long end = System.currentTimeMillis() - start;
    	System.out.println("programing end : " + end/1000 + "seconds");
    	
    }
}