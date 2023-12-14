import javax.swing.JFrame;

public class PainelJogo extends JFrame {

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