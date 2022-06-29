import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class InterfaceJogo extends JPanel implements KeyListener, ActionListener {

  protected static final int larguradatela = 900;
  protected static final int alturadatela = 900;
  protected static final int tamanhounidade = 30;
  protected static final int tamanhojogo = (larguradatela * alturadatela) / tamanhounidade;
  protected static final int DELAY = 100;
  protected final int x[] = new int[tamanhojogo];
  protected final int y[] = new int[tamanhojogo];
  protected int macascomidas;
  protected char direction = 'R';
  protected boolean rodando = false;
  protected boolean inicio = false;

  FrutaJogo FrutaPrincipal = new FrutaJogo();
  FrutaAmarela FrutaAmarela = new FrutaAmarela();
  Player Player = new Player();
  CobraJogo CobraJogo = new CobraJogo();
  JButton botao = new JButton("Iniciar");
  JButton botao2 = new JButton("Reiniciar");
  JButton botao3 = new JButton("Alterar nome");
  JButton botao4 = new JButton("Remover nome");
  JButton botao5 = new JButton("Visualizar players");

  Timer timer;
  Random random;

  InterfaceJogo() {
    // Construtor, define a resolução, cor do background, define o foco como default, adiciona a classse KeyListener e Joga para a tela inicial.
    this.setPreferredSize(new Dimension(larguradatela, alturadatela));
    this.setBackground(new java.awt.Color(0, 0, 0));
    this.setFocusable(true);
    random = new Random();
    this.addKeyListener(this);
    telaInicial();
  }

  public void telaInicial() {
    // Remove os botões, inicio = true, rodando = false, seta o local onde os botões aparecem na tela, e adiciona o botão 1 (iniciar)
    remove(botao3);
    remove(botao4);
    remove(botao5);
    inicio = true;
    rodando = false;
    botao.setBounds(105, 10, 90, 30);
    botao.addActionListener(this);
    Player.showMessage();
    botao.setBorder(BorderFactory.createRaisedSoftBevelBorder());
    botao3.setBounds(105, 150, 90, 30);
    botao3.addActionListener(this);
    botao3.setBorder(BorderFactory.createRaisedSoftBevelBorder());
    botao4.setBounds(105, 200, 90, 30);
    botao4.addActionListener(this);
    botao4.setBorder(BorderFactory.createRaisedSoftBevelBorder());
    botao5.setBounds(95, 250, 110, 30);
    botao5.addActionListener(this);
    botao5.setBorder(BorderFactory.createRaisedSoftBevelBorder());
    add(botao);
  }

  public void iniciarJogo() {
    //Remove todos os botões, sai da tela inico e rodando = true faz aparecer as frutas e a cobra, inicia o jogo com o timer.
    remove(botao);
    remove(botao2);
    remove(botao3);
    remove(botao4);
    remove(botao5);
    novaFruta(1);
    novaFruta(2);
    inicio = false;
    rodando = true;
    timer = new Timer(DELAY, this);
    timer.start();
  }

  public void novaFruta(int fruta) {
    // Adiciona uma nova fruta vermelha em algum lugar aleatorio
    if (fruta == 1) {
      FrutaPrincipal.x = random.nextInt((int) (larguradatela / tamanhounidade)) * tamanhounidade;
      FrutaPrincipal.y = random.nextInt((int) (larguradatela / tamanhounidade)) * tamanhounidade;
    }
    if (fruta == 2) {
      // Adiciona uma nova fruta amarela em algum lugar aleatorio
      FrutaAmarela.x = random.nextInt((int) (larguradatela / tamanhounidade)) * tamanhounidade;
      FrutaAmarela.y = random.nextInt((int) (larguradatela / tamanhounidade)) * tamanhounidade;
    }

  }

  public void moverCobra() {
    // Faz o partescorpo seguir a cabeça, para cada partecorpo -1 da cabeça.
    for (int i = CobraJogo.partescorpo; i > 0; i--) {
      x[i] = x[i - 1];
      y[i] = y[i - 1];
    }

    // Caso o usuario aperte W a cabeça da cobra sobe, S a cobra desce, D a cobra vai para a direita e A a cobra vai para a esquerda.
    switch (direction) {
      case 'U':
        y[0] = y[0] - tamanhounidade;
        break;
      case 'D':
        y[0] = y[0] + tamanhounidade;
        break;
      case 'L':
        x[0] = x[0] - tamanhounidade;
        break;
      case 'R':
        x[0] = x[0] + tamanhounidade;
        break;
    }

  }

  public void paintComponent(Graphics g) {
    // Puxa o método draw e usar o método paintComponet para dar cor e vida.
    super.paintComponent(g);
    draw(g);
  }

  public void draw(Graphics g) {
    // Se Inicio == true então mostrar a mensgem na tela d inicio "Bem vindo ao jogo da cobra"
    if (inicio == true && rodando == false) {
      g.setColor(Color.red);
      g.drawString("Bem vindo ao jogo da cobra", 70, 100);
    }

    if (inicio == false && rodando == true) {

      // Maça Principal é colorica com a cor vermelha e preenchida com o fillReact.
      g.setColor(Color.red);
      g.fillRect(FrutaPrincipal.x, FrutaPrincipal.y, tamanhounidade, tamanhounidade);

      // Maça Amarela é colorica com a cor vermelha e preenchida com o fillReact.
      g.setColor(Color.yellow);
      g.fillRect(FrutaAmarela.x, FrutaAmarela.y, tamanhounidade, tamanhounidade);

      //  Cobra
      for (int i = 0; i < CobraJogo.partescorpo; i++) {
        // Cabeça Cobra é pintada com a cor verde em fillReact
        if (i == 0) {
          g.setColor(Color.green);
          g.fillRect(x[i], y[i], tamanhounidade, tamanhounidade);
        } // Corpo cobra é pitanda com a cor verde escuro em fillReact
        else {
          g.setColor(new Color(45, 180, 0));
          g.fillRect(x[i], y[i], tamanhounidade, tamanhounidade);
        }
      }

    }

    // Se Inicio e Rodando = FALSE então entrar na tela fim de jogo
    if (inicio == false && rodando == false) {
      fimDeJogo(g);
    }

  }

  public void fimDeJogo(Graphics g) {

    timer.stop();

    // texto Pontuacao
    g.setColor(Color.red);
    g.setFont(new Font("Ita1", Font.ITALIC, 20));
    FontMetrics metricas4 = getFontMetrics(g.getFont());
    g.drawString(Player.getNick() + macascomidas, (180 - metricas4.stringWidth(Player.getNick() + macascomidas)) / 2,80);

    // texto fim de jogo
    g.setColor(Color.green);
    g.setFont(new Font("Ita2", Font.ITALIC, 30));
    FontMetrics metricas3 = getFontMetrics(g.getFont());
    g.drawString("Fim de jogo", (larguradatela - metricas3.stringWidth("Fim de jogo")) / 2, g.getFont().getSize());
  }

  public void verificaFruta() {

    // Colisão Fruta Principal
    if ((x[0] == FrutaPrincipal.x) && (y[0] == FrutaPrincipal.y)) {
      CobraJogo.partescorpo++;
      macascomidas++;
      novaFruta(1);
    }
    // Colisão Fruta Amarela
    if ((x[0] == FrutaAmarela.x) && (y[0] == FrutaAmarela.y)) {
      CobraJogo.partescorpo--;
      macascomidas++;
      novaFruta(2);
    }

  }

  public void verificaColisao() {

    // Colisões do Corpo
    for (int i = CobraJogo.partescorpo; i > 0; i--) {
      if ((x[0] == x[i]) && (y[0] == y[i])) {
        Player.setPontuacao(macascomidas);
        Player.salvarpontuacao();
        inicio = false;
        rodando = false;
        add(botao3);
        add(botao4);
        add(botao5);
      }
    }
    // Colisões com as bordas
    if (x[0] < -1) {
      Player.setPontuacao(macascomidas);
      Player.salvarpontuacao();
      inicio = false;
      rodando = false;
      add(botao3);
      add(botao4);
      add(botao5);
    }
    if (x[0] > larguradatela + -1) {
      Player.setPontuacao(macascomidas);
      Player.salvarpontuacao();
      inicio = false;
      rodando = false;
      add(botao3);
      add(botao4);
      add(botao5);
    }
    if (y[0] < 0 - 1) {
      Player.setPontuacao(macascomidas);
      Player.salvarpontuacao();
      inicio = false;
      rodando = false;
      add(botao3);
      add(botao4);
      add(botao5);
    }
    if (y[0] > alturadatela + -1) {
      Player.setPontuacao(macascomidas);
      Player.salvarpontuacao();
      inicio = false;
      rodando = false;
      add(botao3);
      add(botao4);
      add(botao5);
    }

    if (CobraJogo.partescorpo <= 1){
      inicio = false;
      rodando = false;
      add(botao3);
      add(botao4);
      add(botao5);
    }

  }

  public void actionPerformed(ActionEvent e) {
    // Quando um dos botões forem apertados chamará os metodos. 
    if (e.getSource() == botao) {
      iniciarJogo();
    }
    if (e.getSource() == botao2) {
      repaint();
      paintComponent(getGraphics());
      draw(getGraphics());
      iniciarJogo();
    }
    if (e.getSource() == botao3) {
      timer.stop();
      Player.alterarPlayers();
      timer.start();
    }
    if (e.getSource() == botao4) {
      timer.stop();
      Player.deletarPlayers();
      timer.start();
    }
    if (e.getSource() == botao5) {
      timer.stop();
      Player.visualizarPlayers();
      timer.start();
    }
    if (rodando) {
      moverCobra();
      verificaFruta();
      verificaColisao();
    }
    repaint();
  }

  @Override
  public void keyPressed(KeyEvent e) {
    // Caso o usuario tecle durante o jogo rodando com w,a,s,d seta left, seta right, seta up, seta down ele se move.
    switch (e.getKeyCode()) {
      case KeyEvent.VK_LEFT:
        if (direction != 'R') {
          direction = 'L';
        }
        break;
      case KeyEvent.VK_RIGHT:
        if (direction != 'L') {
          direction = 'R';
        }
        break;
      case KeyEvent.VK_UP:
        if (direction != 'D') {
          direction = 'U';
        }
        break;
      case KeyEvent.VK_DOWN:
        if (direction != 'U') {
          direction = 'D';
        }
        break;
      case KeyEvent.VK_W:
        if (direction != 'D') {
          direction = 'U';
        }
        break;
      case KeyEvent.VK_A:
        if (direction != 'R') {
          direction = 'L';
        }
        break;
      case KeyEvent.VK_S:
        if (direction != 'U') {
          direction = 'D';
        }
        break;
      case KeyEvent.VK_D:
        if (direction != 'L') {
          direction = 'R';
        }
        break;

    }

  }

  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub

  }

}