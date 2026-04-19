import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class I228797_F_Q3_ServerSide {

	public static void main(String[] args) throws Exception
	{
		DatagramSocket data_server = new DatagramSocket(2222);
		System.out.println("Server is online");
		
		byte[] buffer = new byte[1024];
		
		DatagramPacket data_packet = new DatagramPacket(buffer, 1024);
		BufferedReader b_in = new BufferedReader(new InputStreamReader(System.in));
		
		String client_str= "", server_str = "";
		
		while(!server_str.toLowerCase().contains("I'm signing off"))
		{	
			data_server.receive(data_packet);
			client_str = new String(data_packet.getData(), 0, data_packet.getLength());
			System.out.println(client_str);
			
			if(client_str.toLowerCase().contains(": goodbye"))
			{
				server_str = "goodbye";
			}
			else
			{
				System.out.print("Enter the Message: ");
				server_str = b_in.readLine();
				server_str = "Server Says: " + server_str;
			}
			
			System.out.println();
			
			DatagramPacket response_packet = new DatagramPacket(server_str.getBytes(),server_str.length() ,data_packet.getAddress(), data_packet.getPort());
			data_server.send(response_packet);
		}
		
		server_str = "goodbye";
		DatagramPacket response_packet = new DatagramPacket(server_str.getBytes(),server_str.length() ,data_packet.getAddress(), data_packet.getPort());
		data_server.send(response_packet);
		
		System.out.println("The Server is offline");
		data_server.close();
	}
}
