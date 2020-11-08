package cl.ucn.disc.hpc.Sudoku;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
// this is the Main class
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //this is going to give us the executed time
        final StopWatch stopWatch = StopWatch.createStarted();

        //this is the incomplete sudoku
        int [][]tablero= {
                {2,0,0,0,8,0,0,6,0},
                {6,0,0,0,0,3,1,7,0},
                {0,1,0,9,0,0,0,4,0},
                {0,8,4,0,1,9,0,0,0},
                {9,6,0,0,0,0,0,0,0},
                {0,2,0,0,0,0,0,0,0},
                {0,0,0,0,7,0,2,9,0},
                {0,0,0,0,0,5,0,0,8},
                {7,0,6,0,0,0,0,0,3},
        };
        Sudoku miSudoku = new Sudoku(tablero);

        //this solve the Sudoku
        miSudoku.resolverSudoku();
        //here show us time take the program
        log.info("Completado {}. \n",stopWatch);


        //this print the solved sudoku
        for(int i=0;i<9;i++)
        {
            if(i % 3 == 0 && i != 0)
            {
                System.out.print("----------------------------------\n");
            }
            for (int j = 0;j<9;j++)
            {
                if (j%3== 0&&j!=0)
                {
                    System.out.print(" | ");
                }
                System.out.print(" "+ tablero[i][j]+" ");
            }
            System.out.println();
        }
        System.out.print("\n\n________________________________________");






    }






}


