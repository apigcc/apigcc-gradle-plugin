package com.github.apiggs;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.util.HashMap;
import java.util.Map;

public class ApiggsPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        Map<String,Object> attrs = new HashMap<>();
        attrs.put("name","apiggs");
        attrs.put("type",ApiggsTask.class);
        attrs.put("group","Documentation");
        project.getTasks().create(attrs);
    }
}
