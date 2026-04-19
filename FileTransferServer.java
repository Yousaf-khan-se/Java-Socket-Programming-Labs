import java.net.*;
import java.io.*;

public class UDPChatServer {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(2222);
			System.out.println("Server Connected");

			Socket clientSocket = serverSocket.accept();
			System.out.println("Connected to Client");

			DataInputStream dataIn = new DataInputStream(clientSocket.getInputStream());
			DataOutputStream dataOut = new DataOutputStream(clientSocket.getOutputStream());

			String fileName;
			while (true) {
				fileName = dataIn.readUTF();
				if (fileName.equals("stop")) {
					dataOut.writeUTF("stop");
					dataOut.flush();
					System.out.println("Client stopped sending files.");
					break;
				}

				System.out.println("Receiving file: " + fileName);

				// Receive file content
				try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
					byte[] buffer = new byte[4096];
					int bytesRead;
					while ((bytesRead = dataIn.read(buffer)) != -1) {
						fileOut.write(buffer, 0, bytesRead);
					}
				}

				System.out.println("File '" + fileName + "' received and saved.");
				dataOut.writeUTF("File '" + fileName + "' received successfully.");
				dataOut.flush();
			}

			dataOut.close();
			dataIn.close();
			clientSocket.close();
			serverSocket.close();
			System.out.println("Server Offline");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
