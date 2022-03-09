package tn.esprit.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.services.InvitationServiceImpl;
@RestController
public class InvitationController {
	@Autowired
	InvitationServiceImpl invitationBatchConfig ;
	@PostMapping("/upload")
	public void upload(@RequestParam("path") String path) throws Exception{
	invitationBatchConfig.ReadDataFromExcel(path);
	}
}
