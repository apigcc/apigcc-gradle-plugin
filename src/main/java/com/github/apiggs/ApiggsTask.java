package com.github.apiggs;

import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskAction;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Gradle构建配置类
 * 配置项参考
 * {@link Environment}
 */
public class ApiggsTask extends DefaultTask {

    String id;
    String title;
    String description;
    String out;
    //groovy传字符串，使用逗号分隔
    String source;
    String dependency;
    String jar;
    String ignore;
    String version;

    @TaskAction
    public void action() {

        Project project = getProject();

        Environment env = new Environment();
        if (source != null) {
            for (String dir : source.split(",")) {
                Path path = resolve(dir);
                env.source(path);
                System.out.println("source "+path);
            }
        } else {
            Path source = project.getProjectDir().toPath().resolve(Environment.DEFAULT_SOURCE_STRUCTURE);
            System.out.println("source " + source);
            env.source(source);
        }
        if (dependency != null) {
            String[] dirs = dependency.split(",");
            for (String dir : dirs) {
                Path path = resolve(dir);
                env.dependency(path);
                System.out.println("dependency "+path);
            }
        }
        if (jar != null) {
            for (String dir : jar.split(",")) {
                Path path = resolve(dir);
                env.jar(path);
                System.out.println("jar "+path);
            }
        }
        if (id != null) {
            env.id(id);
        } else {
            env.id(project.getName());
        }
        if (out != null) {
            Path path = resolve(out);
            env.out(path);
        } else {
            env.out(project.getBuildDir().toPath());
        }
        if (title != null) {
            env.title(title);
        } else {
            env.title(project.getName());
        }
        if (description != null) {
            env.description(description);
        } else if(project.getDescription()!=null){
            env.description(project.getDescription());
        }
        if (version != null){
            env.version(version);
        } else if (project.getVersion()!=null){
            env.version(project.getVersion().toString());
        }
        if (ignore != null) {
            env.ignore(ignore.split(","));
        }

        new Apiggs(env).lookup().build();

        System.out.println("\r\n\napiggs build on "+env.getOut());

    }

    private Path resolve(String dir){
        Path path = Paths.get(dir);
        if(path.isAbsolute()){
            return path;
        }else{
            return getProject().getProjectDir().toPath().resolve(path);
        }
    }

}
