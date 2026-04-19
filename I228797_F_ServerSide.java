import java.net.*;
import java.io.*;

public class I228797_F_ServerSide {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ServerSocket server_side = new ServerSocket(2222);
			System.out.println("Server Connected");
			Socket server = server_side.accept();
			
			
			DataInputStream data_in = new DataInputStream(server.getInputStream());
			DataOutputStream data_out = new DataOutputStream(server.getOutputStream());
			
			//BufferedReader b_in = new BufferedReader(new InputStreamReader(System.in));
			
			int count_vowels = 0;
			String c_str = "";
			while(true)
			{
				c_str = data_in.readUTF();
				System.out.println("Client Says: " + c_str);
				if(c_str.equals("stop"))
				{
					data_out.writeUTF("stop");
					data_out.flush();
					System.out.println("Server Offline");
					break;
				}
				for(char v : c_str.toCharArray())
				{
					v = Character.toLowerCase(v);
					if(v == 'a' || v == 'e' || v == 'i' || v == 'o' || v == 'u')
					{
						count_vowels++;
					}
				}
				System.out.println("Number of vowels in the message are: " + count_vowels);
				data_out.writeUTF(Integer.toString(count_vowels));
				data_out.flush();
				count_vowels = 0;
			}
			data_out.close();
			//b_in.close();
			server_side.close();
			server.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
