import java.io.*;
import java.util.Scanner;

public class ConnectFour {
    char[][] board = new char[6][7];
    String turn;
    char token;
    String path = "src//";
    // directory for file

    public ConnectFour(){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = ' ';
            }
        }
        turn = "Red";
        token = 'R';
    }

    public void nextTurn(){
        if(turn.equals("Red")){
            turn = "Yellow";
            token = 'Y';
        }
        else{
           turn = "Red";
           token = 'R';
        }
    }

    public int nextAvailablePosition(int columnNum){
        int row = board.length - 1;

        while (row >= 0 && board[row][columnNum] != ' ') {
            row--;
        }
        return row;
    }

    public void dropPiece(int columnNum, char token){
        int row = nextAvailablePosition(columnNum);
        try{
            if(row == -1){
                throw new ColumnFull("Column " + columnNum + " is full");
            }
        }
        catch(ColumnFull e){
            System.out.println(e.getMessage());
        }
        board[row][columnNum] = token;
    }

    @Override
    public String toString() {
        String to_return=" 0 1 2 3 4 5 6";
        for(int i=0;i<6;i++) {
            to_return+="\n-----------------------------\n";
            to_return+="| ";
            for(int j=0;j<7;j++) {
                to_return+=board[i][j]+" | ";
            }
        }
        to_return+="\n-----------------------------\n";
        return to_return;
    }

    public void saveGame(){
        Scanner sc = new Scanner(System.in);
        try{
            System.out.println("Enter a filename: ");
            String name = sc.nextLine();


            File myFile = new File(name);
            Scanner fileScan = new Scanner(path + myFile);
            PrintWriter pw = new PrintWriter(path + name);

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    pw.write(board[i][j]);
                    if (j != board[i].length - 1) {
                        pw.write(",");
                    }
                }
                pw.write("\n");
            }
            pw.close();
        }catch(Exception e){
            System.out.println("Unable to read " + e.getMessage());
        }

    }

    public void loadGame(){
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Enter a filename: ");
            String name = sc.nextLine();

            File myFile = new File(name);
            Scanner scFile = new Scanner(new FileReader(path + myFile));


            String line = " ";
            int counter = 0;
            while(scFile.hasNextLine() && counter <= board.length - 1){
                line = scFile.nextLine();
                String[] newString = line.split(",");
                for(int i = 0; i < newString.length; i++){
                    board[counter][i] = newString[i].charAt(0);
                }
                counter++;
            }
            scFile.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}
