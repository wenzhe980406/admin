package ${package_service};

import cn.cepec.talroad.config.domain.Page;
import cn.cepec.talroad.config.domain.PageInfo;
import ${package_domain}.${file_name};


/**
* 开发公司：中节能天融科技有限公司
*
* ${tabInfo} 接口层
*
* @author ${author} ${module_name}
* @created Create Time: ${date?string('yyyy-MM-dd hh:mm:ss')}
*/
public interface ${file_name}Service {

    public boolean save(${file_name} ${file_name?uncap_first});

    public boolean update(${file_name} ${file_name?uncap_first});

    public boolean delete(Integer id);

    public ${file_name} selectById(Integer id);
}
