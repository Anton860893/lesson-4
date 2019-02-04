import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final int SIZE = 3;
    public static char[][] map;

    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static final char DOT_EMPTY = '*';

    public static final int DOTS_TO_WIN = 3;

    public static final Random rand = new Random();
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Победил человек!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья!");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Победил компьютер!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья!");
                break;
            }
        }
        System.out.println("Игра окончена!");
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            try {
                x = sc.nextInt() - 1;
                y = sc.nextInt() - 1;
            } catch (Exception exc) {
                System.out.println("Введите корректные значения!");
                x = -1;
                y = -1;
            }
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
    }

    public static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер сходил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        return map[y][x] == DOT_EMPTY;
    }

    public static boolean checkWin(char symbol) {
        int vertical, horizontal, firstDia = 0, secondDia = 0;
        for (int i = 0; i <= SIZE - 1; i++) {
            horizontal = 0;
            vertical = 0;
            for (int j = 0; j <= SIZE - 1; j++) {
                if (map[i][j] == symbol) {
                    horizontal++;
                    if (horizontal == SIZE) return true;
                }

                if (map[j][i] == symbol) {
                    vertical++;
                    if (vertical == SIZE) return true;
                }
            }

            if (map[i][i] == symbol) {
                firstDia++;
                if (firstDia == SIZE) return true;
            } else firstDia = 0;

            if (map[i][SIZE - 1 - i] == symbol) {
                secondDia++;
                if (secondDia == SIZE) return true;
            } else secondDia = 0;
        }
        return false;
    }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }
}
