<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<nav>
  <ul class="pagination pagination-lg" style="margin:0px auto">
  	<c:if test="${page.hasPrevious }">
		<li>
	  		<a href="?pageNo=${page.pageNo-5 }" aria-label="Previous">
				<span aria-hidden="true">&laquo;</span>
			</a>
		</li>
	</c:if>
	<c:forEach begin="${page.startPage }" end="${page.endPage }" var="i">
		<li
			<c:if test="${i==page.pageNo }">class="disabled"</c:if>
		><a href="?pageNo=${i }">${i }</a></li>
	</c:forEach>
	<c:if test="${page.hasNext }">
		<li>
		  <a href="?pageNo=${page.pageNo+5 }" aria-label="Next">
			<span aria-hidden="true">&raquo;</span>
		  </a>
		</li>
	</c:if>
  </ul>
</nav>