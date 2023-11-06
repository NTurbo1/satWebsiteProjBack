package com.nturbo1.satWebsiteProjBack.service.courses;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nturbo1.satWebsiteProjBack.repository.courses.VideoRepository;
import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Video;

import lombok.Data;

@Service
@Data
public class VideoService {
	
	private final VideoRepository videoRepository;

  public Video createVideo(Video video) {
		return videoRepository.save(video);
	}

	public List<Video> getAllVideos() {
		return videoRepository.findAll();
	}

	public Optional<Video> getVideoById(Long id) {
		return videoRepository.findById(id);
	}

	public Video updateVideo(Long id, Video Video) {
		if (videoRepository.existsById(id)) {
			Video.setId(id);
			return videoRepository.save(Video);
		} else {
			throw new IllegalArgumentException("Video with id " + id + " does not exist.");
		}
	}

	public void deleteVideo(Long id) {
		if (videoRepository.existsById(id)) {
			videoRepository.deleteById(id);
		} else {
			throw new IllegalArgumentException("Video with id " + id + " does not exist.");
		}
	}

}
