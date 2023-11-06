package com.nturbo1.satWebsiteProjBack.repository.courses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long>{

}
