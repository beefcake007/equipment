package com.fanye.dao;

import java.util.List;

import com.fanye.model.Department;
import com.fanye.model.PageBean;

public interface DepartmentDao {

	public List<Department> find(PageBean pageBean,Department s_department);
	
	public int count(Department s_department);
	
	public void add(Department department);
	
	public void update(Department department);
	
	public void delete(int id);
	
	public Department loadById(int id);
}
