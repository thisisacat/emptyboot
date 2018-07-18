package cn.notacat.cat.web.controller;

import cn.notacat.cat.biz.exception.status.EnumStatus;
import cn.notacat.cat.biz.exception.status.StatusCode;

import java.io.Serializable;

public class Response <T> implements Serializable{
	private static final long serialVersionUID = 1L;
	private int status;
	private String msg;
	private T data;
	
	
	public Response(){

	}

	/**通用成功
	 * @param data
	 * @return
	 */
	public static <T> Response<T> success(T data){
		Response<T> response = new Response<T>();
		response.data = data;
		response.status = StatusCode.SUCCESS.getStatus();
		response.msg = StatusCode.SUCCESS.getMsg();
		return response;
	}

	/**
	 * 通用成功
	 * @param <T>
	 * @return
	 */
	public static <T> Response<T> success(){
		return success(null);
	}

	/**
	 * 通用未知异常
	 * @param <T>
	 * @return
	 */
	public static <T> Response<T> unKnowError(String message){
		Response<T> response = new Response<T>();
		response.data = null;
		response.status = StatusCode.UNKNOW_ERROR.getStatus();
		response.msg = message == null?StatusCode.UNKNOW_ERROR.getMsg():message;
		return response;
	}

	/**
	 * 通用未知异常
	 * @param <T>
	 * @return
	 */
	public static <T> Response<T> unKnowError(){
		return unKnowError(null);
	}

	/**
	 * 通用业务异常
	 * @param <T>
	 * @return
	 */
	public static <T> Response<T> bussinessError(String message){
		Response<T> response = new Response<T>();
		response.data = null;
		response.status = StatusCode.BUSSINESS_ERROR.getStatus();
		response.msg = message == null?StatusCode.BUSSINESS_ERROR.getMsg():message;
		return response;
	}

	/**
	 * 通用业务异常
	 * @param <T>
	 * @return
	 */
	public static <T> Response<T> bussinessError(){
		return bussinessError(null);
	}

	/**
	 * 通用系统异常
	 * @param <T>
	 * @return
	 */
	public static <T> Response<T> systemError(String message){
		Response<T> response = new Response<T>();
		response.data = null;
		response.status = StatusCode.SYSTEM_ERROR.getStatus();
		response.msg = message == null?StatusCode.SYSTEM_ERROR.getMsg():message;
		return response;
	}

	/**
	 * 通用系统异常
	 * @param <T>
	 * @return
	 */
	public static <T> Response<T> systemError(){
		return systemError(null);
	}

	/**
	 * 通用数据库异常
	 * @param <T>
	 * @return
	 */
	public static <T> Response<T> databaseError(String message){
		Response<T> response = new Response<T>();
		response.data = null;
		response.status = StatusCode.DATABASE_ERROR.getStatus();
		response.msg = message == null?StatusCode.DATABASE_ERROR.getMsg():message;
		return response;
	}

	/**
	 * 通用数据库异常
	 * @param <T>
	 * @return
	 */
	public static <T> Response<T> databaseError(){
		return databaseError(null);
	}

	/**
	 * 通用参数不正确异常
	 * @param <T>
	 * @return
	 */
	public static <T> Response<T> paramError(String message){
		Response<T> response = new Response<T>();
		response.data = null;
		response.status = StatusCode.PARAM_ERROR.getStatus();
		response.msg = message == null?StatusCode.PARAM_ERROR.getMsg():message;
		return response;
	}


	/**
	 * 通用参数不正确异常
	 * @param <T>
	 * @return
	 */
	public static <T> Response<T> paramError(){
		return paramError(null);
	}


	/**
	 * 通用解密异常
	 * @param <T>
	 * @return
	 */
	public static <T> Response<T> encryptError(String message){
		Response<T> response = new Response<T>();
		response.data = null;
		response.status = StatusCode.ENCRYPT_ERROR.getStatus();
		response.msg = message == null?StatusCode.ENCRYPT_ERROR.getMsg():message;
		return response;
	}

	/**
	 * 通用解密异常
	 * @param <T>
	 * @return
	 */
	public static <T> Response<T> encryptError(){
		return encryptError(null);
	}

	/**
	 * 通用缓存异常
	 * @param <T>
	 * @return
	 */
	public static <T> Response<T> cacheError(String message){
		Response<T> response = new Response<T>();
		response.data = null;
		response.status = StatusCode.CACHE_ERROR.getStatus();
		response.msg = message == null?StatusCode.CACHE_ERROR.getMsg():message;
		return response;
	}

	/**
	 * 通用缓存异常
	 * @param <T>
	 * @return
	 */
	public static <T> Response<T> cacheError(){
		return cacheError(null);
	}

	/**
	 * 通用未授权异常
	 * @param <T>
	 * @return
	 */
	public static <T> Response<T> unauthorizedError(String message){
		Response<T> response = new Response<T>();
		response.data = null;
		response.status = StatusCode.UNAUTHORIZED_ERROR.getStatus();
		response.msg = message == null?StatusCode.UNAUTHORIZED_ERROR.getMsg():message;
		return response;
	}

	/**
	 * 通用未授权异常
	 * @param <T>
	 * @return
	 */
	public static <T> Response<T> unauthorizedError(){
		return unauthorizedError(null);
	}

	/**
	 * 通用请求不存在
	 * @param <T>
	 * @return
	 */
	public static <T> Response<T> notFoundError(String message){
		Response<T> response = new Response<T>();
		response.data = null;
		response.status = StatusCode.NOT_FOUND_ERROR.getStatus();
		response.msg = message == null?StatusCode.NOT_FOUND_ERROR.getMsg():message;
		return response;
	}

	/**
	 * 通用请求不存在
	 * @param <T>
	 * @return
	 */
	public static <T> Response<T> notFoundError(){
		return notFoundError();
	}


	/**
	 * 通用失败异常
	 * @param status
	 * @param message
	 * @param <T>
	 * @return
	 */
	public static <T> Response<T> error(EnumStatus status, String message){
		Response<T> response = new Response<T>();
		response.data = null;
		response.status = status.getStatus();
		response.msg = message == null?status.getMsg():message;
		return response;
	}

	/**
	 * 通用失败异常
	 * @param status
	 * @param <T>
	 * @return
	 */
	public static <T> Response<T> error(EnumStatus status){
		return error(status,null);
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
