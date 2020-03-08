package cn.notacat.cat.web.controller.dws;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/****************************************
 * @@CREATE : 2020-03-03 5:02 下午
 * @@AUTH : NOT A CAT【NOTACAT@CAT.ORZ】
 * @@DESCRIPTION : 
 * @@VERSION :
 *
 *****************************************/
public class Person {


    @NotNull
    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public static void main(String[] args) {
        String a = "null";
        System.out.println(Optional.ofNullable(a).orElse("abc"));
    }

}
