import java.io.*;
import java.net.*;

class Receiver
{
	private DatagramSocket dsock;
	private int port;

	Receiver(int port)
	{
		this.port = port;
		try
		{
			dsock = new DatagramSocket(port);
		}
		catch(IOException e)
		{
			System.out.println(e.toString());
		}
	}

	private String waitForPackets()
	{
		try
		{
			byte[] recvBuf = new byte[5000];
			DatagramPacket packet = new DatagramPacket(recvBuf,recvBuf.length);
			dsock.receive(packet);
			int byteCount = packet.getLength();

			return new String(recvBuf, 0, byteCount);
		}
		catch (IOException e)
		{
			System.err.println("Exception:  " + e);
			return null;
		}
	}

	public void startReceiving()
	{
		String message = "";

		System.out.println("Waiting for packets on port " + port + " ...........");
		while(!message.equals("STOP"))
		{
			message = waitForPackets();

			System.out.println("Received: " + message);
		}
	}

	public static void main(String[] args)
	{
		Receiver recv = new Receiver(5001);


		recv.startReceiving();
	}

}
