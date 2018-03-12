/* 
 *  Program SklepApplication
 *  Autor: Roman Kovalchuk
 *   Data: 28 pazdziernika 2016 r.
 *   
 *   
 */


class SklepApplication {
	
	public static void main(String[] args) {
		new SklepApplication();
	}
		
	
	private UserDialog UI = new ConsoleUserDialog();
	
	

	private static final String GREETING_MESSAGE =
			"Program Sklep_Internetowy - wersja konsolowa\n" +
			"Autor: Roman Kovalchuk\n" +
			"Data: 28 pażdziernika 2016 r.\n";

	private static final String Sklep_menu = 
			"Witamy w naszym sklepie \n" +
			"1 - Zaloguj się jako Sprzedawca \n" +
			"2 - Zarejestruj się \n" +
			"3 - Zaloguj się do konta jako klient \n" +
		    "0 - Zakończ program   \n";
	
	private static final String Admin_menu =
			"1 - Dodaj nowy towar \n"+
	        "2 - Przeglądanie dostępnych towarów \n"+
			"3 - Zmiana ceny wybranego towaru \n"+
	        "4 - Usunąć towar \n"+
			"5 - Utarg \n"+
	        "6 - Lista zarejestrowanych klientów \n"+
			"7 - Zmień hasło \n"+
	        "8 - Wyloguj się z konta \n";
	
	private static final String Klient_menu = 
           "1 - Doładuj konto \n" +
	       "2 - Stan konta \n" +
           "3 - Przeglądanie dostępnych towarów \n" +
	       "4 - Zakup wybranego towaru \n" +
           "5 - Zmień hasło \n" +
	       "6 - Usuń konto \n" +
           "7 - Wyloguj śię z konta \n";
	       
	        
		
	
		
	
	private static final String DATA_FILE_NAME = "SKLEP.BIN";
	
		
	private SklepInter sklep = new SklepInter();
	
	
	public SklepApplication() {
		UI.printMessage(GREETING_MESSAGE);
		
		try {
			
			sklep.loadSklepFromFile(DATA_FILE_NAME);
			UI.printMessage("Wszystkie konta i towary zostawy wczytane z pliku " + DATA_FILE_NAME);
		} catch (Exception e) {
			UI.printErrorMessage(e.getMessage());
		}
		
		while (true) {
			UI.clearConsole();

			try {
				
				switch (UI.enterInt(Sklep_menu + "==>> ")) {
				case 1:
					loginAccountAdmin();
					break;
				case 2:
					createNewAccount();
					break;
				case 3:
					loginAccount();
					break;
				case 0:
					try {
						
						sklep.saveSklepToFile(DATA_FILE_NAME);
						UI.printMessage("\nWszystko zostało zapisane do pliku " + DATA_FILE_NAME);
					} catch (Exception e) {
						UI.printErrorMessage(e.getMessage());
					}

					UI.printInfoMessage("\nProgram skończył działanie!");
					System.exit(0);
				}  
			} catch (Exception e) {
				UI.printErrorMessage(e.getMessage());
			}
		}
	}
	
	
	public  void listAllAccounts() {
		StringBuilder list = new StringBuilder("\nLista kont:\n");
		list.append(sklep.listAccounts());		
		UI.printMessage(list.toString());
	}
	
	
	    		
	 public  void listAllArticles() {
			StringBuilder list = new StringBuilder("\nlista towarów:\n");
			list.append(sklep.listArticles());		
			UI.printMessage(list.toString());
		}
			
    		
	 public void createNewArticle() throws Exception{
	    	String newNazwa = null;
	    	double newPrice;
	    	int newIlosc;
	    	Towar newTowar;
	    	
	    	
	    	UI.printMessage("\nDodawanie nowego towaru\n");
	   while(true){ 	
	    	newNazwa=UI.enterString("Wpisz towar:");
	    	if (sklep.findTowar(newNazwa)!=null){
	    		UI.printErrorMessage("Towar już dodany");
	    		continue;
	    	}
	    	newPrice = UI.enterDouble("Wpisz cenę:");
	    	newIlosc= UI.enterInt("Wpisz ilość towaru:");
	    	try{
	    		newTowar = sklep.createTowar(newNazwa);
	    		newTowar.setPrice(newPrice);
	    		newTowar.setIlosc(newIlosc);
	    		
	    			}catch (Exception e) {
				UI.printErrorMessage(e.getMessage());
				continue;
	    		
	    	}
	    		    	
	    	UI.printMessage("Towar dodany");
	    	break;
	   }
			
	 }		
	
		
	
    
	
	public  void createNewAccount() {	
		String newNick;
		String newPassword;
		Account newAccount;
		String newOwner;
		String newAdres;
		
		UI.printMessage("\nTworzenie nowego konta\n");
		while(true) {
			newNick = UI.enterString("Wybierz nazwę konta:");
			if (newNick.equals("")) return;  
			if (sklep.findAccount(newNick)!=null) {
				UI.printErrorMessage("Konto już istnieje");
				continue;
			}
			
			newPassword = UI.enterString("Podaj hasło:");
			newOwner=UI.enterString("Podaj nazwisko:");
			newAdres=UI.enterString("Podaj Adres:");
			try {
				newAccount = sklep.createAccount(newNick);
			newAccount.setPassword("", newPassword);
			newAccount.setOwner(newOwner);
			newAccount.setAdres(newAdres);
			} catch (Exception e) {
				UI.printErrorMessage(e.getMessage());
				continue;
			}
			UI.printMessage("Konto zostało utworzone");
			break;
		}
	}
	
	public void loginAccountAdmin() {
		String nick, password;
		Account account;
		 
		 
		

		UI.printMessage("\nLogowanie do konta Admina\n");

		nick = UI.enterString("Podaj login:");
		nick = "Admin";
		
		password = UI.enterString("Podaj hasło:");

		account = sklep.findAccount(nick, password);
		if (account == null) {
			UI.printErrorMessage("Błędne dane");
			return;
		}

		while (true) {
			UI.printMessage("\nJesteś zalogowany do konta ");
			UI.printMessage("     Nazwa konta: " + account.getName());
			UI.printMessage("Właściciel konta: " + account.getOwner());
			UI.printMessage("Adres zamieszkania:" + account.getAdres());
			try {

				switch (UI.enterInt(Admin_menu + "==>> ")) {
				case 1:
					createNewArticle();
					break;
				case 2:
					listAllArticles();
					break;
				case 3:
					changePrice();           
					break;
				case 4:
					remoweTowar();		
						
					break;
					
				case 5:
					UI.printMessage("  Utarg: " + sklep.getAllBuyForWholeShop());
					break;
				case 6:
					listAllAccounts();
					break;
				case 7:
					password = changePassword(account, password);
					break;
				case 8:
					account = null;
					UI.printMessage("Nastąpiło wylogowanie z konta");
					return;
				}
			} catch (Exception e) {
				UI.printErrorMessage(e.getMessage());
			}
		}
	}
		
	public void payMoney(Account account)throws Exception {
		double amount;
		UI.printMessage("\nWpłata");
		amount = UI.enterDouble("Podaj kwotę: ");
		account.payIn(amount);
	}
	
	public void buyTowar(Account account) throws Exception{
		
		Towar towar;
		String Utowar;
		int num;
		double amount;
		
		
	    
	   	UI.printMessage("\nKup towar");
		Utowar=UI.enterString("Wpisz nazwę towaru:\n");
		towar=sklep.findTowar(Utowar);
		  if(towar==null){
			  UI.printErrorMessage("Brak towaru");
		  }
		num= UI.enterInt("Podaj ilość:");
		towar.number(num);
		towar.getPrice();
		amount = num*(towar.getPrice());
		account.payOut(amount);
		
		
	  UI.printInfoMessage("Towar zakupiony");
	     
	    account.Allamount(amount);
		  
	}
	
	
	
  
	public void remoweTowar() throws Exception{
		String Utowar;
		String answer;
		Towar UsunTowar;
		UI.printMessage("\nUsunięcie towaru");
		Utowar=UI.enterString("Wpisz nazwę towaru:\n");
		UsunTowar=sklep.findTowar(Utowar);
		  if(UsunTowar==null){
			  UI.printErrorMessage("Brak towaru");
		  }
		  answer = UI.enterString("Zabrać ten towar?(Tak/Nie)");
			if(!answer.equals("Tak")){
				UI.printErrorMessage("\nTowar usunięty");
				
			}
		  
		sklep.remoweTowar(UsunTowar);
			UI.printInfoMessage("\nTowar usunięty");
			
	}
	
	public  void changePrice() throws Exception {
		Towar towar;
		double newPrice;
		String Utowar;
		
		
		UI.printMessage("\nZmiana ceny towaru");
		Utowar=UI.enterString("Wpisz nazwę towaru:\n");
		towar=sklep.findTowar(Utowar);
		  if(towar==null){
			  UI.printErrorMessage("Brak towaru");
		  }
		
		newPrice = UI.enterDouble("Podaj nową cenę: ");
		towar.setPrice(newPrice);
		
		UI.printInfoMessage("\n Cena została zmieniona");
		
	}
	
	public void loginAccount() {
		String nick, password;
		Account account;
		

		UI.printMessage("\nLogowanie do konta\n");

		nick = UI.enterString("Podaj login:");
		password = UI.enterString("Podaj hasło:");
        account = sklep.findAccount(nick, password);
        if (account == null) {
			UI.printErrorMessage("Błędne dane");
			return;
		}
		

		while (true) {
			UI.printMessage("\nJesteś zalogowany do konta ");
			UI.printMessage("     Nazwa konta: " + account.getName());
			UI.printMessage("Właściciel konta: " + account.getOwner());
			UI.printMessage("Adres zamieszkania:" + account.getAdres());
			

			try {

				switch (UI.enterInt(Klient_menu + "==>> ")) {
				case 1:
					payMoney(account);
					break;
				case 2:
					UI.printMessage("  Stan konta: " + account.getBalance());
					break;
				case 3:
					listAllArticles();
					break;
				case 4:
					buyTowar(account);
					break;
					
				case 5:
					password = changePassword(account, password);
					break;
				case 6:
					if (removeAccount(account, password) == false)
						break;
					account = null;
					return;
				
				
				case 7:
					account = null;
					UI.printMessage("Nastąpiło wylogowanie z konta");
					return;
				
				}
			} catch (Exception e) {
				UI.printErrorMessage(e.getMessage());
			}
		}
	}

	
	
	
	public  String changePassword(Account account, String password) throws Exception {
		String newPassword;
		
		UI.printMessage("\nZmiana hasła do kąta");
		newPassword = UI.enterString("Podaj nowe hasło: ");
		account.setPassword(password, newPassword);
		UI.printInfoMessage("\n Hasło zostało zmienione");
		return newPassword;
	}
	
	
	
	
	
	public boolean removeAccount(Account account, String password) throws Exception {
		String answer;

		UI.printMessage("\nUsuwanie konta");
		answer = UI.enterString("Czy na pewno usunąć to konto? (Tak/Nie)");
		if (!answer.equals("Tak")) {
			UI.printErrorMessage("\nKonto nie zostało usunięte");
			return false;
		}

		sklep.removeAccount(account);

		UI.printInfoMessage("\nKonto zostało usunięte");
		return true;
	}
	
	

}