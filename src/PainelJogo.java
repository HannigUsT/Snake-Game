import javax.swing.JFrame;

public class PainelJogo extends JFrame {

  //A classes painel é uma classe estendida da biblioteca JFrame, a classe painel contempla o frame do jogo e um novo objeto InterfaceJogo da classe InterfaceJogo,
  //assim, cria a Interface, ou seja, a borda que delimita a tela, o nome do jogo, as opções de minimizar, aumentar tela ou fechar o jogo, 
  //pela biblioteca JFrame. Já o restante da implementação é chamada pelo novo objeto InterfaceJogo, trazendo toda a complexidade dos métodos anteriores. 
  PainelJogo() {

    InterfaceJogo Interface = new InterfaceJogo();

    this.add(Interface);
    this.setTitle("SnakeGame");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
    this.setVisible(true);
    this.setResizable(false);
    this.setLocationRelativeTo(null);

  }
}