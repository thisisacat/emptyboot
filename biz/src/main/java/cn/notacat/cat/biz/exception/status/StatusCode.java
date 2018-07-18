package cn.notacat.cat.biz.exception.status;

public enum StatusCode implements EnumStatus {
    SUCCESS(1, "操作成功"),
    BUSSINESS_ERROR(0,"业务异常"),
    UNKNOW_ERROR(-1, "未知异常"),
    SYSTEM_ERROR(-2,"系统异常"),
    PARAM_ERROR(-3, "参数不正确"),
    DATABASE_ERROR(-4, "数据库异常"),
    ENCRYPT_ERROR(-5, "加解密异常"),
    CACHE_ERROR(-6, "缓存异常"),
    UNAUTHORIZED_ERROR(-7, "未授权"),
    NOT_FOUND_ERROR(-8,"请求不存在"),
    MAXIMUM_UPLOAD_SIZE_ERROR(-9,"上传文件太大");


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
