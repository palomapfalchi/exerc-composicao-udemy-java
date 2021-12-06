package application;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        System.out.print("Enter department's name: ");
        String departmentName = scanner.nextLine();
        System.out.println("Enter worker data: ");
        System.out.println("Name: ");
        String workerName = scanner.nextLine();
        System.out.println("Level: ");
        String workerLevel = scanner.nextLine();
        System.out.println("Base Salary: ");
        double baseSalary = scanner.nextDouble();

        //instanciou um novo obj do tipo worker com parametros: worker name, instancia de workerlevel com o valor digitado
        //base salarial e um departamento associado a esse objeto.
        Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel), baseSalary, new Department(departmentName));

        System.out.println("How many contracts to this worker? ");
        int n = scanner.nextInt();

        for (int i=1; i<=n; i++) {
            System.out.println("Enter contract #" + i + " data: ");
            System.out.print("Date (DD/MM/YYYY): ");
            Date contractDate = sdf.parse(scanner.next());
            System.out.print("Value per hour: ");
            double valuePerHour = scanner.nextDouble();
            System.out.print("Duration (hours): ");
            int hours = scanner.nextInt();
            HourContract contract = new HourContract(contractDate, valuePerHour, hours);
            worker.addContract(contract);
        }

        System.out.print("Enter month and year to calculate income (MM/YYYY): ");
        String monthAndYear = scanner.next();
        //declara variavel month, com substring, ela vai capturar a posição de 0 a 2 do monthAndYear
        //e integer.parseint vai converter ela em string
        int month = Integer.parseInt(monthAndYear.substring(0, 2));
        int year = Integer.parseInt(monthAndYear.substring(3));
        System.out.println("Name: " + worker.getName());
        System.out.println("Department: " + worker.getDepartment().getName());

        System.out.println("Income for: " + monthAndYear + ": " + String.format("%.2f", worker.income(year, month)));
    }
}
