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
public class Chat {
    private int id;
    private String first_name;
    private String last_name;
    private String username;
    private String type;

    public Chat(int id, String first_name, String type) {
        this.id = id;
        this.first_name = first_name;
        this.type = type;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
   

    public void setId(int id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }


    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    
    public String getType() {
        return type;
    }
    
}
