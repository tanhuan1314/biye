<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>匹配结果管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/lwmatch/lwMatchResult/">匹配结果列表</a></li>
		<shiro:hasPermission name="lwmatch:lwMatchResult:edit"><li><a href="${ctx}/lwmatch/lwMatchResult/form">匹配结果添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lwMatchResult" action="${ctx}/lwmatch/lwMatchResult/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>匹配文件</th>
				<th>模型文件</th>
				<th>阀值</th>
				<th>相似度</th>
				<th>匹配结果</th>
				<th>创建时间</th>
				<shiro:hasPermission name="lwmatch:lwMatchResult:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lwMatchResult">
			<tr>
				<td><a href="${ctx}/lwmatch/lwMatchResult/form?id=${lwMatchResult.id}">
					${lwMatchResult.matchname}
				</a></td>
				<td>
					${lwMatchResult.modelname}
				</td>
				<td>
					${lwMatchResult.tsvalue}
				</td>
				<td>
					${lwMatchResult.matchvalue}
				</td>
				<td>
					${lwMatchResult.result}
				</td>
				<td>
					<fmt:formatDate value="${lwMatchResult.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="lwmatch:lwMatchResult:edit"><td>
    				<a href="${ctx}/lwmatch/lwMatchResult/form?id=${lwMatchResult.id}">查看结果</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>