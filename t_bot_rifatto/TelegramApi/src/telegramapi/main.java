/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package telegramapi;

import java.io.IOException;

/**
 *
 * @author lucid
 */
public class main {
    public static void main(String[] args) throws IOException{
        TelegramApi api = new TelegramApi();
        Result[] messaggi = api.getMessaggio("https://api.telegram.org/bot5260523883:AAGBdOFDWUl1_Enq4SYqbsVqxDrF5HqqXoM/getUpdates");
        if(messaggi[0].getUpdate_id() == -1){
            System.out.println("Nessun nuovo messaggio presente");
        }
        else if(messaggi[0].getMessage() == null){
            System.out.println("Non hai scritto il comando '/citta '");
        }
    }
    
}
