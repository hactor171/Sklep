/* 
 *  Program SklepApplication
 *  Autor: Roman Kovalchuk
 *   Data: 28 pażdziernika 2016 r.
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


class SklepInter implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Account> listOfAccounts = new ArrayList<Account>();
	private ArrayList<Towar> listOfArticles = new ArrayList<Towar>(); 
	
	
	public Account createAccount(String name) throws Exception {
		if (name==null || name.equals("")) throw(new Exception("Nazwa konta nie może być pusta"));
		if (findAccount(name)!=null) throw(new Exception("Konto już istnieje"));
		Account newAccount = new Account(name);
		listOfAccounts.add( newAccount );
		return newAccount;
	}
	
	public Towar createTowar(String nazwa) throws Exception {
		if (nazwa==null || nazwa.equals("")) throw(new Exception("Nazwa konta nie może być pusta"));
		Towar newTowar = new Towar(nazwa);
		listOfArticles.add( newTowar );
		return newTowar;
	}
	
	public void removeAccount(Account account) throws Exception {
		if (account==null) throw(new Exception("Brak konta"));
		listOfAccounts.remove(account);
	}
	
	public void remoweTowar(Towar towar) throws Exception{
		if(towar==null) throw(new Exception("Brak towaru"));
		listOfArticles.remove(towar);
	}
	public Account findAccount(String name) {
		for (Account account : listOfAccounts)
			if (account.getName().equals(name)) return account;
		return null;
	}
	
	public Towar findTowar(String nazwa){
		for (Towar towar : listOfArticles)
		if(towar.getNazwa().equals(nazwa)) return towar;
		
		
		return null;
	}
	   
	
	public Account findAccount(String name, String password) {
		Account account = findAccount(name);
		if (account!=null){
			if (!account.checkPassword(password)) account = null;
		}
		return account;
	}
	
	public double getAllBuyForWholeShop() {
		double allBuy = 0;
		for (Account ac: listOfAccounts) {
			allBuy += ac.getAllbuy();
		}
		
		return allBuy;
	}
	
	public String listAccounts(){
		StringBuilder sb = new StringBuilder();
		int n = 0;
		for (Account account : listOfAccounts){
			if (n++ != 0) sb.append("\n");		
			sb.append(account.toString());
		}
		return sb.toString();
	}
	
	
	void saveSklepToFile(String fileName) throws Exception {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
		out.writeObject(listOfAccounts);
		out.writeObject(listOfArticles);
		out.close();
	}
	
	
	void loadSklepFromFile(String fileName) throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
		listOfAccounts = (ArrayList<Account>)in.readObject();
		listOfArticles = (ArrayList<Towar>)in.readObject();
		in.close();
	}
    
	public String listArticles(){
		StringBuilder sb = new StringBuilder();
		int n = 0;
		for (Towar towar : listOfArticles){
			if (n++ != 0) sb.append("\n");		
			sb.append(towar.toString());
		}
		return sb.toString();
	}
	
	
	void saveTowarToFile(String fileName) throws Exception {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
		out.writeObject(listOfArticles);
		out.close();
	}
	
	
	void loadTowarFromFile(String fileName) throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
		listOfArticles = (ArrayList<Towar>)in.readObject();
		in.close();
	} 

	
	
}

