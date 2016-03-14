import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class main {
	public static final String delim = ":";

	public static void main(String[] args) {
		String hostname = "", nickname = "", username = "", realName = "";
		int port = 0;

		try {
			File file = new File("input.txt");
			Scanner scan = new Scanner(file);

			String buffer = scan.nextLine();
			String[] buff = buffer.split(delim);

			hostname = buff[0];
			port = Integer.parseInt(buff[1]);

			nickname = scan.nextLine();
			username = scan.nextLine();
			realName = scan.nextLine();

			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Server: " + hostname + "\nPort: " + port
				+ "\nNickname: " + nickname + "\nUsername: " + username
				+ "\nReal Name: " + realName);
		System.out.println();

		Socket client = null;
		DataInputStream is = null;
		DataOutputStream os = null;

		try {
			client = new Socket(hostname, port);
			os = new DataOutputStream(client.getOutputStream());
			is = new DataInputStream(client.getInputStream());

			if (client != null && os != null && is != null) {

				os.writeBytes("NICK " + nickname + "\r\n");
				os.writeBytes("USER " + username + " 0 * :" + realName + "\r\n");

				String response = "";

				while ((response = is.readLine()) != null) {
					System.out.println(response);
				}
				os.close();
				is.close();
				client.close();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
