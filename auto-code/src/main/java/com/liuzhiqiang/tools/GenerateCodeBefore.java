package com.liuzhiqiang.tools;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.google.common.base.CaseFormat;
import lombok.SneakyThrows;
import org.mybatis.generator.config.*;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateCodeBefore {
    private String basePath;
    private Configuration config;

    private String modeName;

    private String tabName;

    private String tabInfo;

    public String getTabInfo(){
        return  tabInfo;
    }

    public GenerateCodeBefore(String basePath, String modeName,String tabName,Configuration config) {
        this.basePath = basePath;
        this.config = config;
        this.modeName = modeName;
        this.tabName = tabName.trim();
    }

    @SneakyThrows
    public void execute() {
        Context context = config.getContext("DB2Tables");

        getTableInfo(context.getJdbcConnectionConfiguration());

        context.getTableConfigurations().get(0).setTableName(this.tabName);
        context.getTableConfigurations().get(0).setDomainObjectName(humpToLine(this.tabName.replace("t_","")));
        //生成Dao类存放位置
        setModeNameAndBasePath(context.getJavaClientGeneratorConfiguration(),false);
        //生成xml文件位置
        setModeNameAndBasePath(context.getSqlMapGeneratorConfiguration(),true);
        //生成模型
        setModeNameAndBasePath(context.getJavaModelGeneratorConfiguration(),false);

    }


    public static String humpToLine(String str) {
        String hump = lineToHump(str);
        String clazzName = (str.charAt(0)+"").toUpperCase()+hump.substring(1,hump.length());
        return clazzName;
    }

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /** 下划线转驼峰 */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public void getTableInfo(JDBCConnectionConfiguration jdbcConnectionConfiguration) throws ClassNotFoundException, SQLException {
        Class.forName(jdbcConnectionConfiguration.getDriverClass());
        Connection conn = DriverManager.getConnection(jdbcConnectionConfiguration.getConnectionURL(),jdbcConnectionConfiguration.getUserId(),jdbcConnectionConfiguration.getPassword());
        ResultSet resultSet =  conn.createStatement().executeQuery("SELECT TABLE_NAME,TABLE_COMMENT FROM information_schema.TABLES WHERE TABLE_NAME like '"+this.tabName+"'");
         while(resultSet.next()){
             GenerateStartUp.TABLE_INFO = resultSet.getString(2);
             break;
         }
    }

    //设置模型名称 生成路径
    public void setModeNameAndBasePath(PropertyHolder propertyHolder,boolean isMapper) {
        Field fields[] = propertyHolder.getClass().getDeclaredFields();

        try {
            Field.setAccessible(fields, true);
            for (int i = 0; i < fields.length; i++) {
                String name = fields[i].getName();
                Object val = fields[i].get(propertyHolder);

                if (name.equals("targetProject")) { //添加跟路径
                    File file = new File(basePath + "/" + val);
                    file.mkdirs();
                    fields[i].set(propertyHolder,  file.getAbsolutePath()+"\\");
                }

                if (name.equals("targetPackage")) { //包路径
                    if (isMapper) {
                        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = (SqlMapGeneratorConfiguration) propertyHolder;
                        String path = GenerateStartUp.baseFile +"/" +sqlMapGeneratorConfiguration.getTargetProject()+"/"+(val + ".auto." + modeName).replaceAll("\\.", "/");
                        Context context = config.getContext("DB2Tables");
                        path = path+"/"+context.getTableConfigurations().get(0).getDomainObjectName()+"Mapper.xml";

                        File file =new File(path);
                        file.delete();

                        fields[i].set(propertyHolder, val + ".auto." + modeName);
                    }else{
                        fields[i].set(propertyHolder, GenerateStartUp.basePackage + "." + val + ".auto." + modeName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
