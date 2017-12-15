package com.fanye.util;

/**
 * ��ҳ������
 * @author fanye
 *
 */
public class PageUtil {

	/**
	 * ��ȡ��ҳ����
	 * @param targeUrl	Ŀ���ַ
	 * @param totalNum	�ܼ�¼��
	 * @param currentPage	��ǰҳ
	 * @param pageSize	ÿҳ��С
	 * @return
	 */
	public static String getPagation(String targeUrl,int totalNum,int currentPage,int pageSize){
		int totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
		if(totalPage==0){
			return "<font color=red>δ��ѯ�����ݣ�</font>";
		}
		StringBuffer pageCode=new StringBuffer();
		pageCode.append("<li><a href='"+targeUrl+"?page=1'>��ҳ</a></li>");
		if(currentPage==1){
			pageCode.append("<li class='disabled'><a href='#'>��һҳ</a></li>");
		}else{
			pageCode.append("<li><a href='"+targeUrl+"?page="+(currentPage-1)+"'>��һҳ</a></li>");
		}
		
		for(int i=currentPage-2;i<=currentPage+2;i++){
			if(i<1||i>totalPage){
				continue;
			}
			if(i==currentPage){
				pageCode.append("<li class='active'><a href='#'>"+i+"</li>");
			}else{
				pageCode.append("<li><a href='"+targeUrl+"?page="+i+"'>"+i+"</li>");
			}
		}
		
		if(currentPage==totalPage){
			pageCode.append("<li class='disabled'><a href='#'>��һҳ</a></li>");
		}else{
			pageCode.append("<li><a href='"+targeUrl+"?page="+(currentPage+1)+"'>��һҳ</a></li>");
		}
		pageCode.append("<li><a href='"+targeUrl+"?page="+totalPage+"'>βҳ</li>");
		return pageCode.toString();
	}
}
