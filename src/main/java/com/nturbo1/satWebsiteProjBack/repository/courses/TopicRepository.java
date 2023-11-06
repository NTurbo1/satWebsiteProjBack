package com.nturbo1.satWebsiteProjBack.repository.courses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nturbo1.satWebsiteProjBack.repository.entities.courses.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

}
