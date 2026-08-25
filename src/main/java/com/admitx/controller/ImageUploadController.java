package com.admitx.controller;

import java.io.File;
import java.util.Map;

import com.admitx.config.CloudinaryConfig;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class ImageUploadController {

    public String imageUpload(File file) {

        Cloudinary cloudinary = CloudinaryConfig.getCloudinary();

        try {

            Map<String, Object> result =
                    cloudinary.uploader().upload(
                            file,
                            ObjectUtils.asMap(
                                    "resource_type", "auto",
                                    "folder", "admitx/documents"
                            )
                    );

            String url =
                    String.valueOf(result.get("secure_url"));

            System.out.println("Cloudinary URL: " + url);

            return url;

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
}