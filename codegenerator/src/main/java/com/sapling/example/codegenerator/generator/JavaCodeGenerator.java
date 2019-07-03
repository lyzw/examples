package com.sapling.example.codegenerator.generator;

import com.sapling.common.tools.common.StringUtil;
import com.sapling.common.tools.system.SystemInfoUtil;
import com.sapling.common.tools.text.PlaceHolderUtil;
import com.sapling.example.codegenerator.constants.GeneratorConstants;
import com.sapling.example.codegenerator.constants.JavaCodeConstants;
import com.sun.org.apache.regexp.internal.RE;

import java.util.Map;

/**
 * @author wei.zhou02
 * @date 2019/4/28 14:24
 */
public class JavaCodeGenerator {


    public static String genPackageLine(String packageName) {
        return String.format(JavaCodeConstants.PACKAGE_LINE_PATTEN, packageName);
    }


    public static String genPublicClassDefineLine(String className) {
        return String.format(JavaCodeConstants.PATTEN_PUBLIC_CLASS_DEFINE_LINE, className);
    }

    public static String genGetter(Map<String, Class> params) {
        StringBuilder sb = new StringBuilder();
        params.values().stream().forEach(f -> {
        });
        return null;
    }

    public static String genGetter(String paramName, Class clazz) {
        StringBuilder sb = new StringBuilder();
        sb.append(GeneratorConstants.JAVA_LINE_SPERATOR)
                .append(JavaCodeConstants.TAB)
                .append(JavaCodeConstants.PUBLIC)
                .append(JavaCodeConstants.SPACE)
                .append(clazz.getSimpleName())
                .append(JavaCodeConstants.SPACE)
                .append("get")
                .append(StringUtil.upperFirstLetter(paramName))
                .append(JavaCodeConstants.LEFT_PARENTHESES)
                .append(JavaCodeConstants.RIGHT_PARENTHESES)
                .append(JavaCodeConstants.LEFT_BARCE)
                .append(GeneratorConstants.JAVA_LINE_SPERATOR)
                .append(StringUtil.repeat(JavaCodeConstants.TAB, 2))
                .append(JavaCodeConstants.RETURN)
                .append(JavaCodeConstants.SPACE)
                .append(JavaCodeConstants.SLEF)
                .append(JavaCodeConstants.DOT)
                .append(paramName)
                .append(JavaCodeConstants.LINEEND)
                .append(GeneratorConstants.JAVA_LINE_SPERATOR)
                .append(StringUtil.repeat(JavaCodeConstants.TAB, 1))
                .append(JavaCodeConstants.RIGHT_BARCE)
                .append(GeneratorConstants.JAVA_LINE_SPERATOR);
        return sb.toString();

    }

    public static String genPublicClassComment(String commentTemplate, Map<String, String> properties, int lineLength) {
        // 如果为空，则不生成ClassComment
        if (StringUtil.isEmpty(commentTemplate)) {
            return "";
            //如果以“/**” 开头，则表示模板是标准的注释
        } else if (commentTemplate.contains(JavaCodeConstants.DOC_COMMENT_BEGIN)) {
            return PlaceHolderUtil.calculatePlaceHolder(properties, commentTemplate);
        } else { //否则模板是普通的注释文本内容
            String[] lines = commentTemplate.split(SystemInfoUtil.getLineSeparator());


            return null;
        }
    }

    public static String genNonParamConstructor(String className) {
        return String.format(JavaCodeConstants.PATTEN_NON_PARAM_CONSTRUCTOR, className);
    }

    public static String genPublicClassEndingLine() {
        return JavaCodeConstants.RIGHT_BARCE;
    }


    public static void main(String[] args) {
        System.out.println(genGetter("name",String.class));
    }
}
