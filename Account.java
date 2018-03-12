/* 
 *  Program SklepApplication
 *  Autor: Roman Kovalchuk
 *   Data: 28 pażdziernika 2016 r.
 */

import java.io.Serializable;


public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String name;               // nazwa konta
	private String owner;              // nazwisko użytkownika
	private String adres;              // adres zamieszkania
	private long   passwordCode;       // kod hasła
	private double balance;            // saldo konta
	private double allbuy;             // utarg
	 
	Account(String name){
		this.name = name;
		passwordCode = 0;
		balance = 0;
		allbuy = 0;
	}
	
	
	


	public double getAllbuy() {
		return allbuy;
	}





	public void setAllbuy(double allbuy) {
		
		this.allbuy = allbuy;
		
	}





	public String getAdres() {
		return adres;
	}





	public void setAdres(String adres) {
		this.adres = adres;
	}





	public String getName(){
		return name;
	}
	
	
	public void setOwner(String owner){
		this.owner = owner;
	}
	
	
	public String getOwner(){
		return owner;
	}
	
	
	public boolean checkPassword(String password) {
		if (password==null) return false;
		return password.hashCode()==passwordCode;
	}
	
	
	public void setPassword(String oldPassword, String newPassword) throws Exception {
		if (!checkPassword(oldPassword)) throw new Exception("Błędne hasło");
		passwordCode = newPassword.hashCode(); 
	}
	
	
	public double getBalance(){
		return balance;
	}
	
	
	
	public void Allamount(double amount) throws Exception{
		allbuy += amount;
	}
	
	public void payIn(double amount) throws Exception {
		if (amount<0) throw new Exception("Wpłata nie może być ujemna");
		balance += amount;
	}
	
	public void payOut( double amount) throws Exception {
		if (amount>balance) throw new Exception("Brak środków na koncie");
		balance -= amount;
		
	}
	
	
	
	public String toString(){
		return String.format("   %s <%s> ", name, owner);
	}

	
}

