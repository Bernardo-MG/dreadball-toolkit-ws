 <!--
    Copyright 2015 the original author or authors

    Licensed under the Apache License, Version 2.0 (the "License"); you may not
    use this file except in compliance with the License. You may obtain a copy of
    the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
    WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
    License for the specific language governing permissions and limitations under
    the License.
 -->
 <!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html); charset=utf-8"/>
    <meta charset='utf-8'>
    
    <title>Team Unit Assets list</title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link href="../static/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../static/css/theme.css" rel="stylesheet">
</head>
<body>
<section class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <header class="page-header">
                <h1>Team Unit Assets list</h1>
            </header>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Team</th>
                        <th>Details</th>
                    </tr>
                </thead>
                <tbody>
                <#list model as ava>
                <tr>
                    <td>${ava.id}</td>
                    <td>${ava.teamType.name}</td>
                    <td><a href="./assets/${ava.id}">Check details</a></td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</section>
<script src="../static/lib/jquery/jquery-1.11.3.min.js"></script>
<script src="../static/lib/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>