package tn.esprit.services;

import java.util.List;
import org.springframework.stereotype.Service;

import tn.esprit.entities.Program;
import tn.esprit.entities.Trip;
import tn.esprit.repositories.ProgramRepository;
import tn.esprit.repositories.TripRepository;

@Service
public class ProgramServiceImp implements IProgramSerivce {
	
	private final  ProgramRepository programRepository;
	private   TripRepository tripRepository;

	public ProgramServiceImp(ProgramRepository programRepository) {
		// TODO Auto-generated constructor stub
		this.programRepository = programRepository;
	}

	@Override
	public List<Program> getAllProgram() {
		// TODO Auto-generated method stub
		return programRepository.findAll();
	}

	@Override
	public Program AddProgram(Program program) {
		// TODO Auto-generated method stub
		programRepository.save(program);
		return program;
	}

	@Override
	public Program UpdateProgram(Program program) {

		// TODO Auto-generated method stub
		
		programRepository.save(program);
		return program;
	}

	@Override
	public void DeleteProgram(Program program) {
		// TODO Auto-generated method stub
		programRepository.delete(program);
	}

	@Override
	public Program FindProgramById(int id) {
		// TODO Auto-generated method stub
		return programRepository.getById(id);
	}
	@Override
	public List<Program> getAllProgramTrip(int id) {
		return programRepository.findBytripId(id);
	}
	@Override
	public void DeleteProgramByTrip(int id) {
		Program program = (Program) programRepository.findBytripId(id);
		programRepository.delete(program);
	}
	@Override
	public Program AddProgramByTrip(Program program,int id) {
		Trip trip =tripRepository.getById(id);
		program.setTrip(trip);
		programRepository.save(program);
		return program;
	}
	@Override
	public Program UpdateProgramByTrip(Program program,int id) {

		Trip trip =tripRepository.getById(id);
		program.setTrip(trip);
		programRepository.save(program);
		return program;
	}
	@Override
	public void DeleteProgramById(int id) {
		Program program = programRepository.getById(id);
		programRepository.delete(program) ;
	}

}
