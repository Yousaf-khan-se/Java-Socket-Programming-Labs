import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class I228797_F_Q3_ClientSide {

	public static void main(String[] args) throws IOException
	{
		DatagramSocket data_client = new DatagramSocket();
		System.out.println("CLient is online");
		
		byte[] buffer = new byte[1024];
		DatagramPacket received_packet = new DatagramPacket(buffer, buffer.length);
		BufferedReader b_in = new BufferedReader(new InputStreamReader(System.in));
		
		long clientId = System.currentTimeMillis();
		String clientIdMessage = "Client_" + clientId + " says: ";
		
		String client_str= "", server_str = "";
		
		while(!server_str.toLowerCase().equals("goodbye"))
			{
				System.out.print("Enter the Message: ");
				client_str = b_in.readLine();
				client_str = clientIdMessage + client_str;
				
				System.out.println();
				
				DatagramPacket data_packet = new DatagramPacket(client_str.getBytes(), client_str.length(),InetAddress.getLocalHost(), 2222);
				data_client.send(data_packet);
				
				data_client.receive(received_packet);
				server_str = new String(received_packet.getData(), 0, received_packet.getLength());
				System.out.println(server_str);
			}
		client_str = "client is signing off!";
		DatagramPacket data_packet = new DatagramPacket(client_str.getBytes(), client_str.length(),InetAddress.getLocalHost(), 2222);
		data_packet.setData(client_str.getBytes());
		data_client.send(data_packet);
		System.out.println("The Client is offline");
		data_client.close();
	}

}
