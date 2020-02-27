package com.superwork.apcosplatform.common;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TreeNode {

    private String id;
    private String text;
    private List<TreeNode> nodes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<TreeNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeNode> nodes) {
        this.nodes = nodes;
    }
}
