package com.example.appmusicc.Retrofit;

public class APIService {
//    private static String base_url = "https://appmusic06051998.000webhostapp.com/server/";
   // private static String base_url = "https://06051998.000webhostapp.com/Server/";
    private static String base_url = "https://apppmusic06051998.000webhostapp.com/server/";
    public static DataServiec getData(){
        return APIRetrofitClient.getClient(base_url).create(DataServiec.class);
    }
}
