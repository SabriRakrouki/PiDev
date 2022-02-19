package tn.esprit.services;

import java.util.List;

import tn.esprit.entities.Program;

public interface IProgramSerivce {
	public List<Program> getAllProgram();

	public Program AddProgram(Program program);

	public Program UpdateProgram(int idProgram,Program program);

	public void DeleteProgram(Program program);

	public Program FindProgramById(int id);
}
