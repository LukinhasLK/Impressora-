import com.sun.jna.Library;
import com.sun.jna.Native;
import java.util.Scanner;
import java.util.HashMap;
import java.nio.file.*;

public class Main {


    public interface ImpressoraDLL extends Library {

        ImpressoraDLL INSTANCE = (ImpressoraDLL) Native.load (
                "/Users/Lucas/Documents/E1_Impressora01.dylib", ImpressoraDLL.class); // Adicione o Caminho completo para a DLL

        int AbreConexaoImpressora(int tipo, String modelo, String conexao, int parametro);

        int FechaConexaoImpressora();

        // Impressão de texto: String, int, int, int
        int ImpressaoTexto(String dados, int posicao, int estilo, int tamanho);

        // Corte: int
        int Corte(int avanco);

        // Impressão de QR Code: String, int, int
        int ImpressaoQRCode(String dados, int tamanho, int nivelCorrecao);

        // Impressão de código de barras: int, String, int, int, int
        int ImpressaoCodigoBarras(int tipo, String dados, int altura, int largura, int HRI);

        // Avançar papel: int
        int AvancaPapel(int linhas);

        int AbreGavetaElgin();

        // Abre gaveta: int, int, int
        int AbreGaveta(int pino, int ti, int tf);

        // Sinal sonoro: int, int, int
        int SinalSonoro(int qtd, int tempoInicio, int tempoFim);

        // Imprime XML SAT: String, int
        int ImprimeXMLSAT(String dados, int param);

        // Imprime XML Cancelamento SAT: String, String, int
        int ImprimeXMLCancelamentoSAT(String dados, String assQRCode, int param);

    }

    private static boolean conexaoAberta = false;
    private static int tipo;
    private static String modelo;
    private static String conexao;
    private static int parametro;
    private static final Scanner input = new Scanner(System.in); // Scanner estÃ¡tico e final


    private static String capturarEntrada(String mensagem) {
        System.out.print(mensagem);
        return input.nextLine();
    }


    public static void exibirMenuLogin() {
        System.out.println("*****************************************");
        System.out.println("********** MENU DE LOGIN ***************");
        System.out.println("*****************************************");

    }


    public static void exibirMenuOpcoes() {
        System.out.println("\n*************************************************");
        System.out.println("**************** MENU IMPRESSORA *******************");
        System.out.println("*************************************************\n");

        System.out.println("1  - Configurar Conexao");
        System.out.println("2  - Abrir Conexao");
        System.out.println("3  - Impressao Texto");
        System.out.println("4  - Impressao QRCode");
        System.out.println("5  - Impressao Cod Barras");
        System.out.println("6  - Impressao XML SAT");
        System.out.println("7  - Impressao XML Canc SAT");
        System.out.println("8  - Abrir Gaveta Elgin");
        System.out.println("9  - Abrir Gaveta");
        System.out.println("10 - Sinal Sonoro");
        System.out.println("0  - Fechar Conexao e Sair");
        System.out.println("--------------------------------------");
    }


    public static void main(String[] args) throws java.io.IOException {

        HashMap<String, String> LoginElgin = new HashMap<>();

        boolean Login = true;

        while (Login) {

            System.out.println("Seja Bem Vindo a o Login");
            System.out.println("=== 1 - Cadastro ===");
            System.out.println("=== 2 - Login ===");
            System.out.println("=== 3 - Sair === ");
            System.out.println("Escolha a opação :");
            int opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println(" === Bem vindo no Cadastro === ");
                    System.out.println("Crie um Login :");
                    String LoginNovo = input.nextLine();

                    System.out.println("Crie uma Senha :");
                    String SenhaNovo = input.nextLine();

                    if (LoginElgin.containsKey(LoginNovo)) {
                        System.out.println("Login ja Existe ❌");

                    } else {
                        System.out.println("Login Realizado com Sucesso !!!");
                        LoginElgin.put(LoginNovo, SenhaNovo);
                        Login =false;
                    }
                    break;
                case 2:
                    System.out.println("Bem vindo a o Login ");

                    System.out.println("Digite o Seu Login :");
                    String User = input.nextLine();
                    input.nextLine();

                    System.out.println("Digite a Sua Senha :");
                    String password = input.nextLine();

                    if (LoginElgin.containsKey(User) && LoginElgin.get(User).equals(password)) {
                        System.out.println("Login Realizado com Sucesso !!!");
                        Login = false;

                    } else {
                        System.out.println("Login ou senha incorreto ❌");
                    }
                    break;
                case 3:
                    System.out.println("Saindo ...... 🏃🏽");
                    Login = false;
                    break;

                default:
                    System.out.println("opcao invalida");
                    break;
            }
        }


        exibirMenuLogin();


        while (true) {
            exibirMenuOpcoes();

            String escolha = capturarEntrada("\nDigite a opção desejada: ");

            switch (escolha){
                case "1":
                    System.out.println("Configurando conexao 🔧");
                    break;

                case"2":
                    System.out.println("Abrindo conexao ");
                    break;

                case "3 ":
                    System.out.println("Impriminto Texto ....");
                    break;

                case "4":
                    System.out.println("Impressao QRCode ....");
                    break;

                case "5":
                    System.out.println("Impressao Cod Barras .....");
                    break;

                case "6":
                    System.out.println("Impressao XML SAT ....");
                    break;

                case "7":
                    System.out.println("Impressao XML Canc SAT ....");
                    break;

                case "8":
                    System.out.println("Abrir Gaveta Elgin ....");
                    break;

                case "9":
                    System.out.println("Abrir Gaveta");
                    break;

                case "10":
                    System.out.println("Sinal Sonoro");
                    break;

                case "11":
                    System.out.println("Fechar Conexao e Sair");
                    break;

            }
        }

    }
}