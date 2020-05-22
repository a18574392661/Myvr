package com.bootdo.vrs.filter;

import com.bootdo.vrs.service.IpgetCountService;
import com.bootdo.vrs.service.impl.IpgetCountServiceImpl;
import com.bootdo.vrs.util.ApplicationContextProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;


public class ProCountFilter implements Filter{



    /**
     * 需要过滤的地址
     */
    private static List<String> urlList = Arrays.asList("/pro/h5/proMengchuang","/pro/h5/proHuace","/pro/h5/proDetailed","/pro/h5/proHuance");

    /**
     * 是否不需要过滤
     *
     * @param requestUrl
     *            请求的url
     * @return
     */
    public boolean isPast(String requestUrl) {
        for (String url : urlList) {
            if (requestUrl.equals(url)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void destroy() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {


        String url = ((HttpServletRequest) request).getRequestURI().substring(((HttpServletRequest)request).getContextPath().length());

        //通过地址对特定的请求进行处理，如果不需要可以不用，如果不用，就会对使用的请求进行过滤
        /*if (isPast(url)) {
            IpgetCountService ipgetCountService= ApplicationContextProvider.getBean(IpgetCountService.class);
          String id=request.getParameter("id");
          if (id==null||"".equals(id)){
              chain.doFilter(request, response);
          }
            ipgetCountService.queryProCount(Integer.parseInt(id));


        }
*/
            chain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }





}
