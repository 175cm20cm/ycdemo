package com.yc.favorite.biz;

import org.junit.Assert;
import org.junit.Test;

import com.yc.favorite.bean.Favorite;


public class FavoriteBizTest {
	/**
	 * ������ӵ��������
	 * tags �ֶΣ����֣����£�����
	 * 1���ж�tag�Ƿ����
	 * 2����������insert
	 * 3��������update
	 * ���favorite
	 * insert
	 * 
	 */
	@Test
	public void testInsert() {
		FavoriteBiz biz=new FavoriteBiz();
		Favorite f=new Favorite();
		f.setfLabel("�Ա�");
		f.setfUrl("www.taobao.com");
		f.setfTags("������֣�����");
		f.setfDesc("����վ");
		biz.addFavorite(f);
		Assert.assertNotNull(f.getfId());
	}
}
