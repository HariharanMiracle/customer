package com.src.darkdevildev.customer.Controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value="api")
public class FileUploadController {
	
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "G://Hariharan//work//test1//public//uploads//customer//";
	
//    http://localhost:9900/api/customer/imageUpload
	@PostMapping(value="customer/imageUpload")
	@CrossOrigin
	public @ResponseBody String handleFileUpload(@RequestParam("customerPicture") MultipartFile file) {
		if (file.isEmpty()) {
            System.out.println("message: Please select a file to upload");
            return "message: Please select a file to upload";
        }

        try {
        	int leftLimit = 97; // letter 'a'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 6;
            Random random = new Random();
            StringBuilder buffer = new StringBuilder(targetStringLength);
            for (int i = 0; i < targetStringLength; i++) {
                int randomLimitedInt = leftLimit + (int) 
                  (random.nextFloat() * (rightLimit - leftLimit + 1));
                buffer.append((char) randomLimitedInt);
            }
            String generatedString = buffer.toString();
         
            System.out.println(generatedString);
            
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            String file_name = generatedString + "_" + file.getOriginalFilename();
            Path path = Paths.get(UPLOADED_FOLDER + file_name);
            Files.write(path, bytes);

            return file_name;
        } catch (IOException e) {
            e.printStackTrace();return "message: un-expected error";
        }
	}
	
}
