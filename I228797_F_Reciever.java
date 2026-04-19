import java.net.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class I228797_F_Reciever {
	public static void main(String[] args) throws Exception
	{
		DatagramSocket data_server = new DatagramSocket(2222);
		System.out.println("Server is online");
		
		byte[] buffer = new byte[1024];
		
		DatagramPacket data_packet = new DatagramPacket(buffer, 1024);
		String s_str = "";
		LocalTime time = null;
		DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm:ss a");
		while(!s_str.equals("e"))
		{
			data_server.receive(data_packet);
			
			s_str = new String(data_packet.getData(), 0, data_packet.getLength());
			
			if(s_str.equals("g"))
			{
				time = LocalTime.now();
				String f_time = time.format(format);
				DatagramPacket response_packet = new DatagramPacket(f_time.getBytes(), f_time.length(),data_packet.getAddress(), data_packet.getPort());
				data_server.send(response_packet);
			}
			else if(!s_str.equals("e"))
			{
				System.out.println("Invalid Input!");
			}
		}
		
		System.out.println("The Server is offline");
		data_server.close();
	}
}
