package com.lealpy.simbirsoft_training.training.collections_block.comparators;

import com.lealpy.simbirsoft_training.training.collections_block.Student;
import java.util.Comparator;

public class StudentYearOfBirthComparator implements Comparator<Student> {

    @Override
    public int compare(Student a, Student b) {
        return Integer.compare(a.getYearOfBirth(), b.getYearOfBirth());
    }

}