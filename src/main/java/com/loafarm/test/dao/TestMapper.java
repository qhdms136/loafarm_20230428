package com.loafarm.test.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.loafarm.test.model.Test;

@Repository
public interface TestMapper {
	public List<Test> selectTestList();
}
