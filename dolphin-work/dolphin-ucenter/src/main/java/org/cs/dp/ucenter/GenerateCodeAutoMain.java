package org.cs.dp.ucenter;

import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.utils.StringUtil;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.testng.annotations.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.cs.dp.ucenter.CreateADEG.modifyFileContent;

/**
 * @ClassName GenerateCodeAutoMain
 * @Description 自动生成代码入口(只在开发分支使用)
 * @Author Liujt
 * @Date 2019/11/30 9:22
 **/
@Slf4j
public class GenerateCodeAutoMain {

    //--------------------------------生成控制层，接口层，实现类配置--------------------------------
    //生成类的包头,一个工程设置1次
    public static final String PACKAGE_PATH = "org.cs.dp.ucenter.";
    //文件保存位置(可以指定生成的控制层，接口，实现类的保存路径)
    public static final String SAVE_PATH = null;//"C:\\Users\\Liujt\\Desktop\\test\\";
    //如果SAVE_PATH 为 null，默认保存在工程中
    public static final String SRC_PATH = "src/main/java/";
    //数据库表名
    private static final String TABLE_NAME = "user2org";
    //业务变量名
    public static final String NAME = "UserOrg";
    //业务注释
    public static final String EXPLAIN = "租户管理";
    //作者
    public static final String AUTHOR = "Liujt";

    //--------------------------------mybatis插件配置--------------------------------
    public static Map DATA_SOURCE = new HashMap() {
        {
            //数据驱动
            put("location", "D:\\repository\\mysql\\mysql-connector-java\\5.1.47\\mysql-connector-java-5.1.47.jar");
            //数据地址
            put("connectionURL", "jdbc:mysql://dbserver:3306/vmediax?serverTimezone=UTC");
            //数据用户名
            put("userId", "root");
            //数据密码
            put("password", "a1b2c3d4");
            //生成的实体bean位置
            put("javaBeanPackage", PACKAGE_PATH + "domain.entity");
            //生成的Mapper位置
            put("mapperPackage", PACKAGE_PATH + "mapper");
            //生成的xml
            put("xmlPackage", PACKAGE_PATH + "mapper");
            //以上配置理论上一个业务模块只配置一次

            //表明+生成的实体类名称，和 NAME 字段保持一致 TODO 表名
            put("tableName", TABLE_NAME);
            put("javaBeanName", NAME + "Entity");
        }
    };

    /*----------------------------以上信息需要配置----------------------------*/

    @Test
    public void run() throws Exception {
        //获取generatorConfig.xml文件所在位置
        String genCfg = GenerateCodeAutoMain.class.getClassLoader().getResource("generatorConfig.xml").getPath();

        //先清空文件
        CreateADEG.clearInfoForFile(genCfg);
        //读取模板并写入设置的值
        String newContent = FreeMarkUtil.getTemplate("/generator.ftl", DATA_SOURCE);
        //写入最新数据
        CreateADEG.appendInfoToFile(genCfg, newContent);

        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        System.out.println(genCfg);
        File configFile = new File(genCfg);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = null;
        config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

        //Mapper名称更改
        editJavaBean(genCfg);

        //生成控制层，接口，实现类
        CreateADEG.create(genCfg);

    }

    //修改mybatis生成的bean名称
    public void editJavaBean(String genCfg) throws Exception {
        String basePath = genCfg.split("target/")[0]
                .concat(GenerateCodeAutoMain.SRC_PATH)
                .concat(PACKAGE_PATH.replace(".", "/"))
                .concat("mapper/");

        log.info(basePath);

        String mapper = "Mapper";
        String entityMapper = "EntityMapper";

        String javaPath = basePath.concat(NAME).concat(mapper).concat(".java");
        new File(basePath.concat(NAME).concat(entityMapper).concat(".java")).renameTo(new File(javaPath));

        modifyFileContent(javaPath, NAME + entityMapper, NAME + mapper);

        String xmlPath = basePath.concat(NAME).concat(mapper).concat(".xml");
        new File(basePath.concat(NAME).concat(entityMapper).concat(".xml")).renameTo(new File(xmlPath));

        modifyFileContent(xmlPath, NAME + entityMapper, NAME + mapper);
    }
}

@Slf4j
class CreateADEG {
    //生成的文件名称后缀
    private static final String SERVER_TYPE_C = "Controller.java";
    private static final String SERVER_TYPE_I = "Service.java";
    private static final String SERVER_TYPE_IMPL = "ServiceImpl.java";
    //接口及服务类前缀
    private static final String INTERFACE_PRE = "I";

    //时间
    private static final String DATE = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());

    public static void create(String path) throws IOException {
        Map param = new HashMap();
        //包路径
        param.put("packagePath", GenerateCodeAutoMain.PACKAGE_PATH);
        //业务变量名
        param.put("name", GenerateCodeAutoMain.NAME);
        //业务注释
        param.put("explain", GenerateCodeAutoMain.EXPLAIN);
        //作者
        param.put("author", GenerateCodeAutoMain.AUTHOR);
        //时间
        param.put("date", DATE);

        String saveConPath = null;
        String saveIntPath = null;
        String saveImpPath = null;
        if (StringUtil.isNull(GenerateCodeAutoMain.SAVE_PATH)) {
            String basePath = path.split("target/")[0]
                    .concat(GenerateCodeAutoMain.SRC_PATH)
                    .concat(GenerateCodeAutoMain.PACKAGE_PATH.replace(".", "//"));
            saveConPath = basePath + "controller\\";
            saveIntPath = basePath + "service\\";
            saveImpPath = basePath + "service\\impl\\";
        } else {
            saveConPath = GenerateCodeAutoMain.SAVE_PATH + "controller\\";
            saveIntPath = GenerateCodeAutoMain.SAVE_PATH + "service\\";
            saveImpPath = GenerateCodeAutoMain.SAVE_PATH + "service\\impl\\";
        }

        writerFile(
                saveConPath,
                FreeMarkUtil.getTemplate("/controller.ftl", param),
                GenerateCodeAutoMain.NAME + SERVER_TYPE_C);

        writerFile(
                saveIntPath,
                FreeMarkUtil.getTemplate("/interface.ftl", param),
                INTERFACE_PRE + GenerateCodeAutoMain.NAME + SERVER_TYPE_I);

        writerFile(
                saveImpPath,
                FreeMarkUtil.getTemplate("/impl.ftl", param),
                INTERFACE_PRE + GenerateCodeAutoMain.NAME + SERVER_TYPE_IMPL);

    }

    //写文件
    private static void writerFile(String path, String content, String name) throws IOException {
        log.info("path:" + path + "\n" + "name:" + name);
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        } else {
            removeDir(file, name);
        }
        file = new File(path + name);
        file.createNewFile();
        Writer out = new FileWriter(file);
        out.write(content);
        out.close();
    }

    //删除文件
    private static void removeDir(File dir, String name) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (!file.isDirectory()) {
                if (file.getName().contains(name)) {
                    log.info(file + ":" + file.delete());
                }
            }
        }
    }

    // 清空已有的文件内容，以便下次重新写入新的内容
    public static void clearInfoForFile(String fileName) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void modifyFileContent(String path, String oldstr, String newStr) throws Exception {
        // 读
        File file = new File(path);
        FileReader in = new FileReader(file);
        BufferedReader bufIn = new BufferedReader(in);
        // 内存流, 作为临时流
        CharArrayWriter tempStream = new CharArrayWriter();
        // 替换
        String line = null;
        while ((line = bufIn.readLine()) != null) {
            if (line.startsWith("public interface " + oldstr)) {
                /*tempStream.write("import org.apache.ibatis.annotations.Mapper;");
                tempStream.append(System.getProperty("line.separator"));
                tempStream.write("@Mapper");
                tempStream.append(System.getProperty("line.separator"));*/
                // 将该行写入内存
                tempStream.write(line.replaceAll(oldstr, newStr));
                // 添加换行符
                tempStream.append(System.getProperty("line.separator"));
            } else if (line.lastIndexOf(oldstr + "\">") > 0) {
                // 将该行写入内存
                tempStream.write(line.replaceAll(oldstr, newStr));
                // 添加换行符
                tempStream.append(System.getProperty("line.separator"));
            } else {
                // 将该行写入内存
                tempStream.write(line);
                // 添加换行符
                tempStream.append(System.getProperty("line.separator"));
            }
        }
        // 关闭 输入流
        bufIn.close();
        // 将内存中的流 写入 文件
        FileWriter out = new FileWriter(file);
        tempStream.writeTo(out);
        out.close();
    }

    // 在已有的文件后面追加信息
    public static void appendInfoToFile(String fileName, String info) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, true);
            info = info + System.getProperty("line.separator");
            fileWriter.write(info);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

@Slf4j
class FreeMarkUtil {
    /**
     * FreeMark模板配置
     */
    private static final freemarker.template.Configuration CONFIGURATION;
    /**
     * 配置模板路径
     */
    private static final String TEMPLATE_PATH = "/ftl";
    /**
     * 配置模板编码格式
     */
    private static final String ENCODING = "UTF-8";

    static {
        CONFIGURATION = new freemarker.template.Configuration(freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        CONFIGURATION.setDefaultEncoding(ENCODING);
        CONFIGURATION.setClassForTemplateLoading(FreeMarkUtil.class,
                TEMPLATE_PATH);
    }

    private FreeMarkUtil() {
    }

    /**
     * @param templateName 调用的模板的路径名称
     * @param map          填充的参数
     * @return
     */
    public static String getTemplate(String templateName, Map<String, Object> map) {
        StringWriter out = new StringWriter();
        try {
            Template t = CONFIGURATION.getTemplate(templateName);
            t.process(map, out);
            out.flush();
            return out.getBuffer().toString();
        } catch (Exception e) {
            log.info("FreeMarkUtil文件读取失败:" + e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    log.error("FreeMarkUtil的out流关闭失败:" + e.getMessage());
                }
            }
        }
        return null;
    }
}
