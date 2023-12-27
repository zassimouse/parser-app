<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: denisharitonenko
  Date: 26.12.23
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <style>
            body {
                font-family: Helvetica, sans-serif;
]            }
            table {
                border-collapse: collapse;
            }
        </style>
    </head>
    <body>
    <main class="m-3">
        <table border="1">
                <tr>
                    <c:forEach items="${tiles}" var="tile">
                        <td>${tile.getTileName()}</td>
                    </c:forEach>
                </tr>
                <tr>
                    <c:forEach items="${tiles}" var="tile">
                        <td>${tile.getTileInfo()[0]}</td>
                    </c:forEach>
                </tr>
                <tr>
                    <c:forEach items="${tiles}" var="tile">
                        <td>${tile.getTileInfo()[1]}</td>
                    </c:forEach>
                </tr>
                <tr>
                    <c:forEach items="${tiles}" var="tile">
                        <td>${tile.getTileInfo()[2]}</td>
                    </c:forEach>
                </tr>
        </table>
        <nav>
            <ul class="pagination">
                <c:if test="${currentPage != 1}">
                    <li>
                        <a href="hello-servlet?currentPage=${currentPage-1}&parserType=${parserType}">Previous</a>
                    </li>
                </c:if>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li>
                                <a>${i} <span class="sr-only">(Current)</span></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="hello-servlet?currentPage=${i}&parserType=${parserType}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage lt noOfPages}">
                    <li>
                        <a href="hello-servlet?currentPage=${currentPage+1}&parserType=${parserType}">Next</a>
                    </li>
                </c:if>
            </ul>
        </nav>
        <a href="index.jsp">Start Page</a>
    </main>
    </body>
</html>
