import java.net.*;
import java.io.*;

public class UDPChatClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 2222);
        System.out.println("Client Connected");

        DataInputStream dataIn = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String userInput;
        while (true) {
            System.out.print("Enter 'file' to send a file or 'stop' to exit: ");
            userInput = reader.readLine();
            if ("stop".equalsIgnoreCase(userInput)) {
                dataOut.writeUTF("stop");
                break;
            } else if ("file".equalsIgnoreCase(userInput)) {
                System.out.print("Enter file path: ");
                String filePath = reader.readLine();
                File file = new File(filePath);
                if (!file.exists()) {
                    System.out.println("File does not exist.");
                    continue;
                }

                // Send file name
                dataOut.writeUTF(file.getName());

                // Send file content
                try (FileInputStream fileIn = new FileInputStream(file)) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = fileIn.read(buffer)) != -1) {
                        dataOut.write(buffer, 0, bytesRead);
                    }
                }
                System.out.println("File sent successfully.");
            } else {
                dataOut.writeUTF(userInput);
            }
            dataOut.flush();

            String response = dataIn.readUTF();
            System.out.println("Server response: " + response);
        }

        dataOut.close();
        dataIn.close();
        socket.close();
        System.out.println("Client Offline");
    }
}
