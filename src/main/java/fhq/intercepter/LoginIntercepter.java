package fhq.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginIntercepter implements HandlerInterceptor{
    //在controller之前执行
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse, Object o) throws Exception {
        //方法返回值为true 放行请求 如果返回false时拦截请求
        String username = (String)httpServletRequest.getSession().getAttribute("username");
        if (username==null){
            //没有登录 没有权限访问别的
            httpServletResponse.sendRedirect("/user/log");
            return false;
        }
        return true;
    }
//在controller之前执行
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
//执行完Controller之后执行
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
