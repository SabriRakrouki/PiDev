package tn.esprit.services;

import java.util.List;

import tn.esprit.entities.Domain;
import tn.esprit.entities.Position;

public interface IPositionService {
	Position AddPosition(Position d);
	Position UpdatePosition(Position d);
	Position DeletePosition(int id);
	List<Position> GetAllPosition();
	void affecterPositionEMPL(Position p,int idEmpl);

}
