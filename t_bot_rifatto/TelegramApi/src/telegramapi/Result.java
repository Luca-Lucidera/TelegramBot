/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegramapi;

/**
 *
 * @author lucid
 */
public class Result {
    private int update_id;
    private Message message;
    
    public Result(){
        update_id = -1;
    }
    public Result(int update_id, Message message) {
        this.update_id = update_id;
        this.message = message;
    }

    public int getUpdate_id() {
        return update_id;
    }

    public Message getMessage() {
        return message;
    }
    
}
