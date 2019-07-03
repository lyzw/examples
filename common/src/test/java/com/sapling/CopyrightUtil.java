package com.sapling;

import java.io.File;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/9/17
 * @since v1.0
 */
public class CopyrightUtil {

    String copyright = "/*\n" +
            " * Copyright 2002-2015 the original author or authors.\n" +
            " *\n" +
            " * Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
            " * you may not use this file except in compliance with the License.\n" +
            " * You may obtain a copy of the License at\n" +
            " *\n" +
            " *      http://www.apache.org/licenses/LICENSE-2.0\n" +
            " *\n" +
            " * Unless required by applicable law or agreed to in writing, software\n" +
            " * distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
            " * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
            " * See the License for the specific language governing permissions and\n" +
            " * limitations under the License.\n" +
            " */" +
            "";

    public static void replaceJavaCopyright(String baseDir){

    }

    public static void replaceJavaFileCopyright(File file){
        if (file.getName().endsWith(".java")){

        }
    }

}