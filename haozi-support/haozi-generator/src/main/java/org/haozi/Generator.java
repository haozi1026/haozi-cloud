package org.haozi;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author zyh
 * @version 1.0
 * @date ${DATE} ${TIME}
 */
public class Generator  {
    public static void main(String[] args) {

        FastAutoGenerator.create("jdbc:mysql://haozi.server:3306/haozi_micro", "haozi", "19960118")
                .globalConfig(builder -> {
                    builder.author("auto-generator") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("/Users/yihaozhao/development/workspace/self/haozi-cloud/haozi-cloud/haozi-upms/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("org.haozi") // 设置父包名
                            .entity("dao.po")
                            .mapper("dao.mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "/Users/yihaozhao/development/workspace/self/haozi-cloud/haozi-cloud/haozi-upms/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("upms_user")
                            .addTablePrefix("upms_");
                })
                .execute();


    }
}
