package client;



import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class principal {
	public static void main(String[] args) {
		try{
			Socket socket = null;
			socket = new Socket("localhost", 4545);


			//Chamado do metodo received com o parametro sendo o socket
			//UReceived received = new UReceived();
			mp3 player = new mp3();
			
			while(true)
			{
				System.out.println("1 - Play");
				System.out.println("2 - Stop");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String resp = br.readLine();
				switch (Integer.parseInt(resp)) {
				case 1:
					//Codigo 1 para mostrar as musicas....padronizar isso de alguma forma
					if(socket.isClosed())
					{
						socket = new Socket("localhost", 4545);
					}
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					out.writeUTF("1");


					//Pega a lista de String
					ObjectInputStream inObj = new ObjectInputStream(socket.getInputStream());
					List<String> listaMusicas = new ArrayList<String>();
					listaMusicas = (ArrayList<String>)inObj.readObject();
					for (int i = 1; i < listaMusicas.size(); i++) {
						System.out.println(i+" - " + listaMusicas.get(i).toString());
					}

					//Pega a musica escolhida
					System.out.println("Coloque o numero da musica indicada");
					BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
					String idMusicaEscolhida = buff.readLine();

					out.writeUTF(listaMusicas.get(Integer.parseInt(idMusicaEscolhida)));
					player.setSocket(socket);
					player.play();
					break;
				case 2 :
					player.setFim();
					break;

				default:
					break;
				}

			}
		}
		catch (Exception e)
		{
			System.out.println("Exception : " + e);
		}
	}   
	
	
}
