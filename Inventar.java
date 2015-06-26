package pack;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class Inventar {

	public static void main(String[] args) 
	{
		
		ArrayList<Produs> products = new ArrayList<Produs>();
		Scanner in = new Scanner(System.in);
		
		double sold = 0;
		int id = -1;
		double price = 0;
		double quantity = 0;
		double total = 0;
		int c = -1;
		int ok = 0;
	
		
		while (c != 0)
		{
			
			System.out.println("Introducei codul operatiei pe care doriti sa o realizati:");
			System.out.println("1. Adaugarea unui nou produs in depozit");
			System.out.println("2. Stergerea unui produs din baza de date");
			System.out.println("3. Vanzarea unui produs");
			System.out.println("4. Calculul inventarului");
			System.out.println("5. Schimbarea pretului unui produs");
			System.out.println("6. Citirea datelor din fisier");
			System.out.println("0. Parasirea meniului");
			c = in.nextInt();
			
			if (c == 1)
			{
				System.out.println("Introduceti id: ");
				id = in.nextInt();
				int verif = 0;
				for (int i = 0;i<products.size();i++)
				{
					if (id == products.get(i).getId())
						verif = 1;
				}
				if (verif == 0)
				{
					System.out.println("Introduceti pretul: ");
					price = in.nextDouble();
					System.out.println("Introduceti cantitatea: ");
					quantity = in.nextDouble();
				
					Produs p = new Produs(id,price,quantity);	
					products.add(p);
				}
				else
					System.out.println("Id ul produsului trebuie sa fie unic. Va rugam sa reluati operatia.");
				
			}
			
			if (c == 2)
			{
				System.out.println("Introduceti id - ul produsului pe care doriti sa il stergeti: ");
				id = in.nextInt();
				for (int i = 0;i < products.size();i++)
					if (products.get(i).getId() == id)
					{
						products.remove(i);
					}
			}
			
			if (c == 3)
			{
				System.out.println("Introduceti id - ul produsului pe care doriti sa il vindeti: ");
				id = in.nextInt();
				System.out.println("Introduceti cantitatea pe care doriti sa o vindeti: ");
				quantity = in.nextDouble();
				
				for (int i = 0;i < products.size();i++)
					if (products.get(i).getId() == id)
					{
						if (products.get(i).getQuantity_on_hand() < quantity)
							System.out.println("Cantitate insuficienta");
						else 
							{
								sold = products.get(i).price * quantity;
								quantity = products.get(i).quantity_on_hand - quantity;
								products.get(i).setQuantity_on_hand(quantity);
								ok = ok + 1;
							}
					}
				
				if (ok == 0)
				{
					System.out.println("Produs inexistent");
					ok = 0;
				}
			}
			
			if (c == 4)
			{		
				for (int i = 0;i < products.size();i++)
				{
					System.out.println("Produsul cu id - ul " + products.get(i).getId() +" este disponibil in cantitatea de: " + products.get(i).getQuantity_on_hand() + " la un pret de " + products.get(i).getPrice() + " pe unitate");
					total = products.get(i).totalPrice() + total;	
				}
			
				System.out.println("Suma totala a produselor ramase in depozit este: " + total);
				System.out.println("S-au vandut produse in valoare de: " + sold);
				double t = total + sold;
				System.out.println("Total: " + t);
				total = 0;
			}
			
			if (c == 5)
			{
				System.out.println("Introduceti id - ul produsului");
				id = in.nextInt();
				for (int i = 0;i < products.size();i++)
				{
					if (products.get(i).getId() == id)
					{
						ok = 1;
						System.out.println("Introduceti noul pret");
						price = in.nextDouble();
						products.get(i).setPrice(price);
					}
				}
				if (ok == 0)
				{
					System.out.println("Produs inexistent");
				}
			}
			
			if (c == 6)
			{
				try
				{
					FileInputStream fin = new FileInputStream("D:\\document1.txt");
					ObjectInputStream ois = new ObjectInputStream(fin);
					
					Produs p1 = null;
					
					while(fin != null)
					{
						p1 = (Produs) ois.readObject();
						int verif = 0;
						for (int i = 0;i<products.size();i++)
						{
							if (p1.getId() == products.get(i).getId())
							{
								verif = 1;
							}
						}
							if (verif == 0)
							{
								products.add(p1);
							}
					}
				
					ois.close();
					
				}
				catch(Exception e)
				{
					
				}
				
			}
				
		}
		
		try
		{
			FileOutputStream fout = new FileOutputStream("D:\\document1.txt",true);
			ObjectOutputStream oos = new ObjectOutputStream(fout);	
			for(int i = 0;i < products.size();i++)
			{
				oos.writeObject(products.get(i));
			}
			oos.close();
		}
		
		catch(Exception e)
		{
			System.out.println("Ceva nu a mers cum trebuie!");
		}

	}

}
