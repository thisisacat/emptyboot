package cn.notacat.cat.dal.dao.entity.demo;

import cn.notacat.cat.dal.base.tkmybatis.IEntity;
import cn.notacat.cat.dal.base.tkmybatis.UUIdGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;

@Table(name = "uuid_demo")
public class UUIDDemoEntity extends IEntity {
    @Id
    @KeySql(genId = UUIdGenId.class)
    private String id;

    private String name;

    private Integer age;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }
}