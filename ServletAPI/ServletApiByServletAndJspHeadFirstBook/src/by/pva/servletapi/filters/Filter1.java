package by.pva.servletapi.filters;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class Filter1 implements Filter {

	public void init(FilterConfig arg0) throws ServletException {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

		PrintWriter out = resp.getWriter();
		out.print("filter1 is invoked before<br>");

		chain.doFilter(req, resp);// sends request to next resource.

		out.print("filter1 is invoked after");

	}

	public void destroy() {
	}

}
