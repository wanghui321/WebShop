package com.onest.bean;

public class Page {
	private Long totalcount;//������
	private Integer pagecount=3;//ÿҳ��ʾ������
	private Long totalpage;//��ҳ��
	private Integer dpage;//��ǰ��ʾ��ҳ��
	public Long getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(Long totalcount) {
		this.totalcount = totalcount;
	}
	public Integer getPagecount() {
		return pagecount;
	}
	public void setPagecount(Integer pagecount) {
		this.pagecount = pagecount;
	}
	public Long getTotalpage() {
		return totalpage;
	}
	public void setTotalpage() {
		if(totalcount%pagecount==0) {
			this.totalpage = totalcount/pagecount;
		}else {
			this.totalpage = totalcount/pagecount+1;	
		}
	}
	public Integer getDpage() {
		return dpage;
	}
	public void setDpage(Integer dpage) {
		this.dpage = dpage;
	}
	
}
