/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.bianca.fintrack;

import com.bianca.fintrack.controller.FinTracker;
import com.bianca.fintrack.exceptions.EntradaInvalidaException;
import com.bianca.fintrack.model.RepositorioGenerico;
import com.bianca.fintrack.model.Transacao;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author bianca
 */
public class FinTrack {

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        RepositorioGenerico<Transacao> listaInicial = new RepositorioGenerico<>();
        FinTracker fintracker1 = new FinTracker(listaInicial);

        int opcao = 0;

        do {
            System.out.println("\n===== FINTRACK - SEU CONTROLE FINANCEIRO =====\n"
                    + "1. Adicionar nova transação\n"
                    + "2. Adicionar transação mensal\n"
                    + "3. Listar transações\n"
                    + "4. Mostrar saldo atual\n"
                    + "5. Remover transação\n"
                    + "6. Adicionar transações do dia\n"
                    + "7. Sair");

            try {
                System.out.println("\nDigite uma opção: ");
                opcao = ler.nextInt();

                switch (opcao) {

                    case 1 -> {
                        try {
                            System.out.println("\n1 - Receita");
                            System.out.println("2 - Despesa");

                            int tipo = ler.nextInt();

                            if (tipo != 1 && tipo != 2) {
                                throw new EntradaInvalidaException("Tipo de transação inválido.");
                            }

                            boolean ehReceita = (tipo == 1);

                            System.out.println("\nDigite o valor da transação: ");
                            double valor = ler.nextDouble();
                            ler.nextLine();

                            System.out.println("\nDigite a descrição: ");
                            String descricao = ler.nextLine();

                            System.out.println("\nDigite a data: ");
                            String dataEntrada = ler.nextLine();
                            LocalDate data = LocalDate.parse(dataEntrada);

                            fintracker1.adicionarTransacao(ehReceita, valor, descricao, data);

                        } catch (EntradaInvalidaException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    case 2 -> {
                        try {
                            System.out.println("\n1 - Receita");
                            System.out.println("2 - Despesa");

                            int tipo = ler.nextInt();

                            if (tipo != 1 && tipo != 2) {
                                throw new EntradaInvalidaException("Tipo de transação inválido.");
                            }

                            boolean ehReceita = (tipo == 1);

                            System.out.println("\nDigite o valor da transação: ");
                            double valor = ler.nextDouble();
                            ler.nextLine();

                            System.out.println("\nDigite a descrição: ");
                            String descricao = ler.nextLine();

                            System.out.println("\nDigite a data: ");
                            String dataEntrada = ler.nextLine();
                            LocalDate data = LocalDate.parse(dataEntrada);

                            System.out.println("\nDigite o dia de recorrencia: ");
                            int diaRecorrencia = ler.nextInt();

                            fintracker1.adicionarTransacaoMensal(ehReceita, valor, descricao, data, diaRecorrencia);

                        } catch (EntradaInvalidaException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    case 3 -> {
                        try {
                            fintracker1.listarTransacoes();

                        } catch (EntradaInvalidaException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    case 4 -> {
                        try {
                            fintracker1.calcularSaldoTotal();

                        } catch (EntradaInvalidaException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    case 5 -> {
                        try {
                            System.out.println("\nDigite o id que deseja excluir: ");
                            int id = ler.nextInt();

                            fintracker1.removerTransacao(id);

                        } catch (EntradaInvalidaException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    case 6 -> {
                        try {
                            int quantidade;

                            while (true) {
                                try {
                                    System.out.println("\nDigite a quantidade de transações adicionadas: ");
                                    quantidade = ler.nextInt();
                                    ler.nextLine();

                                    break;
                                } catch (InputMismatchException e) {
                                    System.out.println("Digite apenas números.");
                                    ler.nextLine();
                                }
                            }

                            List<Transacao> listaTransacoes = new ArrayList<>();

                            for (int i = 0; i < quantidade; i++) {
                                System.out.println("\n1 - Receita");
                                System.out.println("2 - Despesa");

                                int tipo = ler.nextInt();
                                ler.nextLine();

                                if (tipo != 1 && tipo != 2) {
                                    throw new EntradaInvalidaException("Tipo de transação inválido.");
                                }

                                boolean ehReceita = (tipo == 1);

                                System.out.println("\nDigite o valor da transação: ");
                                double valor = ler.nextDouble();
                                ler.nextLine();

                                System.out.println("\nDigite a descrição: ");
                                String descricao = ler.nextLine();

                                System.out.println("\nDigite a data: ");
                                String dataEntrada = ler.nextLine();
                                LocalDate data = LocalDate.parse(dataEntrada);

                                Transacao transacao = new Transacao(ehReceita, valor, descricao, data);

                                listaTransacoes.add(transacao);
                            }

                            fintracker1.adicionarTransacaoDia(listaTransacoes);

                        } catch (EntradaInvalidaException e) {
                            System.out.println(e.getMessage());

                        } catch (InputMismatchException e) {
                            System.out.println("Digite apenas números.");
                            ler.nextLine();
                        }
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("\nDigite apenas números.");
                ler.nextLine();
            }

        } while (opcao != 7);
    }
}
