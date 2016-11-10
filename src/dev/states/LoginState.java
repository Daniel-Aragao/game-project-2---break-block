package dev.states;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dev.entitys.Jogador;
import dev.game.Game;
import dev.listeners.IStateListener;
import dev.repositories.UsuarioRepository;

public class LoginState extends State {

	private JPanel panel;
	private JLabel usuarioLabel;
	private JLabel senhaLabel;
	private JTextField usuario;
	private JPasswordField senha;
	private JButton registrar;
	private JButton entrar;

	private UsuarioRepository usuarioRepository;

	public LoginState(IStateListener StateListener) {
		super(StateListener, EStates.Login);

		usuarioRepository = new UsuarioRepository();

		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		GridBagConstraints c = new GridBagConstraints();

		usuarioLabel = new JLabel("Login");
		usuario = new JTextField(16);
		senhaLabel = new JLabel("Senha");
		senha = new JPasswordField(16);
		registrar = new JButton("Registrar");
		entrar = new JButton("Entrar");

		c.gridx = 1;
		c.gridy = 0;
		// c.weightx = 0.2;
		c.ipadx = 10;
		c.ipady = 10;
		panel.add(usuarioLabel, c);

		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.2;
		c.ipadx = 5;
		c.ipady = 5;
		panel.add(usuario, c);

		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 0.2;
		c.ipadx = 10;
		c.ipady = 10;
		panel.add(senhaLabel, c);

		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 0.2;
		c.ipadx = 5;
		c.ipady = 5;
		panel.add(senha, c);

		southPanel.add(registrar);
		southPanel.add(entrar);
		
		c.gridx = 1;
		c.gridy = 4;
		c.weightx = 0.2;
		c.ipadx = 5;
		c.ipady = 5;
		panel.add(southPanel, c);
				
		

		registrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (isUsuarioValido()) {
					Jogador novoUsuario = new Jogador(usuario.getText(), senha.getText());
					if(registrarUsuario(novoUsuario)){
						changeToState(EStates.Menu);
					}
				}
			}
		});

		entrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (isUsuarioValido()) {
					if (isUsuarioLogado())
						changeToState(EStates.Menu);
				}

			}
		});

	}

	private boolean isUsuarioValido() {
		
		if (usuario.getText().equals("") || senha.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Usuário ou senha não informados");
			return false;
		}

		return true;

	}

	private boolean registrarUsuario(Jogador novoUsuario) {
		Jogador jogador = this.usuarioRepository.buscar(this.usuario.getText());
		if (jogador != null){
			JOptionPane.showMessageDialog(null, "Nome de usuário já cadastrado");
			return false;
		}
		boolean isUsuarioAdicionado = this.usuarioRepository.adicionar(novoUsuario);
		if (isUsuarioAdicionado) {
			JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!");
			return true;
		}else {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar o Usuário");
			return false;
		}
	}

	private boolean isUsuarioLogado() {
		Jogador jogador = this.usuarioRepository.buscar(this.usuario.getText());
		if (jogador == null || !senha.getText().equals(jogador.getSenha())) {
			JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos");
			return false;
		}
		Game.nomePlayer = jogador.getUsuario();
		return true;
	}

	@Override
	public Container getPanel() {
		return this.panel;
	}

	@Override
	public void changeToState(EStates State) {
		this.StateListener.StateChanged(State);

	}

	@Override
	public void Draw(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
