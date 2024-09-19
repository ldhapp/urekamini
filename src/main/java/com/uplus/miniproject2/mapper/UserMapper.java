package com.uplus.miniproject2.mapper;

import com.uplus.miniproject2.dto.MainPageUserDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    List<MainPageUserDto> findBySearchCondition(@Param("name") String name,
                                                @Param("mbti") String mbti,
                                                @Param("major") String major,
                                                @Param("gender") String gender,
                                                @Param("offset") int offset,
                                                @Param("pageSize") int pageSize);

    long countBySearchCondition(@Param("name") String name,
                                @Param("mbti") String mbti,
                                @Param("major") String major,
                                @Param("gender") String gender);
}
