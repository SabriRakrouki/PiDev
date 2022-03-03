package tn.esprit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {

}
