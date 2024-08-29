import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LibretaDeNotas { //clase cam
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, ArrayList<Integer>> calificaciones = new HashMap<>();

        System.out.print("Ingrese cantidad de alumnos: "); //primer print
        int cantidadAlumnos = scanner.nextInt();
        System.out.print("Ingrese cantidad de notas por alumno: ");
        int cantidadNotas = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < cantidadAlumnos; i++) { //nombre notas
            System.out.print("Ingrese nombre del alumno " + (i + 1) + ": ");
            String nombre = scanner.nextLine();
            ArrayList<Integer> notas = new ArrayList<>();

            for (int j = 0; j < cantidadNotas; j++) {
                System.out.print("Ingrese la nota " + (j + 1) + " de " + nombre + ": ");
                int nota = scanner.nextInt();
                notas.add(nota);
            }
            calificaciones.put(nombre, notas);
            scanner.nextLine();
        }

        int opcion;
        do {
            // Menú de opciones
            System.out.println("\nMenú DE opciones:");
            System.out.println("1. Mostrar promedio de notas por alumno.");
            System.out.println("2. Mostrar si la nota se considera aprobatoria o reprobatoria por alumno.");
            System.out.println("3. Mostrar si la nota está por sobre o por debajo del promedio general del curso por alumno.");
            System.out.println("0. Salir del menú.");
            System.out.print("Por favor, elija una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    mostrarPromedio(calificaciones);
                    break;
                case 2:
                    mostrarAprobacion(calificaciones, scanner);
                    break;
                case 3:
                    mostrarComparacionPromedio(calificaciones, scanner);
                    break;
                case 0:
                    System.out.println("Abandonando menú");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 0);

        scanner.close();
    }


    private static void mostrarPromedio(HashMap<String, ArrayList<Integer>> calificaciones) {
        System.out.println("\nPromedio de notas por alumno:");
        for (String nombre : calificaciones.keySet()) {
            ArrayList<Integer> notas = calificaciones.get(nombre);
            int suma = 0;
            for (int nota : notas) {
                suma += nota;
            }
            double promedio = (double) suma / notas.size();
            System.out.println(nombre + ": " + promedio);
        }
    }

    // muestra si aprueba o reprueba(?)
    private static void mostrarAprobacion(HashMap<String, ArrayList<Integer>> calificaciones, Scanner scanner) {
        System.out.print("Ingrese nombre del alumno: ");
        String nombre = scanner.nextLine();
        if (calificaciones.containsKey(nombre)) {
            System.out.print("Ingrese nota a comprobar: ");
            int nota = scanner.nextInt();
            evaluarCalificacion(nota, nombre);
        } else {
            System.out.println("Alumno no encontrado.");
        }
    }
    public static void evaluarCalificacion(int calificacion, String materia) {
        if (calificacion < 0 || calificacion > 10) {
            System.out.println("Calificación no válida en la materia de " + materia );
        } else if (calificacion <= 3) {
            System.out.println("Calificación en la materia de: " + materia + " : No Aprobado");
        } else if (calificacion > 3 && calificacion <= 5) {
            System.out.println("Calificación en la materia de: " + materia + " : Insuficiente");
        } else if (calificacion >= 6 && calificacion <= 8) {
            System.out.println("Calificación en la materia de: " + materia + " : Aceptable");
        } else if(calificacion >= 9) {
            System.out.println("Calificación en la materia de: " + materia + " : Excelente");
        }
    }

    //  comparar nota con el promedio curso
    private static void mostrarComparacionPromedio(HashMap<String, ArrayList<Integer>> calificaciones, Scanner scanner) { //  comparar nota con el promedio curso
        int sumaTotal = 0;
        int totalNotas = 0;

        for (ArrayList<Integer> notas : calificaciones.values()) {
            for (int nota : notas) {
                sumaTotal += nota;
                totalNotas++;
            }
        }

        double promedioCurso = (double) sumaTotal / totalNotas;

        System.out.print("Ingrese nombre del alumno: ");
        String nombre = scanner.nextLine();
        if (calificaciones.containsKey(nombre)) {
            System.out.print("Ingrese la nota a comprobar: ");
            int nota = scanner.nextInt();
            if (nota > promedioCurso) {
                System.out.println("La nota está por sobre el promedio genral del curso.");
            } else if (nota < promedioCurso) {
                System.out.println("La nota está por debajo del promedio general del curso.");
            } else {
                System.out.println("La nota está dentro del promedio general del curso.");
            }
        } else {
            System.out.println("Alumno no encontrado.");
        }
    }


}

//se meborro evaluadorrrrrrrrrqaaaaaaaaaaaaa