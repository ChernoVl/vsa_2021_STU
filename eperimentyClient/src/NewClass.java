
import proxy.NewJerseyClient;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vsa
 */
public class NewClass {

    public static void main(String[] args) {
        NewJerseyClient client = new NewJerseyClient();
        Ponuka pon = client.getMenu(Ponuka.class, "pondelok")
        client.close();
    }

}
