package com.yc.damai.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.yc.damai.bean.Product;

public class ProductMapperTest {
	
	@Test
	public void testSelectAll() throws IOException{
		// ��ʼ�� MyBatis ��
		String resource = "mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// SqlSessionFactoryBuilder �Ự�����Ĺ�����
		// SqlSessionFactory  �Ự����
		// SqlSession   �Ự
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		List<Product> list = session.selectList("com.yc.damai.dao.ProductMapper.selectAll");
		System.out.println(list.size());
		
		System.out.println(list.get(0));
	}

}
