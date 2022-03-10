package tn.esprit.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import tn.esprit.spring.message.ResponseFile;
import tn.esprit.spring.message.ResponseMessage;

@RestController
@RequestMapping("/file")
public class FileController {

	@Value("${file.upload-dir}")
	String FILE_DIRECTORY;

	@PostMapping("/uploadFile")
	public ResponseEntity<Object> fileUpload(@RequestParam("File") MultipartFile file) throws IOException {
		File myFile = new File(FILE_DIRECTORY + file.getOriginalFilename());
		myFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(myFile);
		fos.write(file.getBytes());
		fos.close();
		return new ResponseEntity<Object>("The File Uploaded Successfully", HttpStatus.OK);

	}
}
