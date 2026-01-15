package mchien.code.helps;

import java.util.ArrayList;
import java.util.List;

import mchien.code.model.T;
import mchien.code.screen.MenuLogin;
import mchien.code.screen.screen.GameScr;

public class loadIP {
    public static void load(){
        try{
            String text = HttpClient.get("http://data.quytuanhhung.com/serverlist.txt");
            loadIP(text);
//            loadIP("Máy chủ 1:sv1.ngulongplus.com:19129\nLocal:127.0.0.1:19129\nLocal2:192.168.1.137:19129");
        }catch (Exception e){
            loadIP("Máy chủ 1:sv1.ngulongplus.com:19129\nLocal:127.0.0.1:19129\nLocal2:192.168.1.137:19129");
        }
    }

    public static void loadIP(String textIP){
        String[] listIP = textIP.split("\n");
        List<String> names = new ArrayList<>();
        List<String> hosts = new ArrayList<>();
        List<String> ports = new ArrayList<>();

        for(int i=0; i< listIP.length; i++){
            String t = listIP[i].trim();
            if(t.isEmpty() || t.split(":").length < 3)
                continue;
            String name = t.split(":")[0];
            String host = t.split(":")[1];
            String port = t.split(":")[2];

            names.add(name);
            hosts.add(host);
            ports.add(port);
        }

        GameScr.SERVER_ID = new byte[hosts.size()];
        T.serverName = names.toArray(new String[0]);
        GameScr.SV_IP = hosts.toArray(new String[0]);
        GameScr.PORT = ports.toArray(new String[0]);
        if(MenuLogin.indexServer >= hosts.size())
            MenuLogin.indexServer = 0;
    }
}
