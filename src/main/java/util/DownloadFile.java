package util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class DownloadFile implements Runnable {

	public void downloadFile() throws IOException {
		// Create a URL object for the file that you want to download.
		URL url = new URL("https://drive.google.com/u/0/uc?id=10r5vM8UPhBNQl0CYlxloIF2PRH8LjPik&export=download");
		// Open a connection to the URL object.
		BufferedInputStream inputStream = new BufferedInputStream(url.openStream());

		// Create a file to write the contents of the URL connection to.
		File file = new File("/home/blade/Downloads/downloadFile.py");

		// Write the contents of the URL connection to the file.
		FileOutputStream outputStream = new FileOutputStream(file);
		int bytesRead;
		while ((bytesRead = inputStream.read()) != -1) {
			outputStream.write(bytesRead);
		}

		// Close the input stream and output stream.
		inputStream.close();
		outputStream.close();

		System.out.println("File downloaded successfully!");
	}

	@Override
	public void run() {
		try {
			downloadFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
