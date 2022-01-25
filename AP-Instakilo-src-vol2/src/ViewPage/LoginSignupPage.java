package ViewPage;

import UserManagment.User;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Scanner;

public class LoginSignupPage {
    private static Scanner scanner = new Scanner(System.in);

    private static void showMenu() {
        try {
            Runtime.getRuntime().exec("cls");
        } catch (IOException ignore) {
        }
        System.out.println("\u001B[34m"  + "----------------------------------------");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("            Hello there!                ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\u001B[0m");
        System.out.println("1. Login here!");
        System.out.println("2. Don't have an account, make one!");
        System.out.println("3. Exit");

    }

    private static int optionChoosing1(){
        try{
            int option = scanner.nextInt();
            if (10 <= option){
                scanner.nextLine();
                return option;
            }
            System.out.println("\u001B[34m" + "Option out of range! Try again!" + "\u001B[0m");
            scanner.nextLine();
            return optionChoosing();

        }
        catch (Exception e){
            System.out.println("\u001B[34m" + "Invalid Input!" + "\u001B[0m");
            scanner.nextLine();
        }
        return optionChoosing();

    }


    private static int optionChoosing(){
        System.out.println("Enter your choice: ");
        try{
            int option = scanner.nextInt();
            if (1 <= option && option <=3){
                scanner.nextLine();
                return option;
            }
            System.out.println("\u001B[34m" + "Option out of range! Try again!" + "\u001B[0m");
            scanner.nextLine();
            return optionChoosing();

        }
        catch (Exception e){
            System.out.println("\u001B[34m" + "Invalid Input!" + "\u001B[0m");
            scanner.nextLine();
        }
        return optionChoosing();

    }
    
    private static int PasswordStrengthCheck(String password){
        
        //total score of password
        int iPasswordScore = 0;
        
        if( password.length() < 8 )
            return 0;
        else if( password.length() >= 10 )
            iPasswordScore += 2;
        else 
            iPasswordScore += 1;
        
        //if it contains one digit, add 2 to total score
        if( password.matches("(?=.*[0-9]).*") )
            iPasswordScore += 2;
        
        //if it contains one lower case letter, add 2 to total score
        if( password.matches("(?=.*[a-z]).*") )
            iPasswordScore += 2;
        
        //if it contains one upper case letter, add 2 to total score
        if( password.matches("(?=.*[A-Z]).*") )
            iPasswordScore += 2;    
        
        //if it contains one special character, add 2 to total score
        if( password.matches("(?=.*[~!@#$%^&*()_-]).*") )
            iPasswordScore += 2;
        
        return iPasswordScore;    
    }
    
    public static void run() throws NoSuchAlgorithmException {
        User user;
        while (true){
            showMenu();
            int choice = optionChoosing();
            if (choice == 3){
                return;
            }
            if (choice == 1){
                System.out.println("\u001B[34m"  + "----------------------------------------" + "\u001B[0m");
                System.out.print("Enter your user name: ");
                String userName = scanner.nextLine();
                System.out.print("Enter your password: ");
                String passWord = scanner.nextLine();
                user = User.logIn(userName, passWord);
                if (user == null){
                    System.out.println("\u001B[31m" + "User not found!" + "\u001B[0m");
                    run();
                }
                else{
                    System.out.println("\u001B[32m" + "++++++++++++++++++++++++++++++++++++++++" + "\u001B[0m");
                    System.out.println("\u001B[32m" + "Welcome back " + user.getUserName() +"!" + "\u001B[0m");
                    System.out.println("\u001B[32m" + "++++++++++++++++++++++++++++++++++++++++" + "\u001B[0m");
                }
                UserPage.run(user);
            }
            else if (choice == 2){
                System.out.println("\u001B[34m"  + "----------------------------------------" + "\u001B[0m");
                System.out.print("Enter a user name: (This is what we call your here!) ");
                String userName = scanner.nextLine();
                System.out.print("Enter a password: ");
                String passWord = scanner.nextLine();
                while (true)
                {
	                int passStrength = PasswordStrengthCheck(passWord) ;
	                String agreed ;
	                if (passStrength <=4)
	                {
	                	System.out.println("Your password is weak :/ enter another one : ");
	                	passWord = scanner.nextLine() ;
	                }
	                else
	                {
	                	System.out.println("Your passWord strength is " + passStrength + "out of 10 ");
	                	System.out.println("Do you want to keep this one ? 1:Yes 2:No");
	                	agreed = scanner.nextLine() ;
	                	if ( agreed.equals("1"))
	                	{
	                		break ;
	                	}
	                	else
	                	{
	                		System.out.println("Enter another one then :");
	                		passWord = scanner.nextLine() ;
	                	}
	                 }
                }
                user = User.signUp(userName, passWord);
                if (user == null){
                    System.out.println("\u001B[31m" + "User name not available!" + "\u001B[0m");
                    run();
                }
                else{
                    System.out.println("Spread your story through the world! Enter your bio: ");
                    String Bio = scanner.nextLine();
                    user.setBio(Bio);
                    System.out.print("Enter your age: ");
                    int age = optionChoosing1();
                    user.setAge(age);
                    System.out.print("Enter your email address: ");
                    String email = scanner.nextLine();
                    user.setEmail(email);
                    System.out.print("Enter your phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.println("\u001B[32m" + "++++++++++++++++++++++++++++++++++++++++" + "\u001B[0m");
                    System.out.println("\u001B[32m" + "Welcome " + user.getUserName() +"!" + "\u001B[0m");
                    System.out.println("\u001B[32m" + "++++++++++++++++++++++++++++++++++++++++" + "\u001B[0m");
                }
                
                UserPage.run(user);
            }
        }
    }
}
