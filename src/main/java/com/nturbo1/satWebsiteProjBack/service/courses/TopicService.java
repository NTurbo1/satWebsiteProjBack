package com.nturbo1.satWebsiteProjBack.service.courses;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nturbo1.satWebsiteProjBack.repository.courses.TopicRepository;
import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Topic;

import lombok.Data;

@Service
@Data
public class TopicService {
	
	private final TopicRepository topicRepository;

  public Topic createTopic(Topic topic) {
		return topicRepository.save(topic);
	}

	public List<Topic> getAllTopics() {
		return topicRepository.findAll();
	}

	public Optional<Topic> gettopicById(Long id) {
		return topicRepository.findById(id);
	}

	public Topic updateTopic(Long id, Topic topic) {
		if (topicRepository.existsById(id)) {
			topic.setId(id);
			return topicRepository.save(topic);
		} else {
			throw new IllegalArgumentException("topic with id " + id + " does not exist.");
		}
	}

	public void deleteTopic(Long id) {
		if (topicRepository.existsById(id)) {
			topicRepository.deleteById(id);
		} else {
			throw new IllegalArgumentException("topic with id " + id + " does not exist.");
		}
	}
}
