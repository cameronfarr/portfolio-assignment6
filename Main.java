import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ConnectFour newGame = new ConnectFour();
        int answer = 0;
        boolean turn = true;
        char token = 'R';
        String player = "Red";

        do{
            if(turn){
                token = 'R';
                player = "Red";
                turn = !turn;
            }
            else{
                token = 'Y';
                player = "Yellow";
                turn = !turn;
            }
            System.out.println(newGame);
            System.out.println("Which column would "+ player+" like to go in (7 to save, 8 to load, 9 to quit)");
            answer = sc.nextInt();

            if(answer == 0 || answer == 1 || answer == 2 || answer == 3 || answer == 4 || answer == 5 || answer == 6){
                try {
                    newGame.dropPiece(answer, token);
                    newGame.nextTurn();
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
            else if(answer == 7){
                newGame.saveGame();
            }
            else if(answer == 8){
                newGame.loadGame();
            }
        }while(answer != 9);
    }
}
