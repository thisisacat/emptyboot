package cn.notacat.cat.biz.exception.status;

public enum StatusCode implements EnumStatus {
    SUCCESS(1, "操作成功"),
    BUSSINESS_ERROR(-1,"业务异常"),
    UNKNOW_ERROR(-1000, "未知异常"),
    SYSTEM_ERROR(-1001,"系统异常"),
    PARAM_ERROR(-1002, "参数不正确"),
    DATABASE_ERROR(-1003, "数据库异常"),
    ENCRYPT_ERROR(-1004, "加解密异常"),
    CACHE_ERROR(-1005, "缓存异常"),
    UNAUTHORIZED_ERROR(-1006, "未授权"),
    NOT_FOUND_ERROR(-1007,"请求不存在"),
    MAXIMUM_UPLOAD_SIZE_ERROR(-1008,"上传文件太大"),
    MEDIATYPE_ERROR(-1009,"MediaType错误"),;



    private int status;
    private String msg;

    private StatusCode(int status, String msg) {
        this.msg = msg;
        this.status = status;
    }

    @Override
    public int getStatus() {
		return status;
	}

	@Override
	public String getMsg() {
        return this.msg;
    }
}
