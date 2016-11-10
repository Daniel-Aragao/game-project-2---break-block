package dev.states;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import dev.entitys.blocos.Bloco;
import dev.entitys.blocos.BlocoMassive;
import dev.entitys.blocos.BlocoWeak;
import dev.frames.criarMapas.Celula;
import dev.listeners.ICriarMapaClicked;
import dev.listeners.IStateListener;
import dev.util.imports.Assets;
import dev.util.imports.ImageCatalog;
import dev.worlds.Mapa;

public class CriarMapaState extends State {

	private JPanel panel;
	private JPanel gridPanel;
	private JToolBar toobar;
	private JButton voltar;
	private JLabel faseescolhida;
	private int faseescolhidaid;

	private JMenuBar menu;
	private JMenuItem salvar;
	private JMenuItem desfazer;
	private JMenuItem limpar;
	private JMenuItem resetar;
	private JMenu config;
	private JMenu fase;

	private ArrayList<Bloco> blocos;
	private Bloco selecionado;
	private ArrayList<Acao> acoes;
	private ArrayList<Celula> celulas;

	public CriarMapaState(IStateListener StateListener) {
		super(StateListener, EStates.CriacaoMapa);
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		faseescolhida = new JLabel("");

		blocos = getBlocos();

		gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(Mapa.MAPA_HEIGHT, Mapa.MAPA_WIDTH / 2, 1, 1));

		celulas = new ArrayList<Celula>();

		for (int i = 0; i < Mapa.MAPA_HEIGHT; i++) {
			for (int j = 0; j < Mapa.MAPA_WIDTH; j++) {
				Celula celula = new Celula(getClickedListener());
				if ((i == 28 && (j == 4 || j == 5 || j == 6)) || i == 27 && j == 5) {
					celula.isMorta = true;
				}
				gridPanel.add(celula.getPanel());
				celulas.add(celula);
			}
		}

		toobar = new JToolBar("Blocos", JToolBar.VERTICAL);

		voltar = new JButton("Voltar");

		for (Bloco b : blocos) {
			JButton button = new JButton();
			button.setIcon(new ImageIcon(b.getImage()));
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					selecionado = b;

				}
			});
			toobar.add(button);
		}

		selecionado = blocos.get(0);

		acoes = new ArrayList<Acao>();
		menu = new JMenuBar();

		// fases
		fase = new JMenu("Fase");
		fase.setMnemonic(KeyEvent.VK_F);
		for (int i = 1; i < 11; i++) {
			JMenuItem menuitem = new JMenuItem(i + "ª fase");
			menuitem.addActionListener(getfaseActionListener(i - 1));
			fase.add(menuitem);
		}

		// configurações
		config = new JMenu("Configurações");
		config.setMnemonic(KeyEvent.VK_C);

		salvar = new JMenuItem("Salvar", new ImageIcon(Assets.getImage(ImageCatalog.saveicon)));
		salvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

		desfazer = new JMenuItem("Desfazer", new ImageIcon(Assets.getImage(ImageCatalog.back)));
		desfazer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));

		limpar = new JMenuItem("Limpar", new ImageIcon(Assets.getImage(ImageCatalog.limpar)));

		resetar = new JMenuItem("Resetar");

		config.add(resetar);
		config.add(limpar);
		config.add(desfazer);
		config.add(salvar);

		configurarMenu();
		menu.add(fase);
		menu.add(config);
		menu.add(voltar);
		menu.add(faseescolhida);

		panel.add(menu, BorderLayout.NORTH);
		panel.add(gridPanel, BorderLayout.CENTER);
		panel.add(toobar, BorderLayout.WEST);
	}

	private ActionListener getfaseActionListener(int i) {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loadMap(Assets.loadMap(i));
				acoes = new ArrayList<Acao>();
				faseescolhida.setText(" "+(i+1) + "ª fase");
				faseescolhidaid = i;
			}
		};
	}

	private void configurarMenu() {
		voltar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				changeToState(EStates.Menu);
			}
		});
		salvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int[][] mapa = getMap();
				for (int i = 0; i < mapa.length; i++) {
					for (int j = 0; j < mapa[i].length; j++) {
						System.out.print(mapa[i][j] + " ");
					}
					System.out.println();
				}
				
				Assets.salvarMapa(mapa, faseescolhidaid);

			}
		});
		resetar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loadMap(Assets.loadMap(faseescolhidaid + 10));
				acoes = new ArrayList<Acao>();
			}
		});
		desfazer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				desfazerAcao();
			}

		});

		limpar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cleanall();
				acoes.add(new Acao(true));

			}
		});
	}

	public void loadMap(int[][] newmap) {
		for (int i = 0; i < Mapa.MAPA_HEIGHT; i++) {
			for (int j = 0; j < Mapa.MAPA_WIDTH; j++) {
				int valor = newmap[i][j] - 1;
				if (valor > -1) {
					celulas.get(j + i * Mapa.MAPA_WIDTH).setBloco(blocos.get(newmap[i][j] - 1));
				} else {
					celulas.get(j + i * Mapa.MAPA_WIDTH).setBloco(null);
				}
			}
		}
	}

	public int[][] getMap() {
		int[][] map = new int[Mapa.MAPA_HEIGHT][Mapa.MAPA_WIDTH];
		for (int i = 0; i < Mapa.MAPA_HEIGHT; i++) {
			for (int j = 0; j < Mapa.MAPA_WIDTH; j++) {
				if ((i == 28 && (j == 4 || j == 5 || j == 6))) {
					map[i][j] = -2;
				} else if (i == 27 && j == 5) {
					map[i][j] = -1;
				} else {
					Bloco b = celulas.get(j + i * Mapa.MAPA_WIDTH).getBloco();
					if (b != null) {
						map[i][j] = b.getValor();
					} else {
						map[i][j] = 0;
					}
				}
			}
		}
		return map;
	}

	private void desfazerAcao() {
		if (!acoes.isEmpty()) {
			Acao acao = acoes.remove(acoes.size() - 1);
			if (acao.limp) {
				for (Acao a : acoes) {
					if (a != acao) {
						if (a.limp) {
							cleanall();
						} else {
							a.celula.doClick(a.novo);
						}
					}
				}
			} else {
				acao.celula.doClick(acao.antigo);
			}
		}
	}

	private void cleanall() {
		for (Celula c : celulas) {
			c.clean();
		}
	}

	private ICriarMapaClicked getClickedListener() {
		return new ICriarMapaClicked() {

			@Override
			public Bloco click(Celula celula) {
				Acao f = new Acao(celula, celula.getBloco(), selecionado);
				acoes.add(f);
				return selecionado;
			}
		};
	}

	private ArrayList<Bloco> getBlocos() {
		blocos = new ArrayList<Bloco>();
		for (int i = 1; i < 11; i++) {
			Bloco bloco = null;
			if (i == 10) {
				bloco = new BlocoMassive(0, 0, i);
			} else {
				bloco = new BlocoWeak(0, 0, i);
			}
			blocos.add(bloco);
		}
		return blocos;
	}

	class Acao {
		Celula celula;
		Bloco novo;
		Bloco antigo;
		boolean limp;

		public Acao(Celula c, Bloco a, Bloco n) {
			celula = c;
			antigo = a;
			novo = n;
			limp = false;
		}

		public Acao(boolean isLimpar) {
			this.limp = isLimpar;
		}
	}

	@Override
	public Component getPanel() {
		return this.panel;
	}

	@Override
	public void changeToState(EStates State) {
		StateListener.StateChanged(State);

	}

	@Override
	public void Draw(Graphics g) {
		throw new RuntimeException("Não implementado");

	}

	@Override
	public void update() {

	}

}
