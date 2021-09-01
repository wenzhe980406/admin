<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package_mapper}.${file_name}Mapper">

    <!-- like 参考
                <if test="phone != null and phone!=''">
                    and phone like  concat('%',#phone ,'%')
               </if>
     -->

    <!-- in 参考
        mapper 定义 @Param("ids")String[] ids
        select * from a where
        1=1
         <if test="ids != null and ids.size()>0 ">
                and id in
                <foreach collection="ids" item="id" index="index" open="(" close=")" separator=",">
                    #id
                </foreach>
	     </if>
	     order by id desc
     -->
    <!-- 业务 汇集处   ${date?string('yyyy-MM-dd hh:mm:ss')} 里程碑 -->

</mapper>