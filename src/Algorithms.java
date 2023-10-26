public class Algorithms {

    //1-NaivStandard
    public static void NaivStandard(int[][] A, int[][] B, int[][] Result, int N, int P, int M) {
        int aux;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                aux = 0;
                //Dado que la matriz es n*n recorre los elemento de la fila/columna
                //de la matriz A/B y va sumando los resultados hasta llegar al final
                for (int k = 0; k < P; k++) {
                    aux += A[i][k]*B[k][j];
                }
                Result[i][j] = aux;
            }
        }
    }

    //2-NaivOnArray
    public static void NaivOnArray(int[][]A,int[][] B,int[][] Result,int N,int  P,int M){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                //Funciona igual que el NaivStandard sin embargo se ahorra la declaración de una variable
                Result[i][j] = 0;
                for (int k = 0; k < P; k++) {
                    Result[i][j] += A[i][k]*B[k][j];
                }
            }
        }
    }

    //3-NaivKahan
    /* Este método funciona igual que los dos anteriores sin embargo esta pensado para multiplicar matrices
       con valores doubles por eso tiene ñas varibales t,sum,err que lo que hacen es acumular el error que se puede generar
       al operar este tipo de numeros
    */
    public static void NaivKahan(int[][]A,int[][] B,double[][] Result,int N,int  P,int M){
        double t, sum, err;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sum = 0.0;
                err = 0.0;
                for (int k = 0; k < P; k++) {
                    err = err + A[i][k]*B[k][j];
                    t = sum + err;
                    err = (sum - t) + err;
                    sum = t;
                }
                Result[i][j] = sum;
            }
        }
    }

    //4-NaivLoopUnrollingTwo
    public static void NaivLoopUnrollingTwo(int[][]A,int[][] B,int[][] Result,int N,int P,int M) {
        int i, j, k;
        int aux;
        if (P % 2 == 0) {
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0;
                    //Si el numero de filas/columna es par, empieza a multiplicar por parejas (k y k+1) y amuenta en dos para continuar con la siguiente pareja
                    for (k = 0; k < P; k += 2) {
                        aux += A[i][k]*B[k][j] + A[i][k+1]*B[k+1][j];
                    }
                    Result[i][j] = aux;
                }
            }
        } else {
            int PP = P - 1; //Si el numero de filas/columnas es impar se le quita uno a la P para obtener la posición del ultimo elemento
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0;
                    //Si es impar hace el mismo proceso, pero sin contar el utilimo elemento
                    for (k = 0; k < PP; k += 2) {
                        aux += A[i][k]*B[k][j] + A[i][k+1]*B[k+1][j];
                    }
                    //El ultimo elemento se multiplica aquí
                    Result[i][j] = aux + A[i][PP]*B[PP][j];
                }
            }
        }
    }

    //5-NaivLoopUnrollingThree
    //Funciona muy parecido al anterior, solo que utilizando ternas
    public static void NaivLoopUnrollingThree(int[][]A,int[][] B,int[][] Result,int N,int P,int M) {
        int i, j, k;
        int aux;
        if (P % 3 == 0) {
            //Si el numero de filas/columnas es un numero multiplo de 3
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0;
                    //Hará la multiplicación en forma de ternas y por eso aumentará el for en k+=3
                    for (k = 0; k < P; k += 3) {
                        aux += A[i][k] * B[k][j] + A[i][k + 1] * B[k + 1][j] + A[i][k + 2] * B[k + 2][j];
                    }
                    Result[i][j] = aux;
                }
            }
        } else if (P % 3 == 1) {
            //Si el numero de filas/columnas no es un multiplo de 3 y deja una posición sin evaluar
            int PP = P - 1; //Se obtiene la ultima posición la cual no se va a evaluar dentro del for
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0;
                    //Hará la multiplicación en forma de ternas y por eso aumentará el for en k+=3
                    for (k = 0; k < PP; k += 3) {
                        aux += A[i][k] * B[k][j] + A[i][k + 1] * B[k + 1][j] + A[i][k + 2] * B[k + 2][j];
                    }
                    //Se multiplica la ultima posición
                    Result[i][j] = aux + A[i][PP] * B[PP][j];
                }
            }
        } else {
            //Si el numero de filas/columnas no es un multiplo de 3 y deja dos posiciones sin evaluar
            int PP = P - 2; //Se obtiene la penultima posición
            int PPP = P - 1;//Se obtiene la ultima posición la cual no se  va a evaluar dentro del for
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0;
                    //Hará la multiplicación en forma de ternas y por eso aumentará el for en k+=3
                    for (k = 0; k < PP; k += 3) {
                        aux += A[i][k] * B[k][j] + A[i][k + 1] * B[k + 1][j] + A[i][k + 2] * B[k + 2][j];
                    }
                    //Se multiplica las ultimas dos posiciones que quedaron sin evaluar en el for anterior
                    Result[i][j] = aux + A[i][PP] * B[PP][j] + A[i][PPP] * B[PPP][j];
                }
            }
        }
    }
    //6-NaivLoopUnrollingFour
    //Funciona muy parecido que el anterior, solo que utilizando cuartas
    public static void NaivLoopUnrollingFour(int[][]A,int[][] B,int[][] Result,int N,int P,int M){
        int i, j, k;
        int aux;
        if (P % 4 == 0) {
            //Si el numero de filas/columnas es un numero multiplo de 4
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0;
                    //Hará la multiplicación en forma de cuartas y por eso aumentará el for en k+=4
                    for (k = 0; k < P; k += 4) {
                        aux += A[i][k] * B[k][j] + A[i][k + 1] * B[k + 1][j] + A[i][k + 2] * B[k + 2][j]
                                + A[i][k + 3] * B[k + 3][j];
                    }
                    Result[i][j] = aux;
                }
            }
        } else if (P % 4 == 1) {
            //Si el numero de filas/columnas no es un multiplo de 4 y deja una posición sin evaluar
            int PP = P - 1; //Se obtiene la ultima posición la cual no se va a evaluar dentro del for
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0;
                    //Hará la multiplicación en forma de cuartas y por eso aumentará el for en k+=4
                    for (k = 0; k < PP; k += 4) {
                        aux += A[i][k] * B[k][j] + A[i][k + 1] * B[k + 1][j] + A[i][k + 2] * B[k + 2][j]
                                + A[i][k + 3] * B[k + 3][j];
                    }
                    //Se multiplica la ultima posicione que quedo sin evaluar en el for anterior
                    Result[i][j] = aux + A[i][PP] * B[PP][j];
                }
            }
        } else if (P % 4 == 2) {
            //Si el numero de filas/columnas no es un multiplo de 4 y deja dos posiciones sin evaluar
            int PP = P - 2; //Se obtiene la penultima posición la cual no se va a evaluar dentro del for
            int PPP = P - 1; //Se obtiene la ultima posición la cual no se va a evaluar dentro del for
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0;
                    //Hará la multiplicación en forma de cuartas y por eso aumentará el for en k+=4
                    for (k = 0; k < PP; k += 4) {
                        aux += A[i][k] * B[k][j] + A[i][k + 1] * B[k + 1][j] + A[i][k + 2] * B[k + 2][j]
                                + A[i][k + 3] * B[k + 3][j];
                    }
                    //Se multiplica las ultimas dos posiciones que quedaron sin evaluar en el for anterior
                    Result[i][j] = aux + A[i][PP] * B[PP][j] + A[i][PPP] * B[PPP][j];
                }
            }
        } else {
            int PP = P - 3; //Se obtiene la antepenúltima posición la cual no se va a evaluar dentro del for
            int PPP = P - 2; //Se obtiene la penultima posición la cual no se va a evaluar dentro del for
            int PPPP = P - 1; //Se obtiene la ultima posición la cual no se va a evaluar dentro del for
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0;
                    //Hará la multiplicación en forma de cuartas y por eso aumentará el for en k+=4
                    for (k = 0; k < PP; k += 4) {
                        aux += A[i][k] * B[k][j] + A[i][k + 1] * B[k + 1][j] + A[i][k + 2] * B[k + 2][j]
                                + A[i][k + 3] * B[k + 3][j];
                    }
                    //Se multiplica las ultimas tres posiciones que quedaron sin envaluar en el for anterior
                    Result[i][j] = aux + A[i][PP] * B[PP][j] + A[i][PPP] * B[PPP][j] + A[i][PPPP] * B[PPPP][j];

                }
            }
        }
    }
}
