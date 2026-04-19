import java.net.*;
import java.io.*;

public class I228797_F_Q1_ClientSide {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket server = new Socket("localhost", 2222);
		System.out.println("Client Connected");
		DataInputStream data_in = new DataInputStream(server.getInputStream());
		DataOutputStream data_out = new DataOutputStream(server.getOutputStream());
		
		BufferedReader b_in = new BufferedReader(new InputStreamReader(System.in));
		
		String c_str= "", s_str = "";
		
		while(true)
		{
			System.out.print("Enter the String: ");
			c_str = b_in.readLine();
			data_out.writeUTF(c_str);
			data_out.flush();
			
			s_str = data_in.readUTF();
			if(s_str.equals("stop"))
			{
				System.out.println("Client Offline");
				break;
			}
			
			System.out.println("Number of vowels in the message are: " + s_str);
		}
		data_out.close();
		data_in.close();
		server.close();
	}

}
