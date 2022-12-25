import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class SendToUrl {

    public static int passwordEquals(String params, String remaindHash) throws IOException {
        int ret = 0;
        URL url = new URL("https://api.pwnedpasswords.com/range/"+params);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.addRequestProperty("user-agent", "pwdApp");
        conn.connect();
        try(BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))){
            String line;
            while((line=in.readLine())!=null){
                if(remaindHash.toUpperCase().equals(line.split(":")[0])){
                    ret = Integer.parseInt(line.split(":")[1]);
                }
            }
        }
        return ret;
    }

    public static int emailEqual(String email) throws IOException {
        int ret = 0;
        String newEmail ="";
        for(int i = 0; i < email.length(); i++){
            if(email.charAt(i)=='@'){
                newEmail+="%40";
            }else{
                newEmail+=email.charAt(i);
            }
        }
        URL url = new URL("https://haveibeenpwned.com/unifiedsearch/"+newEmail);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //conn.addRequestProperty("user-agent", "pwdApp");
        conn.connect();
        try(BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))){
            String line;
            while((line=in.readLine())!=null){
                if(email.equals(line.split(":")[0])){
                    ret = Integer.parseInt(line.split(":")[1]);
                    System.out.println(line.split(":")[1]);
                }
                System.out.println(line);
            }
        }
        return ret;
    }
}
