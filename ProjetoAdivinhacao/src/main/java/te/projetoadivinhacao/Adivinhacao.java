/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package te.projetoadivinhacao;

/**
 *
 * @author dcet1-lamii11
 */
public class Adivinhacao {

    public Adivinhacao(int random_number, int user_id) {
        this.random_number = random_number;
        this.guesses = 5;
        this.user_id = user_id;
    }
    
    private int random_number;
    private int guesses;
    private int user_id;

    public int getRandom_number() {
        return random_number;
    }

    public void setRandom_number(int random_number) {
        this.random_number = random_number;
    }

    public int getGuesses() {
        return guesses;
    }

    public void setGuesses(int guesses) {
        this.guesses = guesses;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
}
