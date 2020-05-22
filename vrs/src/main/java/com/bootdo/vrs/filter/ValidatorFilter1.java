package com.bootdo.vrs.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public class ValidatorFilter1  implements Filter{



    /**
     * 需要过滤的地址
     */
    private static List<String> urlList = Arrays.asList("/vrs/pro/list","/vrs/pro/listProTie");

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

        System.out.println("进了过滤器");
        String url = ((HttpServletRequest) request).getRequestURI().substring(((HttpServletRequest)request).getContextPath().length());

        //通过地址对特定的请求进行处理，如果不需要可以不用，如果不用，就会对使用的请求进行过滤
        if (isPast(url)) {
            System.out.println("需要拿到参数");
            MyHttpServletRequestWrapper1 requestWrapper1 = new MyHttpServletRequestWrapper1(
                    (HttpServletRequest) request);
            // 1.获取需要处理的参数
         /*   String email = requestWrapper1.getParameter("email");
            // 2.把处理后的参数放回去（这里是大小转小写处理）
            requestWrapper1.setParameter("email", email.toLowerCase());*/
            String sql="";
            Enumeration em = request.getParameterNames();
            while (em.hasMoreElements()) {
                String name = (String) em.nextElement();
                String[] value = request.getParameterValues(name);
                for (int i = 0; i < value.length; i++) {
                    sql = sql + value[i];
                }



            }


            if (sqlValidate(sql)) {
                throw new IOException("您发符送请求中的参数中含有非法字");

            } else {
                chain.doFilter(request,response);
            }

        } else {
            System.out.println("不需要拿到参数");
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }


    protected static boolean sqlValidate(String str) {
        str = str.toLowerCase();//统一转为小写
        String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|" +
                "char|declare|sitename|net user|xp_cmdshell|;|or|-|+|,|like'|and|exec|execute|insert|create|drop|" +
                "table|from|grant|use|group_concat|column_name|" +
                "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|" +
                "chr|mid|master|truncate|char|declare|or|;|-|--|+|,|like|//|/|%|#";//过滤掉的sql关键字，可以手动添加
        String[] badStrs = badStr.split("\\|");
        for (int i = 0; i < badStrs.length; i++) {
            if (str.indexOf(badStrs[i]) >= 0) {
                return true;
            }
        }
        return false;
    }




}
