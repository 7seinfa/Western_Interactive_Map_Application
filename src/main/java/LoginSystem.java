/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.western_interactive_map_application;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
 
/**
 *
 * @author sunny
 */
public class LoginSystem {

    
    public boolean CreateUser(String Username,String Password,boolean isAdmin){

        JSONObject UserInfo = new JSONObject();
        UserInfo.put("Username", Username);
        UserInfo.put("Password", Password);
        UserInfo.put("isAdmin", isAdmin);
        
        //Add user
        JSONArray UserList = new JSONArray();
        UserList.add(UserInfo);
         
        //Write JSON file
        try (FileWriter file = new FileWriter("Users.json")) {
            file.write(UserList.toJSONString()); 
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean ValidateUser(String Username,String Password){
    
    
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("Users.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray UserList = (JSONArray) obj;
            System.out.println(UserList);
             
            //Iterate over employee array
           UserList.forEach( emp -> parseUser( (JSONObject) emp,Username,Password ) );
          
           
           //////////////////////stuck


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
    return false;
    }   
    
 private static boolean parseUser(JSONObject Users,String Name,String Pass) 
    {
       
         
        //Get employee first name
        String Username = (String) Users.get("Username");    
//        System.out.println(Username);
         
        //Get employee last name
        String Password = (String) Users.get("Password");  
 //       System.out.println(Password);
         
        //Get employee website name
        Boolean isAdmin = (Boolean) Users.get("isAdmin");    
//        System.out.println(isAdmin);
        
        if (Username.equals(Name)&&Password.equals(Pass)){
            System.out.println("Username: "+Name);
            System.out.println("Password: "+Pass);
            
        return true;
        }else{
            System.out.println("Username: "+Name);
            System.out.println("Password: "+Pass);
            System.out.println("Does not match record.");
        }
        return false;
    }
    
    
    
    
    
    
    
    
    
    
    public static void main(String args[]) {
        // TODO code application logic here
        LoginSystem LS=new LoginSystem();
        LS.CreateUser("Sunny","abcd",true);
        LS.ValidateUser("Sunny","abcd");
        LS.ValidateUser("Li","cdf");
        LS.CreateUser("Li","cdf",true);
        LS.ValidateUser("Li","cdf");
        LS.ValidateUser("Sunny","abcd");
    }
}
