import java.io.*;
public class prova
{
	public static void main(String[] dax) throws Exception
	{
		FileOutputStream f = new FileOutputStream("saves/default.dat");
		ObjectOutputStream fout = new ObjectOutputStream(f);
		fout.writeObject(new Damone("Bianca",true));
		fout.flush();
		f.close();
		FileInputStream f2 = new FileInputStream("saves/default.dat");
		ObjectInputStream fin = new ObjectInputStream(f2);
		System.out.println(((Pedina) fin.readObject()).getPath());

		FileWriter x = new FileWriter("ciao.dat");
	}
}