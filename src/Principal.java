import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        ConsultaMoeda consulta = new ConsultaMoeda();

        int opcao;
        do {
            System.out.println("---------------------------------------");
            System.out.println("Seja bem-vindo ao conversor de moedas\n");
            System.out.println("1] Dólar ---------------------> Real Brasileiro");
            System.out.println("2] Real Brasileiro -----------> Dólar");
            System.out.println("3] Dólar ---------------------> Euro");
            System.out.println("4] Euro ----------------------> Dólar");
            System.out.println("5] Dólar ---------------------> Dólar Australiano");
            System.out.println("6] Dólar Australiano ---------> Dólar");
            System.out.println("7] Sair");
            System.out.println("Escolha uma opção válida");
            System.out.println("---------------------------------------");
            System.out.print("Digite sua opção: ");
            opcao = leitura.nextInt();

            String moedaBase = "";
            String moedaDestino = "";

            switch (opcao) {
                case 1 -> { moedaBase = "USD"; moedaDestino = "BRL"; }
                case 2 -> { moedaBase = "BRL"; moedaDestino = "USD"; }
                case 3 -> { moedaBase = "USD"; moedaDestino = "EUR"; }
                case 4 -> { moedaBase = "EUR"; moedaDestino = "USD"; }
                case 5 -> { moedaBase = "USD"; moedaDestino = "AUD"; }
                case 6 -> { moedaBase = "AUD"; moedaDestino = "USD"; }
                case 7 -> {
                    System.out.println("Saindo do programa.");
                    break;
                }
                default -> {
                    System.out.println("Opção inválida. Tente novamente.");
                    continue;
                }
            }

            if (opcao >= 1 && opcao <= 6) {
                System.out.print("Digite o valor que deseja converter: ");
                double valor = leitura.nextDouble();

                try {
                    double taxaConversao = consulta.obterTaxaDeConversao(moedaBase, moedaDestino);

                    double valorConvertido = valor * taxaConversao;
                    System.out.printf("Valor para conversão: %.2f %s%nValor convertido: %.2f %s%n",
                            valor, moedaBase, valorConvertido, moedaDestino);
                } catch (RuntimeException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            }

        } while (opcao != 7);

        leitura.close();
    }
}
