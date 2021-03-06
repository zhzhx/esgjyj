package com.eastsoft.esgjyj.context;

import com.eastsoft.esgjyj.util.ShiroUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 线程安全的作用域工具类。
 * <p>用于获取当前线程中的 {@link HttpServletRequest} 对象和 {@link HttpServletResponse} 对象。
 * @author chenkai
 * @since archetype-1.0.0
 * @version 1.0.0
 */
public class ScopeUtil {
	private static ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<HttpServletResponse> responseThreadLocal = new ThreadLocal<>();

	/**
	 * 获取当前线程中的 HTTP 请求对象。
	 * @return 当前线程中的 HTTP 请求对象。
	 */
	public static HttpServletRequest getRequest() {
		return requestThreadLocal.get();
	}
	
	/**
	 * 设置当前线程中的 HTTP 请求对象。
	 * @param request HTTP 请求对象。
	 */
	public static void setRequest(HttpServletRequest request) {
		requestThreadLocal.set(request);
	}
	
	/**
	 * 获取当前线程中的 HTTP 响应对象。
	 * @return 当前线程中的 HTTP 响应对象。
	 */
	public static HttpServletResponse getResponse() {
		return responseThreadLocal.get();
	}
	
	/**
	 * 设置当前线程中的 HTTP 响应对象。
	 * @param response HTTP 响应对象。
	 */
	public static void setResponse(HttpServletResponse response) {
		responseThreadLocal.set(response);
	}
	
	/**
	 * 获取 {@linkplain javax.servlet.http.HttpSession HttpSession} 中的用户对象。
	 * @return 用户对象。
	 */
	public static Object getSessionUser() {
		Object user = ScopeUtil.getRequest().getSession().getAttribute("user");
		return user;
	}
	
	/**
	 * 获取 {@linkplain javax.servlet.http.HttpSession HttpSession} 中的用户对象。
	 * @param requiredType 用户对象类型。
	 * @return 用户对象。
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getSessionUser(Class<T> requiredType) {
		//Object user = ScopeUtil.getRequest().getSession().getAttribute("user");
		Object user = ShiroUtils.getUser();
		return (T) user;
	}
	
	/**
	 * 设置 {@linkplain javax.servlet.http.HttpSession HttpSession} 中的用户对象。
	 * @param user 用户对象。
	 */
	public static void setSessionUser(Object user) {
		ScopeUtil.getRequest().getSession().setAttribute("user", user);
	}
}
