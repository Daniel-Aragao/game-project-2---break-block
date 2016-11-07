package dev.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dev.entitys.Usuario;

public class UsuarioRepository implements IRepository<Usuario> {

	@Override
	public Usuario buscar(String Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario buscar(int id) throws SQLException {

		Connection con = null;
		PreparedStatement stmt = null;

		Usuario usuario = null;

		try {

			con = Conexao.getConexao();
			stmt = con.prepareStatement("SELECT * FROM usuarios WHERE id = ?");
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				usuario = new Usuario(rs.getInt("id"), rs.getString("usuario"), rs.getString("senha"));
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

		return usuario	;
	}

	@Override
	public boolean adicionar(Usuario e) {

		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = Conexao.getConexao();
			stmt = con.prepareStatement(
					"INSERT INTO usuarios (usuario, senha) values(?,?)");

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
	public void alterar(Usuario e) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Usuario> getList(String param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Usuario> getAll() {
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
