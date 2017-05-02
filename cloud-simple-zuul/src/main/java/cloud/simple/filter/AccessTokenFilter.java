package cloud.simple.filter;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class AccessTokenFilter extends ZuulFilter {
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		System.out.println(String.format("%s AccessTokenFilter request to %s", request.getMethod(),
				request.getRequestURL().toString()));
		
		ctx.setSendZuulResponse(true); //设置为true继续执行后面的过滤器，flase为不执行
		ctx.setResponseStatusCode(200);
		ctx.setResponseBody("{\"name\":\"haha\"}");// 输出最终结果
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return false;  //是否执行此过滤器,true为执行，false为不执行
	}

	@Override
	public int filterOrder() {
		return 0;  //数值愈小,优先级越高
	}

	@Override
	public String filterType() {
		return "post";// 在请求被处理之后，会进入该过滤器
	}
}
