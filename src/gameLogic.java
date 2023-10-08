import javax.swing.*;

public class gameLogic {

    int[][] goal = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
    int[][] board;
    int posEmpty;
    int movements = 0;

    public gameLogic() {
        board = new int[4][4];
        startBoard();
    }

    public void startBoard() {

        posEmpty = (int) (Math.random() * 16);
        int posNum;

        for (int k = 1; k <= 15; k++) {
            posNum = (int) (Math.random() * 16);

            while (posNum == posEmpty || board[posNum / 4][posNum % 4] != 0)
                posNum = (posNum + 1) % 16;
            board[posNum / 4][posNum % 4] = k;
        }
        if (board == goal) {
            startBoard();
        }
    }

    public void printBoard() {
        System.out.println();
        for (int row = 0; row < 4; row++) {

            for (int column = 0; column < 4; column++)
                System.out.print(board[row][column] + " ");

            System.out.print("\n");
        }

        System.out.println("El valor de posEmpty es "+posEmpty);

        determineMove();
    }

    public void determineMove() {

        int left = 0,newLeftVal=0;
        int right = 0,newRightVal=0;
        int up = 0,newUpVal=0;
        int down = 0,newDownVal=0;


        switch (posEmpty / 4) {
            case 0:
                down = 1;
                newDownVal=board[(posEmpty / 4)+1][(posEmpty % 4)];
                break;
            case 3:
                up = 1;
                newUpVal=board[(posEmpty / 4)-1][(posEmpty % 4)];
                break;
            default:
                up = 1;
                down = 1;
                newDownVal=board[(posEmpty / 4)-1][(posEmpty % 4)];
                newUpVal=board[(posEmpty / 4)+1][(posEmpty % 4)];
                break;
        }

        switch (posEmpty % 4) {
            case 0:
                right = 1;
                newRightVal=board[(posEmpty / 4)][(posEmpty % 4)+1];
                break;
            case 3:
                left = 1;
                newLeftVal=board[(posEmpty / 4)][(posEmpty % 4)-1];
                break;
            default:
                left = 1;
                right = 1;
                newRightVal=board[(posEmpty / 4)][(posEmpty % 4)+1];
                newLeftVal=board[(posEmpty / 4)][(posEmpty % 4)-1];
                break;
        }

        String message = "";

        if (up == 1) {
            message += "W: Arriba válido, el valor de arriba es "+newUpVal+".\n";
        } else {
            message += "∅  Arriba invàlido.\n";
        }
        if (down == 1) {
            message += "S: Abajo válido, el valor de abajo es "+newDownVal+".\n";
        } else {
            message += "∅  Abajo inválido.\n";
        }
        if (left == 1) {
            message += "A: Izquierda válido, el valor a la izquierda es "+newLeftVal+".\n";
        } else {
            message += "∅  Izquierda inválido.\n";
        }
        if (right == 1) {
            message += "D: Derecha válido, el valor de la derecha es "+newRightVal+".\n";
        } else {
            message += "∅  Derecha invalido.\n";
        }

        System.out.println("\n"+message);


        boolean tag = false;
        do {
            String option = JOptionPane.showInputDialog("Ingrese una de las opciones.");
            option=option.toLowerCase();
            switch (option) {
                case "w":
                    if(up==1) {

                        movements += 1;
                        tag=true;
                    }else{
                        JOptionPane.showMessageDialog(null ,"Jugada no vállida");
                    }
                    break;
                case "s":
                    if(down==1) {

                        movements += 1;
                        tag=true;
                    }else{
                        JOptionPane.showMessageDialog(null ,"Jugada no vállida");
                    }
                    break;
                case "a":
                    if(left==1) {

                        movements += 1;
                        tag=true;
                    }else{
                        JOptionPane.showMessageDialog(null ,"Jugada no vállida");
                    }
                    break;
                case "d":
                    if(right==1) {
                        movements += 1;
                        tag=true;
                    }else {
                        JOptionPane.showMessageDialog(null ,"Jugada no vállida");
                    }
                    break;
                case "win":
                    board=goal;
                    tag=true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"No es una opción válida.");
                    break;
            }
        }while (!tag);

        if (board == goal) {
            victoryScreen();
        } else {
            printBoard();
        }

    }

    public void victoryScreen() {

        for (int row = 0; row < 4; row++) {

            for (int column = 0; column < 4; column++)
                System.out.print(board[row][column] + " ");

            System.out.print("\n");

        }
        System.out.println("Felicidades,se la ganó.");
        System.out.println("Movimientos utilizados:" + movements);
    }
}