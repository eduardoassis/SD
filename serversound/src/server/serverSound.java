package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class serverSound 
{
	public static void main(String args[])
	{
		ServerSocket socket = null;
		Socket conn = null;
		try
		{
			//Criando um server socket- 1 parametro é a porta e 2nd parametro é o backlog
			socket = new ServerSocket(4545,10);
			//Esperar pela conexao
			echo("Esperando por uma conexao de um cliente...");
			while(true)
			{
				//Pega a conexao socket 
				conn = socket.accept();
				//Escrevendo o hostanem + porta da conexao
				echo("Conexao recebida por "+ conn.getInetAddress().getHostName()+ " : "+ conn.getPort());
				//Colocando a conexao em thread
				new clientHandler(conn).start();
			}
		}
		catch(IOException e)
		{
			System.err.println("Unable to close. IOexception");
		}
		try
		{
			socket.close();
		}
		catch(IOException ioException)
		{
			System.err.println("Unable to close. IOexception");
		}
	}

	public static void echo(String msg)
	{
		System.out.println(msg);
	}
}
