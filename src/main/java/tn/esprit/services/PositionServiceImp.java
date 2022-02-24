package tn.esprit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.entities.Domain;
import tn.esprit.entities.Position;
import tn.esprit.repositories.PositionRepository;

@Service
public class PositionServiceImp implements IPositionService {
	@Autowired
	PositionRepository positionRepository;

	@Override
	public Position AddPosition(Position d) {
		positionRepository.save(d);
		return d;
	}

	@Override
	public Position UpdatePosition(Position d) {
		positionRepository.save(d);
		return d;
	}

	@Override
	public Position DeletePosition(int id) {
		Position d=positionRepository.findById(id).get();
		positionRepository.delete(d);
		return d;
	}

	@Override
	public List<Position> GetAllPosition() {
		
		return positionRepository.findAll();
	}

}
