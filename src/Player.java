import java.io.*;
import javax.swing.*;
import javax.swing.JOptionPane;

public class Player {

    private String Nick;
    private int pontuacao;
    protected String alterarnome;
    protected String novonome;
    protected String removernome;
    protected String linha;
    protected String antigocont = "";
    protected String novocont = "";

    File arquivo = new File("./src/Save.txt");

    public Player() {

    }

    public void setNick(String Nick) {
        this.Nick = Nick;
    }

    public String getNick() {
        String a = " " + this.Nick + " ";
        return a;
    }

    public int getPontuacao() {
        return this.pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public void showMessage() {
        Nick = JOptionPane.showInputDialog(null, "Insira o nome do Player : ", "Nick", JOptionPane.INFORMATION_MESSAGE);
    }

    public void visualizarPlayers() {
        // C:\\Users\\Gustavo Lopes\\Desktop\\Save.txt
        JTextArea area = new JTextArea(10, 20);
        area.setEditable(false);
            try (Reader texto = new FileReader("./src/Save.txt")) {
                area.read(texto, "1");
            } catch (Exception e) {
                area.append(e.getMessage());
                e.printStackTrace();
            }
        JOptionPane.showMessageDialog(null, new JScrollPane(area), "Placar geral", JOptionPane.INFORMATION_MESSAGE);

    }

    public void salvarpontuacao() {

        try {
            FileWriter escritor = new FileWriter(arquivo, true);
            BufferedWriter inserir = new BufferedWriter(escritor);
            inserir.write(Nick + " " + pontuacao + "\n");
            inserir.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deletarPlayers() {
        removernome = JOptionPane.showInputDialog(null, "Escreva o nome que deseja remover", "Identificacao",
        JOptionPane.INFORMATION_MESSAGE);

        try {
            FileReader leitor2 = new FileReader(arquivo);
            BufferedReader ler = new BufferedReader(leitor2);
            linha = ler.readLine();

            while (linha != null) {
                antigocont = antigocont + linha + System.lineSeparator();
                linha = ler.readLine();
            }

            novocont = antigocont.replace(removernome, "");
            FileOutputStream arquivonovo = new FileOutputStream("Save.txt");
            arquivonovo.write(novocont.getBytes());
            arquivonovo.close();
            ler.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void alterarPlayers() {
        alterarnome = JOptionPane.showInputDialog(null, "Escreva o nome que deseja alterar", "Identificacao",
        JOptionPane.INFORMATION_MESSAGE);
        novonome = JOptionPane.showInputDialog(null, "Escreva o novo nome", "Identificacao",
        JOptionPane.INFORMATION_MESSAGE);

        try {
            FileReader leitor = new FileReader(arquivo);
            BufferedReader ler = new BufferedReader(leitor);
            linha = ler.readLine();

        while (linha != null) {
            antigocont = antigocont + linha + System.lineSeparator();
            linha = ler.readLine();
        }
            novocont = antigocont.replaceAll(alterarnome, novonome);
            FileOutputStream arquivonovo = new FileOutputStream("Save.txt");
            arquivonovo.write(novocont.getBytes());
            arquivonovo.close();
            ler.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
