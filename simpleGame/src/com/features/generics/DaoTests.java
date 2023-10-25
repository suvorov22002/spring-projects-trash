package com.features.generics;

public class DaoTests {

    public static void main(String[] args) {

        GeneralDAO<Student> studentGeneralDAO = new GeneralDAO<>();
        Student student = new Student(2L, "Paul Martin", 2320);
        studentGeneralDAO.save(student);
        Student student_1 = studentGeneralDAO.get(2L);

        GeneralDAO<Professor> professorGeneralDAO = new GeneralDAO<>();
        Professor professor = new Professor(1L, "Janvier", "Mathematics");
        professorGeneralDAO.save(professor);
    }
}
