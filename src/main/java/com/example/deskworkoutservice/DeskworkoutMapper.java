package com.example.deskworkoutservice;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Optional;

@Mapper
public interface DeskworkoutMapper {

    @Select("SELECT * FROM deskworkouts")
    List<Deskworkout> findAll();

    @Select("SELECT * FROM deskworkouts WHERE bodyparts LIKE CONCAT (#{bodyparts}, '%' )")
    List<Deskworkout> findByBodypartsStartingWith(String bodyparts);

    @Select("SELECT * FROM deskworkouts WHERE id = #{id}")
    Optional<Deskworkout> findById(int id);

    @Insert("INSERT INTO deskworkouts  (name, howto, repetition, bodyparts, difficulty ) VALUES (#{name},#{howto},#{repetition},#{bodyparts},#{difficulty})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Deskworkout deskworkout);

    @Update("UPDATE deskworkouts SET name=#{name}, howto=#{howto}, repetition=#{repetition}, bodyparts=#{bodyparts}, difficulty=#{difficulty} WHERE id = #{id}")
    void update(Deskworkout deskworkout);

    @Delete("DELETE FROM deskworkouts WHERE id = #{id}")
    void delete(int id);
}
