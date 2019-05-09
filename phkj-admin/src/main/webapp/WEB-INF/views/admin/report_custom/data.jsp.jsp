<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.util.*"%>
<head>  
    <title>报表</title>
</head>
<style>
.report_table {width:100%; margin:0;}
.report_table th {background: #e4e4e4; padding:5px; font-weight:bolder}
.report_table td {border-bottom: 1px #ccc dotted; padding:5px;}
</style>
<body style="padding:0; margin:0">
    <table class="report_table">
    	<%
	    List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("mapList");
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    if (list != null && list.size() > 0) {
			Map<String, Object> column = list.get(0);
			String title = "<tr>";
			for (Map.Entry<String, Object> entry : column.entrySet()) {
				String cn = entry.getKey();
				title += "<th>" + cn + "</th>";
			}
			title += "</tr>";
			String content = "";
			for (Map<String, Object> map : list) {
				content += "<tr>";
				for (String dataKey : column.keySet()) {
					Object dataValue = "";
					try {
						dataValue = map.get(dataKey);
						if (dataValue instanceof Timestamp) {
							if (dataValue.toString().contains("1900-01-01")) dataValue = "";			//处理返回日期为空时，sqlserver自动补充1900-01-01，故如果字段结果值中带有1900-01-01，则赋值空
							else dataValue = dateFormat.format(dataValue);
						}
					} catch (Exception e) {
						//System.out.println("error map is " + map + "\nthe key is " + dataKey);
						dataValue = "";
					}
					content += "<td>" + dataValue + "</td>";
				}
				content += "</tr>";
			}
			out.print(title + content);
		}
		%>
</table>
</body>
</html>