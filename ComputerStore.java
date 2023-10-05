

import java.util.Scanner;

public class ComputerStore {

    public static void main(String[] args) {
        int maxComputers;
        int choice1;
        final String PASSWORD = "password";
        Scanner kb = new Scanner(System.in);
        System.out.println("Welcome to the Computer Store");
        // if the max computer is <=0 then i am running this loop until positive number is entered
        do {
            System.out.println("How many computers do you want?");
            maxComputers = kb.nextInt();
            if (maxComputers <= 0) {
                System.out.println("Please enter a positive inventory count");
            }
        } while (maxComputers <= 0);
        
        //inv array is created based on maxcomputers 
        Computer[] inventory = new Computer[maxComputers];
        inventory[0] = new Computer();

        boolean check = true;
        //do while is run until person want to quit that is 5
        do {
            System.out.println("What do you want to do?\n" +
                    "1. Enter new computers (password required)\n" +
                    "2. Change information of a computer (password required)\n" +
                    "3. Display all computers by a specific brand\n" +
                    "4. Display all computers under a certain price.\n" +
                    "5. Quit\n" +
                    "Please enter your choice:");
            choice1 = kb.nextInt();
             
            // switch is used to write cases
            switch (choice1) {
                case 1:
                	//checking the password
                    System.out.println("Enter the password:");
                    String passwordInput = kb.next();
                    boolean passCheck = checkpassword(passwordInput, PASSWORD);
                    // if password is correct below statements are executed
                    if (passCheck) {
                    	//createdComputers is the num of computers created in array
                    	int createdComputers = inventory[0].findNumberOfCreatedComputers();
                    	
                    	//compadd takes how many computers user wants to add
                    	System.out.println("Enter the number of computers you want to add:");
                    	int compadd = kb.nextInt();
                    	
                    	//if compadd is less than maxcomputers i am adding computers 
                        if (compadd <= maxComputers) {
                        	//running for loop compaddd times
                        	for(int i=0;i<compadd;i++) {
                            System.out.println("Enter the brand:");
                            String brand = kb.next();
                            System.out.println("Enter the model:");
                            String model = kb.next();
                            System.out.println("Enter the price:");
                            double price = kb.nextDouble();
                            //createComputer+i is in order to add computers in next position
                            inventory[createdComputers+i] = new Computer(brand, model, price);
                            System.out.println("Computer added successfully.");
                        	}
                        } 
                        //if compadd is greater than maxcomputers, printing inventory is full
                        else {
                            System.out.println("Inventory is full. Cannot add more computers.");
                        }
                    } 
                    //if passcheck is false, printing incorrect password
                    else {
                        System.out.println("Incorrect password. Access denied.");
                    }
                    break;

                case 2:
                	//checking the password
                	System.out.println("Enter the password:");
                    passwordInput = kb.next();
                    passCheck = checkpassword(passwordInput, PASSWORD);
                    
                    //if password is correct below statements are executed
                    if(passCheck) {                    
	                    System.out.println("Enter the position number of the computer to update:");
	                    int position = kb.nextInt();
	                    int createdComputers = inventory[0].findNumberOfCreatedComputers();
	                    
	                    //if position is greater than createdcomputers then, printing no computer in that location
	                    if(position > createdComputers) {
	                    	System.out.println("No computer at this location");
	                    	break;
	                    }
	                    //displaying computer details id position is valid
	                    else {
	                    	inventory[position-1].displayComputer(inventory[position-1]);
	                    }
	                    int val = 0;
	                  //do while is run until person want to quit that is 5
	                    do {
	                    	System.out.println("What information would you like to change?\n" + "1.brand\n" + "2.model\n"
									+ "3.SN\n" + "4.price\n" + "5.Quit\n" + "Enter your choice:" + "");
							val = kb.nextInt();
							switch(val) {
								case 1:
									//to update brand
									System.out.print("Enter the brand name you want to update: ");
									String br = kb.next();
									inventory[position - 1].setBrand(br);
									break;
								case 2:
									//to update model
									System.out.print("Enter the model name you want to update: ");
									String md = kb.next();
									inventory[position - 1].setModel(md);
									break;
								case 3:
									//to update serial number
									System.out.print("Enter the SN you want to update: ");
									long sn = kb.nextLong();
									inventory[position - 1].setSN(sn);
									break;
								case 4:
									//to update price
									System.out.print("Enter the price you want to update: ");
									double pr = kb.nextDouble();
									inventory[position - 1].setPrice(pr);
									break;
								case 5:
									//quitting option 2
									System.out.println("Quitting 2");
									break;
							}
	                    
	                    }while(val != 5);
                    }
                    //if incorrect password is entered
                    else {
                        System.out.println("Incorrect password. Access denied.");
                    }
                    
                    break;

                case 3:
                	//finding computers by given brand
                	System.out.println("Enter a brand name:");
                    String brandName = kb.next();
                    findComputersByBrand(inventory, brandName);
                    break;

                case 4:
                	//finding computer by given price
                    System.out.println("Enter the price to find computers cheaper than:");
                    double prz = kb.nextDouble();
                    findCheaperThanPrice(inventory, prz);
                    break;

                case 5:
                	//quitting from mainmenu
                    check = false;
                    System.out.println("Thank you. Have a good day!");
                    break;
            }

        } while (check);
    }
    //function to check password 
    public static boolean checkpassword(String pswd, String PASSWORD) {
    	Scanner kb = new Scanner(System.in);
    	int count = 1; 	    	
    	if(pswd.equals(PASSWORD)) {
    		return true;
    	}
    	else {
    		do {
    			System.out.println("Enter the password:");
                pswd = kb.next();
                count++;
    		}while(!pswd.equals(PASSWORD) && count <3 );
    	}
    	//if password is wrong 3 times returning false else returning true
    	if(count <= 3 && pswd.equals(PASSWORD))
    	{
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    //function to find computers by given brand 
    public static void findComputersByBrand(Computer[] inventory, String brandName) {
        int createdComputers = inventory[0].findNumberOfCreatedComputers();
        for (int i = 0; i < createdComputers; i++) {
            if (inventory[i].getBrand().equals(brandName)) {
                inventory[i].displayComputer(inventory[i]);
            }
        }
    }
    
    //function to find computers by given price
    public static void findCheaperThanPrice(Computer[] inventory, double price) {
        int createdComputers = inventory[0].findNumberOfCreatedComputers();
        for (int i = 0; i < createdComputers; i++) {
            if (inventory[i].getPrice() <= price) {
                inventory[i].displayComputer(inventory[i]);
            }
        }
    }
}
