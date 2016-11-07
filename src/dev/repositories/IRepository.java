package dev.repositories;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IRepository<T> {

	public T buscar(String Id);
	public T buscar(int id) throws SQLException;
	
	public boolean adicionar(T e);
	
	public void alterar(T e);
	
	public ArrayList<T> getList(String param);
	public ArrayList<T> getAll();
	
	public boolean remover(String id);
	public boolean remover(int id);
	
}
