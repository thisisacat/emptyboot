package cn.notacat.cat.dal.base.tkmybatis;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/****************************************
 * @@CREATE : 2018-02-27 下午3:57
 * @@AUTH : NOT A CAT【NOTACAT@CAT.ORZ】
 * @@DESCRIPTION :
 * @@VERSION :
 *
 *****************************************/
@RegisterMapper
public interface IMapper<T> extends Mapper<T>, MySqlMapper<T> {


}
