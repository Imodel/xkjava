package cloud.simple.filter;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class AccessPasswordFilter extends ZuulFilter {

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		System.out.println(String.format("%s AccessPasswordFilter request to %s", request.getMethod(), request.getRequestURL().toString()));
		
		String username = request.getParameter("password");
		if(null!=username&&username.equals("123456")){
			ctx.setSendZuulResponse(true);
			ctx.setResponseStatusCode(200);
			ctx.set("isSuccess",true);
		}else{
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			ctx.setResponseBody("{\"result\":\"password is not correct!\"}");
			ctx.set("isSuccess",true);
		}
		return null;
	}

	@Override
	public boolean shouldFilter() {
		//RequestContext ctx = RequestContext.getCurrentContext();
		//return (boolean)ctx.getBoolean("isSuccess");
		return false;
	}

	@Override
	public int filterOrder() {
		return 2;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
