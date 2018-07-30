package cn.aouo.sys.mapper;

import cn.aouo.sys.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by USER on 2018/7/25.
 */
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM ow_user WHERE id = #{id}")
    User getUserById(String id);

    @Select("SELECT * FROM ow_user")
    public List<User> getUserList();

    @Insert("insert into ow_user(name, age, ctm) values(#{username}, #{age}, now())")
    public int add(User user);

    @Update("UPDATE ow_user SET username = #{user.username} , age = #{user.age} WHERE id = #{id}")
    public int update(@Param("id") Integer id, @Param("user") User user);

    @Delete("DELETE from ow_user where id = #{id} ")
    public int delete(Integer id);

    @Select("SELECT * FROM ow_user WHERE account = #{account}")
    public List<User> getUserByAccount(String account);

    @Insert("insert into ow_user(id, account, password, email,createDate) values(#{id}, #{account},#{password},#{email}, now())")
    int register(User user);
}
