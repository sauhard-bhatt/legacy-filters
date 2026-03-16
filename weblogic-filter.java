public class EntraToSiteMinderHeaderBridgeFilter implements Filter {

private static final String ENTRA_USER = "X-ENTRA-USER";
private static final String ENTRA_ROLES = "X-ENTRA-ROLES";

private static final String SM_USER = "SM_USER";
private static final String SM_ROLE = "SM_ROLE";

public void doFilter(ServletRequest request, ServletResponse response,
FilterChain chain) throws IOException, ServletException {

HttpServletRequest httpReq = (HttpServletRequest) request;

final String user = httpReq.getHeader(ENTRA_USER);
final String roles = httpReq.getHeader(ENTRA_ROLES);

HttpServletRequest wrapped = new HttpServletRequestWrapper(httpReq) {

@Override
public String getHeader(String name) {

if (SM_USER.equalsIgnoreCase(name)) return user;
if (SM_ROLE.equalsIgnoreCase(name)) return roles;

return super.getHeader(name);
}
};

chain.doFilter(wrapped, response);
}
}
