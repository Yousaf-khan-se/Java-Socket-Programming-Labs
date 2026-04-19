import java.net.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MultiThreadedTimeServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket data_server = new DatagramSocket(2222);
        System.out.println("Server is online");

        while (true) {
            byte[] buffer = new byte[1024];
            DatagramPacket data_packet = new DatagramPacket(buffer, buffer.length);
            data_server.receive(data_packet);

            // Start a new thread to handle communication with the client
            new Thread(new ClientHandler(data_server, data_packet)).start();
        }
    }
}

class ClientHandler implements Runnable {
    private DatagramSocket socket;
    private DatagramPacket packet;

    public ClientHandler(DatagramSocket socket, DatagramPacket packet) {
        this.socket = socket;
        this.packet = packet;
    }

    public void run() {
        String s_str = new String(packet.getData(), 0, packet.getLength());
        String response;

        if (s_str.equals("g")) {
            LocalTime time = LocalTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm:ss a");
            String f_time = time.format(format);
            response = f_time;
        } else if (s_str.equals("e")) {
            response = "Server shutting down";
        } else {
            response = "Invalid Input!";
        }

        try {
            DatagramPacket response_packet = new DatagramPacket(response.getBytes(), response.length(),
                    packet.getAddress(), packet.getPort());
            socket.send(response_packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

