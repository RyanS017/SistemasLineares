import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insira o tamanho do sistema:");
        int n = sc.nextInt();
        double[][] sistema = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("Insira o valor de x" + (j + 1) + " da linha " + (i + 1) + ":");
                sistema[i][j] = sc.nextDouble();
            }
            System.out.println("Insira o valor do termo independente: ");
            sistema[i][n] = sc.nextDouble();
        }

        List<double[][]> lista = new ArrayList<>();
        lista.add(copia(sistema,n));
        for (int z = 0; z < n-1; z++) {
            for (int i = z + 1; i < n; i++) {
                double x;
                if(sistema[i][z] != 0 && sistema[z][z] !=0) {
                     x = sistema[i][z] / sistema[z][z];
                }else if(sistema[z][z] == 0 && sistema[i][z] != 0) {
                    double[] temp5 = new double[n];
                    for(int w = 0; w < n; w++){
                        temp5[w] = sistema[z][w];
                        sistema[z][w] = sistema[i][w];
                        sistema[i][w] = temp5[w];
                    }
                    lista.remove(z);
                    lista.add(z, copia(sistema,n));
                    x = 0;
                }
                else{
                     x = 0;
                }
                for (int j = 0; j <= n; j++) {
                    double resu = sistema[i][j] - (sistema[z][j] * x);
                    if(Math.abs(resu) < 1e-10){
                        sistema[i][j] = 0;
                    }else{
                        sistema[i][j] = resu;
                    }
                }
            }
            lista.add(copia(sistema,n));
            //System.arraycopy(s,0,x1,0,x1.length);
        }
        double []x = new double[n];
        for(int i = n; i > 0; i--){
            double resu = 0;
            int z = 0;
            for (int j = n; j >=i; j-- ){
                if(j == n) {
                    resu = +lista.get(i - 1)[i - 1][j];
                }else{
                    resu = resu -  (lista.get(i - 1)[i - 1][j]) * x[j];
                }
                z = j;
            }
            resu = resu/lista.get(i-1)[i-1][z-1];
            x[i-1] = resu;
        }
        for (int z = 0; z < n; z++) {
            sistema = lista.get(z);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= n; j++) {
                    System.out.printf(String.format("%.3f", sistema[i][j]) + " ");
                }
                System.out.println();
            }
            System.out.println("\n");
        }
        double sistemaResultado = 0;
        for(int i = 0; i < n; i++){
            sistemaResultado = Math.abs(lista.get(n-1)[n-1][i]+ sistemaResultado);
        }
        if(sistemaResultado == 0){
            if(lista.get(n-1)[n-1][n] == 0){
                System.out.println("SPI (Sistema Possível Inderteminado)");
            }else{
                System.out.println("SI (Sistema Impossível)");
            }
        }else{
            System.out.println("SPD (Sistema Possível Determinado)");
            for(int i = 0; i < n; i++) {
                System.out.println("x" + (i + 1) + ": " +String.format("%.3f",x[i]));
            }
        }
    }
    static double[][] copia(double[][]x, int n){
        double[][] s = new double[n][n+1];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n+1; j++){
                s[i][j] = x[i][j];
            }
        }
        return s;
    }
}