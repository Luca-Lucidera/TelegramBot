/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libapitelegram;



/**
 *
 * @author lucid
 */
public class Message {
    private int message_id;
    private From from;
    private Chat chat;
    private int date;
    private String text;

    public Message(int message_id, From from, Chat chat, int date, String text) {
        this.message_id = message_id;
        this.from = from;
        this.chat = chat;
        this.date = date;
        this.text = text;
    }

    public int getMessage_id() {
        return message_id;
    }

    public From getFrom() {
        return from;
    }

    public Chat getChat() {
        return chat;
    }

    public int getDate() {
        return date;
    }

    public String getText() {
        return text;
    }
    
}
