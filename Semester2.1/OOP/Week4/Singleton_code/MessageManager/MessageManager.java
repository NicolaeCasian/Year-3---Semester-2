import java.io.*;
import java.net.*;

class MessageManager{
	// This will be the one and only MessageManager instance
	private static MessageManager manager;
	private static DatagramSocket dsock;

	private MessageManager(){
		try{
			dsock = new DatagramSocket();
		}
		catch(IOException e){
			System.out.println(e.toString());
		}
	}

	// Public synchronized method which returns a
	// MessageManager
	public static synchronized MessageManager getManager(){
		// If true then we need to create an instance of
		// WindowManager
		if (manager == null)
			manager = new MessageManager();

		return manager;
	}

	public void send(String hostname, int port, String message)
	{
		try
		{
			InetAddress address = InetAddress.getByName(hostname);

			byte[] sendBuf = message.getBytes();

			DatagramPacket packet =
			new DatagramPacket(sendBuf, sendBuf.length, address, port);

			dsock.send(packet);
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}










