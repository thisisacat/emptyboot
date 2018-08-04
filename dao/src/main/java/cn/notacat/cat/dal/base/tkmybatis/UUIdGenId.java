package cn.notacat.cat.dal.base.tkmybatis;

import tk.mybatis.mapper.genid.GenId;

import java.util.UUID;

/****************************************
 * @@CREATE : 2018-08-04 下午7:26
 * @@AUTH : NOT A CAT【NOTACAT@CAT.ORZ】
 * @@DESCRIPTION : 
 * @@VERSION :
 *
 *****************************************/
public class UUIdGenId implements GenId<String> {

    @Override
    public String genId(String table, String column) {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
