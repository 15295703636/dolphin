package ${packagePath}controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import ${packagePath}domain.entity.${name}Entity;
import ${packagePath}service.I${name}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @ClassName ${name}Controller
* @Description ${explain}
* @Author ${author}
* @Date ${date}
**/
@RestController
@RequestMapping("${name?uncap_first}")
@Api(tags = "【${explain}】")
public class ${name}Controller {
    @Autowired
    private I${name}Service i${name}Service;

    @PostMapping("add")
    @ApiOperation(value = "添加${explain}", notes = "${explain}")
    public ReturnInfo add${name}(@RequestBody ${name}Entity param){
        return i${name}Service.add${name}(param);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除${explain}", notes = "${explain}")
    public ReturnInfo del${name}(@RequestBody Integer id){
        return i${name}Service.del${name}(id);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改${explain}", notes = "${explain}")
    public ReturnInfo edit${name}(@RequestBody ${name}Entity param){
        return i${name}Service.edit${name}(param);
    }

    @PostMapping("get")
    @ApiOperation(value = "查询${explain}", notes = "${explain}")
    public ReturnInfo get${name}(@RequestBody RequestPage<SplitPageInfo, Object> param){
        return i${name}Service.get${name}(param);
    }
}
