/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsacrypt;


import rsacrypt.Screen;
import java.math.BigInteger;
import java.util.Random;
import javax.swing.JFrame;
//import java.util.Scanner;

/**
 *
 * @author DanielFreitas
 */
public class RSACrypt {
    
    public static Random rand = new Random();
                
        // The Prime Numbers;
    public static BigInteger prime_1;
    public static BigInteger prime_2;
        
        // Public Key
    public static BigInteger n;        
               
        // Euler Totient Number
    public static BigInteger phi;
    
        //e = Random relative prime to Phi   
    public static BigInteger e;
        // inverse = e ^(-1) mod n;
    public static BigInteger inverse;

 
        // Store the encrypted code;
    public static BigInteger code[];
       
    // ENCRYPT A MESSAGE USING 'e' and 'n'
    public static BigInteger[] encrypt (String s, BigInteger e, BigInteger n ){
        BigInteger[] code = new BigInteger[s.length()];
        for (int i = 0; i < s.length() ; i++ ){
                        
            BigInteger c = new BigInteger(""+(int)s.charAt(i));
            
            c = c.modPow(e,n);
                          
            code[i] = c;
        }
        
        System.out.println("");
        return code;
    }
    
    
    // DECRYPT A MESSSAGE USING 'n' and 'd'
    public static String decrypt(BigInteger[] code,BigInteger n, BigInteger inverse){
        String s = new String();
        for (int i = 0 ; i < code.length ; i++){
            char _new;
            BigInteger m = new BigInteger("" + code[i]);
            
            m = m.modPow(inverse, n);
            int value = Integer.parseInt(m.toString());
            _new = (char) value;
            
            s = s +_new;
        }

        return s;
    }
    
    
    // GET GREAT COMMON DIVISOR BETWEEN 2 NUMBERS
    public static BigInteger gcd (BigInteger a, BigInteger b){
        BigInteger r;
        while (b.compareTo(BigInteger.ZERO) != 0){
            r = a;
            a = b;
            b = r.mod(b);
        }
        
        return a;
    }
    
    // GET RELATIVE PRIME FOR AN 'a' NUMBER
    private static BigInteger RPrime (BigInteger a){
        Random rand = new Random();
        BigInteger e = new BigInteger (a.bitCount(),rand);
        
        while (gcd(a,e).compareTo(BigInteger.ONE) != 0){
            e = new BigInteger(a.bitCount(), rand);
        }
        return e;
    } 
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // GETTING A RANDOM NUMBER
        prime_1 = new BigInteger(128,rand);
        prime_2 = new BigInteger(128,rand);
        
        
        // CHECKING IF PRIME
        while(!prime_1.isProbablePrime(7)){
            prime_1 = new BigInteger(128,rand);
            
        }
        // CHECKING IF PRIME
        while(!prime_2.isProbablePrime(7)){
            prime_2 = new BigInteger(128,rand);          
        }
        
        // GETTING VALUES : N,PHI,E and D for RSA Encryption
        
        // n = prime_1 * prime_2;
        n = prime_1.multiply(prime_2);
                   
        
        // phi = (prime_1 - 1) * (prime_2 -1);
        phi = (prime_1.subtract(BigInteger.ONE)).multiply(prime_2.subtract(BigInteger.ONE));
      
        
        //e = Random relative prime to Phi        
        e = RPrime(phi);
        
        // d : d.e = 1 (mod n)
        inverse = e.modInverse(phi);
        
        // OPEN GUI
        new Screen().setVisible(true);
        
       
       
       // NO-GUI TEST
        /*
        Scanner input = new Scanner(System.in);
        String text = input.nextLine();
        
        
        System.out.println("--------------------------");
        System.out.println("Initial Message : "+ text);
        
        
      
        BigInteger code[] = encrypt(text, e, n);        
        
        System.out.println("Encrypted Message : " );
        for (int i = 0; i < code.length; i++){
            System.out.print(code[i] + " ");
            if (i%3 == 2) System.out.println("");
        }
        
        System.out.println("");
        System.out.println("");
        
        String output = decrypt(code, n ,inverse);
        System.out.println("Decrypted Message : "+ output);
        
        */
    }

    // GETTER AND SETTERS
    public static BigInteger getPrime_1() {
        return prime_1;
    }

    public static void setPrime_1(BigInteger prime_1) {
        RSACrypt.prime_1 = prime_1;
    }

    public static BigInteger getPrime_2() {
        return prime_2;
    }

    public static void setPrime_2(BigInteger prime_2) {
        RSACrypt.prime_2 = prime_2;
    }

    public static BigInteger getN() {
        return n;
    }

    public static void setN(BigInteger n) {
        RSACrypt.n = n;
    }

    public static BigInteger getPhi() {
        return phi;
    }

    public static void setPhi(BigInteger phi) {
        RSACrypt.phi = phi;
    }

    public static BigInteger getE() {
        return e;
    }

    public static void setE(BigInteger e) {
        RSACrypt.e = e;
    }

    public static BigInteger getInverse() {
        return inverse;
    }

    public static void setInverse(BigInteger inverse) {
        RSACrypt.inverse = inverse;
    }
    
    public static BigInteger[] getCode() {
        return code;
    }

    public static void setCode(BigInteger[] code) {
        RSACrypt.code = code;
    }
    
}
