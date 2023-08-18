package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class getIP implements Runnable{

    public String getPublicIP() throws IOException {
        URL url = new URL("https://api.ipify.org");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String ipAddress = reader.readLine();
        reader.close();
        return ipAddress;
    }

	@Override
	public void run() {
		try {
			System.out.println(getPublicIP());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}