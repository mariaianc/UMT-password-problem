import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int MinimumChange = 0;
        Scanner sc = new Scanner(System.in);
        String password = sc.nextLine();

        //if the password has less than 6 characters, I have to add characters until the length will be 6
        //so the minimum change is 6 - length of initial password
        if (password.length() < 6) {
            MinimumChange+=6-password.length();
        }

        //if the password has more than 20 characters, I have to subtract characters until the length will be 20
        //so the minimum change is initial password length - 20
        else if (password.length()>20)
        {
            MinimumChange+=password.length()-20;
        }

        int upperCase = 0,lowerCase = 0, digit = 0;//assume that in the password are not any upper/lower case or any digit
        int lengthOfSequence = 1; //in this variable we put the length of a sequence of repeating characters
        char previous_char= password.charAt(0);//this is the first character in the sequence
        int modificationSequence = 0;//assume that it doesn't exist any sequence of repeating characters

        for(int i = 0; i < password.length(); i++){

            //if there exist a lower/upper case or a digit, mark it as found and it result at each one if it will be
            //a modification (=0) or not (=1)
            if(Character.isLowerCase(password.charAt(i))){ 
                lowerCase = 1;
            }
            else if(Character.isUpperCase(password.charAt(i))){
                upperCase = 1;
            }
            else if(Character.isDigit(password.charAt(i))){
                digit = 1;
            }

            //here I compute the length of every sequence of repeating characters
            //have to make a change every time we find a group of 3 characters, so we add the minimum number of changes
            // which is equal with the length of the sequence/3
            if(i>0 && i<password.length())
            {
                if(password.charAt(i)==previous_char) {
                    lengthOfSequence++;
                    previous_char = password.charAt(i);
                }
                else
                {
                    previous_char = password.charAt(i);
                    modificationSequence = modificationSequence + lengthOfSequence / 3; //=how many characters need replacement
                    lengthOfSequence = 1;
                }
                if(lengthOfSequence>=3) modificationSequence = modificationSequence+lengthOfSequence/3;
            }
        }
        MinimumChange+=3-(upperCase+lowerCase+digit)+modificationSequence;
        System.out.println(MinimumChange);
    }
}