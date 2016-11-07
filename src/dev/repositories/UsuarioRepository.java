package dev.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dev.entitys.Jogador;

public class UsuarioRepository implements IRepository<Jogador> {

	@Override
	public Jogador buscar(String Id) {
		Connection con = null;
		PreparedStatement stmt = null;

		Jogador usuario = null;

		try {

			con = Conexao.getConexao();
			stmt = con.prepareStatement("SELECT * FROM jogador WHERE nome = ?");
			stmt.setString(1, Id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				usuario = new Jogador(rs.getString("nome"), rs.getString("senha"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

		return usuario;
	}

	@Override
	public Jogador buscar(int id) throws SQLException {
		return null;
	}

	@Override
	public boolean adicionar(Jogador e) {

		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = Conexao.getConexao();
			stmt = con.prepareStatement(
					"INSERT INTO jogador (nome, senha) values(?,?)");

			stmt.setString(1, e.getUsuario());
			stmt.setString(2, e.getSenha());

			stmt.executeUpdate();
			return true;
		} catch (SQLException ee) {
			ee.printStackTrace();
			return false;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException ee) {
				ee.printStackTrace();
				return false;
			}
		}
		
	}

	@Override
	public void alterar(Jogador e) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Jogador> getList(String param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Jogador> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remover(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remover(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
