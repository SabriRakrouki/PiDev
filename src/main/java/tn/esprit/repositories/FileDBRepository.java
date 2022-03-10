package tn.esprit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.FileDB;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
}
