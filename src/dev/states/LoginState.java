package dev.states;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dev.entitys.Usuario;
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

		GridBagConstraints c = new GridBagConstraints();

		usuarioLabel = new JLabel("Login");
		senhaLabel = new JLabel("Senha");
		usuario = new JTextField();
		senha = new JPasswordField();
		registrar = new JButton("Registrar");
		entrar = new JButton("entrar");

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		// c.weightx = 0.2;
		c.ipadx = 10;
		c.ipady = 10;
		panel.add(usuarioLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.2;
		c.ipadx = 5;
		c.ipady = 5;
		panel.add(usuario, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 0.2;
		c.ipadx = 10;
		c.ipady = 10;
		panel.add(senhaLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 0.2;
		c.ipadx = 5;
		c.ipady = 5;
		panel.add(senha, c);

		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 0;
		c.gridy = 4;
		c.weightx = 0.2;
		c.ipadx = 5;
		c.ipady = 5;
		panel.add(registrar, c);

		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 1;
		c.gridy = 4;
		c.weightx = 0.2;
		c.ipadx = 5;
		c.ipady = 5;
		panel.add(entrar, c);

		registrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (isUsuarioValido()) {
					Usuario novoUsuario = new Usuario(usuario.getText(), senha.getText());
					registrarUsuario(novoUsuario);
				}
			}
		});

		entrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (isUsuarioValido()) {
					if (isUsuarioLogado())
						changeToState(EStates.Game);
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

	private void registrarUsuario(Usuario novoUsuario) {

		boolean isUsuarioAdicionado = this.usuarioRepository.adicionar(novoUsuario);
		if (isUsuarioAdicionado) {
			JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!");
		}else {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar o Usuário");
		}
	}

	private boolean isUsuarioLogado() {
		if (!(this.usuario.getText().equals("admin") && this.senha.getText().equals("123"))) {
			JOptionPane.showMessageDialog(null, "Usuário ou senha incorreto");
			return false;
		}
		return true;
	}

	@Override
	public Container getPanel() {
		return this.panel;
	}

	@Override
	public void changeToState(EStates State) {
		System.out.println("mudando o state para jogar");

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
