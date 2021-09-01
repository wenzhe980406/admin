package ${package_controller};

import cn.cepec.talroad.common.util.RSubject;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ${package_service}.${file_name}Service;
import cn.cepec.talroad.config.domain.Page;
import ${package_domain}.${file_name};

/**
* 开发公司：中节能天融科技有限公司
*
* ${file_name}Controller
* ${tabInfo} 控制层
* @author ${author}
* @created Create Time: ${date?string('yyyy-MM-dd hh:mm:ss')}
*/
@RestController
@RequestMapping("/${module_name}/${file_name?uncap_first}s")
@Api(tags = "${modeNameCN}")
public class ${file_name}Controller {


    /**
    *  根据ID 查询 ${tabInfo} 详细信息
    * @param id
    * @return
    */
    @GetMapping(value = "/{id}")
    @ApiOperation("根据ID查询${tabInfo}信息")
    public RSubject<${file_name}> get(@PathVariable("id") int id){
        RSubject<${file_name}> rSubject = new RSubject();
        try {
            rSubject.setData(${file_name?uncap_first}Service.selectById(id));
            rSubject.setCode(200);
        }catch (Exception e){
            rSubject.setCode(500);
            rSubject.setMsg(" 查询失败,id 不存在");
        }
        return  rSubject;
    }

    /**
    *  根据ID 删除 ${tabInfo} 信息
    * @param id
    * @return
    */
    @DeleteMapping(value = "/{id}")
    @ApiOperation("根据ID删除")
    public RSubject delete(@PathVariable("id") int id){
        RSubject rSubject = new RSubject();
        try {
            rSubject.setData(${file_name?uncap_first}Service.delete(id));
            rSubject.setCode(200);
        }catch (Exception e){
            rSubject.setCode(500);
            rSubject.setMsg(" 查询失败，不可预知的异常");
        }
        return  rSubject;
    }

    /**
    *  新增到 ${tabInfo}
    * @param ${file_name?uncap_first}
    * @return
    */
    @PostMapping(value = "")
    @ApiOperation("新增到${tabInfo}")
    public RSubject save(@RequestBody ${file_name} ${file_name?uncap_first}){
        RSubject rSubject = new RSubject();
        try {
            rSubject.setData(${file_name?uncap_first}Service.save(${file_name?uncap_first}));
            rSubject.setCode(200);
        }catch (Exception e){
            rSubject.setCode(500);
            rSubject.setMsg(" 查询失败，不可预知的异常");
        }
        return  rSubject;
    }


    /**
    *  编辑 ${tabInfo}
    * @param ${file_name?uncap_first}
    * @return
    */
    @PutMapping(value = "/{id}")
    @ApiOperation("编辑${tabInfo}")
    public RSubject update(@PathVariable("id") int id,@RequestBody ${file_name} ${file_name?uncap_first}){
        RSubject rSubject = new RSubject();
        try {
            ${file_name?uncap_first}.setId(id);
            rSubject.setData(${file_name?uncap_first}Service.update(${file_name?uncap_first}));
            rSubject.setCode(200);
        }catch (Exception e){
            rSubject.setCode(500);
            rSubject.setMsg(" 编辑失败");
        }
        return  rSubject;
    }

    /**
    *  分页查询 ${tabInfo}
    * @param page
    * @return
    */
    @GetMapping(value = "")
    @ApiOperation("分页查询${tabInfo}")
    public RSubject query(@RequestBody Page page){
        RSubject rSubject = new RSubject();
        try {
           // rSubject.setData(${file_name?uncap_first}Service.query(page));
            rSubject.setCode(200);
        }catch (Exception e){
            rSubject.setCode(500);
            rSubject.setMsg(" 编辑失败");
        }
        return  rSubject;
    }

    @Autowired
    public ${file_name}Service ${file_name?uncap_first}Service;

}