import java.lang.Math;
import java.util.Map;;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class CipherTextGenerator{
    String plainText = "Unitialised";
    String cipherText = "Unitialised"; 
    String substitutionAlphabet = "Unitialised";
    String keyText = "Unitialised";
    HashMap<Character,Character> keyMap = new HashMap<Character,Character>();
    
    public CipherTextGenerator(String plainTextPass){
        plainText = plainTextPass;
        this.randomiseSubstitutionAlphabet();
        String output = "";
        for(int i = 0; i < plainTextPass.length(); i++){  
            // System.out.println("I = " + i + " / " + plainTextPass.length());
            // System.out.println("Contains Key = " + keyMap.containsKey(new Character(plainTextPass.charAt(i))));
            // System.out.println("Contains Key = " + keyMap.containsKey(plainTextPass.charAt(i)));
            // System.out.println("Key To Look For = " + plainTextPass.charAt(i));
            // System.out.println("KeySet = \n " + keyMap.entrySet());
            if(Character.isLetter(plainTextPass.charAt(i))){
                output = output.concat(Character.valueOf(keyMap.get(new Character(plainTextPass.charAt(i)))).toString());
            }
            else{
                output = output.concat(Character.toString(plainTextPass.charAt(i)));
            }
            // System.out.println("[" + plainTextPass + "]");
            // System.out.println("[" + output + "]");
        }
        System.out.println("Output of String");
        cipherText = output;
    }

    public void randomiseSubstitutionAlphabet(){
        
        //Pass number of times to randomise the plain text
        String rawAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String output = "";
        ArrayList<Character> alphabetList= new ArrayList<Character>(); 
        for(int i = 0; i < rawAlphabet.length(); i++){
            alphabetList.add(rawAlphabet.charAt(i));  
        }

        //Create Substition Alphabet
        System.out.println("Initialising Substition Alphabet");
        while(alphabetList.size() > 0){
            output = output.concat(alphabetList.remove((int)(Math.random() * alphabetList.size())).toString());
            System.out.println("[" + alphabetList.size() + "] -  [" + output + "]");
        }
        keyText = output;

        System.out.println("\n" + "New Substition Alphabet = " + output);
        System.out.println("\n" + "Size of Substitution Alphabet = " + output.length());

        System.out.println("\n" + "Initialising Key Map");
        //keyMap.put(new Character(" ".charAt(0)), new Character(" ".charAt(0)));
        for(int i = 0; i < output.length(); i++){
            System.out.println("[" + (rawAlphabet.charAt(i) + "] -> [" + output.charAt(i)) + "]");   
            keyMap.put(new Character(Character.toUpperCase(rawAlphabet.charAt(i))), new Character(Character.toUpperCase(output.charAt(i))));
            keyMap.put(new Character(Character.toLowerCase(rawAlphabet.charAt(i))), new Character(Character.toLowerCase(output.charAt(i))));
        }
    }

    public void writeResultsToFiles(){
        try{
            FileWriter cipherWriter = new FileWriter("ENCODED" + ".txt", true);
            cipherWriter.write(this.cipherText);
            cipherWriter.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{
            FileWriter keyWriter = new FileWriter("KEY" + ".txt", true);
            keyWriter.write(this.keyText);
            keyWriter.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{
        FileWriter plainWriter = new FileWriter("PLAIN" + ".txt", true);
        plainWriter.write(this.plainText);
        plainWriter.close();    
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main (String[] args){
        Scanner Input = new Scanner(System.in);
        System.out.print("Please Enter Plain Text: ");
        String plainTextInput = Input.nextLine();
        CipherTextGenerator Generator = new CipherTextGenerator(plainTextInput);
        System.out.println("Cipher Text = " + Generator.cipherText);

        Generator.writeResultsToFiles();   
    }
}