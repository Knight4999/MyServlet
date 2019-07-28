package tag;

        import javax.servlet.jsp.PageContext;
        import javax.servlet.jsp.tagext.SimpleTagSupport;
        import java.io.IOException;
        import java.text.SimpleDateFormat;
        import java.util.Date;

public class DateTag extends SimpleTagSupport {
    private String pattern;
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public void doTag() throws IOException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String time =  sdf.format(date);
        PageContext con = (PageContext) getJspContext();
        con.getOut().println(time);
    }

}
