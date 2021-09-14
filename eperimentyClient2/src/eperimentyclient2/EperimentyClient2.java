package eperimentyclient2;

import proxy.NewJerseyClient;

public class EperimentyClient2 {

    public static void main(String[] args) {
        NewJerseyClient client = new NewJerseyClient();

        String str = client.findJedloInMenuForEachDay("gulas");
        System.out.println(str);
        
        client.close();
    }

}
