package tn.esprit.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entities.*;
import tn.esprit.services.ICommentService;
import tn.esprit.services.ITopicService;

@RestController
@RequestMapping("/api/v1/topic")
public class TopicController {

	private final ITopicService topicService;

	public TopicController(ITopicService topicService) {
		
		this.topicService = topicService;
	}

	@GetMapping("/getTopics")
	@ResponseBody
	public List<Topic> getAllTopics() {
		System.out.println(topicService.getAllTopics());
		return topicService.getAllTopics();

	}

	@GetMapping("/getTopic/{topic-id}")
	@ResponseBody
	public Topic getCommentById(@PathVariable("topic-id") int topicId) {
		return topicService.getTopicById(topicId);

	}

	@GetMapping("/count")
	public long countTopics() {
		return topicService.count();

	}

	@PostMapping("/addTopic")
	@ResponseBody
	public Topic AddTopic(@RequestBody Topic topic) {
		return topicService.AddTopic(topic);

	}

	@PutMapping("/updateTopic/{id}")
	public void updateTopic(@RequestBody Topic topic) {
		topicService.updateTopic( topic);
	}

	@DeleteMapping("/remove-topic/{id}")
	@ResponseBody
	public void deleteTopic(@PathVariable("id") int topicId) {

		topicService.removeTopic(topicId);

	}

}
