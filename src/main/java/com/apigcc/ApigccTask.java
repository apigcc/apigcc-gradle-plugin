package com.apigcc;

import com.apigcc.core.Apigcc;
import com.apigcc.core.Context;
import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskAction;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Gradle构建配置类
 * 配置项参考
 */
public class ApigccTask extends DefaultTask {

    String id;
    String name;
    String description;
    String build;
    //groovy传字符串，使用逗号分隔
    String source;
    String dependency;
    String jar;
    String version;
    String css;

    @TaskAction
    public void action() {

        Project project = getProject();

        Context context = new Context();
        if (source != null) {
            for (String dir : source.split(",")) {
                Path path = resolve(dir);
                context.addSource(path);
            }
        } else {
            project.getAllprojects().forEach(p -> context.addSource(p.getProjectDir().toPath().resolve(Context.DEFAULT_CODE_STRUCTURE)));
        }
        if (dependency != null) {
            String[] dirs = dependency.split(",");
            for (String dir : dirs) {
                Path path = resolve(dir);
                context.addDependency(path);
            }
        } else {
            project.getRootProject().getAllprojects().forEach(p-> context.addDependency(p.getProjectDir().toPath().resolve(Context.DEFAULT_CODE_STRUCTURE)));
        }
        if (jar != null) {
            for (String dir : jar.split(",")) {
                Path path = resolve(dir);
                context.addJar(path);
            }
        }
        if (id != null) {
            context.setId(id);
        } else {
            context.setId(project.getName());
        }
        if (build != null) {
            Path path = resolve(build);
            context.setBuildPath(path);
        } else {
            context.setBuildPath(project.getBuildDir().toPath());
        }
        if (name != null) {
            context.setName(name);
        } else {
            context.setName(project.getName());
        }
        if (description != null) {
            context.setDescription(description);
        } else if (project.getDescription() != null) {
            context.setDescription(project.getDescription());
        }
        if (css != null) {
            context.setCss(css);
        }

        Apigcc apigcc = new Apigcc(context);
        apigcc.parse();
        apigcc.render();

    }

    private Path resolve(String dir) {
        Path path = Paths.get(dir);
        if (path.isAbsolute()) {
            return path;
        } else {
            return getProject().getProjectDir().toPath().resolve(path);
        }
    }

}
