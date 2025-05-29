import com.sun.jna.Library;
import com.sun.jna.Native;

import java.util.Scanner;
import java.util.HashMap;
import java.nio.file.*;

public class Main {


    public interface ImpressoraDLL extends Library {

        ImpressoraDLL INSTANCE = (ImpressoraDLL) Native.load(
                "/Users/lukinhas/Downloads/Exemplo Java 2/Bibliotecas E1 Impressora/x64/E1_Impressora01.dll", ImpressoraDLL.class); // Adicione o Caminho completo para a DLL

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

    public static void configurarConexao() {
        if (!conexaoAberta) {
            System.out.println("Digite o tipo de conexao (1 para USB, 2 para Serial, etc.): ");
            tipo = input.nextInt(); // Lê o tipo de conexão como inteiro
            input.nextLine(); // Consumir quebra de linha restante

            System.out.println("Digite o modelo da impressora: ");
            modelo = input.nextLine(); // Lê o modelo da impressora

            System.out.println("Digite a porta de conexão (ex: USB): ");
            conexao = input.nextLine(); // Lê a porta de conexão

            System.out.println("Digite o parâmetro adicional (ex: 0 para padrão): ");
            parametro = input.nextInt();
            input.nextLine(); // Consumir quebra de linha restante

            System.out.println("Parâmetros de conexão configurados com sucesso.");
        } else {
            System.out.println("Conexão já configurada. Pronta para uso.");
        }

    }

    public static void abrirConexao() {
        // Função para abrir a conexão com a impressora
        if (!conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.AbreConexaoImpressora(tipo, modelo, conexao, parametro);
            if (retorno == 0) {
                conexaoAberta = true;
                System.out.println("Conexão aberta com sucesso.");
            } else {
                System.out.println("Erro ao abrir conexão. Código de erro: " + retorno);
            }
        } else {
            System.out.println("Conexão já está aberta.");
        }
    }

    public static void fecharConexao() {
        if (conexaoAberta) {
            ImpressoraDLL.INSTANCE.FechaConexaoImpressora();
            conexaoAberta = false;
            System.out.println("Conexão fechada.");
        } else {
            System.out.println("Nenhuma conexão aberta para fechar.");
        }

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

    public static void ImpressaoTexto() {
        if (conexaoAberta) {
            ImpressoraDLL.INSTANCE.AvancaPapel(1);
            ImpressoraDLL.INSTANCE.ImpressaoTexto("Teste de impressao", 1, 4, 0);
            ImpressoraDLL.INSTANCE.AvancaPapel(2);
            ImpressoraDLL.INSTANCE.Corte(5);
        } else {
            System.out.println("Erro: Conexão não está aberta.");
        }
    }


    public static void ImpressaoQRCode(String dados, int tamanho, int nivelCorrecao) {

        if (conexaoAberta == true) {
            int retorno = ImpressoraDLL.INSTANCE.ImpressaoQRCode("TESTE LUKINHAS", 6, 4);
            ImpressoraDLL.INSTANCE.Corte(5);
            System.out.println("Impressao Realizada");
        } else {
            System.out.println("Conexão está fechada!");
        }
    }

    public static void ImpressaoCodigoBarras() {
        if (conexaoAberta) {
            ImpressoraDLL.INSTANCE.AvancaPapel(3);
            ImpressoraDLL.INSTANCE.ImpressaoCodigoBarras(8, "{A012345678912", 100, 2, 3);
            ImpressoraDLL.INSTANCE.AvancaPapel(3);
            ImpressoraDLL.INSTANCE.Corte(3);
        } else {
            System.out.println("Erro: Conexão não está aberta.");
        }
    }

    public static void ImprimeXMLSAT(String dados, int parametro) {
        if (conexaoAberta) {

            int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLSAT("/Users/lukinhas/Downloads/Exemplo Java 2/XMLSAT.xml", 2);

            if (retorno == 0) {
                System.out.println("Impressão do Danfe SAT realizada com sucesso!");
            } else {
                System.out.println("Erro ao imprimir o Danfe SAT. Código de erro: " + retorno);
            }

            ImpressoraDLL.INSTANCE.AvancaPapel(1);
            ImpressoraDLL.INSTANCE.Corte(5);
        } else {

            System.out.println("Erro: Conexão não está aberta.");
        }
    }


    public static void ImprimeXMLCancelamentoSAT(String dados, String assQRCode, int param) {

        if (conexaoAberta) {

            int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLCancelamentoSAT(dados, assQRCode, param);


            if (retorno == 0) {
                System.out.println("Impressão do Cancelamento SAT realizada com sucesso!");
            } else {
                System.out.println("Erro ao imprimir o Cancelamento SAT. Código de erro: " + retorno);
            }


            ImpressoraDLL.INSTANCE.Corte(3);
        } else {

            System.out.println("Erro: Conexão não está aberta.");
        }
    }


    public static void SinalSonoro() {
        if (conexaoAberta) {
            ImpressoraDLL.INSTANCE.SinalSonoro(4, 50, 5);
        } else {
            System.out.println("Erro: Conexão não está aberta.");
        }
    }

    public static void AbreGaveta() {
        if (conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.AbreGaveta(1, 50, 5);
        } else {
            System.out.println("Erro: Conexão não está aberta.");
        }
    }

    public static void AbreGavetaElgin() {
        if (conexaoAberta) {
            ImpressoraDLL.INSTANCE.AbreGavetaElgin();
        } else {
            System.out.println("Erro: Conexão não está aberta.");
        }
    }

    public static void AvancaPapel(int linhas) {
        if (conexaoAberta) {
            ImpressoraDLL.INSTANCE.AvancaPapel(3);
        } else {
            System.out.println("Erro: Conexão não está aberta.");
        }
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
                        Login = false;
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

            switch (escolha) {
                case "1":
                    System.out.println("Configurando conexao 🔧");
                    configurarConexao();
                    break;

                case "2":
                    System.out.println("Abrindo conexao ");
                    abrirConexao();
                    break;

                case "3 ":
                    System.out.println("Impriminto Texto ....");
                    ImpressaoTexto();
                    break;

                case "4":
                    System.out.println("Impressao QRCode ....");
                    ImpressaoQRCode("Teste Lukinhas e pedro ", 6, 4);
                    ImpressoraDLL.INSTANCE.Corte(5);
                    break;

                case "5":
                    System.out.println("Impressao Cod Barras .....");
                    ImpressaoCodigoBarras();
                    ImpressoraDLL.INSTANCE.AvancaPapel(3);
                    ImpressoraDLL.INSTANCE.ImpressaoCodigoBarras(8, "{A012345678912", 100, 2, 3);
                    ImpressoraDLL.INSTANCE.AvancaPapel(3);
                    ImpressoraDLL.INSTANCE.Corte(3);
                    break;

                case "6":
                    System.out.println("Impressao XML SAT ....");
                    ImprimeXMLSAT("/Users/lukinhas/Downloads/Exemplo Java 2/XMLSAT.xml", 2);
                    break;

                case "7":

                    String xmlCancelamento = "path=/Users/lukinhas/Downloads/ExemploJava2/CANC_SAT.xml";
                    String assinaturaQRCode = "CFe352101301971610009355900085441300858291055306982"; // certifique-se de que está correta
                    int param = 2; // USB

                    ImprimeXMLCancelamentoSAT(xmlCancelamento, assinaturaQRCode, param);
                    break;

                case "8":
                    System.out.println("Abrir Gaveta Elgin ....");
                    AbreGaveta();
                    break;

                case "9":
                    System.out.println("Abrir Gaveta");
                    AbreGaveta();
                    break;

                case "10":
                    System.out.println("Sinal Sonoro");
                    SinalSonoro();
                    break;

                case "11":
                    System.out.println("Fechar Conexao e Sair");
                    fecharConexao();
                    break;

            }
        }

    }

}