package com.admitx.config;
import java.util.HashMap;
import java.util.Map;

import com.cloudinary.Cloudinary;
public class CloudinaryConfig {

    public static Cloudinary cloudinary ;
    public static Cloudinary getCloudinary(){
        if(cloudinary == null){
            Map<String, Object> config = new HashMap<>();

            config.put("cloud_name", "phgupzcr");
            config.put("api_key", "452938494314543");
            config.put("api_secret","ot1Cvw0UMYHPN3oKFRm-kIunP48");
            config.put("secure",true);

            cloudinary = new Cloudinary(config);
        }
        return cloudinary;
    }
    
}
