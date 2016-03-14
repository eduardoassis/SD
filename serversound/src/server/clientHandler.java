package server;


import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class clientHandler extends Thread  {

	private Socket conn;
	private DataInputStream in;
	private DataOutputStream out;
	private ObjectOutputStream outObj; 
	
	String dir = "D:/musicaTeste/";

	public clientHandler(Socket conn)throws IOException {
		this.conn= conn;
		in=new DataInputStream(conn.getInputStream());
	}

	public void run() {
		try
		{
				String mensagem = in.readUTF();
                System.out.println("Recebendo mensagem:["+mensagem+"]");
                int id = Integer.parseInt(mensagem);
                switch (id) {
				case 1:
					List<String> listaMusicas = new ArrayList<String>(); 
					listaMusicas = song.getListSong();
					outObj = new ObjectOutputStream(conn.getOutputStream());
					outObj.writeObject(listaMusicas);
 					String musicaEscolhida = in.readUTF();
					System.out.println("Musica escolhida foi:" + musicaEscolhida);
					sendSong(musicaEscolhida);
					break;
				default:
					break;
				}
			
			
		}
		catch(Exception e )
		{
			System.out.println("Erro na Thread do Server");
		}
	}
	
	//Metodo para o envio da musica
	public void sendSong(String nameSong)	
	{
		try
		{
			//Send welcome message to client
			System.out.println("Welcome to the Server"); 
			//Preciso colocar a musica a ser tocadas
			File mp3File = new File(dir + nameSong);
			FileInputStream fileIn = new FileInputStream(mp3File);
			//Cria o o array de bytes
			byte [] buffer = new byte[(int)mp3File.length()];
			BufferedInputStream bis = new BufferedInputStream(fileIn);
			bis.read(buffer,0,buffer.length); 
			OutputStream os = conn.getOutputStream();
			os.write(buffer, 0, buffer.length);
			os.flush();
			os.close();
			bis.close();
			//client disconnected, so close socket
			conn.close();

		}
		catch(IOException e)
		{
			System.out.println("IOException on socket : " + e);
		}
	}
	
	


}
