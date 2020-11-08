package cl.ucn.disc.hpc.Sudoku;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

//Sudoku Class
public class Sudoku {


    private static int [][] tablero;

    private static final int SIN_ASIGNAR=0;

    public Sudoku()
    {
        tablero = new int[9][9];
    }

    public Sudoku(int sudoku[][])
    {
        this.tablero=sudoku;
    }



    //this is going to solved the sudoku
    public boolean resolverSudoku() throws ExecutionException, InterruptedException {

        //here start whit row 0 to de 9
        for(int row = 0;row < 9;row++ ){
            //here continue whit column
            for(int column = 0;column < 9;column++ ){

                final ExecutorService executorService=Executors.newFixedThreadPool(17);

                //here we see if the tablero in specific position has assigned a number
                if(tablero[row][column] == SIN_ASIGNAR){
                    //if the dont have a number start to try som numbers
                    for(int numero = 1;numero <= 9;numero++ ){
                        //is going to check if the number can bi here
                        if(esValido(row, column, numero)) {




                            //List<Future<Boolean>> results = executorService.invokeAll(tests);executorService.submit(new SudoTask(ro))
                            tablero[row][column] = numero;
                            if(resolverSudoku()){
                                return true;
                            }else{
                                tablero[row][column] = SIN_ASIGNAR;
                            }
                        }
                    }
                    return false;
                }
                executorService.shutdown();
            }
        }
        return true;
    }


    //this is going to see row
    private static boolean contieneEnFila(int fila, int numero)
    {
        for(int i=0;i<9;i++)
        {
            if (tablero[fila][i]==numero)
            {
                return true;
            }
        }
        return false;
    }
    //this is going to sheck de column
    private static boolean contieneEnColumna(int columna, int numero)
    {
        for (int i=0;i<9;i++)
        {
            if (tablero[i][columna]== numero)
            {
                return true;
            }

        }
        return false;
    }
    //this is going to check the 3x3 box
    private static boolean contieneEnCaja(int fila, int columna, int numero)
    {
        int f = fila - fila % 3;
        int c = columna - columna % 3;
        for(int i = f; i<f+3;i++)
        {
            for(int j = c;j<c+3;j++)
            {
                if (tablero[i][j]==numero)
                {
                    return true;
                }
            }
        }
        return false;
    }
    //this are going to check if is a valid number
    private boolean esValido(int fila, int columna, int numero) throws InterruptedException, ExecutionException
    {

        List<Callable<Boolean>> prueba=new ArrayList<>();
        prueba.add(()-> contieneEnColumna(columna,numero));
        prueba.add(()-> contieneEnFila(fila, numero));
        prueba.add(()-> contieneEnCaja(fila, columna, numero));


        ExecutorService executorService = Executors.newFixedThreadPool(1);

        List<Future<Boolean>>result = executorService.invokeAll(prueba);
        executorService.shutdown();

        for(Future<Boolean>future:result){
            if (!Boolean.TRUE.equals(future.get())){
                return false;
            }
        }
        return true;

        //return !(contieneEnFila(fila,numero)|| contieneEnColumna(columna,numero)||contieneEnCaja(fila,columna,numero));


    }
    //this is the Runnable class called named SudoTask
    private static class SudoTask implements Runnable
    {
        final int fila;
        final int columna;
        final int num;






        private static final int SIN_ASIGNAR=0;


        public SudoTask (final int fila, final int columna, final int num)
        {
            this.fila=fila;
            this.columna=columna;
            this.num=num;

        }
        //this is the run
        public void run(){




        }





    }

}





