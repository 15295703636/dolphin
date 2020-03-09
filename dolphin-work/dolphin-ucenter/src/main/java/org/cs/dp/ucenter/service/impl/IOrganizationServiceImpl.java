package org.cs.dp.ucenter.service.impl;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.exception.BaseException;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dolphin.common.utils.GetNameByCode;
import org.cs.dolphin.common.utils.ThreadLocalUserInfoUtil;
import org.cs.dp.radar.api.entity.RestDept;
import org.cs.dp.radar.api.entity.RestDeptReq;
import org.cs.dp.radar.api.entity.RestError;
import org.cs.dp.ucenter.domain.GetUserReqBean;
import org.cs.dp.ucenter.domain.TreeNodeBean;
import org.cs.dp.ucenter.domain.entity.OrganizationEntity;
import org.cs.dp.ucenter.mapper.OrganizationMapper;
import org.cs.dp.ucenter.mapper.SonarMapper;
import org.cs.dp.ucenter.mapper.UserMapper;
import org.cs.dp.ucenter.service.IOrganizationService;
import org.cs.dp.ucenter.service.ISoMruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName IOrganizationServiceImpl
 * @Description 组织信息维护
 * @Author Liujt
 * @Date 2019/11/22 11:05
 **/
@Slf4j
@Service
public class IOrganizationServiceImpl implements IOrganizationService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SonarMapper sonarMapper;
    @Autowired
    private OrganizationMapper organizationMapper;
    @Autowired
    private ISoMruService iSoMruService;

    @Override
    @Transactional(rollbackFor = {Exception.class, BaseException.class})
    public ReturnInfo addOrg(OrganizationEntity param) throws BaseException {
        if (null == param.getCustomer_id()) {
            param.setCustomer_id(ThreadLocalUserInfoUtil.get().getCustomer_id());
        }
        param.setYsx_id(dealAddOrg(param));
        organizationMapper.insertSelective(param);
        return new ReturnInfo();
    }

    /**
     * 调用云视讯添加组织接口
     *
     * @param param
     */
    private Long dealAddOrg(OrganizationEntity param) throws BaseException {
        OrganizationEntity orgParam = new OrganizationEntity();
        orgParam.setOrg_id(param.getOrg_preid());
        OrganizationEntity organization = organizationMapper.getList(orgParam).get(0);
        RestDeptReq restDeptReq = new RestDeptReq();
        restDeptReq.setParentId(organization.getYsx_id());
        restDeptReq.setFullName(param.getOrg_name());
        restDeptReq.setShortName(param.getOrg_name());
        ReturnInfo returnInfo = iSoMruService.getService(iSoMruService.ADDDEPT_NAME, ThreadLocalUserInfoUtil.get().getCustomer_id(), restDeptReq);
        if (MessageCode.COMMON_SUCCEED_FLAG != returnInfo.getReturnCode()) {
            throw new BaseException("添加组织调用云视讯接口异常", GetNameByCode.getNameByCode(((RestError) returnInfo.getReturnData()).getErrorCode()));
        } else {
            return ((RestDept) returnInfo.getReturnData()).getId();
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, BaseException.class})
    public ReturnInfo upload(MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        Workbook workbook = null;
        if (name.split("\\.")[1].equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        } else if (name.split("\\.")[1].equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        }
        TreeNodeBean treeNodeBean = tree(workbook);
        treeNodeBean = treeNodeBean.getChild().get(0);

        if (null != treeNodeBean) {
            organizationMapper.deleteByPrimaryKey(null, ThreadLocalUserInfoUtil.get().getCustomer_id());
            OrganizationEntity org = organizationMapper.getList(new OrganizationEntity(ThreadLocalUserInfoUtil.get().getCustomer_id())).get(0);
            log.info("Excel遍历{}结果：{};", org.getOrg_id(), JSON.toJSONString(treeNodeBean));
            saveTreeOrg(Arrays.asList(treeNodeBean), org.getOrg_id());
        }
        return new ReturnInfo();
    }

    @Override
    public void export(HttpServletResponse response) {
        //TODO 组织导出接口
    }

    private static TreeNodeBean tree(Workbook wb) {
        // 先初始化一个结果
        TreeNodeBean treeNode = new TreeNodeBean();

        int numberOfSheets = wb.getNumberOfSheets();
        for (int x = 0; x < numberOfSheets; x++) {
            XSSFSheet sheet = (XSSFSheet) wb.getSheetAt(x);
            int columnNum = 0;
            if (sheet.getRow(0) != null) {
                columnNum = sheet.getRow(0).getLastCellNum() - sheet.getRow(0).getFirstCellNum();
            }
            // 初始化一些计数器
            int id = 0; // 遍历赋值连续ID
            int level = 0;// 记录层级
            int rowNumber = 0;// 记录行号
            treeNode.setId("" + id);
            treeNode.setName("组织图");
            treeNode.setChild(new ArrayList<>());
            // 初始化一个缓存map , 用于缓存最新的父类
            HashMap<Integer, TreeNodeBean> map = new HashMap<>();
            map.put(level, treeNode);
            // 开始遍历
            if (columnNum > 0) {
                for (Row row : sheet) {
                    rowNumber++;
                    // 跳过前两行
                    if (rowNumber < 2) {
                        continue;
                    }

                    String[] singleRow = new String[columnNum];
                    int n = 0;
                    for (int i = 0; i < columnNum; i++) {
                        // 查询到有信息的行
                        Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        // 判断是否为空
                        if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                            n++;
                            // 跳过循环
                            continue;
                        } else {// 不是空
                            id++;
                            singleRow[n] = cell.getStringCellValue().trim();
                            TreeNodeBean treeNodeTmp = new TreeNodeBean();
                            level = n + 1;
                            treeNodeTmp.setId("" + id);
                            treeNodeTmp.setName(singleRow[n]);
                            treeNodeTmp.setChild(new ArrayList<>());
                            // 取到他的父类
                            TreeNodeBean pTreeNode = map.get(n);
                            treeNodeTmp.setPid(pTreeNode.getId());
                            pTreeNode.getChild().add(treeNodeTmp);
                            // 更新缓存
                            map.put(level, treeNodeTmp);
                            break;
                        }
                    }
                }
            }
        }
        return treeNode;
    }

    /**
     * @param treeNodeBean 子节点
     * @param periodId     fu
     * @return
     */
    public boolean saveTreeOrg(List<TreeNodeBean> treeNodeBean, Integer periodId) {
        OrganizationEntity org = null;
        if (treeNodeBean.size() > 0) {
            for (TreeNodeBean item : treeNodeBean) {
                org = new OrganizationEntity(
                        null, item.getName(), null, periodId, ThreadLocalUserInfoUtil.get().getCustomer_id()
                );
                //插入数据库
                organizationMapper.insertSelective(org);

                //子节点继续循环
                saveTreeOrg(item.getChild(), org.getOrg_id());
            }
        }

        return false;
    }

    @Override
    public ReturnInfo editOrg(OrganizationEntity param) throws BaseException {
        dealEditOrg(param);
        organizationMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    private Long dealEditOrg(OrganizationEntity param) throws BaseException {
        OrganizationEntity orgParam = new OrganizationEntity();
        orgParam.setOrg_id(param.getOrg_preid());
        OrganizationEntity organization = organizationMapper.getList(orgParam).get(0);
        RestDeptReq restDeptReq = new RestDeptReq();
        restDeptReq.setParentId(organization.getYsx_id());
        restDeptReq.setFullName(param.getOrg_name());
        restDeptReq.setShortName(param.getOrg_name());
        ReturnInfo returnInfo = iSoMruService.getService(iSoMruService.ADDDEPT_NAME, ThreadLocalUserInfoUtil.get().getCustomer_id(), restDeptReq);
        if (MessageCode.COMMON_SUCCEED_FLAG != returnInfo.getReturnCode()) {
            throw new BaseException("添加组织调用云视讯接口异常", GetNameByCode.getNameByCode(((RestError) returnInfo.getReturnData()).getErrorCode()));
        } else {
            return ((RestDept) returnInfo.getReturnData()).getId();
        }
    }

    @Override
    public ReturnInfo delOrg(List<Integer> id) throws BaseException {
        Integer customer_id = ThreadLocalUserInfoUtil.get().getCustomer_id();
        if (0 < sonarMapper.getDeviceByOrg(customer_id, id.get(0))) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, "该组织节点下存在设备!");
        }

        GetUserReqBean getUser = new GetUserReqBean();
        getUser.setCustomer_id(customer_id);
        getUser.setOrg_id(id.get(0));
        if (userMapper.getUsersList(getUser).size() > 0) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, "该组织节点下存在用户!");
        }

        if (0 < sonarMapper.getServerByOrg(customer_id, id.get(0))) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, "该组织节点下存在流媒体服务!");
        }

        List<Integer> childId = organizationMapper.getChildIdByParentId(id.get(0));
        childId.add(id.get(0));

        for (Integer orgId : childId) {
            dealDelOrg(orgId);
        }
        organizationMapper.deleteByPrimaryKey(childId, null);
        return new ReturnInfo();
    }

    private Long dealDelOrg(Integer orgId) throws BaseException {
        OrganizationEntity orgParam = new OrganizationEntity();
        orgParam.setOrg_id(orgId);
        OrganizationEntity organization = organizationMapper.getList(orgParam).get(0);
        ReturnInfo returnInfo = iSoMruService.getService(iSoMruService.DELETEADDDEPT_NAME, ThreadLocalUserInfoUtil.get().getCustomer_id(), organization.getYsx_id());
        if (MessageCode.COMMON_SUCCEED_FLAG != returnInfo.getReturnCode()) {
            throw new BaseException("删除组织调用云视讯接口异常", GetNameByCode.getNameByCode(((RestError) returnInfo.getReturnData()).getErrorCode()));
        } else {
            return ((RestDept) returnInfo.getReturnData()).getId();
        }
    }

    @Override
    public ReturnInfo getOrg(RequestPage<SplitPageInfo, OrganizationEntity> param) {
        if (null != param.getPage()) {
            PageHelper.startPage(param.getPage().getCurrPage(), param.getPage().getPerPageNum());
        }
        param.getInfo().setCustomer_id(ThreadLocalUserInfoUtil.get().getCustomer_id());
        List<OrganizationEntity> list = organizationMapper.getList(param.getInfo());
        PageInfo p = new PageInfo(list);
        param.getPage().setTotals((int) p.getTotal());
        return new ReturnInfo(param.getPage(), list);
    }

    @Override
    public ReturnInfo getOrg() {
        OrganizationEntity organization = new OrganizationEntity();
        organization.setCustomer_id(ThreadLocalUserInfoUtil.get().getCustomer_id());
        return new ReturnInfo(organizationMapper.getList(organization));
    }

    @Override
    public ReturnInfo getOrgTree(OrganizationEntity param) {
        List<OrganizationEntity> list = organizationMapper.getList(param);
        list = listGetTree(list);
        log.info("组织树：{}", JSON.toJSONString(list));
        return new ReturnInfo(list);
    }

    private static List<OrganizationEntity> listGetTree(List<OrganizationEntity> list) {
        List<OrganizationEntity> treeList = new ArrayList<>();
        for (OrganizationEntity tree : list) {
            //找到根
            if (tree.getOrg_preid() == 0) {
                treeList.add(tree);
            }
            //找到子
            for (OrganizationEntity treeNode : list) {
                if (treeNode.getOrg_preid() == tree.getOrg_id()) {
                    if (tree.getNode() == null) {
                        tree.setNode(new ArrayList<>());
                    }
                    tree.getNode().add(treeNode);
                }
            }
        }
        return treeList;
    }

}
