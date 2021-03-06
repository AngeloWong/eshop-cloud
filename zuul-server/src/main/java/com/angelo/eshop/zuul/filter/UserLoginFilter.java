package com.angelo.eshop.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserLoginFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(UserLoginFilter.class);

    // pre，routing，post，error
    @Override
    public String filterType() {
        return "pre";
    }

    // 顺序
    @Override
    public int filterOrder() {
        return 0;
    }

    // 根据逻辑判断是否要过滤
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));

        Object userId = request.getParameter("userId");
        if (userId == null) {
            logger.warn("userId is empty, not allow to access website");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("userId is empty, not allow to access website");
            } catch (Exception e) {
                logger.error("send response error", e);
            }
            return null;
        }

        logger.info("userId is ok, allow to access website");
        return null;
    }
}
