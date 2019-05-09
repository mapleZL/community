package com.phkj.vo.system;

import java.io.Serializable;
import java.util.List;

public class Tree implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 6768120337214387717L;
    private Integer      id;
    private String       name;
    private List<Tree>   child;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Tree> getChild() {
		return child;
	}
	public void setChild(List<Tree> child) {
		this.child = child;
	}

}