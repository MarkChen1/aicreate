/**
 * Copyright(C) 2017 MassBot Co. Ltd. All rights reserved.
 *
 */
package cc.aicreate.config.mvc.exception;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义异常解析器
 * 
 * @since 2017年3月21日 下午6:55:12
 * @version $Id$
 * @author JiangJibo
 *
 */
public class CustomizedExceptionResolver implements HandlerExceptionResolver {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		if (ex instanceof CustomizedException || ex instanceof TimeoutException) {
			CustomizedException e = (CustomizedException) ex;
			String message = e.getLocalizedMessage();
			try {
				response.setHeader("Content-type", MediaType.TEXT_HTML_VALUE + ";charset=UTF-8");
				response.getOutputStream().write(message.getBytes(Charset.forName("UTF-8")));
				return new ModelAndView();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

}
