package dev.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dev.entitys.Ranking;

public class RankingRepository implements IRepository<Ranking>{

	@Override
	public Ranking buscar(String Id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implement yet");
	}

	@Override
	public Ranking buscar(int id) throws SQLException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implement yet");
	}

	@Override
	public boolean adicionar(Ranking e) {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = Conexao.getConexao();
			stmt = con.prepareStatement(
					"INSERT INTO pontuacao (jogadorNome, pontosObtidos) values(?,?)");

			stmt.setString(1, e.getNomeJogador());
			stmt.setInt(2, e.getPontos());

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
	public void alterar(Ranking e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implement yet");
	}

	@Override
	public ArrayList<Ranking> getList(String param) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implement yet");
	}

	@Override
	public ArrayList<Ranking> getAll() {
		
		Connection con = null;
		PreparedStatement stmt = null;
		ArrayList<Ranking> rankings = new ArrayList<Ranking>();
		
		try {
			
			con = Conexao.getConexao();
			stmt = con.prepareStatement("SELECT * FROM pontuacao ORDER BY pontosObtidos DESC");
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Ranking ranking = new Ranking(rs.getString("jogadorNome"), rs.getInt("pontosObtidos"));
				rankings.add(ranking);
			}

			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		return rankings;
		
	}

	@Override
	public boolean remover(String id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implement yet");
	}

	@Override
	public boolean remover(int id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implement yet");
	}

}
