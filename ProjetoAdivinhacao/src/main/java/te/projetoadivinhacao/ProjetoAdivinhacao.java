/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package te.projetoadivinhacao;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author dcet1-lamii11
 */
public class ProjetoAdivinhacao {

    public static void main(String[] args) {
        Random rand = new Random();
        int random_number = rand.nextInt(100);
        Scanner reader = new Scanner(System.in);
        for(int i=0;i<5;i++){
            System.out.println("Você tem "+(5-i)+" tentativas\n");
            System.out.println("\nInsira um chute: \n");
            int guess_number = reader.nextInt();
            if (guess_number > random_number) {
                System.out.println("\nMuito alto\n");
            } else if (guess_number < random_number) {
                System.out.println("\nMuito baixo\n");
            } else {
                System.out.println("\nAcertou\n");
                break;
            }
            
        }
    }
}
