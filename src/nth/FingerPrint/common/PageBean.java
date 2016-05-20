package nth.FingerPrint.common;

import java.io.Serializable;
import java.util.List;

public class PageBean implements Serializable {
	
	
	private List list; //要返回的某一页的内容

	private int allRow;//数据库中满足要求的总记录条数
	
	private int totalPage;//总页数
	
	private int currentPage;//当前第几页
	
	private int pageSize;//每页包含记录的条数
	
	
	private boolean isFirstPage;//是否为第一页
	
	
	private boolean isLastPage;//是否为最后一页
	
	
	private boolean hasPreviousPage;//是否有前一页
	
	
	private boolean hasNextPage;//是否有下一页
	
	public PageBean() {
		super();
	}	
	
	public PageBean(List list, int allRow, int totalPage, int currentPage,
			int pageSize) {
		super();
		this.list = list;
		this.allRow = allRow;
		this.totalPage = totalPage;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}

	public void init()
	{
		this.isFirstPage = isFirstPage();
		this.isLastPage = isLastPage();
		this.hasPreviousPage = isHasPreviousPage();
		this.hasNextPage = isHasNextPage();
	}
	
	/*
	 * 计算总页数
	 */
	
	public static int countTotalPage(final int pageSize,final int allRow)
	{
		 int local_totalPage = allRow % pageSize == 0?allRow/pageSize:allRow/pageSize + 1;
		 return local_totalPage;
	}
	
	/*
	 * 计算查询的起始位置
	 */
	public static int countOffset(final int pageSize, final int currentPage)
	{
		int offset = pageSize * (currentPage - 1) ;
		return offset;
	}
	/*
	 * 确定当前页
	 */
	
	public static int countCurrentPage(int page)
	{
		final int curPage = (page <= 0 ?1:page);
		return curPage;
		
	}
	
	public boolean isFirstPage()
	{
		return currentPage == 1;
	}
	
	public boolean isLastPage()
	{
		return currentPage >= totalPage;
	}
	
	public boolean isHasPreviousPage()
	{
		return currentPage != 1;
	}
	
	public boolean isHasNextPage()
	{
		return currentPage < totalPage;
	}
/*
 * get and set methods 
 */
	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getAllRow() {
		return allRow;
	}

	public void setAllRow(int allRow) {
		this.allRow = allRow;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}

	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}	
}

