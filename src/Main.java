import com.sun.jna.Library;
import com.sun.jna.Native;
import java.util.Scanner;
import java.util.HashMap;
import java.nio.file.*;

public class Main {


    public interface ImpressoraDLL extends Library {

        ImpressoraDLL INSTANCE = (ImpressoraDLL) Native.load (
                "SEU-DIRETORIO/E1_Impressora01.dll", ImpressoraDLL.class); // Adicione o Caminho completo para a DLL



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

        HashMap<String,String>LoginElgin = new HashMap<>();

        boolean Login = false;

        while(!Login){

            System.out.println("Seja Bem Vindo a o Login");
            System.out.println("=== 1 - Cadastro ===");
            System.out.println("=== 2 - Login ===");
            System.out.println("=== 3 - Sair === ");
            System.out.println("Escolha a opação :");
            String opcao = input.nextLine();
            input.nextLine();

            switch (opcao){
                case 1:
                    System.out.println("");
            }
        }




        exibirMenuLogin();





        while (true) {
            exibirMenuOpcoes();

            String escolha = capturarEntrada("\nDigite a opÃ§Ã£o desejada: ");

            if (escolha.equals("0")) {
                // Fechar a conexÃ£o e sair
                System.out.println("Programa encerrado.");
                break;
            } else if (escolha.equals("1")) {
                // Chamar a funÃ§Ã£o de configurar a conexÃ£o (os alunos devem implementar)
            } else if (escolha.equals("2")) {
                // Chamar a funÃ§Ã£o de abrir a conexÃ£o (os alunos devem implementar)
            } else if (escolha.equals("3")) {
                // Chamar a funÃ§Ã£o de imprimir texto (os alunos devem implementar)
            } else if (escolha.equals("4")) {
                // Chamar a funÃ§Ã£o de imprimir QRCode (os alunos devem implementar)
            } else if (escolha.equals("5")) {
                // Chamar a funÃ§Ã£o de imprimir cÃ³digo de barras (os alunos devem implementar)
            } else if (escolha.equals("6")) {
                // Chamar a funÃ§Ã£o de imprimir XML SAT (os alunos devem implementar)
            } else if (escolha.equals("7")) {
                // Chamar a funÃ§Ã£o de imprimir XML Cancelamento SAT (os alunos devem implementar)
            } else if (escolha.equals("8")) {
                // Chamar a funÃ§Ã£o de abrir gaveta Elgin (os alunos devem implementar)
            } else if (escolha.equals("9")) {
                // Chamar a funÃ§Ã£o de abrir gaveta genÃ©rica (os alunos devem implementar)
            } else if (escolha.equals("10")) {
                // Chamar a funÃ§Ã£o de emitir sinal sonoro (os alunos devem implementar)
            } else {
                System.out.println("OpÃ§Ã£o invÃ¡lida. Tente novamente.");
            }
        }

    }
}
