package cn.notacat.cat.web.controller.dws.vo;

import lombok.*;
import lombok.experimental.Accessors;

/****************************************
 * @@CREATE : 2020-03-08 6:08 下午
 * @@AUTH : NOT A CAT【NOTACAT@CAT.ORZ】
 * @@DESCRIPTION : 
 * @@VERSION :
 *
 *****************************************/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString
public class Teacher {
    private String name;
    private Integer age;


    public static void main(String[] args) {
//        Student s = new Student();
//        Student s1 = new Student("a",1);

//        Student s1 = new Student();
//        System.out.println(s1.getAge());
        Student s = Student.builder().name("1").build();
        System.out.println(s.getName());
        System.out.println(s.toString());
    }
}
