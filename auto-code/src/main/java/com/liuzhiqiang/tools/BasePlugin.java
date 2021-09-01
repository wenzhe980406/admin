package com.liuzhiqiang.tools;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 开发公司：青岛海豚数据技术有限公司
 * 版权：青岛海豚数据技术有限公司
 * <p>
 * BasePlugin
 *
 * @author 刘志强
 * @created Create Time: 2019/1/16
 */
public class BasePlugin extends PluginAdapter {

     public String getProperty(String property){

        return GenerateStartUp.basePackage+"."+properties.getProperty(property)+"."+GenerateStartUp.modeName;
     }


    public boolean validate(List<String> list) {
        return true;
    }

    /**
     * 修改实体类
     *
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        classAnnotation(topLevelClass, null);
        Set<FullyQualifiedJavaType> set = new HashSet<FullyQualifiedJavaType>();
        set.add(new FullyQualifiedJavaType(Annotation.ApiModel.getClazz()));
        set.add(new FullyQualifiedJavaType(Annotation.DATA.getClazz()));
        topLevelClass.addImportedTypes(set);

        topLevelClass.addAnnotation(Annotation.ApiModel.getAnnotation() + "(value=\"" + GenerateStartUp.TABLE_INFO + "\")");
        topLevelClass.addAnnotation(Annotation.DATA.getAnnotation() + "");

        try {
            // 生成controller文件
            generateControllerFile(topLevelClass, introspectedTable);
            // 生成vo文件
            generateVoFile(topLevelClass, introspectedTable);
            // 生成service文件
            generateServiceFile(topLevelClass, introspectedTable);
            // 生成Impl文件
            generateImplFile(topLevelClass, introspectedTable);
            //生成 business mapper.xml
            generateBusinessMapperFile(topLevelClass, introspectedTable);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }

    /**
     * 修改mapper接口
     *
     * @param interfaze
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        interfazeAnnotation(interfaze, null);
        Set<FullyQualifiedJavaType> set = new HashSet<FullyQualifiedJavaType>();
        set.add(new FullyQualifiedJavaType(Annotation.Mapper.getClazz()));
        interfaze.addImportedTypes(set);
        interfaze.addAnnotation(Annotation.Mapper.getAnnotation() + "");
        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }

    /**
     * 实体类字段
     *
     * @param field
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @return
     */
    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        // 生成注释
        fieldAnnotation(field, introspectedColumn.getRemarks());
        // 生成注释结束


        // 追加ApiModelProperty注解
        topLevelClass.addImportedType(new FullyQualifiedJavaType(Annotation.ApiModelProperty.getClazz()));


        // 追加长度验证注解
        String a = field.getType().getShortName();
        String n = field.getName();
        if(n.equals("addTime") ||n.equals("updateUser")||n.equals("updateTime")||n.equals("addUser") ){
            field.addAnnotation(Annotation.ApiModelProperty.getAnnotation() + "(value=\"" + introspectedColumn.getRemarks() + "\",hidden = true)");
        }else{
            field.addAnnotation(Annotation.ApiModelProperty.getAnnotation() + "(value=\"" + introspectedColumn.getRemarks() + "\")");
        }


        if (StringUtils.equals("String", a)) {
          //  topLevelClass.addImportedType(new FullyQualifiedJavaType(Annotation.Length.getClazz()));
          //  field.addAnnotation(Annotation.Length.getAnnotation() + "(max = " + introspectedColumn.getLength() + ", message = \"" + introspectedColumn.getRemarks() + "名长度最长为" + introspectedColumn.getLength() + "\")");
        }
        // 追加日期格式化注解
        if (introspectedColumn.getJdbcTypeName() == "TIMESTAMP") {
            field.addAnnotation(Annotation.JsonFormat.getAnnotation() + "(pattern = \"yyyy-MM-dd\",timezone=\"GMT+8\")");
            topLevelClass.addImportedType(new FullyQualifiedJavaType(Annotation.JsonFormat.getClazz()));
        }
        // tinyint数据（Byte）转换成（Integer）类型
        if (StringUtils.equals("Byte", a)) {
            field.setType(new FullyQualifiedJavaType("java.lang.Integer"));
        }
        return super.modelFieldGenerated(field, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
    }


    /**
     * get方法 false 不生成
     *
     * @param method
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @return
     */
    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    /**
     * get方法 false 不生成
     *
     * @param method
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @return
     */
    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    /**
     * mapper接口方法
     * DeleteByPrimaryKey方法
     *
     * @param method
     * @param interfaze
     * @param introspectedTable
     * @return
     */
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        methodAnnotation(method, "根据主键删除数据");
        return super.clientDeleteByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable);
    }




    /**
     * mapper接口方法
     * Insert方法
     *
     * @param method
     * @param interfaze
     * @param introspectedTable
     * @return
     */
    public boolean clientInsertMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        methodAnnotation(method, "插入数据库记录（不建议使用）");
        return super.clientInsertMethodGenerated(method, interfaze, introspectedTable);
    }


    /**
     * mapper接口方法
     * InsertSelective方法
     *
     * @param method
     * @param interfaze
     * @param introspectedTable
     * @return
     */
    public boolean clientInsertSelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        methodAnnotation(method, "插入数据库记录（建议使用）");
        return super.clientInsertSelectiveMethodGenerated(method, interfaze, introspectedTable);
    }


    /**
     * mapper接口方法
     * SelectByPrimaryKey方法
     *
     * @param method
     * @param interfaze
     * @param introspectedTable
     * @return
     */
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        methodAnnotation(method, "根据主键id查询");
        return super.clientSelectByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable);
    }


    /**
     * mapper接口方法
     * UpdateByPrimaryKeySelective方法
     *
     * @param method
     * @param interfaze
     * @param introspectedTable
     * @return
     */
    public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        methodAnnotation(method, "修改数据(推荐使用)");
        return super.clientUpdateByPrimaryKeySelectiveMethodGenerated(method, interfaze, introspectedTable);
    }


    /**
     * mapper接口方法
     * UUpdateByPrimaryKey方法
     *
     * @param method
     * @param interfaze
     * @param introspectedTable
     * @return
     */
    public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        methodAnnotation(method, "修改数据");
        return super.clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(method, interfaze, introspectedTable);
    }

    /**
     * 方法注释生成
     *
     * @param method
     * @param explain
     */
    public static void methodAnnotation(Method method, String explain) {
        // 生成注释
        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(explain);
        method.addJavaDocLine(sb.toString());
        Parameter parm = method.getParameters().get(0);
        sb.setLength(0);
        sb.append(" * @param ");
        sb.append(parm.getName());
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine(" */");
        // 生成注释结束
    }


    /**
     * 属性注释生成
     *
     * @param field
     * @param explain
     */
    public static void fieldAnnotation(Field field, String explain) {
        // 生成注释
        StringBuilder sb = new StringBuilder();
        field.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(explain);
        field.addJavaDocLine(sb.toString());
        field.addJavaDocLine(" */");
        // 生成注释结束
    }

    /**
     * 类注释生成
     *
     * @param topLevelClass
     * @param explain
     */
    public static void classAnnotation(TopLevelClass topLevelClass, String explain) {
        // 生成注释
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine("* 开发公司：中节能天融科技有限公司");
        topLevelClass.addJavaDocLine("* ");
        topLevelClass.addJavaDocLine("* "+GenerateStartUp.TABLE_INFO+" 实体类");
        topLevelClass.addJavaDocLine("* ");
        topLevelClass.addJavaDocLine("* @author "+GenerateStartUp.author);
        topLevelClass.addJavaDocLine("* @created Create Time: " +new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        topLevelClass.addJavaDocLine("*/");

        // 生成注释结束
    }


    /**
     * 接口注释生成
     *
     * @param interfaze
     * @param explain
     */
    public static void interfazeAnnotation(Interface interfaze, String explain) {
        // 生成注释
        interfaze.addJavaDocLine("/**");
        interfaze.addJavaDocLine("* 开发公司：中节能天融科技有限公司");
        interfaze.addJavaDocLine("* ");
        interfaze.addJavaDocLine("* "+GenerateStartUp.TABLE_INFO +" mapper ");
        interfaze.addJavaDocLine("* ");
        interfaze.addJavaDocLine("* @author "+GenerateStartUp.author);
        interfaze.addJavaDocLine("* @created Create Time: " +new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        interfaze.addJavaDocLine("*/");
        // 生成注释结束
    }

    // 生成controller
    public void generateControllerFile(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) throws IOException, TemplateException {

        String packageController = getProperty("controller");
        if (packageController != null) {
            String packageService = getProperty("service");
            String packageDomain = topLevelClass.getType().getPackageName();
            String[] mulu = getProperty("controller").split("\\.");
            String moduleName = mulu[mulu.length - 1];
            String fileName = topLevelClass.getType().getShortName();

            String path = introspectedTable.getContext().getJavaModelGeneratorConfiguration().getTargetProject() + "/" + getProperty("controller").replaceAll("\\.", "/");
            File catalog = new File(path);
            catalog.mkdirs();
            File mapperFile = new File(path + '/' + fileName + "Controller.java");

            if(mapperFile.exists()){
                System.err.println("controller : "+catalog.getAbsolutePath()+" 已经存在，不生成！");
                return;
            }else{
                System.err.println("controller 生成路径 : "+catalog.getAbsolutePath());
            }


            Template template = FreeMarkerTemplateUtils.getTemplate("Controller.ftl");
            FileOutputStream fos = new FileOutputStream(mapperFile);
            Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);

            Map<String, Object> dataMap = new HashMap<String, Object>();

            dataMap.put("package_domain", packageDomain);
            dataMap.put("package_controller", packageController);
            dataMap.put("package_service", packageService);
            dataMap.put("module_name", moduleName);
            dataMap.put("file_name", fileName);
            dataMap.put("date", new Date());
            dataMap.put("author",GenerateStartUp.author);
            dataMap.put("tabInfo", GenerateStartUp.TABLE_INFO );

            dataMap.put("modeNameCN", GenerateStartUp.modeNameCN );

            template.process(dataMap, out);

        }
    }

    // 生成vo类
    public void generateVoFile(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) throws IOException, TemplateException {
        String packageVo = getProperty("vo");
        if (packageVo != null) {
            String packageDomain = topLevelClass.getType().getPackageName();

            String[] mulu = getProperty("vo").split("\\.");
            String moduleName = mulu[mulu.length - 1];
            String fileName = topLevelClass.getType().getShortName();

            String path =introspectedTable.getContext().getJavaModelGeneratorConfiguration().getTargetProject() + "/" + getProperty("vo").replaceAll("\\.", "/");
            File catalog = new File(path);
            catalog.mkdirs();
            File mapperFile = new File(path + '/' + fileName + "Vo.java");

            if(mapperFile.exists()){
                System.err.println(" vo  : "+catalog.getAbsolutePath()+" 已经存在，不生成！");
                return;
            }else{
                System.err.println(" vo 生成路径 : "+catalog.getAbsolutePath());
            }

            Template template = FreeMarkerTemplateUtils.getTemplate("Vo.ftl");
            FileOutputStream fos = new FileOutputStream(mapperFile);
            Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);

            Map<String, Object> dataMap = new HashMap<String, Object>();

            dataMap.put("package_vo", packageVo);
            dataMap.put("package_domain", packageDomain);
            dataMap.put("module_name", moduleName);
            dataMap.put("file_name", fileName);
            dataMap.put("date", new Date());
            dataMap.put("author",GenerateStartUp.author);
            dataMap.put("tabInfo", GenerateStartUp.TABLE_INFO );
            template.process(dataMap, out);

        }
    }

    // 生成service
    public void generateServiceFile(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) throws IOException, TemplateException {
        String packageService = getProperty("service");
        if (packageService != null) {
            String packageDomain = topLevelClass.getType().getPackageName();
            String[] mulu = getProperty("service").split("\\.");
            String moduleName = mulu[mulu.length - 1];
            String fileName = topLevelClass.getType().getShortName();

            String path = introspectedTable.getContext().getJavaModelGeneratorConfiguration().getTargetProject() + "/" + getProperty("service").replaceAll("\\.", "/");
            File catalog = new File(path);
            catalog.mkdirs();
            File mapperFile = new File(path + '/' + fileName + "Service.java");

            if(mapperFile.exists()){
                System.err.println("service : "+catalog.getAbsolutePath()+" 已经存在，不生成！");
                return;
            }else{
                System.err.println("service 生成路径 : "+catalog.getAbsolutePath());
            }

            Template template = FreeMarkerTemplateUtils.getTemplate("Service.ftl");
            FileOutputStream fos = new FileOutputStream(mapperFile);
            Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);

            Map<String, Object> dataMap = new HashMap<String, Object>();

            dataMap.put("package_domain", packageDomain);
            dataMap.put("package_service", packageService);
            dataMap.put("module_name", moduleName);
            dataMap.put("file_name", fileName);
            dataMap.put("date", new Date());
            dataMap.put("author",GenerateStartUp.author);
            dataMap.put("tabInfo", GenerateStartUp.TABLE_INFO );
            template.process(dataMap, out);

        }
    }


    // 生成实现类
    public void generateImplFile(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) throws IOException, TemplateException {
        String packageImpl = getProperty("impl");
        if (packageImpl != null) {
            String packageService = getProperty("service");
            String packageMapper = introspectedTable.getContext().getJavaClientGeneratorConfiguration().getTargetPackage();

            String packageDomain = topLevelClass.getType().getPackageName();

            String fileName = topLevelClass.getType().getShortName()+"Service";
            String path = introspectedTable.getContext().getJavaModelGeneratorConfiguration().getTargetProject() + "/" + (getProperty("impl")+".impl").replaceAll("\\.", "/");
            File catalog = new File(path);
            catalog.mkdirs();
            File mapperFile = new File(path + '/' + fileName + "Impl.java");

            if(mapperFile.exists()){
                System.err.println("service impl  : "+catalog.getAbsolutePath()+" 已经存在，不生成！");
                return;
            }else{
                System.err.println("service impl 生成路径 : "+catalog.getAbsolutePath());
            }

            Template template = FreeMarkerTemplateUtils.getTemplate("Impl.ftl");
            FileOutputStream fos = new FileOutputStream(mapperFile);
            Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);

            Map<String, Object> dataMap = new HashMap<String, Object>();

            dataMap.put("package_impl", packageImpl+".impl");
            dataMap.put("package_service", packageService);
            dataMap.put("package_mapper", packageMapper);
            dataMap.put("package_domain", packageDomain);
            dataMap.put("file_name", topLevelClass.getType().getShortName());
            dataMap.put("date", new Date());
            dataMap.put("clazz", fileName);
            dataMap.put("author",GenerateStartUp.author);
            dataMap.put("tabInfo", GenerateStartUp.TABLE_INFO );
            template.process(dataMap, out);

        }
    }


    // 生成实现类
    public void generateBusinessMapperFile(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) throws IOException, TemplateException {
        String packageImpl = getProperty("impl");
        if (packageImpl != null) {
            String packageMapper = introspectedTable.getContext().getJavaClientGeneratorConfiguration().getTargetPackage();

            String fileName = topLevelClass.getType().getShortName()+"Mapper.xml";
            String path = introspectedTable.getContext().getSqlMapGeneratorConfiguration().getTargetProject() +'/'
                    + introspectedTable.getContext().getSqlMapGeneratorConfiguration().getTargetPackage().replace(".auto.",".business.").replaceAll("\\.", "/");;

            File catalog = new File(path );
            catalog.mkdirs();
            File mapperFile = new File(path + '/' + fileName );

            if(mapperFile.exists()){
                System.err.println("bussiness mapper.xml   : "+catalog.getAbsolutePath()+" 已经存在，不生成！");
                return;
            }else{
                System.err.println("bussiness mapper.xml  生成路径 : "+catalog.getAbsolutePath());
            }

            Template template = FreeMarkerTemplateUtils.getTemplate("BussinessMapper.ftl");
            FileOutputStream fos = new FileOutputStream(mapperFile);
            Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);

            Map<String, Object> dataMap = new HashMap<String, Object>();


            dataMap.put("package_mapper", packageMapper);

            dataMap.put("file_name", topLevelClass.getType().getShortName());
            dataMap.put("date", new Date());
            template.process(dataMap, out);

        }
    }
}
