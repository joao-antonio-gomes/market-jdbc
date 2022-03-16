package market.menu;

import market.connection.ConnectionFactory;

import java.sql.Connection;
import java.util.Scanner;

abstract public class Menu {
    protected static Scanner scanner = new Scanner(System.in);

    protected static void limpaMenu() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    protected static void separador() {
        System.out.println("--------------------------------------------------------------------------------");
    }
}
