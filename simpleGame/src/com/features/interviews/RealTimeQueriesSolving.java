package com.features.interviews;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class RealTimeQueriesSolving {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) {

        numberOfMaleAndFemale();
        allDepartements();
        ageAverageOfMaleAndFemale();
        highestPaidEmployee();
        dateOfRecruit();
        numberOfEmployeeDepartment();
        averageSalaryOfDepartment();
        youngestMale();
        mostWorkingExperiences();
        maleAndFemaleInDep();
        averageSalaryOfMaleAndFemale();
        employeeName();
        averageAndTotalSalary();
        youngerAndOlder();
        oldestEmployee();

    }

    /**
     * How many male and female are there in the organization
     */
    private static void numberOfMaleAndFemale(){

        Map<String, Long> countGenderMap = UtilsData.employeeList.stream()
                .collect(Collectors.groupingBy(Employees::getGender, Collectors.counting()));

        Set<Map.Entry<String, Long>> entrySet = countGenderMap.entrySet();

        for(Map.Entry<String, Long> entry : entrySet) {
            if("Female".equals(entry.getKey())){
                System.out.println("Number of female is: " + entry.getValue());
            }
            else{
                System.out.println("Number of male is: " + entry.getValue());
            }
        }

//        Long totalFemale = UtilsData.employeeList.stream().filter(e -> "Female".equals(e.getGender())).count();
//
//        System.out.println("Female are: " + totalFemale);
//        System.out.println("Male are: " + (UtilsData.employeeList.size() - totalFemale));
    }

    /**
     * Print the name of all departements in the organization
     */
    private static void allDepartements() {
        UtilsData.employeeList.stream().map(Employees::getDepartment).distinct().forEach(System.out::println);
    }

    /**
     * Average age of male and female in the organization
     */
    private static void ageAverageOfMaleAndFemale() {

        double aveg;
       Map<String, List<Employees>> listEmployeeMap = UtilsData.employeeList.stream()
                .collect(Collectors.groupingBy(Employees::getGender));

//        UtilsData.employeeList.stream()
//                .collect(Collectors.groupingBy(Employees::getGender, Collectors.averagingDouble(Employees::getAge)));

       Set<Map.Entry<String, List<Employees>>> entrySet = listEmployeeMap.entrySet();




       for(Map.Entry<String, List<Employees>> entry : entrySet) {

           if("Female".equals(entry.getKey())) {

               df.setRoundingMode(RoundingMode.DOWN);

               aveg = entry.getValue().stream().mapToInt(Employees::getAge).average().orElse(0d);
               System.out.println("Average of female: " + df.format(aveg));

           }
           else{

//               df.setRoundingMode(RoundingMode.UP);
//               System.out.println("double (RoundingMode.UP)  : " + df.format(aveg));

               aveg = entry.getValue().stream().mapToInt(Employees::getAge).average()
                       .orElse(0d);
               System.out.printf("Average of male: %.2f", aveg);
               System.out.println();
           }
       }
    }

    /**
     * Highest paid employee in organisation
     */
    private static void highestPaidEmployee() {

        Employees highEmployee = UtilsData.employeeList.stream().max(Comparator.comparing(Employees::getSalary)).orElse(null);
        assert highEmployee != null;
        System.out.println("Employee with highest salary" + highEmployee.toString());


        highEmployee = UtilsData.employeeList.stream()
                .sorted(Comparator.comparing(Employees::getSalary).reversed()).findFirst().orElse(null);

        assert highEmployee != null;
        System.out.println("Employee with highest salary" + highEmployee);

    }

    /**
     * Name of all employees who have joined after 2015
     */
    private static void dateOfRecruit() {

        UtilsData.employeeList.stream().filter(e -> e.getYearOfJoining() > 2015).map(Employees::getName).forEach(System.out::println);

    }

    /**
     * Number of employees in each departement
     */
    private static void numberOfEmployeeDepartment() {

        Map<String, List<Employees>> departmentResourceMap = UtilsData.employeeList.stream()
                .collect(Collectors.groupingBy(Employees::getDepartment));

//        UtilsData.employeeList.stream()
//                .collect(Collectors.groupingBy(Employees::getDepartment, Collectors.counting()));

        Set<Map.Entry<String, List<Employees>>> entrySet = departmentResourceMap.entrySet();

        for(Map.Entry<String, List<Employees>> entry : entrySet) {

            System.out.println("In " + entry.getKey() + " we have: " + entry.getValue().size() + " resources");

        }
    }

    /**
     * Avarege salary of each departement
     */
    private static void averageSalaryOfDepartment() {

        double aveg;
        Map<String, List<Employees>> departmentResourceMap = UtilsData.employeeList.stream()
                .collect(Collectors.groupingBy(Employees::getDepartment));

//        UtilsData.employeeList.stream()
//                .collect(Collectors.groupingBy(Employees::getDepartment, Collectors.averagingDouble(Employees::getSalary)));

        Set<Map.Entry<String, List<Employees>>> entrySet = departmentResourceMap.entrySet();

        for(Map.Entry<String, List<Employees>> entry : entrySet) {

            System.out.print("Average salary in " + entry.getKey());
            aveg = entry.getValue().stream().mapToDouble(Employees::getSalary).average().getAsDouble();

            df.setRoundingMode(RoundingMode.UP);
            System.out.println(" " + df.format(aveg));


        }

    }

    /**
     * Get the details of the youngest male employee in the product department development
     */
    private static void youngestMale() {

       Employees youngestEmployee = UtilsData.employeeList.stream().filter(e -> e.getDepartment().equals("Product Development") && e.getGender().equals("Male"))
               .min(Comparator.comparingInt(Employees::getAge)).orElse(null);

        System.out.println("The youngest Employee in product development department is: " + youngestEmployee);
    }

    /**
     * Most working experience in the organization
     */
    private static void mostWorkingExperiences() {

        Employees mostWorkingExperience = UtilsData.employeeList.stream()
                .min(Comparator.comparing(Employees::getYearOfJoining)).orElse(null);

        System.out.println("Most working experiences: " + mostWorkingExperience);
    }

    /**
     * How many male and female we have in sales and marketing department
     */
    private static void maleAndFemaleInDep() {

        Map<String, Long> countMaleFemaleMap = UtilsData.employeeList.stream()
                .filter(e -> e.getDepartment().equals("Sales And Marketing"))
                .collect(Collectors.groupingBy(Employees::getGender, Collectors.counting()));

        System.out.println("Regroupment in Sales And Marketing: " + countMaleFemaleMap);
    }

    /**
     * Average salary of male and female employee
     */
    private static void averageSalaryOfMaleAndFemale() {

        Map<String, Double> avegSalaryMaleFemale = UtilsData.employeeList.stream()
                .collect(Collectors.groupingBy(Employees::getGender, Collectors.averagingDouble(Employees::getSalary)));

        System.out.println("Average salary of male and female: " + avegSalaryMaleFemale);
    }

    /**
     * List down the name of all employee in each department
     */
    private static void employeeName() {

        Map<String, List<String>> countEmployeeNameMap = UtilsData.employeeList.stream()
                .collect(Collectors.groupingBy(Employees::getDepartment, Collectors.mapping(Employees::getName, Collectors.toList())));

        System.out.println("Employees by departement: " + countEmployeeNameMap);
    }

    /**
     * Average salary and total salary of the whole organization
     */
    private static void averageAndTotalSalary() {

        double aveg = UtilsData.employeeList.stream().mapToDouble(Employees::getSalary).average().getAsDouble();
        double sum = UtilsData.employeeList.stream().mapToDouble(Employees::getSalary).sum();

        DoubleSummaryStatistics employeeSalaryStatistics=
                UtilsData.employeeList.stream().collect(Collectors.summarizingDouble(Employees::getSalary));
        System.out.println("Average Salary = "+employeeSalaryStatistics.getAverage());
        System.out.println("Total Salary = "+employeeSalaryStatistics.getSum());

        System.out.println("Salaire total: " + sum + ", Moyenne de salaire: " + df.format(aveg));
    }

    /**
     * separe employees younger or equals to 25 year from those who are older
     */
    private static void youngerAndOlder() {

        Map<Boolean, List<Employees>> countYoungerMap = UtilsData.employeeList.stream()
                .collect(Collectors.partitioningBy(e -> e.getAge() <= 25));

        Set<Map.Entry<Boolean, List<Employees>>> entrySet = countYoungerMap.entrySet();

        for(Map.Entry<Boolean, List<Employees>> entry : entrySet) {

            if(entry.getKey()) {
                System.out.println("Younger than 25");
                entry.getValue().forEach(System.out::println);
            }
            else{
                System.out.println("Older than 25");
                entry.getValue().forEach(System.out::println);
            }

        }

    }

    /**
     * Who is the oldest employee of the organization
     */
    private static void oldestEmployee() {

        Employees oldestEmployee = UtilsData.employeeList.stream().max(Comparator.comparing(Employees::getAge)).get();
        System.out.println("The oldest employee is: " + oldestEmployee);
    }
}
