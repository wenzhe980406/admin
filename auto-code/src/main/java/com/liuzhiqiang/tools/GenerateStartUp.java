package com.liuzhiqiang.tools;/**
 * Created by lzq on 2019/1/17.
 */

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 开发公司：青岛海豚数据技术有限公司
 * 版权：青岛海豚数据技术有限公司
 * <p>
 * GenerateStartUp
 *
 * @author 刘志强
 * @created Create Time: 2019/1/17
 */
public class GenerateStartUp {
    //生成目录
    public static String baseFile = "E:\\zjn-tn2\\cntn-env-protect";
    //模块名称
    public static String modeName = "rain";
    //模块名称
    public static String tabName = "t_waste_water_rain_ostep_thrshud_real_time";
    //作者
    public static String  author = "王松";

    //模块名称中文
    public static String  modeNameCN = "雨水";




    public static void main(String[] args) {
        List<String> warnings = new ArrayList<String>();

        try {
            boolean overwrite = true;
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("mybatis-generator.xml");
            ConfigurationParser cp = new ConfigurationParser(warnings);

            Configuration config = cp.parseConfiguration(is);
            GenerateCodeBefore generateCodeBefore = new GenerateCodeBefore(baseFile,modeName,tabName,config);

            generateCodeBefore.execute();



            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        }
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }
    //模块名称
    public static String basePackage = "cn.cepec.talroad";

    public static String  TABLE_INFO ;
}