
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CCMS</title>
</head>
<body>
<sql:setDataSource var="ds" driver="com.mysql.cj.jdbc.Driver"  
     url="jdbc:mysql://localhost:3306/ccms?useSSL=false"  
     user="root"  password="Parag@12345"/>  
<sql:query dataSource="${ds}" var="rs">select * from roles where not roleId = '1' ;
</sql:query>
<sql:query dataSource="${ds}" var="allRoles">select * from roles;
</sql:query>

</body>
</html>