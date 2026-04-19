import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class I228797_F_Q2_Sender {
	public static void main(String[] args) throws IOException
	{
		DatagramSocket data_client = new DatagramSocket();
		System.out.println("CLient is online");
		
		byte[] buffer = new byte[1024];
		DatagramPacket received_packet = new DatagramPacket(buffer, buffer.length);
		
		String c_str = "";
		
		try (Scanner scan = new Scanner(System.in)) {
			while(!c_str.equals("e"))
			{
				System.out.print("Enter 'g' to get Sever time or press 'e' to exit: ");
				c_str = scan.next();
				
				System.out.println();
				
				if(c_str.equals("g"))
				{
					DatagramPacket data_packet = new DatagramPacket(c_str.getBytes(), c_str.length(),InetAddress.getLocalHost(), 2222);
					data_client.send(data_packet);
					data_client.receive(received_packet);
					System.out.println("The Server time is " + new String(received_packet.getData(), 0, received_packet.getLength()));
				}
				else if(!c_str.equals("e"))
				{
					System.out.println("Invalid input!");
				}
			}
		}
		c_str = "e";
		DatagramPacket data_packet = new DatagramPacket(c_str.getBytes(), c_str.length(),InetAddress.getLocalHost(), 2222);
		data_packet.setData(c_str.getBytes());
		data_client.send(data_packet);
		System.out.println("The Client is offline");
		data_client.close();
	}
}
