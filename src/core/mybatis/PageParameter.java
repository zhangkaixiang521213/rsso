package core.mybatis;

import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * 分页参数类
 * 
 */
public class PageParameter implements Page{

    public static final int DEFAULT_PAGE_SIZE = 3;

    private int pageSize;
    private int currentPage;
    private int prePage;
    private int nextPage;
    private int totalPage;
    private int totalCount;

    public PageParameter() {
        this.currentPage = 1;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    /**
     * 
     * @param currentPage
     * @param pageSize
     */
    public PageParameter(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
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

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

	@Override
	public List getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		 return currentPage;
	}

	@Override
	public int getNumberOfElements() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		 return pageSize;
	}

	@Override
	public Sort getSort() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getTotalElements() {
		// TODO Auto-generated method stub
		 return totalCount;
	}

	@Override
	public int getTotalPages() {
		// TODO Auto-generated method stub
		 return totalPage;
	}

	@Override
	public boolean hasContent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasNextPage() {
		// TODO Auto-generated method stub
		if(currentPage<totalPage){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean hasPreviousPage() {
		// TODO Auto-generated method stub
		if(currentPage>1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean isFirstPage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLastPage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pageable nextPageable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pageable previousPageable() {
		// TODO Auto-generated method stub
		return null;
	}

}
