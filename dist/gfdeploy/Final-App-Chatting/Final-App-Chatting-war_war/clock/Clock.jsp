<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<% 
    Calendar calendar = new GregorianCalendar();

    int hour = calendar.get(Calendar.HOUR);
    int minute = calendar.get(Calendar.MINUTE);
    int second = calendar.get(Calendar.SECOND);
    String am_pm = (calendar.get(Calendar.AM_PM) == 0)? "AM" : "PM";
    
    out.println("<header>" + hour+":"+ minute +":"+ second +" "+ am_pm + "</header>");
%>