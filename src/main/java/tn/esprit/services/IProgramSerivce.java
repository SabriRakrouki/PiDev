package tn.esprit.services;

import java.util.List;

import tn.esprit.entities.Program;

public interface IProgramSerivce {
	public List<Program> getAllProgram();

	public Program AddProgram(Program program);

	public Program UpdateProgram(Program program);

	public void DeleteProgram(Program program);

	public Program FindProgramById(int id);
	
	public List<Program> getAllProgramTrip(int id);
	public void DeleteProgramByTrip(int id);
	public Program AddProgramByTrip(Program program,int id);
	public Program UpdateProgramByTrip(Program program,int id);
	public void DeleteProgramById(int id);
}
