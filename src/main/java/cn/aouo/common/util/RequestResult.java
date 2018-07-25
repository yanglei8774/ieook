package cn.aouo.common.util;


import java.io.Serializable;

/**
 * 通用处理结果类
 */
public class RequestResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 异常编码  **/
    public static final int CODE_EXCEPTION = 0;

    /** 正常编码  **/
    public static final int CODE_NORMAL = 1;

    /** 警告编码  **/
    public static final int CODE_WARN = 2;

    /** 逻辑错误编码 */
    public static final int CODE_LOGIC_ERROR = 4;

    /**
     * 错误编码
     */
    private int code;

    /**
     * 成功标识
     */
    private boolean isSuccess = true;

    /**
     * 错误消息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    private int totalCount;
    private int pageSize;
    private int pageIndex;

	 public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public RequestResult() {
    }

    public RequestResult(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public RequestResult(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }
    public RequestResult(boolean isSuccess, String message, Object data) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.data=data;
    }

    public void setResult(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public void setResult(boolean isSuccess, String message, Object data) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
