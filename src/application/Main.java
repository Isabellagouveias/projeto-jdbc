package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entity.Seller;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Conexão com o banco
        SellerDao sellerDao = DaoFactory.createSellerDao();
        Scanner scanner = new Scanner(System.in);
        //Variáveis globais
        String name;
        String lastname;
        String email;
        double salary;
        long id;

        System.out.println("Digite o que voce gostaria de fazer: \nC - Create \nR - Read \nU - Update \nD - Delete");
        System.out.print("Digite: ");
        String ch = scanner.next().toUpperCase();

        switch (ch.charAt(0)) {
            case 'C':
                //C - CREATED
                System.out.println("****Criar um Seller****\n");

                System.out.print("Digite o nome do Seller: ");
                name = scanner.next();

                System.out.print("Digite o Sobrenome do Seller: ");
                lastname = scanner.next();

                System.out.print("Digite o E-mail do Seller: ");
                email = scanner.next();

                System.out.print("Digite o Salário do Seller: ");
                salary = scanner.nextDouble();

                sellerDao.insert(new Seller(null, name, lastname, email, salary));

                break;
            case 'R':
                System.out.println("Deseja buscar por id? == Digite 1: \n ou");
                System.out.print("Deseja buscar todos? == Digite 2: ");
                int op = scanner.nextInt();
                if (op == 1) {
                    System.out.print("Digite o ID que deseja buscar: ");
                    id = scanner.nextLong();

                    Seller foundSeller = sellerDao.findById(id);
                    if(foundSeller == null) {
                        System.out.println("Seller não encontrado!");
                        return;
                    }
                    System.out.println(foundSeller);
                    System.out.println("***********************************");
                } else if (op == 2) {
                    //R
                    System.out.println("=== Buscar todo mundo ===");
                    List<Seller> list = sellerDao.findAll();
                    for (Seller seller : list) {
                        System.out.println(seller);
                    }
                } else {
                    System.out.println("Numero invalido");
                    return;
                }
                break;
            case 'U':
                //U - UPDATE
                System.out.println("****Atualizar um Seller****\n");

                System.out.print("Digite o ID do seller que deseja alterar: ");
                id = scanner.nextLong();

                if (sellerDao.findById(id) == null) {
                    System.out.println("ID não encontrado!!");
                    return;
                }

                System.out.print("Digite o nome do Seller: ");
                name = scanner.next();

                System.out.print("Digite o Sobrenome do Seller: ");
                lastname = scanner.next();

                System.out.print("Digite o E-mail do Seller: ");
                email = scanner.next();

                System.out.print("Digite o Salario do Seller: ");
                salary = scanner.nextDouble();

                sellerDao.update(new Seller(id, name, lastname, email, salary));
                break;
            case 'D':
                //D - DELETE
                System.out.print("Digite o ID que deseja deletar: ");
                id = scanner.nextLong();

                if (sellerDao.findById(id) == null) {
                    System.out.print("ID não encontrado!!");
                    return;
                }

                sellerDao.deleteById(id);

                break;
            default:
                System.out.println("Código invalido");
        }
    }
}
