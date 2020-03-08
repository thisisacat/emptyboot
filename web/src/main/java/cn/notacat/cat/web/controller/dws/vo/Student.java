package cn.notacat.cat.web.controller.dws.vo;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/****************************************
 * @@CREATE : 2020-03-08 6:08 下午
 * @@AUTH : NOT A CAT【NOTACAT@CAT.ORZ】
 * @@DESCRIPTION : 
 * @@VERSION :
 *
 *****************************************/
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@ToString

@Builder//builder模式
@NoArgsConstructor//为了builder模式
@AllArgsConstructor//为了builder模式
@Slf4j//日志
public class Student {
    private String name;
    private Integer age;

}
