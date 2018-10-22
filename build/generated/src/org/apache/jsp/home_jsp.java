package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Test Online</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style.css\">\n");
      out.write("<!--        <script src=\"jquery-3.2.1.min.js\"-->\n");
      out.write("        <script type=\"text/javascript\" src=\"js/jquery-3.2.1.min.js\"></script>\n");
      out.write("        <script TYPE=\"text/javascript\">\n");
      out.write("            $(document).ready(function(){\n");
      out.write("                \n");
      out.write("            });\n");
      out.write("            function checkPermission(testName) {\n");
      out.write("//                var isMember = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${currentUser.isNotGuest()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("';\n");
      out.write("                if ('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${currentUser.isNotGuest()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("') {\n");
      out.write("                    return true;\n");
      out.write("                } else {\n");
      out.write("                    alert(\"Please login to do \" + testName + \" test.\");\n");
      out.write("                    return false;\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        \n");
      out.write("        <h1 style=\"text-align: center; font-size: 30px;\">Welcome to Test Online</h1>\n");
      out.write("        <h1>\n");
      out.write("            ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${currentUser.fullName() == null || currentUser.fullName() == \"\" ? \"Guest\" : currentUser.fullName()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("<br/>\n");
      out.write("            ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${role.getName()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("<br/>\n");
      out.write("        </h1>\n");
      out.write("        <!--login - register - logout-->-->\n");
      out.write("        ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${topBtn}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\n");
      out.write("        \n");
      out.write("        <div class=\"container\">\n");
      out.write("            <a href='/test/quick_test'><div class=\"div-btn quick-test-btn test-section-btn\"><span class=\"test-section-text\">Q U I C K &nbsp; T E S T</span></div></a>\n");
      out.write("            <a onclick=\"return checkPermission('reading')\" href='/test/home'><div class=\"div-btn reading-btn test-section-btn\"><span class=\"test-section-text\">R E A D I N G</span></div></a>\n");
      out.write("            <a onclick=\"return checkPermission('writing')\" href='/test/home'><div class=\"div-btn test-section-btn writing-btn\"><span class=\"test-section-text\">W R I T I N G</span></div></a>\n");
      out.write("            <a onclick=\"return checkPermission('listening')\" href='/test/home'><div class=\"div-btn test-section-btn listening-btn\"><span class=\"test-section-text\">L I S T E N I N G</span></div></a>\n");
      out.write("            <a onclick=\"return checkPermission('speaking')\" href='/test/home'><div class=\"div-btn test-section-btn speading-btn\"><span class=\"test-section-text\">S P E A K I N G</span></div></a>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <br/><br/><br/>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
