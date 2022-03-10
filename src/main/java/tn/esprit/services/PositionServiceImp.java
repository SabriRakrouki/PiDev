package tn.esprit.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.entities.Domain;
import tn.esprit.entities.Employee;
import tn.esprit.entities.Entreprise;
import tn.esprit.entities.Position;
import tn.esprit.entities.User;
import tn.esprit.repositories.EmployeeRepository;
import tn.esprit.repositories.PositionRepository;
import tn.esprit.repositories.UserRepository;

@Service
public class PositionServiceImp implements IPositionService {
	@Autowired
	PositionRepository positionRepository;
	@Autowired
	EmployeeRepository empRepo;
	@Autowired
	UserRepository userRepo;

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
		Position d = positionRepository.findById(id).get();
		positionRepository.delete(d);
		return d;
	}

	@Override
	public List<Position> GetAllPosition() {

		return positionRepository.findAll();
	}

	@Override
	public void affecterPositionEMPL(Position NewPosition, int idEmpl) {

		Employee e = empRepo.getById(idEmpl);
		Set<Employee> se = new HashSet<Employee>();
		Set<Position> sp = new HashSet<Position>();
		Position Position = positionRepository.findBypotionName(NewPosition.getPotionName());
		if (Position == null) {
			se.add(e);

			if (e.getPosition() == null) {
				NewPosition.setEmployees(se);
				positionRepository.save(NewPosition);
			} else if (e.getPosition() != null) {
				e.setPosition(NewPosition);
				positionRepository.save(NewPosition);
				// empRepo.save(e);

			}

		} else {

			e.setPosition(Position);
			empRepo.save(e);

		}
	}

}
