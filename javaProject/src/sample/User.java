/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

/**
 *
 * @author Mukaffi
 */
public class User {
    
    private String username;
    private String password;

    User (String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    String getUsername() {return username;}
    String getPassword() {return password;}


}
