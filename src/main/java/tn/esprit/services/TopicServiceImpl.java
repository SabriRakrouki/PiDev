package tn.esprit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tn.esprit.entities.Topic;
import tn.esprit.repositories.TopicRepository;

@Service
public class TopicServiceImpl implements ITopicService {

	private final TopicRepository topicRepository;

	public TopicServiceImpl(TopicRepository topicRepository) {

		this.topicRepository = topicRepository;
	}

	@Override
	public List<Topic> getAllTopics() {

		return topicRepository.findAll();
	}

	@Override
	public Topic getTopicById(int id) {
		return topicRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
	}

	@Override
	public Topic AddTopic(Topic Topic) {
		topicRepository.save(Topic);
		return Topic;
	}

	@Override
	public void removeTopic(int id) {
		topicRepository.deleteById(id);
	}

	@Override
	public Topic updateTopic( Topic topic) {
		
		topicRepository.save(topic);
		return topic;
	}

	@Override
	public long count() {
		return topicRepository.count();
	}
}
