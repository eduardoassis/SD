package client;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import javazoom.jl.player.Player;

public class mp3 extends Thread{
	private Player player;
	private Socket socket;

	public void setSocket(Socket socket)
	{
		this.socket = socket;
	}
	//Construtor padrão
	public mp3(){}

	//Metodo para tocar o MP3
	public void play()
	{
		try{
			InputStream is = socket.getInputStream();
			BufferedInputStream buffer = new BufferedInputStream(is);
			this.player = new Player(buffer);
		}
		catch(Exception e)
		{
			System.out.println("Problema ao Tocar!");
			e.printStackTrace();
		}
		new Thread() {
			public void run() {
				try {
					player.play();
				}
				catch (Exception e) { System.out.println(e); }
			}
		}.start();
	}

	public void setFim() throws IOException {  
		player.close();
		this.socket.close();
		
		//Preciso verificar o problema na hora que fecha....pois nao posso ficar fechando e abrindo socket 
		//toda vez que preciso tocar uma musica
	} 

}
