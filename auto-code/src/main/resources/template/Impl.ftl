package ${package_impl};

import cn.cepec.talroad.config.domain.Page;
import cn.cepec.talroad.config.domain.PageInfo;
import ${package_mapper}.${file_name}Mapper;
import ${package_service}.${file_name}Service;
import ${package_domain}.${file_name};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* 开发公司：中节能天融科技有限公司
*
* * ${tabInfo} 实现层
*
* @author ${author}
* @created Create Time: ${date?string('yyyy-MM-dd hh:mm:ss')}
*/
@Service
public class ${file_name}ServiceImpl implements ${file_name}Service {

    /**
    * 保存
    * @param ${file_name?uncap_first}
    * @return
    */
    @Override
    public boolean save(${file_name} ${file_name?uncap_first}) {
        return ${file_name?uncap_first}Mapper.insertSelective(${file_name?uncap_first})>0;
    }

    /**
    * 根据主键修改
    * @param ${file_name?uncap_first}
    * @return
    */
    @Override
    public boolean update(${file_name} ${file_name?uncap_first}) {
        return ${file_name?uncap_first}Mapper.updateByPrimaryKeySelective(${file_name?uncap_first})>0;
    }

    /**
    * 根据主键删除
    * @param id
    * @return
    */
    @Override
    public boolean delete(Integer id) {
        return ${file_name?uncap_first}Mapper.deleteByPrimaryKey(id)>0;
    }

    /**
    * 根据主键查询
    * @param id
    * @return
    */
    public ${file_name} selectById(Integer id){
        return ${file_name?uncap_first}Mapper.selectByPrimaryKey(id);
    }

    /**
    * 分页查询 示例
    * @param page
    * @return
    *//**
    @Override
    public PageInfo query(Page page) {
        PageInfo pageInfo = new PageInfo(page);
        pageInfo.setRecords(${file_name?uncap_first}Mapper.query(pageInfo));
        return pageInfo;
    }
    */


    @Autowired
    private ${file_name}Mapper ${file_name?uncap_first}Mapper;

}
