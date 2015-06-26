package pack;

import java.io.Serializable;

public class Produs implements Serializable
{

	public int id;
	public double price;
	public double quantity_on_hand;
	
	
	public Produs()
	{
		this.id = -1;
		this.price = 0;
		this.quantity_on_hand = 0;
		
	}
	
	public Produs(int id, double price, double quantity_on_hand) 
	{
		super();
		this.id = id;
		this.price = price;
		this.quantity_on_hand = quantity_on_hand;
	}
	
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public double getPrice() 
	{
		return price;
	}
	public void setPrice(double price) 
	{
		this.price = price;
	}
	public double getQuantity_on_hand() 
	{
		return quantity_on_hand;
	}
	public void setQuantity_on_hand(double quantity_on_hand) 
	{
		this.quantity_on_hand = quantity_on_hand;
	}
	
	public double totalPrice()
	{
		double sum = 0;
		sum = price*quantity_on_hand;
		return sum;
	}
	
}
