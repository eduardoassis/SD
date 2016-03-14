package server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class song {
	//Metodo para retornar todas as músicas no diretório
		public static List<String> getListSong()
		{
			List<String> lista = new ArrayList<String>();    
			String dir = "D:/musicaTeste/";
			File diretorio = new File(dir);
			File fList[] = diretorio.listFiles();
			for (int i = 0; i < fList.length; i++) {
				lista.add(fList[i].getName());
			}
			return lista;
		}
}
