package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class findOutOS implements Runnable {
	public void findOS() {
		String osName = System.getProperty("os.name").toLowerCase();
		System.out.println(osName);
		if (osName.contains("win")) {

		} else if (osName.contains("mac")) {
		} else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) {
			CommandRunner();
		} else {
			System.out.println("User's operating system cannot be determined.");
		}
	}

	public void CommandRunner() {
		String command = "ifconfig"; // Replace this with the command you want to run.
		String command1 = "uname -a";// use for check os and all information for user

		String commands[] = new String[] { command, command1 };
		try {
			for (String cmd : commands) {
				ProcessBuilder processBuilder = new ProcessBuilder();
				processBuilder.command("bash", "-c", cmd); // For Windows, use "cmd", "/c", command
				Process process = processBuilder.start();
				// Read the output of the command (if any) to the console.
				InputStream inputStream = process.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				String line;
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}
				int exitCode = process.waitFor();
				System.out.println("Command executed, exit code: " + exitCode);
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();

		}

	}

	@Override
	public void run() {
		findOS();
	}

	/*
	 * public static void getInfor() throws IOException { ServerSocket serverSocket
	 * = new ServerSocket(8080);
	 * 
	 * while (true) { Socket socket = serverSocket.accept();
	 * 
	 * InputStream inputStream = socket.getInputStream(); OutputStream outputStream
	 * = socket.getOutputStream();
	 * 
	 * byte[] buffer = new byte[1024]; int bytesRead = inputStream.read(buffer);
	 * 
	 * String request = new String(buffer, 0, bytesRead);
	 * 
	 * HttpHeader header = HttpHeader.parse(request);
	 * 
	 * String userAgent = header.getUserAgent();
	 * 
	 * String operatingSystem =
	 * UserAgentParser.parse(userAgent).getOperatingSystem();
	 * 
	 * System.out.println("Operating system: " + operatingSystem);
	 * 
	 * socket.close(); } }
	 * 
	 */
}