package edu.ap.spring.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import edu.ap.spring.jpa.JokeRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Controller
@Scope("session")
public class JokeController {
   
   public JokeController() {
   }

   JokeRepository repository;

   @Autowired
   public void setJokeRepository(JokeRepository repository){
       this.repository = repository;
   }

   @RequestMapping("/joke")
   @ResponseBody
   public String joke(@RequestParam("firstname") String firstName,
                      @RequestParam("lastname") String lastName) {
        String jokeJson = "";

       try {
           jokeJson = jsonGetRequest(firstName, lastName);
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }

       JSONObject jsonObject;
       try {
           jsonObject = new JSONObject(jokeJson);
           String j =  jsonObject.get("value").toString();
           jsonObject = new JSONObject(j);
           String joketxt = jsonObject.get("joke").toString();


           List<String> l = repository.findAll();
           if (!l.contains(joketxt)) {
               repository.save(joketxt);
           }
           return joketxt;
       } catch (JSONException e) {
           return e.getMessage();
       }

   }
		   
   @RequestMapping("/joke_post")
   @ResponseBody
   public String joke_post() {

       return null;

   }
   
   @RequestMapping("/")
   public String root() {
	   return "redirect:/joke";
   }

    public static String jsonGetRequest(String firstName, String lastName) throws Exception {
        String url = "http://api.icndb.com/jokes/random?firstName=" + firstName + "&lastName=" + lastName;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print in String
        return response.toString();
    }
}
