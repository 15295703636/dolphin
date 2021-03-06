package org.cs.dolphin.common.utils;

public enum GetNameByCode {
    //云视讯转码
    _1001(1001, "无效的token"),
    _1002(1002, "无效的参数。"),
    _1003(1003, "参数deviceSN为空或者不合法。"),
    _1004(1004, "无效的媒体类型，只支持application/json。"),
    _1005(1005, "您不具备执行此操作的权限。"),
    _1006(1006, "JSON串内字段名拼写错误。"),
    _1007(1007, "系统内部错误。"),
    _1008(1008, "操作失败！"),
    _1009(1009, "获取失败！"),
    _1010(1010, "不支持！"),
    _1011(1011, "请重试。"),
    _1012(1012, "服务未被激活。"),
    _1013(1013, "会议端口数已超出许可。"),
    _1014(1014, "网关端口数已超出许可。"),
    _1015(1015, "请重试。"),
    _1016(1016, "无法访问中心区。"),
    _1017(1017, "无法访问主控区。"),
    _1018(1018, "正在处理您的请求，请勿重复请求。"),
    _1019(1019, "本地可用区已停止。"),
    _1020(1020, "本地可用区未启动。"),
    _1100(1100, "无效的用户名或密码。"),
    _1101(1101, "登录失败超过5次，账号会被锁定，您还有{0}次机会。"),
    _1102(1102, "您的账户已被临时锁定，请稍后再试或直接联系管理员解锁。"),
    _1103(1103, "您的账户已被停用，请联系管理员。"),
    _1104(1104, "没有找到这个用户名！"),
    _1105(1105, "邮箱和用户名不匹配！"),
    _1106(1106, "该公司管理员未分配到任何公司！"),
    _1109(1109, "请勿重复登录。"),
    _1400(1400, "会议不存在。"),
    _1401(1401, "会议号码已被占用！"),
    _1402(1402, "正在修改会议。"),
    _1403(1403, "正在删除会议。"),
    _1404(1404, "正在结束会议。"),
    _1405(1405, "正在开启会议。"),
    _1406(1406, "会议不处于预约状态。"),
    _1407(1407, "会议已开启。"),
    _1408(1408, "会议未开启。"),
    _1409(1409, "会议已失效。"),
    _1410(1410, "该终端未加入会议。"),
    _1411(1411, "会议进行中。"),
    _1412(1412, "该会议号码已被占用！"),
    _1415(1415, "无效的会议时间。"),
    _1418(1418, "无效的会议ID."),
    _1421(1421, "未找到合适的MRU."),
    _1422(1422, "未找到合适的Gateway."),
    _1424(1424, "连接MRU失败。"),
    _1427(1427, "不允许重名。"),
    _1431(1431, "当前不是主会场模式。"),
    _1433(1433, "全部静音失败。"),
    _1436(1436, "连接与会者失败。"),
    _1439(1439, "挂断与会者失败。"),
    _1442(1442, "分屏设置失败。"),
    _1445(1445, "字幕设置失败。"),
    _1448(1448, "对与会者静音失败。"),
    _1451(1451, "删除与会者失败。"),
    _1454(1454, "邀请AVC终端失败。"),
    _1455(1455, "邀请SVC终端失败。"),
    _1457(1457, "产生云会议室号失败，已超时。"),
    _1460(1460, "未找到名为SVC的会议模板。"),
    _1463(1463, "会议延时失败。"),
    _1466(1466, "会议端口数已耗尽。"),
    _1467(1467, "公司端口数已耗尽。"),
    _1468(1468, "平台端口数已耗尽。"),
    _1472(1472, "MRU端口数已耗尽。"),
    _1475(1475, "公司Gateway端口数已耗尽。"),
    _1478(1478, "平台Gateway音频端口数已耗尽。"),
    _1481(1481, "平台Gateway视频端口数已耗尽。"),
    _1483(1483, "开启录制失败。"),
    _1484(1484, "停止录制失败。"),
    _1485(1485, "暂停录制失败。"),
    _1486(1486, "恢复录制失败。"),
    _1487(1487, "设置录制分屏失败。"),
    _1490(1490, "开启直播失败。"),
    _1491(1491, "停止直播失败。"),
    _1492(1492, "正在授予双流令牌。"),
    _1493(1493, "正在取消双流令牌。"),
    _1494(1494, "资源不足导致录制失败。"),
    _1496(1496, "直播服务器不可用。"),
    _1500(1500, "无效的会议控制请求。"),
    _1601(1601, "终端名称不能为空！"),
    _1602(1602, "终端呼叫模式不能为空!"),
    _1603(1603, "终端的SIP号码不能为空！"),
    _1604(1604, "终端的SIP密码不能为空！"),
    _1605(1605, "终端的IP地址不能为空！"),
    _1606(1606, "无效的SIP号码。"),
    _1607(1607, "无效的IP地址。"),
    _1608(1608, "指定的终端不存在。"),
    _1609(1609, "该E164号码已被占用！"),
    _1610(1610, "该序列号已被占用，请重新输入！"),
    _1611(1611, "该SIP号码已被占用！"),
    _1612(1612, "无效的E164号。"),
    _1613(1613, "该终端不存在。"),
    _1614(1614, "终端配置文件不存在。"),
    _1615(1615, "这些设备序列号已被分配了：{0}"),
    _1616(1616, "设备不在线。"),
    _1619(1619, "终端呼叫协议不合法。"),
    _1622(1622, "终端的设备序列号不能为空。"),
    _1625(1625, "终端的SIP号码和IP地址不能都为空。"),
    _1628(1628, "终端的E164号与IP地址不能都为空。"),
    _1631(1631, "终端的所属可用区不能为空。"),
    _1700(1700, "不能删除正在会议中的用户！"),
    _1701(1701, "密码不能为空！"),
    _1702(1702, "用户名不能为空！"),
    _1703(1703, "姓名不能为空！"),
    _1704(1704, "无效的邮箱地址！"),
    _1705(1705, "无效的手机号码！"),
    _1706(1706, "原密码输入错误！"),
    _1707(1707, "邮箱重复！"),
    _1708(1708, "手机重复！"),
    _1709(1709, "账号重复！"),
    _1711(1711, "请为部门管理员选择所在部门！"),
    _1712(1712, "邮箱不能为空！"),
    _1713(1713, "手机号不能为空！"),
    _1714(1714, "该用户不是公司管理员。"),
    _1715(1715, "该客服代表下存在公司或未指派的公司管理员，不允许删除!"),
    _1716(1716, "用户数量超出许可！"),
    _1717(1717, "用户名不合法！"),
    _1718(1718, "该用户拥有已预约的会议。"),
    _2025(2025, "该用户已拥有一个云会议室。"),
    _2400(2400, "该文件不存在。"),
    _2403(2403, "该文件链接已过期。"),
    _2406(2406, "不合法的文件链接。"),
    _2409(2409, "无法访问该文件。"),
    _2412(2412, "公司的文件存储空间已耗尽。"),
    _2100(2100, "不能删除一个有下级部门的部门。"),
    _2101(2101, "不能删除一个有下属用户或终端的部门。"),

    //呼叫协议
    SVC(36, "SVC"),
    SIP(37, "SIP"),
    H323(28, "H323"),

    //会议类型
    _1(1,"会议"),
    _2(2,"互动"),
    _3(3,"录播");

    private Integer code;
    private String name;

    GetNameByCode(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getNameByCode(int code) {
        for (GetNameByCode getNameByCode : GetNameByCode.values()) {
            if (code == getNameByCode.code) {
                return getNameByCode.name;
            }
        }
        return null;
    }
}
