<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JCache Management</title>
    <link rel="stylesheet" href="webjars/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="webjars/bootstrap/3.3.6/css/bootstrap-theme.min.css">
</head>
<body>
<div class="container theme-showcase" role="main">

    <div class="jumbotron">
        <div class="container">
            <h1>JCache Management</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-2">
            Statistics
            <div class="btn-group btn-toggle">
                <form action="jcache" method="post">
                    <c:if test="${statistics == false}">
                        <button class="btn btn-default" onclick="reload('true', this)">ON</button>
                        <button class="btn active btn-info" onclick="reload('false', this)">OFF</button>
                    </c:if>
                    <c:if test="${statistics == true}">
                        <button class="btn active btn-info" onclick="reload('true', this)">ON</button>
                        <button class="btn btn-default" onclick="reload('false', this)">OFF</button>
                    </c:if>
                    <input type="hidden" id="statistics" name="statistics">
                </form>
            </div>
        </div>
    </div>

    <div class="page-header">
        <h1>Running Caches</h1>
    </div>

    <c:forEach var="cache" items="${caches.allWithStatistics}">
        <div class="col-sm-3">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title"><c:out value="${cache.name}"/></h3>
                </div>
                <div class="panel-body">
                    <c:if test="${cache.statisticsEnabled}">
                        <table class="table table-striped">
                            <tbody>
                            <tr>
                                <td>hits</td>
                                <td><c:out value="${cache.statistics.hits}"/></td>
                            </tr>
                            <tr>
                                <td>misses</td>
                                <td><c:out value="${cache.statistics.misses}"/></td>
                            </tr>
                            <tr>
                                <td>hit percentage</td>
                                <td><c:out value="${cache.statistics.hitPercentage}"/>&nbsp;%</td>
                            </tr>
                            <tr>
                                <td>miss percentage</td>
                                <td><c:out value="${cache.statistics.missPercentage}"/>&nbsp;%</td>
                            </tr>
                            <tr>
                                <td>gets</td>
                                <td><c:out value="${cache.statistics.gets}"/></td>
                            </tr>
                            <tr>
                                <td>puts</td>
                                <td><c:out value="${cache.statistics.puts}"/></td>
                            </tr>
                            <tr>
                                <td>removals</td>
                                <td><c:out value="${cache.statistics.removals}"/></td>
                            </tr>
                            <tr>
                                <td>evictions</td>
                                <td><c:out value="${cache.statistics.evictions}"/></td>
                            </tr>
                            <tr>
                                <td>average get time</td>
                                <td><c:out value="${cache.statistics.averageGetTime}"/>&nbsp;ms</td>
                            </tr>
                            <tr>
                                <td>average put time</td>
                                <td><c:out value="${cache.statistics.averagePutTime}"/>&nbsp;ms</td>
                            </tr>
                            <tr>
                                <td>average remove time</td>
                                <td><c:out value="${cache.statistics.averageRemoveTime}"/>&nbsp;ms</td>
                            </tr>
                            </tbody>
                        </table>
                    </c:if>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<script src="webjars/jquery/2.1.4/jquery.min.js"></script>
<script>
    function reload(enabled, button) {
        document.getElementById('statistics').value = enabled;
        $(button).closest('form').submit();
    }
</script>
</body>
</html>
