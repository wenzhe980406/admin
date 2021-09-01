package ${package_vo};

import ${package_domain}.${file_name};
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
* 开发公司：中节能天融科技有限公司
*
* ${tabInfo} 值对象
*
* @author ${author}
* @created Create Time:  ${date?string('yyyy-MM-dd hh:mm:ss')}
*/
@ApiModel(description="${tabInfo}")
@Data
public class ${file_name}Vo extends ${file_name} {

}