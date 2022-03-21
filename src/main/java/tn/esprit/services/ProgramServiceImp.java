package tn.esprit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.entities.Program;

import tn.esprit.repositories.ProgramRepository;

@Service
public class ProgramServiceImp implements IProgramSerivce {
	
	private final  ProgramRepository programRepository;

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

}
